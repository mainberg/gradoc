package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-02
 * @modified 2015-05-02
 * 
 * A plank is a horizontal piece of wood
 *   
 */

@Entity
@XmlRootElement
public class Sediment extends Geometry implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private int height;
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }	
	
	@ManyToOne
	@JoinColumn(name = "location")
	private Location location;
	public Location getLocation() { return location; }
	public void setLocation(Location location) { this.location = location; }
	
	@ManyToOne
	@JoinColumn(name = "context")
	private Descriptor context;
	public Descriptor getContext() { return context; }
	public void setContext(Descriptor context) { this.context = context; }
	
}
