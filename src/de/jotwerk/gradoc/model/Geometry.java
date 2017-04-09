package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-28
 * @modified 2015-05-28
 * 
 * A finding is a discovery with coordinates, which can be sampled
 *   
 */

@Entity
public abstract class Geometry extends Discovery implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal x;					
	
	@Column(precision = 10, scale = 2)
	private BigDecimal y;					
		
	private String kind;
	
	private String sample;
	
	public String getKind() { return kind; }
	public void setKind(String kind) { this.kind = kind; }

	public String getSample() { return sample; }
	public void setSample(String sample) { this.sample = sample; }
	
	public BigDecimal getX() { 	return x; }
	public void setX(BigDecimal x) { this.x = x; }

	public BigDecimal getY() { return y; }
	public void setY(BigDecimal y) { this.y = y; }

}
