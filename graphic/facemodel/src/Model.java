import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {
	public static final double centroidX = -117.4609375;
	public static final double centroidY = 9408.332031500002;
	public static final double centroidZ = -59481.8759765;
	private int width, height ;
	private List<Triangle> triangles;
	public BufferedImage image ;
	private boolean lightOn =false;
	private Point light;
	private double Intensity=1;
	private double coeff=0.7;
	private double PI = Math.PI;
	private int angle;
	private Point camN;
	private int D =150000;
	
	public Model(int width, int height) {
		this.width = width;
		this.height = height;
		this.angle = 0;
		this.camN = new Point(0,0,1);
		triangles = Reader.readTexture("face-texture.txt",Reader.readShape("face-shape.txt"));
		updateTem(angle);
		this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.light = new Point(-117,-9408.0,200000);
	}	
	public void update() {
		setChanged();
		notifyObservers();	
	}
	public void updateTem(int tem) {
		setCamN(tem);
		setOrder(tem);
		Collections.sort(triangles);
	}
	public void setOrder(int tem) {
		for(Triangle tri: this.triangles) {
			tri.setDist(camN);
		}
	}
	public void setAngle(int angle) {
		this.angle = angle;
	}
	
	public void setCamN(int tem) {
		camN.setX(Math.sin(PI*tem/180));
		camN.setZ(Math.cos(PI*tem/180));
	}
	public void setI(double intensity) {
		this.Intensity = intensity;
	}
	public void setTurnOn() {
		this.lightOn = true;
	}
	public void setLS(double x, double y, double z) {
		this.light = new Point(x,y,z);
	}
	public BufferedImage getImg() {
		return image;
	}
	public List<Triangle> getTti() {
		return triangles;
	}
	public boolean getLight() {
		return lightOn;
	}
	public Point getLS() {
		return light;
	}
	public double getC(){
		return coeff;
	}
	public double getI(){
		return Intensity;
	}
	public Point getCamN() {
		return camN;
	}
	public int getAngle(){
		return angle;
	}
	
}
