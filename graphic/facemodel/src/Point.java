
public class Point {
	double x;
	double y;
	double z;
	double texture;
	public Point(double x,double y,double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	public double getZ(){
		return z;
	}
	public void setTexture(double t) {
		this.texture = t;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
	public void setZ(double z) {
		this.z = z;
	}
	 public String toString() {
		 StringBuffer result = new StringBuffer() ;
		 result.append("("+x+", "+y+", "+z+")") ;  
		 return result.toString() ;
	}
	public double getTexture() {
		return texture;
	}
		  
}
