package aurelienribon.utils.notifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public class ObservableList<T> extends ArrayList<T> {
	private final Object source;

	public ObservableList() {
		this.source = null;
	}

	public ObservableList(Object source) {
		this.source = source;
	}

	// -------------------------------------------------------------------------
	// API
	// -------------------------------------------------------------------------

	@Override
	public boolean add(T e) {
		boolean ret = super.add(e);
		fireElementAdded(indexOf(e), e);
		return ret;
	}

	@Override
	public void add(int index, T element) {
		super.add(index, element);
		fireElementAdded(index, element);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean ret = super.addAll(c);
		for (T elem : c)
			fireElementAdded(indexOf(elem), elem);
		return ret;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		boolean ret = super.addAll(index, c);
		for (T elem : c)
			fireElementAdded(indexOf(elem), elem);
		return ret;
	}

	@Override
	public boolean remove(Object o) {
		int idx = indexOf(o);
		if (idx < 0)
			return false;
		T e = super.remove(idx);
		fireElementRemoved(idx, e);
		return true;
	}

	@Override
	public T remove(int index) {
		T e = super.remove(index);
		fireElementRemoved(index, e);
		return e;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean ret = false;
		for (Object elem : c)
			ret |= remove(elem);
		return ret;
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex) {
		if (fromIndex > toIndex)
			throw new IllegalArgumentException("fromIndex must be less than or equal to toIndex");
		for (int i=toIndex; i>=fromIndex; i--)
			remove(fromIndex);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		for (int i=size()-1; i>=0; i--)
			remove(i);
	}

	@Override
	public T set(int index, T element) {
		T e = super.set(index, element);
		fireElementRemoved(index, e);
		fireElementAdded(index, element);
		return e;
	}

	// -------------------------------------------------------------------------
	// Events
	// -------------------------------------------------------------------------

	private final EventListenerList listeners = new EventListenerList();

	public interface ListChangeListener<T> extends EventListener {
		public void elementAdded(Object source, int idx, T elem);
		public void elementRemoved(Object source, int idx, T elem);
	}

	public void addListChangedListener(ListChangeListener listener) {
		listeners.add(ListChangeListener.class, listener);
	}

	public void removeListChangedListener(ListChangeListener listener) {
		listeners.remove(ListChangeListener.class, listener);
	}

	private void fireElementAdded(int idx, T elem) {
		for (ListChangeListener listener : listeners.getListeners(ListChangeListener.class))
			listener.elementAdded(source != null ? source : this, idx, elem);
	}

	private void fireElementRemoved(int idx, T elem) {
		for (ListChangeListener listener : listeners.getListeners(ListChangeListener.class))
			listener.elementRemoved(source != null ? source : this, idx, elem);
	}
}
