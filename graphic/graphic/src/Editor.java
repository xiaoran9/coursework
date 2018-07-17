package assignment1;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
/**
 * the editor extends the JPanel to listen the buttons inside.
 */
public class Editor extends JPanel {
	private TabPanel editor;
	private ImagePanel drawing;
	private Model model;
	/**
	 * editor to build the layout and listen the buttons and mouse.
	 * @param editor 
	 * @param model
	 */
	public Editor(TabPanel editor,Model model){
		this.editor = editor;
		drawing = editor.getMandel();
		this.model = model;
		/*
		 * setup components of this panel
		 * it have two panel named upper and lower
		 */
		this.setLayout(new GridLayout(2, 1));
		JPanel upper = new JPanel(new GridLayout(8, 10));
		JPanel lower = new JPanel(new GridLayout(4, 1));
		JTextField sample = new JTextField(4);
		
		JLabel label = new JLabel("Light Model :OFF");
		sample.setText(String.valueOf(model.getSample()));
		upper.add(sample);
		this.add(upper);
		this.add(lower);

		/*
		 * lower have 6 button: Reset Redo Undo SaveImage SaveSer Load
		 */
		JButton inputSample = new JButton("Input Samples");
		JButton enter = new JButton("Draw to Curves");
//		JButton newCurve = new JButton("Draw New Curve");
		JButton reset = new JButton("Reset");
		JButton turnOn = new JButton("Turn on Light");
		JButton turnOff = new JButton("Turn off Light");
		
		// the action in drawing area to draw points
		drawing.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
		    	if (SwingUtilities.isLeftMouseButton(e)) {
		    		//if the model is not ligth, the click point is the adding point for drawing curves
		    		if(!model.getLightModel()) 
						model.setClickPoint(e.getPoint());
		    		else
		    			model.setLightSource(e.getPoint());
		    		
		    		model.setClick(true);
		    		model.update();
		    	}
		    }
			 public void mouseReleased(MouseEvent e) {
				 if(model.getClick()) {
					 if(!model.getLightModel())
						 model.setClick(false);
					}
				}	
		});
		// enter pressed means draw curve depending on the input points
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			model.setEnter(true);
			model.drawCurve();
			
			};
		 });
		//turn on the light model to draw light source
		turnOn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setTurnOn();
				label.setText("Light Model :ON");
			};
		 });
		//turn on the light model to draw points for curve
		turnOff.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.setTurnOff();
				label.setText("Light Model :OFF");
			};
		 });
		//reset the drawing ares 
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			model.setReset(true);
			model.clearPoints();
			model.update();
			};
		 });
		/* try to add more than one curves but failed
		newCurve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			model.newCurve();
			model.setEnter(false);
			};
		 });*/
		//input the number of sampling points
		inputSample.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try { //maker sure input is not empty and in the range of 3 to 100
					if(!sample.getText().equals("")&&Integer.parseInt(sample.getText())>=3 &&Integer.parseInt(sample.getText())<=100) {
					
						model.setSample(Integer.parseInt(sample.getText()));
					}else {
						JOptionPane.showMessageDialog(null,"the sample should be bigger than 3 and less than 100");
					}
				}catch(Exception ioe) {
					JOptionPane.showMessageDialog(null,"the sample should be bigger than 3 and less than 100");
				}
			};
		 });
		
		upper.add(inputSample);
		upper.add(enter);
		upper.add(reset);

		lower.add(label);
		lower.add(turnOn);
		lower.add(turnOff);
	}
		
	
}
