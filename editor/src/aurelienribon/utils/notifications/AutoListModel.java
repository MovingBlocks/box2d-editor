package aurelienribon.utils.notifications;

import javax.swing.ListModel;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class AutoListModel<T> implements ListModel<T> {
	private final EventListenerList listeners = new EventListenerList();
	private final ObservableList<T> model;

	public AutoListModel(ObservableList<T> model) {
		this.model = model;
		model.addListChangedListener(modelListener);

		for (Object elem : model)
			if (elem instanceof Changeable)
				((Changeable)elem).addChangeListener(elemChangeListener);
	}

	@Override
	public int getSize() {
		return model.size();
	}

	@Override
	public T getElementAt(int index) {
		return model.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(ListDataListener.class, l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(ListDataListener.class, l);
	}

	public void forceRefresh() {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, model.size()-1);
		for (ListDataListener listener : listeners.getListeners(ListDataListener.class))
			listener.contentsChanged(evt);
	}

	// -------------------------------------------------------------------------
	// Helpers
	// -------------------------------------------------------------------------

	private void fireIntervalAdded(int idx1, int idx2) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, idx1, idx2);
		for (ListDataListener listener : listeners.getListeners(ListDataListener.class))
			listener.intervalAdded(evt);
	}

	private void fireIntervalRemoved(int idx1, int idx2) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, idx1, idx2);
		for (ListDataListener listener : listeners.getListeners(ListDataListener.class))
			listener.intervalRemoved(evt);
	}

	private void fireContentsChanged(int idx1, int idx2) {
		ListDataEvent evt = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, idx1, idx2);
		for (ListDataListener listener : listeners.getListeners(ListDataListener.class))
			listener.contentsChanged(evt);
	}

	// -------------------------------------------------------------------------
	// Listeners
	// -------------------------------------------------------------------------

	private final ObservableList.ListChangeListener<T> modelListener = new ObservableList.ListChangeListener<T>() {
		@Override
		public void elementAdded(Object source, int idx, T elem) {
			fireIntervalAdded(idx, idx);
			if (elem instanceof Changeable)
				((Changeable)elem).addChangeListener(elemChangeListener);
		}

		@Override
		public void elementRemoved(Object source, int idx, T elem) {
			fireIntervalRemoved(idx, idx);
			if (elem instanceof Changeable)
				((Changeable)elem).removeChangeListener(elemChangeListener);
		}
	};

	private final ChangeListener elemChangeListener = new ChangeListener() {
		@Override public void propertyChanged(Object source, String propertyName) {
			int idx = model.indexOf(source);
			fireContentsChanged(idx, idx);
		}
	};
}
