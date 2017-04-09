package de.jotwerk.gradoc.model;

public class Term {
	
	private final String key;
	public String getKey() { return key; }
	
	private final String description;
	public String getDescription() { return description; }
	
	public Term(String key, String description) {
		this.key = key; 
		this.description = description;
	}
	
}
