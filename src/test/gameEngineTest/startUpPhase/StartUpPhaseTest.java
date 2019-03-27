package test.gameEngineTest.startUpPhase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import gameEngine.Continent;
import gameEngine.Country;
import gameEngine.Player;
import gameEngine.StartUpPhase;

/**
 * This class tests the start up phase of the game. This class has a total of 3
 * test cases.
 * 
 * @author Aravind
 * @version 2.0.0
 */
public class StartUpPhaseTest {

	StartUpPhase initialize;
	Player player1, player2, player3, player4;
	private ArrayList<Country> europeCountryList, countryList;
	private ArrayList<Player> playerList;
	private ArrayList<Continent> continentList;
	Continent continent1, continent2;
	Country country1, country2, country3, country4, country5, country6, country7, country8, country9;

	/**
	 * This method sets the value that are to be initialized before running each
	 * test case.
	 */
	@Before
	public void set() {
		player1 = new Player();
		player2 = new Player();
		player3 = new Player();
		player4 = new Player();
		europeCountryList = new ArrayList<Country>();
		playerList = new ArrayList<Player>();
		continentList = new ArrayList<Continent>();

		player1.setName("Aravind");
		player2.setName("charan");
		player3.setName("shivani");
		player4.setName("Sourabh");

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		continent1 = new Continent();
		country1 = new Country();
		country1.setName("India");
		country2 = new Country();
		country2.setName("china");
		country3 = new Country();
		country3.setName("pakistan");
		country7 = new Country();
		country7.setName("japan");
		country8 = new Country();
		country8.setName("Iran");
		country9 = new Country();
		country9.setName("Iraq");

		continent2 = new Continent();
		country4 = new Country();
		country4.setName("France");
		country5 = new Country();
		country5.setName("Germany");
		country6 = new Country();
		country6.setName("Sweden");

		countryList = new ArrayList<Country>();
		countryList.add(country1);
		countryList.add(country2);
		countryList.add(country3);
		countryList.add(country7);
		countryList.add(country8);

		continent1.setCountriesComprised(countryList);

		europeCountryList.add(country4);
		europeCountryList.add(country5);
		europeCountryList.add(country6);

		continent2.setCountriesComprised(europeCountryList);

		continentList.add(continent1);
		continentList.add(continent2);
	}

	/**
	 * This method is used to test the method that calculates the number of armies
	 * that are to be given to each player at the start of the game.
	 */
	@Test
	public void noOfArmiesTest() {

		System.out.println(playerList.size());
		int noOfArmies = initialize.calculateNoOfArmies(playerList.size());
		assertEquals(noOfArmies, 35);

	}

	/**
	 * This method is used to test the method that calculates the number of
	 * continents in the game.
	 */
	@Test
	public void noOfContinentsTest() {
		int noOfContinents = initialize.noOfContinents(continentList);
		assertEquals(noOfContinents, 2);
	}

	/**
	 * This method is used to initialize the number of players for the game.
	 */
	@Test
	public void noOfPlayersTest() {
		int noOfPlayers = initialize.noOfPlayers(playerList);
		assertEquals(noOfPlayers, 3);
	}

}
