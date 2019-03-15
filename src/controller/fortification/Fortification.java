package controller.fortification;

import java.util.ArrayList;

import constants.LogLevel;
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
	public static boolean fortifyArmies(Player player, Country fromCountry,Country toCountry, int armies) {
		
		Utilities.gameLog("Player: "+player.getName()+"|| Stage: Fortification || Countries involved: "+fromCountry.getName()+","+toCountry.getName(),LogLevel.INFO);
		ArrayList<Country> playercountries = player.getCountries();
		if(playercountries != null && playercountries.contains(fromCountry) && playercountries.contains(toCountry) && armies>0) {
			//Update the number for armies in the fortifying country.
			int i = playercountries.indexOf(fromCountry);
			Country country1 = playercountries.get(i);
			country1.setArmies(country1.getArmies()-armies);
			playercountries.remove(i);
			playercountries.add(country1);
			//Update the number for armies in the fortified country.
			int j = playercountries.indexOf(toCountry);
			Country country2 = playercountries.get(j);
			country2.setArmies(country2.getArmies()+armies);
			playercountries.remove(j);
			playercountries.add(country2);
			player.setCountries(playercountries);
			Utilities.gameLog("Player: "+player.getName()+"|| Countries fortified!! || "
					+ country1.getName() +" : "+country1.getArmies()+" || "+ country2.getName() +" : "+country2.getArmies(),LogLevel.INFO);
			return true;
		}
		Utilities.gameLog("Player: "+player.getName()+"|| Countries could not be fortified",LogLevel.WARN);
		return false;
	}
}
