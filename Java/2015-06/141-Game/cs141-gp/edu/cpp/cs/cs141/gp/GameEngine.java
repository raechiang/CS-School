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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import edu.cpp.cs.cs141.gp.Map;
import edu.cpp.cs.cs141.gp.Player;

/**
 * This class runs the game. It calls the ActiveAgent, Briefcase, Enemy,
 * Map, Player, PowerUp, and UserInterface classes to successfully
 * run the game. It represents the communication among all of these classes
 * to fulfill the {@link #setUp()} and {@link #turn()}.
 * 
 * @author Pink Panthers
 *
 */
public class GameEngine {

	/**
	 * The following six fields create the objects by calling all of the classes.
	 */
	private UserInterface ui = new UserInterface();
	private Map theMap = new Map();
	private Player theSpy = new Player();
	private Enemy[] theNinja = {new Enemy(), new Enemy(), new Enemy(),
			new Enemy(), new Enemy(), new Enemy()}; // array of 6 enemies
	private PowerUp[] theItem = {new PowerUp(0), new PowerUp(1), new PowerUp(2)}; // array of 3 enemies
	private Briefcase brief = new Briefcase();
	
	/**
	 * This field may not have been necessary; however, it improved ease of use, especially
	 * since the main, or most frequently used, action to be used would be move. In any instance
	 * that the outer menu is not displayed, the user has the option to return to it. This is
	 * used in the {@link #playerTurn()} method. If it is true, the outer menu is displayed; otherwise,
	 * it is skipped.
	 */
	private boolean outerMenu = true;
	
	/**
	 * This field represents whether the {@link #theSpy} has been killed during the enemies' turn,
	 * {@link #enemyAttack(int)} and {@link #enemyTurn()}. The purpose is to prevent multiple
	 * deaths (sometimes resulting in Game Over) in the same turn, which would occur when the
	 * player returns to the original spawning point and an enemy or enemies are on the spawning
	 * point or directly adjacent.
	 */
	private boolean killedOnce = false;
	
	/**
	 * This boolean field is rather self-explanatory.
	 * If {@link #winGame} is false, the {@link #theSpy} has not yet found the diamond.
	 * It is only used and changed in {@link #turn()} and {@link #playerMove(int)}.
	 */
	private boolean winGame = false;
	
	/**
	 * If {@link #hardMode} is true, {@link #theNinja} will prioritize its movements
	 * according to the player. If it is false, {@link #theNinja} will always move randomly.
	 * Consequently it is used only in {@link #enemyMoveSet(int)} but is established in the
	 * beginning of a new game in {@link #setUp()}.
	 */
	private boolean hardMode = false;
	
	/**
	 * This field determines whether it is still the {@link #theSpy}'s turn, used in a loop for
	 * {@link #playerTurn()} and can be changed in {@link #playerMove(int)}, which allows the player
	 * to have multiple actions if allowed. If the value is true, then it is still the player's turn,
	 * or else it is not the player's turn.
	 */
	private boolean yourTurn;
	
	/**
	 * This integer field represents the number of turns taken so far. Originally it was used to
	 * test the loops, but it was nice to know the number of turns the player has taken, so it was
	 * kept. It is increased by one every {@link #turn()}.
	 */
	private int turnNumber;
	
	/**
	 * This method displays the welcome text and the game menu. Depending on the selection in the
	 * first game menu, it creates {@link #theMap}, sets {@link #theSpy}, {@link #theItem},
	 * {@link #theNinja}, and {@link #brief} as well as the vision when a New Game is selected.
	 * Otherwise, it will {@link #load()} the most recent save file of the game.
	 */
	public void setUp() {
		int userInput = ui.displayGameMenu();
		switch (userInput) {
			case 1: // new game
				createNewGame();
				break;
			case 2: // load game
				load();
				break;
		}
	}
	
	/**
	 * This method creates a new instance of the game. It is used in {@link #setUp()} and 
	 * {@link #load()} when loading is cancelled.
	 */
	public void createNewGame() {
		hardMode = ui.setDifficulty();
		turnNumber = 0;
		theMap.createMap();
		theMap.placePlayer(theSpy.getRow(), theSpy.getCol());
		setItems();
		setEnemies();
		setBriefCase();
		theMap.setVision();
	}
	
