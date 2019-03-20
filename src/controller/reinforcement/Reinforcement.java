package controller.reinforcement;

import java.util.ArrayList;

import constants.Constants;
import constants.LogLevel;
import model.Country;
import model.Player;
import model.Card;
import utilities.Utilities;

/**
 * This class represents the reinforcement phase of the game. It contains
 * methods that will be required by the player to perform necessary
 * reinforcement actions.
 * 
 * @author Shivani
 * @version 1.0.0
 */
public class Reinforcement {

	/**
	 * This method returns the number of armies that the player will get in the
	 * reinforcement phase of the game. The player gets (Number of countries
	 * owned/3) armies if he does not own any continent, otherwise he gets the sum
	 * all control values of the continents owned by him.
	 * 
	 * @param player
	 *            Contains all the details of the player.
	 * @return The number of armies that the player will get for reinforcement.
	 */
	public boolean getReinforcementArmies(Player player) {
		// Number of armies to be given to the player for reinforcement
		int armies = 0;
		// The control value associated with the continents owned by the player
		int controlvalue = 0;
		// If the player owns continents then the number of armies
		// given to him is the sum of the control values
		try {
			if (player.getContinents() != null && player.getContinents().size() != 0) {
				for (int i = 0; i < player.getContinents().size(); i++) {
					controlvalue = player.getContinents().get(i).getControlValue();
					armies = armies + controlvalue;
				}
			} else {
				armies = player.getCountries().size() / 3;
			}
			// Update the number of armies the player owns.
			player.setArmies(player.getArmies() + armies);
			player.setNumberOfArmiesLeft(armies);
			Utilities.gameLog("Player: " + player.getName()
					+ " || Stage: Reinforcement Armies || Number of armies given: " + armies, LogLevel.INFO);
			return true;
		} catch (Exception e) {
			Utilities.gameLog("Player: " + player.getName()
					+ " || Stage: Reinforcement Armies || Cannot give armies to reinforce!! ", LogLevel.ERROR);
			return false;
		}
	}

	/**
	 * This method determines the number of armies a player gets when he exchanges
	 * his cards.
	 * 
	 * @param player
	 * @return
	 */
	public int armiesFromCardExchange(Player player) {
		int artilleryCount=0;
		int cavalryCount=0;
		int infantryCount=0;
		int count=0;
		boolean result=false;
		ArrayList<Card> playerCards=null;
		
		//Check if player has any cards
		if(player.getCardType()!=null) {
			playerCards=player.getCardType();
		}else {
			return 0;
		}
		//Count what type of cards does the player have
		for (Card card : playerCards) {
			if (card.getType().equals(Constants.ARTILLERY)) {
				artilleryCount++;
			}else if(card.getType().equals(Constants.INFANTRY)) {
				infantryCount++;
			}else if(card.getType().equals(Constants.CAVALRY)) {
				cavalryCount++;
			}
		}
		//Check if player has 3 cards of same type and exchange them.
		if(artilleryCount==3) {
			for(Card cardToRemove : player.getCardType()) {
				if (cardToRemove.getType().equals(Constants.ARTILLERY)) {
					playerCards.remove(cardToRemove);
				}
			}
			result= true;
		}else if(infantryCount==3) {
			for(Card cardToRemove : player.getCardType()) {
				if (cardToRemove.getType().equals(Constants.INFANTRY)) {
					playerCards.remove(cardToRemove);
				}
			}
			result= true;
		}else if(cavalryCount==3) {
			for(Card cardToRemove : player.getCardType()) {
				if (cardToRemove.getType().equals(Constants.CAVALRY)) {
					playerCards.remove(cardToRemove);
				}
			}
			result= true;
		}// Check if the player has 3 cards of different types and exchange them.
		else if(artilleryCount>0 && infantryCount>0 && cavalryCount>0) {
			for(Card cardToRemove : player.getCardType()) {
				if (cardToRemove.getType().equals(Constants.CAVALRY)) {
					playerCards.remove(cardToRemove);
					count++;
				}else if(cardToRemove.getType().equals(Constants.ARTILLERY)) {
					playerCards.remove(cardToRemove);
					count++;
				}else if(cardToRemove.getType().equals(Constants.CAVALRY)) {
					playerCards.remove(cardToRemove);
					count++;
				}
				if(count==3) {
					result= true;
					break;
				}
			}
		}else {
			return 0;
		}
		//Update the player with the list after the cards are exchanged.
		player.setCardType(playerCards);
		
		if(result==true){
			int armies= (player.getCardExchangeCount()+1)*5;
			player.setCardExchangeCount(player.getCardExchangeCount()+1);
			return armies;
		}
		return 0;
	}

	/**
	 * This method is used to make changes in the number of armies in a country when
	 * the player is in the reinforcement stage.
	 * 
	 * @param player
	 *            Contains all the details of the player.
	 * @param country
	 *            Contains all the details of the country that the player chooses to
	 *            reinforce.
	 * @return returns true if the number of armies is successfully updated else
	 *         false.
	 */

	public boolean reinforceArmies(Player player, Country country, int armies) {
		Utilities.gameLog(
				"Player: " + player.getName() + "|| Stage: Reinforcement || Country reinforced: " + country.getName(),
				LogLevel.INFO);

		ArrayList<Country> countries = player.getCountries();
		if (countries != null && countries.contains(country)) {
			int i = countries.indexOf(country);
			Country country1 = countries.get(i);
			country1.setArmies(country1.getArmies() + armies);
			countries.remove(i);
			countries.add(country1);
			player.setCountries(countries);
			player.setNumberOfArmiesLeft(player.getNumberOfArmiesLeft() - armies);
			Utilities.gameLog("Player: " + player.getName() + "|| Country reinforced!!", LogLevel.INFO);
			return true;
		} else {
			Utilities.gameLog("Player: " + player.getName() + "|| Country could not be reinforced!!", LogLevel.WARN);
			return false;
		}
	}
}
