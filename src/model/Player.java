package model;

import java.util.ArrayList;
import java.util.Objects;

import utilities.Utilities;


/**
 * This class represents each player in the game. The players have a number of
 * armies with which they can participate in battles. Each player also has a set
 * of countries that are owned by him. The player can place these armies in any
 * f his owned countries. The players also get a card after each turn, these
 * cards can be exchanged for armies.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Player {
	// Name of the player
	private String name;
	// Number of armies owned by the player
	private int armies;
	private int numberOfArmiesLeft;
	// Number of cards owned by the player
	private int cardExchangeCount;
	// Types of cards owned by the player
	private ArrayList<Card> cards;
	// The countries that the player owns
	private ArrayList<Country> countries =new ArrayList<Country>();
	// The continents that the player owns
	private ArrayList<Continent> continents;
	

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
	 * This method gets the number of armies that a player owns.
	 * 
	 * @return armies
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * This method sets the value for the number of armies that are owned by the
	 * player.
	 * 
	 * @param armies
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}

	/**
	 * This method gives the count for the number of cards that a player has. The
	 * player gets a card after each turn. When a player captures the last country
	 * of another player, the winning player gets all the cards of the defeated
	 * player.
	 * 
	 * @return
	 */
	public int getCardExchangeCount() {
		return cardExchangeCount;
	}

	/**
	 * This method gives the count for the number of cards that a player has. The
	 * player gets a card after each turn. When a player captures the last country
	 * of another player, the winning player gets all the cards of the defeated
	 * player.
	 * 
	 * @param cardCount
	 *            cannot be more than 5
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
	 * 
	 * @param cardType
	 *            can be of 3 types only : Infantry, Cavalry, Artillery
	 */
	public void setCardType(ArrayList<Card> cardType) {
		this.cards = cardType;
	}

	/**
	 * This method gets all the names of the countries that the player owns.
	 * @return a list of all the countries.
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * This method sets the value of all the countries that a player owns.
	 * @param countries
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 *The method gets all the continents that are owned by the player.
	 * @return a list of continents owned by the player.
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * The method sets the value of the continents that the player owns.
	 * @param continents
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}
	
	/**
	 * This method gets the number of armies left for the player to deploy.
	 * @return
	 */
	public int getNumberOfArmiesLeft() {
		return numberOfArmiesLeft;
	}

	/**
	 * This method sets the number of armies left forthe user to deploy.
	 * @param numberOfArmiesLeft
	 */
	public void setNumberOfArmiesLeft(int numberOfArmiesLeft) {
		this.numberOfArmiesLeft = numberOfArmiesLeft;
	}
		
	@Override
	public boolean equals(Object obj) 
	{ 
	          
	 // checking if both the object references refer to the same object. 
	    if(this == obj) 
	            return true; 
	          
	        // it checks if the argument is of the  
	        // type player
	        if(obj == null || obj.getClass()!= this.getClass()) 
	            return false; 
	          
	        // type casting of the argument.  
	        Player player = (Player) obj; 
	          
	        // comparing the state of argument with  
	        // the state of 'this' Object. 
	        return (player.name == this.name); 
	    }
	
	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}
}
