package model;

import constants.GamePhase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
			phaseName.set("INITIALIZATION");
			currentPhase = GamePhase.INITIALIZATION;
			//TODO: bind required fields.
			break;
		case REINFORCEMENT:
			phaseName.set("REINFORCEMENT");
			currentPhase = GamePhase.REINFORCEMENT;
			//TODO: bind required fields.
			break;
		case ATTACK:
			phaseName.set("ATTACK");
			currentPhase = GamePhase.ATTACK;
			//TODO: bind required fields.
			break;
		case FORTIFICATION:
			phaseName.set("FORTIFICATION");
			currentPhase = GamePhase.FORTIFICATION;
			//TODO: bind required fields.
			break;
		case END:
			phaseName.set("TURN ENDS");
			currentPhase = GamePhase.END;
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
