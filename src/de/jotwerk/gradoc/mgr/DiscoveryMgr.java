package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Discovery;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager=true)
@SessionScoped
public abstract class DiscoveryMgr extends HoldingMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected abstract Class<? extends Discovery> getItemClass();
	
	@Override
	public String load() {
		if (login.getCampaign() == null) {
			login.setMessage("Keine Kampagne gesetzt. Bitte auswählen.");
			return "/index.xhtml";
		} else {		
			return super.load();
		}
	}	
	
	protected void prePersist(Item item) {
		super.prePersist(item);
		((Discovery)item).setCampaign(login.getCampaign());
	}
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.descriptor d";
	}
	
//	public List<? extends Discovery> getItems() {		
//		TypedQuery<? extends Discovery> tq = login.getEntityManager().createQuery(
//			getItemQuery() + "LEFT JOIN i.descriptor where i.task = :task", getItemClass());
//		tq.setParameter("task", login.getTask());
//		return tq.getResultList();		 
//	}
	
	
}
