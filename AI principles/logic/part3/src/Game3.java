import java.util.Scanner;

public class Game3 {
	private static Scanner read = new Scanner(System.in);
	private static Environment map;
	private static RGS rand;
	private static SPS siglePoint;

	private static DLS dpll;
	private static KnowledgeBase kb;
	static boolean update;
	
	public static boolean checkInput(String input){
		if(input.equalsIgnoreCase("A") || input.equalsIgnoreCase("B")||input.equalsIgnoreCase("C")){
		return true;
		}else {return false;}
		
	}
	 public static void checkFinish(){
		 if( kb.getCoveredNum() == map.getNettle() - kb.getMarkedNum() ||kb.getMarkedNum() == map.getNettle() ){
			kb.print();
			System.out.println("agent Win the game");
			System.exit(0);
		}
	 }
	
	public static void main(String[] args) {
		System.out.println("welcome to nettle world");
		System.out.println("Please input the hard level you wanted");
		System.out.println("A-Easy level");
		System.out.println("B-Medium level");
		System.out.println("C-Hard level");
		String level = read.nextLine();
		
		while(!checkInput(level)){
			System.out.println("Please input A, B or C");
		 level = read.nextLine();
		}
		System.out.println("Please select the world you want(1 to 5)");
		int i = read.nextInt();
		while(!(i<=5) ||! (i > 0)){
			System.out.println("Please selecet the world you want(1 to 5)");
			 i = read.nextInt();
		}
		map = new Environment(level,i);
		kb = new KnowledgeBase(map.getSize());
		Agent newA = new Agent(map,kb);
		
		rand = new RGS();
		siglePoint = new SPS();
		dpll = new DLS(kb,newA);
		int x = 0;
		int y = 0;
		newA.probe(x,y);
		kb.print();
		while(true){
			kb = siglePoint.siglePoint( kb, map,newA);
			checkFinish();
			update = false;
			kb = dpll.dpllStrategy();
			checkFinish();
			if(!update){
			rand.randomGuess(kb,newA);
			checkFinish();
			}
					
		}
	}
}
