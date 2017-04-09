package de.jotwerk.gradoc.mgr;

import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import javax.xml.transform.Templates;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.shiro.SecurityUtils;

import de.jotwerk.gradoc.Emf;
import de.jotwerk.gradoc.Login;
import de.jotwerk.gradoc.model.Item;

public abstract class ItemMgr implements Converter {	
		
		@ManagedProperty(value="#{login}")
		protected Login login;
		public void setLogin(Login login) { this.login = login; }
					
		protected long id = -1;
		public long getId() { return id; }
		public void setId(long id) { this.id = id; }
		
		protected Item curItem;
		public Item getCurItem() { return curItem; }
		
		protected int uses;
		public int getUses() { return uses; }
		
		protected String exportFormat;
		public String getExportFormat() { return exportFormat; }
		public void setExportFormat(String exportFormat) { this.exportFormat = exportFormat; }		
		
		protected abstract Class<? extends Item> getItemClass();
		
		/* Convenience function to get the name of items, which is used - convention over configuration - for forms etc. */		
		public String getForm() { return getItemClass().getSimpleName().toLowerCase(); }
				
		protected Item createItem() {
			Item item = null;
			try {
				item = getItemClass().newInstance();
				postCreate(item);
			} catch (Exception e) {
				System.err.println(getItemClass().getName() + " lässt sich nicht erzeugen");
			}
			return item;
		}
		
		protected void postCreate(Item item) {
			if (item != null) {
				item.setCreated(new Date());
				item.setCreator(login.getAccount().getFullname());
			}
		}
		
		@PostConstruct
		protected void init() {
			curItem = createItem();
			id = -1;
		}
		
		public Item findItem(long id) {
			return login.getEntityManager().find(getItemClass(), id);
		}
						
		public List<? extends Item> getItems() {
			EntityManager em = Emf.createEntityManager();
			TypedQuery<? extends Item> tq = em.createQuery(
				getItemQuery() + " WHERE " + getWhereClause() + getOrderClause(), getItemClass());
			setQueryParameters(tq);
			List<? extends Item> result = tq.getResultList();
			em.close();
			return result;	
		}

		protected String getItemQuery() {
			return "select i from " + getItemClass().getSimpleName() + " i";
		}
		
		protected String getWhereClause() {
			return " i.id > 0";
		}
		
		protected String getOrderClause() {
			return "";
		}		
		
		protected void setQueryParameters(TypedQuery<? extends Item> tq) { 
		}
				
		public String load() {
			if (! checkPermission("load")) {
				login.setMessage("Zugriff auf Eingabemaske nicht gestattet");
				return "/index.xhtml";				
			} else {
				curItem = (id > -1) ? findItem(id) : createItem();
				postLoad(curItem);				
				return "";
			}			
		}
		
		protected void postLoad(Item item) {
			; // to overwrite
		}
				
		public String cancel() {
			init();
			return "";
		}		
		
		public String save() {
			if (checkPermission("save")) {
				saveItem(curItem);
				init();
			}
			return "";
		}		

		protected void saveItem(Item item) {
			prePersist(item);
			EntityManager em = login.getEntityManager();
			em.getTransaction().begin();			
			if (id == -1) {				
				em.persist(item);
			} else {				
				em.merge(item);
			}		
			em.getTransaction().commit();
//			em.close();
		}
		
		protected void prePersist(Item item) {
			if (item != null) {
				item.setModified(new Date());
				item.setModifier(login.getAccount().getFullname());
			}
		}
		
		protected void postPersist(Item item) {
		}
		
		public String delete() {
			if (checkPermission("delete")) {
				EntityManager em = login.getEntityManager();
				em.getTransaction().begin();
				em.remove(curItem);
				em.getTransaction().commit();				
				init();
			}
			return "";
		}
		
		protected void preDelete(Item item) {
		}
		
