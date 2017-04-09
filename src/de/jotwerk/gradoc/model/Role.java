package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2014-04-26
 * @modified 2014-04-26
 * 
 * A Role represents a permission, that an account has at a fundort * 
 *   
 */


@Entity
public class Role implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	protected long id;
		
	@Column(length = 25, nullable = false)
	private String permission;
	
	@ManyToOne
	@JoinColumn(name = "task", nullable= false)
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "account", nullable= false)
	private Account account;	
	
	public long getId() { return this.id; }
	public void setId(final long id) { this.id = id; }	

	public Account getAccount() { return account; }
	public void setAccount(final Account account) { this.account = account; }
		
	public Task getTask() { return task; }
	public void setTask(final Task task) { this.task = task; }
	
	public String getPermission() { return permission; }
	public void setPermission(final String permission) { this.permission = permission; }
		
}
