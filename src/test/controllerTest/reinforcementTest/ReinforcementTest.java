package test.controllerTest.reinforcementTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.reinforcement.Reinforcement;
import model.Continent;
import model.Country;
import model.Player;

public class ReinforcementTest {
	
	Country country;
	Player player;
	Continent continent;
	Reinforcement r;
	
	@Before
	public void set() {
		country = new Country();
		player = new Player();
		continent=new Continent();
		
		country.setArmies(10);
		country.setName("Canada");
		
		player.setName("Shivani");
		player.setArmies(9);
		
		ArrayList<Country> countries = null;
		countries.add(country);
		player.setCountries(countries);
		
		ArrayList<Continent> continents = null;
		continent.setName("Asia");
		continent.setControlValue(3);
		continents.add(continent);
		continent.setName("Australia");
		continent.setControlValue(1);
		continents.add(continent);
		player.setContinents(continents);
		
		r = new Reinforcement();
	}

	@Test
	public void positiveTest1() {
		//To test if the getReinforcementArmies() method returns correct number of armies.
		int b = r.getReinforcementArmies(player);
		assertTrue(b==1);
		
	}
	
	

}
