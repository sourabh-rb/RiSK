package model;

import java.util.ArrayList;

import constants.GamePhase;
import controller.initialization.StartUpPhase;
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
	private IntegerProperty armyCount;
	private IntegerProperty armyLeft;
	private IntegerProperty armyInCountry;
	private Player currentPlayer;
	
	private StartUpPhase startUpPhaseObject;
	

	
	
	private GamePhase currentPhase;
	
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
			//armyLeft.set();
			break;
		case REINFORCEMENT:
			currentPhase = GamePhase.REINFORCEMENT;
			phaseName.set("REINFORCEMENT");
			//TODO: bind required fields.
			countriesOwned = FXCollections.observableArrayList(currentPlayer.getCountries());
			armyCount.set(currentPlayer.getArmies());
			//armyLeft.set(0);
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
			
			//neighbour countries owned
			break;
		case END:
			currentPhase = GamePhase.END;
			phaseName.set("TURN ENDS");
			//TODO: bind required fields.
			//Change player here !! @Charan
			break;
		}
	}
	
	public PhaseManager()
	{
		startUpPhaseObject = StartUpPhase.getInstance();
		playerName = new SimpleStringProperty(this, "playerName", "");
		phaseName = new SimpleStringProperty(this, "phaseName", "");
		armyCount = new SimpleIntegerProperty();
		armyLeft = new SimpleIntegerProperty();
		armyInCountry = new SimpleIntegerProperty();
		playerName.set("Player 1");
		currentPhase = GamePhase.START;
		nextPhase();
		
	}
	
	public void nextPhase()
	{
		GamePhase nextPhase = GamePhase.END;
		currentPlayer = getCurrentPlayer();
		switch(currentPhase)
		{
		case START:
			nextPhase = GamePhase.INITIALIZATION;
			break;
		case INITIALIZATION:
			//TODO:Check if initialization using round robin has been completed then go to reinforce.
			nextPhase = GamePhase.REINFORCEMENT;
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
	
	private Player getCurrentPlayer()
	{
		return startUpPhaseObject.player_List.get(0);
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
	
	
	
}