	/**
	 * This method makes {@link #theItem} spawn into an array of three. It checks with {@link #theMap}
	 * to find whether the square is taken already or not. If not, then the items are placed. If so, then
	 * the method continues to try to make possible spawning points.
	 */
	public void setItems() {
		for (int itemNum = 0; itemNum<3; itemNum++) {
			theItem[itemNum].spawnPossibleLocation();
			if ( theMap.getIsTaken(theItem[itemNum].getPossibleRS(), theItem[itemNum].getPossibleCS()) ) {
				// want to make sure they spawn in an empty space
				while ( theMap.getIsTaken(theItem[itemNum].getPossibleRS(), 
						theItem[itemNum].getPossibleCS()) ) {
					theItem[itemNum].spawnPossibleLocation();
				}
			}
			// item is spawned and placed on the map
			theItem[itemNum].spawnItem();
			theMap.placeItem(theItem[itemNum].getItemRow(), theItem[itemNum].getItemCol(),
								theItem[itemNum].getItemID());
		}
		
	}
	
	/**
	 * This method ensures that the enemies, {@link #theNinja}, are properly placed on {@link #theMap}.
	 * As with {@link #setItems()}, it will continuously attempt to make new possible positions
	 * if it is not empty. Otherwise, it will place the enemy.
	 */
	public void setEnemies() {
		int enemyID = 0;
		while (enemyID<6) {
			theNinja[enemyID].spawnPossibleLocation();
			// want to make sure they spawn in an empty space
			if (!(theMap.getIsTaken(theNinja[enemyID].getPossibleRow(), theNinja[enemyID].getPossibleCol()))) {
				// spawns enemy and places it on the map
				theNinja[enemyID].spawnEnemy();
				theMap.placeEnemy(theNinja[enemyID].getRow(), theNinja[enemyID].getCol());
				enemyID++;
			}
		}
	}
	
	/**
	 * This method sets {@link #brief} on {@link #theMap} in one of the nine rooms.
	 */
	public void setBriefCase(){
		// sets the briefcase on the map
		theMap.placeBriefCase(brief.getRandomCol(),brief.getRandomRow());
	}
	
	/**
	 * This method represents the turns, looping the individual {@link #playerTurn()} and
	 * {@link #enemyTurn()}. The loop continues only if both of the following are true: {@link #theSpy}
	 * still has lives and {@link #winGame} is false. {@link #enemyTurn()} only passes when the game
	 * has not been won yet. Vision is reestablished after both parties' turns end.
	 */
	public void turn() {
		// only loops if the spy has lives and if winGame is false
		while (theSpy.getLives() >= 0 && winGame != true) {
			turnNumber += 1;
			ui.displayTurnNum(turnNumber);
			
			playerTurn();
			
			if (!(winGame)) {
				enemyTurn();
			}
			theMap.setVision();
		}
	}
	
	/**
	 * This method lets {@link #theSpy} take multiple actions in the outer and directional
	 * game menus. During the times that {@link #outerMenu} is true, the user can select one of the
	 * following actions (and may repeat actions depending on the selection):
	 * (1) Move {@link #playerMove(int)}, (2) Shoot {@link #playerAttack()}, (3) Look 
	 * {@link #lookAction()}, (4) Display Stats, (5) Toggle Debug, (6) Save the Game {@link #save()},
	 * (7) Quit, (8) View Key when debug mode is on.
	 * 
	 * If (1) move is selected, {@link #playerMove(int)} is passed with the user's inputed argument for
	 * direction. {@link #yourTurn} is changed to false; as such, if the player chooses to move, that is
	 * the last move that the player may use.
	 * If (2) shoot is selected, and if there is ammo, {@link #playerAttack()} is passed and
	 * the player will shoot; if not, the player will not shoot. {@link #yourTurn} is changed to false; as
	 * such, if the player chooses to shoot, that is the last move the player may use.
	 * If (3) look is selected, the {@link #lookAction()} is passed only if the user has not yet looked that
	 * turn. The user therefore may look once per turn, but may shoot or move after.
	 */
	public void playerTurn() {
		// Allows you do multiple actions in the menus, unless yourTurn is false
		yourTurn = true;
		// Needed to make sure you only look once per turn
		boolean canLook = true;
		while (yourTurn) {
			// Only displays the map at the beginning of your turn.
			// Helps with the look command and also it feels messy and tedious to
			// display it too many times
			ui.displayMap(theMap.getPositionVal(), theMap.getVision());
			
			int actionInput = ui.selectAction(outerMenu);
			switch (actionInput) {
				case 1: // move selected
					outerMenu = false;
					int moveInput = ui.selectDirection();
					if (moveInput > 0 && moveInput < 5) {
						playerMove(moveInput);
						
					}
					else if (moveInput == 5) { // returns to the action menu
						outerMenu = true;
					}
					break;
				case 2: // Shoot selected
					if (theSpy.getAmmo() == 1) { // only works if you actually have ammo,
						playerAttack();
						yourTurn = false; // takes up one turn
					}
					else if (theSpy.getAmmo() == 0) {
						ui.displayNoShoot();
					}
					break;
				case 3: // Look selected
					if (canLook) {
						lookAction();
						canLook = false; // so you only look once per turn
					}
					else {
						ui.displayNoLook(); // doesn't let you mess up your turn if you tried and failed
					}
					break;
				case 4: // Stats Display Selected
					ui.displayStats(theSpy.getLives(), theSpy.getAmmo(), hardMode);
					ui.displayPlayerPosition(theSpy.getRow(), theSpy.getCol());
					break;
				case 5: // Debug Toggle selected
					ui.changeDebug();
					break;
				case 6: // Save Game selected
					save();
					break;
				case 7: // quit
					ui.displayQuit();
					System.exit(0);
					break;
				case 8: // key display selected
					ui.viewKey();
					break;
			}
			
		}
		
	}
	
