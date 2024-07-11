package com.bptn.project.kickoff;


public class Player {
	
	
	private String name;
	private Integer number;
	private String position;
	private int goalScored;
	private int yellowCard;
	private int redCard;
	
	/*
	 * Overloaded constructor
	 */
	Player(String name){
		this(name, 0 , "default");
	}
	
	Player(String name, Integer number){
		this(name, number, "default");
		
	}
	
	//constructor for players which sets some variables
	Player(String name, Integer number, String position) {
		this.name = name;
		this.number = number;
		this.position = position;
		this.goalScored = 0;
		this.yellowCard = 0;
		this.redCard = 0;
	}
	
	//formating of the player information
	public String toString() {
		
		return "#: " + Color.RED_BOLD_BRIGHT + number + Color.RESET +
				", Name: " + Color.PURPLE_BOLD_BRIGHT + name + Color.RESET + 
				", Position: " + Color.RED_BOLD_BRIGHT + position + Color.RESET;
	}
	
	//return string of player position
	String getPosition() {
		return position;
	}

	//sets the player position based on the parameter
	void setPosition(String position) {
		this.position = position;
	}

	//returns the amount of goal scored by the player
	int getGoalScored() {
		return goalScored;
	}
	
	//increments the goal scored based on the parameter
	void setGoalScored(int goalScored) {
		this.goalScored += goalScored;
	}

	//returns the amount of yellow cards received by the player
	int getYellowCard() {
		return yellowCard;
	}
	
	//updates number of yellow cards based on the parameter
	void setYellowCard(int yellowCard) {
		this.yellowCard += yellowCard;
	}

	//returns the amount of yellow cards received by the player
	int getRedCard() {
		return redCard;
	}
	
	//updates number of red cards based on the parameter
	void setRedCard(int redCard) {
		this.redCard += redCard;
	}

	//returns the name of the player
	String getName() {
		return name;
	}

	//returns the players jersey number
	Integer getNumber() {
		return number;
	}
	
	
	

}
