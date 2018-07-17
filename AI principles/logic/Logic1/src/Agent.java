import java.util.LinkedList;
import java.util.Queue;

public class Agent {
	private Environment map;
	private KnowledgeBase kb;
	private Queue<Grid> frontierS; 

	/**
	 * open a new grid.
	 * if the open grid is 0 open all its neighbours
	 * if the open grid is -1 stop the game and agent lost the game
	 * @param gridX -grid's position x
	 * @param gridY -grid's position y
	 */
	public void probe	(int gridX,int gridY){
		frontierS = new LinkedList<Grid>(); //create a frontier to save the grid
		Grid current ;
		int value ;
		frontierS.add(kb.getGrid(gridX, gridY)); // add the first grid into the frontier
		if(!kb.getGrid(gridX, gridY).getVisit() && !kb.getGrid(gridX, gridY).getFlag()){ //check if the grid is covered and not marked
			kb.update(gridX,gridY,map.getLocation(gridX, gridY)); //Open the grid
			}
		do{ // open all the grid in the frontier
			current = frontierS.poll();
			value = map.getLocation(current.getX(), current.getY());
			gridX = current.getX();
			gridY = current.getY();
			if(value == 0) { // save the appropriate neighbours to the frontier and open them.
				search(current); 
			} else if(value == -1) { //if open a mine(nettle) stop the game and agent lose
				System.out.println("agent lost the game");
				kb.print(); //print the map of kb
				System.exit(0);
			}
		}while(!frontierS.isEmpty()); //end the loop in frontier is empty
	
	}
	/**
	 * Search all the neighbour make sure they are all inside of board and not marked
	 * @param current
	 */
	public void search(Grid current){
		Grid neighbour ;
		for(int i = 1; i <= 8; i++){
			neighbour = current.neighbour(i);
			int neighbourX = neighbour.getX();
			int neighbourY = neighbour.getY();
			if(neighbourX < map.getSize() && neighbourY < map.getSize() && neighbourX >= 0 && neighbourY >= 0){ //make sure the neighbour is inside the board
				neighbour = kb.getGrid(neighbourX, neighbourY);
				if(!kb.getGrid(neighbourX, neighbourY).getVisit()){ //if the neighbour is not visited 
					frontierS.add(kb.getGrid(neighbourX, neighbourY)); //add the neighbour into the frontier list
					kb.update(neighbourX,neighbourY,map.getLocation(neighbourX, neighbourY)); //open the neighbour
				}
			}
		}
	}
	/**
	 * the agent have the Environment map and the created map kb.
	 */
	 public Agent(Environment map, KnowledgeBase kb) {
		this.map = map;
		this.kb = kb;
	}
}
