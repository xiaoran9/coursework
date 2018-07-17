import java.util.ArrayList;

public class History {
	ArrayList<ArrayList> domainList;
	
	public History(ArrayList<ArrayList> domainList) {
		this.domainList = domainList;
	}
	
	public String toString() {
		StringBuffer result = new StringBuffer() ;
		for (int i = 0; i < this.domainList.size(); i++) {
			result.append("Var "+i+": ");
			for(int j = 0;j<this.domainList.get(i).size();j++){
			   result.append(this.domainList.get(i).get(j)+" ") ;
			   }
			result.append("\n");
		}
		return result.toString();
	}
	public ArrayList<Integer> getDomain(int depth){
		return domainList.get(depth);
		
	}
	public int getSize() {
		return this.domainList.size();
	}
	
}
