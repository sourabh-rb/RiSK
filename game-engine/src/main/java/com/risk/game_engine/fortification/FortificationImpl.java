package com.risk.game_engine.fortification;

import java.util.ArrayList;

import com.risk.model.Country;
import com.risk.model.Player;

public class FortificationImpl implements Fortification{
	
	public boolean fortifyArmies(Player player, Country fromcountry,Country tocountry) {
		ArrayList<Country> playercountries = player.getCountries();
		if(playercountries != null && playercountries.contains(fromcountry) && playercountries.contains(tocountry)) {
			//Get the index of the countries that the user wants to involve in the fortification phase.
			int i = playercountries.indexOf(fromcountry);
			int j = playercountries.indexOf(tocountry);
			//Reduce one army from the country the user chose.
			Country country1 = playercountries.get(i);
			country1.setArmies(country1.getArmies()-1);
			//Add one army to the country the user wants to fortify.
			Country country2 = playercountries.get(i);
			country2.setArmies(country2.getArmies()+1);
			//Update the countries with new army values
			playercountries.remove(i);
			playercountries.remove(j);
			playercountries.add(country1);
			playercountries.add(country2);
			//Update the player with the new values.
			player.setCountries(playercountries);
			return true;
		}
		return false;
	}
	
}
