package aurelienribon.bodyeditor;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesEvents {
	private final EventListenerList listeners = new EventListenerList();

	// -------------------------------------------------------------------------

	public static interface AppToScreenListener extends EventListener {
		public void recreateWorld();
		public void modelImageChanged();
	}

	public static interface ScreenToAppListener extends EventListener {
		public void selectModelImage();
	}

	// -------------------------------------------------------------------------

	public void addAppToScreenListener(AppToScreenListener listener) {
		listeners.add(AppToScreenListener.class, listener);
	}

	public void addScreenToAppListener(ScreenToAppListener listener) {
		listeners.add(ScreenToAppListener.class, listener);
	}

	// -------------------------------------------------------------------------

	public void recreateWorld() {
		for (AppToScreenListener listener : listeners.getListeners(AppToScreenListener.class))
			listener.recreateWorld();
	}

	public void selectModelImage() {
		for (ScreenToAppListener listener : listeners.getListeners(ScreenToAppListener.class))
			listener.selectModelImage();
	}

	public void modelImageChanged() {
		for (AppToScreenListener listener : listeners.getListeners(AppToScreenListener.class))
			listener.modelImageChanged();
	}
}
