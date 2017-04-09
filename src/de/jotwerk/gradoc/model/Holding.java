package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-05-02
 * @modified 2015-05-02
 * 
 * A holding represents an item, that belongs to a task.
 *   
 */

@Entity
public abstract class Holding extends Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "task", nullable= false)
	protected Task task;
		
	public Task getTask() { return task; }
	public void setTask(Task task) { this.task = task; }
	
}
