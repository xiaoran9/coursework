import java.util.LinkedList;
import java.util.Queue;

public class SPS {
	private KnowledgeBase kb;
	private boolean update;
	Queue<Grid> coveredN; // list of covered neighbour
	int numberM = 0; //number of the grid marked
	int numberC = 0; //number of the node covered
	public KnowledgeBase siglePoint(KnowledgeBase kb,Environment map, Agent newA){
		this.kb = kb;
		do{
			update =false;
			
			for(int i = 0;i < kb.getSize() ;i++){
				for(int j=0; j<kb.getSize() ; j++ ){ //scan all the board in the board
					coveredN = new LinkedList<Grid>(); //each time create a new covered grid list which don't contain marked one
					numberM = 0; //the marked grids' number during in the current grid
					numberC = 0; //in the current grid's neighbour how many uncovered but not marked node
					Grid current = kb.getGrid(i, j); 
					if(current.getValue() > 0 &&  current.getValue() < 9){  //the grid number biggger than 1
						Grid neighbour;

						for(int n=1;n <= 8; n++) { //check the current grid's neighbour
							neighbour = current.neighbour(n);
							checkNeigh(neighbour); // get during current's neighbour how many marked neighbour and how many uncovered neighbours
						}
						int different = current.getValue() - numberM;  
						if(different == 0){ // means no nettle in the current grid's surround 
							while(!coveredN.isEmpty()){ //probe all the covered neighbour
								Grid grid = coveredN.poll();	
									newA.probe(grid.getX(),grid.getY()); //probe the grid (X,Y)
									update =true;
							}
						}
						else if(different==numberC){ //all the neighbour is mine 
							while(!coveredN.isEmpty()){ //Mark all the neighbour
								Grid grid = coveredN.poll();	
								kb.setflag(grid.getX(), grid.getY());
								kb.print();
								update =true;
							}
						}
					}
				}
			}
		}while(update); // if any grid changed, do the SPS again
		return kb;
	}
	/**
	 * check the current grid's one neighbour if not flag and not uncovered.
	 * add the current's numberC
	 * if the neighbour is marked
	 * add the current's numberM
	 * @param neighbour -one neighbour grid need to check
	 */
	public void checkNeigh( Grid neighbour){
		if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){//if the neighbour is inside the board
			neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
			if(!neighbour.getVisit() && ! neighbour.getFlag() ){ //  if not flag and not uncovered.
				numberC++;
				coveredN.add(neighbour); // add the neighbour in to list coveredN
			}
			if(neighbour.getFlag()){ //if the neighbour is marked
				numberM++;
			}
		}
		
	}
}
