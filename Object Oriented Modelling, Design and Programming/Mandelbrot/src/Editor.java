
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
/**
 * the editor extends the JPanel to listen the buttons inside.
 * it have 6 option: enter undo redo reset saveImage saveFile load
 * @author 150011754
 *
 */
public class Editor extends JPanel {
	private TabPanel editor;
	private ImagePanel mandel;
	private Model model;
	/**
	 * editor to build the layout and listen the buttons and mouse.
	 * @param editor 
	 * @param model
	 */
	public Editor(TabPanel editor,Model model){
		this.editor = editor;
		mandel = editor.getMandel();
		this.model = model;
		/*
		 * setup components of this panel
		 * it have two panel named upper and lower 
		 * lower have 6 button: Reset Redo Undo SaveImage SaveSer Load
		 */
		this.setLayout(new GridLayout(2, 1));
		JPanel upper = new JPanel(new GridLayout(8, 10));
		JPanel lower = new JPanel(new GridLayout(4, 1));
		this.add(upper);
		this.add(lower);
		/*
		 * upper have 5 input JTextField and JLabel with one button Enter 
		 */
		JTextField maxR = new JTextField(5);
		upper.add(new JLabel("Max Real: "));
		maxR.setText(String.valueOf(model.getMandelbert().getMaxR()));
		upper.add(maxR); 
		JTextField minR = new JTextField(5);
		minR.setText(String.valueOf(model.getMandelbert().getMinR()));
		upper.add(new JLabel("Min Real:"));
		upper.add(minR);
		JTextField maxI = new JTextField(5);
		maxI.setText(String.valueOf(model.getMandelbert().getMaxI()));
		upper.add(new JLabel("Max Imaginary: "));
		upper.add(maxI);
		JTextField minI = new JTextField(5);
		minI.setText(String.valueOf(model.getMandelbert().getMinI()));
		upper.add(new JLabel("Max Imaginary: "));
		upper.add(minI);
		JTextField iterField = new JTextField(5);
		iterField.setText(String.valueOf(model.getMandelbert().getIterations()));
		upper.add(new JLabel("Iterations"));
		upper.add(iterField);
		/*
		 * lower have 6 button: Reset Redo Undo SaveImage SaveSer Load
		 */
		JButton enter = new JButton("Enter");
		JButton reset = new JButton("Reset");
		JButton undo = new JButton("Undo");
		JButton redo = new JButton("Redo");
		JButton saveFile = new JButton("Save ser");
		JButton saveImg = new JButton("Save Img");
		JButton load = new JButton("Load");
		
		// Listener added to Mandelbrot for zooming
		mandel.addMouseListener(new MouseAdapter(){
		    @Override
			public void mousePressed(MouseEvent e) {
				//sets start point
		    	model.setStart(e.getPoint());
			}
			public void mouseReleased(MouseEvent e) {
				if (model.getDragged()) {
					/*
					 *  before doing anything, the current values are saved 
					 *  so that the user can undo by the trace
					 */
					saveTrace();
					double newMaxR, newMinR, newMinI, newMaxI;
					/*
					 * in order to avoiding the Skewed, the values saved locally
					 * so they would not effect the others calculation. 
					 */
					model.setEnd(e.getPoint());
					newMaxR = (model.getMandelbert().getMaxR() - model.getMandelbert().getMinR()) * (e.getX() / (double) mandel.getWidth()) + model.getMandelbert().getMinR();
					newMinR = (model.getMandelbert().getMaxR() - model.getMandelbert().getMinR()) * (model.getStart().x / (double) mandel.getWidth()) + model.getMandelbert().getMinR();
					newMaxI = (model.getMandelbert().getMaxI() - model.getMandelbert().getMinI()) * (e.getY() / (double) mandel.getHeight()) + model.getMandelbert().getMinI();
					newMinI = (model.getMandelbert().getMaxI() - model.getMandelbert().getMinI()) * (model.getStart().y / (double) mandel.getHeight()) + model.getMandelbert().getMinI();
					// stop drawing the rectangle.
					model.setDragged(false);
					model.updateImg(Math.min(newMaxR, newMinR), Math.max(newMaxR, newMinR), Math.min(newMaxI, newMinI), Math.max(newMaxI, newMinI), model.getMandelbert().getIterations(), model.getMandelbert().getColor());
				}
			}
		});
		
		
		// Listener added dragging area and draws rectangle
		mandel.addMouseMotionListener(new MouseAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					// Sets the end point
					model.setEnd(e.getPoint());
					/*
					 * The boolean operator for dragging have two option
					 * 1.there are no conflicts
					 * 2 when no drawing, the rectangle donsen't stuck
					 */
					model.setDragged(true);
					mandel.repaint();
				}
			}
		});
		//when the enter button pressed, update information in testFile 
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.updateImg(Double.parseDouble(minR.getText()), Double.parseDouble(maxR.getText()), Double.parseDouble(minI.getText()), Double.parseDouble(maxI.getText()), Integer.parseInt(iterField.getText()), model.getMandelbert().getColor());
			};
		});
		//when the reset button pressed, back to the open image
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Double maxR = MandelbrotCalculator.INITIAL_MAX_REAL;
				Double maxI = MandelbrotCalculator.INITIAL_MAX_IMAGINARY;
				Double minR = MandelbrotCalculator.INITIAL_MIN_REAL;
				Double minI = MandelbrotCalculator.INITIAL_MIN_IMAGINARY;
				int interations = MandelbrotCalculator.INITIAL_MAX_ITERATIONS;	
				String color = "white";
				model.updateImg(minR, maxR, minI, maxI, interations,color);
			};
		});
		//when the undo button pressed, back to the last image
		undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!editor.getTrace().isEmpty()) {
					saveUndo();
					double minR, maxR, minI, maxI;
					int iteration = editor.getTrace().pop().intValue();
					minI =editor.getTrace().pop();
					maxI = editor.getTrace().pop();
					minR = editor.getTrace().pop();
					maxR = editor.getTrace().pop();
					model.updateImg(minR, maxR, minI, maxI, iteration, model.getMandelbert().getColor());
				}
			};
		});
		//when the redo button pressed, back to the last undo image
		redo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (!editor.getUndo().isEmpty()) {
					double minR, maxR, minI, maxI;
					int iteration = editor.getUndo().pop().intValue();
					minI = editor.getUndo().pop();
					maxI = editor.getUndo().pop();
					minR = editor.getUndo().pop();
					maxR = editor.getUndo().pop();
					model.updateImg(minR, maxR, minI, maxI, iteration, model.getMandelbert().getColor());
				}
			};
		});
		//when the saveFile button pressed, save an serialisation file of Mandelbrot setting
		saveFile.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File("."));
				 int returnVal = chooser.showSaveDialog(null);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
				      try {
						model.saveIFile( chooser.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				  }
				  if (returnVal == JFileChooser.CANCEL_OPTION) {
				    System.out.print("You pressed cancel");
				   }
			}
		});
		//when the saveImg button pressed, save a image file which cannot load 
	   saveImg.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File("."));
				 int returnVal = chooser.showSaveDialog(null);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
				      try {
						model.saveImage( chooser.getSelectedFile().getAbsolutePath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				      }
				      if (returnVal == JFileChooser.CANCEL_OPTION) {
				    	  System.out.print("You pressed cancel");
				       
				     }
			}
		});
		//when the load button pressed, open the chooser to load a serialisation file
        load.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new File("."));
				 int returnVal = chooser.showOpenDialog(null);
				 if(returnVal == JFileChooser.APPROVE_OPTION){    // if the user not cancel the operation
						File selectedFile = chooser.getSelectedFile().getAbsoluteFile();  //get the absolute address of the file
					 try {
						model.loadFile(selectedFile);
					} catch (IOException | ClassNotFoundException e1) {
						e1.printStackTrace();
						}
			        }  
				 if (returnVal == JFileChooser.CANCEL_OPTION) {
					 System.out.print("You pressed cancel");
				 }
			}
		});
		upper.add(enter);
		lower.add(reset);
		lower.add(redo);
		lower.add(undo);
		lower.add(saveImg);
		lower.add(saveFile);
		lower.add(load);
		
	}
	/**
	 * save the values into the trace stack
	 */
	public void saveTrace(){
		// stored  the value into the stack
		editor.getTrace().push(model.getMandelbert().getMaxR());
		editor.getTrace().push(model.getMandelbert().getMinR());
		editor.getTrace().push(model.getMandelbert().getMaxI());
		editor.getTrace().push(model.getMandelbert().getMinI());
		editor.getTrace().push((double) model.getMandelbert().getIterations());
		
	}
	/**
	 * save the value into the undo stack
	 */
	public void saveUndo(){
		editor.setUndo(model.getMandelbert().getMaxR());
		editor.setUndo(model.getMandelbert().getMinR());
		editor.setUndo(model.getMandelbert().getMaxI());
		editor.setUndo(model.getMandelbert().getMinI());
		editor.setUndo((double) model.getMandelbert().getIterations());
	}

	
	
}
