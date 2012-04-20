package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.canvas.Canvas;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Main {
    public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (ClassNotFoundException ex) {
				} catch (InstantiationException ex) {
				} catch (IllegalAccessException ex) {
				} catch (UnsupportedLookAndFeelException ex) {
				}
				
				LwjglCanvas glCanvas = new LwjglCanvas(new Canvas(), false);
				MainWindow mw = Ctx.window;

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mw.setSize(
					Math.min(1150, screenSize.width - 100),
					Math.min(800, screenSize.height - 100)
				);

				mw.setCanvas(glCanvas.getCanvas());
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
    }
}
