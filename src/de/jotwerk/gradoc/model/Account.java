package de.jotwerk.gradoc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 2.1
 * @created 2015-04-20
 * @modified 2015-04-26
 * 
 * An Account represents an user with his roles  
 *   
 */


@Entity
public class Account extends Item implements Serializable {
	
	private static final long serialVersionUID = 1L;
		
	@Column(length = 25, nullable = false)
	private String password;
	
	@Column(length = 50, nullable = false)
	private String fullname;
	
	@OneToMany(mappedBy = "account", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Role> roles;
		
	public Account() {
		roles = new HashSet<Role>();
	}
		
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	public String getFullname() { return fullname; }
	public void setFullname(String fullname) { this.fullname = fullname; }

	public Set<Role> getRoles() { return roles; }
	public void setRoles(Set<Role> roles) { this.roles = roles; }

}
