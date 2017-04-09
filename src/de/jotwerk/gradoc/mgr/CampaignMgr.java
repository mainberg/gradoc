package de.jotwerk.gradoc.mgr;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Campaign;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager=true)
@SessionScoped
public class CampaignMgr extends HoldingMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Campaign> getItemClass() { return Campaign.class; }
	
//	public List<? extends Holding> getItems() {		
//		TypedQuery<? extends Holding> tq = login.getEntityManager().createQuery(getItemQuery(), getItemClass());
//		return tq.getResultList();		
//	}
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Campaign)item).setName(login.getTask().getCode() + " ##");
		((Campaign)item).setStart(new Date());
		((Campaign)item).setEnd(new Date());
	}
	
}
