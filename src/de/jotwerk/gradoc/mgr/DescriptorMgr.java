package de.jotwerk.gradoc.mgr;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import de.jotwerk.gradoc.model.Descriptor;
import de.jotwerk.gradoc.model.Item;

@ManagedBean(eager=true)
@SessionScoped
public class DescriptorMgr extends HoldingMgr implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected Class<Descriptor> getItemClass() { return Descriptor.class; }	
	
	public void postLoad(Item item) {
		uses = countUse(item, "descriptor", "Artifact") +
			countUse(item, "descriptor", "Finding") +
			countUse(item, "descriptor", "Photo") +
			countUse(item, "descriptor", "Pile") +
			countUse(item, "descriptor", "Plank") +
			countUse(item, "descriptor", "Protocol") +
			countUse(item, "descriptor", "Sediment"); 
	}	
	
}
