package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-05-05
 * @modified 2015-05-05
 * 
 * A protocol describe an entry in a diary or an observation
 *   
 */


@Entity
@XmlRootElement
public class Protocol extends Discovery implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date date;
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date;}
	
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
