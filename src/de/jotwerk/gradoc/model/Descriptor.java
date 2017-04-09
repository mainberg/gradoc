package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-04-26
 * @modified 2015-04-26
 * 
 * A descriptor serves controlled vocabulary
 *   
 */


@Entity
public class Descriptor extends Holding implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Column(length = 25, nullable = false)
	private String category;
		
	public String getCategory() { return category; }
	public void setCategory(String category) { this.category = category; }
		
}
