
public class KnowledgeBase {
	private Grid[][] map = null;
	private int size ;
	private int covered = 0;
	private int marked = 0;
	public KnowledgeBase(int size) {
		this.size =size;
		map = new Grid[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				this.map[i][j] = new Grid(9,false,false,i,j);
				covered++;
			}
		}
	}
	
	public void update(int x, int y,int value) {
		map[x][y].setValue(value);
		map[x][y].setVisit(true);
		covered--;
		System.out.println("Reveal grid ("+x+","+y+")"+" and covered grid number is :"+covered);
	}
	public void setflag(int x, int y){
		map[x][y].setFlag(true);
		map[x][y].setValue(10);
		marked++;
		System.out.println("mark grid ("+x+","+y+")"+" and marked grid number is :"+marked);
	}
	public int getCoveredNum(){
		return this.covered;
	}
	public int getMarkedNum(){
		return this.marked;
	}
	public Grid getGrid(int x,int y){
		return map[x][y];
	}
	public int getSize(){
		return this.size;
	}
	public void print() {
		
		for(int i = 0;i < size ;i++){
			for(int j=0; j<size ; j++ ){
				System.out.print(" "+map[i][j]);
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}
	
}
