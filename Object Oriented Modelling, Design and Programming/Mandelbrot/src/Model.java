import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import javax.imageio.ImageIO;
/**
 * The model handles all of the user input form Delegate and.
 * feed all the information to the image
 * it have references to Mandelbrot to do this
 * 
 * @author 150011754
 *
 */
public class Model extends Observable {
	private Mandelbrot mandelbrot;
	//save the image's width and height
	private int width, height ;
	private BufferedImage image;
	//the point of the mouse
	// used in zooming and drawing the rectangle over the image
	private Point start, end; 
	private boolean dragged =false;
	/**
	 * Initialise a Mandelbrot set and set the width and height
	 * @param width -image's width and the xResolution in calculation
	 * @param height -image's height and the yResolution in calculation
	 */
	public Model(int width, int height) {
		this.mandelbrot = new Mandelbrot();
		this.width = width;
		this.height = height;
	}
	/**
	 * Method to return the Mandelbrot set 
	 * @return -a setting of Mandelbrot set 
	 */
	public Mandelbrot getMandelbert(){
		return this.mandelbrot;
	}
	/**
	 * Method to know if the mouse is dragging
	 * @return -turn means the mouse is dragged else the mouse is not dragging
	 */
	public boolean getDragged(){
		return this.dragged;
	}
	/**
	 * get the start point of the mouse
	 * @return the point which contain x and y
	 */
	public Point getStart() {
		return start;
	}
	/**
	 * get the end point of the mouse 
	 * @return same to the start
	 */
	public Point getEnd() {
		return end;
	}
	/**
	 * the image feeds the Mandelbrot which different color
	 * @return -buffered Image
	 */
	public BufferedImage getImg(){
		return image;
	}
	/**
	 * Refresh the end point 
	 * @param end -the new end point of the dragging
	 */
	public void setEnd(Point end){
		this.end = end;
	}
	/**
	 * Refresh the start point 
	 * @param start -the new start point of the dragging
	 */
	public void setStart(Point points){
		this.start = points;
	}
	/**
	 * a boolean to save the mouse state
	 * @param dragged - if the mouse is dragged
	 */
	public void setDragged(boolean dragged){
		this.dragged = dragged;
	}
	/**
	 * change the Mandelbrot Setting to get the new image.
	 * @param minReal -the lower real bound for the complex constant C (equivalent to lower bound X value in Mandelbrot set)
	 * @param maxReal -the upper real bound for the complex constant C (equivalent to upper bound X value in Mandelbrot set)
	 * @param minImaginary -the lower imaginary bound for the complex constant C (equivalent to lower bound Y value in Mandelbrot set)
	 * @param maxImaginary -the upper imaginary bound for the complex constant C (equivalent to upper bound Y value in Mandelbrot set)
	 * @param maxIterations -the maximum number of iterations to iterate the complex formula
	 */
	public void updateImg(double minReal, double maxReal, double minImaginary, double maxImaginary, int maxIterations,String color){
		mandelbrot.setMinR(minReal);
		mandelbrot.setMaxR(maxReal);
		mandelbrot.setMinI(minImaginary);
		mandelbrot.setMaxI(maxImaginary);
		mandelbrot.setIterations(maxIterations);
		mandelbrot.setColor(color);
		//notify the Delegate to pain the image
		setChanged();
		notifyObservers();
	}
	/**
	 * 	map	the	iteration values to different shades of a colour,
	 * @param color - the main color to map the Mandelbrot set image
	 */
	public void updateColor(String color){
		mandelbrot.setColor(color);
		setChanged();
		notifyObservers();
	}
	/**
	 * Create a new bufferedImage with different color by the 2-D intriguer array
	 */
	public void setImage(){
		// get the 2-D intriguer array by the new setting 
		int map[][] = MandelbrotCalculator.calcMandelbrotSet(this.width,this.height,mandelbrot.getMinR(),mandelbrot.getMaxR(),mandelbrot.getMinI(),mandelbrot.getMaxI(), mandelbrot.getIterations(), mandelbrot.getRadius());
		image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
		//create different color setting to
		int black = 0 ,max =1000, white = 0xFFFFFF;
		int[] colors = new int[max];
		for (int i = 0; i<max; i++) {
			switch (mandelbrot.getColor()){
			case "blue": // when input string is blue set the Hue is 1/1.6f so the main color is blue
				colors[i] = Color.HSBtoRGB(1/1.6f, 1, i/(i+8f));
				break;
			case "red": // when input string is red set the Hue is 1/100f so the main color is red
				colors[i] = Color.HSBtoRGB(1/100f, 1, i/(i+8f));
				break;
			case "green": //when input string is red set the Hue is 1/5f so the main color is green
				colors[i] = Color.HSBtoRGB(1/5f, 1, i/(i+8f));
				break;
				}
        }
		//scan all the 2-d array to set color
		for(int y = 0; y < this.height; y++){
			for(int x = 0;x < this.width; x++){
				int iteration = map[y][x];
				if (iteration == mandelbrot.getIterations()) { //highest iteration is black
					image.setRGB(x, y, black);
				} else {
					if(mandelbrot.getColor().equals("white")){ //while user want to see color is white, set interation 
						image.setRGB(x, y, white);}
					else{
						image.setRGB(x, y, colors[iteration]);//higher iteration numbers are mapped to brighter/whiter	shades	
						}
				}
			}
		}
	}
	/**
	 * load the file of Serialisation.which contain the object Mandelbrot.
	 * @param file -Mandelbrot object which is Serializable
	 * @throws IOException 
	 * @throws ClassNotFoundException
	 */
	public void loadFile(File file) throws IOException, ClassNotFoundException {
		FileInputStream fileIn = new FileInputStream(file);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		this.mandelbrot = (Mandelbrot) in.readObject();
		in.close();
        fileIn.close();
		setChanged();
		notifyObservers();
	}
	/**
	 * Save the image display and the name set by user
	 * @param name -the string name
	 * @throws IOException
	 */
	public void saveImage(String name) throws IOException {
		ImageIO.write(image, "png", new File(name));
	}
	/**
	 * save a .ser file which can open by this program to zoom and do and modify
	 * @param name -name is a String
	 * @throws IOException
	 */
	public void saveIFile(String name) throws IOException {
		System.out.print("a file saved name:"+name);
		FileOutputStream fileOut =new FileOutputStream(name);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this.mandelbrot);
        out.close();
        fileOut.close();
	}
	public void setWidth(int width) {
		this.width = height;
		
	}
	public void setHeight(int height) {
		this.height =height;
		
	}
}
