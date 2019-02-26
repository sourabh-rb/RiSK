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
	Fortification f;
	
	@Before
	public void set() {
		fromCountry = new Country();
		fromCountry.setArmies(10);
		fromCountry.setName("Canada");
		
		toCountry = new Country();
		toCountry.setArmies(7);
		toCountry.setName("Italy");
		
		player = new Player();
		player.setName("Shivani");
		player.setArmies(9);
		player.setCardExchangeCount(0);
		ArrayList<Country> countries = null;
		countries.add(fromCountry);
		countries.add(toCountry);
		player.setCountries(countries);
		
		f = new Fortification();
	}

	@Test
	public void positiveTest1() {
		
		boolean b = f.fortifyArmies(player, fromCountry, toCountry);
		assertTrue(b);
	}


}
