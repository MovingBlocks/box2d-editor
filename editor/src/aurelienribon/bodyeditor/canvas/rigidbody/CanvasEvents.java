package aurelienribon.bodyeditor.canvas.rigidbody;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class CanvasEvents {
	public static interface Listener extends EventListener {
		public void recreateWorldRequested();
	}

	private static final EventListenerList listeners = new EventListenerList();

	public static void addListener(Listener listener) {
		listeners.add(Listener.class, listener);
	}

	public static void fireRecreateWorldRequested() {
		for (Listener listener : listeners.getListeners(Listener.class))
			listener.recreateWorldRequested();
	}
}
