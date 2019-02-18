package com.risk.model;


/**
 * This class represents the 3 types of cards that can be given to a player after each turn
 * or after capturing the last country of another player.
 * @author shivani
 * @version 1.0
 */
public class Card {
	
	//Value of type of card : Infantry, Cavalry, Artillery
	private String type;   

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
 * @param type Can be of 3 types: Infantry, Cavalry, Artillery
 */
public void setType(String type) {
	this.type = type;
}
}
