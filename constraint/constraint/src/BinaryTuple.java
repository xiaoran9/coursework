/**
 * Assumes tuple values are integers
 */
public final class BinaryTuple {
  private int val1, val2 ;
  
  public BinaryTuple(int v1, int v2) {
    val1 = v1 ;
    val2 = v2 ;
  }
  
  public String toString() {
    return "<"+val1+", "+val2+">" ;
  }
  
  public boolean matches(int v1, int v2) {
    return (val1 == v1) && (val2 == v2) ;
  }
  //add some new matches to only one val
  public boolean matches1(int v1) {
	    return (val1 == v1) ;
	  }
  public boolean matches2(int v2) {
	    return (val2 == v2) ;
	  }
  public int getVal1() {
	  return val1;
  }
  public int getVal2() {
	  return val2;
  }
}