	/**
	 * This method lets the {@link #theSpy} move and pick up items {@link #theItem} by passing
	 * {@link #checkItems(int, int)}. It also lets the player check for the briefcase {@link #brief}.
	 * If it's true, {@link #winGame} is set to true, and the game will be terminated.
	 * 
	 * Movement can only be achieved after {@link #theMap} tells the GameEngine that it is okay for the
	 * player to move to the potential position. If it is okay, the player is moved and the previous position
	 * is reset to empty in {@link #theMap}.
	 * 
	 * {@link #yourTurn} is set to false after the player successfully moves, or after the {@link #brief} has
	 * been found.
	 * 
	 * @param moveInput An integer value representing the desired direction for {@link #theSpy} to move.
	 */
	public void playerMove(int moveInput) {
		
		theSpy.move(moveInput);
		if (theSpy.getIsChangeInRange() &&
				theMap.getPositionValue(theSpy.getPossibleRow(), theSpy.getPossibleCol())!=1) {
			if (theMap.getPositionValue(theSpy.getPossibleRow(), theSpy.getPossibleCol()) !=8 ) {
				// creates possible positions
				theSpy.setPosition(theSpy.getPossibleRow(), theSpy.getPossibleCol());
				// Prints the turns invincible if you have any turns to notify you if there are any left
				if (theItem[0].getTurnsInvinc() > 0) {
					theItem[0].reduceTurnsInvinc();
					ui.displayInvulnTurns(theItem[0].getTurnsInvinc());
				}
				// checks to see if the player picks up items
				checkItems(theSpy.getRow(), theSpy.getCol());
				
				// places the player
				theMap.placePlayer(theSpy.getRow(), theSpy.getCol());
				theMap.resetPosition(theSpy.getOldRow(), theSpy.getOldCol());
				yourTurn = false;
			}
			else { // checks for briefcase
				if (moveInput == 4) {
					ui.displayWin();
					yourTurn = false;
					winGame = true;
				}
				else {
					ui.displayNoMovement();
				}
			}
		}
		else {
			ui.displayNoMovement();
		}
	}
	
	/**
	 * This method represents {@link #theSpy}'s attack. It is passed by {@link #playerTurn()}
	 * if the shoot action is selected. The method requests for the user to input the direction
	 * to shoot in and then sends it to {@link #playerShoot(int, int, int, int)}.
	 */
	public void playerAttack() {
		int direction = ui.selectDirection();
		switch (direction) {
			case 1: // up
				playerShoot(theSpy.getRow(), theSpy.getCol(), -1, 0);
				break;
			case 2: // right
				playerShoot(theSpy.getRow(), theSpy.getCol(), 0, 1);
				break;
			case 3: // left
				playerShoot(theSpy.getRow(), theSpy.getCol(), 0, -1);
				break;
			case 4: // down
				playerShoot(theSpy.getRow(), theSpy.getCol(), 1, 0);
				break;
		}
		
	}
	
