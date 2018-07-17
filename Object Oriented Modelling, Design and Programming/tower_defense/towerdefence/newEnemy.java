package towerdefence;
/**
 * newEnemy is the subclass of enemy which health 5.
 * and move 3 step of a game step 
 * @author xm24
 */
public class newEnemy extends Enemy {
	private static int health = 5;
	private static int position = 0;
	private static int price = 200;
	private static String name = "N";
	/**
	 * build a new enemy 
	 */
	public newEnemy() {
		super(health, position, price, name);
	}
	@Override
	public void advance() {
			super.advance();
			super.advance();
			super.advance();
	}
	@Override
	public String toString() {
		return super.toString() + " and  Elephant";
	}
}