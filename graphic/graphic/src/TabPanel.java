package assignment1;

import java.awt.Dimension;
import java.util.Stack;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
/**
 * a tab panel to contain tow tabs Editor
 *
 */
@SuppressWarnings("serial")
public class TabPanel extends JPanel{
	private ImagePanel mandel;
	private JTabbedPane tabs; // tabs for GUI organisations
	/**
	 * the initialize of the tabPanel which contain image and model
	 */
	public TabPanel(ImagePanel mandel, Model model){
		this.mandel = mandel;
		this.setSize(300, 600);
		/*create the tab 
		 * Editor and ColorPicker
		 * */
		tabs = new JTabbedPane();
		tabs.setPreferredSize(new Dimension(300,600));
		tabs.addTab("Editor", new Editor(this, model));

		this.add(tabs);
	}
	/**
	 * get the panel 
	 * @return - the ImagePanel
	 */
	public ImagePanel getMandel() {
		return mandel;
	}

	
}
