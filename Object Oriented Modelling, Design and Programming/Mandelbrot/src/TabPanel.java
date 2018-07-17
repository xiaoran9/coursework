import java.awt.Dimension;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * a tab panel to contain tow tabs Editor and colorPicker
 * @author 150011754
 *
 */
@SuppressWarnings("serial")
public class TabPanel extends JPanel{
	private ImagePanel mandel;
	private JTabbedPane tabs; // tabs for GUI organisations
	private Stack<Double> trace, undos; // stack for storing the previous zooms
	/**
	 * the initialize of the tabPanel which contain image and model
	 * @param mandel -the Mandelbrot image panel
	 * @param model -the model to save information of Mandelbrot setting values
	 */
	public TabPanel(ImagePanel mandel, Model model){
		this.mandel = mandel;
		this.setSize(300, 600);
		trace = new Stack<Double>(); //stack to save each setting of user change
		undos = new Stack<Double>(); //only save the setting before undo
		/*create the tab 
		 * Editor and ColorPicker
		 * */
		tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(300,600));
		tabs.addTab("Editor", new Editor(this, model));
		tabs.addTab("ColorPick", new ColorPick(model));
		this.add(tabs);
	}
	/**
	 * get the stack of trace.
	 * it can not only get the element in the stack but also push into
	 * @return -the trace stack
	 */
	public Stack<Double> getTrace() {
		return trace;
	}
	/**
	 * the undo stack
	 * it can not only get the element in the stack but also push into
	 * @return -the undo stack
	 */
	public Stack<Double> getUndo() {
		return undos;
	}
	/**
	 * push a new double into the undo stack
	 * @param undo -a Double want to saved into the undo stack
	 */
	public void setUndo(Double undo) {
		this.undos.push(undo);
	}
	/**
	 * get the panel 
	 * @return - the ImagePanel
	 */
	public ImagePanel getMandel() {
		return mandel;
	}

	
}
