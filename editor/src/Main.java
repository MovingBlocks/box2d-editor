import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.canvas.rigidbody.Canvas;
import aurelienribon.bodyeditor.ui.MainWindow;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		SwingUtilities.invokeLater(new Runnable() {
			@Override public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				LwjglCanvas glCanvas = new LwjglCanvas(new Canvas(), false);
				
				MainWindow mw = new MainWindow(glCanvas.getCanvas());

				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				mw.setSize(
					Math.min(1150, screenSize.width - 100),
					Math.min(800, screenSize.height - 100)
				);
				
				mw.setLocationRelativeTo(null);
				mw.setVisible(true);

				mw.addWindowListener(new WindowAdapter() {
					@Override public void windowOpened(WindowEvent e) {
						parseArgs(args);
					}
				});
			}
		});
    }

	private static void parseArgs(String[] args) {
		String ARG_PROJECT_PATH = "project";

		String projectPath = getArg(args, ARG_PROJECT_PATH);
		if (!projectPath.equals("")) {
			File file = new File(projectPath);
			if (!file.isFile()) {
				try {
					file.createNewFile();
					file = file.getCanonicalFile();
					IoManager.instance().setProjectFile(file);
				} catch (IOException ex) {
					System.err.println("Cannot use file: " + projectPath);
				}
			}
		}
	}

	private static String getArg(String[] args, String arg) {
		for (int i=0; i<args.length; i++)
			if (args[i].startsWith("--" + arg + "="))
				return args[i].substring(("--" + arg + "=").length());
		return "";
	}
}
