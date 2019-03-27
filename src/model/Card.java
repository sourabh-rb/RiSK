package model;

import java.util.ArrayList;
import java.util.Objects;

import constants.Constants;
import constants.LogLevel;
import utilities.Utilities;

/**
 * This class represents the 3 types of cards that can be given to a player
 * after each turn or after capturing the last country of another player.
 * 
 * @author shivani
 * @version 1.0.0
 */
public class Card {

	// Value of type of card : Infantry, Cavalry, Artillery
	private String type;
	
	
	public Card()
	{
		type = null;
	}

	/**
	 * This method gets the type of card that the player will receive.
	 * 
	 * @return type
	 */
	public String getType() {
		return type;
	}

	/**
	 * This method defines the types of cards a player can own.
	 * 
	 * @param type
	 *            Can be of 3 types: Infantry, Cavalry, Artillery
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public boolean equals(Object obj) 
	{ 
	          
	 // checking if both the object references refer to the same object. 
	    if(this == obj) 
	            return true; 
	          
	        // it checks if the argument is of the  
	        // type country
	        if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
	          
	        // type casting of the argument.  
	        Card card = (Card) obj; 
	          
	        // comparing the state of argument with  
	        // the state of 'this' Object. 
	        return (card.type == this.type); 
	    }
	
	@Override
	public int hashCode() {
		return Objects.hash(this.type);
	}
	
//	/**
//	 * This method gives the count of each type of card that the player has.
//	 * 
//	 * @param player
//	 * @return Array of integer type containing the number of cards of each type in
//	 *         the order artillery, infantry, cavalry.
//	 */
//	public ArrayList<Integer> cardCount(Player player) {
//
//		int artilleryCount = 0;
//		int cavalryCount = 0;
//		int infantryCount = 0;
//		ArrayList<Integer> cardCount = new ArrayList<Integer>();
////		cardCount.add(1);
////		cardCount.add(1);
////		cardCount.add(3);
//		
//		ArrayList<Card> playerCards = null;
////		ArrayList<Card> testList = new ArrayList();
////        Card testCard1=new Card();
////        Card testCard2=new Card();
////        Card testCard3=new Card();
////        testCard1.setType(Constants.ARTILLERY);
////        testCard2.setType(Constants.CAVALRY);
////        testCard3.setType(Constants.INFANTRY);
////        testList.add(testCard1);
////        testList.add(testCard1);
////        testList.add(testCard1);
////        testList.add(testCard2);
////        testList.add(testCard3);
////        player.setCardType(testList);
//		if (player.getCardType() != null && player.getCardType().size() != 0) {
//			playerCards = player.getCardType();
//		} else {
//			Utilities.gameLog("Player: " + player.getName() + " || Stage: Card count check  || Player has no cards!! ",
//					LogLevel.ERROR);
//			return cardCount;
//		}
//		// Count what type of cards does the player have
//		for (Card card : playerCards) {
//			if (card.getType().equals(Constants.ARTILLERY)) {
//				artilleryCount++;
//			} else if (card.getType().equals(Constants.INFANTRY)) {
//				infantryCount++;
//			} else if (card.getType().equals(Constants.CAVALRY)) {
//				cavalryCount++;
//			}
//		}
//		cardCount.set(0, artilleryCount);
//		cardCount.set(1,infantryCount);
//		cardCount.set(2, cavalryCount);
//
//		return cardCount;
//
//	}
}
