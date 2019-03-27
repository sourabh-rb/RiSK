<<<<<<< HEAD:src/gameEngine/StartUpPhase.java

package gameEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import constants.LogLevel;
import gameEngine.Continent;
import gameEngine.Country;
import gameEngine.Player;
import utilities.Utilities;

/**
 * This class takes care of the initialization part where it decides which
 * countries are given to which player, how many armies should be given to each
 * player and how many armies are placed in each country for that particular
 * player. Assigning of countries and placeng the armies is random. Each player
 * will get equal number of armies.
 * 
 * @author Aravind Reddy
 *
 */
public class StartUpPhase {
	private int noOfPlayers;
	private ArrayList<Continent> continentList = new ArrayList<Continent>();
	private ArrayList<Country> neighborCountries = new ArrayList<Country>();
	private ArrayList<Country> countriesInContinent = new ArrayList<Country>();
	private  ArrayList<Player> playerList = new ArrayList<Player>();
	private HashMap<String, Country> countryNameToObj = new HashMap<String, Country>();
	private HashMap<String, Continent> continentNameToObj = new HashMap<String, Continent>();
	private ArrayList<Country> countryList = new ArrayList<Country>();
	private static StartUpPhase startPhaseObject = null;


	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public ArrayList<Continent> getContinentList() {
		return continentList;
	}

	public void setContinentList(ArrayList<Continent> continentList) {
		this.continentList = continentList;
	}

	public ArrayList<Country> getNeighborCountries() {
		return neighborCountries;
	}

	public void setNeighborCountries(ArrayList<Country> neighborCountries) {
		this.neighborCountries = neighborCountries;
	}

	public ArrayList<Country> getCountriesInContinent() {
		return countriesInContinent;
	}

