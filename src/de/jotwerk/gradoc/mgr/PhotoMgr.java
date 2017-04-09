package de.jotwerk.gradoc.mgr;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Item;
import de.jotwerk.gradoc.model.Photo;

@ManagedBean(eager = true)
@SessionScoped
public class PhotoMgr extends DiscoveryMgr implements Serializable {	
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Photo> getItemClass() { return Photo.class; }
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Photo)item).setDate(new Date());
		((Photo)item).setName("Foto ###");
	}
	
	@Override
	protected String getItemQuery() {
		return super.getItemQuery() + " LEFT JOIN i.location l";
	}
		
}
