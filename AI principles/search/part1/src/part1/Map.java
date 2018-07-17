package part1;

public class Map {

	static char[][] map;
	Position current;

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
				current= new Position(null,9,2);
				map[9][2]='O';
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
					current= new Position(null,8,4);
					map[9][2]='O';
			}
		
	}
	public char getState(int x ,int y) {
		char state;
		state = map[x][y];
		return state ;
	}
	
	public void expected(Position expected,char aim){
		int x =expected.getPsotionX();
		int y = expected.getPsotionY();
		 char state = map[expected.getPsotionX()][expected.getPsotionY()];
		   System.out.println("aim is"+ aim);
		   System.out.println("state is"+ state);
		   if(state!='I'&&state != aim){
			   map[x][y] = '.';
		}
	}
	
	public void mapMoved(Position last, Position current,char aim ){
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

