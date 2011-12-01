package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.models.DynamicObjectModel;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.notifications.AutoListModel;
import aurelienribon.utils.notifications.ChangeListener;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ObjectsPanel extends javax.swing.JPanel {
	private final ImageIcon assetIcon = new ImageIcon(ObjectsPanel.class.getResource("gfx/ic_texture.png"));
	
    public ObjectsPanel() {
        initComponents();
		bodiesList.setModel(new AutoListModel(ObjectsManager.instance().getRigidBodies()));
		objectsList.setModel(new AutoListModel(ObjectsManager.instance().getDynamicObjects()));
		bodiesList.setCellRenderer(bodiesListCellRenderer);
		objectsList.setCellRenderer(objectsListCellRenderer);

		bodiesList.addListSelectionListener(new ListSelectionListener() {
			@Override public void valueChanged(ListSelectionEvent e) {
				RigidBodyModel model = (RigidBodyModel) bodiesList.getSelectedValue();
				ObjectsManager.instance().setSelectedRigidBody(model);
			}
		});

		objectsList.addListSelectionListener(new ListSelectionListener() {
			@Override public void valueChanged(ListSelectionEvent e) {
				DynamicObjectModel model = (DynamicObjectModel) objectsList.getSelectedValue();
				ObjectsManager.instance().setSelectedDynamicObject(model);
			}
		});

		ObjectsManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(ObjectsManager.PROP_SELECTION)) {
					RigidBodyModel selectedBody = ObjectsManager.instance().getSelectedRigidBody();
					DynamicObjectModel selectedObject = ObjectsManager.instance().getSelectedDynamicObject();

					if (bodiesList.getSelectedValue() != selectedBody) bodiesList.setSelectedValue(selectedBody, true);
					if (objectsList.getSelectedValue() != selectedObject) objectsList.setSelectedValue(selectedObject, true);

					((AutoListModel)bodiesList.getModel()).forceRefresh();
				}
			}
		});

		IoManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(IoManager.PROP_OUTPUTFILE)) {
					((AutoListModel)bodiesList.getModel()).forceRefresh();
				}
			}
		});

		addBodyBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {addRigidBody();}});
		delBodyBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {delRigidBody();}});
		editBodyBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {editRigidBody();}});
		addObjectBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {addDynamicObject();}});
		delObjectBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {delDynamicObject();}});
		editObjectBtn.addMouseListener(new MouseAdapter() {@Override public void mouseClicked(MouseEvent e) {editDynamicObject();}});
    }

	// -------------------------------------------------------------------------
	// Actions
	// -------------------------------------------------------------------------

	private void addRigidBody() {
		Window wnd = SwingUtilities.getWindowAncestor(this);
		String name = JOptionPane.showInputDialog(wnd, "Name of the new rigid body?");
		if (name != null) {
			RigidBodyModel model = new RigidBodyModel();
			model.setName(name);
			ObjectsManager.instance().getRigidBodies().add(model);
			ObjectsManager.instance().setSelectedRigidBody(model);
		}
	}

	private void delRigidBody() {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model != null) ObjectsManager.instance().getRigidBodies().remove(model);
	}


	private void editRigidBody() {
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model != null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			String name = JOptionPane.showInputDialog(wnd, "Name of the selected rigid body?", model.getName());
			if (name != null) model.setName(name);
		}
	}

	private void addDynamicObject() {
		Window wnd = SwingUtilities.getWindowAncestor(this);
		String name = JOptionPane.showInputDialog(wnd, "Name of the new dynamic object?");
		if (name != null) {
			DynamicObjectModel model = new DynamicObjectModel();
			model.setName(name);
			ObjectsManager.instance().getDynamicObjects().add(model);
			ObjectsManager.instance().setSelectedDynamicObject(model);
		}
	}

	private void delDynamicObject() {
		DynamicObjectModel model = ObjectsManager.instance().getSelectedDynamicObject();
		if (model != null) ObjectsManager.instance().getDynamicObjects().remove(model);
	}


	private void editDynamicObject() {
		DynamicObjectModel model = ObjectsManager.instance().getSelectedDynamicObject();
		if (model != null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			String name = JOptionPane.showInputDialog(wnd, "Name of the selected dynamic object?", model.getName());
			if (name != null) model.setName(name);
		}
	}

	// -------------------------------------------------------------------------
	// ListCellRenderers
	// -------------------------------------------------------------------------

	private final ListCellRenderer bodiesListCellRenderer = new ListCellRenderer() {
		private final JLabel label = new JLabel() {{
			setBorder(new EmptyBorder(2, 5, 2, 5));
			setIcon(assetIcon);
		}};

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			RigidBodyModel model = (RigidBodyModel)value;
			String path = IoManager.instance().relativize(model.getImagePath());
			String txt = model.getName();
			if (!model.getImagePath().equals("")) txt += "  --  \"" + path + "\"";

			label.setText(txt);

			if (isSelected) {
				label.setBackground(Theme.TEXTAREA_SELECTED_BACKGROUND);
				label.setForeground(Theme.TEXTAREA_SELECTED_FOREGROUND);
				label.setOpaque(true);
			} else {
				label.setForeground(Theme.TEXTAREA_FOREGROUND);
				label.setOpaque(false);
			}

			return label;
		}
	};

	private final ListCellRenderer objectsListCellRenderer = new ListCellRenderer() {
		private final JLabel label = new JLabel() {{
			setBorder(new EmptyBorder(2, 5, 2, 5));
			setIcon(assetIcon);
		}};

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			DynamicObjectModel model = (DynamicObjectModel)value;
			String txt = model.getName();
			if (!model.getRigidBodies().isEmpty()) {
				txt += "  (" + model.getRigidBodies().get(0);
				for (int i=1; i<model.getRigidBodies().size(); i++)
					txt += " + " + model.getRigidBodies().get(i).getName();
				txt += ")";
			}

			label.setText(txt);

			if (isSelected) {
				label.setBackground(Theme.TEXTAREA_SELECTED_BACKGROUND);
				label.setForeground(Theme.TEXTAREA_SELECTED_FOREGROUND);
				label.setOpaque(true);
			} else {
				label.setForeground(Theme.TEXTAREA_FOREGROUND);
				label.setOpaque(false);
			}

			return label;
		}
	};

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        addBodyBtn = new javax.swing.JLabel();
        editBodyBtn = new javax.swing.JLabel();
        delBodyBtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bodiesList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        addObjectBtn = new javax.swing.JLabel();
        editObjectBtn = new javax.swing.JLabel();
        delObjectBtn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        objectsList = new javax.swing.JList();

        jPanel1.setOpaque(false);

        jLabel6.setText("Rigid bodies");

        addBodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N

        editBodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_edit.png"))); // NOI18N

        delBodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(252, Short.MAX_VALUE)
                .addComponent(addBodyBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editBodyBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delBodyBtn))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addContainerGap(255, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(delBodyBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(editBodyBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addBodyBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        bodiesList.setBackground(Theme.TEXTAREA_BACKGROUND);
        bodiesList.setForeground(Theme.TEXTAREA_FOREGROUND);
        bodiesList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(bodiesList);

        jPanel2.setOpaque(false);

        jLabel9.setText("Dynamic objects");

        addObjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N

        editObjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_edit.png"))); // NOI18N

        delObjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                .addComponent(addObjectBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editObjectBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delObjectBtn))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(delObjectBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(editObjectBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addObjectBtn, javax.swing.GroupLayout.Alignment.TRAILING))
        );

        objectsList.setBackground(Theme.TEXTAREA_BACKGROUND);
        objectsList.setForeground(Theme.TEXTAREA_FOREGROUND);
        objectsList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(objectsList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBodyBtn;
    private javax.swing.JLabel addObjectBtn;
    private javax.swing.JList bodiesList;
    private javax.swing.JLabel delBodyBtn;
    private javax.swing.JLabel delObjectBtn;
    private javax.swing.JLabel editBodyBtn;
    private javax.swing.JLabel editObjectBtn;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList objectsList;
    // End of variables declaration//GEN-END:variables

}
