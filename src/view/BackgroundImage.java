package view;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Class is passed an image so that image is the background. Used for panel classes that can't extend 
 * JPanel because we already extending something.
 * 
 * @author Harmeet Singh.
 *
 */
public class BackgroundImage extends JPanel {
	/**
	 * Needed for Serializable
	 */
	private static final long serialVersionUID = 1358382210458286016L;
	
	private Image image;

	BackgroundImage(Image image) {
	    this.image = image;
	}
		
	/**
	 * passes an image so that image is the background.
	 */
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
	    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}
}
