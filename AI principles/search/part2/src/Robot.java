
import java.util.Scanner;

public class Robot {
	static BestFirst bestFS ;
	static ASearch ASearch ;
	int nextX ;
	int nextY ;;
	Position bob ;
	static Position goal;
	private static Map map ;
	private static boolean aimFined=false;
	private static Scanner read = new Scanner(System.in);
	private static int inputMap;
	private static String search;
	static String heuristic;
	
	public static Position run(Position goal,Position initial){
	    map.setStart(initial);
	    System.out.println("the original map of is ");
	    map.printMap();
	    Position lastPosition =null;
	    if(search.toLowerCase().equals("a")){
	    	ASearch = new ASearch();
	    	lastPosition = ASearch.bestFirstSearch(map,initial,goal,heuristic);
	    }
	    if(search.toLowerCase().equals("b")){
	    	bestFS = new BestFirst();
	    	lastPosition = bestFS.bestFirstSearch(map,initial,goal,heuristic);
	    }
	    printPath(lastPosition);
		return lastPosition;
	}
	public static void printPath(Position lastPosition){
		if(lastPosition!=null){
			aimFined =true;
		System.out.println("the path of the searching is ");
		int pathL=1;
		do{
			pathL++;
			System.out.print(lastPosition);
			lastPosition = lastPosition.getParent();
		}while(lastPosition.getParent()!=null);
		System.out.println(lastPosition);
		 System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("- the length of path is:  "+pathL+"  -");
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
	 System.out.println("Welcome the A* search and Best First Search:");
	 do{
	 System.out.println("pleas selected which map you wanted(from 1 to 7)");
	 inputMap =read.nextInt();
	 }while(inputMap>7||inputMap<=0);
	do{
		 System.out.println("pleas input which search method you wanted:");
		 System.out.println("A: A* search");
		 System.out.println("B: Best First Search");
		 search = read.next();
	}while(!search.toLowerCase().equals("a")&&!search.toLowerCase().equals("b"));
	do{
		 System.out.println("pleas input which heuristic method you wanted:");
		 System.out.println("M: Manhattan");
		 System.out.println("C: Chebyshev");
		 System.out.println("N: Combine");
		 heuristic = read.next();
	}while(!heuristic.toLowerCase().equals("m")&&!heuristic.toLowerCase().equals("c")&&!heuristic.toLowerCase().equals("n"));
	map = new Map();
    map.loadMap(inputMap);
	goal = map.getBob();
	 Position initial = map.getCurrentP();
	 initial =run(goal,initial);
	 if(aimFined){
		 initial.changeParent(null);
		 map.loadMap(inputMap);
		    System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-          bob founded!!!!            -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
		 map = new Map();
		 map.loadMap(inputMap);
		 goal = map.getGoal();
		 run(goal,initial);
	 }
	 else{
		 System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-        bob not founded.....         -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
	 }
	}

}
