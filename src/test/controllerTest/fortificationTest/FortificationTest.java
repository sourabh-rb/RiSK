package test.controllerTest.fortificationTest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.fortification.Fortification;
import model.Country;
import model.Player;

public class FortificationTest {
	
	Country fromCountry;
	Country toCountry;
	Player player;
	int armies;
	Fortification f;
	private ArrayList<Country> countries;
	
	@Before
	public void set() {
		fromCountry = new Country();
		toCountry = new Country();
		player = new Player();
		countries = new ArrayList<>();
		
		fromCountry.setArmies(10);
		fromCountry.setName("Canada");
		
		toCountry = new Country();
		toCountry.setArmies(7);
		toCountry.setName("Italy");
		
		player.setName("Shivani");
		countries.add(fromCountry);
		countries.add(toCountry);
		player.setCountries(countries);
		
		
		f = new Fortification();
	}

	@Test
	public void positiveTest1() {
		armies=2;
		boolean b = f.fortifyArmies(player, fromCountry, toCountry, armies);
		assertTrue(b);
	}
	
	@Test
	public void negativeTest1() {

		armies=-2;
		boolean b = f.fortifyArmies(player, fromCountry, toCountry, armies);
		assertFalse(b);
	}


}