	public void setCountriesInContinent(ArrayList<Country> countriesInContinent) {
		this.countriesInContinent = countriesInContinent;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public HashMap<String, Country> getCountryNameToObj() {
		return countryNameToObj;
	}

	public void setCountryNameToObj(HashMap<String, Country> countryNameToObj) {
		this.countryNameToObj = countryNameToObj;
	}

	public HashMap<String, Continent> getContinentNameToObj() {
		return continentNameToObj;
	}

	public void setContinentNameToObj(HashMap<String, Continent> continentNameToObj) {
		this.continentNameToObj = continentNameToObj;
	}

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public static StartUpPhase getStartPhaseObject() {
		return startPhaseObject;
	}

	public static void setStartPhaseObject(StartUpPhase startPhaseObject) {
		StartUpPhase.startPhaseObject = startPhaseObject;
	}

	/**
	 * 
	 */

	public StartUpPhase() 
	{
		
	}
		
	public static synchronized StartUpPhase getInstance() {
        if(startPhaseObject == null) {
        	startPhaseObject = new StartUpPhase();
        }
        return startPhaseObject;
    }


	/**
	 * This function creates objects for all the map elements like country,
	 * continent, player
	 * 
	 * @param continentHashMap  This hashmap contains continents and their
	 *                          respective values
	 * @param terrritoryHashMap This hashmap contains country as the key element and
	 *                          values comprise continent to which it belongs and
	 *                          the countries neighboring countries
	 * @param noOfPlayers       Number of palyers want to paly the game
	 */

	public void mappingElements(HashMap<String, Integer> continentHashMap,
			HashMap<String, ArrayList<String>> terrritoryHashMap, int noOfPlayers) {

		int i = 0;
		for (String key : continentHashMap.keySet()) {
			continentList.add(new Continent());
			continentList.get(i).setName(key);
			continentList.get(i).setControlValue(continentHashMap.get(key));
			continentNameToObj.put(key, continentList.get(i));
			int h = continentHashMap.get(key);
			i++;
		}
		Utilities.gameLog("Created continent objects and added their values to the respective objects", LogLevel.INFO);
		int j = 0;
		for (String key : terrritoryHashMap.keySet()) {
			ArrayList<Country> countryListInContinent = new ArrayList<Country>();
			countryList.add(new Country());
			countryList.get(j).setName(key);
			Continent conObj = continentNameToObj.get(terrritoryHashMap.get(key).get(0));
			conObj.getCountriesComprised().add(countryList.get(j));
			countryNameToObj.put(key, countryList.get(j));
			j++;
		}
		// Setting neighboring countries for each country
		for (Country c : countryList) {
			for (String neigh_country : terrritoryHashMap.get(c.getName()).subList(1,
					terrritoryHashMap.get(c.getName()).size())) {
				neighborCountries.add(countryNameToObj.get(neigh_country));
			}
			System.out.println("Neighboring countries for "+c.getName());
			for(int g=0;g<neighborCountries.size();g++) {
		    	System.out.println(neighborCountries.get(g).getName());
		    }
			c.setNeighborCounties((ArrayList<Country>) neighborCountries.clone());
			neighborCountries.clear();
		}
		// Creating player objects
		int playerNumber = 1;
		for (int k = 0; k < noOfPlayers; k++) {
			playerList.add(new Player());
			playerList.get(k).setName("Player" + playerNumber);
			playerNumber++;
		}
		System.out.println("PLayerlist size: " + playerList.size());
		Utilities.gameLog("Number of Player objects created: " + playerList.size(), LogLevel.INFO);
		initialSetUp(playerList, continentList, countryList);
	}

/**
 * This function return number of players in the game
 * @param playerList list of player objects
 * @return It is returning number of players
 */
	public int noOfPlayers(ArrayList<Player> playerList) {
		return playerList.size();
	}

	/**
	 * This function calculates number of continents in the provided map
	 * @param continentList contains list of continent objects
	 * @return returns number of continents
	 */
	public int noOfContinents(ArrayList<Continent> continentList) {
		return continentList.size();
	}
/**
 * This function is to calculate number of countries
 * 
 * @param countryList contains country objects
 * @return number of countries
 */
	public int noOfCountries(ArrayList<Country> countryList) {
		return countryList.size();
	}

	/**
	 * This function takes care of initial setup of the game based on the input
	 * given by the user. It provides certain number of armies to each player and
	 * certain countries in the selected map.
	 * @param playerList    This contains all the player objects
	 * @param continentList contains all continent objects
	 * @param countryList   contains country objects
	 */
	public void initialSetUp(ArrayList<Player> playerList, ArrayList<Continent> continentList,
			ArrayList<Country> countryList) {
		noOfPlayers = noOfPlayers(playerList);
		System.out.println("Number of players " + noOfPlayers);
		Utilities.gameLog("Total number of Players playing the game " + noOfPlayers, LogLevel.INFO);
		int numberOfArmiesEach = 0;
		int noOfTotalCountries = 0;
		ArrayList<Country> listOfAllCountries = new ArrayList<Country>();
		System.out.println("Number of continents: " + noOfContinents(continentList));
		Utilities.gameLog("Total number of continents in the whole map " + noOfContinents(continentList), LogLevel.INFO);
		for (int i = 0; i < continentList.size(); i++) {
			for (int j = 0; j < continentList.get(i).getCountriesComprised().size(); j++) {
				listOfAllCountries.add(continentList.get(i).getCountriesComprised().get(j));
				noOfTotalCountries = noOfTotalCountries + 1;
			}
		}
		System.out.println("No Of total Countries" + noOfCountries(countryList));
		Utilities.gameLog("Total number of countries in the whole map " + noOfCountries(countryList), LogLevel.INFO);
		numberOfArmiesEach = calculateNoOfArmies(noOfPlayers);
		// looping through all players to give equal number of armies
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setArmies(numberOfArmiesEach);
			System.out.println(playerList.get(i).getArmies());
		}

		setRandomCountriesForEach(listOfAllCountries, continentList, noOfPlayers, playerList);

		// Assigning an army to each of the countries for a particular player and
		// calculating the remaining armies under each player
		for (int j = 0; j < playerList.size(); j++) {
			playerList.get(j).setNumberOfArmiesLeft(numberOfArmiesEach);
			System.out.println("Player name: " + playerList.get(j).getName());
			System.out.println(playerList.get(j).getName() + " is assigned with " + numberOfArmiesEach + " armies");
			Utilities.gameLog(
					"Total number of armies assigned to " + playerList.get(j).getName() + " is " + numberOfArmiesEach, LogLevel.INFO);
			for (int k = 0; k < playerList.get(j).getCountries().size(); k++) {
				playerList.get(j).getCountries().get(k).setArmies(1);
				System.out.println("1 army given to " + playerList.get(j).getCountries().get(k).getName());
				int previousNumberOfArmiesLeft = playerList.get(j).getNumberOfArmiesLeft();
				playerList.get(j).setNumberOfArmiesLeft(--previousNumberOfArmiesLeft);
				System.out.println("No of armies left" + playerList.get(j).getNumberOfArmiesLeft());
				Utilities.gameLog("No of armies left with " + playerList.get(j).getName() + " is "
						+ playerList.get(j).getNumberOfArmiesLeft(), LogLevel.INFO);
			}
		}
	}

