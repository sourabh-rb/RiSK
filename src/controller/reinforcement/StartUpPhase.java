/**
 * 
 */
package controller.reinforcement;

import java.util.ArrayList;
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
	static int noOfPlayers = 3;

	/**
	 * 
	 */
	public StartUpPhase() {
		System.out.println("Inside startupphase constructor");
		Player player1 = new Player();
		Player player2 = new Player();
		Player player3 = new Player();
		player1.setName("Aravind");
		player2.setName("charan");
		player3.setName("shivani");
		
		ArrayList<Player> playerList = new ArrayList<Player>();
		playerList.add(player1);
		playerList.add(player2);
		playerList.add(player3);

		Continent continent1 = new Continent();
		Country country1 = new Country();
		country1.setName("India");
		Country country2 = new Country();
		country2.setName("china");
		Country country3 = new Country();
		country3.setName("pakistan");
		Country country7 = new Country();
		country7.setName("japan");

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
		continent1.setCountriesComprised(countryList);

		ArrayList<Country> europeCountryList = new ArrayList<Country>();
		europeCountryList.add(country4);
		europeCountryList.add(country5);
		europeCountryList.add(country6);
		continent2.setCountriesComprised(europeCountryList);
		
		continentList.add(continent1);
		continentList.add(continent2);

		initialSetUp(noOfPlayers, playerList, continentList, countryList);

	}

	/**
	 * @param player player object has all the player details
	 */
	public void initialSetUp(int noOfPlayers, ArrayList<Player> playerList, ArrayList<Continent> continentList,ArrayList<Country> countryList) {
		noOfPlayers = playerList.size();
		int numberOfArmiesEach = 0;
		int noOfTotalCountries = 0;
		ArrayList<Country> listOfAllCountries = new ArrayList<Country>();
		System.out.println("Number of continents: "+continentList.size());
		for(int i=0;i<continentList.size();i++) {
			for(int j=0;j<continentList.get(i).getCountriesComprised().size();j++) {
				listOfAllCountries.add(continentList.get(i).getCountriesComprised().get(j));
				noOfTotalCountries = noOfTotalCountries+1;
			}
		}
		System.out.println("No Of total Countries"+noOfTotalCountries);
		 numberOfArmiesEach = calculateNoOfArmies(noOfPlayers);
		 //looping through all players to give equal number of armies
		 for (int i = 0; i < playerList.size(); i++) {
			 playerList.get(i).setArmies(numberOfArmiesEach);
			 System.out.println(playerList.get(i).getArmies());
		 }
	
		setRandomCountriesForEach(listOfAllCountries, continentList, noOfPlayers,playerList);
		
		//Assigning an army to each of the countries of a particular player and calculating the remaining armies under each player
		
			for(int j=0;j<playerList.size();j++) {
				 playerList.get(j).setNumberOfArmiesLeft(numberOfArmiesEach);
				System.out.println("Player name: "+playerList.get(j).getName());
				System.out.println(playerList.get(j).getName()+" is assigned with " +numberOfArmiesEach +" armies" );
				for(int k=0;k<playerList.get(j).getCountries().size();k++) {
					playerList.get(j).getCountries().get(k).setArmies(1);
					System.out.println("1 army given to "+playerList.get(j).getCountries().get(k).getName());
					int previousNumberOfArmiesLeft = playerList.get(j).getNumberOfArmiesLeft();
					playerList.get(j).setNumberOfArmiesLeft(--previousNumberOfArmiesLeft);
					System.out.println("No of armies left"+playerList.get(j).getNumberOfArmiesLeft());
				}
				
			}
			
		}
		

	/**
	 * @param noOfPlayers
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

	public void setRandomCountriesForEach(ArrayList<Country> listOfAllCountries,ArrayList<Continent> continentList, int noOfPlayers,ArrayList<Player> playerList) {
	
		for(int j=0;j<listOfAllCountries.size();j++) {
			ArrayList<Country> randomCountryList = new ArrayList<Country>();
			int randomPlayer = (int) (Math.random() * playerList.size());
			System.out.println("Random Number: "+randomPlayer);
			//System.out.println(playerList.get(randomPlayer).getCountries());
			 playerList.get(randomPlayer).getCountries().add(listOfAllCountries.get(j));
			playerList.get(randomPlayer).setCountries(playerList.get(randomPlayer).getCountries());
		}
		/*
		for(int i=0;i<noOfPlayers-1;i++) {
			for(int j=0;j<listOfAllCountries.size()/noOfPlayers-1;j++) {
		
				int randomCountry = (int) (Math.random() * listOfAllCountries.size());
				
				playerList.get(i).getCountries().add(listOfAllCountries.get(randomCountry));
				playerList.get(i).setCountries(playerList.get(i).getCountries());
			}
		}
	
		HashSet hs=new HashSet();
		while(hs.size()<listOfAllCountries.size()){
		int randomCountry=(int)(Math.random()*listOfAllCountries.size());
		hs.add(randomCountry);
		}
		Iterator it=hs.iterator();
		while(it.hasNext()){
		System.out.println(it.next());
		}
		*/
		for(int i=0;i<playerList.size();i++) {
			System.out.println("Player Name: "+playerList.get(i).getName()+" and his countries below");
			for(int j=0;j<playerList.get(i).getCountries().size();j++) {
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
