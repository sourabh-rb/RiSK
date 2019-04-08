package test.gameEngineTest.player;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import constants.Constants;
import gameEngine.StartUpPhase;
import gameEngine.Card;
import gameEngine.Continent;
import gameEngine.Country;
import gameEngine.Player;

/**
 * This class tests the Reinforcement, Fortification and attack phases.
 * 
 * @author Shivani, Aravind
 * @version 2.0.0
 */
public class PlayerTest {

	StartUpPhase initialize;
	Country country1, country2, attackCountry1, attackCountry2, attackCountry3, attackCountry4, attackCountry5, attackCountry6, fromCountry, toCountry;
	Player player1, player2, attackPlayer1, attackPlayer2, attackPlayer3, attackPlayer4, player;
	Continent continent1, continent2, attackContinent1, attackContinent2;
	Card card1, card2, card3;
	private ArrayList<Country> countries, fortificationCountries, attackCountryListPlayer1, attackCountryListPlayer2, attackCountryListPlayer3, attackCountryListPlayer4,attackCountryList;
	private ArrayList<Continent> continents,attackContinentList;
	private ArrayList<Card> cardTypes, attackCardTypeList;
	ArrayList<Player> attackPlayerList;
	Player p;
	int armies;

	/**
	 * This method sets the value that are to be initialized before running each
	 * test case.
	 */
	@Before
	public void set() {
		initialize= new StartUpPhase();
		country1 = new Country();
		country2 = new Country();
		attackCountry1 = new Country();
		attackCountry2 = new Country();
		attackCountry3 = new Country();
		attackCountry4 = new Country();
		attackCountry5 = new Country();
		attackCountry6 = new Country();
		fromCountry = new Country();
		toCountry = new Country();
		
		card1 = new Card();
		card2 = new Card();
		card3 = new Card();
		
		player1 = new Player();
		player2 = new Player();
		attackPlayer1 = new Player();
		attackPlayer2 = new Player();
		attackPlayer3 = new Player();
		attackPlayer4 = new Player();
		player = new Player();
		
		continent1 = new Continent();
		continent2 = new Continent();
		attackContinent1 = new Continent();
		attackContinent1 = new Continent();
		
		countries = new ArrayList<>();
		fortificationCountries = new ArrayList<>();
		attackCountryListPlayer1 = new ArrayList<>();
		attackCountryListPlayer2 = new ArrayList<>();
		attackCountryListPlayer3 = new ArrayList<>();
		attackCountryListPlayer4 = new ArrayList<>();
		attackCountryList = new ArrayList<>();
		fortificationCountries = new ArrayList<>();
		
		continents = new ArrayList<>();
		attackContinentList = new ArrayList<>();
		
		cardTypes = new ArrayList<>();
		attackCardTypeList = new ArrayList<>();
		country1.setName("Canada");
		country2.setName("India");
		continent1.setName("Asia");
		continent2.setName("Australia");
		player1.setName("Shivani");
		player2.setName("Sourabh");
		attackPlayer1.setName("Arvind");
		attackPlayer2.setName("Charan");
		attackPlayer3.setName("Reddy");
		attackPlayer4.setName("Adiga");
		player.setName("Aravind");

		fromCountry.setArmies(10);
		fromCountry.setName("Canada");
		toCountry.setArmies(7);
		toCountry.setName("Italy");
		
		attackCountry1.setName("India");
		attackCountry1.setOwner(attackPlayer1);
		attackCountry2.setName("china");
		attackCountry2.setOwner(attackPlayer1);
		attackCountry3.setName("pakistan");
		attackCountry3.setOwner(attackPlayer1);
		
		attackCountry4.setName("France");
		attackCountry4.setOwner(attackPlayer1);
		attackCountry5.setName("Germany");
		attackCountry5.setOwner(attackPlayer2);
		attackCountry6.setName("Sweden");
		attackCountry6.setOwner(attackPlayer1);
		
		
		
		attackPlayer1.setCountries(attackCountryList);
		attackPlayer2.setCountries(countries);
		
		
		attackContinent1.setCountriesComprised(attackCountryList);
		
		attackContinentList.add(attackContinent1);
		attackContinentList.add(attackContinent2);
		
		attackCountryListPlayer1.add(attackCountry1);
		attackCountryListPlayer1.add(attackCountry2);
		attackCountryListPlayer1.add(attackCountry3);
		attackCountryListPlayer1.add(attackCountry4);
		attackCountryListPlayer1.add(attackCountry6);
		
		attackCountryListPlayer2.add(attackCountry5);
		
		attackPlayer1.setCountries(attackCountryListPlayer1);
		attackPlayer2.setCountries(attackCountryListPlayer2);
		
		attackPlayer1.setCardType(attackCardTypeList);

		fortificationCountries.add(fromCountry);
		fortificationCountries.add(toCountry);
		player.setCountries(fortificationCountries);


		p = new Player();
		
	}