	/**
	 * This function is used to calculate number of armies each player gets and it
	 * is dependent on number of players wants to play the game
	 * 
	 * @param noOfPlayers Number of players playing the game
	 *
	 */
	public int calculateNoOfArmies(int noOfPlayers) {
		int noOfArmies = 0;

		if (noOfPlayers == 2)
			noOfArmies = 10;
		else if (noOfPlayers == 3)
			noOfArmies = 35;
		else if (noOfPlayers == 4)
			noOfArmies = 30;
		else if (noOfPlayers == 5)
			noOfArmies = 25;
		else if (noOfPlayers == 6)
			noOfArmies = 20;
		Utilities.gameLog("Assigned " + noOfArmies + " armies to each player", LogLevel.INFO);
		return noOfArmies;
	}

	/**
	 * This function is used to set random countries for each player
	 * 
	 * @param noOfPlayers        Number of players playing the game
	 * @param listOfAllCountries list all country objects
	 * @param continentList      contains list of all continent objects
	 * @param playerList         contains list of player objects
	 */
	public void setRandomCountriesForEach(ArrayList<Country> listOfAllCountries, ArrayList<Continent> continentList,
			int noOfPlayers, ArrayList<Player> playerList) {
		// Randomly assigning the countries to all the players
		/*
		 * for(int j=0;j<listOfAllCountries.size();j++) { ArrayList<Country>
		 * randomCountryList = new ArrayList<Country>(); int randomPlayer = (int)
		 * (Math.random() * playerList.size());
		 * System.out.println("Random Number: "+randomPlayer);
		 * //System.out.println(playerList.get(randomPlayer).getCountries());
		 * playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(j));
		 * playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).
		 * getCountries()); }
		 */

		ArrayList<Integer> allCountryIndices = new ArrayList<Integer>();
		for (Integer i = 0; i < listOfAllCountries.size(); i++) {
			allCountryIndices.add(i);
		}
		ArrayList<Integer> randomCountryIndices = new ArrayList<Integer>();
		for (int i = 0; i < noOfPlayers; i++) {
			int noOfEqualCountries = listOfAllCountries.size() / noOfPlayers;
			Integer randomCountry;
			for (int j = 0; j < noOfEqualCountries; j++) {
				int countryPresent;
				do {
					countryPresent = 0;
					randomCountry = (int) (Math.random() * listOfAllCountries.size());
					System.out.println("Random Country index: " + randomCountry);
					if (randomCountryIndices.contains(randomCountry)) {
						countryPresent = 1;
					}
				} while (countryPresent == 1);
				randomCountryIndices.add(randomCountry);
				System.out.println("Random countries assigned - " + randomCountryIndices.toString());
				allCountryIndices.remove(randomCountry);
				System.out.println("countries yet to be assigned - " + allCountryIndices.toString());
				playerList.get(i).getCountries().add(listOfAllCountries.get(randomCountry));
        listOfAllCountries.get(randomCountry).setOwner(playerList.get(i));
				// playerList.get(i).setCountries(playerList.get(i).getCountries());
			}
		}
		// Assigning left over countries to random players
		for (int i = 0; i < allCountryIndices.size(); i++) {
			int randomPlayer = (int) (Math.random() * playerList.size());
			System.out.println("Random Player: " + randomPlayer);
			listOfAllCountries.get(allCountryIndices.get(i));
			System.out.println("char");
			playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(allCountryIndices.get(i)));
      listOfAllCountries.get(allCountryIndices.get(i)).setOwner(playerList.get(randomPlayer));
			playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).getCountries());
		}
		Utilities.gameLog("Assigned countries randomly to all the players", LogLevel.INFO);

		// Display owned countries of each player including player name
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("Player Name: " + playerList.get(i).getName() + " and his countries below");
			for (int j = 0; j < playerList.get(i).getCountries().size(); j++) {
				System.out.println(playerList.get(i).getCountries().get(j).getName());
			}
		}

	}
	
	public void assignLeftOVerArmies(String countryName, int noOfArmies) {
		for(int i=0;i<countryList.size();i++) {
			if(countryName.equals(countryList.get(i).getName())){
				countryList.get(i).setArmies(countryList.get(i).getArmies()+noOfArmies);
				i=countryList.size();
			}
		}
	}
	
	/**
	 * This method is used to assign armies during Game initialization
	 * @return player army left
	 */
	
	public int initialPhaseArmyAssignment(Player player)
	{
		if(player.getNumberOfArmiesLeft()>0)
		{
			return player.getNumberOfArmiesLeft()-1;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start up phase started");
		StartUpPhase start = new StartUpPhase();
	}
	
	public ArrayList<Country> getMapCountries()
	{
		return countryList;
	}

}


