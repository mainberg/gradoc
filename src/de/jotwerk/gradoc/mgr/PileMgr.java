package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Pile;

@ManagedBean(eager = true)
@SessionScoped
public class PileMgr extends GeometryMgr implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String load() {
		if (login.getLocation() == null) {
			login.setMessage("Keine Fläche gesetzt. Bitte auswählen.");
			return "/index.xhtml";
		} else {		
			return super.load();
		}
	}
	
	@Override
	protected Class<Pile> getItemClass() { return Pile.class; }
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Pile)item).setName("P ###");
	}
			
}
