package de.jotwerk.gradoc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-01
 * @modified 2015-05-01
 * 
 * The Item-class is the common superclass of all entity-classes. 
 * It supports identification, naming, task-membership, record-creation 
 * and -modification and notes  
 *   
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	protected long id;
	public long getId() { return this.id; }
	public void setId(final long id) { this.id = id; }
		
	@Column(length=50, nullable = true)
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(length=50, nullable = false)
	protected String creator;
	public String getCreator() { return creator; }
	public void setCreator(String creator) { this.creator = creator; }
	
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date created;
	public Date getCreated() { return this.created; }
	public void setCreated(Date created) { this.created = created; }
	
	@Column(length=50, nullable = false)
	protected String modifier;
	public String getModifier() { return modifier; }
	public void setModifier(String modifier) { this.modifier = modifier; }
	
	@Temporal(TemporalType.DATE) @Column(updatable = true)
	protected Date modified;
	public Date getModified() { return this.modified; }
	public void setModified(Date modified) { this.modified = modified; }
	
	@Lob 
	private String note;		
	public String getNote() { return note; }
	public void setNote(String note) { this.note = note; }
	
	@Override
    public boolean equals(Object object) {		
        return (object instanceof Item) && (id != 0L) 
             ? id  == (((Item) object).id) 
             : (object == this);
    }
	
	
	
}
