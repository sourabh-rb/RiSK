package controller.fortification;

import java.util.ArrayList;

import model.Country;
import model.Player;

/**
 * This class represents the fortification phase of the game. It contains methods that will be required by the 
 * player to perform necessary fortification actions.
 * 
 * @author Shivani
 * @version 1.0.0
 * 
 */

public class Fortification{
	
	/**
	 * This method is used to perform the fortification stage in the game. In this stage the user can move an army from one
	 * country that he owns to another if they are connected.
	 *   
	 * @param player Contains the details of the player.
	 * @param fromcountry The country from which the army will be moved.
	 * @param tocountry Thecountry to which the army will be moved.
	 * @return true if the fortification was successful, else false.
	 */
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
