package part1;

import java.util.Scanner;

public class Robot {
	private static DepthFirst depthFirst ;
	private static BreadthFirst breadthFirst ;
	private static Map map ;
	private static boolean aimFined=false;
	private static Scanner read = new Scanner(System.in);
	private static int inputMap;
	private static String search;



	public static Position run(char goal,Position initial){
		
	    map.setStart(initial);
	    System.out.println("the original map of is ");
	    map.printMap();
	    Position lastPosition =null;
	    if(search.toLowerCase().equals("d")){
	    	depthFirst = new DepthFirst();
	    	lastPosition = depthFirst.depthFirstSearch(map,initial,goal);
	    }
	    if(search.toLowerCase().equals("b")){
	    	breadthFirst = new BreadthFirst();
	    	lastPosition = breadthFirst.breadthFirstSearch(map,initial,goal);
	    }
	    printPath(lastPosition);
		return lastPosition;
	    
	}
	public static void printPath(Position lastPosition){
		if(lastPosition!=null){
			aimFined =true;
		System.out.println("the path of the searching is ");
		int pathCost =0;
		map.loadMap(inputMap);
		
		do{
			pathCost++;
			System.out.print(lastPosition);
			lastPosition = lastPosition.getParent();
			
			
		}while(lastPosition.getParent()!=null);
		System.out.println(" ");
		    System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("- the length of path is:  "+pathCost+"  -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
		System.out.println("=========================================================================================");
		}else{
			System.out.println("Sorry!  search failure we did't find the aim, the search map looks above :");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method 
	 System.out.println("Welcome the Depth First search and Breadth First Search:");
	 do{
	 System.out.println("pleas selected which map you wanted(from 1 to 7 and 7 is the own example map)");
	 inputMap =read.nextInt();
	 }while(inputMap>7||inputMap<=0);
	
	do{
		 System.out.println("pleas input which search method you wanted:");
		 System.out.println("D: Deepth First search");
		 System.out.println("B: Breadth First Search");
		 search = read.next();
		 
	}while(!search.toLowerCase().equals("d")&&!search.toLowerCase().equals("b"));
	 map = new Map();
	 map.loadMap(inputMap);
     Position initial = map.getCurrentP();
     initial= run('B',initial);
     
	 if(aimFined){
		 map = new Map();
		 map.loadMap(inputMap);
		    System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-          bob founded!!!!            -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
		  initial.changeParent(null);
		 run('G',initial);
	 }
	 else{
		 System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-        bob not founded.....         -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
	 }
	}

}
