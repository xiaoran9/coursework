import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class Delegate extends JPanel implements Observer {
	public static final String TITLE ="3D Human Face";
	private ImagePanel drawing;
	private JFrame frame;
	private JPanel upper;

	public static final int WIDTH = 1000;
	public static final int HEIGHT = 800 ;
	public static Model model;
	
	public Delegate(int width, int height,Model model){
//		this.model =model;
		this.drawing =  new ImagePanel(width,height, model);
		this.model = model;
		upper = new JPanel();
		frame  = new JFrame();
		frame.setSize(width,height);
		setupComponents();
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		model.addObserver(this);	
	}
	private void setupComponents(){
		frame.setTitle(TITLE);
		frame.setLayout(new BorderLayout(5,5));
		
		TitledBorder DrawingBorder = BorderFactory.createTitledBorder("Drawing Area");
		drawing.setBorder(DrawingBorder);
		JTextField lightX = new JTextField(5);
		upper.add(new JLabel("Light x: "));
		lightX.setText(String.valueOf(model.getLS().getX()));
		upper.add(lightX); 
		
		JTextField lightY = new JTextField(5);
		upper.add(new JLabel("Light Y:"));
		lightY.setText(String.valueOf(model.getLS().getY()));
		upper.add(lightY); 
		
		JTextField lightZ = new JTextField(5);
		upper.add(new JLabel("Light Z: "));
		lightZ.setText(String.valueOf(model.getLS().getZ()));
		upper.add(lightZ); 
		
		JTextField intensity = new JTextField(5);
		upper.add(new JLabel("Light Intensity: "));
		intensity.setText(String.valueOf(model.getI()));
		upper.add(intensity); 
		JTextField angle = new JTextField(5);
		upper.add(new JLabel("Angle: "));
		angle.setText(String.valueOf(model.getAngle()));
		upper.add(angle); 
		
		JButton turnOn = new JButton("Enter");
		
		turnOn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try { //maker sure input is not empty and in the range of 3 to 100
					if(!angle.getText().equals("")&&Integer.parseInt(angle.getText())>=-90 &&Integer.parseInt(angle.getText())<=90) {
						model.setAngle(Integer.parseInt(angle.getText()));
					}else {
						JOptionPane.showMessageDialog(null,"the angle should be between 90, -90");
					}
					if(!lightX.getText().equals("")&&
						!lightY.getText().equals("")&&
						!lightZ.getText().equals("")&& 
						(Double.parseDouble(lightZ.getText())>=150000 ||Double.parseDouble(lightZ.getText())<=-150000) ) {
						model.setLS(Double.parseDouble(lightX.getText()),Double.parseDouble(lightY.getText()),Double.parseDouble(lightZ.getText()));
					}else {
						JOptionPane.showMessageDialog(null,"the Z should bigger than 150000 or smaller than -150000");
					}
					if(!intensity.getText().equals("")) {
						model.setI(Double.parseDouble(intensity.getText()));
					}else {
						JOptionPane.showMessageDialog(null,"the light intensity should bigger than 0");
					}
					model.setTurnOn();
					model.update();
				}catch(Exception ioe) {
					JOptionPane.showMessageDialog(null,"input the correct range of input");
				}
			};
		 });
		upper.add(turnOn);
		frame.add(drawing, BorderLayout.CENTER);
		frame.add(upper, BorderLayout.NORTH);
	}

	/**
	 * override the update to be print the image.
	 */	
	@Override
	public void update(Observable o, Object arg) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				drawing.repaint();
			}
		});
	}
	
	public static void main(String[] args) {
		model =new Model(WIDTH, HEIGHT);
		new Delegate(WIDTH, HEIGHT,model);
		
	}
}
