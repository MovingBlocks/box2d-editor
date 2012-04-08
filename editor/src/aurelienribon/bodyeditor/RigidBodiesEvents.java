package aurelienribon.bodyeditor;

import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesEvents {
	public static interface Listener extends EventListener {
		public void recreateWorldRequested();
	}

	private final EventListenerList listeners = new EventListenerList();

	public void addListener(Listener listener) {
		listeners.add(Listener.class, listener);
	}

	public void fireRecreateWorldRequested() {
		for (Listener listener : listeners.getListeners(Listener.class))
			listener.recreateWorldRequested();
	}
}
