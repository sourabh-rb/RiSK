package gameEngine;

import java.util.ArrayList;

import constants.GamePhase;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
* <h1>PhaseManager</h1>
* The PhaseManager is responsible for managing
* the game phases:
* initialization phase, reinforcement phase, attack phase, and fortification phase.
*
* @author  Sourabh Rajeev Badagandi
* @version 1.0.0
*
*/
public class PhaseManager
{
	private StringProperty playerName;
	private StringProperty phaseName;
	private ObservableList<Country> countriesOwned;
	private ObservableList<Country> neighbourCountriesOwned;
	private ObservableList<Country> attackableCountries;

	private ObservableList<Integer> cardTypeCountList;
	private TurnManager turnManager;
	private IntegerProperty infantryCardCount;
	private IntegerProperty cavalryCardCount;
	private IntegerProperty artilleryCardCount;
	
	private IntegerProperty armyCount;
	private IntegerProperty armyLeft;
	private IntegerProperty armyInCountry;
	private IntegerProperty totalCardsCount;

	private Player currentPlayer;
	
	private StartUpPhase startUpPhaseObject;
	
	private GamePhase currentPhase;
	
	/**
	 * This method is used to update the current phase of the game with the nextPhase
	 * @param nextPhase
	 */
	
	private void updateGamePhase(GamePhase nextPhase)
	{
		switch(nextPhase)
		{
		case INITIALIZATION:
			currentPhase = GamePhase.INITIALIZATION;
			phaseName.set("INITIALIZATION");
			//TODO: bind required fields.
			countriesOwned = FXCollections.observableArrayList(currentPlayer.getCountries());
			armyCount.set(currentPlayer.getArmies());
			armyLeft.set(currentPlayer.getNumberOfArmiesLeft());
			break;
		case REINFORCEMENT:
			currentPhase = GamePhase.REINFORCEMENT;
			phaseName.set("REINFORCEMENT");
			//TODO: bind required fields.
			countriesOwned = FXCollections.observableArrayList(currentPlayer.getCountries());
			armyCount.set(currentPlayer.getArmies());

			cardTypeCountList = FXCollections.observableArrayList(currentPlayer.cardCount());
			
			System.out.println(cardTypeCountList);
			System.out.println(cardTypeCountList.get(2));
			artilleryCardCount.set(cardTypeCountList.get(0).intValue());
			infantryCardCount.set(cardTypeCountList.get(1).intValue());
			cavalryCardCount.set(cardTypeCountList.get(2).intValue());
			
			totalCardsCount.set(cardTypeCountList.get(0)+cardTypeCountList.get(1)+cardTypeCountList.get(2));			

			break;
		case ATTACK:
			currentPhase = GamePhase.ATTACK;
			phaseName.set("ATTACK");
			//TODO: bind required fields.
			countriesOwned = FXCollections.observableArrayList(currentPlayer.getCountries());
			armyCount.set(currentPlayer.getArmies());
			//armyLeft.set(0);
			
			//attackable countries
			break;
		case FORTIFICATION:
			currentPhase = GamePhase.FORTIFICATION;
			phaseName.set("FORTIFICATION");
			//TODO: bind required fields.
			
			//neighbor countries owned
			break;
		case END:
			currentPhase = GamePhase.END;
			phaseName.set("TURN ENDS");
			//TODO: bind required fields.
			//Change player here !! @Charan
			System.out.println("Update game phase");
			currentPlayer=getNextPlayer();
        System.out.println(currentPlayer.getName());
			                       playerName.set(currentPlayer.getName());
			                       updateGamePhase(GamePhase.INITIALIZATION);
			                       //nextPhase();
			break;
		}
	}
	
