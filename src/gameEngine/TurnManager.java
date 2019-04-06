package gameEngine;

import java.util.*;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import utilities.Utilities;

public class TurnManager {
	private StartUpPhase startUpPhase;
	private Player currentPlayerObj;
	
	
	public TurnManager() {
		startUpPhase=StartUpPhase.getInstance();
		currentPlayerObj=startUpPhase.getPlayerList().get(0);
	}
	
	
	public TurnManager(StartUpPhase sObj)
	{
		startUpPhase=sObj;
		System.out.println(startUpPhase.getPlayerList().size());
		currentPlayerObj=startUpPhase.getPlayerList().get(0);
		
	}
	
	public  Player currentPlayer(String currentPlayer )
	{
		
		for(int i=0;i<startUpPhase.getPlayerList().size();i++)
		{
			if(currentPlayer.contentEquals(startUpPhase.getPlayerList().get(i).getName())) 
				{
				currentPlayerObj=startUpPhase.getPlayerList().get(i);
				}
		}
		
		return currentPlayerObj;
		
		
	}
	
	public Player nextPlayer(String currentPlayer)
	{
		int currentPlayerIndex;
		System.out.println("Pritnignnnnn"+currentPlayer);
		currentPlayerObj=currentPlayer(currentPlayer );
		currentPlayerIndex=startUpPhase.getPlayerList().indexOf(currentPlayerObj);
		
		System.out.println("next player function "+currentPlayerIndex);
		
		if(currentPlayerIndex==startUpPhase.getPlayerList().size()-1) return startUpPhase.getPlayerList().get(0);
		
		else return startUpPhase.getPlayerList().get(currentPlayerIndex+1);
		
	}
		
	

}