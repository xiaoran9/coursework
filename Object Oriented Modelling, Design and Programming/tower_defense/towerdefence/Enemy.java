package towerdefence;

/**
 * @author xm24
 */
public abstract class Enemy {
	private int health;
	private int position;
	private int price;
	private String name;
	/**
	 * set a new enemy with health.
	 * @param health a integer seen as the health of enemy
	 * @param position an integer of the position
	 * @param price an integer of the price of enemy
	 * @param name a string of the name of enemy
	 */
	public Enemy(int health, int position, int price, String name) {
		this.health = health;
		this.position = position;
		this.price = price;
		this.name = name;
	}
	/**
	 * get the name of enemy.
	 * @return return the name of the enemy 
	 */
	public String getName() {
		return name;
	}
	/**
	 * get the price of enemy.
	 * @return return the price of the enemy 
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * get the current health of enemy.
	 * @return return the health of the enemy 
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * get the current position of the enemy.
	 * @return return the position of the enemy 
	 */
	public int getPosition() {
		return position;
	}
	/**
	 * set the position of the enemy.
	 * @param position an integer to represent the position of the enemy 
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	/**
	 * reduce the health when the enemy was hit by tower.
	 * @param t tower which hit the enemy 
	 */
	public void hit(Tower t) {
		if (t.getPosition() >= this.position) {
			health -= t.getDamage();
			if (health < 0) {
			 health = 0;
			}
		}
	}
	/**
	 * the advance ot enemy is move forward once.
	 */
	public void advance() {
		this.position += 1;
	}
	@Override
	public String toString() {
		return "Enemy postion" + position + " and health " + health;
	}
}
