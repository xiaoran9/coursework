package towerdefence;
/**
 * the new tower are avaliable to.
 * @author xm24
 */
public class newTower extends Tower {
	private static int price = 200;
	private static int damage = 3;
	private static String name = "T";
	public newTower(int position) {
		super(damage, position, price, name);
	}
	@Override
	public boolean willFire(int timeStep) {
		if (timeStep % 2 == 0) {
		return true;
		}
		return false;
	}
	@Override
	public String toString() {
		return super.toString() + "  catapult  ";
	}
}
