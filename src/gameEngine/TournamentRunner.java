package gameEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import constants.Constants;
import java.util.List;

public class TournamentRunner {
	
	ArrayList<String> Maps=null;
	ArrayList<String> Players=null;
	int gamesToPlay;
	int noOfTurns;
	int noOfPlayers;
	static StartUpPhase currentStartUpPhase;
	static PhaseManager currentPlayerPhase;
	HashMap<String,String> gameResults;
	
	public TournamentRunner(ArrayList<String> maps,ArrayList<String> players,int gamesToPlay,int noOfTurns)
	{
		this.Maps=maps;
		this.Players=players;
		this.gamesToPlay=gamesToPlay;
		this.noOfTurns=noOfTurns;
		this.noOfPlayers= 5-Collections.frequency(Players, "null");
		
		this.gameResults=new HashMap<String,String>();
		
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public StartUpPhase mapDataLoader(String file) throws IOException
	{
		String line;
		String fileContents = "";
		List<Object> mapValidation;
		String errorMessage;
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

			while ((line = reader.readLine()) != null)
				fileContents = fileContents + line + "\n";

		} catch (IOException e) {
			e.printStackTrace();
		}
		HashMap<String,Integer> continentHashMap = new HashMap<String,Integer>();
		HashMap<String,ArrayList<String>> territoryHashMap = new HashMap<String,ArrayList<String>>();
		ValidateGraph gt=new ValidateGraph();
		mapValidation = gt.initiateCheck(fileContents.toString());
		errorMessage = mapValidation.get(0).toString();
		if(errorMessage.equals("Success"))
		{
			continentHashMap = (HashMap<String,Integer>)mapValidation.get(1);
			territoryHashMap = (HashMap<String,ArrayList<String>>)mapValidation.get(2);
			StartUpPhase startPhase = StartUpPhase.getInstance();
			startPhase.mappingElements(continentHashMap, territoryHashMap, noOfPlayers);
			return startPhase;
			
		}
		return null;
		
	}
	 
	public void run() throws IOException
	{
		
		
		for(String map:Maps)
		{
			currentStartUpPhase=mapDataLoader(map);
			currentPlayerPhase = new PhaseManager(currentStartUpPhase);
			for(int gameNo=0;gameNo<gamesToPlay;gameNo++)
			{
				gameResults.put(map,playGame());
			}
			
			
		}
		
		
		
		
		
	}
	
	public String strategyDecider(String playerStrategy)
	{
		if(playerStrategy.contentEquals("Aggressive")) return Constants.AGGRESSIVE;
		else if(playerStrategy.contentEquals("Benevolent")) return Constants.BENEVOLENT;
		else if(playerStrategy.contentEquals("Human")) return Constants.HUMAN;
		else if(playerStrategy.contentEquals("Random")) return Constants.RANDOM;
		else return Constants.CHEATER;
		
		
	}
	
	public String playGame() {
		String playerStrategy;
		
		for(int turnNo=0;turnNo<noOfTurns;turnNo++)
		{
			playerStrategy=Players.get(turnNo/noOfPlayers);
			currentPlayerPhase.getCurrentPlayer().reinforceArmies(null,0,strategyDecider(playerStrategy));
			
			
			
			
		}
		
		return null;
		
	}

}
