import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Reader {
	//read the shape and save it in the triangle 
	public static ArrayList<Triangle> readShape(String filename) {
		Scanner sfr = null;
		Queue<Point> tri = new LinkedList<Point>();
		ArrayList<Triangle> triangles = new ArrayList<Triangle>();
		try{
			sfr = new Scanner(new FileReader(filename));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		while (sfr.hasNextLine()){
			double[] p = new double[3];
			Scanner s2 = new Scanner(sfr.nextLine());
			int i = 0;
			boolean newP =false;
			while(s2.hasNext()){
				String s = s2.next();	
				p[i]=  Double.parseDouble(s);
				i++;
				newP =true;
			}
			if(newP){
				Point point = new Point(p[0],p[1],p[2]);
				tri.add(point);
			}else{
				Triangle t = new Triangle(tri.poll(),tri.poll(),tri.poll());
				triangles.add(t);
			}
		}
		
		return triangles;
	}
	//add all texture into associate points
	public static List<Triangle> readTexture(String filename, List<Triangle> tri) {
		Scanner sfr = null;
		try{
			sfr = new Scanner(new FileReader(filename));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
		for(Triangle t: tri) {
			Scanner s2 = new Scanner(sfr.nextLine());
				if(!s2.hasNext()){
					s2 = new Scanner(sfr.nextLine());
				}
				t.getP1().setTexture(Double.parseDouble(s2.next()));
				t.getP2().setTexture(Double.parseDouble(s2.next()));
				t.getP3().setTexture(Double.parseDouble(s2.next()));
				t.setTexture();
			
		}
		return tri;
	}
}		
			


