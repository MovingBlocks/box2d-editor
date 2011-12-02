package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.AppEvents;
import aurelienribon.bodyeditor.AppObjects;
import aurelienribon.bodyeditor.EarClippingManager.Polygonizers;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import java.awt.Window;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
        jLabel6 = new javax.swing.JLabel();
        drawShapeChk = new javax.swing.JCheckBox();
        drawGridChk = new javax.swing.JCheckBox();
        enableSnapToGridChk = new javax.swing.JCheckBox();
        drawPolysChk = new javax.swing.JCheckBox();
        drawAssetChk = new javax.swing.JCheckBox();
        polygonizerCbox = new javax.swing.JComboBox();
        gridGapSpinner = new javax.swing.JSpinner();

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

        jLabel6.setText("Polygonizer:");

        drawShapeChk.setSelected(true);
        drawShapeChk.setText("Draw shapes");
        drawShapeChk.setOpaque(false);
        drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawShapeChkActionPerformed(evt);
            }
        });

        drawGridChk.setText("Draw grid with gap:");
        drawGridChk.setOpaque(false);
        drawGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawGridChkActionPerformed(evt);
            }
        });

        enableSnapToGridChk.setText("Enable snap-to-grid");
        enableSnapToGridChk.setOpaque(false);
        enableSnapToGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableSnapToGridChkActionPerformed(evt);
            }
        });

        drawPolysChk.setSelected(true);
        drawPolysChk.setText("Draw convex polygons");
        drawPolysChk.setOpaque(false);
        drawPolysChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawPolysChkActionPerformed(evt);
            }
        });

        drawAssetChk.setSelected(true);
        drawAssetChk.setText("Draw background image");
        drawAssetChk.setOpaque(false);
        drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawAssetChkActionPerformed(evt);
            }
        });

        polygonizerCbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BAYAZIT", "EWJORDAN" }));
        polygonizerCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polygonizerCboxActionPerformed(evt);
            }
        });

        gridGapSpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.03f), Float.valueOf(0.0010f), Float.valueOf(1.0f), Float.valueOf(0.01f)));
        gridGapSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridGapSpinnerStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawAssetChk)
                    .addComponent(drawShapeChk)
                    .addComponent(drawPolysChk))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(drawGridChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(enableSnapToGridChk))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(drawAssetChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawShapeChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(drawPolysChk))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(drawGridChk)
                            .addComponent(gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(enableSnapToGridChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        add(jPanel2);
    }// </editor-fold>//GEN-END:initComponents

	private void drawShapeChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawShapeChkActionPerformed
		Settings.isShapeDrawn = drawShapeChk.isSelected();
}//GEN-LAST:event_drawShapeChkActionPerformed

	private void drawAssetChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawAssetChkActionPerformed
		Settings.isImageDrawn = drawAssetChk.isSelected();
}//GEN-LAST:event_drawAssetChkActionPerformed

	private void drawPolysChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawPolysChkActionPerformed
		Settings.isPolygonDrawn = drawPolysChk.isSelected();
}//GEN-LAST:event_drawPolysChkActionPerformed

	private void gridGapSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_gridGapSpinnerStateChanged
		Settings.gridGap = (Float)gridGapSpinner.getValue();
}//GEN-LAST:event_gridGapSpinnerStateChanged

	private void polygonizerCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polygonizerCboxActionPerformed
		Settings.polygonizer = Polygonizers.valueOf((String) polygonizerCbox.getSelectedItem());
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
		AppEvents.fireRecreateWorldRequested();
}//GEN-LAST:event_polygonizerCboxActionPerformed

	private void drawGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drawGridChkActionPerformed
		Settings.isGridShown = drawGridChk.isSelected();
}//GEN-LAST:event_drawGridChkActionPerformed

	private void enableSnapToGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableSnapToGridChkActionPerformed
		Settings.isSnapToGridEnabled = enableSnapToGridChk.isSelected();
}//GEN-LAST:event_enableSnapToGridChkActionPerformed

	private void insertPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertPointsBtnActionPerformed
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(wnd, "Please create or choose a rigid body first.");
			return;
		}

		AppObjects.insertPointsBetweenSelected();
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
}//GEN-LAST:event_insertPointsBtnActionPerformed

	private void removePointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removePointsBtnActionPerformed
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(wnd, "Please create or choose a rigid body first.");
			return;
		}

		AppObjects.removeSelectedPoints();
		ObjectsManager.instance().getSelectedRigidBody().computePolygons();
}//GEN-LAST:event_removePointsBtnActionPerformed

	private void clearPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearPointsBtnActionPerformed
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(wnd, "Please create or choose a rigid body first.");
			return;
		}

		ObjectsManager.instance().getSelectedRigidBody().clear();
		AppEvents.fireRecreateWorldRequested();
}//GEN-LAST:event_clearPointsBtnActionPerformed

	private void setBackgroundImageBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setBackgroundImageBtnActionPerformed
		RigidBodyModel model = ObjectsManager.instance().getSelectedRigidBody();
		if (model == null) {
			Window wnd = SwingUtilities.getWindowAncestor(this);
			JOptionPane.showMessageDialog(wnd, "Please create or choose a rigid body first.");
			return;
		}

		File image = promptImage();
		if (image != null && image.isFile()) {
			model.setImagePath(image.getPath());
			ObjectsManager.instance().setSelectedRigidBody(model);
		}
	}//GEN-LAST:event_setBackgroundImageBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearPointsBtn;
    private javax.swing.JCheckBox drawAssetChk;
    private javax.swing.JCheckBox drawGridChk;
    private javax.swing.JCheckBox drawPolysChk;
    private javax.swing.JCheckBox drawShapeChk;
    private javax.swing.JCheckBox enableSnapToGridChk;
    private javax.swing.JSpinner gridGapSpinner;
    private javax.swing.JButton insertPointsBtn;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JComboBox polygonizerCbox;
    private javax.swing.JButton removePointsBtn;
    private javax.swing.JButton setBackgroundImageBtn;
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
		File outputFile = IoManager.instance().getProjectFile();
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
