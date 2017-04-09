package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-27
 * @modified 2015-05-30
 * 
 * A artifact is recent finding i.e. a cement block etc.
 *   
 */

@Entity
@XmlRootElement
public class Artifact extends Geometry implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	

}
