package towerdefence;

/**
 * Elephant is the subclass of enemy which health 10.
 * and move 2 game step once
 * @author xm24
 */
public class Elephant extends Enemy {
	private static int health = 10;
	private static int position = 0;
	private static int price = 50;
	private static String name = "E";
	private static int advanceTime = 1;
	/**
	 * build a new enemy named elephant.
	 */
	public Elephant() {
		super(health, position, price, name);
	}
	@Override
	public void advance() {
		if (advanceTime == 2) {
			super.advance();
			advanceTime = 1;
		} else {
			advanceTime++;
		}
	}
	@Override
	public String toString() {
		return super.toString() + " and  Elephant";
	}
}
