import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class Heuristic {
	
	// dynamic smallest domain first heuristic
	public ArrayList<Integer> dynamicH(ArrayList<Integer> varList,	HashMap<Integer, ArrayList> domainList) {
		ArrayList<Integer> newOrder = new ArrayList<Integer>();
		Iterator<Integer> itOldList = varList.iterator();
		while(itOldList.hasNext()) {
			int var =itOldList.next();
			if(newOrder.size()>0) {
				for(int i =0;i<newOrder.size();i++) {
					if(domainList.get(newOrder.get(i)).size()> domainList.get(var).size()) {
						newOrder.add(i,var);
						break;
					}else if(i==newOrder.size()-1) {
						newOrder.add(var);
						break;
					}
				}
			}else 
				newOrder.add(var);
		}
		return newOrder;
	}
	
	// Maximum cardinality the linear order is 0 to n
	public ArrayList<Integer> maxC(ArrayList<Integer> varList,BinaryCSP csp) {
		int ran =(int)Math.random()*csp.getNoVariables();
		ArrayList<Integer> newOrder = new ArrayList<Integer>();
		Graph graph = new Graph(csp.getNoVariables());
		graph = creatGraph(graph,csp);
		int max=0;
		int cardinality;
		int addVar = 0;
		//initialize
		int first = varList.get(0);
		varList.remove((Integer)first);
		newOrder.add(first);
		
		while(!varList.isEmpty()) {
			max=0;
			for(Integer subVar:varList) {
				cardinality = 0;
				for(Integer selectVar: newOrder) {
					if(graph.check(selectVar, subVar)) {
						cardinality++;
					}
				}
				if(cardinality>max) {
					max = cardinality;
					addVar = subVar;
				}
			}
			varList.remove((Integer)addVar);
			newOrder.add(addVar);
		}
		
		return newOrder;
		
	}
	//Maximum Degree
	//it find the 
	public ArrayList<Integer> maxDegree(ArrayList<Integer> varList,BinaryCSP csp){
		ArrayList<Integer> newOrder = new ArrayList<Integer>();
		ArrayList<Integer> varDegree = new ArrayList<Integer>();
		// the nodes of graph are the variables 
		Graph graph = new Graph(csp.getNoVariables());
		graph = creatGraph(graph,csp);
		int max =0;
		int degree = 0;
		int add = 0;
		for(Integer var:varList){
			varDegree.add(graph.getDegrees(var));
		}
		while(newOrder.size() != varList.size()) {
			max =0;
			degree = 0;
			add = 0;
			Iterator<Integer> varIt = varList.iterator();
			while(varIt.hasNext()) {
				int var = varIt.next();
				degree = varDegree.get(var);
				if(degree>max) { 
					max =degree;
					add =var;
					}
			}
			varDegree.set(add, 0);
			newOrder.add(add);
		}
		
		return newOrder;
	}
	public ArrayList<Integer> min(ArrayList<Integer> varList,BinaryCSP csp){
		ArrayList<Integer> newOrder = new ArrayList<Integer>();
		ArrayList<Integer> varDegree = new ArrayList<Integer>();
		Graph graph = new Graph(csp.getNoVariables());
		graph = creatGraph(graph,csp);
		int max =0;
		int degree = 0;
		int add = 0;
		for(Integer var:varList){
			varDegree.add(graph.getDegrees(var));
		}
		while(newOrder.size() != varList.size()) {
			max =0;
			degree = 0;
			add = 0;
			Iterator<Integer> varIt = varList.iterator();
			while(varIt.hasNext()) {
				int var = varIt.next();
				degree = varDegree.get(var);
				if(degree>max) { 
					max =degree;
					add =var;
					}
			}
			varDegree.set(add, 0);
			newOrder.add(add);
		}
		
		return newOrder;
	}
	//
	public Graph creatGraph(Graph graph,BinaryCSP csp) {
		for(int i = 0;i<csp.getNoVariables();i++) {
			for(int j = i+1;j<csp.getNoVariables();j++) {
				for(BinaryConstraint bc: csp.getConstraints()) {
					if(bc.matchesCon(i, j)) {
						graph.addEdge(i,j);
					}
				}
			}
		}
		
		return graph;
		
	}
	
}