=======

package controller.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import constants.LogLevel;
import model.Continent;
import model.Country;
import model.Player;
import utilities.Utilities;

/**
 * This class takes care of the initialization part where it decides which
 * countries are given to which player, how many armies should be given to each
 * player and how many armies are placed in each country for that particular
 * player. Assigning of countries and placeng the armies is random. Each player
 * will get equal number of armies.
 * 
 * @author Aravind Reddy
 *
 */
public class StartUpPhase {
	private int noOfPlayers;
	private ArrayList<Continent> continentList = new ArrayList<Continent>();
	private ArrayList<Country> neighborCountries = new ArrayList<Country>();
	private ArrayList<Country> countriesInContinent = new ArrayList<Country>();
	private  ArrayList<Player> playerList = new ArrayList<Player>();
	private HashMap<String, Country> countryNameToObj = new HashMap<String, Country>();
	private HashMap<String, Continent> continentNameToObj = new HashMap<String, Continent>();
	private ArrayList<Country> countryList = new ArrayList<Country>();
	private static StartUpPhase startPhaseObject = null;


	public int getNoOfPlayers() {
		return noOfPlayers;
	}

	public void setNoOfPlayers(int noOfPlayers) {
		this.noOfPlayers = noOfPlayers;
	}

	public ArrayList<Continent> getContinentList() {
		return continentList;
	}

	public void setContinentList(ArrayList<Continent> continentList) {
		this.continentList = continentList;
	}

	public ArrayList<Country> getNeighborCountries() {
		return neighborCountries;
	}

	public void setNeighborCountries(ArrayList<Country> neighborCountries) {
		this.neighborCountries = neighborCountries;
	}

	public ArrayList<Country> getCountriesInContinent() {
		return countriesInContinent;
	}

