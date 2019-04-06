
package gameEngine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;


import constants.Constants;
import constants.LogLevel;

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

	private ArrayList<Card> cards = new ArrayList<Card>();

	// The countries that the player owns
	private ArrayList<Country> countries = new ArrayList<Country>();
	// The continents that the player owns
	private ArrayList<Continent> continents;

	


	ArrayList<Card> cardTypeList = new ArrayList<Card>();
	ArrayList<Country> lostCountries = new ArrayList<Country>();
	
	ArrayList<Country> europeCountryList = new ArrayList<Country>();
	static ArrayList<Player> playerList = new ArrayList<Player>();
	ArrayList<Continent> continentList = new ArrayList<Continent>();
	ArrayList<Country> countryListPlayer1 = new ArrayList<Country>();
	ArrayList<Country> countryListPlayer2 = new ArrayList<Country>();
	
	static Country country1;
	static Country country5;

	StartUpPhase start=StartUpPhase.getInstance();
	


	

	public Player()
	{
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

	ArrayList<Integer> attackingCountryDiceValues = new ArrayList<Integer>();
	ArrayList<Integer> defendingCountryDiceValues = new ArrayList<Integer>();

	/**
	 * This function gives random dice value for each throw
	 * 
	 * @return random dice value
	 */
	public int randomDiceValue() {
		ThreadLocalRandom random = ThreadLocalRandom.current();
		int randomDiceValue = random.nextInt(1, 7);
		return randomDiceValue;
	}

	/**
	 * This function decreases one army from the defeated country every time either
	 * attacking country or defending country looses
	 * 
	 * @param defeatedCountry after attacking defeated country object is received
	 * @return armies left after decreasing
	 */
	public int decreaseOneArmy(Country defeatedCountry) {
		defeatedCountry.setArmies(defeatedCountry.getArmies() - 1);
		System.out.println(
				"Number of armies left in " + defeatedCountry.getName() + " is " + defeatedCountry.getArmies());
		return defeatedCountry.getArmies();
	}

	/**
	 * This function decides whether the attacking country or defending country wins
	 * 
	 * @param attackingCountry            attacking country object
	 * @param defendingCountry            defending country object
	 * @param noOfDiceForAttackingCountry number of dice attacker wishes to throw
	 * @param noOFDiceForDefendingCountry number of dice defender wants to throw
	 * @param action                      this parameter decides whether to attack
	 *                                    once or attack until it is possible
	 * @return conclusion after attack
	 */
	public String winner(Country attackingCountry, Country defendingCountry, int noOfDiceForAttackingCountry,
			int noOFDiceForDefendingCountry, String action) {

		int noOfArmiesInDefeatedCountry = 0;
		
		//int noOfArmiesInAttackingCountry = 0;

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
				System.out.println("Armies left in defending country after atatck: "+noOfArmiesInDefeatedCountry);
			} else if (attackingCountryDiceValues.get(i) == defendingCountryDiceValues.get(i)) {
				noOfArmiesInDefeatedCountry = decreaseOneArmy(attackingCountry);
			} else {
				noOfArmiesInDefeatedCountry = decreaseOneArmy(attackingCountry);

			}
		}
		attackingCountryDiceValues.clear();
		defendingCountryDiceValues.clear();

		if (noOfArmiesInDefeatedCountry == 0) {
			return "defeated";
		} else if (action.equals("allOutWinner")) {
			if (attackingCountry.getArmies() != 1)

				return noOfDiceOnAllOut(attackingCountry, defendingCountry);
			else
				return "onlyOneArmy";
		} else
			return "notDefeated";
	}

	/**
	 * This function calculates number of maximum dice each gets in allout mode and
	 * then calls winner function to determine the winner
	 * 
	 * @param attackingCountry attacking country object
	 * @param defendingCountry defending country object
	 * @return conclusion on the winner result
	 */
	public String noOfDiceOnAllOut(Country attackingCountry, Country defendingCountry) {

		int maxNoOfDiceForAttacker = 0;
		int maxNoOfDiceForDefender = 0;
		String[] maxDiceForEach = maxNoOfDice(attackingCountry, defendingCountry).split(" ");
		maxNoOfDiceForAttacker = Integer.parseInt(maxDiceForEach[0]);
		maxNoOfDiceForDefender = Integer.parseInt(maxDiceForEach[1]);
		System.out
				.println("maxAttackerDice: " + maxNoOfDiceForAttacker + " maxDefenderDice: " + maxNoOfDiceForDefender);
		String allOutAttack = winner(attackingCountry, defendingCountry, maxNoOfDiceForAttacker, maxNoOfDiceForDefender,
				"allOutWinner");
		return allOutAttack;
	}

	/**
	 * This function takes care of attack phase of the game and it has all the
	 * constraints and criteria
	 * 
	 * @param attackingCountry            attacking country object from which user
	 *                                    wants to attack
	 * @param defendingCountrydefending   country object for which user wants to
	 *                                    attack
	 * @param noOfDiceForAttackingCountry number of dice to be used for attacking
	 *                                    and this is specified by the user
	 * @param noOFDiceForDefendingCountry number of dice to be used for defending
	 *                                    and usually it will be maximum number of
	 *                                    dice the defender can have at that point
	 *                                    of time
	 * @param action                      action specifies whether to perform all
	 *                                    out operation or just one time attack
	 */
	public String attack(Country attackingCountry, Country defendingCountry, int noOfDiceForAttackingCountry,
			int noOFDiceForDefendingCountry, String action) {
		int noOfSuccesfullAttacks = 0;
		System.out.println("Inside attack method");
		String attackRes = null;
		System.out.println("Number of countries attacker has before attacking: "+attackingCountry.getOwner().getCountries().size());
		if (attackingCountry.getArmies() >= 2) {
			if (action.equals("allOutWinner")) {
				attackRes = noOfDiceOnAllOut(attackingCountry, defendingCountry);
			} else if (action.equals("attack")) {

				attackRes = winner(attackingCountry, defendingCountry, noOfDiceForAttackingCountry,
						noOFDiceForDefendingCountry, "attack");
			}

			System.out.println("Result of attacking : "+attackRes);
			if (attackRes.equals("defeated")) {
				defendingCountry.setOwner(attackingCountry.getOwner());// change the defeated country owner to attacked country owner
				attackingCountry.getOwner().getCountries().add(defendingCountry); //assign the defeated country to winner
				System.out.println("Number of countries attacker has after attacking: "+attackingCountry.getOwner().getCountries().size());
				System.out.println("Number of total countries in the game: "+start.getCountryList().size());
				if(attackingCountry.getOwner().getCountries().size() == start.getCountryList().size()) {
					return "champion";
				}
				// can give this player a card
			 cardTypeList = attackingCountry.getOwner().getCardType();

				cardTypeList.add(Utilities.giveCard());
				attackingCountry.getOwner().setCardType(cardTypeList);
				cardTypeList.clear();
				
				// ASk player if wants to continue attacking
				
				System.out.println("Defeated the country");
				return "defeated";
				// If player do not want to continue, he must fortify
			} else if (attackRes.equals("notDefeated")) {
				// ASk player if wants to continue attacking
				System.out.println("not Defeated the country yet");
				return "notdefeated";
			} else if (attackRes.equals("onlyOneArmy")) {
				System.out.println("Only one army left in the attacking country So, cannot attack");
				// ASk player if wants to continue attacking with another country
				return "onlyOneArmy";
			}
		} else
			System.out.println("cannot attack because there should be atleast 2 armies in the attacking country");
		// ASk player if wants to continue attacking with another country
		return "cannotAttack";

	}

	/**
	 * This function gives maximum number of dice for particular attacking or
	 * defending country
	 * 
	 * @param attackingCountry attacking country object is sent from the user
	 * @param defendingCountry defending country object is sent from the user
	 * @return provides max number of dice for both and are been appended as a
	 *         string
	 */
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
		if (defendingCountry.getArmies() > 1) {
			maxNoOfDiceForDefending = 2;
		} else
			maxNoOfDiceForDefending = 1;
		return Integer.toString(maxNoOfDiceForAttacking)+" "+Integer.toString(maxNoOfDiceForDefending);//Concating 2 dice values
	}
	
	/**
	 * This function gives maximum number of dice for particular attacking  country
	 * 
	 * @param attackingCountry attacking country object is sent from the user
	 * 
	 * @return provides max number of dice for attacker
	 */
	public int maxNoOfDiceForAttack(Country attackingCountry) {
		int maxNoOfDiceForAttacking = 0;
		
		if (attackingCountry.getArmies() > 1) {
			if (attackingCountry.getArmies() > 3) {
				maxNoOfDiceForAttacking = 3;
			} else {
				maxNoOfDiceForAttacking = attackingCountry.getArmies() - 1;
			}
		}
		return maxNoOfDiceForAttacking;
	}
	
	/**
	 * This function gives maximum number of dice for particular
	 * defending country
	 * 
	 * @param defendingCountry defending country object is sent from the user
	 * @return provides max number of dice for defender
	 */
	public int maxNoOfDiceForDefence(Country defendingCountry) {
		
		int maxNoOfDiceForDefending = 0;
		if (defendingCountry.getArmies() > 1) {
			maxNoOfDiceForDefending = 2;
		} else
			maxNoOfDiceForDefending = 1;
		return maxNoOfDiceForDefending;
	}

	/**
	 * This method determines the number of armies a player gets when he exchanges
	 * his cards.
	 * 
	 * @param player
	 * @return
	 */
	public int armiesFromCardExchange(int artilleryCount, int infantryCount, int cavalryCount) {

		boolean artilleryFlag = false;
		boolean infantryFlag = false;
		boolean cavalryFlag = false;
		boolean result = false;
		ArrayList<Card> playerCards = null;
		int[] cardCount = new int[3];
		int indexes[] = new int[5];

		// Check if player can return cards
		if (((artilleryCount == 3 && infantryCount == 0 && cavalryCount == 0)
				|| (infantryCount == 3 && artilleryCount == 0 && cavalryCount == 0)
				|| (cavalryCount == 3 && infantryCount == 0 && artilleryCount == 0))
				|| (artilleryCount == 1 && infantryCount == 1 && cavalryCount == 1)) {
			
//			ArrayList<Card> testList = new ArrayList();
//	        Card testCard1=new Card();
//	        Card testCard2=new Card();
//	        Card testCard3=new Card();
//	        testCard1.setType(Constants.ARTILLERY);
//	        testCard2.setType(Constants.CAVALRY);
//	        testCard3.setType(Constants.INFANTRY);
//	        testList.add(testCard1);
//	        testList.add(testCard1);
//	        testList.add(testCard1);
//	        testList.add(testCard2);
//	        testList.add(testCard3);
//	        this.setCardType(testList);

			playerCards = this.getCardType();
		} else {
			Utilities.gameLog(
					"Player: " + this.getName() + " || Stage: Reinforcement Armies || Invalid card selection!! ",
					LogLevel.ERROR);
			return 0;
		}
		// When player has 3 cards of same type and exchange them.
		if (artilleryCount == 3) {
			for (int i = 0, j = 0; i < playerCards.size(); i++) {
				if (Constants.ARTILLERY.equals(playerCards.get(i).getType())) {
					indexes[j] = i;
					j++;
				}
			}
			Arrays.sort(indexes);
			
			for (int i = 2; i >= 0; i--) {
				playerCards.remove(indexes[i]);
			}
			result = true;
		} else if (infantryCount == 3) {
			for (int i = 0, j = 0; i < playerCards.size(); i++) {
				if (Constants.INFANTRY.equals(playerCards.get(i).getType())) {
					indexes[j] = i;
					j++;
				}
			}
			Arrays.sort(indexes);
			for (int i = 2; i >= 0; i--) {
				playerCards.remove(indexes[i]);
			}
			result = true;
		} else if (cavalryCount == 3) {
			for (int i = 0, j = 0; i < playerCards.size(); i++) {
				if (Constants.CAVALRY.equals(playerCards.get(i).getType())) {
					indexes[j] = i;
					j++;
				}
			}
			
			Arrays.sort(indexes);
			for (int i = 2; i >=0; i--) {
				
				playerCards.remove(indexes[i]);
				
			}
			result = true;
		}
		// Check if the player has 3 cards of different types and exchange them.
		else if (artilleryCount > 0 && infantryCount > 0 && cavalryCount > 0) {
			for (int i = 0, j = 0; i < playerCards.size(); i++) {
				if (Constants.ARTILLERY.equals(playerCards.get(i).getType()) && artilleryFlag == false) {
					indexes[j] = i;
					artilleryFlag = true;
					j++;
				} else if (Constants.INFANTRY.equals(playerCards.get(i).getType()) && infantryFlag == false) {
					indexes[j] = i;
					infantryFlag = true;
					j++;
				} else if (Constants.CAVALRY.equals(playerCards.get(i).getType()) && cavalryFlag == false) {
					indexes[j] = i;
					cavalryFlag = true;
					j++;
				}
				if (artilleryFlag == true && infantryFlag == true && cavalryFlag == true) {
					break;
				}
			}
			Arrays.sort(indexes);
			for (int i = 2; i >=0; i--) {
				playerCards.remove(indexes[i]);
			}
			result = true;
		} else {
			Utilities.gameLog(
					"Player: " + this.getName() + " || Stage: Reinforcement Armies || Cards cannot be exchanged!! ",
					LogLevel.WARN);
			return 0;
		}

		if (result == true) {
			int armies = (this.getCardExchangeCount() + 1) * 5;
			this.setCardExchangeCount(this.getCardExchangeCount() + 1);
			// Update the player with the list after the cards are exchanged.
			this.setCardType(playerCards);
			// Update the number of armies the player owns.
			this.setArmies(this.getArmies() + armies);
			this.setNumberOfArmiesLeft(armies);
			Utilities.gameLog("Player: " + this.getName()
					+ " || Stage: Reinforcement Armies || Number of armies given: " + armies, LogLevel.INFO);
			return armies;
		} else {
			Utilities.gameLog(
					"Player: " + this.getName() + " || Stage: Reinforcement Armies || Cards cannot be exchanged!!",
					LogLevel.WARN);
			return 0;
		}
	}
	
	/**
	 * This method gives the count of each type of card that the player has.
	 * 
	 * @param player
	 * @return Array of integer type containing the number of cards of each type in
	 *         the order artillery, infantry, cavalry.
	 */
	public ArrayList<Integer> cardCount() {

		
//		cardCount.add(1);
//		cardCount.add(1);
//		cardCount.add(3);
		
		ArrayList<Card> testList = new ArrayList();
        Card testCard1=new Card();
        Card testCard2=new Card();
        Card testCard3=new Card();
        testCard1.setType(Constants.ARTILLERY);
        testCard2.setType(Constants.CAVALRY);
        testCard3.setType(Constants.INFANTRY);
        testList.add(testCard1);
        testList.add(testCard1);
        testList.add(testCard1);
        testList.add(testCard2);
        testList.add(testCard3);
        this.setCardType(testList);
//		if (this.getCardType() != null && this.getCardType().size() != 0) {
//			cards = this.getCardType();
//		} else {
//			Utilities.gameLog("Player: " + this.getName() + " || Stage: Card count check  || Player has no cards!! || Type:" + this.getCardType(),
//					LogLevel.ERROR);
//			ArrayList<Integer> myList = new ArrayList<Integer>(Collections.nCopies(3, 0));
//			myList.set(0, 3);
//			myList.set(1,3);
//			myList.set(2, 1);
//			return myList;
//		}
		// Count what type of cards does the player have
		int artilleryCount = 0;
		int cavalryCount = 0;
		int infantryCount = 0;
		ArrayList<Integer> cardCount = new ArrayList<Integer>();
		
		if(this.cards != null && this.cards.size() != 0)
		{
			System.out.println(this.cards);
			
			for (Card card : this.cards)
			{
				if (card.getType().equals(Constants.ARTILLERY)) {
					artilleryCount++;
				} else if (card.getType().equals(Constants.INFANTRY)) {
					infantryCount++;
				} else if (card.getType().equals(Constants.CAVALRY)) {
					cavalryCount++;
				}
			}
			
		}

		cardCount.add(artilleryCount);
		cardCount.add(infantryCount);
		cardCount.add(cavalryCount);
			
			return cardCount;
		
			
		
	}
	
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
	public boolean getReinforcementArmies() {
		// Number of armies to be given to the player for reinforcement
		int armies = 0;
		// The control value associated with the continents owned by the player
		int controlValue = 0;
		// If the player owns continents then the number of armies
		// given to him is the sum of the control values
		try {
			if (this.getContinents() != null && this.getContinents().size() != 0) {
				for (int i = 0; i < this.getContinents().size(); i++) {
					controlValue = this.getContinents().get(i).getControlValue();
					armies = armies + controlValue;
				}
			} else {
				armies = this.getCountries().size() / 3;
			}
			// Update the number of armies the player owns.
			this.setArmies(this.getArmies() + armies);
			this.setNumberOfArmiesLeft(armies);
			Utilities.gameLog("Player: " + this.getName()
					+ " || Stage: Reinforcement Armies || Number of armies given: " + armies, LogLevel.INFO);
			return true;
		} catch (Exception e) {
			Utilities.gameLog("Player: " + this.getName()
					+ " || Stage: Reinforcement Armies || Cannot give armies to reinforce!! ", LogLevel.ERROR);
			return false;
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

	public boolean reinforceArmies(Country country, int armies) {
		Utilities.gameLog(
				"Player: " + this.getName() + "|| Stage: Reinforcement || Country reinforced: " + country.getName(),
				LogLevel.INFO);

		ArrayList<Country> countries = this.getCountries();
		if (countries != null && countries.contains(country)) {
			int i = countries.indexOf(country);
			Country country1 = countries.get(i);
			country1.setArmies(country1.getArmies() + armies);
			countries.remove(i);
			countries.add(country1);
			this.setCountries(countries);
			this.setNumberOfArmiesLeft(this.getNumberOfArmiesLeft() - armies);
			Utilities.gameLog("Player: " + this.getName() + "|| Country reinforced!!", LogLevel.INFO);
			return true;
		} else {
			Utilities.gameLog("Player: " + this.getName() + "|| Country could not be reinforced!!", LogLevel.WARN);
			return false;
		}
	}
	
	/**
	 * This method is used to perform the fortification stage in the game. In this stage the user can move an army from one
	 * country that he owns to another if they are connected.
	 *   
	 * @param player Contains the details of the player.
	 * @param fromCountry The country from which the army will be moved.
	 * @param toCountry Thecountry to which the army will be moved.
	 * @return true if the fortification was successful, else false.
	 */
	public boolean fortifyArmies(Country fromCountry,Country toCountry, int armies) {
		
		Utilities.gameLog("Player: "+this.getName()+"|| Stage: Fortification || Countries involved: "+fromCountry.getName()+","+toCountry.getName(),LogLevel.INFO);
		ArrayList<Country> playerCountries = this.getCountries();
		if(playerCountries != null && playerCountries.contains(fromCountry) && playerCountries.contains(toCountry) && armies>0) {
			//Update the number for armies in the fortifying country.
			int i = playerCountries.indexOf(fromCountry);
			Country country1 = playerCountries.get(i);
			country1.setArmies(country1.getArmies()-armies);
			playerCountries.remove(i);
			playerCountries.add(country1);
			//Update the number for armies in the fortified country.
			int j = playerCountries.indexOf(toCountry);
			Country country2 = playerCountries.get(j);
			country2.setArmies(country2.getArmies()+armies);
			playerCountries.remove(j);
			playerCountries.add(country2);
			this.setCountries(playerCountries);
			Utilities.gameLog("Player: "+this.getName()+"|| Countries fortified!! || "
					+ country1.getName() +" : "+country1.getArmies()+" || "+ country2.getName() +" : "+country2.getArmies(),LogLevel.INFO);
			return true;
		}
		Utilities.gameLog("Player: "+this.getName()+"|| Countries could not be fortified",LogLevel.WARN);
		return false;
	}
	public static void main(String[] args) {
		Player play = new Player();
		//play.set();

		StartUpPhase start = StartUpPhase.getInstance();


		System.out.println("Attacking country armies: " + playerList.get(0).getCountries().get(0).getName() + " "
				+ playerList.get(0).getCountries().get(0).getArmies());
		System.out.println("Defending country armies: " + playerList.get(1).getCountries().get(0).getName() + " "
				+ playerList.get(1).getCountries().get(0).getArmies());
		play.attack(playerList.get(0).getCountries().get(0), playerList.get(1).getCountries().get(0), 1, 1, "allOutWinner");
	}
	
	

}
