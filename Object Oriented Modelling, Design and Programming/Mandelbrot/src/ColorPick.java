import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * the color pick is a tab to chose the color user wanted 
 * it only have 4 options
 * red, blue, green. white
 * @author 150011754
 *
 */
@SuppressWarnings("serial")
public class ColorPick extends JPanel {
	/**
	 * initialise the color picker which contain action listener
	 * @param editor -the tabPa
	 * @param model
	 */
	public ColorPick(Model model) {
		//set the layout of the whole panel
		this.setLayout(new GridLayout(2, 1));
		//grate an upper pannel to save the label and button
		JPanel upper = new JPanel(new GridLayout(8, 10));
		this.add(upper);
		upper.add(new JLabel("Color Piker"));
		JPanel toolBar = new JPanel(new GridLayout(2, 1));
		upper.add(toolBar);
		JButton blue = new JButton("Blue");
		JButton red = new JButton("Red");
		JButton green = new JButton("Green");
		JButton white = new JButton("white");
		blue.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {
			    //when the blue bottom selected, send a blue to model to change the color setting.
				model.updateColor("blue");
			}
		});
		red.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//when the blue bottom selected, send a red to model to change the color setting.
				model.updateColor("red");
			}
		});
		green.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//when the blue bottom selected, send a green to model to change the color setting.
				model.updateColor("green");
			}
		});
		white.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//when the blue bottom selected, send a white to model to change the color setting.
				model.updateColor("white");
			}
		});
		//add this to the panel named toolBar
		toolBar.add(white);
		toolBar.add(blue);
		toolBar.add(red);
		toolBar.add(green);
		
		
		
	}

}
