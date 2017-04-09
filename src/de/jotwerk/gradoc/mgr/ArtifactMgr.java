package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Artifact;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager = true)
@SessionScoped
public class ArtifactMgr extends GeometryMgr implements Serializable {	
	
	private static final long serialVersionUID = 1L;
		
	@Override
	protected Class<Artifact> getItemClass() { return Artifact.class; }
	
	@Override
	protected void postCreate(Item item) {
		super.postCreate(item);
		((Artifact)item).setName("Objekt ###");
	}
			
}
