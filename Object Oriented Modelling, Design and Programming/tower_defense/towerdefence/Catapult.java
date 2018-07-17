package towerdefence;

/**
 * @author xm24
 */
public class Catapult extends Tower {
	private static int price = 100;
	private static int damage = 5;
	private static String name = "C";
	public Catapult(int position) {
		super(damage, position, price, name);
	}
	@Override
	public boolean willFire(int timeStep) {
		if (timeStep % 3 == 0) {
		return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return super.toString() + "  catapult  ";
	}
}