	public void setCountriesInContinent(ArrayList<Country> countriesInContinent) {
		this.countriesInContinent = countriesInContinent;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public HashMap<String, Country> getCountryNameToObj() {
		return countryNameToObj;
	}

	public void setCountryNameToObj(HashMap<String, Country> countryNameToObj) {
		this.countryNameToObj = countryNameToObj;
	}

	public HashMap<String, Continent> getContinentNameToObj() {
		return continentNameToObj;
	}

	public void setContinentNameToObj(HashMap<String, Continent> continentNameToObj) {
		this.continentNameToObj = continentNameToObj;
	}

	public ArrayList<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(ArrayList<Country> countryList) {
		this.countryList = countryList;
	}

	public static StartUpPhase getStartPhaseObject() {
		return startPhaseObject;
	}

	public static void setStartPhaseObject(StartUpPhase startPhaseObject) {
		StartUpPhase.startPhaseObject = startPhaseObject;
	}

	/**
	 * 
	 */

	public StartUpPhase() 
	{
		
	}
		
	public static synchronized StartUpPhase getInstance() {
        if(startPhaseObject == null) {
        	startPhaseObject = new StartUpPhase();
        }
        return startPhaseObject;
    }


	/**
	 * This function creates objects for all the map elements like country,
	 * continent, player
	 * 
	 * @param continentHashMap  This hashmap contains continents and their
	 *                          respective values
	 * @param terrritoryHashMap This hashmap contains country as the key element and
	 *                          values comprise continent to which it belongs and
	 *                          the countries neighboring countries
	 * @param noOfPlayers       Number of palyers want to paly the game
	 */

	public void mappingElements(HashMap<String, Integer> continentHashMap,
			HashMap<String, ArrayList<String>> terrritoryHashMap, int noOfPlayers) {

		int i = 0;
		for (String key : continentHashMap.keySet()) {
			continentList.add(new Continent());
			continentList.get(i).setName(key);
			continentList.get(i).setControlValue(continentHashMap.get(key));
			continentNameToObj.put(key, continentList.get(i));
			int h = continentHashMap.get(key);
			i++;
		}
		Utilities.gameLog("Created continent objects and added their values to the respective objects", LogLevel.INFO);
		int j = 0;
		for (String key : terrritoryHashMap.keySet()) {
			ArrayList<Country> countryListInContinent = new ArrayList<Country>();
			countryList.add(new Country());
			countryList.get(j).setName(key);
			Continent conObj = continentNameToObj.get(terrritoryHashMap.get(key).get(0));
			conObj.getCountriesComprised().add(countryList.get(j));
			countryNameToObj.put(key, countryList.get(j));
			j++;
		}
		// Setting neighboring countries for each country
		for (Country c : countryList) {
			for (String neigh_country : terrritoryHashMap.get(c.getName()).subList(1,
					terrritoryHashMap.get(c.getName()).size())) {
				neighborCountries.add(countryNameToObj.get(neigh_country));
			}
			System.out.println("Neighboring countries for "+c.getName());
			for(int g=0;g<neighborCountries.size();g++) {
		    	System.out.println(neighborCountries.get(g).getName());
		    }
			c.setNeighborCounties((ArrayList<Country>) neighborCountries.clone());
			neighborCountries.clear();
		}
		// Creating player objects
		int playerNumber = 1;
		for (int k = 0; k < noOfPlayers; k++) {
			playerList.add(new Player());
			playerList.get(k).setName("Player" + playerNumber);
			playerNumber++;
		}
		System.out.println("PLayerlist size: " + playerList.size());
		Utilities.gameLog("Number of Player objects created: " + playerList.size(), LogLevel.INFO);
		initialSetUp(playerList, continentList, countryList);
	}

/**
 * This function return number of players in the game
 * @param playerList list of player objects
 * @return It is returning number of players
 */
	public int noOfPlayers(ArrayList<Player> playerList) {
		return playerList.size();
	}

	/**
	 * This function calculates number of continents in the provided map
	 * @param continentList contains list of continent objects
	 * @return returns number of continents
	 */
	public int noOfContinents(ArrayList<Continent> continentList) {
		return continentList.size();
	}
/**
 * This function is to calculate number of countries
 * 
 * @param countryList contains country objects
 * @return number of countries
 */
	public int noOfCountries(ArrayList<Country> countryList) {
		return countryList.size();
	}

	/**
	 * This function takes care of initial setup of the game based on the input
	 * given by the user. It provides certain number of armies to each player and
	 * certain countries in the selected map.
	 * @param playerList    This contains all the player objects
	 * @param continentList contains all continent objects
	 * @param countryList   contains country objects
	 */
	public void initialSetUp(ArrayList<Player> playerList, ArrayList<Continent> continentList,
			ArrayList<Country> countryList) {
		noOfPlayers = noOfPlayers(playerList);
		System.out.println("Number of players " + noOfPlayers);
		Utilities.gameLog("Total number of Players playing the game " + noOfPlayers, LogLevel.INFO);
		int numberOfArmiesEach = 0;
		int noOfTotalCountries = 0;
		ArrayList<Country> listOfAllCountries = new ArrayList<Country>();
		System.out.println("Number of continents: " + noOfContinents(continentList));
		Utilities.gameLog("Total number of continents in the whole map " + noOfContinents(continentList), LogLevel.INFO);
		for (int i = 0; i < continentList.size(); i++) {
			for (int j = 0; j < continentList.get(i).getCountriesComprised().size(); j++) {
				listOfAllCountries.add(continentList.get(i).getCountriesComprised().get(j));
				noOfTotalCountries = noOfTotalCountries + 1;
			}
		}
		System.out.println("No Of total Countries" + noOfCountries(countryList));
		Utilities.gameLog("Total number of countries in the whole map " + noOfCountries(countryList), LogLevel.INFO);
		numberOfArmiesEach = calculateNoOfArmies(noOfPlayers);
		// looping through all players to give equal number of armies
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setArmies(numberOfArmiesEach);
			System.out.println(playerList.get(i).getArmies());
		}

		setRandomCountriesForEach(listOfAllCountries, continentList, noOfPlayers, playerList);

		// Assigning an army to each of the countries for a particular player and
		// calculating the remaining armies under each player
		for (int j = 0; j < playerList.size(); j++) {
			playerList.get(j).setNumberOfArmiesLeft(numberOfArmiesEach);
			System.out.println("Player name: " + playerList.get(j).getName());
			System.out.println(playerList.get(j).getName() + " is assigned with " + numberOfArmiesEach + " armies");
			Utilities.gameLog(
					"Total number of armies assigned to " + playerList.get(j).getName() + " is " + numberOfArmiesEach, LogLevel.INFO);
			for (int k = 0; k < playerList.get(j).getCountries().size(); k++) {
				playerList.get(j).getCountries().get(k).setArmies(1);
				System.out.println("1 army given to " + playerList.get(j).getCountries().get(k).getName());
				int previousNumberOfArmiesLeft = playerList.get(j).getNumberOfArmiesLeft();
				playerList.get(j).setNumberOfArmiesLeft(--previousNumberOfArmiesLeft);
				System.out.println("No of armies left" + playerList.get(j).getNumberOfArmiesLeft());
				Utilities.gameLog("No of armies left with " + playerList.get(j).getName() + " is "
						+ playerList.get(j).getNumberOfArmiesLeft(), LogLevel.INFO);
			}
		}
	}

