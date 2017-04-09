package de.jotwerk.gradoc.mgr;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.jotwerk.gradoc.model.Holding;
import de.jotwerk.gradoc.model.Item;

public abstract class HoldingMgr extends ItemMgr {
	
	protected abstract Class<? extends Holding> getItemClass();
	
	@Override
	public String load() {
		if (login.getTask() == null) {
			login.setMessage("Keine Maßnahme gesetzt. Bitte ausloggen.");
			return "/index.xhtml";
		} else {		
			return super.load();
		}
	}	
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Holding)item).setTask(login.getTask());
	}
	
	@Override
	protected String getWhereClause() {
		return super.getWhereClause() + " and i.task = :task";
	}
	
	@Override
	protected void setQueryParameters(TypedQuery<? extends Item> tq) {
		super.setQueryParameters(tq);
		tq.setParameter("task", login.getTask());
	}
	
//	public List<? extends Holding> getItems() {		
//		TypedQuery<? extends Holding> tq = login.getEntityManager().createQuery(
//			getItemQuery() + " where i.task = :task", getItemClass());
//		tq.setParameter("task", login.getTask());
//		return tq.getResultList();		 
//	}
	
	public List<? extends Holding> getItemsByCategory(String category) {
		EntityManager em = login.getEntityManager();		
		TypedQuery<? extends Holding> tq = em.createQuery(
			getItemQuery() + " where i.task = :task and i.category = :category", getItemClass());
		tq.setParameter("task", login.getTask());
		tq.setParameter("category", category);
		List<? extends Holding> results = tq.getResultList();
		return results;		
	}
		
}
