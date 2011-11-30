package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.utils.notifications.AutoListModel;
import aurelienribon.utils.notifications.ChangeListener;
import java.awt.Component;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
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
		bodiesList.setModel(new AutoListModel(ObjectsManager.instance().getRigidBodiesList()));
		bodiesList.setCellRenderer(listCellRdr);

		bodiesList.addListSelectionListener(new ListSelectionListener() {
			@Override public void valueChanged(ListSelectionEvent e) {
				ObjectsManager.instance().setSelectedRigidBody((RigidBodyModel)bodiesList.getSelectedValue());
			}
		});

		ObjectsManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals("selectedAsset")) {
					RigidBodyModel am = ObjectsManager.instance().getSelectedRigidBody();
					if (am != bodiesList.getSelectedValue() || bodiesList.getSelectedValues().length > 1)
						bodiesList.setSelectedValue(am, true);
				}
			}
		});

		IoManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				((AutoListModel)bodiesList.getModel()).forceRefresh();
			}
		});
    }
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        addBodyBtn = new javax.swing.JLabel();
        delBodyBtn = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bodiesList = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        addObjectBtn = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        delObjectBtn = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        objectsList = new javax.swing.JList();

        jPanel1.setOpaque(false);

        jLabel6.setText("Rigid bodies");

        addBodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N

        delBodyBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 217, Short.MAX_VALUE)
                .addComponent(addBodyBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delBodyBtn))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(delBodyBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(addBodyBtn, javax.swing.GroupLayout.Alignment.TRAILING))
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

        addObjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N

        jLabel9.setText("Dynamic objects");

        delObjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(addObjectBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delObjectBtn))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(delObjectBtn)
                    .addComponent(addObjectBtn))
                .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBodyBtn;
    private javax.swing.JLabel addObjectBtn;
    private javax.swing.JList bodiesList;
    private javax.swing.JLabel delBodyBtn;
    private javax.swing.JLabel delObjectBtn;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList objectsList;
    // End of variables declaration//GEN-END:variables

	// -------------------------------------------------------------------------
	// ListCellRenderer
	// -------------------------------------------------------------------------

	private final ListCellRenderer listCellRdr = new ListCellRenderer() {
		private final JLabel label = new JLabel();

		{
			label.setBorder(new EmptyBorder(2, 5, 2, 5));
			label.setIcon(assetIcon);
		}
		
		@Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
			RigidBodyModel am = (RigidBodyModel)value;
			String path = IoManager.instance().relativize(am.getPath());
			label.setText(path);

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
	// Asset Prompter
	// -------------------------------------------------------------------------

	private final javax.swing.filechooser.FileFilter imageUiFilter = new javax.swing.filechooser.FileFilter() {
		@Override public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			return isSameString(getExtension(f), "png", "jpg", "jpeg");
		}

		@Override public String getDescription() {
			return "Image files or directories";
		}
	};

	private final java.io.FileFilter imageFilter = new java.io.FileFilter() {
		@Override
		public boolean accept(File f) {
			if (f.isDirectory())
				return true;
			return isSameString(getExtension(f), "png", "jpg", "jpeg");
		}
	};

    private String[] promptAssetsByFiles() {
		File outputFile = IoManager.instance().getOutputFile();
		String startupPath = outputFile != null ? outputFile.getParent() : ".";

		JFileChooser chooser = new JFileChooser(startupPath);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(imageUiFilter);

		if (chooser.showOpenDialog(SwingUtilities.getWindowAncestor(this)) == JFileChooser.APPROVE_OPTION) {
			List<String> assetPaths = new ArrayList<String>();
			List<File> remainingFiles = new ArrayList<File>();

			Collections.addAll(remainingFiles, chooser.getSelectedFiles());
			int deepness = 0;
			int maxDeepness = 8;

			while (!remainingFiles.isEmpty()) {
				File currentFile = remainingFiles.remove(0);
				if (currentFile.isFile()) {
					assetPaths.add(currentFile.getPath());
				} else if (deepness < maxDeepness) {
					deepness += 1;
					Collections.addAll(remainingFiles, currentFile.listFiles(imageFilter));
				}
			}

			return assetPaths.toArray(new String[assetPaths.size()]);
		}

		return null;
	}

	private boolean isSameString(String str1, String... strs) {
		for (String str : strs)
			if (str1.equalsIgnoreCase(str))
				return true;
		return false;
	}

	private String getExtension(File f) {
		int idx = f.getName().lastIndexOf(".");
		if (idx < 0 || idx == f.getName().length()-1)
			return "";
		return f.getName().substring(idx+1);
	}
}