	/**
	 * Phase manager constructor
	 */
	public PhaseManager()
	{
		startUpPhaseObject = StartUpPhase.getInstance();
		playerName = new SimpleStringProperty();
		phaseName = new SimpleStringProperty(this, "phaseName", "");
		turnManager=new TurnManager();
		infantryCardCount = new SimpleIntegerProperty();
		cavalryCardCount = new SimpleIntegerProperty();
		artilleryCardCount = new SimpleIntegerProperty();
		armyCount = new SimpleIntegerProperty();
		armyLeft = new SimpleIntegerProperty();
		armyInCountry = new SimpleIntegerProperty();
		totalCardsCount = new SimpleIntegerProperty();
		playerName.set("Player1");

		currentPhase = GamePhase.START;
		nextPhase();
		
		
	}
	
	/**
	 * This method is used to implement the next phase for the current player
	 */
	public void nextPhase()
	{
		int leftCount=0;
		GamePhase nextPhase = GamePhase.END;
		currentPlayer = getCurrentPlayer();
		switch(currentPhase)
		{
		case START:
			nextPhase = GamePhase.INITIALIZATION;
			break;
		case INITIALIZATION:
			//TODO:Check if initialization using round robin has been completed then go to reinforce.
			for(Player playerObj: startUpPhaseObject.getPlayerList())
			{
                   leftCount+=playerObj.getNumberOfArmiesLeft();

			}

			System.out.println("Printing left count "+leftCount);

			if(leftCount==0) nextPhase = GamePhase.REINFORCEMENT;
			else nextPhase = GamePhase.END;
			break;
			
		case REINFORCEMENT:
			nextPhase = GamePhase.ATTACK;
			break;
			
		case ATTACK:
			nextPhase = GamePhase.FORTIFICATION;
			break;
			
		case FORTIFICATION:
			nextPhase = GamePhase.FORTIFICATION;
			
			break;
			
		case END:
			//choose next player
			break;
		}
		
		updateGamePhase(nextPhase);
		
	}
	public Player getNextPlayer()
	{
		return turnManager.nextPlayer(playerName.get());
	}
	
	public Player getCurrentPlayer()
	{

		return turnManager.currentPlayer(playerName.get());

	}
	public GamePhase getCurrentGamePhase()
	{
		return currentPhase;
	}
	public StringProperty playerNameProperty()
	{
		return playerName;
	}
	
	public StringProperty phaseNameProperty()
	{
		return phaseName;
	}
	
	public ObservableList<Country> getCountriesOwnedObservableList()
	{
		return countriesOwned;
	}
	
	public ObservableList<Country> neighbourCountriesOwned()
	{
		return neighbourCountriesOwned;
	}
	
	public ObservableList<Country> attackableCountries()
	{
		return attackableCountries;
	}
	

	public ObservableList<Integer> cardTypeCountList()
	{
		return cardTypeCountList;
	}
	

	public IntegerProperty armyCountProperty()
	{
		return armyCount;
	}
	
	public IntegerProperty armyLeftProperty()
	{
		return armyLeft;
	}
	
	public IntegerProperty armyInCountryProperty()
	{
		return armyInCountry;
	}
	

	public IntegerProperty infantryCardCountProperty()
	{
		return infantryCardCount;
	}
	
	public IntegerProperty cavalryCardCountProperty()
	{
		return cavalryCardCount;
	}
	
	public IntegerProperty artilleryCardCountProperty()
	{
		return artilleryCardCount;
	}
	
	public IntegerProperty totalcardsCountProperty()
	{
		return totalCardsCount;
	}
	
	public int cavalryCardCountForSpinner()
	{
		return cavalryCardCount.intValue();
	}
	
	public int infantryCardCountForSpinner()
	{
		return infantryCardCount.intValue();
	}
	
	public int artilleryCardCountForSpinner()
	{
		return artilleryCardCount.intValue();
	}
	
	/**
	 * Calls the method used to retrieve armies in exchange for cards
	 * @param a artillery spinner value	
	 * @param i infantry spinner value	
	 * @param c cavalry spinner value	
	 * @return armycount
	 */
	public int getArmiesForCards(int a,int i,int c)
	{
		System.out.println(c);
		return currentPlayer.armiesFromCardExchange(a,i,c);
	}
	
	
}
