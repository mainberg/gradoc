package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-02
 * @modified 2015-05-28
 * 
 * A plank is a horizontal piece of wood
 *   
 */

@Entity
@XmlRootElement
public class Plank extends Geometry implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	private String bark;
	public String getBark() { return bark; }
	public void setBark(String bark) { this.bark = bark; }
	
	private String burning;
	public String getBurning() { return burning; }
	public void setBurning(String burning) { this.burning = burning; }
	
	@Column(precision = 2, scale = 0)
	private int diameter;
	public int getDiameter() { return diameter; }
	public void setDiameter(int diameter) { this.diameter = diameter; }
	
	@Column(precision = 10, scale = 2)
	private BigDecimal z;
	public BigDecimal getZ() { return z; }
	public void setZ(BigDecimal z) { this.z = z; }
	
	@Column(precision = 10, scale = 2)
	private BigDecimal x1;
	public BigDecimal getX1() { return x1; }
	public void setX1(BigDecimal x) { this.x1 = x; }
	
	@Column(precision = 10, scale = 2)
	private BigDecimal y1;
	public BigDecimal getY1() { return y1; }
	public void setY1(BigDecimal y) { this.y1 = y; }
	
	@Column(precision = 10, scale = 2)
	private BigDecimal z1;
	public BigDecimal getZ1() { return z1; }
	public void setZ1(BigDecimal z1) { this.z1 = z1; }
	
	@ManyToOne
	@JoinColumn(name = "context")
	private Descriptor context;
	public Descriptor getContext() { return context; }
	public void setContext(Descriptor context) { this.context = context; }	

}
