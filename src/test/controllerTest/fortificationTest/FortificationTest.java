package test.controllerTest.fortificationTest;

import static org.junit.Assert.*;

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
		toCountry = new Country();
		player = new Player();
		f = new Fortification();
	}

	@Test
	public void positiveTest1() {
		
		boolean b = f.fortifyArmies(player, fromCountry, toCountry);
		assertTrue(b);
	}


}
