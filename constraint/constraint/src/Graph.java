import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
	int numVar;
	LinkedList<Integer> adjListArray[];
	public Graph(int numVar){
		this.numVar =numVar;
		adjListArray = new LinkedList[numVar];
		// Create a new list for each variable
		for(int i = 0; i < numVar ; i++)
			adjListArray[i] = new LinkedList<>();
	}
	// Adds an edge to an undirected graph
	public void addEdge(int var, int futureVar) {
		// Add an edge from var to futureVar. 
		this.adjListArray[var].addLast(futureVar);		
		// Since graph is undirected, add an edge from futureVar to var
		this.adjListArray[futureVar].addLast(var);
	}
	
	public int getDegrees(int var) {
		return adjListArray[var].size();
	}
	
	public boolean check(int var, int otherVar) {
		if(adjListArray[var].contains(otherVar)) {
			return true;
		}
		return false;
	}
	
	public String toString() {
	    StringBuffer result = new StringBuffer() ;
	    
	    for (int i =0;i<this.numVar;i++) {
	    	result.append("the llist of variabel "+ i+"\n") ;
	    	for(Integer arr: adjListArray[i]){
	    		result.append(arr+"  ");
			}
	    }
	      result.append("\n") ;
	    return result.toString() ;
	  }
	
}
