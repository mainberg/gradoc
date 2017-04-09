package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Plank;

@ManagedBean(eager = true)
@SessionScoped
public class PlankMgr extends GeometryMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Plank> getItemClass() { return Plank.class;	}
	
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
		((Plank)item).setX1(login.getLocation().getX());
		((Plank)item).setY1(login.getLocation().getY());
		((Plank)item).setName("L ###");
	}
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.context c";
	}
	
	
		
}
