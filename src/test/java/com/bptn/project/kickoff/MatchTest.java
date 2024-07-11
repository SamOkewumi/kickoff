package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MatchTest {
	
	private Match match;
	private Team team = new Team("Test Team");
	
	@BeforeEach
	public void createMatch() {
		String title = "Test Match";
		String description = "Test Description";
		String date = "Mon, January 1, 2000, 7:00PM";
		String location = "Test Location";
		String awayTeamName = "Test AwayTeam";
		String homeTeamName = "Test HomeTeam";
		match = new Match(title, description, date, location, awayTeamName, homeTeamName);
		match.setHomeTeam(team);
	}
	
	@Test
	public void testMatchCreation() {
		assertEquals("Test Match", match.getTitle());
		assertEquals("Test Description", match.getDescription());
		assertEquals("Mon, January 1, 2000, 7:00PM", match.getDate());
		assertEquals("Test Location", match.getLocation());
		assertEquals("Test AwayTeam", match.getAwayTeamName());
		assertEquals("Test HomeTeam", match.getHomeTeamName());
		assertTrue(match.isMatchPlayed() == false);
	}
	
	@Test
	public void testRecordResult() {
		match.recordResult(3, 2);
		assertEquals(3, match.getHomeTeamScore());
		assertEquals(2, match.getAwayTeamScore());
		assertTrue(match.isMatchPlayed());
		
	}
	
	@Test
	public void testRecordPlayerGoal() {
		team.addPlayer(new Player("Test Player", 10, "GK"));
		match.recordPlayerGoal(1, 2);
		assertEquals(2, team.getPlayer(1).getGoalScored());
	}

}
