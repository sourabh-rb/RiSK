package model;

import java.util.ArrayList;
import utilities.Utilities;

/**
 * This class represents the continents in the game. Each continent has a set of
 * countries that make up the continent. Each country should be a part of only
 * one continent. Each continent has a control value associated to it. This can
 * be same or different from the other continents. To conquer the continent the
 * player must capture all of the countries that make up the continent.
 * 
 * @author shivani
 * @version 1.0
 *
 */
public class Continent {
	// Name of the continent
	private String name;
	// Name of player that owns the continent
	private Player owner;
	// Control value associated with the continent
	private int controlValue;
	// List of all the countries that make up the continent
	private ArrayList<Country> countriesComprised = new ArrayList<Country>();

	/**
	 * This method gets the list of all the countries that are a part of the
	 * continent.
	 * 
	 * @return countriesComprised
	 */
	public ArrayList<Country> getCountriesComprised() {
		return countriesComprised;
	}

	/**
	 * This method sets list of the countries that are a part of the continent.
	 * 
	 * @param countriesComprised
	 */
	public void setCountriesComprised(ArrayList<Country> countriesComprised) {
		this.countriesComprised = countriesComprised;
	}

	/**
	 * This method gets the name of the continent.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method sets the name of the continent. Each continent has a unique name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method gets the name of the owner of the continent.
	 * 
	 * @return owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 * This method sets the value for owner as the name of the player that has
	 * conquered all of the countries in that continent. The value is null till all
	 * the countries in the continent are conquered by the same player.
	 * 
	 * @param owner
	 *            Name of the owner of the continent
	 */
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * This method sets the control value of the continent. Each continent has a
	 * control value that is used to fing the number of armies the conquerer of the
	 * continent receives. The values of all the continents may or may not be the
	 * same.
	 * 
	 * @return controlValue
	 */
	public int getControlValue() {
		return controlValue;
	}

	/**
	 * This method sets the control value of the continent. Each continent has a
	 * control value that is used to fing the number of armies the conquerer of the
	 * continent receives. The values of all the continents may or may not be the
	 * same.
	 * 
	 * @param controlValue
	 *            ranges from 2 to 10
	 */
	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
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
		Continent continent = (Continent) obj;

		// comparing the state of argument with
		// the state of 'this' Object.
		return (continent.name == this.name);
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
