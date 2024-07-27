package com.bptn.project.kickoff.player;

import com.bptn.project.kickoff.color.Color;

/**
 * Class representing a player in a team.
 */
public class Player {
	
	
	private String name;
	private Integer number;
	private String position;
	private int goalScored;
	private int yellowCard;
	private int redCard;
	
	/**
     * Constructor for creating a Player with just a name.
     *
     * @param name the name of the player
     */
	public Player(String name){
		this(name, 0 , "default");
	}
	
	 /**
     * Constructor for creating a Player with a name and number.
     *
     * @param name the name of the player
     * @param number the jersey number of the player
     */
	public Player(String name, Integer number){
		this(name, number, "default");
		
	}
	
	/**
     * Constructor for creating a Player with a name, number, and position.
     *
     * @param name the name of the player
     * @param number the jersey number of the player
     * @param position the position of the player
     */
	public Player(String name, Integer number, String position) {
		this.name = name;
		this.number = number;
		this.position = position;
		this.goalScored = 0;
		this.yellowCard = 0;
		this.redCard = 0;
	}
	
	/**
     * Returns a string representation of the player.
     *
     * @return a string representation of the player
     */
	@Override
	public String toString() {
		
		return "#: " + Color.RED_BOLD_BRIGHT + number + Color.RESET +
				", Name: " + Color.PURPLE_BOLD_BRIGHT + name + Color.RESET + 
				", Position: " + Color.RED_BOLD_BRIGHT + position + Color.RESET;
	}
	
	/**
	 * Gets the player's position. 
	 * 
	 * @return the position of the player
	 */
	public String getPosition() {
		return position;
	}

	/**
     * Sets the position of the player.
     *
     * @param position the new position of the player
     */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Returns the number of goals recorded for the player.
	 *  
	 * @return the number of goals scored by the player
	 */
	public int getGoalScored() {
		return goalScored;
	}
	
	/**
     * Increments the number of goals scored by the player.
     *
     * @param goalScored the number of goals to add to the player's total
     */
	public void setGoalScored(int goalScored) {
		this.goalScored += goalScored;
	}

	/**
	 * Gets the number of yellow cards received by the player.
	 *  
	 * @return
	 */
	public int getYellowCard() {
		return yellowCard;
	}
	
	/**
     * Increments the number of yellow cards received by the player.
     *
     * @param yellowCard the number of yellow cards to add to the player's total
     */
	public void setYellowCard(int yellowCard) {
		this.yellowCard += yellowCard;
	}

	/**
	 * Gets the number of red cards received by the player.
	 *  
	 * @return
	 */
	public int getRedCard() {
		return redCard;
	}
	
	/**
     * Increments the number of red cards received by the player.
     *
     * @param redCard the number of red cards to add to the player's total
     */
	public void setRedCard(int redCard) {
		this.redCard += redCard;
	}

	/**
     * Gets the name of the player.
     *
     * @return the name of the player
     */
	public String getName() {
		return name;
	}

	/**
     * Gets the jersey number of the player.
     *
     * @return the jersey number of the player
     */
	public Integer getNumber() {
		return number;
	}
	
	

}
