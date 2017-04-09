package de.jotwerk.gradoc.mgr;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Location;

@ManagedBean(eager=true)
@SessionScoped
public class LocationMgr extends HoldingMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Location> getItemClass() { return Location.class; }
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Location)item).setCategory("quadrat");
		((Location)item).setName("Q ###");
		((Location)item).setX(new BigDecimal(0));
		((Location)item).setY(new BigDecimal(0));
		((Location)item).setA(new BigDecimal(1));
		((Location)item).setB(new BigDecimal(1));
	}
	
	public void postLoad(Item item) {
		uses = countUse(item, "location", "Photo") +
			countUse(item, "location", "Protocol") +
			countUse(item, "location", "Sediment"); 
	}	
		
}
