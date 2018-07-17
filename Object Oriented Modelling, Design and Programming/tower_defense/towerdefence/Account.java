package towerdefence;
/**
 * the initialise of a account which can save the money of the user.
 * @author xm24
 */
public class Account {
	private static int budget;
	/**
	* initialise an account with 200.
	*/
	public Account(){
		budget = 200;
	}
	/**
	* get the budget from the account.
	* @return return budget
	*/
	public static int getBudget() {
		return budget;
	}
	/**
	* when earn money, add it to the budget.
	* @param coins the money earned
	*/
	public void earn(int coins) {
		budget += coins;
	}
	/**
	* when spend money, reduce it from the budget.
	* @param coins the money spend
	*/
	public void spend(int coins) {
		budget -= coins;
	}
	/**
	* checking if account is enough to spend the money.
	* @param coins the money will spend 
	*/
	public boolean checkAccount(int coins) {
		if (budget -coins >= 0) {
			return true;
		}
		return false;
	}
}
