import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * draw the picture of Mandelbrot set
 * @author 150011754
 *
 */
public class ImagePanel extends JPanel{
	private Model model;
	public ImagePanel(int width, int height, Model model) {
		this.setSize(width,height);
		this.model = model;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if(!model.getDragged() ){
			model.setImage();
		}
		//draw the image based on Mandelbrot's setting
		g.drawImage(model.getImg(),0,0,null);
		/*
		 * when the mouse is dragging , draw an appropriate rectangle to show where it will display after zooming 
		 */
		if(model.getDragged()){
			if(model.getMandelbert().getColor().equals("blue")){
				g.setColor(Color.white);
			}else{
			g.setColor(Color.blue);}
			g.drawRect(Math.min(model.getStart().x,model.getEnd().x), Math.min(model.getStart().y, model.getEnd().y), 
					Math.abs(model.getEnd().x - model.getStart().x), Math.abs(model.getEnd().y - model.getStart().y));
		}
	}
	
	
}
