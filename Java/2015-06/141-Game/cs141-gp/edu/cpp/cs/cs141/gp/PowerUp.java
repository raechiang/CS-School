/**
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Group Project
 *
 * Escape the Dungeon for CS 141
 *
 * The purpose of this game is to test our ability to use object-oriented
 * programming. The game consists of multiple classes that make up the
 * different aspects incorporated. Within them are the different behaviors
 * (methods) and attributes (fields) that make up each class. This includes 
 * ActiveAgent, Briefcase, Enemy, GameEngine, Map, Player, PowerUp, and
 * UserInterface. The class ActiveAgent adjusts the positions when directed.
 * The Briefcase class determines where the briefcase will be placed. The
 * Enemy class places the enemies and stabs the player when it comes close,
 * and it contains Pursuit Mode during which enemies follow the player and
 * it is activated when Hard Mode is on. The GameEngine contains all of the
 * essentials to run the game itself. The Map keeps track of the positions
 * of the Player, Enemies, and Briefcase. The Player class keeps track of
 * the player's lives as well as the ammo. The PowerUp class contains the
 * invincibility, radar, and ammo. Lastly, the UserInterface contains all
 * menus and interaction with the operator.
 * 
 * Pink Panthers
 * Dominique Acuna
 * Rachel Chiang (Captain)
 * Danyel Gil
 * Seongmin Kim
 * Kartik Soni
 */
package edu.cpp.cs.cs141.gp;

import java.io.Serializable;
import java.util.Random;

/**
 * This class is for powerup items that the player can pick up in the game. It
 * contains code for the spawn location of items, their effects, and their positions,
 * and their related fields and methods.
 * 
 * @author Pink Panthers
 *
 */
public class PowerUp implements Serializable {
	
	/**
	 * These fields represent the possible spawning positions of the items, which are randomized
	 * in {@link #spawnPossibleLocation()} and are used to ensure the location is empty enough 
	 * to be placed.
	 */
	private int possibleRowSpawn, possibleColSpawn;
	
	/**
	 * These fields represent the actual positions of the items; the {@link #possibleRowSpawn}
	 * and {@link #possibleColSpawn} are saved after the position is verified as empty. These values
	 * can be retrieved in {@link #getItemRow()} and {@link #geItemCol()}.
	 */
	private int itemRow, itemCol;
	
	/**
	 * This field represents the {@link #itemID}, which is needed to set the effects of the specific
	 * items in {@link #setEffect()}.
	 */
	private int itemID;
	
	/**
	 * This field represents the number of turns remaining that the player is invincible, which is 
	 * defaulted at 0 and is set to 5 in {@link #setEffect()}. They are reduced every turn at
	 * {@link #reduceTurnsInvinc()} and the value can be retrieved using {@link #getTurnsInvinc()}.
	 */
	private int turnsInvincible;
	
	/**
	 * This field is defaulted at false and set to true in {@link #setEffect()} if the player picks up
	 * the appropriate item.
	 */
	private boolean isAmmo;
	
	/**
	 * This field is defaulted at false and set to true in {@link #setEffect()} if the player picks up
	 * the radar.
	 */
	private boolean isRadar;
	
	/**
	 * This constructor instantiates the items' fields: {@link #itemID}, {@link #turnsInvincible},
	 * {@link #isRadar}, and {@link #isAmmo}.
	 * @param itemID
	 */
	public PowerUp(int itemID) {
		this.itemID = itemID;
		turnsInvincible = 0;
		isRadar = false;
		isAmmo = false;
	}
	
	/**
	 * This method locates possible spawn locations for the items. It randomizes the positions,
	 * {@link #possibleRowSpawn} and {@link #possibleColSpawn}
	 */
	public void spawnPossibleLocation() {
		Random rng = new Random();
		possibleRowSpawn = rng.nextInt(8); // [0,8)
		possibleColSpawn = rng.nextInt(8) + 1; // [1,8)
	}
	
	/**
	 * After ensuring that the {@link #possibleRowSpawn} and {@link #possibleColSpawn} are empty
	 * through the GameEngine class, this saves the items' positions, setting {@link #itemRow} and
	 * {@link #itemCol}.
	 */
	public void spawnItem() {
		itemRow = possibleRowSpawn;
		itemCol = possibleColSpawn;
	}
	
	/**
	 * This method checks the position of items and the player. If it is the same, it calls
	 * {@link #setEffect()}.
	 * @param isSame is true when the player's future positions match the items' positions
	 * @param nextPlayerRow is the player's future row position
	 * @param nextPlayerCol is the player's future column position
	 */
	public void checkItemPosition(boolean isSame, int nextPlayerRow, int nextPlayerCol) {
		if (isSame) {
			if (nextPlayerRow == itemRow && nextPlayerCol == itemCol) {
				setEffect();
			}
		}
	}
	
	/**
	 * This method contains the effects of the items once the player encounters them.
	 * Depending on the {@link #itemID}, (0) sets the {@link #turnsInvincible} value to 5,
	 * (1) sets {@link #isRadar} to true, and (2) sets the {@link #isAmmo} to true.
	 */
	public void setEffect() {
		switch (itemID) {
			case 0: // invinc
				turnsInvincible = 5;
				break;
			case 1: // radar
				isRadar = true;
				break;
			case 2: // ammo
				isAmmo = true;
				break;
		}
	}
	
	/**
	 * This makes sure the items don't respawn or can't be triggered more than once by
	 * resetting the values of {@link #isRadar} to false and {@link #isAmmo} to false.
	 */
	public void resetItems() {
		isRadar = false;
		isAmmo = false;
	}
	
	/**
	 * This method simply reduces the {@link #turnsInvincible}.
	 */
	public void reduceTurnsInvinc() {
		turnsInvincible -= 1;
	}
	
	/**
	 * This method returns the {@link #itemID}, used for displaying the different
	 * items.
	 * @return {@link #itemID}
	 */
	public int getItemID() {
		return itemID;
	}
	
	/**
	 * This method returns {@link #possibleRowSpawn}
	 * @return {@link #possibleRowSpawn}
	 */
	public int getPossibleRS() {
		return possibleRowSpawn;
	}
	
	/**
	 * This method returns {@link #possibleColSpawn}
	 * @return {@link #possibleColSpawn}
	 */
	public int getPossibleCS() {
		return possibleColSpawn;
	}
	
	/**
	 * This retrieves the {@link #itemRow} of the item.
	 * @return {@link #itemRow}
	 */
	public int getItemRow() {
		return itemRow;
	}
	
	/**
	 * This retrieves the {@link #itemCol} of the item.
	 * @return {@link #itemRow}
	 */
	public int getItemCol() {
		return itemCol;
	}
	
	/**
	 * This method retrieves the number of turns invincible that are remaining.
	 * @return {@link #turnsInvincible}
	 */
	public int getTurnsInvinc() {
		return turnsInvincible;
	}
	
	/**
	 * This method retrieves whether the radar has been triggered.
	 * @return {@link #isRadar}
	 */
	public boolean getIsRadar() {
		return isRadar;
	}
	
	/**
	 * This method retrieves whether the ammo item has been triggered.
	 * @return {@link #isAmmo}
	 */
	public boolean getIsAmmo() {
		return isAmmo;
	}
	
}
