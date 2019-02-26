package controller.fortification;

import java.util.ArrayList;

import model.Country;
import model.Player;
import utilities.Utilities;

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
	 * @param fromCountry The country from which the army will be moved.
	 * @param toCountry Thecountry to which the army will be moved.
	 * @return true if the fortification was successful, else false.
	 */
	public boolean fortifyArmies(Player player, Country fromCountry,Country toCountry) {
		
		Utilities.gameLog("Player: "+player.getName()+"|| Stage: Fortification || Countries involved: "+fromCountry+","+toCountry);
		ArrayList<Country> playercountries = player.getCountries();
		if(playercountries != null && playercountries.contains(fromCountry) && playercountries.contains(toCountry)) {
			//Get the index of the countries that the user wants to involve in the fortification phase.
			int i = playercountries.indexOf(fromCountry);
			int j = playercountries.indexOf(toCountry);
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
			Utilities.gameLog("Player: "+player.getName()+"|| Countries fortified");
			return true;
		}
		Utilities.gameLog("Player: "+player.getName()+"|| Countries could not be fortified");
		return false;
	}
}
