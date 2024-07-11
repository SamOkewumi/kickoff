package com.bptn.project.kickoff;

public abstract class Event {

	private String title;
	private String description;
	private String date;
	private String location;

	public abstract String getEventType();

	String getTitle() {
		return title;
	}

	void setTitle(String title) {
		this.title = title;
	}

	String getDescription() {
		return description;
	}

	void setDescription(String description) {
		this.description = description;
	}

	String getDate() {
		return date;
	}

	void setDate(String date) {
		this.date = date;
	}

	String getLocation() {
		return location;
	}

	void setLocation(String location) {
		this.location = location;
	}

	//Constructor for an event object
	Event(String title, String description, String date, String location) {
		this.title = title;
		this.description = description;
		this.date = date;
		this.location = location;
	}


	@Override
	public String toString() {

		return String.format("[title=%s, description=%s, date=%s, location=%s ]", title, description, date, location);
	}




}
