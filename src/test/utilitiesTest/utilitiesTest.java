package test.utilitiesTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import constants.GamePhase;
import model.Country;
import model.Player;
import utilities.Utilities;

public class utilitiesTest {
	Country country1;
	Player player;
	Utilities u;
	private ArrayList<Country> countries;
	
	/**
	 * This method is used to initialize and set the values for the common properties pertaining to countries and players 
	 */
	@Before
	public void set() {
		country1 = new Country();
		player = new Player();
		countries = new ArrayList<>();
		
		country1.setName("India");
		countries.add(country1);
		player.setName("Shivani");
		player.setCountries(countries);
		
		u = new Utilities();
	}

	/**
	 * This method is used to test the isUserInputValid method for valid input in fortification phase.
	 */
	@Test
	public void checkPositiveValidInputFortification() {
		int armies=3;
		int userInput=1;
		
		boolean b= u.isUserInputValid(userInput, armies, GamePhase.FORTIFICATION);
		assertTrue(b);
		
	}
	
	/**
	 * This method is used to test the isUserInputValid method for invalid input in fortification phase.
	 */
	@Test
	public void checkNegativeValidInputFortification() {
		int armies=3;
		int userInput=5;
		
		boolean b= u.isUserInputValid(userInput, armies, GamePhase.FORTIFICATION);
		assertFalse(b);
		
	}
	
	/**
	 * This method is used to test the isUserInputValid method for valid input in reinforcement phase.
	 */
	@Test
	public void checkPositiveValidInputReinforcement() {
		int armies=4;
		int userInput=2;
		boolean b= u.isUserInputValid(userInput, armies, GamePhase.REINFORCEMENT);
		assertTrue(b);
		
	}
	/**
	 * This method is used to test the isUserInputValid method for invalid input in reinforcement phase.
	 */
	@Test
	public void checkNegativeValidInputReinforcement() {
		int armies=4;
		int userInput=10;
		boolean b= u.isUserInputValid(userInput, armies, GamePhase.REINFORCEMENT);
		assertFalse(b);
		
	}
	
	/**
	 * This method is used to test the method getCountryFromPlayer.
	 */
	@Test
	public void checkGetCountryFromPlayer() {
		String countryName="India";
		Country c= u.getCountryFromPlayer(player, countryName);
		assertTrue(c.equals(country1));
		
	}
}
