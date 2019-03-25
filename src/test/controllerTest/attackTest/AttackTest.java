package test.controllerTest.attackTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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
	Country country1;
	Country country5;

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
		// playerList.add(player4);

		Continent continent1 = new Continent();
		country1 = new Country();
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

		countryListPlayer1.add(country1);
		countryListPlayer1.add(country2);
		countryListPlayer1.add(country3);
		countryListPlayer1.add(country4);
		country1.setArmies(5);

		countryListPlayer2.add(country5);
		countryListPlayer2.add(country6);
		country5.setArmies(2);
		player1.setCountries(countryListPlayer1);
		player2.setCountries(countryListPlayer2);
		playerAttack = new Player();
	}

	@Test
	public void decreaseOneArmy() {
		int noOfArmiesLeft = playerAttack.decreaseOneArmy(country1);
		assertEquals(noOfArmiesLeft, 3);
	}

	@Test
	public void maxNoOfDice() {
		String diceValues = playerAttack.maxNoOfDice(country1, country5);
		assertEquals(diceValues, "3 2");
	}

	@Test
	public void attack() {
		boolean res=false;
		String atttackRes = playerAttack.attack(country1, country5, 3, 2, "allOutWinner");
		/*
		 * System.out.println("aravind"); if(atttackRes.equals("defeated")) res= true;
		 * assertTrue(res);
		 */
		String def = "defeated";
		assertEquals(atttackRes,def);
		
	}
}
