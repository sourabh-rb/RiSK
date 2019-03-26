package test.controllerTest.reinforcementTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import constants.Constants;
import controller.reinforcement.Reinforcement;
import model.Card;
import model.Continent;
import model.Country;
import model.Player;

/**
 * This class is used to test the reinforcement class. 
 * @author Shivani
 * @version 1.0.0
 *
 */
public class ReinforcementTest {
	
	Country country1,country2;
	Player player1, player2;
	Continent continent1,continent2;
	Card card1,card2,card3;
	private ArrayList<Country> countries;
	private ArrayList<Continent> continents;
	private ArrayList<Card> cardTypes;
	Reinforcement r;
	
	/**
	 * This method is used to initialize and set the values for the common properties pertaining to countries and players 
	 */
	@Before
	public void set() {
		country1 = new Country();
		country2 = new Country();
		card1 = new Card(); 
		card2 = new Card();
		card3 = new Card();
		player1 = new Player();
		player2= new Player();
		continent1=new Continent();
		continent2=new Continent();
		countries = new ArrayList<>();
		continents = new ArrayList<>();
		cardTypes = new ArrayList<>();
		
		country1.setName("Canada");	
		country2.setName("India");
		continent1.setName("Asia");
		continent2.setName("Australia");
		player1.setName("Shivani");
		player2.setName("Sourabh");
		
		r = new Reinforcement();
}

	/**
	 * This method is used to check if the getReinforcementArmies method is updating the correct value
	 * for the player.
	 */
	@Test
	public void positiveGetReinforcementArmies() {
		
		country1.setArmies(10);		
		countries.add(country1);
		
		continent1.setControlValue(3);
		continents.add(continent1);
		
		continent2.setControlValue(1);
		continents.add(continent2);
		
		country2.setArmies(9);
		countries.add(country2);
		
		player1.setCountries(countries);
		player1.setContinents(continents);
		
		player2.setCountries(countries);
		assertTrue(r.getReinforcementArmies(player1));
		
		assertTrue(r.getReinforcementArmies(player2));
	}
	
	/**
	 * This method is used to check if the reinforceArmies method is updating the countries correctly.
	 */
	@Test
	public void positiveReinforceArmies() {
		
		country1.setArmies(10);		
		countries.add(country1);
		
		continent1.setControlValue(3);
		continents.add(continent1);
		
		continent2.setControlValue(1);
		continents.add(continent2);
		
		player1.setName("Shivani");
		player1.setArmies(9);
		player1.setCountries(countries);
		player1.setContinents(continents);
		int armies=2;
		//To test if the reinforceArmies() method returns correct number of armies.
		assertTrue(r.reinforceArmies(player1,country1,armies));
	}
	
	/**
	 * This method checks the number of armies returned to the player after he exchanges the cards.
	 */
	@Test
	public void positiveFirstCardExchange() {
		card1.setType(Constants.ARTILLERY);
		cardTypes.add(card1);
		card2.setType(Constants.ARTILLERY);
		cardTypes.add(card2);
		card3.setType(Constants.ARTILLERY);
		cardTypes.add(card3);
		player1.setCardExchangeCount(0);
		player1.setCardType(cardTypes);
		int armies= r.armiesFromCardExchange(player1,3,0,0);
		assertEquals(5,armies);
	}
	
	/**
	 * This method checks if the user can exchange different cards.
	 */
	@Test
	public void positiveDifferentCardExchange() {
		card1.setType(Constants.ARTILLERY);
		cardTypes.add(card1);
		card2.setType(Constants.INFANTRY);
		cardTypes.add(card2);
		card3.setType(Constants.CAVALRY);
		cardTypes.add(card3);
		player1.setCardExchangeCount(2);
		player1.setCardType(cardTypes);
		int armies= r.armiesFromCardExchange(player1,1,1,1);
		assertEquals(15,armies);
	}
	
	/**
	 * This method checks whether the user has selected correct cards to exchange.
	 */
	@Test
	public void invalidCardSelection() {
		player1.setCardExchangeCount(2);
		player1.setCardType(cardTypes);
		int armies= r.armiesFromCardExchange(player1,0,0,0);
		assertEquals(0,armies);
	}
}
