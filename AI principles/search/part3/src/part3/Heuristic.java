package part3;

public class Heuristic {
	int goalX;
	int goalY;
	int score;
	public int Manhattan(Position goal,int x,int y){
		goalX = goal.getPsotionX();
		goalY= goal.getPsotionY();
		score = Math.abs(goalX-x) +Math.abs(goalX-y);
		System.out.println("the score is "+ score);
	return score;
	}
	public int chebyshev(Position goal,int x,int y){
		goalX = goal.getPsotionX();
		goalY= goal.getPsotionY();
		score = Math.max(goalX-x, goalY-y);
	return score;
	}
	public int pathCost(Position curretn){
		int pathCost = 0;
		while(curretn.getParent()!=null){
			pathCost++;
			curretn = curretn.getParent();
		}
	return pathCost;
	}
	
	
	
}
