
public class Triangle implements Comparable<Triangle>{
	private Point p1;
	private Point p2;
	private Point p3;
	private Point normal;
	private double centroidX;
	private double centroidY;
	private double centroidZ;
	private double texture;
	private double distance;
	public static final double xMax = 89369.460938;
	public static final double xMin = -89604.382813;
	public static final double yMax = 92407.40625;
	public static final double yMin = -111224.070313;
	public static final double zMax = 139986.234375;
	public static final double zMin = -21022.482422;
	public static final double rangeX = xMax-xMin;
	public static final double rangeY = yMax-yMin;
	public static final double rangeZ = zMax-zMin;
	public static final int WIDTH = 400;
	public static final int HEIGHT = 550 ;

	public Triangle(Point p1,Point p2,Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.centroidX = (p1.getX() + p2.getX() +p3.getX())/3;
		this.centroidY = (p1.getY() + p2.getY() +p3.getY())/3;
		this.centroidZ = (p1.getZ() + p2.getZ() +p3.getZ())/3;
		this.distance = centroidZ;
		calNormal();
	}
	// cross product a * b = (ay*bz -az*by)x +(az*bx-ax*bz)Y +(ax*by -ay*bx)Z
	public void calNormal(){
		double Xa = p1.getX() -p2.getX();
		double Ya = p1.getY() -p2.getY();
		double Za = p1.getZ() -p2.getZ();
		double Xb = p1.getX() -p3.getX();
		double Yb = p1.getY() -p3.getY();
		double Zb = p1.getZ() -p3.getZ();
		double x = Ya*Zb -Za*Yb;
		double y = Za*Xb -Xa*Zb;
		double z = Xa*Yb - Ya*Xb;
		double length = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2)+Math.pow(z, 2));
		
		this.normal = new Point(-x/length,-y/length,-z/length);
	}
	// distance from the points to screen 
	public void setDist(Point camN) {
		distance = (-centroidX*camN.getX()+centroidZ*camN.getZ());
	}
	public void setTexture() {
		this.texture = (p1.getTexture()+p2.getTexture()+p3.getTexture())/3;
	}
	public void setNormal(double x,double y,double z) {
		this.normal = new Point(x,-y,z);
	}
	public double getDist(){
		return distance;
	}
	public Point getNormal(){
		return normal;
	}
	public Point getP1(){
		return p1;
	}
	public Point getP2(){
		return p2;
	}
	public Point getP3(){
		return p3;
	}
	public double getCentroidX(){
		return centroidX ;
	}
	public double getCentroidY(){
		return centroidY;
	}
	public double getCentroidZ(){
		return centroidZ;
	}
	public int[] getPointsX(Point camN,int angle) {
		//scale the shape as the screen size
		int xpoints[] = new int[3];
		xpoints[0] = (int) (((p1.getX() -xMin)/rangeX)*WIDTH *camN.getZ() + (p1.getZ()- zMin)*WIDTH/rangeZ *camN.getX())+WIDTH;
		xpoints[1] = (int) (((p2.getX() -xMin)/rangeX)*WIDTH *camN.getZ() + (p2.getZ()- zMin)*WIDTH/rangeZ*camN.getX())+WIDTH;
		xpoints[2] = (int) (((p3.getX() -xMin)/rangeX)*WIDTH *camN.getZ() + (p3.getZ()- zMin)*WIDTH/rangeZ*camN.getX())+WIDTH;
		
		return xpoints;
	}
	public int[] getPointsY() {
		int ypoints[] = new int[3];
		ypoints[0] = (int) (((-p1.getY() -yMin)/rangeY)*HEIGHT);
		ypoints[1] = (int) (((-p2.getY() -yMin)/rangeY)*HEIGHT);
		ypoints[2] = (int) (((-p3.getY() -yMin)/rangeY)*HEIGHT);
		return ypoints;
	}
	public double getTexture() {
		return texture;
	}
	 public String toString() {
		 StringBuffer result = new StringBuffer() ;
		 result.append("["+p1+"\n ") ;  
		 result.append(p2+"\n ") ;  
		 result.append(p3+"]\n") ;
		 result.append(texture+"]\n") ;
		 result.append("\n") ; 
		 return result.toString() ;
	}
	@Override
	public int compareTo(Triangle o) {
		Triangle tri = o;
		if(this.distance > tri.distance) return 1;
		if(this.distance < tri.distance) return -1;
		return 0;
	}
}
