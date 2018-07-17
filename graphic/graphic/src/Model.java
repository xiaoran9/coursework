package assignment1;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;

/**
 * The model handles all of the user input form Delegate and.
 * feed all the information to drawing area
 */
public class Model extends Observable {
	private int width,height,samples,times;
	private ArrayList<BezierCurves> outputCurves;
	private ArrayList<ArrayList> inputList;
	private boolean lightOn,click,enter,reset;
	private Point lightSource;
	
	public Model(int width, int height) {
		this.width = width;
		this.height = height;
		this.samples =50;
		this.times =1000;
		this.outputCurves = new ArrayList<BezierCurves>();
		this.inputList =new ArrayList<ArrayList>();
		ArrayList<Point> points = new ArrayList<Point>();
		BezierCurves bezierCurves = new BezierCurves(times);
		inputList.add(points);
		outputCurves.add(bezierCurves);
		this.lightOn =false;
		this.click =false;
		this.enter =false;
		this.reset=false;
	}
	//update the drawing area
	public void update() {
		setChanged();
		notifyObservers();	
	}
	/**
	 * draw curves
	 */
	public void drawCurve() {
		this.outputCurves.get(outputCurves.size()-1).drawCurve(inputList.get(inputList.size()-1));
		setChanged();
		notifyObservers();
	}
//	public void newCurve() {
//		ArrayList<Point> points = new ArrayList<Point>();
//		BezierCurves bezierCurves = new BezierCurves(times);
//		this.inputList.add(points);
//		this.outputCurves.add(bezierCurves);
//		System.out.println(outputCurves.size());
//	}
	
	//add the point to the list
	public void setClickPoint(Point p) {
		this.inputList.get(inputList.size()-1).add(p);
	}
	//get the input list of  different curves but it dos't finish
	public ArrayList<ArrayList> getInput() {
		return inputList;
	}
	//get the points of list
	public ArrayList<Point> getPoint(int index) {
		return inputList.get(index);
	}
	//get the curves 
	public ArrayList<BezierCurves> getOutput() {
		return outputCurves;
	}
	
	/**
	 * set the modify type by click and,reset and entry 
	 * enter 
	 */
	public void setClick(boolean click){
		this.click = click;
	}
	public boolean getClick(){
		return this.click;
	}
	public boolean getEnter(){
		return this.enter;
	}
	public void setEnter(boolean enter){
		this.enter = enter;
	}
	public void setReset(boolean reset){
		this.reset = reset;
	}
	public boolean getReset(){
		return this.reset;
	}

	/**
	 * the part is control the light source
	 */
	public boolean getLightModel() {
		return lightOn;
	}
	public void setTurnOn() {
		this.lightOn = true;
	}
	public void setTurnOff() {
		this.lightOn = false;
	}
	public Point getLightSource() {
		return this.lightSource;
	}
	public void setLightSource(Point lightSource) {
		this.lightSource = lightSource;
	}
	/**
	 * set the samples of curve in the range of 3 to 100
	 * @return
	 */
	public int getSample() {
		return samples;
	}
	public void setSample(int samples) {
		this.samples= samples;
	}
	//reset
	public void clearPoints() {
		for(int i=0; i< this.inputList.size();i++) {
			for(int n =0;n<  this.inputList.get(i).size();n++) {
				this.inputList.get(i).clear();
			}
			this.inputList.get(i).clear();
			
		}
		
	}
}
