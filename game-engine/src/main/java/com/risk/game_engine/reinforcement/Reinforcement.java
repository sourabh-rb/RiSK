package com.risk.game_engine.reinforcement;

import com.risk.model.Country;
import com.risk.model.Player;

/**
 * This interface defines the methods that the modules will be using to interact with the 
 * reinforcement module
 * 
 * @author Shivani
 * @version 1.0.0
 *
 */

public interface Reinforcement {

	/**
	 * This method returns the number of armies that the player will get in the reinforcement phase of the game.
	 * The player gets (Number of countries owned/3) armies if he does not own any continent, otherwise he gets the 
	 * sum all control values of the continents owned by him.
	 * 
	 * @param player Contains all the details of the player.
	 * @return The number of armies that the player will get for reinforcement.
	 */
	public int getReinforcementArmies(Player player);
	
	/**
	 * This method is used to make changes in the number of armies in a country when the player is in the reinforcement stage
	 * 
	 * @param player Contains all the details of the player.
	 * @param country Contains all the details of the country that the player chooses to reinforce.
	 * @return returns true if the number of armies is successfully updated else false.
	 */
	public boolean reinforceArmies(Player player,Country country);
	
}
