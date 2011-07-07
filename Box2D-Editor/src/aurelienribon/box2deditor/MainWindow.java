package aurelienribon.box2deditor;

import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MainWindow extends javax.swing.JFrame {
	private final DefaultListModel assetsListModel;
	private File assetsRootDir;
	private File outputFile;

    public MainWindow(final LwjglCanvas glCanvas) {
        initComponents();
		renderPanel.add(glCanvas.getCanvas(), BorderLayout.CENTER);

		assetsListModel = new DefaultListModel();
		assets_assetList.setModel(assetsListModel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renderPanel = new javax.swing.JPanel();
        westPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        assets_assetListScrollPane = new javax.swing.JScrollPane();
        assets_assetList = new javax.swing.JList();
        assets_removeAssetBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        init_setAssetsRootBtn = new javax.swing.JButton();
        init_addAssetsByFilesBtn = new javax.swing.JButton();
        init_addAssetsByPackBtn = new javax.swing.JButton();
        init_setOutputFileBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        init_assetsRootDirLbl = new javax.swing.JTextField();
        init_outputFileLbl = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        export_saveBtn = new javax.swing.JButton();
        eastPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        shape_computeBtn = new javax.swing.JButton();
        shape_clearBtn = new javax.swing.JButton();
        shape_addbtn = new javax.swing.JButton();
        shape_subBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        shape_drawShapeChk = new javax.swing.JCheckBox();
        shape_drawPolysChk = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        renderPanel.setLayout(new java.awt.BorderLayout());
        getContentPane().add(renderPanel, java.awt.BorderLayout.CENTER);

        westPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("    Assets");
        jLabel1.setOpaque(true);

        assets_assetList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        assets_assetList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        assets_assetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                assets_assetListValueChanged(evt);
            }
        });
        assets_assetListScrollPane.setViewportView(assets_assetList);

        assets_removeAssetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_delete.png"))); // NOI18N
        assets_removeAssetBtn.setText("Remove selected asset");
        assets_removeAssetBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        assets_removeAssetBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        assets_removeAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assets_removeAssetBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assets_removeAssetBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assets_assetListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(assets_assetListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assets_removeAssetBtn)
                .addContainerGap())
        );

        init_setAssetsRootBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_import.png"))); // NOI18N
        init_setAssetsRootBtn.setText("Set assets root dir");
        init_setAssetsRootBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_setAssetsRootBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        init_setAssetsRootBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_setAssetsRootBtnActionPerformed(evt);
            }
        });

        init_addAssetsByFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_import.png"))); // NOI18N
        init_addAssetsByFilesBtn.setText("Add assets by image files or dirs");
        init_addAssetsByFilesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_addAssetsByFilesBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        init_addAssetsByFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_addAssetsByFilesBtnActionPerformed(evt);
            }
        });

        init_addAssetsByPackBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_import.png"))); // NOI18N
        init_addAssetsByPackBtn.setText("Add assets by image packs");
        init_addAssetsByPackBtn.setEnabled(false);
        init_addAssetsByPackBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_addAssetsByPackBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        init_addAssetsByPackBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_addAssetsByPackBtnActionPerformed(evt);
            }
        });

        init_setOutputFileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_import.png"))); // NOI18N
        init_setOutputFileBtn.setText("Set output file");
        init_setOutputFileBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_setOutputFileBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        init_setOutputFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_setOutputFileBtnActionPerformed(evt);
            }
        });

        jLabel9.setBackground(new java.awt.Color(102, 102, 102));
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("    Configuration");
        jLabel9.setOpaque(true);

        init_assetsRootDirLbl.setEditable(false);
        init_assetsRootDirLbl.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        init_assetsRootDirLbl.setText("<no dir specified>");

        init_outputFileLbl.setEditable(false);
        init_outputFileLbl.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        init_outputFileLbl.setText("<no file specified>");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(init_setAssetsRootBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(init_assetsRootDirLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(init_setOutputFileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(init_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(init_addAssetsByFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(init_addAssetsByPackBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(init_setAssetsRootBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_assetsRootDirLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_setOutputFileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_outputFileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(init_addAssetsByFilesBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_addAssetsByPackBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel11.setBackground(new java.awt.Color(102, 102, 102));
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("    Export");
        jLabel11.setOpaque(true);

        export_saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_export.png"))); // NOI18N
        export_saveBtn.setText("Save");
        export_saveBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        export_saveBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        export_saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(export_saveBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(export_saveBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout westPanelLayout = new javax.swing.GroupLayout(westPanel);
        westPanel.setLayout(westPanelLayout);
        westPanelLayout.setHorizontalGroup(
            westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        westPanelLayout.setVerticalGroup(
            westPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(westPanelLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(westPanel, java.awt.BorderLayout.WEST);

        eastPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel6.setBackground(new java.awt.Color(102, 102, 102));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("    Shape creation");
        jLabel6.setOpaque(true);

        shape_computeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_shape.png"))); // NOI18N
        shape_computeBtn.setText("Compute convex polygons");
        shape_computeBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        shape_computeBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        shape_computeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_computeBtnActionPerformed(evt);
            }
        });

        shape_clearBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_delete.png"))); // NOI18N
        shape_clearBtn.setText("Clear points");
        shape_clearBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        shape_clearBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        shape_clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_clearBtnActionPerformed(evt);
            }
        });

        shape_addbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_add.png"))); // NOI18N
        shape_addbtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        shape_addbtn.setMargin(new java.awt.Insets(2, 3, 2, 2));

        shape_subBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_remove.png"))); // NOI18N
        shape_subBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        shape_subBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("<html>To define an object shape, first use some right clics to add points defining the hull of the shape. Then, clic on \"compute convex shapes\". You're done !<br/><br/> You can select multiple points by dragging the mouse around them. Select 2 points and you will be able to add another one between them with the \"+\" button.<br/><br/>Zoom with mouse wheel, pan by holding the left mouse button.");

        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shape borders");
        shape_drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawShapeChkActionPerformed(evt);
            }
        });

        shape_drawPolysChk.setSelected(true);
        shape_drawPolysChk.setText("Draw convex polygons");
        shape_drawPolysChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawPolysChkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_computeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_addbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_subBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_drawShapeChk)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_drawPolysChk)
                .addContainerGap(49, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(shape_computeBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shape_clearBtn)
                        .addComponent(shape_addbtn))
                    .addComponent(shape_subBtn))
                .addGap(18, 18, 18)
                .addComponent(shape_drawShapeChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawPolysChk)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(160, Short.MAX_VALUE))
        );

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/logo.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout eastPanelLayout = new javax.swing.GroupLayout(eastPanel);
        eastPanel.setLayout(eastPanelLayout);
        eastPanelLayout.setHorizontalGroup(
            eastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        eastPanelLayout.setVerticalGroup(
            eastPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(eastPanelLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(eastPanel, java.awt.BorderLayout.EAST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void init_addAssetsByFilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_init_addAssetsByFilesBtnActionPerformed
		String[] assets = promptAssetsByFiles();
		if (assets != null) {
			for (String asset : assets) {
				if (assetsRootDir != null) {
					String assetRootPath = asset.substring(0, Math.min(assetsRootDir.getPath().length(), asset.length()));
					String assetTruncatedPath = asset.substring(assetRootPath.length());
					if (isSameString(assetRootPath, assetsRootDir.getPath()) && !assetsListModel.contains(assetTruncatedPath))
						addAssetTolist(assetTruncatedPath);
				} else if (!assetsListModel.contains(asset)) {
					addAssetTolist(asset);
				}
			}
		}
	}//GEN-LAST:event_init_addAssetsByFilesBtnActionPerformed

	private void init_addAssetsByPackBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_init_addAssetsByPackBtnActionPerformed
		// TODO add your handling code here:
	}//GEN-LAST:event_init_addAssetsByPackBtnActionPerformed

	private void init_setOutputFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_init_setOutputFileBtnActionPerformed
		File file = promptOutputFile();
		if (file != null) {
			outputFile = file;
			init_outputFileLbl.setText(file.getPath());
		}
	}//GEN-LAST:event_init_setOutputFileBtnActionPerformed

	private void export_saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_saveBtnActionPerformed
		if (outputFile == null) {
			JOptionPane.showMessageDialog(this, "Output file has not been set.");
			return;
		}
	}//GEN-LAST:event_export_saveBtnActionPerformed

	private void assets_assetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_assets_assetListValueChanged
		AppContext.instance().clearTempObjects();

		if (assets_assetList.isSelectionEmpty()) {
			App.instance().clearAsset();
			return;
		}

		String assetName = (String) assets_assetList.getSelectedValue();
		AppContext.instance().setCurrentAssetPath(assetName);
		AppContext.instance().loadTempShapePoints();
		
		String assetPath = assetsRootDir != null
			? assetsRootDir.getPath() + assetName
			: assetName;
		App.instance().setAssetByFile(assetPath);
	}//GEN-LAST:event_assets_assetListValueChanged

	private void init_setAssetsRootBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_init_setAssetsRootBtnActionPerformed
		File dir = promptAssetsRootDir();
		if (dir != null) {
			for (int i=0; i<assetsListModel.size(); i++) {
				String asset = assetsRootDir != null
					? assetsRootDir.getPath() + (String) assetsListModel.get(i)
					: (String) assetsListModel.get(i);
				String assetRootPath = asset.substring(0, Math.min(dir.getPath().length(), asset.length()));
				if (isSameString(assetRootPath, dir.getPath()))
					changeAssetName(i, asset.substring(assetRootPath.length()));
			}

			assetsRootDir = dir;
			init_assetsRootDirLbl.setText(assetsRootDir.getPath());
		}
	}//GEN-LAST:event_init_setAssetsRootBtnActionPerformed

	private void assets_removeAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assets_removeAssetBtnActionPerformed
		if (!assets_assetList.isSelectionEmpty()) {
			int idx = assets_assetList.getSelectedIndex();
			removeAssetFromlist(idx);
		}
	}//GEN-LAST:event_assets_removeAssetBtnActionPerformed

	private void shape_clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_clearBtnActionPerformed
		AppContext.instance().clearTempObjects();
		AppContext.instance().clearCurrentObject();
	}//GEN-LAST:event_shape_clearBtnActionPerformed

	private void shape_computeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_computeBtnActionPerformed
		AppContext.instance().computePolygons();
	}//GEN-LAST:event_shape_computeBtnActionPerformed

	private void shape_drawShapeChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawShapeChkActionPerformed
		AppContext.instance().isShapeDrawn = shape_drawShapeChk.isSelected();
	}//GEN-LAST:event_shape_drawShapeChkActionPerformed

	private void shape_drawPolysChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawPolysChkActionPerformed
		AppContext.instance().arePolyDrawn = shape_drawPolysChk.isSelected();
	}//GEN-LAST:event_shape_drawPolysChkActionPerformed

	private void addAssetTolist(String asset) {
		assetsListModel.addElement(asset);
		AppContext.instance().addBodyModel(asset);
	}

	private void removeAssetFromlist(int idx) {
		String oldName = (String) assetsListModel.get(idx);
		AppContext.instance().removeBodyModel(oldName);

		assetsListModel.remove(idx);
		if (assetsListModel.size() > idx)
			assets_assetList.setSelectedIndex(idx);
		else if (assetsListModel.size() > 0)
			assets_assetList.setSelectedIndex(idx-1);
	}

	private void changeAssetName(int idx, String newName) {
		String oldName = (String) assetsListModel.get(idx);
		AppContext.instance().changeBodyModelPath(oldName, newName);
		assetsListModel.set(idx, newName);
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList assets_assetList;
    private javax.swing.JScrollPane assets_assetListScrollPane;
    private javax.swing.JButton assets_removeAssetBtn;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JButton export_saveBtn;
    private javax.swing.JButton init_addAssetsByFilesBtn;
    private javax.swing.JButton init_addAssetsByPackBtn;
    private javax.swing.JTextField init_assetsRootDirLbl;
    private javax.swing.JTextField init_outputFileLbl;
    private javax.swing.JButton init_setAssetsRootBtn;
    private javax.swing.JButton init_setOutputFileBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel renderPanel;
    private javax.swing.JButton shape_addbtn;
    private javax.swing.JButton shape_clearBtn;
    private javax.swing.JButton shape_computeBtn;
    private javax.swing.JCheckBox shape_drawPolysChk;
    private javax.swing.JCheckBox shape_drawShapeChk;
    private javax.swing.JButton shape_subBtn;
    private javax.swing.JPanel westPanel;
    // End of variables declaration//GEN-END:variables


	private final FileFilter imageUiFilter = new FileFilter() {
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

    private File promptAssetsRootDir() {
		File startupDir = assetsRootDir != null && assetsRootDir.exists()
			? assetsRootDir
			: new File(".");
		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
			return chooser.getSelectedFile();

		return null;
	}
	
    private String[] promptAssetsByFiles() {
		File startupDir = assetsRootDir != null && assetsRootDir.exists()
			? assetsRootDir
			: new File(".");
		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(imageUiFilter);

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			List<String> assetPaths = new ArrayList<String>();
			List<File> remainingFiles = new ArrayList<File>();

			Collections.addAll(remainingFiles, chooser.getSelectedFiles());
			int deepness = 0;
			int maxDeepness = 5;

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

	private File promptOutputFile() {
		JFileChooser chooser = new JFileChooser(".");
		chooser.setFileFilter(new FileFilter() {
			@Override public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return isSameString(getExtension(f), "txt");
			}

			@Override public String getDescription() {
				return "Shape file (.txt)";
			}
		});

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			int idx = selectedFile.getName().lastIndexOf(".");
			if (idx < 0 || !selectedFile.getName().substring(idx).equals(".txt"))
				selectedFile = new File(selectedFile.getPath() + ".txt");
			return selectedFile;
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
