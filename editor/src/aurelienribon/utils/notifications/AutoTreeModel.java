package aurelienribon.utils.notifications;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com
 */
public abstract class AutoTreeModel implements TreeModel {
	private final Map<Object, TreePath> pathsMap = new HashMap<Object, TreePath>(100);
	private final EventListenerList listeners = new EventListenerList();

	public Map<Object, TreePath> getPathsMap() {
		return Collections.unmodifiableMap(pathsMap);
	}

	@Override
	public void addTreeModelListener(TreeModelListener l) {
		listeners.add(TreeModelListener.class, l);
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		listeners.remove(TreeModelListener.class, l);
	}

	// -------------------------------------------------------------------------
	// Registrations
	// -------------------------------------------------------------------------

	public void registerRoot(Object root) {
		assert root != null;
		assert !pathsMap.containsKey(root);
		pathsMap.put(root, new TreePath(new Object[] {root}));

		if (root instanceof Changeable)
			((Changeable)root).addChangeListener(changeListener);

		if (root instanceof ObservableListsHolder)
			for (int i=0, n=((ObservableListsHolder)root).getObservableListCount(); i<n; i++)
				registerList(root, ((ObservableListsHolder)root).getObservableList(i));
	}

	private void registerElement(Object parent, Object child) {
		assert parent != null;
		assert child != null;
		assert pathsMap.containsKey(parent);
		assert !pathsMap.containsKey(child);

		Object[] parentPath = pathsMap.get(parent).getPath();
		Object[] childPath = new Object[parentPath.length+1];
		System.arraycopy(parentPath, 0, childPath, 0, parentPath.length);
		childPath[childPath.length-1] = child;
		pathsMap.put(child, new TreePath(childPath));

		if (child instanceof Changeable)
			((Changeable)child).addChangeListener(changeListener);

		if (child instanceof ObservableListsHolder)
			for (int i=0, n=((ObservableListsHolder)child).getObservableListCount(); i<n; i++)
				registerList(child, ((ObservableListsHolder)child).getObservableList(i));
	}

	private void unregisterElement(Object elem) {
		assert elem != null;
		assert pathsMap.containsKey(elem);
		pathsMap.remove(elem);

		if (elem instanceof Changeable)
			((Changeable)elem).removeChangeListener(changeListener);

		if (elem instanceof ObservableListsHolder)
			for (int i=0, n=((ObservableListsHolder)elem).getObservableListCount(); i<n; i++)
				unregisterList(((ObservableListsHolder)elem).getObservableList(i));
	}

	private void registerList(Object parent, ObservableList list) {
		assert list != null;
		list.addListChangedListener(listChangeListener);
		for (Object elem : list)
			registerElement(parent, elem);
	}

	private void unregisterList(ObservableList list) {
		assert list != null;
		list.removeListChangedListener(listChangeListener);
		for (Object elem : list)
			unregisterElement(elem);
	}

	// -------------------------------------------------------------------------
	// Events
	// -------------------------------------------------------------------------

	protected void fireNodeAdded(Object parent, int childIdx, Object child) {
		assert pathsMap.containsKey(parent);
		TreeModelEvent evt = new TreeModelEvent(this, pathsMap.get(parent), new int[]{childIdx}, new Object[]{child});
		for (TreeModelListener listener : listeners.getListeners(TreeModelListener.class))
			listener.treeNodesInserted(evt);
	}

	protected void fireNodeRemoved(Object parent, int childIdx, Object child) {
		assert pathsMap.containsKey(parent);
		TreeModelEvent evt = new TreeModelEvent(this, pathsMap.get(parent), new int[]{childIdx}, new Object[]{child});
		for (TreeModelListener listener : listeners.getListeners(TreeModelListener.class))
			listener.treeNodesRemoved(evt);
	}

	protected void fireNodeChanged(Object parent) {
		assert pathsMap.containsKey(parent);
		TreeModelEvent evt = new TreeModelEvent(this, pathsMap.get(parent));
		for (TreeModelListener listener : listeners.getListeners(TreeModelListener.class))
			listener.treeNodesChanged(evt);
	}

	// -------------------------------------------------------------------------
	// Listeners
	// -------------------------------------------------------------------------

	private final ChangeListener changeListener = new ChangeListener() {
		@Override
		public void propertyChanged(Object source, String propertyName) {
			fireNodeChanged(source);
		}
	};

	private final ObservableList.ListChangeListener listChangeListener = new ObservableList.ListChangeListener() {
		@Override
		public void elementAdded(Object source, int idx, Object elem) {
			registerElement(source, elem);
			fireNodeAdded(source, idx, elem);
		}

		@Override
		public void elementRemoved(Object source, int idx, Object elem) {
			unregisterElement(elem);
			fireNodeRemoved(source, idx, elem);
		}
	};
}
