package com.risk.model;

import java.util.ArrayList;

import com.risk.utility.Utilities;

/**
 * This Country represents each country in the game. These countries are owned
 * by at most 1 player at a time. Some countries are interlinked with other
 * countries, these are called neighbour countries. Each country can be occupied
 * by 'n' number of armies of the player that owns it.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Country {
	// Name of country
	private String name;
	// Name of player that owns the country
	private Player owner;
	// List of all the adjacent connected countries
	private ArrayList<Country> neighborCounties;
	// Number of armies that occupy the country
	private int armies;

	/**
	 * This method gets the name of the country.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of the country
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the name of the player that owns the country.
	 * 
	 * @return Owner Name of the player that owns the country
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * This method sets the name of the player as the owner of the country. The
	 * player becomes the owner if he has been alloted the country by the system in
	 * the initial stage or when he conquers the country.
	 * 
	 * @param owner
	 *            Name of the player that owns the country.
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * This method gives a list of neighboring countries.
	 * 
	 * @return neighborCountries List of countries.
	 */
	public ArrayList<Country> getNeighborCounties() {
		return neighborCounties;
	}

	/**
	 * This method sets the values of the countries that will be neighbors.
	 * 
	 * @param neighborCounties
	 *            List of countries.
	 */
	public void setNeighborCounties(ArrayList<Country> neighborCounties) {
		this.neighborCounties = neighborCounties;
	}

	/**
	 * This method returns the number of armies that are present in the country.
	 * 
	 * @return armies
	 */
	public int getArmies() {
		return armies;
	}

	/**
	 * This method sets the number of armies that the player wants to place in a
	 * country.
	 * 
	 * @param armies
	 */
	public void setArmies(int armies) {
		this.armies = armies;
	}

	@Override
	public boolean equals(Object obj) {

		// checking if both the object references to the same object.
		if (this == obj)
			return true;

		// it checks if the argument is of the
		// type country
		if (obj == null || obj.getClass() != this.getClass())
			return false;

		// type casting of the argument.
		Country country = (Country) obj;

		// comparing the state of argument with
		// the state of 'this' Object.
		return (country.name == this.name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		Integer value = Utilities.getIntegerValue(this.name);
		result = prime * result + ((value == null) ? 0 : value);
		return result;
	}
}
