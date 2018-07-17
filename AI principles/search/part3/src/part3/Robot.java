package part3;

import java.util.Scanner;

public class Robot {
	private static bidirection bi ;
	int nextX ;
	int nextY ;;
	Position bob ;
	static Position goal;
	private static Map map ;
	private static boolean aimFined=false;
	private static Scanner read = new Scanner(System.in);
	private static int inputMap;



	public static Position run(Position initial,Position goal,Map map){
		
	    map.setStart(initial);
	    System.out.println("the original map of is ");
	    map.printMap();
	    Position lastPosition =null;
	    bi = new bidirection();
	    	aimFined = bi.biSearch(inputMap,initial,goal, map);
	   
	    printPath(lastPosition);
		return lastPosition;
	    
	}
	public static void printPath(Position lastPosition){
		if(aimFined){
		System.out.println("the path of the searching is ");
		do{
			System.out.print(lastPosition);
			lastPosition = lastPosition.getParent();
			
		}while(lastPosition.getParent()!=null);
		System.out.println("");
		System.out.println("=========================================================================================");
		}else{
			System.out.println("Sorry!  search failure we did't find the aim, the search map looks above :");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method 
	 System.out.println("Welcome the bidirection search:");
	 do{
	 System.out.println("pleas selected which map you wanted(from 1 to 6)");
	 inputMap =read.nextInt();
	 }while(inputMap>=6||inputMap<=0);
	 map = new Map();
	    map.loadMap(inputMap);
		goal = map.getBob();
		 Position initial = map.getCurrentP();
		 initial =run(goal,initial, map);
		 
     initial= run(map.getBob(),initial, map);
	 if(aimFined){
		 map = new Map();
		 map.loadMap(inputMap);
		    System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-          bob founded!!!!            -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
		 
		 run(map.goal,initial, map);
	 }
	 else{
		 System.out.println("---------------------------------------");
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
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
		    System.out.println("-                                     -");
		    System.out.println("-                                     -");
		    System.out.println("---------------------------------------");
	 }
	}

}
