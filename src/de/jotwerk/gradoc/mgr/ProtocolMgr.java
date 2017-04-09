package de.jotwerk.gradoc.mgr;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Protocol;

@ManagedBean
@SessionScoped
public class ProtocolMgr extends DiscoveryMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected Class<Protocol> getItemClass() { return Protocol.class; }
	
	public List<Protocol> getItems() {	
		TypedQuery<Protocol> tq;
		EntityManager em = login.getEntityManager();
		if (checkPermission("inspect")) {
			tq = em.createQuery(
					getItemQuery() + " where i.campaign = :campaign", Protocol.class);
			tq.setParameter("campaign", login.getCampaign());
		} else {
			tq = em.createQuery(
				getItemQuery() + " where i.creator = :creator and i.campaign = :campaign", Protocol.class);
			tq.setParameter("creator", login.getAccount().getFullname());
			tq.setParameter("campaign", login.getCampaign());
		}
		List<Protocol> result = tq.getResultList();
		return result;		 
	}
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Protocol)item).setDate(new Date());
		((Protocol)item).setName("Bericht ###");
	}
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.location l LEFT JOIN i.context c";
	}
		
}
