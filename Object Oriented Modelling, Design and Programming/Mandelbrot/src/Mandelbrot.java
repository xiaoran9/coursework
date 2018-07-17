import java.io.Serializable;

/**
 * the Mandelbrot object implements Serializable to save the Mandelbrot setting 
 * @author 150011754
 *
 */
@SuppressWarnings("serial")
public class Mandelbrot implements Serializable {
	private double maxReal, maxImg, minReal, minImg, radius; 
	private int maxIterations;
	private String color;
	/**
	 * initial the Mandelbrot with the given values
	 */
	public Mandelbrot() {
		this.minReal = MandelbrotCalculator.INITIAL_MIN_REAL;
		this.maxReal = MandelbrotCalculator.INITIAL_MAX_REAL;
		this.minImg = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
		this.maxImg = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
		this.radius = MandelbrotCalculator.DEFAULT_RADIUS_SQUARED;
		this.maxIterations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;
		this.color = "white";
	}
	/**
	 * return the value of  maxReal 
	 * @return -a double value of  maxReal 
	 */
	public double getMaxR() {
		return this.maxReal;
	} 
	/**
	 * return the value of  minReal 
	 * @return -a double value of  minReal 
	 */
	public double getMinR() {
		return this.minReal;
	}
	/**
	 * return the value of  maxImg 
	 * @return -a double value of  maxImg 
	 */
	public double getMaxI() {
		return this.maxImg;
	}
	/**
	 * return the value of  minImg 
	 * @return -a double value of  minImg 
	 */
	public double getMinI() {
		return this.minImg;
	}
	/**
	 * return the value of  maxIterations 
	 * @return -a int value of  maxIterations 
	 */
	public int getIterations() {
		return this.maxIterations;
	}
	/**
	 * return the value of  maxIterations 
	 * @return -a string of color
	 */
	public String getColor() {
		return this.color;
	}
	/**
	 * return the value of  radius 
	 * @return -a double value of  radius 
	 */
	public double getRadius() {
		return this.radius;
	}
	/**
	 * change the color as input one
	 * @param color -the string of colors name
	 */
	public void setColor(String color) {
		 this.color = color;
	} 
	/**
	 * set the value of maxReal
	 * @param maxReal -the MaxReal of new Mandelbrot set
	 */
	public void setMaxR(Double maxReal) {
		 this.maxReal = maxReal;
	}
	/**
	 * set the value of minReal
	 * @param minReal -the minReal of the new  Mandelbrot set
	 */
	public void setMinR(Double minReal) {
		 this.minReal = minReal;
	}
	/**
	 * set the value of maxImg
	 * @param maxImg -the maxImg of the new  Mandelbrot set
	 */
	public void setMaxI(Double maxImg) {
		 this.maxImg = maxImg;
	}
	/**
	 *  set the value of minImg
	 * @param minImg -the minImg of the new  Mandelbrot set
	 */
	public void setMinI(Double minImg) {
		 this.minImg = minImg;
	}
	/**
	 *  set the value of maxIterations
	 * @param maxIterations -the maxIterations of the new  Mandelbrot set
	 */
	public void setIterations(int maxIterations) {
		 this.maxIterations =maxIterations;
	}
	
}
