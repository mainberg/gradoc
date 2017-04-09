package de.jotwerk.gradoc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Christof Mainberger (christof.mainberger@uwarc.de)
 * @version 1.0
 * @created 2015-05-27
 * @modified 2015-05-30
 * 
 * A artifact is any finding beside planks, piles, and artifacts
 *   
 */

@Entity
@XmlRootElement
public class Finding extends Geometry implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int number;
	public int getNumber() { return number; }
	public void setNumber(int number) { this.number = number; }
	
	private int count;
	public int getCount() { return count; }
	public void setCount(int count) { this.count = count; }
	
	private int weight;
	public int getWeight() { return weight; }
	public void setWeight(int weight) { this.weight = weight; }
	
	private String location;
	public String getLocation() { return location; }
	public void setLocation(String location) { this.location = location; }
	
	@ManyToOne
	@JoinColumn(name = "context")
	private Descriptor context;
	public Descriptor getContext() { return context; }
	public void setContext(Descriptor context) { this.context = context; }
	
	private String farbe;	
	public String getFarbe() { return farbe; }
	public void setFarbe(String farbe) { this.farbe = farbe; }	

	private String wanddicke;
	public String getWanddicke() { return wanddicke; }
	public void setWanddicke(String wanddicke) { this.wanddicke = wanddicke; }	
	
	private String magerung;
	public String getMagerung() { return magerung; }
	public void setMagerung(String magerung) { this.magerung = magerung; }
	
	private String katalogtext;
	public String getKatalogtext() { return katalogtext; }
	public void setKatalogtext(String katalogtext) { this.katalogtext = katalogtext; }
	
	private String gesteinsgattung;
	public String getGesteinsgattung() { return gesteinsgattung; }
	public void setGesteinsgattung(String gesteinsgattung) { this.gesteinsgattung = gesteinsgattung; }
}
