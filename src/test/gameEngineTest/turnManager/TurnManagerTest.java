package test.gameEngineTest.turnManager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import gameEngine.Player;
import gameEngine.StartUpPhase;
import gameEngine.TurnManager;

/**
 * This class checks the turn manager to find current and next players.
 * @author Shivani
 * @version 2.0.0
 */
public class TurnManagerTest {

	StartUpPhase start1;
	TurnManager turns;
	Player player1, player2, player3, outputPlayer;
	
	/**
	 * Set common values before each test case.
	 */
	@Before
	public void set() {
		
		
		player1 = new Player();
		player2 = new Player();
		player3 = new Player();
		player1.setName("Sourabh");
		player2.setName("Charan");
		player3.setName("Aravind");
		
		start1 = new StartUpPhase();
		start1.getPlayerList().add(player1);
		start1.getPlayerList().add(player2);
		start1.getPlayerList().add(player3);
		turns = new TurnManager(start1);
	}
	
	/**
	 * This method returns the current player.
	 */
	@Test
	public void checkCurrentPlayer() {

		
		
		outputPlayer = turns.currentPlayer("Charan");
		assertEquals(outputPlayer,player2);
	}
	
	/**
	 * This method returns the next player.
	 */
	@Test
	public void checkNextPlayer() {
	
		outputPlayer = turns.nextPlayer("Charan");
		assertEquals(outputPlayer, player3);
	}
	
}
