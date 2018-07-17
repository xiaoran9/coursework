
public class Map {

	static char[][] map;
	Position initial ;
	Position current ;
	Position bob ;
	Position goal ;

	public void loadMap(int n){
		if(n == 1){
		map = new char [][] {
			{'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O'},
			{'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'B', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
			{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'G'},};
			bob = new Position(null, 7, 8 );
			goal = new Position(null,9,9);
			current= new Position(null,0,0);
			map[0][0]='O';
			
		}
		else if(n==2){
		map = new char [][] {
				{'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
				{'O', 'X', 'O', 'X', 'O', 'O', 'B', 'O', 'O', 'X'},
				{'O', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
				{'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'G', 'O', 'I', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				};
				bob = new Position(null, 1, 6 );
				goal = new Position(null,9,0);
				current= new Position(null,9,2);
				map[9][2]='O';
		}
		else if(n==3){
		 map = new char [][] {
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
				{'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
				{'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
				};
				bob = new Position(null, 4, 8 );
				goal = new Position(null,8,4);
				current= new Position(null,4,1);
				map[4][1]='O';
		}
		else if(n==4){
		 map = new char [][] {
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
				{'O', 'I', 'O', 'O', 'O', 'X', 'X', 'O', 'B', 'O'},
				{'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'X', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
				{'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'G', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
				};
				bob = new Position(null, 4, 8 );
				goal = new Position(null,8,4);
				current= new Position(null,4,1);
				map[4][1]='O';
		}
		else if(n==5){	
		 map = new char [][] {
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'B', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'X'},
				{'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O'},
				{'O', 'X', 'O', 'O', 'G', 'O', 'X', 'O', 'I', 'O'},
				{'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
			};
			bob = new Position(null, 1, 2);
			goal = new Position(null,9,0);
			current= new Position(null,8,8);
			map[8][8]='O';
		}
		else if(n==6){	
			
		map = new char [][] {
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
				{'O', 'O', 'B', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
				{'O', 'X', 'X', 'X', 'O', 'O', 'X', 'O', 'O', 'I'},
				{'O', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O', 'O'},
				{'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O'},
				{'X', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O'},
				{'X', 'X', 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'X'},
				{'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X'},
				{'O', 'X', 'X', 'X', 'G', 'O', 'X', 'X', 'O', 'X'},
				{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O'}
				};
				bob = new Position(null, 1, 2 );
				goal = new Position(null,8,4);
				current= new Position(null,9,2);
				map[2][9]='O';
		}
		else if(n==7){	
			
			map = new char [][] {
					{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X'},
					{'O', 'O', 'B', 'O', 'O', 'X', 'X', 'O', 'O', 'O'},
					{'O', 'X', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'O'},
					{'O', 'X', 'X', 'X', 'O', 'O', 'X', 'X', 'O', 'O'},
					{'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O'},
					{'X', 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O'},
					{'X', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'X', 'X'},
					{'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X'},
					{'O', 'X', 'X', 'X', 'I', 'X', 'X', 'X', 'O', 'X'},
					{'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'G', 'O'}
					};
					bob = new Position(null, 1, 2 );
					goal = new Position(null,9,8);
					current= new Position(null,8,4);
					map[8][4]='O';
			}
	}
	public char getState(int x ,int y) {
		char state;
		 state = map[x][y];
		return state ;
	}
	public Position getBob(){
		return bob;
	}
	public Position getGoal(){
		return goal;
	}
	public void expected(Position expected,Position aim){
		
		int x =expected.getPsotionX();
		int y = expected.getPsotionY();
	    int aimX =aim.getPsotionX();
		int aimY = aim.getPsotionY();

	   if((x!=aimX&& y!=aimY)){
			   map[x][y] = '.';
		}
	}
	public void mapMoved(Position last, Position current ){
		char currentS=map[current.getPsotionX()][current.getPsotionY()];
		
		if(last !=null&& currentS !='I'){
			char lastS =map[last.getPsotionX()][last.getPsotionY()];
			if(lastS!='I'){
				map[last.getPsotionX()][last.getPsotionY()] = '-';
			}
		}
		if(currentS != 'I'){
		map[current.getPsotionX()][current.getPsotionY()] = 'R';
		}
	}
	public Position getCurrentP() {
		return current;
	}	
	public void printMap(){
		for(int i = 0;i < 10 ;i++){
			for(int j=0; j<10; j++ ){
				System.out.print(" "+map[i][j]);
			}
			System.out.println(" ");
		}
	}
	public void setStart(Position start){
		map[start.getPsotionX()][ start.getPsotionY()]='I';
	}
	

}

