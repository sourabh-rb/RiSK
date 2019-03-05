/**
 * 
 */
package test.controllerTest.initializationTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.initialization.StartUpPhase;
import model.Continent;
import model.Country;
import model.Player;

/**
 * This class contains test methods for initialization phase
 * 
 * @author Aravind Reddy
 *
 */
public class InitializationTest {
	StartUpPhase initialize;
	ArrayList<Country> europeCountryList = new ArrayList<Country>();
	ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Continent> continentList = new ArrayList<Continent>();
	@Before
	public void set() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		player1.setName("Aravind");
		player2.setName("charan");
		player3.setName("shivani");
		player4.setName("Sourabh");

		
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		//playerList.add(player4);

		Continent continent1 = new Continent();
		Country country1 = new Country();
		country1.setName("India");
		Country country2 = new Country();
		country2.setName("china");
		Country country3 = new Country();
		country3.setName("pakistan");
		Country country7 = new Country();
		country7.setName("japan");
		Country country8 = new Country();
		country8.setName("Iran");
		Country country9 = new Country();
		country9.setName("Iraq");

		Continent continent2 = new Continent();
		Country country4 = new Country();
		country4.setName("France");
		Country country5 = new Country();
		country5.setName("Germany");
		Country country6 = new Country();
		country6.setName("Sweden");

		

		ArrayList<Country> countryList = new ArrayList<Country>();
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
		 initialize = new StartUpPhase();
	}
	@Test
	public void noOfArmiesTest() {
		int noOfArmies = initialize.calculateNoOfArmies(playerList.size());
		assertEquals(noOfArmies, 35);
		
	}
	
	@Test
	public void noOfContinentsTest() {
		int noOfContinents = initialize.noOfContinents(continentList);
		assertEquals(noOfContinents, 2);
	}
	@Test
	public void noOfPlayersTest() {
		int noOfPlayers = initialize.noOfPlayers(playerList);
		assertEquals(noOfPlayers, 3);
	}
	
	
	

}
