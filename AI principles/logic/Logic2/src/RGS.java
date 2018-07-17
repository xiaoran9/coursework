import java.util.Random;

public class RGS {
	
	public void randomGuess(KnowledgeBase kb, Agent newA){
		Random  rand = new Random();
		int x = 0;
		int y = 0;
		do{
			x = rand.nextInt(kb.getSize());
			y = rand.nextInt(kb.getSize());
		}while(kb.getGrid(x, y).getVisit()&&!kb.getGrid(x, y).getFlag());
		System.out.println("the random is "+"("+x+","+y+")");
		newA.probe(x,y);
	}
	
}
