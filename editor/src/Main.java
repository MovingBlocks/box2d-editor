import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ui.MainWindow;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class Main {
    public static void main(final String[] args) {
		parseArgs(args);
		makeWindow();
    }

	private static void parseArgs(String[] args) {
		for (int i=0; i<args.length; i++) {
			if (args[i].startsWith("--outputfile=")) {
				try {
					File file = new File(args[i].substring("--outputfile=".length()));
					AppManager.instance().outputFile = file.getCanonicalFile();
				} catch (IOException ex) {
					System.err.println("Given output file path cannot be retrieved...");
					AppManager.instance().outputFile = null;
				}
			}
		}
	}

	private static void makeWindow() {
		final LwjglCanvas glCanvas = new LwjglCanvas(RenderPanel.instance(), false);

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {}

				MainWindow mw = new MainWindow(glCanvas.getCanvas());
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mw.setSize(
					Math.min(1150, screenSize.width - 100),
					Math.min(800, screenSize.height - 100)
				);
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
	}
}
