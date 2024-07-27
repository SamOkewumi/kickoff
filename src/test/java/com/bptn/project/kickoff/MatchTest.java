package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bptn.project.kickoff.match.Match;
import com.bptn.project.kickoff.player.Player;
import com.bptn.project.kickoff.team.Team;

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
		assertEquals("Match", match.getEventType());
		assertTrue(match.isMatchPlayed() == false);
		assertEquals(team, match.getHomeTeam());
	}

	@Test
	public void testRecordResult() {
		match.recordResult(3, 2);
		assertEquals(3, match.getHomeTeamScore());
		assertEquals(2, match.getAwayTeamScore());
		assertTrue(match.isMatchPlayed());
		assertEquals(3, team.getTotalGoals());
		
		String expected = "[\n	📢TITLE: Test Match\n	🏆Results: Test HomeTeam [ 3 : 2 ] Test AwayTeam\n	🗒️DESCRIPTION: Test Description\n	📅DATE: Mon, January 1, 2000, 7:00PM\n	📍LOCATION: Test Location]";

		assertEquals(expected, match.toString());

	}

	@Test
	public void testRecordPlayerGoal() {
		team.addPlayer(new Player("Test Player", 10, "GK"));
		match.recordPlayerGoal(1, 2);
		assertEquals(2, team.getPlayer(1).getGoalScored());
	}

	@Test
	public void testParentSettersAndGetters() {
		match.setTitle("Test Event Super Class");
		assertEquals("Test Event Super Class", match.getTitle());

		match.setDescription("Ensure the setters and getters work as expected");
		assertEquals("Ensure the setters and getters work as expected", match.getDescription());

		match.setDate("At runtime");
		assertEquals("At runtime", match.getDate());

		match.setLocation("Somewhere in memory");
		assertEquals("Somewhere in memory", match.getLocation());
		
		match.setHomeTeamName("Test");
		match.setAwayTeamName("Away Test");

		String expected = "[\n	📢TITLE: Test Event Super Class\n	🗒️DESCRIPTION: Ensure the setters and getters work as expected\n	📅DATE: At runtime\n	📍LOCATION: Somewhere in memory]";

		assertEquals(expected, match.toString());
		
	}

}
