package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	
	private Player player;
	
	@BeforeEach 
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
		String[] positions = {"ST", "FW", "CB", "GK", "Bench", "CM", "DF"};
		
		//iterate through an array of strings and test if the position variable is updated as a result
		for(String position : positions) {
			player.setPosition(position);
			assertEquals(position, player.getPosition());
		}
		
	}
	
	@Test
	public void testSetGoalScored() {
		int previousTotal = 0;
		
		//increment the goal scored to determine if the change is being recorded
		for (int i = 0; i <5; i++) {
			player.setGoalScored(i);
			assertEquals(previousTotal + i, player.getGoalScored());
			previousTotal += i;
			
		}
		
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
		int[] redCardNumber = {1, 2, 3, 5, 9};
		int previousAmount = player.getRedCard();
		
		for(int increment : redCardNumber) {
			player.setRedCard(increment);
			assertEquals(previousAmount + increment, player.getRedCard());
			previousAmount = player.getRedCard();
		}

	}
	
	

}
