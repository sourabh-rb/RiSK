package model;

import utilities.Utilities;

/**
 * This class represents the 3 types of cards that can be given to a player
 * after each turn or after capturing the last country of another player.
 * 
 * @author shivani
 * @version 1.0
 */
public class Card {

	// Value of type of card : Infantry, Cavalry, Artillery
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
		final int prime = 31;
		int result = 1;
		Integer value = Utilities.getIntegerValue(this.type);
		result = prime * result + ((value == null) ? 0 : value);
		return result;
	}
}