	/**
	 * This method is passed by {@link #playerAttack()} and checks whether {@link #theSpy}
	 * hit {@link #theNinja}. Only the first (closest) enemy in the given direction of the player
	 * will be killed. When the player shoots, the ammo in his clip will reduce.
	 * 
	 * @param row integer value of the player's row position
	 * @param col integer value of the player's column position
	 * @param rowCheck integer value of the amount to increase or decrease by,
	 * 				depending on the direction
	 * @param colCheck integer value of the amount to increase or decrease by,
	 * 				depending on the direction
	 */
	public void playerShoot (int row, int col, int rowCheck, int colCheck) {
		boolean didHit = false;
		while (row < 9 && row >=0 && col < 9 && col >= 0) {
			if (theSpy.getAmmo() > 0) {
				if (theMap.getPositionValue(row, col) == 3) {
					for (int i = 0; i < 6; i++) {
						if (theNinja[i].getRow() == row && theNinja[i].getCol() == col) {
							theNinja[i].dies();
							theMap.resetPosition(theNinja[i].getRow(), theNinja[i].getCol());
							ui.displayShootResult();
							theSpy.reduceAmmo();
							didHit = true;
						}
					}
				}
			}
			row += rowCheck;
			col += colCheck;
		}
		if (!(didHit)) {
			theSpy.reduceAmmo();
			ui.displayNoHit();
		}
	}
	
	/**
	 * This method checks to see if {@link #theSpy} will step on {@link #theItem}. Once
	 * the player steps on the item, the appropriate effect will be triggered. It is passed
	 * in the {@link #playerMove(int)} method.
	 * 
	 * @param nextPlayerRow Integer value of the future row position of the player
	 * @param nextPlayerCol Integer value of the future column position of the player
	 */
	public void checkItems(int nextPlayerRow, int nextPlayerCol) {
		theMap.checkItemPlayerPositions(nextPlayerRow, nextPlayerCol);
		for (int itemNum = 0; itemNum<3; itemNum++) {
			// if the isItem is true, it means that the player did step on an item, and it
			// triggers the item's effect
			theItem[itemNum].checkItemPosition(theMap.getIsItem(), nextPlayerRow, nextPlayerCol);
			// if it is the invincibility item, it feeds the appropriate display
			if (theItem[itemNum].getTurnsInvinc() == 5) {
				ui.displayItemPickUp(theItem[itemNum].getItemID());
			}
			// same thing except it's for the radar
			if (theItem[itemNum].getIsRadar()) {
				ui.displayItemPickUp(theItem[itemNum].getItemID());
				ui.displayBriefcaseLocation(theMap.getBriefcaseRow(), theMap.getBriefcaseCol());
			}
			// Ammo
			if (theItem[itemNum].getIsAmmo()) {
				ui.displayItemPickUp(theItem[itemNum].getItemID());
				ui.displayReload(theSpy.getAmmo());
				// if the ammo is 1, can't reload, else can reload
				if (theSpy.getAmmo() == 0) {
					theSpy.reload();
				}
			}
			// Items are reset at the end.
			theItem[itemNum].resetItems();
		}
	}
	
	/**
	 * If the player selected to look, the vision will increase by one increment in
	 * the desired direction on {@link #theMap}, affecting the display for only that turn.
	 */
	public void lookAction() {
		int vGet = ui.selectDirection();
		theMap.setPlayerVision(true, vGet);
	}
	
