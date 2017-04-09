package de.jotwerk.gradoc.mgr;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.persistence.TypedQuery;
import javax.xml.transform.sax.TransformerHandler;

import de.jotwerk.gradoc.model.Geometry;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager = true)
@SessionScoped
public abstract class GeometryMgr extends DiscoveryMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Is necessary to get the appropriate generic parameter type to match the result type in getItems.
	*/
	@Override
	protected abstract Class<? extends Geometry> getItemClass();
	
	/**
	 * Location must be set in login for conversion an finding right items
	 */	
	@Override
	public String load() {
		if (login.getLocation() == null) {
			login.setMessage("Keine Fläche gesetzt. Bitte auswählen.");
			return "/index.xhtml";
		} else {		
			return super.load();
		}
	}
	
	/**
	 * Setting Default-Values
	 */
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Geometry)item).setX(login.getLocation().getX());
		((Geometry)item).setY(login.getLocation().getY());
	}
	
	@Override
	protected String getWhereClause() {
		return super.getWhereClause() + " and i.x >= :xmin and i.x < :xmax and i.y >= :ymin and i.y < :ymax";
	}
	
	@Override
	protected void setQueryParameters(TypedQuery<? extends Item> tq) {
		super.setQueryParameters(tq);
		tq.setParameter("xmin", login.getLocation().getX());
		tq.setParameter("xmax", login.getLocation().getX().add(login.getLocation().getA()));
		tq.setParameter("ymin", login.getLocation().getY());
		tq.setParameter("ymax", login.getLocation().getY().add(login.getLocation().getB()));
	}
	
	@Override
	protected void initTransformer(TransformerHandler th) {
		super.initTransformer(th);
		th.getTransformer().setParameter("digits", login.getTask().getDigits());
	}
	
//	/**
//	 * getItems must restrict the current item list to those, which belong to the current location
//	 */	
//	@Override
//	public List<? extends Geometry> getItems() {		
//		TypedQuery<? extends Geometry> tq = login.getEntityManager().createQuery(
//			getItemQuery() + " LEFT JOIN i.descriptor d where i.x >= :xmin and i.x <= :xmax and i.y >= :ymin and i.y <= :ymax", getItemClass());
//		tq.setParameter("xmin", login.getLocation().getX());
//		tq.setParameter("xmax", login.getLocation().getX().add(login.getLocation().getA()));
//		tq.setParameter("ymin", login.getLocation().getY());
//		tq.setParameter("ymax", login.getLocation().getY().add(login.getLocation().getB()));
//		return tq.getResultList();		
//	}
	
	/**
	 * Conversion routine. xkoords and ykoords in the form a calculated relative the current location
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String koord) {
		if (component.getId().startsWith("x")) {
			return login.getLocation().getX().add(new BigDecimal(koord));
		} else {
			return login.getLocation().getY().add(new BigDecimal(koord));
		}		
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object koord) {
		if (component.getId().startsWith("x")) {
			return (((BigDecimal)koord).subtract(login.getLocation().getX())).toString();
		} else {
			return (((BigDecimal)koord).subtract(login.getLocation().getY())).toString();
		}
	}	
	
	/**
	 * Validation of x and y Coordinates, in the range from 0 to dimensions login.location
	 */
	public void validateX(FacesContext context, UIComponent component, Object koord) throws ValidatorException {
		inRange((BigDecimal)koord, login.getLocation().getX(), login.getLocation().getA());		
	}
	
	public void validateY(FacesContext context, UIComponent component, Object koord) throws ValidatorException {
		inRange((BigDecimal)koord, login.getLocation().getY(), login.getLocation().getB());		
	}
	
	protected void inRange(BigDecimal val, BigDecimal base, BigDecimal length) throws ValidatorException {
		if (val.compareTo(base) == -1 || val.compareTo(base.add(length)) == 1 ) {
				throw new ValidatorException(new FacesMessage("blah"));
		}
	}
		
}
