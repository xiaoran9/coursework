package assignment1;
import java.awt.Point;
import java.util.ArrayList;
public class BezierCurves {
	private static  double[] curveX ;
	private static double[] curveY ;
	private int times;
	//Initialise the Bezier curves
	public BezierCurves(int times) {
		this.times =times;
		this.curveX = new double[times];
		this.curveY = new double[times];
	}
	public  double[] getCurveX(){
		return this.curveX;
	}
	public  double[] getCurveY(){
		return this.curveY;
	}
	public void clearnCurve() {
		this.curveX = null;
		this.curveY = null;
	}
	//calculate the curve by 1000 times 
	public void drawCurve( ArrayList<Point> points) {
		for(int t=0;t<this.times;t++) {
			calcPoint(points,((double)1/this.times)*(t+1),t);
		}
	}
	//caloculate each points 
	public void calcPoint( ArrayList<Point> points,Double t,int time) {
		double resultX = 0, resultY = 0;
		int N = points.size();
		ArrayList<Double> x = new ArrayList<>();
		ArrayList<Double> y = new ArrayList<>();
		ArrayList<Double> tTimes = new ArrayList<>();
		ArrayList<Double> times = new ArrayList<>();
		tTimes.add(1.0);
		times.add(1.0);
		
		for(int i=1;i<N;i++) {
			tTimes.add(t*tTimes.get(i-1)); //T[i] = t^i
			times.add((1-t)*times.get(i-1));//T'[i] = (1-t)^i
		}
		for(int i = 0; i < N;i++) {
			//Pi=(N i)*(1-t)i * t I * p’i *
			resultX += (double)(times.get(N-i-1)*tTimes.get(i)*points.get(i).x*(factorial(N-1)/(factorial(i)* factorial(N-i-1))));
			resultY += (double)(times.get(N-i-1)*tTimes.get(i)*points.get(i).y*(factorial(N-1)/(factorial(i)* factorial(N-i-1))));
		}
		this.curveX[time] =resultX;
		this.curveY[time] = resultY;
	}
	//factorial n is 1*2*...*n
	private static int factorial(int n) {
		int result = 1;
		if(n>0) {
			for (int i =1; i<=n;i++) {
				result = result * i;
			}
		}
		return result;
	}
}