	/**
	 * This method is used to check if the getReinforcementArmies method is updating
	 * the correct value for the player.
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
		assertTrue(player1.getReinforcementArmies());

		assertTrue(player2.getReinforcementArmies());
	}

	/**
	 * This method is used to check if the reinforceArmies method is updating the
	 * countries correctly.
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
		int armies = 2;
		// To test if the reinforceArmies() method returns correct number of armies.
		assertTrue(player1.reinforceArmies(country1, armies,Constants.HUMAN));
	}

	/**
	 * This method checks the number of armies returned to the player after he
	 * exchanges the cards.
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
		int armies = player1.armiesFromCardExchange(3, 0, 0);
		assertEquals(5, armies);
	}
	
	/**
	 * This method checks the incorrect input for card exchange by player.
	 */
	@Test
	public void negativeFirstCardExchange() {
		card1.setType(Constants.ARTILLERY);
		cardTypes.add(card1);
		card2.setType(Constants.ARTILLERY);
		cardTypes.add(card2);
		card3.setType(Constants.ARTILLERY);
		cardTypes.add(card3);
		player1.setCardExchangeCount(0);
		player1.setCardType(cardTypes);
		int armies = player1.armiesFromCardExchange(3, 1, 0);
		assertEquals(0, armies);
	}

	/**
	 * This method checks if the user can exchange different cards.
	 */
	@Test
	public void positiveDifferentCardExchange() {

		card1.setType(Constants.CAVALRY);
		cardTypes.add(card1);
		card2.setType(Constants.INFANTRY);
		cardTypes.add(card2);
		card3.setType(Constants.ARTILLERY);
		cardTypes.add(card3);
		player1.setCardExchangeCount(2);
		player1.setCardType(cardTypes);
		int armies = player1.armiesFromCardExchange(1, 1, 1);
		assertEquals(15, armies);
	}

	/**
	 * This method checks whether the user has selected correct cards to exchange.
	 */
	@Test
	public void invalidCardSelection() {
		player1.setCardExchangeCount(2);
		player1.setCardType(cardTypes);
		int armies = p.armiesFromCardExchange(0, 0, 0);
		assertEquals(0, armies);
	}

	/**
	 * This method is used to test the fortify armies method for a positive result.
	 */
	@Test
	public void positiveFortifyArmies() {

		armies = 2;
		boolean b = player.fortifyArmies(fromCountry, toCountry, armies);
		assertTrue(b);
	}

	/**
	 * This method is used to test the fortify armies method for a negative result.
	 */
	@Test
	public void negativeFortifyArmies() {

		armies = -2;
		boolean b = player.fortifyArmies(fromCountry, toCountry, armies);
		assertFalse(b);
	}
	
	/**
	 * This function is to test if decrease army function is reducing number of armies by 1 or not
	 */
		@Test
		public void decreaseOneArmy() {
			attackCountry1.setArmies(6);
			int noOfArmiesLeft = p.decreaseOneArmy(attackCountry1);
			assertEquals(noOfArmiesLeft, 5);
		}
	/**
	 * This function tests maxNoOfDice method which gives maximum number of dice attacker and defender can have
	 */
		@Test
		public void maxNoOfDice() {
			attackCountry1.setArmies(6);
			attackCountry4.setArmies(1);
			String diceValues = p.maxNoOfDice(attackCountry1, attackCountry4);
			System.out.println(diceValues);
			String dice = "3 1";
			assertEquals(diceValues,dice);
		}
		
	/**
	 * This function tests the attack method result
	 */
		@Test
		public void attack() {
			attackCountry1.setArmies(6);
			attackCountry4.setArmies(1);
			String atttackRes = p.attack(attackCountry1, attackCountry4, 3, 2, "allOutWinner");
			String def = "defeated";
			assertEquals(atttackRes,def);
			
		}
		/**
		 * This function gives the attack result when attacker and defender decides number of dice to throw explicitly
		 */
		@Test
		public void attackWithOneArmy() {
			attackCountry1.setArmies(1);
			//country5.setArmies(4);
			String atttackRes = p.attack(attackCountry1, attackCountry5, 1, 2, "attack");
			assertEquals(atttackRes,"cannotAttack");
		}
		/**
		 * This function is used to test if it is providing winner of the game properly or not
		 */
		@Test
		public void gameWinner() {
			StartUpPhase.getInstance().getCountryList().clear();
			StartUpPhase.getInstance().getCountryList().add(attackCountry1);
			StartUpPhase.getInstance().getCountryList().add(attackCountry2);
			StartUpPhase.getInstance().getCountryList().add(attackCountry3);
			attackCountry1.setOwner(attackPlayer3);
			attackCountry2.setOwner(attackPlayer3);
			attackCountry3.setOwner(attackPlayer4);
			attackCountryListPlayer3.add(attackCountry1);
			attackCountryListPlayer3.add(attackCountry2);
			attackCountryListPlayer4.add(attackCountry3);
			attackPlayer3.setCountries(attackCountryListPlayer3);
			attackPlayer4.setCountries(attackCountryListPlayer4);
			attackCountry1.setArmies(6);
			attackCountry3.setArmies(1);
			String atttackRes = p.attack(attackCountry1, attackCountry3, 3, 2, "allOutWinner");
			assertEquals(atttackRes,"champion");
		}
		
		/**
		 * This function is to test if valid move(owner change) happened after attacking
		 */
		@Test
		public void movingOwnerAfterAttack(){
			attackCountry1.setArmies(6);
			attackCountry5.setArmies(1);
			p.attack(attackCountry1, attackCountry5, 3, 2, "allOutWinner");
			assertEquals(attackCountry4.getOwner(),attackCountry1.getOwner());
		}
		/**
		 * This function is to test if valid move(number of countries change under attacker) happened after winning through attack
		 */
		@Test
		public void movingCountryAfterAttack() {
			attackCountry1.setArmies(6);
			attackCountry5.setArmies(1);
			p.attack(attackCountry1, attackCountry5, 3, 2, "allOutWinner");
			assertEquals(attackCountry1.getOwner().getCountries().size(),6);
		}
}