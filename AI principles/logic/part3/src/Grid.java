public class Grid {
	int value;
	int x;
	int y;
	boolean visit;
	boolean flag;
	int markedNeigh;
	int coverededNeigh;
	String n;
	public Grid(int x,int y){
			this.x = x;
			this.y = y;
		}
	
	public Grid(int value, boolean visit, boolean flag,int x,int y){
		this.value = value;
		this.visit = visit;
		this.flag = flag;
		this.x = x;
		this.y = y;
		this.n ="N"+x+y;
		this.markedNeigh = 0;
		this.coverededNeigh =0;
	}
	public void setMarked(int number){
		this.markedNeigh = number;
	}
	public void setCovered(int number){
		this.coverededNeigh = number;
	}
	public void setValue(int value){
		this.value = value;
	}
	public void setFlag( boolean flag){
		this.flag = flag;
	}
	public void setVisit( boolean visit){
		this.visit = visit;
	}
	public String getString(){
		return this.n;
	}
	public int getMarked(){
		return this.markedNeigh;
	}
	public int getCovered(){
		return this.coverededNeigh;
	}
	public int getY(){
		return this.y;
	}
	public int getX(){
		return this.x;
	}
	public int getValue() {
		return this.value;
	}
	public boolean getFlag() {
		return this.flag;
	}
	public boolean getVisit() {
		return this.visit;
	}
	
public Grid neighbour(int i) {
		int nextX = this.x;
		int nextY = this.y;
		Grid grid1 = null;
		switch(i){
		case 1:
			grid1 = new Grid(nextX-1,nextY-1);
			break;
		case 2:
			grid1 = new Grid(nextX-1,nextY);
			break;
		case 3:
			grid1 = new Grid(nextX-1,nextY+1);
			break;
		case 4:
			grid1 = new Grid(nextX,nextY-1);
			break;
		case 5:
			grid1 = new Grid(nextX,nextY+1);
			break;
		case 6:
			grid1 = new Grid(nextX+1,nextY-1);
			break;
		case 7:
			grid1 = new Grid(nextX+1,nextY);
			break;
		case 8:
			grid1 = new Grid(nextX+1,nextY+1);
			break;
		
		}
		return grid1;
	}
	
 
	
	
	@Override
	public String toString() {
//		return "("+x+","+y+"): "+value+" ";
		return " "+value+" ";
	}
	public boolean equals(Object o) {
		if(o instanceof Grid){
			Grid other = (Grid) o;
			return x == other.x && y == other.y;
		}
		return false;
	}
}

