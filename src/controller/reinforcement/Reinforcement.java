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
		int controlValue = 0;
		// If the player owns continents then the number of armies
		// given to him is the sum of the control values
		try {
			if (player.getContinents() != null && player.getContinents().size() != 0) {
				for (int i = 0; i < player.getContinents().size(); i++) {
					controlValue = player.getContinents().get(i).getControlValue();
					armies = armies + controlValue;
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
		
		boolean artilleryFlag=false;
		boolean infantryFlag=false;
		boolean cavalryFlag=false;
		int count=0;
		boolean result=false;
		ArrayList<Card> playerCards=null;
		int[] cardCount=new int[3];
		int indexes[]= new int[5];		
		
		cardCount=Utilities.cardCount(player);
		//Check if player has any cards
		if(cardCount!=null) {
			playerCards=player.getCardType();
		}else {
			Utilities.gameLog("Player: " + player.getName()
			+ " || Stage: Reinforcement Armies || Player has no cards to exchange!! ", LogLevel.ERROR);
			return 0;
		}
		//Check if player has 3 cards of same type and exchange them.
		if(cardCount[0]==3) {
			for(int i=0,j=0; i<playerCards.size();i++) {
				if(Constants.ARTILLERY.equals(playerCards.get(i).getType())){
					indexes[j]=i;
					j++;
				}
			}
			for(int i=2;i>=0;i--){
				playerCards.remove(indexes[i]);
			}
			result= true;
		}else if(cardCount[1]==3) {
			for(int i=0,j=0; i<playerCards.size();i++) {
				if(Constants.INFANTRY.equals(playerCards.get(i).getType())){
					indexes[j]=i;
					j++;
				}
			}
			for(int i=2;i>=0;i--){
				playerCards.remove(indexes[i]);
			}
			result= true;
		}else if(cardCount[2]==3) {
			for(int i=0,j=0; i<playerCards.size();i++) {
				if(Constants.CAVALRY.equals(playerCards.get(i).getType())){
					indexes[j]=i;
					j++;
				}
			}
			for(int i=2;i>=0;i--){
				playerCards.remove(indexes[i]);
			}
			result= true;
		}// Check if the player has 3 cards of different types and exchange them.
		else if(cardCount[0]>0 && cardCount[1]>0 && cardCount[2]>0) {
			for(int i=0,j=0; i<playerCards.size();i++) {
				if(Constants.ARTILLERY.equals(playerCards.get(i).getType()) && artilleryFlag==false){
					indexes[j]=i;
					artilleryFlag=true;
					j++;
				}else if(Constants.INFANTRY.equals(playerCards.get(i).getType()) && infantryFlag==false) {
					indexes[j]=i;
					infantryFlag=true;
					j++;
				}else if(Constants.INFANTRY.equals(playerCards.get(i).getType()) && cavalryFlag==false) {
					indexes[j]=i;
					cavalryFlag=true;
					j++;
				}
				if(artilleryFlag==true && infantryFlag==true && cavalryFlag==true) {
					break;
				}
			}
			for(int i=2;i>=0;i--){
				playerCards.remove(indexes[i]);
			}
			result=true;
		}else {
			Utilities.gameLog("Player: " + player.getName()
			+ " || Stage: Reinforcement Armies || Cards cannot be exchanged!! ", LogLevel.WARN);
			return 0;
		}
		
		if(result==true){
			int armies= (player.getCardExchangeCount()+1)*5;
			player.setCardExchangeCount(player.getCardExchangeCount()+1);
			//Update the player with the list after the cards are exchanged.
			player.setCardType(playerCards);
			// Update the number of armies the player owns.
			player.setArmies(player.getArmies() + armies);
			player.setNumberOfArmiesLeft(armies);
			Utilities.gameLog("Player: " + player.getName()
			+ " || Stage: Reinforcement Armies || Number of armies given: " + armies, LogLevel.INFO);
			return armies;
		}else {
			Utilities.gameLog("Player: " + player.getName()
			+ " || Stage: Reinforcement Armies || Cards cannot be exchanged!!", LogLevel.WARN);
			return 0;
		}
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
