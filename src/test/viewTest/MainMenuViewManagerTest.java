package test.viewTest;
/**
 * This class is used to test the Save/Load functionality.
 * This class has a total of 8 test cases.
 * 
 * @author Sourabh Rajeev Badagandi
 * @version 1.0.0
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import org.junit.Before;
import org.junit.Test;

import gameEngine.StartUpPhase;

public class MainMenuViewManagerTest
{
	private File test_file;
	
	/**
	 * This method is used to initialize the variables before each test is run.
	 */
	@Before
	public void set()
	{
		File test_file = new File("C:\\SRB\\Misc\\JavaFX\\RiSK\\Saved Games\\test_game.ser");
		try {
    		FileInputStream fi = new FileInputStream(test_file);
    		ObjectInputStream oi = new ObjectInputStream(fi);

    		StartUpPhase.setStartPhaseObject((StartUpPhase) oi.readObject());

    		oi.close();
    		fi.close();

    	} catch (FileNotFoundException e) {
    		System.out.println("File not found");
    	} catch (IOException e) {
    		System.out.println("Error initializing stream");
    	} catch (ClassNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		
	}
	
	/**
	 * This method is used to test if the game has been loaded successfully.
	 */
	@Test
	public void testLoadingSavedObject()
	{

		assertNotNull(StartUpPhase.getInstance());
		
	}
	
	/**
	 * This method is used to test if the game has loaded Players.
	 */
	@Test
	public void testLoadedPlayers()
	{

		assertNotNull(StartUpPhase.getInstance().getPlayerList());
		
	}

}
