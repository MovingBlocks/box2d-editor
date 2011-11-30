import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
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
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {}

				LwjglCanvas glCanvas = new LwjglCanvas(AppManager.instance().getRenderPanel(), false);
				AppManager.instance().setRenderCanvas(glCanvas.getCanvas());
				
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
		final String ARG_PRJ_FILE = "prjfile";
		final String ARG_ASSETS_FILES = "assets";

		String prjFilePath = getArg(args, ARG_PRJ_FILE);
		if (!prjFilePath.equals("")) {
			File file = new File(prjFilePath);
			if (!file.isFile()) {
				try {
					file.createNewFile();
					file = file.getCanonicalFile();
					IoManager.instance().setOutputFile(file);
				} catch (IOException ex) {
					System.err.println("Cannot use file: " + prjFilePath);
				}
			}
		}

		String assetsList = getArg(args, ARG_ASSETS_FILES);
		String[] assetsPaths = assetsList.split(";");
		for (String path : assetsPaths) {
			File file = new File(path);
			if (file.exists()) {
				try {
					String fullpath = file.getCanonicalPath();
					ObjectsManager.instance().getBodiesList().add(new RigidBodyModel(fullpath));
				} catch (IOException ex) {
					System.err.println("Cannot use file: " + path);
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
