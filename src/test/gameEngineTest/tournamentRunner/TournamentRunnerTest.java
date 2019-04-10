package test.gameEngineTest.tournamentRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import constants.Constants;
import gameEngine.StartUpPhase;
import gameEngine.TournamentRunner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import test.gameEngineTest.player.PlayerTest;

/**
 * This class is used to test the TournamentRunner functionality.
 * This class has a total of 5 test cases.
 * 
 * @author Sourabh Rajeev Badagandi
 * @version 1.0.0
 */
public class TournamentRunnerTest
{
	private ArrayList<File> mapsTest;
	private ArrayList<String> strategyTest;
	private int gameCountTest;
	private int turnCountTest;
	private TournamentRunner runnerTest; 
	
	/**
	 * This method is used to initialize the variables before each test is run.
	 */
	@Before
	public void set()
	{
		mapsTest = new ArrayList<File>();
		File mapDir = new File(".//maps"); 
		File[] fileList = mapDir.listFiles();
		for(File file : fileList)
		{
			mapsTest.add(file);
		}
			
		strategyTest = new ArrayList<String>();
		strategyTest.add(Constants.AGGRESSIVE);
		strategyTest.add(Constants.CHEATER);
		strategyTest.add(Constants.RANDOM);
		
		gameCountTest = 4;
		turnCountTest = 20;
		
		runnerTest = new TournamentRunner(mapsTest, strategyTest, gameCountTest, turnCountTest);
		
	}

	/**
	 * This method is used to test if the maps list has been loaded successfully.
	 */
	@Test
	public void testLoadedMapsSuccessfully()
	{

		assertNotNull(runnerTest.getMaps());
		
	}
	
	/**
	 * This method is used to test if the player strategy list has been loaded successfully.
	 */
	@Test
	public void testLoadedPlayersSuccessfully()
	{

		assertNotNull(runnerTest.getPlayerStrategy());
		
	}
	
	/**
	 * This method is used to test if games count has been loaded successfully.
	 */
	@Test
	public void testLoadedGamesCountSuccessfully()
	{

		assertEquals(gameCountTest, runnerTest.getGamesToPlay());
		
	}
	
	/**
	 * This method is used to test if turn count has been loaded successfully.
	 */
	@Test
	public void testLoadedTurnCountSuccessfully()
	{

		assertEquals(turnCountTest, runnerTest.getNoOfTurns());
		
	}
}
