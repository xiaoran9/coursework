import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EES {
	private Queue<Grid> frontier;
	private KnowledgeBase kb;
	private Agent agent;
	private List<Grid> list1;
	private List<Grid> list2;
	
	/**
	 * create a ess with the kb, map and agent
	 * @param kb
	 * @param map
	 * @param agent
	 */
	public EES(KnowledgeBase kb,Environment map, Agent agent) {
		this.kb = kb;
		this.agent =agent;
	}

	public KnowledgeBase easyEquation(){
		getFrontier(); //
		while(!frontier.isEmpty()){
			Grid current = frontier.poll();
			gridNeighbours(current,list1,false);
		}
		
		return kb;
	}
	/**
	 * get the frontier that have all the grid have covered but not flag neighbours
	 */
	public void getFrontier() {
		frontier = new LinkedList<Grid>(); //create the new frontier
		for(int i = 0;i < kb.getSize() ;i++){
			for(int j=0; j<kb.getSize() ; j++ ){ //scan all the grid in the map
				if(kb.getGrid(i, j).getValue() > 0 && kb.getGrid(i, j).getValue() <= 8){ //if the grid is inside of the board
					if(checkFrontier(kb.getGrid(i, j))){ //add the grid to the frontier when grid's  covered but not flag neighbours
					frontier.add(kb.getGrid(i, j)); 
					}
				}
			}
		}
	}
	/**
	 * if the current neighbour have any covered but not flag neighbour
	 * @param current -the neighbour need to check
	 * @return -true means there have appropriate neighbour, flase mean the current grid surrounding is safety
	 */
	public boolean checkFrontier(Grid current){
		for(int n=1;n <= 8; n++) { //check all neighbours
			Grid neighbour = current.neighbour(n);
			if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){ //if the neighbour not out of board
				neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
				if(!neighbour.getFlag() && !neighbour.getVisit()){ //if the neighbour have any covered but not flag neighbour
					return true;
				}
			}
		}
		return false;
		
	}
	/**
	 * find the pair to run EES
	 * @param current
	 * @param list
	 * @param findCovered
	 */
	public void gridNeighbours(Grid current,  List<Grid> list,boolean findCovered){
		Grid neighbour = null;
		int countC = 0; //the number of covered but not marked grid
		int countM = 0; //the number of Marked gird
		for(int n=1;n <= 8; n++) {
			neighbour = current.neighbour(n);
			//neighbour not out of board
			if(neighbour.getX() < kb.getSize() && neighbour.getY() < kb.getSize() && neighbour.getX() >= 0 && neighbour.getY() >= 0){ //grid inside the board
				
				if(!findCovered){ 
					if(uncoverdnNeighbour(neighbour)){ //check if the current's neighbour in the frontier
						list1 = new ArrayList<Grid>();//the appropriate neighbour for one gird in the pair 
						list2 = new ArrayList<Grid>();
						neighbour = kb.getGrid(neighbour.getX(), neighbour.getY()); 
						gridNeighbours(current, list1, true ); //get the list one's appropriate neighbour
						gridNeighbours(neighbour, list2, true );
						strategy(current, neighbour, list1, list2);// Comparing the tow grid to inference the netlle
					}
				}else{// count the current grid's marked neighbour and add the covered but not marked neighbour into list
					neighbour = kb.getGrid(neighbour.getX(), neighbour.getY());
					if(!neighbour.getFlag() && !neighbour.getVisit() ){ // covered but not marked grid
						list.add(neighbour); 
						countC++;
						}
					if(neighbour.getFlag()){ // marked grid
						countM++;
						}
				}
			}
		}
		current.setCovered(countC);
		current.setMarked(countM);
	}

	/**
	 * if the neighbour is in the frontier
	 * @param neighbour
	 * @return - true mean the neighbour can consist a pair with current neighbour and check EES. false mean not a pair
	 */
	public boolean uncoverdnNeighbour(Grid neighbour){
		Grid current = kb.getGrid(neighbour.getX(), neighbour.getY());
		if(current.getValue()>0 &&current.getValue()<9){
			if(frontier.contains(current)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param current1
	 * @param current2
	 * @param list1
	 * @param list2
	 */
	public void strategy(Grid current1,Grid current2, List<Grid> list1, List<Grid> list2){
		comparing(list1, list2); // Remove the same grid in tow list
		int value1 = current1.getValue()-current1.getMarked(); 
		int value2 = current2.getValue()-current2.getMarked();
		int value = Math.abs(value1-value2); 
		if(list1.isEmpty()){ // if list one is empty mean the nettle is in the list2's neighbour
			conlusion(value,list2); 
			
		}else if(list2.isEmpty()){
			conlusion(value,list1);
		}
	}
	/**
	 * remove the same grid form list1 and list2
	 * @param list1
	 * @param list2
	 */
	public void comparing( List<Grid> list1, List<Grid> list2){
		for(int i = list1.size()-1; i >= 0;i--){ //Scan all the list1's grid
			for(int j = list2.size()-1; j >= 0;j--){ //Scan all the list2's grid
				if(list1.get(i).equals(list2.get(j))){ //tow list have same grid delete it 
					list1.remove(i);
					list2.remove(j);
					comparing(list1, list2); //send the new lists to find the same one, which can avoid the NullPointException
					return;	
				}
			}
		}
	}
	/**
	 * inference the remain grid depend on the different value
	 * @param value
	 * @param list
	 */
	public void conlusion(int value,List<Grid> list ){
		if(value == 0){ //the remain grids must be the safe
			for(int n = 0; n < list.size(); n++){ //get all grid in the list
				list.get(n); 
				Game2.update =true;
				agent.probe(list.get(n).getX(), list.get(n).getY()); //open the grid
				
			}
		}else if(value <= list.size()){  //the remian grids all are nettles set a flag.
			for(int n = 0; n < list.size(); n++){
				list.get(n);
				kb.setflag(list.get(n).getX(), list.get(n).getY());
				kb.print();
			}
			Game2.update =true;
		}
	}
	
}
