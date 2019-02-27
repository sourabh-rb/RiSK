/**
 * 
 */
package controller.initialization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import model.Continent;
import model.Country;
import model.Player;

/**
 * This class takes care of the initialization part where it decides which
 * countries are given to which player, how many armies should be given to each
 * player and how many armies are placed in each country for that particular
 * player. Assigning of countries and placeng the armies is random. Each player
 * will get equalll number of armies.
 * 
 * @author Aravind Reddy
 *
 */
public class StartUpPhase {
	static int noOfPlayers = 2;

	/**
	 * 
	 */
	public StartUpPhase() {
		System.out.println("Inside startupphase constructor");
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		Player player4 = new Player();
		player1.setName("Aravind");
		player2.setName("charan");
		player3.setName("shivani");
		player4.setName("Sourabh");

		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);
		// playerList.add(player4);

		Continent continent1 = new Continent();
		Country country1 = new Country();
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
		Country country5 = new Country();
		country5.setName("Germany");
		Country country6 = new Country();
		country6.setName("Sweden");

		ArrayList<Continent> continentList = new ArrayList<Continent>();

		ArrayList<Country> countryList = new ArrayList<Country>();
		countryList.add(country1);
		countryList.add(country2);
		countryList.add(country3);
		countryList.add(country7);
		countryList.add(country8);
		countryList.add(country9);
		continent1.setCountriesComprised(countryList);

		ArrayList<Country> europeCountryList = new ArrayList<Country>();
		europeCountryList.add(country4);
		europeCountryList.add(country5);
		europeCountryList.add(country6);
		continent2.setCountriesComprised(europeCountryList);

		continentList.add(continent1);
		continentList.add(continent2);

		initialSetUp( playerList, continentList, countryList);

	}

	/**
	 * 
	 */
	public void mappingElements(HashMap<String,Integer> continentHashMap, HashMap<String,ArrayList<String>> terrritoryHashMap, int noOfPlayers) {
        ArrayList<Continent> continent_list = new ArrayList<Continent>();
        ArrayList<Country> neigh_countries = new ArrayList<Country>();
        ArrayList<Country> countriesInContinent = new ArrayList<Country>();
        ArrayList<Player> player_List = new ArrayList<Player>();
        HashMap<String,Country> country_name2obj=new HashMap<String,Country>();
        HashMap<String,Continent> continent_name2obj=new HashMap<String,Continent>();
        ArrayList<Country> country_list = new ArrayList<Country>();
        int i=0;
		for (String key: continentHashMap.keySet()) {
			 continent_list.add(new Continent());
			 continent_list.get(i).setName(key);
			 continent_list.get(i).setControlValue(continentHashMap.get(key));
			 continent_name2obj.put(key, continent_list.get(i));
			 int h=continentHashMap.get(key);
			 i++;
		}
		int j=0;
		for(String key : terrritoryHashMap.keySet() ) {
			ArrayList<Country> countryListInContinent = new ArrayList<Country>();
			country_list.add(new Country());
			country_list.get(j).setName(key);
		    Continent conObj = continent_name2obj.get(terrritoryHashMap.get(key).get(0));
		    conObj.getCountriesComprised().add(country_list.get(j));
		    //conObj.setCountriesComprised(conObj.getCountriesComprised());
			country_name2obj.put(key,country_list.get(j));
			j++;
		}
		
		for(Country c:country_list )
		{
		   for ( String neigh_country: terrritoryHashMap.get(c.getName()).subList(1,terrritoryHashMap.get(c.getName()).size() ))
		   {
			   neigh_countries.add(country_name2obj.get(neigh_country));
		   }
		   c.setNeighborCounties(neigh_countries);
		}
		int playerNumber=1;
		for(int k=0;i<noOfPlayers;i++) {
			player_List.add(new Player());
			player_List.get(k).setName("Player"+playerNumber);
			playerNumber++;
		}
		initialSetUp( player_List, continent_list, country_list);
	}

	/**
	 * This function takes care of initial setup of the game based on the input
	 * given by the user. It provides certain number of armies to each player and
	 * certain countries in the selected map.
	 * 
	 * 
	 * @param playerList player object has all the player details
	 * @param player     player object has all the player details
	 * @param player     player object has all the player details
	 * 
	 * 
	 */
	public void initialSetUp( ArrayList<Player> playerList, ArrayList<Continent> continentList,
			ArrayList<Country> countryList) {
		noOfPlayers = playerList.size();
		int numberOfArmiesEach = 0;
		int noOfTotalCountries = 0;
		ArrayList<Country> listOfAllCountries = new ArrayList<Country>();
		System.out.println("Number of continents: " + continentList.size());
		for (int i = 0; i < continentList.size(); i++) {
			for (int j = 0; j < continentList.get(i).getCountriesComprised().size(); j++) {
				listOfAllCountries.add(continentList.get(i).getCountriesComprised().get(j));
				noOfTotalCountries = noOfTotalCountries + 1;
			}
		}
		System.out.println("No Of total Countries" + noOfTotalCountries);
		numberOfArmiesEach = calculateNoOfArmies(noOfPlayers);
		// looping through all players to give equal number of armies
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).setArmies(numberOfArmiesEach);
			System.out.println(playerList.get(i).getArmies());
		}

		setRandomCountriesForEach(listOfAllCountries, continentList, noOfPlayers, playerList);

		// Assigning an army to each of the countries of a particular player and
		// calculating the remaining armies under each player
		for (int j = 0; j < playerList.size(); j++) {
			playerList.get(j).setNumberOfArmiesLeft(numberOfArmiesEach);
			System.out.println("Player name: " + playerList.get(j).getName());
			System.out.println(playerList.get(j).getName() + " is assigned with " + numberOfArmiesEach + " armies");
			for (int k = 0; k < playerList.get(j).getCountries().size(); k++) {
				playerList.get(j).getCountries().get(k).setArmies(1);
				System.out.println("1 army given to " + playerList.get(j).getCountries().get(k).getName());
				int previousNumberOfArmiesLeft = playerList.get(j).getNumberOfArmiesLeft();
				playerList.get(j).setNumberOfArmiesLeft(--previousNumberOfArmiesLeft);
				System.out.println("No of armies left" + playerList.get(j).getNumberOfArmiesLeft());
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
		return noOfArmies;
	}

	// testing a push
	// second testing a push
	public void setRandomCountriesForEach(ArrayList<Country> listOfAllCountries, ArrayList<Continent> continentList,
			int noOfPlayers, ArrayList<Player> playerList) {

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
				//playerList.get(i).setCountries(playerList.get(i).getCountries());
			}
		}
		// Assigning left over countries to random players
		for (int i = 0; i < allCountryIndices.size(); i++) {
			int randomPlayer = (int) (Math.random() * playerList.size());
			System.out.println("Random Player: " + randomPlayer);
			playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(allCountryIndices.get(i)));
			playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).getCountries());
		}

		// Display owned countries of each player including player name
		for (int i = 0; i < playerList.size(); i++) {
			System.out.println("Player Name: " + playerList.get(i).getName() + " and his countries below");
			for (int j = 0; j < playerList.get(i).getCountries().size(); j++) {
				System.out.println(playerList.get(i).getCountries().get(j).getName());
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start up phase started");
		StartUpPhase start = new StartUpPhase();
	}

}
