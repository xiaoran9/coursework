package part3;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;



public class bidirection   {
	int nextX ;
	int nextY ;
	PriorityQueue<Position> frontierI;
	PriorityQueue<Position> frontierG;
	private ArrayList<Position> foundPathI;
	private ArrayList<Position> foundPathG;
	private Heuristic h = new Heuristic();
	
	public boolean biSearch(int inputMap, Position start, Position goal,Map map){
		frontierI = new PriorityQueue<Position>(10,new PriorityComparator());
		frontierG = new PriorityQueue<Position>(10,new PriorityComparator());
		foundPathI = new ArrayList<Position>();
		foundPathG = new ArrayList<Position>();
		frontierI.add(start);
		frontierG.add(goal);
		Position lastP1 =null;
		Position lastP2 =null;
		
		while(!frontierI.isEmpty()&& !frontierG.isEmpty()){
		
			if(oneSide( frontierI, frontierG, map, foundPathI,lastP1,   goal)){
			return true;
		}
		if(oneSide(frontierG,frontierI,map,foundPathG,lastP2,  start)){
			return true;
		}
		}
		return false;
	}

	public boolean oneSide(Queue<Position> frontier,Queue<Position> otherF,Map map,ArrayList<Position> foundPath,Position lastP, Position goal){
		if(!frontier.isEmpty()){
			Position current ;
			current = (Position)frontier.remove();
			foundPath.add(current);
			int currentX = current.getPsotionX();
			int currentY = current.getPsotionY();
			if(checkQue(currentX,currentY,otherF)){
		    	printState(current,frontier);
		    	printPath(current);
		    	map.mapMoved(lastP, current);
		    	map.printMap();
		    	return true;
		    	}
		  
		    map.mapMoved(lastP, current);
		
		    north(current, map,frontier,goal);
		    east(current, map,frontier,goal);
		    south(current, map,frontier,goal);
		    west(current, map,frontier,goal);
			printState(current,frontier);
			map.printMap();
			lastP=current;
		}
		return false;
	}
	public void east(Position current,Map map,Queue<Position> frontier,Position aim){
		nextX = current.getPsotionX();
		nextY =current.getPsotionY()+1;
		searchNext(nextX,nextY, current, map,frontier, aim);
	}
	public void south(Position current,Map map,Queue<Position> frontier,Position aim){
		nextX=current.getPsotionX()+1;
		nextY =current.getPsotionY();
		searchNext(nextX,nextY, current,map, frontier, aim);
	}
	public void west(Position current,Map map,Queue<Position> frontier,Position aim){
		nextX=current.getPsotionX();
		nextY =current.getPsotionY()-1;
		searchNext(nextX,nextY, current,map, frontier, aim);
	}
	public void north(Position current,Map map,Queue<Position> frontier,Position aim){
		nextX=current.getPsotionX()-1;
		nextY =current.getPsotionY();
		searchNext(nextX,nextY, current, map,frontier, aim);
	}
	public static void printPath(Position lastPosition){
		
		do{
			System.out.print(lastPosition);
			lastPosition = lastPosition.getParent();
			
		}while(lastPosition.getParent()!=null);
		
	}
	public boolean checkQue(int x, int y,Queue<Position> otherfrontier){
		
		for(Position f: otherfrontier){
			if(f.getPsotionX()==x && f.getPsotionY()==y){
				return true;
			}
		}
		return false;
	}
	public boolean checkf(int x, int y,Queue<Position>Que,Position current){
		for(Position f: Que){
			if(f.getPsotionX()==x && f.getPsotionY()==y){
				return false;
			}
		}
		while(current.getParent()!=null){
			current =current.getParent();
			if(current.getPsotionX() ==x&&current.getPsotionY()==y){
				return false;
			}
		}
		return true;
	}
	public void printState(Position current,Queue<Position> frontier){
	   	  System.out.println("=========================================================================================");
		  System.out.println("current node is"+current);
		  System.out.println("the list of states expanded is");
		  current.printExpected();
		  System.out.println("and the frontier is");
		  printQueue(frontier);
		  
	}
	
	public void printQueue(Queue<Position> frontier){
		if(!frontier.isEmpty()){
		for(Position f: frontier){
			System.out.print(f);
		}
		System.out.println(" ");
		}
	}

	public void searchNext(int x, int y,Position current,Map map,Queue<Position> frontier,Position aim){
	
		if(x<=9 && x>=0 && y<=9 && y>=0){
			Position nextP ;
				if(checkf(x,y,frontier,current)){
					int priority = Huristic(aim,x,y);
					nextP = new Position(current,x, y,priority);
					int pathCost = h.pathCost(nextP);
					nextP.changePriority(pathCost);
					current.addExpected(nextP);
					frontier.add(nextP);
			} 
		}
	}
	public int Huristic(Position aim,int x, int y){
		int priority =0;
			 priority = h.Manhattan(aim, x,y);
			return priority;
	}
}
