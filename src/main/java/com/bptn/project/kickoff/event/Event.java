package com.bptn.project.kickoff.event;

/**
 * Abstract base class for representing an event.
 * Subclasses must implement the {@link #getEventType()} method to specify the type of event.
 */
public abstract class Event {

	private String title;
	private String description;
	private String date;
	private String location;
	
	
	/**
     * Returns the type of event.
     *
     * @return the type of event
     */
	public abstract String getEventType();
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

    /**
     * Constructor for creating an Event object.
     *
     * @param title       the title of the event
     * @param description the description of the event
     * @param date        the date of the event
     * @param location    the location of the event
     */
	public Event(String title, String description, String date, String location) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.location = location;
	}

	
	/**
	 * Ensures the subclasses override the toString() method. 
	 */
	public abstract String toString();




}
