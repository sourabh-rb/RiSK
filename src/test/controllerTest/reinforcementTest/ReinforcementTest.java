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
	
	Country country1,country2;
	Player player;
	Continent continent1,continent2;
	Reinforcement r;
	private ArrayList<Country> countries;
	private ArrayList<Continent> continents;
	
	@Before
	public void set() {
		country1 = new Country();
		player = new Player();
		continent1=new Continent();
		continent2=new Continent();
		country2 = new Country();
		countries = new ArrayList<>();
		continents = new ArrayList<>();
	
		country1.setArmies(10);
		country1.setName("Canada");
				
		countries.add(country1);
		
		continent1.setName("Asia");
		continent1.setControlValue(3);
		continents.add(continent1);
		continent2.setName("Australia");
		continent2.setControlValue(1);
		continents.add(continent2);
		
		player.setName("Shivani");
		player.setArmies(9);
		player.setCountries(countries);
		player.setContinents(continents);
		
		r = new Reinforcement();
}

	@Test
	public void positiveTest1() {
		//To test if the getReinforcementArmies() method returns correct number of armies.
		int b = r.getReinforcementArmies(player);
		assertTrue(b==4);
	}
}
