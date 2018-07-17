import java.util.LinkedList;
import java.util.Queue;

public class SPS {
	private KnowledgeBase kb;
	private boolean update;
	Queue<Grid> coveredN;
	int numberM = 0; //number of the grid marked
	int numberC = 0; //number of the node covered
	public KnowledgeBase siglePoint(KnowledgeBase kb,Environment map, Agent newA){
		this.kb = kb;
		do{
			update =false;
			
			for(int i = 0;i < kb.getSize() ;i++){
				for(int j=0; j<kb.getSize() ; j++ ){ //scan all the board in the board
					coveredN = new LinkedList<Grid>(); //each time create a new covered grid list which don't contain marked one
					numberM = 0;
					numberC = 0;
					Grid current = kb.getGrid(i, j);
					if(current.getValue() > 0 &&  current.getValue() < 9){
						Grid neighbour;

						for(int n=1;n <= 8; n++) {
							neighbour = current.neighbour(n);
							checkNeigh(neighbour);
						}
						int different = current.getValue() - numberM;
						if(different == 0){
							while(!coveredN.isEmpty()){
								Grid grid = coveredN.poll();	
								
								if(map.getLocation(grid.getX(), grid.getY()) == 0){
									newA.probe(grid.getX(),grid.getY());
									kb.print();
								}else{
									kb.update(grid.getX(),grid.getY(),map.getLocation(grid.getX(), grid.getY()));
									kb.print();
								}
								update =true;
							}
						}
						else if(different==numberC){
							while(!coveredN.isEmpty()){
								Grid grid = coveredN.poll();	
								kb.setflag(grid.getX(), grid.getY());
								kb.print();
								update =true;
							}
						}
					}
				}
			}
		}while(update);
		
		return kb;
	}
	public void checkNeigh( Grid neighbour){
		if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){
			neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
			if(!neighbour.getVisit() && ! neighbour.getFlag() ){
				numberC++;
				coveredN.add(neighbour);
			}
			if(neighbour.getFlag()){
				numberM++;
			}
		}
		
	}
}
