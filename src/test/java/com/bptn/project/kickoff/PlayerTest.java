package com.bptn.project.kickoff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.bptn.project.kickoff.player.Player;

public class PlayerTest {



	@Test
	public void testPlayerCreationWithAllFields() {
		Player player = new Player("Kylian Mbappe", 10, "FW");
		assertEquals("Kylian Mbappe", player.getName());
		assertEquals(10, player.getNumber());
		assertEquals("FW", player.getPosition());
		assertEquals(0, player.getGoalScored());
		assertEquals(0, player.getYellowCard());
		assertEquals(0, player.getRedCard());
	}

	@Test
	public void testPlayerCreationWithNameAndNumber() {
		Player player = new Player("Kylian Mbappe", 10);
		assertEquals("Kylian Mbappe", player.getName());
		assertEquals(10, player.getNumber());
		assertEquals("default", player.getPosition());
		assertEquals(0, player.getGoalScored());
		assertEquals(0, player.getYellowCard());
		assertEquals(0, player.getRedCard());
	}

	@Test
	public void testPlayerCreationWithNameOnly() {
		Player player = new Player("Kylian Mbappe");
		assertEquals("Kylian Mbappe", player.getName());
		assertEquals(0, player.getNumber());
		assertEquals("default", player.getPosition());
		assertEquals(0, player.getGoalScored());
		assertEquals(0, player.getYellowCard());
		assertEquals(0, player.getRedCard());
	}



	@Test
	public void testPositionUpdate() {
		Player player = new Player("Kylian Mbappe", 10, "FW");
		String[] positions = {"ST", "FW", "CB", "GK", "Bench", "CM", "DF"};

		//iterate through an array of strings to determine if the position variable is being updated
		for(String position : positions) {
			player.setPosition(position);
			assertEquals(position, player.getPosition());
		}

	}

	@Test
	public void testSetGoalScored() {
		Player player = new Player("Kylian Mbappe", 10, "FW");
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
		Player player = new Player("Kylian Mbappe", 10, "FW");
		player.setYellowCard(1);
		assertEquals(1, player.getYellowCard());

		int num = 5;
		player.setYellowCard(num);
		assertEquals(1 + num, player.getYellowCard());
	}

	@Test
	public void testSetRedCard() {
		Player player = new Player("Kylian Mbappe", 10, "FW");
		int[] redCardNumber = {1, 2, 3, 5, 9};
		int previousAmount = player.getRedCard();

		for(int increment : redCardNumber) {
			player.setRedCard(increment);
			assertEquals(previousAmount + increment, player.getRedCard());
			previousAmount = player.getRedCard();
		}

	}
	
	@Test
    public void testToString() {
        Player player = new Player("Kylian Mbappe", 10, "FW");   
        assertTrue(player.toString().contains("Kylian Mbappe"));
        assertTrue(player.toString().contains("10"));
        assertTrue(player.toString().contains("FW"));
    }



}
