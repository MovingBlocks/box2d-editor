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
		public void autoTrace();
	}

	// -------------------------------------------------------------------------

	public void addAppToScreenListener(AppToScreenListener listener) {
		listeners.add(AppToScreenListener.class, listener);
	}

	// -------------------------------------------------------------------------

	public void recreateWorld() {
		for (AppToScreenListener listener : listeners.getListeners(AppToScreenListener.class))
			listener.recreateWorld();
	}

	public void modelImageChanged() {
		for (AppToScreenListener listener : listeners.getListeners(AppToScreenListener.class))
			listener.modelImageChanged();
	}

	public void autoTrace() {
		for (AppToScreenListener listener : listeners.getListeners(AppToScreenListener.class))
			listener.autoTrace();
	}
}