	/**
	 * This function is used to calculate number of armies each player gets and it
	 * is dependent on number of players wants to play the game
	 * 
	 * @param noOfPlayers Number of players playing the game
	 *
	 */
	public int calculateNoOfArmies(int noOfPlayers) {
		int noOfArmies = 0;

		if (noOfPlayers == 2)
			noOfArmies = 10;
		else if (noOfPlayers == 3)
			noOfArmies = 35;
		else if (noOfPlayers == 4)
			noOfArmies = 30;
		else if (noOfPlayers == 5)
			noOfArmies = 25;
		else if (noOfPlayers == 6)
			noOfArmies = 20;
		Utilities.gameLog("Assigned " + noOfArmies + " armies to each player", LogLevel.INFO);
		return noOfArmies;
	}

	/**
	 * This function is used to set random countries for each player
	 * 
	 * @param noOfPlayers        Number of players playing the game
	 * @param listOfAllCountries list all country objects
	 * @param continentList      contains list of all continent objects
	 * @param playerList         contains list of player objects
	 */
	public void setRandomCountriesForEach(ArrayList<Country> listOfAllCountries, ArrayList<Continent> continentList,
			int noOfPlayers, ArrayList<Player> playerList) {
		// Randomly assigning the countries to all the players
		/*
		 * for(int j=0;j<listOfAllCountries.size();j++) { ArrayList<Country>
		 * randomCountryList = new ArrayList<Country>(); int randomPlayer = (int)
		 * (Math.random() * playerList.size());
		 * System.out.println("Random Number: "+randomPlayer);
		 * //System.out.println(playerList.get(randomPlayer).getCountries());
		 * playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(j));
		 * playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).
		 * getCountries()); }
		 */

		ArrayList<Integer> allCountryIndices = new ArrayList<Integer>();
		for (Integer i = 0; i < listOfAllCountries.size(); i++) {
			allCountryIndices.add(i);
		}
		ArrayList<Integer> randomCountryIndices = new ArrayList<Integer>();
		for (int i = 0; i < noOfPlayers; i++) {
			int noOfEqualCountries = listOfAllCountries.size() / noOfPlayers;
			Integer randomCountry;
			for (int j = 0; j < noOfEqualCountries; j++) {
				int countryPresent;
				do {
					countryPresent = 0;
					randomCountry = (int) (Math.random() * listOfAllCountries.size());
					System.out.println("Random Country index: " + randomCountry);
					if (randomCountryIndices.contains(randomCountry)) {
						countryPresent = 1;
					}
				} while (countryPresent == 1);
				randomCountryIndices.add(randomCountry);
				System.out.println("Random countries assigned - " + randomCountryIndices.toString());
				allCountryIndices.remove(randomCountry);
				System.out.println("countries yet to be assigned - " + allCountryIndices.toString());
				playerList.get(i).getCountries().add(listOfAllCountries.get(randomCountry));
        listOfAllCountries.get(randomCountry).setOwner(playerList.get(i));
				// playerList.get(i).setCountries(playerList.get(i).getCountries());
			}
		}
		// Assigning left over countries to random players
		for (int i = 0; i < allCountryIndices.size(); i++) {
			int randomPlayer = (int) (Math.random() * playerList.size());
			System.out.println("Random Player: " + randomPlayer);
			listOfAllCountries.get(allCountryIndices.get(i));
			System.out.println("char");
			playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(allCountryIndices.get(i)));
      listOfAllCountries.get(allCountryIndices.get(i)).setOwner(playerList.get(randomPlayer));
			playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).getCountries());
		}
		Utilities.gameLog("Assigned countries randomly to all the players", LogLevel.INFO);

		// Display owned countries of each player including player name
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("Player Name: " + playerList.get(i).getName() + " and his countries below");
			for (int j = 0; j < playerList.get(i).getCountries().size(); j++) {
				System.out.println(playerList.get(i).getCountries().get(j).getName());
			}
		}

	}
	
	public void assignLeftOVerArmies(String countryName, int noOfArmies) {
		for(int i=0;i<countryList.size();i++) {
			if(countryName.equals(countryList.get(i).getName())){
				countryList.get(i).setArmies(countryList.get(i).getArmies()+noOfArmies);
				i=countryList.size();
			}
		}
	}
	
	/**
	 * This method is used to assign armies during Game initialization
	 * @return player army left
	 */
	
	public int initialPhaseArmyAssignment(Player player)
	{
		if(player.getNumberOfArmiesLeft()>0)
		{
			return player.getNumberOfArmiesLeft()-1;
		}
		else
		{
			return 0;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start up phase started");
		StartUpPhase start = new StartUpPhase();
	}
	
	public ArrayList<Country> getMapCountries()
	{
		return countryList;
	}

}


>>>>>>> dev:src/controller/initialization/StartUpPhase.java
