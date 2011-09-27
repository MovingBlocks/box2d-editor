package aurelienribon.utils.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class TitlePanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		if (getComponentCount() == 0)
			return;

		Component cmp = getComponent(0);
		int w = cmp.getWidth() + cmp.getX() + 10;
		int h = getHeight();

		Graphics2D gg = (Graphics2D)g;
		gg.setFont(getFont());
		gg.setColor(getBackground());

		int[] xs = {0, w, w + h, 0};
		int[] ys = {0, 0, h, h};

		gg.fillPolygon(xs, ys, 4);
	}
}
