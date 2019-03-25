
package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import constants.Constants;
import constants.GamePhase;
import constants.LogLevel;
import model.Card;
import model.Country;
import model.Player;

public class Utilities {

	/**
	 * This method gets the integer value for the string that is passed to it.
	 * 
	 * @param s
	 *            The string that is to be converted to integer.
	 * @return The integer value.
	 */
	public static Integer getIntegerValue(String s) {
		StringBuilder sb = new StringBuilder();
		for (char c : s.toCharArray())
			sb.append((int) c);

		Integer mInt = new Integer(sb.toString());
		return mInt;
	}

	/**
	 * This method is used to maintain the logs in a text file for the game.
	 * 
	 * @param message
	 */
	public static void gameLog(String message, LogLevel l) {
		String FilePath = ".//Logs//game_log.txt";
		try {
			Utilities.createDirectoryIfNotExist(".//Logs");
			File logFile = new File(FilePath);
			logFile.createNewFile();
			BufferedWriter wr = new BufferedWriter(new FileWriter(logFile, true));

			System.out.println(l + ": " + message);
			wr.newLine();
			wr.write(l + ": " + message);
			wr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method creates a directory to save the text file if it does not exist.
	 * 
	 * @param path
	 */
	public static void createDirectoryIfNotExist(String path) {
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("Creating dir" + path);
			System.out.println(file.mkdirs());

		}
	}

	/**
	 * This method is used to fetch the country object from the layers list by
	 * comparing the country with the country name passed.
	 * 
	 * @param player
	 * @param countryName
	 * @return
	 */
	public static Country getCountryFromPlayer(Player player, String countryName) {
		for (Country country : player.getCountries()) {
			if (countryName.equals(country.getName())) {
				return country;
			}
		}
		return null;
	}

	/**
	 * This method is used to get all the neighbor countries of the country passed
	 * that are owned by the player
	 * 
	 * @param player
	 * @param country
	 * @return
	 */
	public static ArrayList<Country> getNeighborList(Player player, Country country) {
		ArrayList<Country> neighborList =new ArrayList<>();
		for (Country neighbor : country.getNeighborCounties()) {
			if (neighbor.getOwner().equals(player)) {
				neighborList.add(neighbor);
			}
		}
		return neighborList;
	}

	/**
	 * This method is used to identify if number of armies that the player has
	 * entered for the reinforcement or fortification phase is validor not
	 * 
	 * @param userInput
	 * @param armies
	 *            For reinforcement phase it is the number of armies the player is
	 *            left with to deploy, for fortification phase it is the number of
	 *            armies he has in the selected country.
	 * @param phase
	 * @return true if the number is valid, else false
	 */

	public static boolean isUserInputValid(int userInput, int armies, GamePhase phase) {

		if (phase.equals(GamePhase.REINFORCEMENT) || phase.equals(GamePhase.INITIALIZATION)) {
			if (userInput > 0 && userInput <= armies) {
				return true;
			} else {
				return false;
			}
		} else if (phase.equals(GamePhase.FORTIFICATION)) {
			if (userInput > 0 && userInput <= armies - 1) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}


	public static boolean isFortificationPossible(Player player) {
		// ArrayList<Country> neighborList=null;
		for (Country country : player.getCountries()) {
			if (country.getNeighborCounties() != null && country.getArmies() > 1) {
				for (Country neighbor : country.getNeighborCounties()) {
					if (neighbor.getOwner().equals(player)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * This method is used to retrieve the enemy countries
	 * 
	 * @param player
	 * @param country
	 * @return
	 */
	public static ArrayList<Country> getEnemyNeighborList(Player player, Country country) {
		ArrayList<Country> neighborList =new ArrayList<>();
		for (Country neighbor : country.getNeighborCounties()) {
			if (!(neighbor.getOwner().equals(player))) {
				neighborList.add(neighbor);
			}
		}
		return neighborList;
	}
  /**
	 * This method returns the card that is given to the player after he wins an attack.
	 * @return The card that will be given to the player.
	 */
	public static Card giveCard() {
		Random random = new Random();
		Card card=new Card();
		int randomNum = 1 + random.nextInt((2) + 1);
		if(randomNum==1) {
			card.setType(Constants.INFANTRY);
		}else if(randomNum==2) {
			card.setType(Constants.CAVALRY);
		}else {
			card.setType(Constants.ARTILLERY);
		}
		return card;
	} 
}
