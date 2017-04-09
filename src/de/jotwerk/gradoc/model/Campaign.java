package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-04-26
 * @modified 2015-04-26
 * 
 * A timing serves temporal tags: campaign, dates etc.
 *   
 */


@Entity
public class Campaign extends Holding implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date start;									// First day of the timing
	
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date end;									    // Last day of the timing
		
	public Date getStart() { return start; }
	public void setStart(Date start) { this.start = start; }

	public Date getEnd() { return end; }
	public void setEnd(Date end) { this.end = end; }

}
