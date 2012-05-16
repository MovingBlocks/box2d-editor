package aurelienribon.utils.ui;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.awt.Window;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class SwingHelper {
	/**
	 * Adds a listener to the window parent of the given component. Can be
	 * before the component is really added to its hierachy.
	 */
	public static void addWindowListener(final Component source, final WindowListener listener) {
		if (source instanceof Window) {
			((Window)source).addWindowListener(listener);
		} else {
			source.addHierarchyListener(new HierarchyListener() {
				@Override public void hierarchyChanged(HierarchyEvent e) {
					if ((e.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) == HierarchyEvent.SHOWING_CHANGED) {
						SwingUtilities.getWindowAncestor(source).addWindowListener(listener);
					}
				}
			});
		}
	}

	/**
	 * Opens the given website in the default browser, or show a message saying
	 * that no default browser could be accessed.
	 */
	public static void browse(Component parent, String uri) {
		boolean error = false;

		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI(uri));
			} catch (URISyntaxException ex) {
				throw new RuntimeException(ex);
			} catch (IOException ex) {
				error = true;
			}
		} else {
			error = true;
		}

		if (error) {
			String msg = "Impossible to open the default browser from the application.\nSorry.";
			JOptionPane.showMessageDialog(parent, msg);
		}
	}

	/**
	 * Gets the parent JFrame of the component.
	 */
	public static JFrame getFrame(Component cmp) {
		return (JFrame) SwingUtilities.getWindowAncestor(cmp);
	}
}
