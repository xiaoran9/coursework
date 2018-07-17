package towerdefence;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * @author xm24
 */
public class Game {
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<Tower> towers = new ArrayList<Tower>();
	private ArrayList<String> map = new ArrayList<String>();
	private Account money = new Account();
	private int corridorLength;
	private boolean win = false;
	/**
	 * build a new game with corridorLength.
	 * @param corridorLength an integer of the corridor length of a new game
	 */
	public Game(int corridorLength) {
		this.corridorLength = corridorLength;
	}
	/**
	 * the advance of each tower and enemy
	 */
	public void advance() {
		 Iterator<Enemy> e = enemies.iterator();
		while (e.hasNext()) {
			Enemy enemy = e.next();
			for (Tower t:towers) {
				if (t.willFire(t.getTimeStep())) {
					enemy.hit(t);
				}
				t.addTimeStep();
			}
			if (enemy.getHealth() != 0) {
				beforeMoved(enemy);
				enemy.advance();
				afterMoved(enemy);
			} else {
				e.remove();
				map.remove(enemy.getPosition());
				map.add(enemy.getPosition(), "  _  ");
				money.earn(enemy.getPrice());
			}
			if (enemy.getPosition() >= corridorLength - 1) {
				enemy.setPosition(corridorLength - 1);
			}
		}
	}
	/**
	 * check user win or lose or not both.
	 * @return return whether the game con continue or stop
	 */
	public boolean continues() {
		if (enemies.isEmpty()) {
			win = true;
			return true;
		}
		for (Enemy e:enemies) {
			if (e.getPosition() == corridorLength - 1) {
				return true;
			}
		}
		return false;
	}
	/**
	 * the win state of game.
	 * @return return if the player win or not
	 */
	public boolean getWin() {
		return win;
	}
	/**
	 * add an enemy named newenemy  to ArrayList of enemy and map.
	 */
	public void setNewEnemy() {
		newEnemy newE = new newEnemy();
		enemies.add(newE);
		map.remove(0);
		map.add(0, newE.getName());
	}
	/**
	 * add a rat to ArrayList of enemy and map.
	 */
	public void setRat() {
		Rat rat = new Rat();
		enemies.add(rat);
		map.remove(0);
		map.add(0, rat.getName());
	}
	/**
	 * add an elephant to ArrayList of enemy and map.
	 */
	public void setElephant() {
		Elephant elephant = new Elephant();
		enemies.add(elephant);
		map.remove(0);
		map.add(0, elephant.getName());
	}
	/**
	 * depend on the user's input to set towers. meanwhile check if the money is enough.
	 * @param tower a string character to represent a kind of tower 
	 * @param position an integer of a position in the map
	 * @return return integer to represent situation such as no money or this position have other tower or set succeed
	 */
	public int setTower(String tower, int position) {
		String input = tower.toLowerCase();
		Tower t = null;
		int situation = 0;
		switch (input) {
		case "s":
			t = new Slingshot(position);
			break;
		case "c":
			t = new Catapult(position);
			break;
		case "t":
			t = new newTower(position);
			break;
			}
		for (Tower check:towers) {
			if (check.getPosition() == position) {
				situation = 1;
				return situation;
			}
		}
		if (money.checkAccount(t.getPrice())) {
			towers.add(t);
			money.spend(t.getPrice());
			map.remove(position);
			map.add(position, t.getName());
		} else {
			situation = 2;
		}
		return situation;
	}
	/**
	 * before an enemy moved delete it from the map.
	 * @param position an integer of a position in the map
	 * @return return boolean to represent if the position have a tower
	 */
	public boolean checkpostion(int position) {
		for (Tower t:towers) {
			if (t.getPosition() == position) {
				return false;
			}
		}
		return true;
	}
	/**
	 * before an enemy moved delete it from the map.
	 * @param e a enemy
	 */
	public void beforeMoved(Enemy e) {
		map.remove(e.getPosition());
		map.add(e.getPosition(), "  _  ");
	}
	/**
	 * after an enemy moved replace it in the map.
	 * @param e a enemy
	 */
	public void afterMoved(Enemy e) {
		map.remove(e.getPosition());
		map.add(e.getPosition(), e.getName());
	}
	/**
	* set the initial map arrlyList without enemies and towers.
	*/
	public void setMap() {
		for (int i = 0; i < corridorLength - 1; i++) {
			map.add(i, "  _  ");
		}
		map.add(corridorLength - 1, "  F");
	}
	/**
	* Display the current map for user.
	*/
	public void displayMap() {
		System.out.println("");
		for (int i = 0; i < corridorLength; i++) {
				System.out.print(map.get(i));
		}
		System.out.println("");
		System.out.println("money:" + Account.getBudget());
		System.out.println("");
	}
}
