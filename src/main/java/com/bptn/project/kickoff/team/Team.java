package com.bptn.project.kickoff.team;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.bptn.project.kickoff.color.Color;
import com.bptn.project.kickoff.match.Match;
import com.bptn.project.kickoff.player.Player;


public class Team {

	private static int playerID = 1;
	private static int matchID = 1;
	private int totalGoals;
	private int totalMatches;

	private String teamName; 

	/**
	 * A map that pairs incrementing match IDs with match details
	 */
	private Map<Integer, Match> matches; 

	/**
	 * A map that pairs incrementing player IDs with player information
	 */
	private Map<Integer, Player> roster; 

	/**
	 * Initializes a new team with the specified name and sets default values to some field variables.
	 * 
	 * @param teamName the name of the team
	 */
	public Team(String teamName) {
		this.teamName = teamName;
		totalGoals = 0;
		totalMatches = 0;
		this.matches =  new HashMap<>();
		this.roster = new LinkedHashMap<>();
	}

	/**
	 * Adds a player to the team roster.
	 * Increments the playerID variable as a result.
	 * 
	 * @param player the player to be added to the roster
	 */
	public void addPlayer(Player player) { //add a player to the list of players

		roster.put(playerID, player);
		playerID++;

	}


	/**
	 * Retrieves the player with the specified ID.
	 * 
	 * @param playerID used to retrieve a specific player
	 * @return the player requested or {@code null} if the player is not found
	 */
	public Player getPlayer(int playerID) {
		if(roster.containsKey(playerID)) {
			return roster.get(playerID);
		}
		return null;
	}

	/**
	 * Identifies the player to be removed from the roster.
	 * 
	 * @param playerID the player to be removed
	 */
	public void removePlayer(Integer playerID) {
		if(roster.containsKey(playerID)) {
			roster.remove(playerID);
		}
	}

	/**
	 * Checks if a player with the specified name, number and position exist in the roster.
	 * 
	 * @param name the name of the player
	 * @param number the number of the player
	 * @param position the position of the player
	 * @return {@code true} if the player exists; {@code false} otherwise
	 */
	public boolean containsPlayer(String name, Integer number, String position) { 

		for(Map.Entry<Integer, Player> playerInfo : roster.entrySet()) {
			if(playerInfo.getValue().getName().equals(name) && playerInfo.getValue().getNumber() == number && playerInfo.getValue().getPosition().equals(position)) {
				return true;
			}
		}
		return false; 
	}


	/**
	 * Gets the name of the team. 
	 * 
	 * @return the name of the team
	 */
	public String getTeamName() { //return String of teamName
		return teamName;
	}
	/**
	 * Sets the name of the team based on the parameter value.
	 * 
	 * @param teamName the name to set the team
	 */
	public void setTeamName(String teamName) { //set the teamName
		this.teamName = teamName;
	}
	/**
	 * 
	 * @return the total number of goals
	 */
	public int getTotalGoals() { 
		return totalGoals;
	}
	
	/**
     * Increments the number of goals for the team.
     *
     * @param totalGoals the number of goals to add to the team's total
     */
	public void setTotalGoals(int totalGoals) { 
		this.totalGoals += totalGoals;
	}

	/**
	 * 
	 * @return the total number of matches
	 */
	public int getTotalMatches() {
		return totalMatches;
	}

	/**
     * Increments the number of match for the team.
     *
     * @param totalMatches the number of matches to add to the team's total
     */
	public void setTotalMatches(int totalMatches) { 
		this.totalMatches += totalMatches; 
	}

	/**
	 * Returns the full map of the matches.
	 * 
	 * @return the map containing all the matches, where the key is the match ID and the value is the match details
	 */
	public Map<Integer, Match> getMatches() { 

		return matches;
	}

	/**
	 * Returns the full map of the roster.
	 * 
	 * @return the map containing all the players, where the key is the player ID and the value is the player details
	 */
	public Map<Integer, Player> getRoster() { 

		return roster;
	}

	/**
	 * A formatted display of all the matches. 
	 */
	public void displayMatches() { 
		for (Map.Entry<Integer, Match> match : matches.entrySet()) {
			System.out.println(Color.WHITE_BOLD_BRIGHT + Color.BLACK_BACKGROUND + "️MatchID: " + match.getKey() + " ☘️ " + Color.RESET + match.getValue());
		}
	}

	/**
	 * Adds a new match to the map. The key is an incrementing match ID, and the value is the match details.
	 * 
	 * @param match the match to be added
	 */
	public void setMatch( Match match) { 
		matches.put(matchID, match);
		matchID++;
	}

	/**
	 * Returns the requested match information.
	 * 
	 * @param input to located the specified match
	 * @return the specified match
	 */
	public Match getMatch(String input) { 

		return matches.get(Integer.parseInt(input)); 
	}

	/**
	 * A formatted display of all the players on the roster.
	 * 
	 * Adds an extra space to align the text for matchIDs with only a single digit
	 */
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
