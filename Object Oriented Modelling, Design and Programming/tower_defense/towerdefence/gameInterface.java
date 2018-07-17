package towerdefence;

import java.util.Scanner;
/**
 * gameInterface is the interface of the game which recall game to build different tower defence games.
 * @author xm24
 */
public class gameInterface {
	private static Scanner read = new Scanner(System.in); 
	/**
	 * the introduction of the defence tower game.
	 */
	public static void introGame() {
		Game game;
		System.out.println("Wlecome to play the Tower Defence Game");
		System.out.println("Here is the introduction:");
		System.out.println("below is the map of the game, which is a simple corridor");
		game = new Game(10);
		game.setMap();
		game.displayMap();
		Continue();
		System.out.println("the F means the end of the corridor, when any enmies achieve the point you lose");
		System.out.println("now we will meet a enemy Rat");
		game.setRat();
		game.displayMap();
		Continue();
		System.out.println("the enemy Rat moves twice each time but its health is 1 ");
		game.advance();
		game.displayMap();
		Continue();
		System.out.println("the rat move to point 3. now you can buid a tower to defete it");
		System.out.println("because we can only beat the enemy in front of us, new we will add a setSlingShot at point 8");
		game.setTower("s", 7);
		game.displayMap();
		Continue();
		System.out.println("it will cost 50 coins of your budget, but when you kill the enemy, you can earn coins");
		System.out.println("now you earn10 coins beacse the rat dead ");
		game.advance();
		game.displayMap();
		Continue();
		System.out.println(" now we can see some other enemy:elephant");
		System.out.println("elephant move so slow but have a higer health:10. it worth 50 coins");
		game.setElephant();
		game.advance();
		game.displayMap();
		Continue();
		System.out.println("so, maybe we can add other tower in to the map");
		System.out.println("catapult is other tower,which is more expensive(100 coins),and each time it wil damage the enemy 5 point of health.");
		System.out.println("But it can only be used once ever 3 game steps");
		System.out.println("now let skip the rounds");
		game.setTower("c", 8);
		game.advance();
		game.displayMap();
		game.advance();
		game.displayMap();
		game.advance();
		game.displayMap();
		System.out.println("let's paly the game");
		mainMenu();
	}
	/**
	 * the main menu of the tower defence game.
	 */
	public static void mainMenu() {
		System.out.println("please select what you whated");
		System.out.println("N: new Game");
		System.out.println("I: introduction Gmae");
		String input = read.next();
		while (!(input.toLowerCase().equals("n") || input.toLowerCase().equals("i"))) {
			System.out.println("please input I(introduction game) or N(new Game):");
			input = read.next();
		}
		if (input.toLowerCase().equals("n")) {
			gameMenu();
			} else if (input.toLowerCase().equals("i")) {
				introGame();
			}
	}
	/**
	 * the game menu of the tower defence game.
	 */
	public static void gameMenu() {
		System.out.println("please select what you whated");
		System.out.println("game1:1");
		System.out.println("game new character:2");
		int input = read.nextInt();
		while (!(input == 1 || input == 2 || input == 3)) {
			System.out.println("please input 1 or 2:");
			input = read.nextInt();
		}
		if (input == 1) {
			gameLevel1(15);
		}
		if (input == 2) {
			gameNewCharacter(10);
		}
	}
	/**
	 * the new game with tow rats and one elephant.
	 * @param corridorLength an integer of the corridor length of this game
	 */
	public static void gameLevel1(int corridorLength) {
		Game game1 = new Game(corridorLength);
		game1.setMap();
		game1.setRat();
		game1.displayMap();
		gameStep(corridorLength, game1);
		game1.setRat();
		game1.displayMap();
		gameStep(corridorLength, game1);
		game1.setElephant();
		game1.displayMap();
		gameStep(corridorLength, game1);
		while (!game1.continues()) {
			gameStep(corridorLength, game1);
		}
		gameFinished(game1);
	}
	/**
	 * the new game with new character.
	 * @param corridorLength an integer of the corridor length of this game
	 */
	public static void gameNewCharacter(int corridorLength) {
		Game game2 = new Game(corridorLength);
		game2.setMap();
		game2.setNewEnemy();
		game2.displayMap();
		gameStep(corridorLength, game2);
		game2.setRat();
		game2.displayMap();
		gameStep(corridorLength, game2);
		while (!game2.continues()) {
			gameStep(corridorLength, game2);
		}
		gameFinished(game2);
	}
	/**
	 * build the game steps contain 3 steps set tower , make advance and dispaly map.
	 * @param corridorLength an integer of the corridor length of this game
	 * @param game Game of this game
	 */
	public static void gameStep(int corridorLength, Game game) {
		towerselected(corridorLength, game);
		game.advance();
		game.displayMap();
	}
	/**
	 * read the input of use to set a kind of tower with input position or not build it.
	 * @param corridorLength an integer of the corridor length of this game
	 * @param game Game of this game
	 */
	public static void towerselected(int corridorLength, Game game) {
		System.out.println("please select tower(S-SlingShot;  C-Catapult;  T-new Tower;  N-no Tower) :");
		String tower = read.next();
		while (cheekInput(tower)) {
			System.out.println("please only input S or C or N:");
			tower = read.next();
		}
		if (!tower.toLowerCase().equals("n")) {
			System.out.println("please select tower position(position is less than " + corridorLength + "):");
			int position = read.nextInt();
			while (validPoint(position, corridorLength)) {
				System.out.println("plase select beteen 2 to " + (corridorLength - 1));
				position = read.nextInt();
			}
			int situation = game.setTower(tower, position - 1);
			while (!towerSituation(situation)) {
			position = read.nextInt();
				while (validPoint(position, corridorLength)) {
					System.out.println("plase select beteen 2 to " + (corridorLength - 1));
					position = read.nextInt();
				}
			situation = game.setTower(tower, position - 1);
			}
		}
	}
	/**
	 * when get return situation from set tower ,display the incorrect situation to user it get a correct input.
	 * @param situation an integer of settower's situation
	 * @return return is the situation need input again
	 */
	public static boolean towerSituation(int situation) {
		if (situation == 1) {
			System.out.println("sorry this position have a tower, you should choce the other one");
			return false;
		} else if (situation == 2) {
			System.out.println("sorry you don't have enough money, you can try other one or add 999(no) stop adding tower");
		}
		return true;
	}
	/**
	 * make user the input is not out of expected.
	 * @param input a String of input
	 */
	public static boolean cheekInput(String input) {
		if (input.toLowerCase().equals("s") || input.toLowerCase().equals("c") || input.toLowerCase().equals("t") || input.toLowerCase().equals("n")) {
			return false;
		}
		return true;
	}
	/**
	 * make user the tower's position is not out of corridor or before the beginning points.
	 * @param position an integer of the tower's position
	 * @param corridorLength an integer of the corridor length of this game
	 */
	public static boolean validPoint(int position, int corridorLength) {
		if (position >= corridorLength || position <= 1) {
			return true;
		}
		return false;
	}
	/**
	 * allow user to read the text of the output and press enter to continue.
	 */
	public static void Continue() {
		   System.out.println("Press ENTER to continue...");
		   read.nextLine();
		}
	/**
	 * if the game finished find player win or not, and then ,ask if user want to play other game.
	 * @param game the game user played
	 */
	public static void gameFinished(Game game) {
		if (game.getWin()) {
			System.out.println("Congratulation! you win.");
		} else {
			System.out.println("Sorry you lose it");
		}
		System.out.println("Which one do you wanted:");
		System.out.println("1. Fishied the game");
		System.out.println("2. retrun to the game Menu");
		int input = read.nextInt();
		while (!(input == 1 || input == 2)) {
			System.out.println("please input 1(fished) or 2( Game menu):");
			input = read.nextInt();
		}
		if (input == 1) {
			System.out.println("thank you!");
		} else if (input == 2) {
			gameMenu();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mainMenu();
	}
}