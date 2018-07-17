import java.util.ArrayList;
import java.util.PriorityQueue;
public class ASearch {
	private int nextX ;
	private int nextY ;
	private PriorityQueue<Position> frontier; 
	private Heuristic h = new Heuristic();
	private String heuristic ;
	private ArrayList<Position> foundPath;
	
	public Position bestFirstSearch(Map map, Position start, Position aim,String heuristic){
		int startX = start.getPsotionX();
		int startY = start.getPsotionY();
		this.heuristic =heuristic;
		Position foundP = null;
		char aimSign = map.getState(aim.getPsotionX(), aim.getPsotionY());
		int score = ChoseHuristic(aim,startX ,startY );
		Position initial = new Position(null,startX,startY,score);
		frontier =new PriorityQueue<Position>(10,new PriorityComparator()); 
		foundPath = new ArrayList<Position>();
		  frontier.add(initial);
		  Position lastP;
		  lastP=null;
		while(!frontier.isEmpty()){
			Position current;
			current = (Position)frontier.remove();
			foundPath.add(current);
			foundP = current;
			int currentX = current.getPsotionX();
			int currentY = current.getPsotionY();
		    char state =map.getState(currentX,currentY);
		    if(state == aimSign){
		    	printState(current);
		    	map.mapMoved(lastP, current);
		    	    System.out.println("---------------------------------------");
				    System.out.println("-                                     -");
				    System.out.println("-                                     -");
		    	System.out.println("-  the search size is:"+foundPath.size());
				    System.out.println("-                                     -");
				    System.out.println("-                                     -");
				    System.out.println("-                                     -");
				    System.out.println("---------------------------------------");
		    	map.printMap();
		    	return foundP;
		    	}
		    	map.mapMoved(lastP, current);
		    north(current, map, aim);
		    east(current, map, aim);
		    south(current, map, aim);
		    west(current, map, aim);
		    
		    printState(current);
			map.printMap();
		    
			lastP = current;
		}
		return null;
	}
	public void printState(Position current){
	   	  System.out.println("=========================================================================================");
		  System.out.println("current node is "+current +"and it's property is"+ current.getPriority());
		  System.out.println("the list of states expanded is");
		  current.printExpected();
		  System.out.println("and the frontier is");
		  printPriority();
	}
	
	public void printPriority(){
		if(!frontier.isEmpty()){
		for(Position f: frontier){
			System.out.print(f);
		}
		System.out.println(" ");
		}
	}
	
	public void east(Position current,Map map,Position aim){
		nextX = current.getPsotionX();
		nextY =current.getPsotionY()+1;
		searchNext(nextX,nextY, current, map, aim);
	}
	public void south(Position current,Map map,Position aim){
		nextX=current.getPsotionX()+1;
		nextY =current.getPsotionY();
		searchNext(nextX,nextY, current, map, aim);
	}
	public void west(Position current,Map map,Position aim){
		nextX=current.getPsotionX();
		nextY =current.getPsotionY()-1;
		searchNext(nextX,nextY, current, map, aim);
	}
	public void north(Position current,Map map,Position aim){
		nextX=current.getPsotionX()-1;
		nextY =current.getPsotionY();
		searchNext(nextX,nextY, current, map, aim);
	}

	public void searchNext(int x, int y,Position current,Map map,Position aim){
		if(x<=9 && x>=0 && y<=9 && y>=0){
			Position nextP;
			 char state =map.getState(x,y);
				if(state!='X'&&state!='-'&&state!='.'&&state!='I'){
				 int priority = ChoseHuristic(aim,x,y);
				 nextP = new Position(current,x, y,priority);
				 int pathCost = h.pathCost(nextP);
				 nextP.changePriority(pathCost);
				current.addExpected(nextP);
				map.expected(nextP,aim);
				frontier.add(nextP);
			} 
		}
	}
	public int ChoseHuristic(Position aim,int x, int y){
		int priority =0;
		int priority2 =0;
		if(heuristic.toLowerCase().equals("m")){
			 priority = h.Manhattan(aim, x,y);
			return priority;
		}
		if(heuristic.toLowerCase().equals("c")){
			priority = h.chebyshev(aim, x, y);
			return priority;
		}
		if(heuristic.toLowerCase().equals("n")){
			priority = h.chebyshev(aim, x, y);
			 priority2 = h.Manhattan(aim, x,y);
			return (int)(priority/2+priority2/2);
		}
		return priority;
	}
}
