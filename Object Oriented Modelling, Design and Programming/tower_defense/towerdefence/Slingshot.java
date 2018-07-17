package towerdefence;

/**
 * Sligshot is the subclass of Tower which shot one game time once.
 * damage enemy 1 health once
 * @author xm24
 */
public class Slingshot extends Tower {
	private static int price = 50;
	private static int damage = 1;
	private static String name = "S";
	/**
	 * build a new sling shot tower with it's damage position price and name.
	 * @param position an integer of the tower's position
	 */
	public Slingshot(int position) {
		super(damage, position, price, name);
	}
	@Override
	public String toString() {
		return super.toString() + "  catapult  ";
	}
}
