package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;

import controller.initialization.StartUpPhase;
import utilities.Utilities;

/**
 * This class represents each player in the game. The players have a number of
 * armies with which they can participate in battles. Each player also has a set
 * of countries that are owned by him. The player can place these armies in any
 * f his owned countries. The players also get a card after each turn, these
 * cards can be exchanged for armies.
 * 
 * @author Shivani
 * @version 1.0.0
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
	private ArrayList<Country> countries = new ArrayList<Country>();
	// The continents that the player owns
	private ArrayList<Continent> continents;

	ArrayList<Country> europeCountryList = new ArrayList<Country>();
	static ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Continent> continentList = new ArrayList<Continent>();
	ArrayList<Country> countryListPlayer1 = new ArrayList<Country>();
	ArrayList<Country> countryListPlayer2 = new ArrayList<Country>();
	static Country country1;
	Country country5;

	public void set() {
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		player1.setName("Aravind");
		player2.setName("charan");
		player3.setName("shivani");
		player4.setName("Sourabh");

		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		// playerList.add(player4);

		Continent continent1 = new Continent();
		country1 = new Country();
		country1.setName("India");
		Country country2 = new Country();
		country2.setName("china");
		Country country3 = new Country();
		country3.setName("pakistan");
		Country country7 = new Country();
		country7.setName("japan");
		Country country8 = new Country();
		country8.setName("Iran");
		Country country9 = new Country();
		country9.setName("Iraq");

		Continent continent2 = new Continent();
		Country country4 = new Country();
		country4.setName("France");
		country5 = new Country();
		country5.setName("Germany");
		Country country6 = new Country();
		country6.setName("Sweden");

		ArrayList<Country> countryList = new ArrayList<Country>();
		countryList.add(country1);
		countryList.add(country2);
		countryList.add(country3);
		countryList.add(country7);
		countryList.add(country8);

		continent1.setCountriesComprised(countryList);

		europeCountryList.add(country4);
		europeCountryList.add(country5);
		europeCountryList.add(country6);
		continent2.setCountriesComprised(europeCountryList);

		continentList.add(continent1);
		continentList.add(continent2);

		countryListPlayer1.add(country1);
		countryListPlayer1.add(country2);
		countryListPlayer1.add(country3);
		countryListPlayer1.add(country4);
		country1.setArmies(4);

		countryListPlayer2.add(country5);
		countryListPlayer2.add(country6);
		country5.setArmies(5);
		player1.setCountries(countryListPlayer1);
		player2.setCountries(countryListPlayer2);

	}

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
	 * 
	 * @param cardType can be of 3 types only : Infantry, Cavalry, Artillery
	 */
	public void setCardType(ArrayList<Card> cardType) {
		this.cards = cardType;
	}

	/**
	 * This method gets all the names of the countries that the player owns.
	 * 
	 * @return a list of all the countries.
	 */
	public ArrayList<Country> getCountries() {
		return countries;
	}

	/**
	 * This method sets the value of all the countries that a player owns.
	 * 
	 * @param countries
	 */
	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}

	/**
	 * The method gets all the continents that are owned by the player.
	 * 
	 * @return a list of continents owned by the player.
	 */
	public ArrayList<Continent> getContinents() {
		return continents;
	}

	/**
	 * The method sets the value of the continents that the player owns.
	 * 
	 * @param continents
	 */
	public void setContinents(ArrayList<Continent> continents) {
		this.continents = continents;
	}

	/**
	 * This method gets the number of armies left for the player to deploy.
	 * 
	 * @return
	 */
	public int getNumberOfArmiesLeft() {
		return numberOfArmiesLeft;
	}

	/**
	 * This method sets the number of armies left forthe user to deploy.
	 * 
	 * @param numberOfArmiesLeft
	 */
	public void setNumberOfArmiesLeft(int numberOfArmiesLeft) {
		this.numberOfArmiesLeft = numberOfArmiesLeft;
	}

	@Override
	public boolean equals(Object obj) {

		// checking if both the object references refer to the same object.
		if (this == obj)
			return true;

		// it checks if the argument is of the
		// type player
		if (obj == null || obj.getClass() != this.getClass())
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

	public int randomDiceValue() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int randomDiceValue = random.nextInt(1, 7);
		return randomDiceValue;
	}
	public int decreaseOneArmy(Country defeatedCountry) {
		defeatedCountry.setArmies(defeatedCountry.getArmies() - 1);
		System.out.println("Number of armies left in "+defeatedCountry.getName()+" is "+defeatedCountry.getArmies());
		return defeatedCountry.getArmies();
	}

	ArrayList<Integer> attackingCountryDiceValues = new ArrayList<Integer>();
	ArrayList<Integer> defendingCountryDiceValues = new ArrayList<Integer>();

	public String winner(Country attackingCountry, Country defendingCountry, int noOfDiceForAttackingCountry,
			int noOFDiceForDefendingCountry, String action) {
		int noOfArmiesInDefeatedCountry = 0;
		for (int i = 0; i < noOfDiceForAttackingCountry; i++) {
			attackingCountryDiceValues.add(randomDiceValue());
		}
		Collections.sort(attackingCountryDiceValues, Collections.reverseOrder());
		System.out.println("Dice values for Attacking country in Descending order");
		for (int diceValue : attackingCountryDiceValues) {
			System.out.println(diceValue);
		}

		for (int i = 0; i < noOFDiceForDefendingCountry; i++) {
			defendingCountryDiceValues.add(randomDiceValue());
		}
		Collections.sort(defendingCountryDiceValues, Collections.reverseOrder());
		System.out.println("Dice values for Defending country in Descending order");
		for (int diceValue : defendingCountryDiceValues) {
			System.out.println(diceValue);
		}

		int name = noOfDiceForAttackingCountry < noOFDiceForDefendingCountry ? noOfDiceForAttackingCountry
				: noOFDiceForDefendingCountry;
		for (int i = 0; i < name; i++) {
			if (attackingCountryDiceValues.get(i) > defendingCountryDiceValues.get(i)) {
				noOfArmiesInDefeatedCountry = decreaseOneArmy(defendingCountry);
			}
			else if(attackingCountryDiceValues.get(i) == defendingCountryDiceValues.get(i)){
				noOfArmiesInDefeatedCountry = decreaseOneArmy(attackingCountry);
			}
			else {
			
				noOfArmiesInDefeatedCountry = decreaseOneArmy(attackingCountry);
			}
		}
		attackingCountryDiceValues.clear();
		defendingCountryDiceValues.clear();
		if (noOfArmiesInDefeatedCountry == 0) {
			return "defeated";
		} else if (action.equals("allOutWinner")) {
			if(attackingCountry.getArmies()!=1)
			return noOfDiceOnAllOut(attackingCountry, defendingCountry);
			else
				return "onlyOneArmy";
		}		
		else
			return "notDefeated";

	}

	

	public String noOfDiceOnAllOut(Country attackingCountry, Country defendingCountry) {
		int attackingArmies = attackingCountry.getArmies();
		int defendingArmies = defendingCountry.getArmies();
		int noOfDiceForAttacker = 0;
		int noOfDiceForDefender;
		if (attackingArmies != 1) {
			if (attackingArmies > 3) {
				noOfDiceForAttacker = 3;
			} else {
				noOfDiceForAttacker = attackingArmies - 1;
			}
		} 

		if (defendingArmies == 1)
			noOfDiceForDefender = 1;
		else
			noOfDiceForDefender = 2;

		String allOutAttack = winner(attackingCountry, defendingCountry, noOfDiceForAttacker, noOfDiceForDefender, "allOutWinner");
		return allOutAttack;
	}
	
	public void attack(Country attackingCountry, Country defendingCountry, int noOfDiceForAttackingCountry,
			int noOFDiceForDefendingCountry, String action) {
		int noOfSuccesfullAttacks = 0;
		
		String attackRes = null;
		if(attackingCountry.getArmies()>=2) {
		if (action.equals("allOutWinner")) {
			attackRes = noOfDiceOnAllOut(attackingCountry, defendingCountry);
		}
		else if (action.equals("attack")) {
			
			attackRes = winner(attackingCountry, defendingCountry, noOfDiceForAttackingCountry,
					noOFDiceForDefendingCountry, "attack");
		}
		if (attackRes.equals("defeated") ) {
			// can give this player a card
			// ASk player if wants to continue attacking
			//assign defeated country to the attacker
			System.out.println("Defeated the country");
			//If player do not want to continue, he must fortify
		} else if (attackRes.equals("notDefeated")) {
			// ASk player if wants to continue attacking
			System.out.println("not Defeated the country yet");
		}
		else if(attackRes.equals("onlyOneArmy")) {
			System.out.println("Only one army left in the attacking country So, cannot attack");
		}
		}
		else
			System.out.println("cannot attack because there should be atleast 2 armies in the attacking country");

	}

	public String maxNoOfDice(Country attackingCountry, Country defendingCountry) {
		int maxNoOfDiceForAttacking = 0;
		int maxNoOfDiceForDefending = 0;
		if (attackingCountry.getArmies() > 1) {
			if (attackingCountry.getArmies() > 3) {
				maxNoOfDiceForAttacking = 3;
			} else {
				maxNoOfDiceForAttacking = attackingCountry.getArmies() - 1;
			}
		}
		if(defendingCountry.getArmies()>1) {
			maxNoOfDiceForDefending=2;
		}
		else 
			maxNoOfDiceForDefending=1;
		return Integer.toString(maxNoOfDiceForAttacking)+" "+Integer.toString(maxNoOfDiceForDefending);

	}

	public static void main(String[] args) {
		Player play = new Player();
		play.set();
		StartUpPhase start = new StartUpPhase();
		System.out.println("Attacking country armies: "+playerList.get(0).getCountries().get(0).getName() +" "+ playerList.get(0).getCountries().get(0).getArmies());
		System.out.println("Defending country armies: "+playerList.get(1).getCountries().get(0).getName()+" " + playerList.get(1).getCountries().get(0).getArmies());
		play.attack(playerList.get(0).getCountries().get(0), playerList.get(1).getCountries().get(0),3,2,"attack");
	}
}
