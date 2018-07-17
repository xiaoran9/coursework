package assignment1;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
/**
 * draw the curve
 *
 */
public class ImagePanel extends JPanel implements ActionListener{
	private Model model;
	Timer time = new Timer(10,this);
	int tem = 1;
	public ImagePanel(int width, int height, Model model) {
		this.setSize(width,height);
		this.model = model;
	}
	@Override
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		//if the model it not reset 
		if(!model.getReset()) {
			// if click on the drawing area  draw the points
			if(model.getClick() ) {
				drawPoint(g);
			}
			//only in the input points time can draw line for each points
			if(!model.getEnter() && !model.getLightModel()) {
				drawLine(g);
			}
			//draw curves
			if(model.getEnter() || model.getLightModel()) {	
				drawCurve(g);
			}
		}
		if (model.getEnter()) {
			model.setEnter(false);
			}
		if (model.getReset()) {
			model.setReset(false);
			}
	}
	
	private void drawPoint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		Point select = new Point();
		//draw blue points for input 
		if(!model.getLightModel()){
			g.setColor(Color.blue);
			for(int i =0;i<model.getInput().size();i++) {
				for(int n = 0;n<model.getPoint(i).size();n++) {
					select = model.getPoint(i).get(n);
					Ellipse2D.Double circle = new Ellipse2D.Double(select.x,select.y,10,10);
					g2d.fill(circle);
				}
			}
		}
		else{//draw yellow points for light source 
			g.setColor(Color.yellow);
			select =model.getLightSource();
			Ellipse2D.Double circle = new Ellipse2D.Double(select.x,select.y,10,10);
			g2d.fill(circle);
			double[] x =model.getOutput().get(model.getOutput().size()-1).getCurveX();
			double[] y =model.getOutput().get(model.getOutput().size()-1).getCurveY();
			for(int i =0;i<model.getInput().size();i++) {
				light( x,y, model.getLightSource(), g,model.getSample());
			}
		}
	}
	//draw black line between tow input points
	private void drawLine(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		Point p1;
		Point p2;
		for(int i =0;i<model.getInput().size();i++) {
			for(int n=0;n<model.getPoint(i).size()-1;n++) {
				p1 =model.getPoint(i).get(n);
				p2 =model.getPoint(i).get(n+1);
				g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		}
	}

	private void drawCurve(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.black);
		for(int i =0;i<model.getOutput().size();i++) {
			double[] x =model.getOutput().get(i).getCurveX();
			double[] y =model.getOutput().get(i).getCurveY();
			Point p=new Point();
			
			for(int t=0;t<1000;t++) {
				p.x =(int)x[t];
				p.y =(int)y[t];
				g2d.drawLine(p.x,p.y,p.x,p.y);
			}
		}
	}
	
	//draw the gray scale value for samples
	public void light(double[] x,double[] y,Point light,Graphics g,int samples) {
		Graphics2D g2d = (Graphics2D) g;
		time.start();
		double[] lightVecX = new double[samples+1],lightVecY = new double[samples+1],normalX = new double[samples+1],normalY= new double[samples+1];
		Point[] line = new Point[samples];
		double vectorX ,vectorY;
		Point a = new Point(),b=new Point();
		int lenth =(int)(1000/samples);

		a.x = (int) x[0];
		a.y = (int) y[0];
		line[0] = a;
		
		for(int i=0;i<samples;i++) {
			
			//light path
			vectorX =  light.x -(x[i*lenth]);
			vectorY =  light.y- (y[i*lenth]);
			//unit path
			lightVecX[i] = (vectorX/Math.sqrt(vectorX*vectorX+vectorY*vectorY ));
			lightVecY[i] = (vectorY/Math.sqrt(vectorX*vectorX+vectorY*vectorY ));
			
			//self-occlusio
			if(i>0 && i<samples) {
				int xx = (int) x[i*lenth+(int)lenth/2];
				int yy= (int) y[i*lenth+(int)lenth/2];
				a = new Point(xx,yy);
				line[i] =a;
			}
		}
	
		for(int i=0;i<tem;i++) {
			//tangent line 
			vectorX =  x[i*lenth]-x[i*lenth+1];
			vectorY =  y[i*lenth]-y[i*lenth+1];
			// unit normal vector
			normalX[i] =  -(vectorY/Math.sqrt(vectorX*vectorX+vectorY*vectorY ));
			normalY[i] = (vectorX/Math.sqrt(vectorX*vectorX+vectorY*vectorY ));
			
			a=new Point();
			int result = 0;
			a.x = (int) x[i*lenth];
			a.y = (int) y[i*lenth];
			
			for(int n=1;n<samples;n++) {
				if(n!=i) { //delete the segment that the current sample is in 
					if(!(i == 0 && n==1)) {  // the first segment and the first point
						if(!across(line[n-1],line[n],light,a)){ //calculate the dot product
						result = (int)((lightVecX[i]*normalX[i]+lightVecY[i]*normalY[i])*255);
						}
					else {// the point block by others 
						result =0;
						break;
						}
					}
				}
			}
			
			if(result<0) {result =0;}
			//color range from balck to white is form 0 to 255
			Color reflect = new Color(result, result,result);
			
			g2d.setColor(reflect);
			Ellipse2D.Double circle = new Ellipse2D.Double((int)x[i*lenth],(int)y[i*lenth],10,10);
			g2d.fill(circle);
			g.setColor(Color.red);
			//draw the light path
			g2d.drawLine((int)x[i*lenth],(int)y[i*lenth],model.getLightSource().x,model.getLightSource().y);
		}
	}
	//compare two segments. if they across return true , if not return false
	public boolean across(Point a1,Point a2,Point b1,Point b2) {
		if ( (Math.max(a1.x, a2.x)< Math.min(b1.x, b2.x)) ||
			  Math.max(a1.y, a2.y)< Math.min(b1.y, b2.y) || 
			  Math.max(b1.x, b2.x)< Math.min(a1.x, a2.x) ||
			  Math.max(b1.x, b2.x)< Math.min(a1.x, a2.x) ||
			  Math.max(b1.y, b2.y)< Math.min(a1.y, a2.y) ) {  
	        return false;  
	    }  
	    if ( mult(b1, a2, a1)* mult(a2, b2, a1)<0 ||
	    	mult(a1, b2, b1)*mult(b2, a2, b1)<0 ) {  
	        return false;  
	    }  
	    return true; 
	}
	double mult(Point a, Point b, Point c) {  
	    return (a.x-c.x)*(b.y-c.y)-(b.x-c.x)*(a.y-c.y);  
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the temp is less than sample continue animation
		if(tem<model.getSample()) {
			tem=tem+1;
			repaint();
		}
		else{ //stop animation
			  time.stop();
			  model.setClick(false);
			  tem=0;
		}
	}  		
}