	/**
	 * This method is for the {@link #theNinja}s' turns. The enemy can either move, using {@link 
	 * #enemyMove(int)} or {@link #enemyMoveSet(int)}, or attack, using {@link #enemyAttack(int)}.
	 * The loop to some extent (as the potential positions are randomly selected) prevents the enemy 
	 * to waste a turn if it hits an adjacent non-passable object because of the loop.
	 * {@link #killedOnce} is used in this method to prevent enemies from killing you more than once
	 * per the entire enemy party's turn. Furthermore, if the enemy spawns on {@link #theSpy}'s original
	 * position, the enemy will be moved to either north or east automatically, to give the user at least
	 * a fighting chance to survive.
	 */
	public void enemyTurn() {
		boolean theirTurns = true;
		int enemyID = 0;
		while (theirTurns) {
			
			// If the enemy is not dead, they can move or stab
			if ( !(theNinja[enemyID].getDeathVal()) ) {
				theNinja[enemyID].resetStab();
				theNinja[enemyID].checkPlayer(theSpy.getRow(), theSpy.getCol());
				if (theNinja[enemyID].getStab()) { // if the player is found
					killedOnce = false;
					enemyAttack(enemyID);
					if (killedOnce) {
						theirTurns = false;
					}
				}
				else { // if the player is not in an adjacent square, the enemy moves
					enemyMoveSet(enemyID);
				}
			}
			if (enemyID<5) {
				enemyID++;
			}
			else {
				theirTurns = false;
			}
		}
		
		if (killedOnce) {
			for (int i = 0; i < 6; i++) {
				if (theNinja[i].getRow() == theSpy.getRow() &&
						theNinja[i].getCol() == theSpy.getCol()) {
					// the enemy on the spawn position will move
					theNinja[i].checkDirection(1); // move it up
					if (theNinja[i].getIsChangeInRange() &&
							!(theMap.getIsTaken(theNinja[i].getPossibleRow(), 
									theNinja[i].getPossibleCol())) ) {
						enemyMove(i);
					}
					else {
						theNinja[i].checkDirection(2); // move it right
						if (theMap.getIsTaken(theNinja[i].getPossibleRow(), theNinja[i].getPossibleCol())) {
							enemyMove(i);
						}
						else {
							theNinja[i].setPosition(theNinja[i].getSpawnRow(), theNinja[i].getSpawnCol());
							theMap.placeEnemy(theNinja[i].getRow(), theNinja[i].getCol());
							theMap.resetPosition(theNinja[i].getOldRow(), theNinja[i].getOldCol());
						}
					}
					
					// and the player's position on the map will be shown
					theMap.placePlayer(theSpy.getRow(), theSpy.getCol());
				}
			}
		}
		
	}
	
	/**
	 * This method lets the game know what to do when {@link #theNinja} stabs {@link #theSpy}.
	 * If the spy is not invincible, then the appropriate {@link #theNinja} will stab the {@link #theSpy}.
	 * {@link #killedOnce} will be changed to true, which will prevent multiple player deaths in one round
	 * of enemy turns. After being stabbed, {@link #theSpy} is reset to the spawning point and its
	 * corresponding previous position on {@link #theMap} is reset to empty. Furthermore, if the player
	 * has no more lives, the game will end.
	 * 
	 * @param enemyID integer value for the array to call on the different enemies
	 */
	public void enemyAttack(int enemyID) {
		if ( theItem[0].getTurnsInvinc() == 0 ) { // if the spy is not invincible
			if (theSpy.getLives() >= 0) { // stab (-1 life)
				theSpy.loseLife();
				ui.displayDeath(theSpy.getLives());
				theSpy.resetPlayer();
				theMap.resetPosition(theSpy.getOldRow(), theSpy.getOldCol());
				// in case you kill yourself, the ninja's position can't be left empty
				theMap.placeEnemy(theNinja[enemyID].getRow(), theNinja[enemyID].getCol());
				theMap.placePlayer(theSpy.getRow(), theSpy.getCol());
				killedOnce = true;
			}
			else { // stab to death. Game ends.
				ui.displayDeath(theSpy.getLives());
				System.exit(0);
			}
		}
	}
	
	/**
	 * This method sets the movement for the enemies, and it passes {@link #enemyMove(int)}.
	 * If {@link #hardMode} is true, then they will attempt to pursue {@link #theSpy} until they either
	 * successfully kill the player or their 'Line-of-Sight' is broken.
	 * @param enemyID integer which identifies the enemy in an array
	 */
	public void enemyMoveSet(int enemyID) {
		boolean theirMove = true;
		int testedPositionsQnt = 0;
		// for the pursuitMode
		theNinja[enemyID].findPlayer(theSpy.getRow(), theSpy.getCol(), theSpy.getOldRow(), theSpy.getOldCol());
		
		while (theirMove) {
			if ( !(theNinja[enemyID].getMode()) || !(hardMode) ) { // if hardMode/pursuitMode are false
				// enemy movement is randomized
				// creates random possible move locations
				theNinja[enemyID].createMoveLocation();
				// if it is within the grid and there is no object in the way,
				if (theNinja[enemyID].getIsChangeInRange() &&
						!(theMap.getIsTaken(theNinja[enemyID].getPossibleRow(), theNinja[enemyID].getPossibleCol()) ) ) {
					enemyMove(enemyID);
					theirMove = false; // ends the loop
				}
				else {
					// More chances to move randomly if they couldn't
					testedPositionsQnt++;
					if (testedPositionsQnt > 3) { // but eventually they run out of chances.
						theirMove = false; // ends the loop
					}
				}
			}
			else if (hardMode && theNinja[enemyID].getMode()) { // Pursuit mode
				// Very similar to the above process, except instead of randomizing the direction
				// the frontDirection is already determined. It goes in the same direction as 
				// wherever the player is/was
				theNinja[enemyID].checkDirection(theNinja[enemyID].getFrontDirection());
				if (theNinja[enemyID].getIsChangeInRange() &&
						!(theMap.getIsTaken(theNinja[enemyID].getPossibleRow(), theNinja[enemyID].getPossibleCol())) ) {
					enemyMove(enemyID);
					theirMove = false;
				}
				else {
					testedPositionsQnt++;
					if (testedPositionsQnt > 3) {
						theirMove = false;
					}
				}
			}
		}
		
	}
	
