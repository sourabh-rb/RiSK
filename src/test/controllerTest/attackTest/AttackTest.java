package test.controllerTest.attackTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.initialization.StartUpPhase;
import model.Card;
import model.Continent;
import model.Country;
import model.Player;

public class AttackTest {
	Player playerAttack;
	ArrayList<Country> europeCountryList = new ArrayList<Country>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Continent> continentList = new ArrayList<Continent>();
	ArrayList<Country> countryListPlayer1 = new ArrayList<Country>();
	ArrayList<Country> countryListPlayer2 = new ArrayList<Country>();
	ArrayList<Country> countryListPlayer3 = new ArrayList<Country>();
	ArrayList<Country> countryListPlayer4 = new ArrayList<Country>();
	Country country1;
	Country country2;
	Country country3;
	Country country5;
	Player player3;
	Player player4;
	Player player1;
	Player player2;
	ArrayList<Country> countryList = new ArrayList<Country>();

	@Before
	public void set() {

		 player1 = new Player();
		 player2 = new Player();
		player3 = new Player();
	player4 = new Player();
		
		player1.setName("Aravind");
		player2.setName("charan");
		

		playerList.add(player1);
		playerList.add(player2);
		
		// playerList.add(player4);

		Continent continent1 = new Continent();
		country1 = new Country();
		country1.setName("India");
		country1.setOwner(player1);
		country2 = new Country();
		country2.setName("china");
		 country3 = new Country();
		country3.setName("pakistan");
		

		Continent continent2 = new Continent();
		Country country4 = new Country();
		country4.setName("France");
		 country5 = new Country();
		country5.setName("Germany");
		Country country6 = new Country();
		country6.setName("Sweden");

		
		countryList.add(country1);
		countryList.add(country2);
		countryList.add(country3);
		

		continent1.setCountriesComprised(countryList);

		europeCountryList.add(country4);
		europeCountryList.add(country5);
		europeCountryList.add(country6);
		continent2.setCountriesComprised(europeCountryList);

		continentList.add(continent1);
		continentList.add(continent2);

		countryListPlayer1.add(country1);
		countryListPlayer1.add(country2);
		countryListPlayer1.add(country3);
		countryListPlayer1.add(country4);
		countryListPlayer1.add(country6);

		countryListPlayer2.add(country5);
		
		
		player1.setCountries(countryListPlayer1);
		player2.setCountries(countryListPlayer2);
		ArrayList<Card> cardTypeList = new ArrayList<Card>();
		player1.setCardType(cardTypeList);	
		
		playerAttack = new Player();
	}
/**
 * This function is to test if decrease army function is reducing number of armies by 1 or not
 */
	@Test
	public void decreaseOneArmy() {
		country1.setArmies(6);
		int noOfArmiesLeft = playerAttack.decreaseOneArmy(country1);
		assertEquals(noOfArmiesLeft, 5);
	}
/**
 * This function tests maxNoOfDice method which gives maximum number of dice attacker and defender can have
 */
	@Test
	public void maxNoOfDice() {
		country1.setArmies(6);
		country5.setArmies(1);
		String diceValues = playerAttack.maxNoOfDice(country1, country5);
		System.out.println(diceValues);
		String dice = "3 1";
		assertEquals(diceValues,dice);
	}
	
/**
 * This function tests the attack method result
 */
	@Test
	public void attack() {
		country1.setArmies(6);
		country5.setArmies(1);
		String atttackRes = playerAttack.attack(country1, country5, 3, 2, "allOutWinner");
		String def = "defeated";
		assertEquals(atttackRes,def);
		
	}
	/**
	 * This function gives the attack result when attacker and defender decides number of dice to throw explicitly
	 */
	@Test
	public void attackWithOneArmy() {
		country1.setArmies(1);
		//country5.setArmies(4);
		String atttackRes = playerAttack.attack(country1, country5, 1, 2, "attack");
		assertEquals(atttackRes,"cannotAttack");
	}
	/**
	 * This function is used to test if it is providing winner of the game properly or not
	 */
	@Test
	public void gameWinner() {
		StartUpPhase.getInstance().getCountryList().add(country1);
		StartUpPhase.getInstance().getCountryList().add(country2);
		StartUpPhase.getInstance().getCountryList().add(country3);
		country1.setOwner(player3);
		country2.setOwner(player3);
		country3.setOwner(player4);
		countryListPlayer3.add(country1);
		countryListPlayer3.add(country2);
		countryListPlayer4.add(country3);
		player3.setCountries(countryListPlayer3);
		player4.setCountries(countryListPlayer4);
		country1.setArmies(6);
		country3.setArmies(1);
		String atttackRes = playerAttack.attack(country1, country3, 3, 2, "allOutWinner");
		assertEquals(atttackRes,"champion");
	}
}
