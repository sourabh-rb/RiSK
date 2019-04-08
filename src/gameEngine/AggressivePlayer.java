package gameEngine;

import java.util.ArrayList;

import utilities.Utilities;

public class AggressivePlayer extends Player{
	static AggressivePlayer aggPlayer;
	static StartUpPhase start;
	Player player1= new Player();
	TurnManager turn = new TurnManager();
	public String attack(Country strongestCountryToAttack, Country defendingCountry, int noOfDiceForAttackingCountry,
			int noOFDiceForDefendingCountry, String action) {
		ArrayList<Country> enemyCountriesList =null;
		int noOfSuccesfullAttacks = 0;
		System.out.println("Inside AggressivePlayer attack method");
		String attackRes = null;
		System.out.println("Number of countries attacker has before attacking: "+strongestCountryToAttack.getOwner().getCountries().size());
		if (strongestCountryToAttack.getArmies() >= 2) {
			
			enemyCountriesList = start.getEnemyList(strongestCountryToAttack.getOwner(), strongestCountryToAttack);
			for(Country enemyCountry:enemyCountriesList) {
				
			}
				attackRes = noOfDiceOnAllOut(strongestCountryToAttack, defendingCountry);
				if (!attackRes.equals("onlyOneArmy")) {
					if (attackRes.equals("defeated")) {
						
					}
				}
				else
					System.out.println("Strongest attacking country cannot attack anymore because it has left with only one army");
				

			System.out.println("Result of attacking : "+attackRes);
			if (attackRes.equals("defeated")) {
				defendingCountry.setOwner(strongestCountryToAttack.getOwner());// change the defeated country owner to attacked country owner
				strongestCountryToAttack.getOwner().getCountries().add(defendingCountry); //assign the defeated country to winner
				System.out.println("Number of countries attacker has after attacking: "+strongestCountryToAttack.getOwner().getCountries().size());
				System.out.println("Number of total countries in the game: "+start.getCountryList().size());
				if(strongestCountryToAttack.getOwner().getCountries().size() == start.getCountryList().size()) {
					return "champion";
				}
				// can give this player a card
			 cardTypeList = strongestCountryToAttack.getOwner().getCardType();

				cardTypeList.add(Utilities.giveCard());
				strongestCountryToAttack.getOwner().setCardType(cardTypeList);
				cardTypeList.clear();
				
				// ASk player if wants to continue attacking
				
				System.out.println("Defeated the country");
				return "defeated";
				// If player do not want to continue, he must fortify
			} else if (attackRes.equals("notDefeated")) {
				// ASk player if wants to continue attacking
				System.out.println("not Defeated the country yet");
				return "notdefeated";
			} else if (attackRes.equals("onlyOneArmy")) {
				System.out.println("Only one army left in the attacking country So, cannot attack");
				// ASk player if wants to continue attacking with another country
				return "onlyOneArmy";
			}
		} else
			System.out.println("cannot attack because there should be atleast 2 armies in the attacking country");
		// ASk player if wants to continue attacking with another country
		return "cannotAttack";

	}
	
	public static void main(String[] args) {
		 aggPlayer = new AggressivePlayer();
		 start = new StartUpPhase();
	}
}
