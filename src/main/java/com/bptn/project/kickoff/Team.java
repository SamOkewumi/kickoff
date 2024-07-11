package com.bptn.project.kickoff;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class Team {

	private static int playerID = 1;
	private static int matchID = 1;
	private int totalGoals;
	private int totalMatches;

	private String teamName; // let user set the name of the team

	// A map that pairs incrementing matchID with match details
	private Map<Integer, Match> matches; //

	//A map that pairs incrementing playerID with player information
	private Map<Integer, Player> roster; 

	//Constructor for a team object, sets the name and initializes variables
	public Team(String teamName) {
		this.teamName = teamName;
		totalGoals = 0;
		totalMatches = 0;
		this.matches =  new HashMap<>();
		this.roster = new LinkedHashMap<>();
	}

	//method to add players to the roster map which increments the playerID as as a player is added. 
	public void addPlayer(Player player) { //add a player to the list of players

		roster.put(playerID, player);
		playerID++;

	}


	//Uses playerID as a parameter to return details pertaining to the player
	public Player getPlayer(int playerID) {
		if(roster.containsKey(playerID)) {
			return roster.get(playerID);
		}

		return null;
	}

	//Uses playerID as a parameter to remove the passed player parameter from the roster. 
	public void removePlayer(Integer playerID) {
		if(roster.containsKey(playerID)) {
			roster.remove(playerID);
		}
	}

	//A flag to confirm if a set of player information already exists in the roster
	public boolean containsPlayer(String name, Integer number, String position) { //boolean to check if entry already exist

		for(Map.Entry<Integer, Player> playerInfo : roster.entrySet()) {
			if(playerInfo.getValue().getName().equals(name) && playerInfo.getValue().getNumber() == number && playerInfo.getValue().getPosition().equals(position)) {
				return true;
			}
		}
		return false; //if no player matches the parameter, return false
	}


	//returns the name of the team. 
	String getTeamName() { //return String of teamName
		return teamName;
	}
	//used to set the name of the team.
	void setTeamName(String teamName) { //set the teamName
		this.teamName = teamName;
	}
	//returns the total goal value
	int getTotalGoals() { //return # of total goals for the team
		return totalGoals;
	}
	//allows for the incrementing of the total goal based on the parameters
	void setTotalGoals(int totalGoals) { 
		this.totalGoals += totalGoals;
	}

	//returns the total amount of matches for the team
	int getTotalMatches() {
		return totalMatches;
	}

	//update the total number of matches based on the parameter
	void setTotalMatches(int totalMatches) { 
		this.totalMatches += totalMatches; 
	}

	//returns the fill map of matches
	public Map<Integer, Match> getMatches() { // return a full map of the matches

		return matches;
	}

	// return a full map of the player roster
	public Map<Integer, Player> getRoster() { 

		return roster;
	}

	//formated printout of the matchID and match details
	public void displayMatches() { 
		for (Map.Entry<Integer, Match> match : matches.entrySet()) {
			System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "️MatchID: " + match.getKey() + " ☘️ " + Color.RESET + match.getValue());
		}
	}

	//add a new match to the map with the key as the name of the opponent and the value as the match
	public void setMatch( Match match) { 
		matches.put(matchID, match);
		matchID++;
	}

	//return a specific match based on the key (matchID) value passed
	public Match getMatch(String input) { 

		return matches.get(Integer.parseInt(input)); 
	}

	//formated printout of the playerID and player details
	public void displayRoster() { 
		int extraSpace = 1;

		for (Map.Entry<Integer, Player> player : roster.entrySet()) {

			if (extraSpace < 10) {
				System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "PlayerID: " + player.getKey()  + "  💫️ " + Color.RESET + "Jersey: " + player.getValue());
				extraSpace++;
			}
			else {
				System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "PlayerID: " + player.getKey() + " 💫️ " + Color.RESET + "Jersey: " + player.getValue());
			}
		}
	}


}
