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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        setBackgroundImageBtn = new javax.swing.JButton();
        clearPointsBtn = new javax.swing.JButton();
        insertPointsBtn = new javax.swing.JButton();
        removePointsBtn = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        drawGridChk = new javax.swing.JCheckBox();
        drawPolysChk = new javax.swing.JCheckBox();
        drawAssetChk = new javax.swing.JCheckBox();
        gridGapSpinner = new javax.swing.JSpinner();
        drawShapeChk = new javax.swing.JCheckBox();
        polygonizerCbox = new javax.swing.JComboBox();
        enableSnapToGridChk = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        setCreationModeBtn = new javax.swing.JToggleButton();
        setEditionModeBtn = new javax.swing.JToggleButton();
        setTestModeBtn = new javax.swing.JToggleButton();

        setOpaque(false);

        jPanel5.setBorder(new aurelienribon.utils.ui.GroupBorder());

        setBackgroundImageBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_texture.png"))); // NOI18N
        setBackgroundImageBtn.setText("Set background image");
        setBackgroundImageBtn.setFocusable(false);
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
        clearPointsBtn.setFocusable(false);
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
        insertPointsBtn.setFocusable(false);
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
        removePointsBtn.setFocusable(false);
        removePointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        removePointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        removePointsBtn.setOpaque(false);
        removePointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removePointsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(setBackgroundImageBtn)
                    .addComponent(clearPointsBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(insertPointsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removePointsBtn))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearPointsBtn, setBackgroundImageBtn});

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {insertPointsBtn, removePointsBtn});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(setBackgroundImageBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearPointsBtn))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(insertPointsBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removePointsBtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(new aurelienribon.utils.ui.GroupBorder());

        jLabel6.setText("Polygonizer:");
        jLabel6.setFocusable(false);

        drawGridChk.setText("Draw grid with gap:");
        drawGridChk.setFocusable(false);
        drawGridChk.setOpaque(false);
        drawGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawGridChkActionPerformed(evt);
            }
        });

        drawPolysChk.setSelected(true);
        drawPolysChk.setText("Draw convex polygons");
        drawPolysChk.setFocusable(false);
        drawPolysChk.setOpaque(false);
        drawPolysChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawPolysChkActionPerformed(evt);
            }
        });

        drawAssetChk.setSelected(true);
        drawAssetChk.setText("Draw background image");
        drawAssetChk.setFocusable(false);
        drawAssetChk.setOpaque(false);
        drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawAssetChkActionPerformed(evt);
            }
        });

        gridGapSpinner.setModel(new javax.swing.SpinnerNumberModel(Float.valueOf(0.03f), Float.valueOf(0.0010f), Float.valueOf(1.0f), Float.valueOf(0.01f)));
        gridGapSpinner.setFocusable(false);
        gridGapSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                gridGapSpinnerStateChanged(evt);
            }
        });

        drawShapeChk.setSelected(true);
        drawShapeChk.setText("Draw shapes");
        drawShapeChk.setFocusable(false);
        drawShapeChk.setOpaque(false);
        drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drawShapeChkActionPerformed(evt);
            }
        });

        polygonizerCbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BAYAZIT", "EWJORDAN" }));
        polygonizerCbox.setFocusable(false);
        polygonizerCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polygonizerCboxActionPerformed(evt);
            }
        });

        enableSnapToGridChk.setText("Enable snap-to-grid");
        enableSnapToGridChk.setFocusable(false);
        enableSnapToGridChk.setOpaque(false);
        enableSnapToGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableSnapToGridChkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(drawAssetChk)
                    .addComponent(drawShapeChk)
                    .addComponent(drawPolysChk))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(enableSnapToGridChk)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(drawGridChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drawAssetChk)
                    .addComponent(drawGridChk)
                    .addComponent(gridGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drawShapeChk)
                    .addComponent(enableSnapToGridChk))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(drawPolysChk)
                    .addComponent(jLabel6)
                    .addComponent(polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel1.setBorder(new aurelienribon.utils.ui.GroupBorder());

        buttonGroup1.add(setCreationModeBtn);
        setCreationModeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_createShape.png"))); // NOI18N
        setCreationModeBtn.setSelected(true);
        setCreationModeBtn.setText("Creation");
        setCreationModeBtn.setFocusable(false);
        setCreationModeBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setCreationModeBtn.setMargin(new java.awt.Insets(2, 2, 2, 3));
        setCreationModeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setCreationModeBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(setEditionModeBtn);
        setEditionModeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_editShape.png"))); // NOI18N
        setEditionModeBtn.setText("Edition");
        setEditionModeBtn.setFocusable(false);
        setEditionModeBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setEditionModeBtn.setMargin(new java.awt.Insets(2, 2, 2, 3));
        setEditionModeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setEditionModeBtnActionPerformed(evt);
            }
        });

        buttonGroup1.add(setTestModeBtn);
        setTestModeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_test.png"))); // NOI18N
        setTestModeBtn.setText("Test");
        setTestModeBtn.setFocusable(false);
        setTestModeBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        setTestModeBtn.setMargin(new java.awt.Insets(2, 2, 2, 3));
        setTestModeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setTestModeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(setCreationModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setEditionModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setTestModeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {setCreationModeBtn, setEditionModeBtn, setTestModeBtn});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(setCreationModeBtn)
                    .addComponent(setEditionModeBtn)
                    .addComponent(setTestModeBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel1, jPanel5});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
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

	private void setCreationModeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setCreationModeBtnActionPerformed
		Settings.mode = Settings.Modes.CREATION;
	}//GEN-LAST:event_setCreationModeBtnActionPerformed

	private void setEditionModeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setEditionModeBtnActionPerformed
		Settings.mode = Settings.Modes.EDITION;
	}//GEN-LAST:event_setEditionModeBtnActionPerformed

	private void setTestModeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setTestModeBtnActionPerformed
		Settings.mode = Settings.Modes.TEST;
	}//GEN-LAST:event_setTestModeBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JComboBox polygonizerCbox;
    private javax.swing.JButton removePointsBtn;
    private javax.swing.JButton setBackgroundImageBtn;
    private javax.swing.JToggleButton setCreationModeBtn;
    private javax.swing.JToggleButton setEditionModeBtn;
    private javax.swing.JToggleButton setTestModeBtn;
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
