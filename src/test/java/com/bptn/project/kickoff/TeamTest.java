package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TeamTest {
	
	static private Team team;
	
	@BeforeAll
	static public void createTeam() {
		team = new Team("Test Team");
	}
	
	@Test
	public void testTeamCreation() {
		assertEquals("Test Team", team.getTeamName());
		assertTrue(team.getTotalGoals() == 0);
		assertEquals(0, team.getTotalMatches());
	}
	
	@Test
	public void testTeamNameChange() {
		team.setTeamName("Better Test Team");
		assertEquals("Better Test Team", team.getTeamName());
	}
	
	@Test
	public void testUpdatingRoster() {
		team.getRoster().clear();
		team.addPlayer(new Player("Kylian Mbappe", 10, "FW"));
		team.addPlayer(new Player("N'golo Kante"));
		team.addPlayer(new Player("Lebron James", 23));
		assertEquals(3, team.getRoster().size());
		team.removePlayer(1);
		assertEquals(2, team.getRoster().size());
	}
	
	@Test
	public void testFindPlayer() {
		team.addPlayer(new Player("Kylian Mbappe", 10, "FW"));
		team.addPlayer(new Player("N'Golo Kante"));
		team.addPlayer(new Player("Lebron James", 23));
		assertTrue(team.containsPlayer("Kylian Mbappe", 10, "FW"));
		assertFalse(team.containsPlayer("Fake Player", 10, "GK"));
		assertTrue(team.containsPlayer("N'Golo Kante", 0, "default"));
	}
	
	@Test
	public void testAddGoals() {
		team.setTotalGoals(3);
		assertEquals(3, team.getTotalGoals());
	}
	
	@Test
	public void testAddMatch() {
		team.setTotalMatches(1);
		assertEquals(1, team.getTotalMatches());
	}
	
	@Test
	public void testSetMatches() {
		team.setMatch(new Match("Test Match", "This is a test Match", "Test Date", "Test Location", "Test homeTeamName", "Test awayTeamName"));
		assertEquals(1, team.getMatches().size());
		team.setMatch(new Match("Test Match2", "This is another test Match", "Test Date", "Test Location", "Test homeTeamName", "Test awayTeamName"));
		assertEquals(2, team.getMatches().size());
	}
	


}
