import java.util.Scanner;

public class Game1 {
	private static Scanner read = new Scanner(System.in);
	private static Environment map;
	private static RGS rand;
	private static SPS siglePoint;
	private static KnowledgeBase kb;
	/**
	 * make sure the input belong to the A, B and C
	 * @param input
	 * @return
	 */
	public static boolean checkInput(String input){
		if(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("B")||input.equalsIgnoreCase("C")){
		return true;
		}else {return false;}
	}
	/**
	 * check if the agent win the games
	 */
	 public static void checkFinish(){
			if( kb.getCoveredNum() == map.getNettle() - kb.getMarkedNum() ||kb.getMarkedNum() == map.getNettle() ){ //if all the nettle fined or remain is nettle
				System.out.println("agent Win the game");
				kb.print();
				System.exit(0);
			}
	 }
	
	public static void main(String[] args) {
		System.out.println("welcome to nettle world");
		System.out.println("Please input the hard level you wanted");
		System.out.println("A-Easy level");
		System.out.println("B-Medium level");
		System.out.println("C-Hard level");
		String level = read.nextLine(); //read the input to distinguish 
		
		while(!checkInput(level)){ //if the input not belong to the A, B and C, ask the use input again
			System.out.println("Please input A, B or C");
			level = read.nextLine();
		}
		System.out.println("Please select the world you want(1 to 5)");
		int i = read.nextInt();
		while(!(i<=5) ||! (i > 0)){ //if the input not belong to 1 to 5, ask the use input again
			System.out.println("Please selecet the world you want(1 to 5)");
			 i = read.nextInt();
		}
		
		map = new Environment(level,i); //create the world of nettle depend on the input
		kb = new KnowledgeBase(map.getSize()); // create the same size of input world but it's empty
		Agent newA = new Agent(map,kb); //create an agent 
		
		rand = new RGS();  //create a random guessing strategy
		siglePoint = new SPS(); //create a single point strategy
		int x = 0;
		int y = 0;
		newA.probe(x,y); //any game start at (0,0)
		kb.print();
		while(true){
			kb = siglePoint.siglePoint( kb, map,newA);
			checkFinish();
			rand.randomGuess(kb,newA);
					
		}
	}
}
