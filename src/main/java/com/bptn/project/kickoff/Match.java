package com.bptn.project.kickoff;



public class Match extends Event{

	private String awayTeamName;
	private String homeTeamName;
	private int homeTeamScore;
	private int awayTeamScore;
	private boolean matchPlayed;
	private Team homeTeam;


	//Constructor for a match event
	public Match(String title, String description, String date, String location, String awayTeamName, String homeTeamName) {
		super(title, description, date, location);
		this.awayTeamName = awayTeamName;
		this.homeTeamName = homeTeamName;
		this.setMatchPlayed(false);
	}


	@Override
	public String getEventType() { //override abstract method
		return "Match";
	}


	//return the score for the home team
	int getHomeTeamScore() {
		return homeTeamScore;
	}

	//update the home team score based on the parameter
	void setHomeTeamScore(int homeTeamScore) {
		this.homeTeamScore = homeTeamScore;
	}

	//returns the away team score
	int getAwayTeamScore() {
		return awayTeamScore;
	}

	//update the away team score based on the parameter
	void setAwayTeamScore(int awayTeamScore) {
		this.awayTeamScore = awayTeamScore;
	}

	//return the instance of the team
	Team getHomeTeam() {
		return homeTeam;
	}

	//set the team instance based on the parameter
	void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	//return the away team name
	String getAwayTeamName() {
		return awayTeamName;
	}

	//set the away team name based on the parameter 
	void setAwayTeamName(String awayTeamName) {
		this.awayTeamName = awayTeamName;
	}

	//update scores for the match based on the parameters
	public void recordResult(int homeTeamScore, int awayTeamScore) {
		this.homeTeamScore += homeTeamScore;
		this.homeTeam.setTotalGoals(homeTeamScore);

		this.awayTeamScore += awayTeamScore;

		this.setMatchPlayed(true);
	}

	//locate and update player statistic based on parameter
	public void recordPlayerGoal(int playerID, int goal) { 

		homeTeam.getPlayer(playerID).setGoalScored(goal);
	}


	@Override
	public String toString() { //override parent class toString method
		if(this.isMatchPlayed()) { //if the game has been played, display the results. 
			return String.format("[\n	📢TITLE: %s\n	🏆Results: %s [ %s : %s ] %s\n	🗒️DESCRIPTION: %s\n	📅DATE: %s\n	📍LOCATION: %s]", this.getTitle(), this.getHomeTeamName(), this.getHomeTeamScore(), this.getAwayTeamScore(), this.getAwayTeamName(), this.getDescription(), this.getDate(), this.getLocation());
		}
		return String.format("[\n	📢TITLE: %s\n	🗒️DESCRIPTION: %s\n	📅DATE: %s\n	📍LOCATION: %s]", this.getTitle(), this.getDescription(), this.getDate(), this.getLocation());

	}

	//return away team name
	public String getHomeTeamName() {
		return homeTeamName;
	}

	//set home team name
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	//returns the played state of a match
	public boolean isMatchPlayed() {
		return matchPlayed;
	}

	//update a match to played once scores have been recorded
	public void setMatchPlayed(boolean matchPlayed) {
		this.matchPlayed = matchPlayed;
	}





}
