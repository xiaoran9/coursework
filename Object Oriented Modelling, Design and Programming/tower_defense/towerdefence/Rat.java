package towerdefence;
/**
 * rat is the subclass of enemy which health 1.
 * and move tow step one game step 
 * @author xm24
 */
public class Rat extends Enemy {
	private static int health = 1;
	private static int position = 0;
	private static int price = 10;
	private static String name = "R";
	/**
	 * build a new enemy rat.
	 */
	public Rat() {
		super(health, position, price, name);
	}
	@Override
	public void advance() {
		super.advance();
		super.advance();
	}
	@Override
	public String toString() {
		return super.toString() + "  catapult  ";
	}
}
