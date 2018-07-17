
public class Grid {
	int value;
	int x;
	int y;
	boolean visit;
	boolean flag;
	
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
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
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

