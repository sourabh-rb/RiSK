package model;

import java.util.ArrayList;

import constants.GamePhase;
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
	

	
	
	private GamePhase currentPhase;
	
	private void updateGamePhase(GamePhase nextPhase)
	{
		switch(nextPhase)
		{
		case INITIALIZATION:
			currentPhase = GamePhase.INITIALIZATION;
			phaseName.set("INITIALIZATION");
			//TODO: bind required fields.
			break;
		case REINFORCEMENT:
			currentPhase = GamePhase.REINFORCEMENT;
			phaseName.set("REINFORCEMENT");
			//TODO: bind required fields.
			break;
		case ATTACK:
			currentPhase = GamePhase.ATTACK;
			phaseName.set("ATTACK");
			//TODO: bind required fields.
			break;
		case FORTIFICATION:
			currentPhase = GamePhase.FORTIFICATION;
			phaseName.set("FORTIFICATION");
			//TODO: bind required fields.
			break;
		case END:
			currentPhase = GamePhase.END;
			phaseName.set("TURN ENDS");
			//TODO: bind required fields.
			
			break;
		}
	}
	
	public PhaseManager()
	{
		playerName = new SimpleStringProperty(this, "playerName", "");
		phaseName = new SimpleStringProperty(this, "phaseName", "");
		playerName.set("Player 1");
		currentPhase = GamePhase.INITIALIZATION;
		updateGamePhase(currentPhase);
		
	}
	
	public void nextPhase()
	{
		GamePhase nextPhase = GamePhase.END;
		
		switch(currentPhase)
		{
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
	
	
	
}
