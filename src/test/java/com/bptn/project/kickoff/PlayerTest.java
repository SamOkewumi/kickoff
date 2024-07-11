package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	private Player player;
	
	@BeforeEach //solution implemented as playerCreation would fail due to setYellowCard running before. 
	public void createPlayer() {
		player = new Player("Kylian Mbappe", 10, "FW");
	}
	
	@Test
	public void testPlayerCreation() {
		assertEquals("Kylian Mbappe", player.getName());
		assertEquals(10, player.getNumber());
		assertEquals("FW", player.getPosition());
		assertEquals(0, player.getGoalScored());
		assertEquals(0, player.getYellowCard());
		assertEquals(0, player.getRedCard());
	}
	
	
	@Test
	public void testSetPosition() {
		player.setPosition("ST");
		assertEquals("ST", player.getPosition());
	}
	
	@Test
	public void testSetGoalScored() {
		player.setGoalScored(1);
		assertEquals(1, player.getGoalScored());
		
	}
	
	@Test
	public void testSetYelowCard() {
		player.setYellowCard(1);
		assertEquals(1, player.getYellowCard());
		
		int num = 5;
		player.setYellowCard(num);
		assertEquals(1 + num, player.getYellowCard());
	}
	
	@Test void testSetRedCard() {
		player.setRedCard(1);
		assertEquals(1, player.getRedCard());
	}
	
	

}
