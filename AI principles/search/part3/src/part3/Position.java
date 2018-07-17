package part3;


import java.util.ArrayList;

public  class Position implements Comparable<Position>{
	 private int positionX;
	 private int positionY;
	 private Position parent;
	 private  int priority;
	 private  ArrayList<Position>  expected = new ArrayList<Position>();
	 public Position(Position parent, int x, int y, int priority){
		    this.parent=parent;
			positionX =x;
			positionY =y;
			this.priority=priority;
		}
	 public Position(Position parent, int x, int y){
			this.parent=parent;
			positionX =x;
			positionY =y;
		}
	 public Position( int x, int y){
			positionX =x;
			positionY =y;
		}
	public Position getParent(){
		return parent;
	}
	public int getPsotionX(){
		return positionX;
	}
	public int getPsotionY(){
		return positionY;
	}
	 public int getPriority(){
			return priority;
		}
	public void changePriority(int pathCost){
		this.priority += pathCost;
	}
	
	public void addExpected(Position children){
		expected.add(children);
	}
	public void printExpected(){
		for(Position e: expected){
			System.out.print(e);
		}
		System.out.println(" ");
	}

	public int compare(Position p1,Position p2){
			return p1.getPriority()-p2.getPriority();
		}
	@Override
	public String toString(){
		return "("+ positionX+","+positionY+")";
	}

	@Override
	public int compareTo(Position o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
