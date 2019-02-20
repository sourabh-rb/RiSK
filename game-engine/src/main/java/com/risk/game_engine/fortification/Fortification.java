package com.risk.game_engine.fortification;

import com.risk.model.Country;
import com.risk.model.Player;

public interface Fortification {
	
	/**
	 * This method is used to perform the fortification stage in the game. In this stage the user can move an army from one
	 * country that he owns to another if they are connected.
	 *   
	 * @param player Contains the details of the player.
	 * @param fromcountry The country from which the army will be moved.
	 * @param tocountry Thecountry to which the army will be moved.
	 * @return true if the fortification was successful, else false.
	 */
	public boolean fortifyArmies(Player player, Country fromcountry,Country tocountry);

}
