package towerdefence;
/**
 * the initialise of a tower
 * @author xm24
 */
public abstract class Tower {
private int damage;
	private int position;
	private int price;
	private String name;
	private int timeStpe;
	/**
	* set a new tower with the damage, position, price, name, and initialise the time steps.
	* @param damage a integer represent the damage of this tower
	* @param position a integer represent the position of this tower
	* @param price a integer represent a price of this tower
	* @param name a string character to present the name of this tower
	*/
	public Tower(int damage, int position, int price, String name) {
		this.damage = damage;
		this.position = position;
		this.price = price;
		this.name = name;
		this.timeStpe = 0;
	}
	/**
	* get the neme of tower.
	* @return return tower's name
	*/
	public String getName() {
		return name;
	}
	/**
	* get the price of tower.
	* @return return tower's time price
	*/
	public int getPrice() {
		return price;
	}
	/**
	* get the time step of tower.
	* @return return tower's time step
	*/
	public int getTimeStep() {
		return timeStpe;
	}
	/**
	* get the damage of tower.
	* @return return tower's damage
	*/
	public int getDamage() {
		return damage;
	}
	/**
	* get the position of the tower.
	* @return tower's position
	*/
	public int getPosition() {
		return position;
	}
	/**
	* each advance the time step should be added.
	*/
	public void addTimeStep() {
		this.timeStpe++;
	}
	/**
	* the method will check if it available to shot.
	* @param timeStep a integer represent timeSetp the tower's time step
	* @return true means the tower available to shot
	*/
	public boolean willFire(int timeStep) {
		return true;
	}
	@Override
	public String toString() {
		return "Tower postion" + position + " and damage " + damage;
	}
}
