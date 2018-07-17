import java.util.* ;

public final class BinaryConstraint {
  private int firstVar, secondVar ;
  private ArrayList<BinaryTuple> tuples ;
  
  public BinaryConstraint(int fv, int sv, ArrayList<BinaryTuple> t) {
    firstVar = fv ;
    secondVar = sv ;
    tuples = t ;
  }
  
  public String toString() {
    StringBuffer result = new StringBuffer() ;
    result.append("c("+firstVar+", "+secondVar+")\n") ;
    for (BinaryTuple bt : tuples)
      result.append(bt+"\n") ;
    return result.toString() ;
  }
  // SUGGESTION: You will want to add methods here to reason about the constraint


  public boolean matchesCon(int v1, int v2) {
	    return (firstVar == v1) && (secondVar == v2) ;
	  }
  public boolean matchesVar1(int v) {
	    return (firstVar == v) ;
	  }

  public boolean matchesVar2(int v) {
	    return(secondVar == v) ;
	  }
  public int getVar1() {
	    return firstVar ;
	  }

public int getVar2() {
	    return secondVar ;
	  }

 public boolean checkTuple(int valueF,int valueD) {
	  Iterator<BinaryTuple> it = tuples.iterator();
	  while(it.hasNext()) {
		  BinaryTuple bt =it.next();
		  if(bt.matches(valueD, valueF )) {
			  return true;
		  } 
	  }
	return false;	 
 }
}
