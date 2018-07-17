import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class ImagePanel extends JPanel implements ActionListener{
	private  int RGB =255;
	private Model model;
	Timer time = new Timer(1,this);
	int tem;
	public ImagePanel(int width, int height, Model model) {
		this.setSize(width,height);
		this.model = model;
		tem = model.getAngle();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		time.start();
		model.updateTem(tem);
		for(Triangle tri: model.getTti()) {
			// get the loaction of x and y in screen
			int xpoints[] = tri.getPointsX(model.getCamN(),tem);
		    int ypoints[] = tri.getPointsY();
			int npoints =3;
			
			Color greyscale = Color.black ;
			if(model.getLight()){
				int Id = Lambertian( tri,model.getLS() );
				if(Id>0 && Id<255)
					greyscale = new Color(Id,Id,Id);
				else if(Id>=255){
					greyscale = new Color(255,255,255);
				}	
			}else {
				//if there is not light , add the texture to the triangle
				greyscale = new Color((int)tri.getTexture(),(int)tri.getTexture(),(int)tri.getTexture());
			}
			g.setColor(greyscale);
			g.fillPolygon(xpoints, ypoints, npoints);	
		}
	}
	//Diffuse reflection
	private int Lambertian(Triangle tri,Point light){
		//calculate the light vector
		double vLightX = light.getX() - tri.getCentroidX();
		double vLightY = light.getY() - tri.getCentroidY();
		double vLightZ = light.getZ() - tri.getCentroidZ();
		double length = Math.sqrt(Math.pow(vLightX, 2)+Math.pow(vLightY, 2)+Math.pow(vLightZ, 2));
		// I* C* dotProduct(V-light, v-normal)
		double Id =  ((tri.getNormal().getX()*vLightX/length
					+tri.getNormal().getY()*vLightY/length
					+tri.getNormal().getZ()*vLightZ/length)*model.getI()*model.getC());
		return (int)(Id*RGB);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// if the tem is less than sample continue animation
		if(tem<model.getAngle()) {
			tem=tem+1;
			repaint();
		}else if(tem>model.getAngle()) {
			tem=tem-1;
			repaint();
		}
		else if(tem==model.getAngle()){ //stop animation
			  time.stop();
			  tem = model.getAngle();
		}
	}  	
}