		protected int countUse(Item item, String field, String table) {
			Query query = login.getEntityManager().createQuery("select count(t.id) from " + table + " t where t." + field + " = :item");
			query.setParameter("item", curItem);		
			return ((Number) query.getSingleResult()).intValue();
		}
		
		
		/*
		 *  Export exports a list of items after transforming by a xsl-Stylesheet
		 */
		
		public void export() {	
			
			FacesContext fc = FacesContext.getCurrentInstance();			
					
			try {				
				
				ExternalContext ec = fc.getExternalContext();
				
				// Get the templates form the chosen stylesheet 
				SAXTransformerFactory stf = (SAXTransformerFactory)TransformerFactory.newInstance();				
				Templates templates = stf.newTemplates(new StreamSource(ec.getRealPath("/xsl/" + exportFormat)));				
								
				Calendar cal  = Calendar.getInstance();
				Date     time = cal.getTime();
				DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				String downloadFilename = 
						login.getCampaign().getName().replaceAll(" ", "_") + "_" + 
						login.getLocation().getName().replaceAll(" ", "_") + "_" + 
						getItemClassName() + formatter.format(time) + ".csv\"";
				
				
				// Resetting the response
				ec.responseReset();
				// the response-content-type (mimetype of the response) is got from the stylesheet <xsl:output --- />
				ec.setResponseContentType(templates.getOutputProperties().getProperty("media-type"));
				// the response should offer downloading a file				
				ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + downloadFilename);
							
				// the items from getItems have to be wrapped in an element to be transformed together 
				JAXBContext context = (JAXBContext) JAXBContext.newInstance(ItemWrapper.class, getItemClass());
				
				QName qName = new QName(getItemClassName() + "s");
		        ItemWrapper wrapper = new  ItemWrapper(getItems());
		        JAXBElement<ItemWrapper> jaxbElement = new JAXBElement<ItemWrapper>(qName, ItemWrapper.class, wrapper);
		        
		        //the marshaling results in a transformation 
				TransformerHandler th = stf.newTransformerHandler(templates);
				initTransformer(th);
				
				
				SAXResult sr = new SAXResult(th);
				
				// the transformation results in the response#outputStream
				OutputStream os = ec.getResponseOutputStream();
				byte[] bom = { (byte) 239, (byte) 187, (byte) 191 };
				os.write(bom);
				th.setResult(new StreamResult(os));
				
				// now do it!
				Marshaller marschaller = context.createMarshaller();
				marschaller.marshal(jaxbElement, sr);
												
				os.flush();
			    os.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
			// important: do write the jsf-response to the response
			fc.responseComplete();			
		}
		
		protected void initTransformer(TransformerHandler th) {
			; // to override
		}
		
		// Convenient function to get an normalized name from ItemClass
		protected String getItemClassName() {
			return getItemClass().getSimpleName().toLowerCase();
		}		
				
		//A function to build an permission-string
		protected boolean checkPermission(String method) {
			return SecurityUtils.getSubject().isPermitted(
				login.getTask().getCode() + ":" + getItemClass().getSimpleName() + ":" + method
			);
		}	
		
		// the following two methods enable the ItemMgrs to be FacesConverters 
		public Object getAsObject(FacesContext context, UIComponent arg1, String id) {
			return id.equals("_")?null:findItem(Long.parseLong(id));
		}
		
		public String getAsString(FacesContext arg0, UIComponent arg1, Object item) {
			return (item != null?"" + ((Item)item).getId(): "_");
		}
		
		
				
		
		
		
		/*
		 * Diese private Klasse hilft beim Export eine Item-Liste in ein XML-Element zu verpacken, dass dann Transformiert werden kann...
		 */		
		private static class ItemWrapper {
			 
		    private List<? extends Item> items;
		 
		    @SuppressWarnings("unused")
			public ItemWrapper() {
		        items = new ArrayList<Item>();
		    }
		 
		    public ItemWrapper(List<? extends Item> items) {
		        this.items = items;
		    }
		 
		    @XmlAnyElement(lax=true)
		    public List<? extends Item> getItems() {
		        return items;
		    }
		 
		}
		
				
}
