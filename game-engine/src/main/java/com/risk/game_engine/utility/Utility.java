package com.risk.game_engine.utility;

import com.risk.model.Player;

/**
 * This class contains all the utilities that the game engine will be using for the 
 * @author Shivani
 *
 */
public class Utility {
	
	public static int getReinforcementArmies(Player player) {
		// Number of armies to be given to the player for reinforcement
		int armies=0;
		// The control value associated with the continents owned by the player
		int controlvalue=0;
		
		// If the player owns continents then the number of armies 
		//given to him is the sum of the control values
		if(player.getContinents()!=null) {
			for(int i=0;i<player.getContinents().size();i++) {
			controlvalue=player.getContinents().get(i).getControlValue();
			armies=armies+controlvalue;
			}
		}else {
			armies = player.getCountries().size()/3;
		}
		return armies;
	}
}
