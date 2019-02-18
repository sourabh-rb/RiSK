package com.risk.model;

import java.util.ArrayList;

/**
 * This class represents each player in the game. The players have a number of armies with which they can
 * participate in battles. Each player also has a set of countries that are owned by him.
 * The player can place these armies in any f his owned countries.
 * The players also get a card after each turn, these cards can be exchanged for armies.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Player {
//Name of the player
private String name; 	
//Number of armies owned by the player
private int armies; 		
//Number of countries owned by the player
private int countryCount;   
//Number of cards owned by the player
private int cardExchangeCount;	
//Types of cards owned by the player
private ArrayList<Card> cards;  

/**
 * This method gets the name of the player.
 * 
 * @return name
 */
public String getName() {
	return name;
}
/**
 * This method sets the name of the player.
 * 
 * @param name
 */
public void setName(String name) {
	this.name = name;
}
/**
 * This method gets the total number of countries that the player owns.
 * 
 * @return countryCount
 */
public int getCountryCount() {
	return countryCount;
}
/**
 * This method sets the value of the total number of countries owned by the player.
 * 
 * @param countryCount
 */
public void setCountryCount(int countryCount) {
	this.countryCount = countryCount;
}
/**
 * This method gets the number of armies that a player owns.
 * 
 * @return armies
 */
public int getArmies() {
	return armies;
}
/**
 * This method sets the value for the number of armies that are owned by the player.
 * 
 * @param armies
 */
public void setArmies(int armies) {
	this.armies = armies;
}
/**
 * This method gives the count for the number of cards that a player has.
 * The player gets a card after each turn.
 * When a player captures the last country of another player, the winning player gets all the cards of the
 * defeated player. 
 * @return
 */
public int getCardExchangeCount() {
	return cardExchangeCount;
}
/**
 * This method gives the count for the number of cards that a player has.
 * The player gets a card after each turn.
 * When a player captures the last country of another player, the winning player gets all the cards of the
 * defeated player.
 * @param cardCount cannot be more than 5
 */
public void setCardExchangeCount(int cardCount) {
	this.cardExchangeCount = cardCount;
}
/**
 * This method lists all the card type that the player owns.
 * 
 * @return cardType
 */
public ArrayList<Card> getCardType() {
	return cards;
}
/**
 * This method sets the card type for each of the cards owned by the player.
 * @param cardType can be of 3 types only : Infantry, Cavalry, Artillery
 */
public void setCardType(ArrayList<Card> cardType) {
	this.cards = cardType;
}
}
