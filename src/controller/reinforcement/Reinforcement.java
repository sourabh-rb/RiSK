package controller.reinforcement;

import java.util.ArrayList;

import constants.LogLevel;
import model.Country;
import model.Player;
import utilities.Utilities;

/**
 * This class represents the reinforcement phase of the game. It contains methods that will be required by the 
 * player to perform necessary reinforcement actions.
 * 
 * @author Shivani
 * @version 1.0.0
 * 
 */
public class Reinforcement{

	/**
	 * This method returns the number of armies that the player will get in the reinforcement phase of the game.
	 * The player gets (Number of countries owned/3) armies if he does not own any continent, otherwise he gets the 
	 * sum all control values of the continents owned by him.
	 * 
	 * @param player Contains all the details of the player.
	 * @return The number of armies that the player will get for reinforcement.
	 */
	public int getReinforcementArmies(Player player) {
		// Number of armies to be given to the player for reinforcement
		int armies=0;
		// The control value associated with the continents owned by the player
		int controlvalue=0;
		
		// If the player owns continents then the number of armies 
		//given to him is the sum of the control values
		if(player.getContinents()!=null && player.getContinents().size()!=0) {
			for(int i=0;i<player.getContinents().size();i++) {
			controlvalue=player.getContinents().get(i).getControlValue();
			armies=armies+controlvalue;
			}
		}else {
			armies = player.getCountries().size()/3;
		}
		// Update the number of armies the player owns.
		player.setArmies(player.getArmies()+armies);
		Utilities.gameLog("Player: "+player.getName()+" || Stage: Reinforcement Armies || Number of armies given: "+armies, LogLevel.INFO);
		return armies;
	}
	
	/**
	 * This method is used to make changes in the number of armies in a country when the player is in the reinforcement stage
	 * 
	 * @param player Contains all the details of the player.
	 * @param country Contains all the details of the country that the player chooses to reinforce.
	 * @return returns true if the number of armies is successfully updated else false.
	 */
	public boolean reinforceArmies(Player player,Country country) {
		Utilities.gameLog("Player: "+player.getName()+"|| Stage: Reinforcement || Country reinforced: "+ country.getName(),LogLevel.INFO);
		ArrayList<Country> countries = player.getCountries();
		if(countries != null && countries.contains(country)) {
			int i = countries.indexOf(country);
			Country country1 = countries.get(i);
			country1.setArmies(country1.getArmies()+1);
			countries.remove(i);
			countries.add(country1);
			player.setCountries(countries);
			Utilities.gameLog("Player: "+player.getName()+"|| Country reinforced!!",LogLevel.INFO);
			return true;
		}else {
			Utilities.gameLog("Player: "+player.getName()+"|| Country could not be reinforced!!",LogLevel.WARN);
		return false;
		}
	}
}
