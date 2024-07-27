package com.bptn.project.kickoff.match;

import com.bptn.project.kickoff.event.Event;
import com.bptn.project.kickoff.team.Team;

public class Match extends Event{

	private String awayTeamName;
	private String homeTeamName;
	private int homeTeamScore;
	private int awayTeamScore;
	private boolean matchPlayed;
	private Team homeTeam;


	/**
	 * Constructor for a match event.
	 *
	 * @param title        the title of the match
	 * @param description  the description of the match
	 * @param date         the date of the match
	 * @param location     the location of the match
	 * @param awayTeamName the name of the away team
	 * @param homeTeamName the name of the home team
	 */
	public Match(String title, String description, String date, String location, String awayTeamName, String homeTeamName) {
		super(title, description, date, location);
		this.awayTeamName = awayTeamName;
		this.homeTeamName = homeTeamName;
		this.setMatchPlayed(false);
	}


	@Override
	public String getEventType() { 
		return "Match";
	}


	/**
	 * Returns the home team score.
	 * 
	 * @return the score for the home team
	 */
	public int getHomeTeamScore() {
		return homeTeamScore;
	}


	/**
	 * Returns the away team score.
	 * 
	 * @return the score for the away team
	 */
	public int getAwayTeamScore() {
		return awayTeamScore;
	}


	/**
	 * Returns the instance of the homeTeam
	 * 
	 * @return the instance of the team
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * Injects an instance of the team into the local team variable
	 * 
	 * @param homeTeam 
	 */
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	//return the away team name
	public String getAwayTeamName() {
		return awayTeamName;
	}

	//set the away team name based on the parameter 
	public void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	/**
	 * Records the match results for the home team and away team. 
	 * Increments the total goal for the home team.
	 * Sets the status of the match as played.
	 * 
	 * @param homeTeamScore
	 * @param awayTeamScore
	 */
	public void recordResult(int homeTeamScore, int awayTeamScore) {
		this.homeTeamScore += homeTeamScore;
		if(this.homeTeam != null) {
			this.homeTeam.setTotalGoals(homeTeamScore);
		}

		this.awayTeamScore += awayTeamScore;

		this.setMatchPlayed(true);
	}

	/**
	 * Updates the number of goals scored for the player in the match
	 *  
	 * @param playerID identify the player
	 * @param goal number of goal to record for the player
	 */
	public void recordPlayerGoal(int playerID, int goal) { 
		if(this.homeTeam != null) {
			homeTeam.getPlayer(playerID).setGoalScored(goal);
		}
	}

	/**
	 * Returns a string representation of the match.
	 * Displays the results for matches that are completed. 
	 *
	 * @return a string representation of the match
	 */
	@Override
	public String toString() { 
		if(this.isMatchPlayed()) { 
			return String.format("[\n	📢TITLE: %s\n	🏆Results: %s [ %s : %s ] %s\n	🗒️DESCRIPTION: %s\n	📅DATE: %s\n	📍LOCATION: %s]", this.getTitle(), this.getHomeTeamName(), this.getHomeTeamScore(), this.getAwayTeamScore(), this.getAwayTeamName(), this.getDescription(), this.getDate(), this.getLocation());
		}
		return String.format("[\n	📢TITLE: %s\n	🗒️DESCRIPTION: %s\n	📅DATE: %s\n	📍LOCATION: %s]", this.getTitle(), this.getDescription(), this.getDate(), this.getLocation());

	}

	/**
	 * Gets the name of the home team.
	 * 
	 * @return the name of the home team
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}

	/**
	 * Sets the name of the home team.
	 * 
	 * @param homeTeamName the name of the home team
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	/**
	 * Returns a boolean representing the status of the match.
	 * 
	 * @return the status of the match 
	 */
	public boolean isMatchPlayed() {
		return matchPlayed;
	}

	/**
	 * Updates the status of the match once the scores have been record.
	 * 
	 * @param matchPlayed the new status for the match. 
	 */
	public void setMatchPlayed(boolean matchPlayed) {
		this.matchPlayed = matchPlayed;
	}





}
