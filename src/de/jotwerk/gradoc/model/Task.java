package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-01
 * @modified 2015-07-11
 * 
 * Task represents the central namespace of all other Items, e.g. re, si, kh.  
 *   
 */


@Entity
public class Task extends Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
			
	@Column(length = 10, nullable = false) 
	private String code;
	public String getCode() { return code; }
	public void setCode(String code) { this.code = code; }
	
	@Column(length = 25, nullable = false)
	private String number;
	public String getNumber() { return number; }
	public void setNumber(String number) { this.number = number; }
	
	private int digits;
	public int getDigits() { return digits; }
	public void setDigits(int digits) { this.digits = digits; }
		
}
