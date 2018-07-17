import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
/**
 * the delegate to observe the model and update when notify
 * @author 150011754
 *
 */
public class Delegate implements Observer {
	private ImagePanel mandelbrot;
	private TabPanel tabs;
	private JFrame frame;
	private JPanel right;
	/**
	 * create the delegate by set up a frame and tow JPanel
	 * @param model
	 */
	public Delegate(Model model){
		this.mandelbrot =  new ImagePanel(900,600, model);
		this.tabs = new TabPanel(mandelbrot, model);
		//set up the frame for the GUI
		this.frame = new JFrame();
		this.right = new JPanel();
		setupComponents();
		model.addObserver(this);
	}
	/**
	 * set up the components by add the two JPanel into the frame and set the frame size
	 */
	private void setupComponents(){
		frame.setSize(1200, 600);
		frame.setLayout(new BorderLayout(5,5)); //leave space from each JPanel
		TitledBorder mandelBorder = BorderFactory.createTitledBorder("Mandelbrot");
		mandelbrot.setBorder(mandelBorder);
		frame.add(mandelbrot, BorderLayout.CENTER);
		right.setLayout(new FlowLayout());
		frame.add(right, BorderLayout.LINE_END);
		right.add(tabs);
		mandelBorder = BorderFactory.createTitledBorder("Editor");
		/*
		 * set the frame's initial value 
		 */
		frame.setResizable(true);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	/**
	 * override the update to be print the image.
	 */	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				mandelbrot.repaint();
			}
		});
	}

}
