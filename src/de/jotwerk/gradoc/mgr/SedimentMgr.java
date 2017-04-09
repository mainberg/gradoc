package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Sediment;

@ManagedBean(eager = true)
@SessionScoped
public class SedimentMgr extends GeometryMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Sediment> getItemClass() { return Sediment.class; }
	
	@Override
	protected void prePersist(Item item) {
		((Sediment)curItem).setLocation(login.getLocation());
		super.prePersist(item);
	}
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Sediment)item).setName("Sediment ###");
	}
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.context c";
	}
			
}
