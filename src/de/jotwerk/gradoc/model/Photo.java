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
 * @created 2015-05-02
 * @modified 2015-05-02
 * 
 * A photo represents an photo.
 *   
 */


@Entity
@XmlRootElement
public class Photo extends Discovery implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(length = 30)
	private String photographer;
	
	@Column(length = 30)
	private String rights;
	
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date date;	
	
	@ManyToOne
	@JoinColumn(name = "location")
	private Location location;	
		
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }

	public String getPhotographer() { return photographer; }
	public void setPhotographer(final String photographer) { this.photographer = photographer; }

	public String getRights() { return rights; }
	public void setRights(final String rights) { this.rights = rights; }
	
	public Location getLocation() { return location; }
	public void setLocation(Location location) { this.location = location; }
	
}
