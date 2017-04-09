package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-02
 * @modified 2015-05-28
 * 
 * A pile is a vertically piece of wood.
 *   
 */

@Entity
@XmlRootElement
public class Pile extends Geometry implements Serializable {	
	
	private static final long serialVersionUID = 1L;
			
	private String bark;
	
	@Column(precision = 2, scale = 0)
	private int diameter;
		
	private String profile;
	
	@Column(precision = 2, scale = 0)
	private int arc;
	
	@Column(precision = 2, scale = 0)
	private int exposition;
	
	private String inclination;
	
	private String top;
		
	public String getBark() { return this.bark; }
	public void setBark(String bark) { this.bark = bark; }

	public int getDiameter() { return this.diameter; }
	public void setDiameter(int diameter) { this.diameter = diameter; }	

	public String getProfile() { return this.profile; }
	public void setProfile(String profile) { this.profile = profile; }

	public int getArc() { return this.arc; }
	public void setArc(final int arc) { this.arc = arc; }	

	public int getExposition() { return this.exposition; }
	public void setExposition(final int exposition) { this.exposition = exposition; }

	public String getTop() { return this.top; }
	public void setTop(String top) { this.top = top; }
	
	public String getInclination() { return inclination;}
	public void setInclination(String inclination) { this.inclination = inclination; }

}
