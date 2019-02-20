package com.risk.game_engine.reinforcement;

import java.util.ArrayList;

import com.risk.model.Country;
import com.risk.model.Player;

public class ReinforcementImpl implements Reinforcement{

	public int getReinforcementArmies(Player player) {
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
		// Update the number of armies the player owns.
		player.setArmies(player.getArmies()+armies);
		return armies;
	}
	
	public boolean reinforceArmies(Player player,Country country) {
		ArrayList<Country> countries = player.getCountries();
		if(countries != null && countries.contains(country)) {
			int i = countries.indexOf(country);
			Country country1 = countries.get(i);
			country1.setArmies(country1.getArmies()+1);
			countries.remove(i);
			countries.add(country1);
			player.setCountries(countries);
			return true;
		}else {
		return false;
		}
	}
}
