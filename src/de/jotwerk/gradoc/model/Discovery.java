package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-05-02
 * @modified 2015-05-05
 * 
 * A discovery represents everything which can be found or observed.
 *   
 */

@Entity
public abstract class Discovery extends Holding implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "descriptor")
	private Descriptor descriptor;
		
	@ManyToOne
	@JoinColumn(name = "campaign")
	private Campaign campaign;

	public Descriptor getDescriptor() { return descriptor; }
	public void setDescriptor(Descriptor descriptor) { this.descriptor = descriptor; }

	public Campaign getCampaign() { return campaign; }
	public void setCampaign(Campaign campaign) { this.campaign = campaign; }		

}
