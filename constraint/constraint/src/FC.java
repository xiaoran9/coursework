import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
public class FC {
	BinaryCSP csp;
	int nodeNum;
	long arcRevNum;
	HashMap<Integer, ArrayList> domainList;
	HashMap<Integer, Integer>solution;
	Heuristic heuristic;
	long startTime;
	int h;
	public FC(BinaryCSP csp,int h) {
		this.csp = csp;
		domainList = new HashMap<Integer, ArrayList> ();
		createDomainList(); 
		this.solution = new HashMap<Integer, Integer>();
		heuristic = new Heuristic();
		nodeNum =1;
		arcRevNum = 0;
		startTime = System.currentTimeMillis();
		this.h = h;
	} 
	
	public void forwardChecking(ArrayList<Integer> varList) {
		//check if the varList is empty, 
		//empty means it find solution
		if(varList.isEmpty()) {
			System.out.println("show solution:");
			for(int i = 0; i<solution.size();i++) 
				System.out.println("Var "+i+": "+solution.get(i));
			long endTime = System.currentTimeMillis();
			System.out.println("time taken :"+ (endTime-startTime)+" ms");
			System.out.println("node number :"+ nodeNum);
			System.out.println("arc Revision number :"+ arcRevNum);
			System.exit(0);	
		}
		if(h==3)
			varList = heuristic.dynamicH(varList, domainList);
		
		int var = varList.get(0); 
		int val = (int) domainList.get(var).get(0);
		
		//the variable == value 
		branchFCLeft(varList,var,val);
		//the variable != value
		branchFCRight(varList,var,val);
		}
	private void branchFCLeft(ArrayList<Integer> varList, int var, int val) {
		nodeNum++;
		//assign the var = val in sollution 
		solution.put(var,val);
		
		//save the current domain situation
		//if var != val, undo pruning can copy back to this situation
		HashMap<Integer, ArrayList> domainNew = copyDomain(this.domainList);
		
		//reviseFutherArcs change the variable's domain 
		//if there are no variable's domain empty 
		if(reviseFutureArcs(varList,var)) { 
			//assume this variagle can be assigned and create a new variable which delete the assigned variable
			ArrayList<Integer> varList1= new ArrayList<Integer>();
			for(Integer i: varList) {
				if(i != var) varList1.add(i);
			}
			
			forwardChecking(varList1);
		}
		//undo pruning, get the domain list which not changed by reviseFutureArcs
		this.domainList = domainNew;
		//unassign variable 
		solution.remove(var);
	}
	
	private void branchFCRight(ArrayList<Integer> varList, int var, int val) {
		nodeNum++;
		//delete value in var's domain
		deleteValue(var,val);
		//if var is not empty,it can try to use assign the other value 
		if(!domainList.get(var).isEmpty()) {
			HashMap<Integer, ArrayList> domainNew = copyDomain(this.domainList);
			if(reviseFutureArcsR(varList,var)) {
				//pruning the future domain that only support by var =val
				forwardChecking(varList);
			}
			//undo pruning
			this.domainList = domainNew;
		}
		restoreValue(var,val);	
	}
	private void restoreValue(int var, int val) {
		domainList.get(var).add(0,val);
		
	}
//	delete the value in domain var
	private void deleteValue(int var, int val) {
		Iterator<Integer> it = domainList.get(var).iterator();
		while(it.hasNext()) {
			if(it.next()==val) {
				it.remove();
				break;
			}
		}
	}
	//this is the left branch arc revision, it can attempt every possible (val,futureVal)
	//if can't find tupel as (val,futureVal), it may delete the future vale form domain
	//if the future var is not empty, return ture
	//else return false
	private boolean reviseFutureArcs(ArrayList<Integer> varList, int var) {
		boolean support = true;
		//check all future variable expect var
		for(int i =1;i<varList.size();i++){
			Iterator<Integer> dFuture = domainList.get(varList.get(i)).iterator();
			while(dFuture.hasNext()) {
				int futureVal =dFuture.next();
				int val = (int) domainList.get(var).get(0);
				//tuple (val,future) exist in c(var,futureVar) 
				support= revise( futureVal, val,varList.get(i),var);
				if(!support){
					//if not exist, remove futureVar in future domain
					dFuture.remove();
					if(domainList.get(varList.get(i)).isEmpty()) {
						 return false;
					 }
				}
			}
		}
		return true;
	}

	//the right hand arcs revise,
	//it check all the future variable if they can supported by the remain value of var
	//return true means no domain empty.
	private boolean reviseFutureArcsR(ArrayList<Integer> varList, int var) {
		for(int j =1;j<varList.size();j++){
			Iterator<Integer> dFuture = domainList.get(varList.get(j)).iterator();
			while(dFuture.hasNext()) {
				boolean support = false;
				int futherVal =dFuture.next();
				//check all value in domain of variable 
				for(int i = 0;i< domainList.get(var).size();i++){
					int val = (int) domainList.get(var).get(i);
					support= revise( futherVal, val,varList.get(j),var);
					if(support)
						break;
				}
				if(!support){
					dFuture.remove();
					if(domainList.get(varList.get(j)).isEmpty()) 
						 return false;
				}
			}
		}
		return true;
	}
	//create domain list
	private void createDomainList() {
		for(int j = 0;j<this.csp.getNoVariables();j++) {
			ArrayList<Integer> domain = new ArrayList<Integer>();
			
			for(int i = this.csp.getLB(j);i<=this.csp.getUB(j);i++) {
				
				domain.add(i);
			}
			this.domainList.put(j,domain);
		}
	}
	//check if in constraints c(depth, future) or C(future,depth) have associated tuple:
	//depth =valueD; future = valueF
	 private boolean revise(int valueF,int valueD,int future,int depth) {
		 Iterator<BinaryConstraint> constraints = csp.getConstraints().iterator();
		 while(constraints.hasNext()) {
			 BinaryConstraint constr = constraints.next();
			 this.arcRevNum++;
			 if(constr.matchesCon(depth, future)) {
				if( constr.checkTuple(valueF,valueD)) {
					return true;
				}
				return false;
			 }
			 if( constr.matchesCon(future, depth)) {
					if( constr.checkTuple(valueD,valueF)) {
						return true;
					}
					return false;
				 }
		 }
		 
		 return true;
	 }
	 //copy the current domain situation 
	 private HashMap<Integer,ArrayList> copyDomain(HashMap<Integer,ArrayList> history){
		 HashMap<Integer,ArrayList> domainList = new HashMap<Integer,ArrayList>();
			for(int i =0;i<csp.getNoVariables();i++) {	
				ArrayList<Integer> domain = new ArrayList<Integer> (history.get(i));
				domainList.put(i,domain);
			}
			return domainList;
		}

}

