package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.EarClippingManager.Polygonizers;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import java.awt.Window;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodyOptionsPanel extends javax.swing.JPanel {
    public RigidBodyOptionsPanel() {
        initComponents();
    }
	
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        setBackgroundImageBtn = new javax.swing.JButton();
        clearPointsBtn = new javax.swing.JButton();
        insertPointsBtn = new javax.swing.JButton();
        removePointsBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        shape_drawShapeChk = new javax.swing.JCheckBox();
        shape_drawGridChk = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        shape_enableSnapToGridChk = new javax.swing.JCheckBox();
        shape_drawPolysChk = new javax.swing.JCheckBox();
        shape_drawAssetChk = new javax.swing.JCheckBox();
        shape_polygonizerCbox = new javax.swing.JComboBox();
        shape_gridGapSpinner = new javax.swing.JSpinner();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setOpaque(false);

        setBackgroundImageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_texture.png"))); // NOI18N
        setBackgroundImageBtn.setText("Set background image");
        setBackgroundImageBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setBackgroundImageBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        setBackgroundImageBtn.setOpaque(false);
        setBackgroundImageBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setBackgroundImageBtnActionPerformed(evt);
            }
        });

        clearPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N
        clearPointsBtn.setText("Clear all points");
        clearPointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clearPointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        clearPointsBtn.setOpaque(false);
        clearPointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearPointsBtnActionPerformed(evt);
            }
        });

        insertPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N
        insertPointsBtn.setText("Insert point(s)");
        insertPointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        insertPointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        insertPointsBtn.setOpaque(false);
        insertPointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertPointsBtnActionPerformed(evt);
            }
        });

        removePointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_remove.png"))); // NOI18N
        removePointsBtn.setText("Remove point(s)");
        removePointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removePointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        removePointsBtn.setOpaque(false);
        removePointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePointsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setBackgroundImageBtn)
                    .addComponent(clearPointsBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insertPointsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removePointsBtn))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearPointsBtn, setBackgroundImageBtn});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {insertPointsBtn, removePointsBtn});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(insertPointsBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removePointsBtn))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(setBackgroundImageBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearPointsBtn)))
                .addContainerGap())
        );

        jPanel1.add(jPanel3);

        add(jPanel1);

        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 10, 0, 0, Theme.MAIN_BACKGROUND));
        jPanel2.setOpaque(false);

        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shapes");
        shape_drawShapeChk.setOpaque(false);
        shape_drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawShapeChkActionPerformed(evt);
            }
        });

        shape_drawGridChk.setText("Draw grid with gap (px):");
        shape_drawGridChk.setOpaque(false);
        shape_drawGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawGridChkActionPerformed(evt);
            }
        });

        jLabel6.setText("Polygonizer:");

        shape_enableSnapToGridChk.setText("Enable snap-to-grid");
        shape_enableSnapToGridChk.setOpaque(false);
        shape_enableSnapToGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_enableSnapToGridChkActionPerformed(evt);
            }
        });

        shape_drawPolysChk.setSelected(true);
        shape_drawPolysChk.setText("Draw convex polygons");
        shape_drawPolysChk.setOpaque(false);
        shape_drawPolysChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawPolysChkActionPerformed(evt);
            }
        });

        shape_drawAssetChk.setSelected(true);
        shape_drawAssetChk.setText("Draw background image");
        shape_drawAssetChk.setOpaque(false);
        shape_drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetChkActionPerformed(evt);
            }
        });

        shape_polygonizerCbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BAYAZIT", "EWJORDAN" }));
        shape_polygonizerCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_polygonizerCboxActionPerformed(evt);
            }
        });

        shape_gridGapSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));
        shape_gridGapSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                shape_gridGapSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shape_drawAssetChk)
                    .addComponent(shape_drawShapeChk)
                    .addComponent(shape_drawPolysChk))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(shape_drawGridChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(shape_enableSnapToGridChk))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(shape_drawAssetChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_drawShapeChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_drawPolysChk))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(shape_drawGridChk)
                            .addComponent(shape_gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_enableSnapToGridChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(shape_polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

	private void shape_drawShapeChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawShapeChkActionPerformed
		Settings.isShapeDrawn = shape_drawShapeChk.isSelected();
}//GEN-LAST:event_shape_drawShapeChkActionPerformed

	private void shape_drawAssetChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawAssetChkActionPerformed
		Settings.isImageDrawn = shape_drawAssetChk.isSelected();
}//GEN-LAST:event_shape_drawAssetChkActionPerformed

	private void shape_drawPolysChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawPolysChkActionPerformed
		Settings.isPolygonDrawn = shape_drawPolysChk.isSelected();
}//GEN-LAST:event_shape_drawPolysChkActionPerformed

	private void shape_gridGapSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_shape_gridGapSpinnerStateChanged
		Settings.gridGap = (Integer)shape_gridGapSpinner.getValue();
}//GEN-LAST:event_shape_gridGapSpinnerStateChanged

	private void shape_polygonizerCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_polygonizerCboxActionPerformed
		Settings.polygonizer = Polygonizers.valueOf((String) shape_polygonizerCbox.getSelectedItem());
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
		AppManager.instance().getRenderPanel().createBody();
}//GEN-LAST:event_shape_polygonizerCboxActionPerformed

	private void shape_drawGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawGridChkActionPerformed
		Settings.isGridShown = shape_drawGridChk.isSelected();
}//GEN-LAST:event_shape_drawGridChkActionPerformed

	private void shape_enableSnapToGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_enableSnapToGridChkActionPerformed
		Settings.isSnapToGridEnabled = shape_enableSnapToGridChk.isSelected();
}//GEN-LAST:event_shape_enableSnapToGridChkActionPerformed

	private void insertPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertPointsBtnActionPerformed
		AppManager.instance().insertPointBetweenSelected();
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
}//GEN-LAST:event_insertPointsBtnActionPerformed

	private void removePointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePointsBtnActionPerformed
		AppManager.instance().removeSelectedPoints();
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
}//GEN-LAST:event_removePointsBtnActionPerformed

	private void clearPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPointsBtnActionPerformed
		ObjectsManager.instance().getSelectedRigidBody().clear();
		AppManager.instance().getRenderPanel().createBody();
}//GEN-LAST:event_clearPointsBtnActionPerformed

	private void setBackgroundImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBackgroundImageBtnActionPerformed
		File image = promptImage();
		if (image != null && image.isFile()) {
			RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
			model.setImagePath(image.getPath());
			ObjectsManager.instance().setSelectedRigidBody(model);
		}
	}//GEN-LAST:event_setBackgroundImageBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearPointsBtn;
    private javax.swing.JButton insertPointsBtn;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JButton removePointsBtn;
    private javax.swing.JButton setBackgroundImageBtn;
    private javax.swing.JCheckBox shape_drawAssetChk;
    private javax.swing.JCheckBox shape_drawGridChk;
    private javax.swing.JCheckBox shape_drawPolysChk;
    private javax.swing.JCheckBox shape_drawShapeChk;
    private javax.swing.JCheckBox shape_enableSnapToGridChk;
    private javax.swing.JSpinner shape_gridGapSpinner;
    private javax.swing.JComboBox shape_polygonizerCbox;
    // End of variables declaration//GEN-END:variables

	// -------------------------------------------------------------------------
	// Image Prompt
	// -------------------------------------------------------------------------

	private final FileFilter imageUiFilter = new FileFilter() {
		@Override
		public boolean accept(File f) {
			if (f.isDirectory()) return true;
			return isSameString(getExtension(f), "png", "jpg", "jpeg");
		}

		@Override
		public String getDescription() {
			return "Image files or directories";
		}
	};

    private File promptImage() {
		File outputFile = IoManager.instance().getOutputFile();
		String startupPath = outputFile != null ? outputFile.getParent() : ".";

		JFileChooser chooser = new JFileChooser(startupPath);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(imageUiFilter);

		Window wnd = SwingUtilities.getWindowAncestor(this);
		
		if (chooser.showOpenDialog(wnd) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();

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
