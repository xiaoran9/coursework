package part1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class BreadthFirst {
	int nextX ;
	int nextY ;
	Queue<Position> frontier; 
	private ArrayList<Position> foundPath;
	
	public Position breadthFirstSearch(Map map, Position start, char aim){
		frontier =new LinkedList<Position>(); 
		foundPath = new ArrayList<Position>();
		frontier.add(start);
		Position lastP =null;
		while(!frontier.isEmpty()){
			Position current ;
			current = (Position)frontier.poll();
			
			foundPath.add(current);
			int currentX = current.getPsotionX();
			int currentY = current.getPsotionY();
			
		    char state =map.getState(currentX,currentY);
		    if(state == aim){
		    	printState(current);
		    	   System.out.println("---------------------------------------");
				    System.out.println("-                                     -");
				    System.out.println("-                                     -");
		    	System.out.println("-  the search size is:"+foundPath.size());
				    System.out.println("-                                     -");
				    System.out.println("-                                     -");
				    System.out.println("---------------------------------------");
		    	map.mapMoved(lastP, current,aim);
		    	map.printMap();
		    	return lastP;
		    	}
		  
		    	map.mapMoved(lastP, current,aim);
		
		    
		    north(current, map, aim);
		    east(current, map, aim);
		    south(current, map, aim);
		    west(current, map, aim);
			
			//printState(current);
			//map.printMap();
		    lastP=current;
		}
		return null;
	}
	public void printState(Position current){
	   	  System.out.println("=========================================================================================");
		  System.out.println("current node is"+current);
		  System.out.println("the list of states expanded is");
		  current.printExpected();
		  System.out.println("and the frontier is");
		  printQueue();
		  
	}
	
	public void printQueue(){
		if(!frontier.isEmpty()){
		for(Position f: frontier){
			System.out.print(f);
		}
		System.out.println(" ");
		}
	}
	public void east(Position current,Map map,char aim){
		nextX = current.getPsotionX();
		nextY =current.getPsotionY()+1;
		successorF(nextX,nextY, current, map, aim);
	}
	public void south(Position current,Map map,char aim){
		nextX=current.getPsotionX()+1;
		nextY =current.getPsotionY();
		successorF(nextX,nextY, current, map, aim);
	}
	public void west(Position current,Map map,char aim){
		nextX=current.getPsotionX();
		nextY =current.getPsotionY()-1;
		successorF(nextX,nextY, current, map, aim);
	}
	public void north(Position current,Map map,char aim){
		nextX=current.getPsotionX()-1;
		nextY =current.getPsotionY();
		successorF(nextX,nextY, current, map, aim);
	}

	public void successorF(int x, int y,Position current,Map map,char aim){
		if(x<=9 && x>=0 && y<=9 && y>=0){
			Position nextP ;
			 char state =map.getState(x,y);
				if(state!='X'&&state!='-'&&state!='.'&&state!='I'){
				nextP=new Position(current,x, y);
				current.addExpected(nextP);
				map.expected(nextP,aim);
				frontier.add(nextP);
			} 
		}
	}
}
