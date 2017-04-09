package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-01
 * @modified 2015-05-01
 * 
 * A location serves for spatial tags: sectors, squares, sections, coordinates, etc.
 *   
 */
@Entity
@XmlRootElement
public class Location extends Holding implements Serializable {
	
private static final long serialVersionUID = 1L;

	@Column(precision = 10, scale = 2)
	private BigDecimal x;					// Longitude of the left-lower edge of the location 
	public BigDecimal getX() { 	return x; }
	public void setX(BigDecimal x) { this.x = x; }
	
	@Column(precision = 10, scale = 2)
	private BigDecimal y;					// Latitude of the left-lower edge of the location 
	public BigDecimal getY() { return y; }
	public void setY(BigDecimal y) { this.y = y; }
	
	@Column(precision = 7, scale = 2)
	private BigDecimal a;							// Width of the left-lower edge of the location
	public BigDecimal getA() { return a; }
	public void setA(BigDecimal a) { this.a = a; }
	
	@Column(precision = 7, scale = 2)
	private BigDecimal b;							// Height of the left-lower edge of the location 
	public BigDecimal getB() { return b; }
	public void setB(BigDecimal b) { this.b = b; }
	
	@Column(length = 25, nullable = false)
	private String category;
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
	
	@Column(length = 25, nullable= true)
	private String signature;
	public String getSignature() { return signature; }
	public void setSignature(String signature) { this.signature = signature; }
		
}