	/**
	 * The enemy is moved to the possible locations. This method is passed by {@link #enemyTurn()} or
	 * {@link #enemyMoveSet(int)}. {@link #theNinja} are placed on {@link #theMap}, and the previous
	 * position of each is reset to empty on the map.
	 * @param enemyID is the index for the array that the specific enemy is found in
	 */
	public void enemyMove(int enemyID) {
		// the enemy is moved to the possible locations
		theNinja[enemyID].setPosition(theNinja[enemyID].getPossibleRow(), theNinja[enemyID].getPossibleCol());
		// places the enemy on the map and resets the previous position to empty
		theMap.placeEnemy(theNinja[enemyID].getRow(), theNinja[enemyID].getCol());
		theMap.resetPosition(theNinja[enemyID].getOldRow(), theNinja[enemyID].getOldCol());
	}
	
	/**
	 * This saves the game when the player selects this action in the method {@link #playerTurn()}.
	 * It asks for the player's desired file name and then saves it to that.
	 */
	public void save() {
		boolean invalidSave = true;
		FileOutputStream fos;
		while (invalidSave) {
			try {
				String input= ui.askFileName();
				if (input.equalsIgnoreCase("C")) {
					invalidSave = false;
				}
				else {
					invalidSave = false;
					fos = new FileOutputStream(input);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(theSpy);
					oos.writeObject(brief);
					for(int i= 0 ; i<6; i++ ){
						oos.writeObject(theNinja[i]);
					}
					for(int i= 0 ; i<3 ; i++ ) {
						oos.writeObject(theItem[i]);
					}
					oos.writeObject(theMap);
					oos.writeInt(turnNumber);
					oos.writeBoolean(hardMode);
					ui.displaySave(true);
					oos.close();
				}
			} catch (IOException e) {
				invalidSave = true;
				ui.displaySave(false);
			}
		}
	}
	
	/**
	 * This method loads a given file instance of the game. The user must input an existing
	 * file. This method is selected during {@link #setUp()}.
	 */
	public void load() {
		Player somePlayer = null;
		Briefcase someBrief = null;
		Enemy someEnemy = null;
		PowerUp someItem = null;
		Map someMap = null;
		FileInputStream fis;
		ui.displayChoice();
		boolean invalidLoad = true;
		while (invalidLoad) {
			try {
				String input = ui.askFileName();
				if (input.equalsIgnoreCase("C")) {
					invalidLoad = false;
					createNewGame();
				}
				else {
					invalidLoad = false;
					fis = new FileInputStream(input);
					ObjectInputStream ois = new ObjectInputStream(fis);
					somePlayer = (Player) ois.readObject();
					theSpy = somePlayer;
					someBrief = (Briefcase) ois.readObject();
					brief = someBrief;
					for (int i = 0; i < 6; i++) {
						someEnemy = (Enemy) ois.readObject();
						theNinja[i] = someEnemy;
					}
					for (int i = 0; i < 3; i++) {
						someItem = (PowerUp) ois.readObject();
						theItem[i] = someItem;
					}
					someMap = (Map) ois.readObject();
					theMap = someMap;
					turnNumber = (int) ois.readInt();
					hardMode = (boolean) ois.readBoolean();
					ui.displayLoad(true);
					ois.close();
				}
			} catch (IOException | ClassNotFoundException e) {
				invalidLoad = true;
				ui.displayLoad(false);
			}
		}
	}

}