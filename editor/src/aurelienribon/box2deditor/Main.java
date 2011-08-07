package aurelienribon.box2deditor;

import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Aurelien Ribon (aurelien.ribon@gmail.com)
 */
public class Main {
    public static void main(final String[] args) {
		PrintStream ps = new PrintStream(AppContext.outputStream);
		System.setOut(ps);
		System.setErr(ps);

		parseArgs(args);
		makeWindow();

		System.out.println("box2d-editor | 2011");
		System.out.println("Welcome!\n");
    }

	private static void parseArgs(String[] args) {
		for (int i=0; i<args.length; i++) {
			if (args[i].startsWith("--outputfile=")) {
				try {
					File file = new File(args[i].substring("--outputfile=".length()));
					AppContext.instance().outputFile = file.getCanonicalFile();
				} catch (IOException ex) {
					System.err.println("Given output file path cannot be retrieved...");
					AppContext.instance().outputFile = null;
				}
			}
		}
	}

	private static void makeWindow() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {}

				LwjglCanvas glCanvas = new LwjglCanvas(App.instance(), false);

				MainWindow mw = new MainWindow(glCanvas.getCanvas());
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mw.setSize(
					Math.min(1250, screenSize.width - 100),
					Math.min(850, screenSize.height - 100)
				);
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);
			}
		});
	}
}
