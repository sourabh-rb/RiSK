package com.risk.model;

/**
 * Class Dice represents the dice that are rolled during the battles in the
 * game. The attacker and defender, both roll the dice and the player with
 * higher value on the dice wins the round. The number of dice rolled by a
 * player depends on the number of armies the attacking country has.
 * 
 * @author shivani
 * @version 1.0
 */
public class Dice {
	// Value showed by the dice
	private int value;

	/**
	 * This method gets the value of each dice roll a player makes.
	 * 
	 * @return value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * This method sets the value of each dice roll a player makes.
	 * 
	 * @param value
	 *            ranges from 1 to 6
	 */
	public void setValue(int value) {
		this.value = value;
	}
}
