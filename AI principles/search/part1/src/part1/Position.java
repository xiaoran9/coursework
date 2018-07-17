package part1;

import java.util.ArrayList;

public class Position {
	 private int positionX;
	 private int positionY;
	 private Position parent;
	private  ArrayList<Position>  expected = new ArrayList<Position>();
	
	public Position(Position parent, int x, int y){
		this.parent=parent;
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
	public void changeParent(Position parent){
		this.parent =parent;
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
	@Override
	public String toString(){
		return "("+ positionX+","+positionY+")";
	}
}
