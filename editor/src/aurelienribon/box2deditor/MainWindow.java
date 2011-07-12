package aurelienribon.box2deditor;

import aurelienribon.box2deditor.renderpanel.App;
import com.badlogic.gdx.backends.lwjgl.LwjglCanvas;
import com.badlogic.gdx.math.Vector2;
import java.awt.BorderLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

public class MainWindow extends javax.swing.JFrame {
	private final DefaultListModel assetsListModel;

	// -------------------------------------------------------------------------

    public MainWindow(final LwjglCanvas glCanvas) {
        initComponents();
		renderPanel.add(glCanvas.getCanvas(), BorderLayout.CENTER);

		assetsListModel = new DefaultListModel();
		assets_assetList.setModel(assetsListModel);

		addComponentListener(componentListener);
    }

	// -------------------------------------------------------------------------

	private final ComponentListener componentListener = new ComponentAdapter() {
		@Override
		public void componentShown(ComponentEvent e) {
			File outputFile = AppContext.instance().outputFile;
			if (outputFile != null)
				setOutputFile(outputFile, true);
		}
	};

	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renderPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        westPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        assets_assetListScrollPane = new javax.swing.JScrollPane();
        assets_assetList = new javax.swing.JList();
        assets_removeAssetBtn = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        init_addAssetsByFilesBtn = new javax.swing.JButton();
        init_setOutputFileBtn = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        init_outputFileLbl = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        export_saveBtn = new javax.swing.JButton();
        eastPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        shape_clearBtn = new javax.swing.JButton();
        shape_addbtn = new javax.swing.JButton();
        shape_subBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        shape_drawShapeChk = new javax.swing.JCheckBox();
        shape_drawPolysChk = new javax.swing.JCheckBox();
        shape_drawAssetChk = new javax.swing.JCheckBox();
        shape_drawAssetOpacity50Chk = new javax.swing.JCheckBox();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        renderPanel.setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));

        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_ball.png"))); // NOI18N
        jLabel2.setText("<html>\nOnce defined, you can directly test your shape in a physics environment.\nFor this purpose, hold \"shift\" + \"left click\" and drag the mouse to throw some dynamic objects to the shape.");
        jLabel2.setIconTextGap(10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap())
        );

        renderPanel.add(jPanel1, java.awt.BorderLayout.SOUTH);

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
        assets_assetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                assets_assetListValueChanged(evt);
            }
        });
        assets_assetListScrollPane.setViewportView(assets_assetList);

        assets_removeAssetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_delete.png"))); // NOI18N
        assets_removeAssetBtn.setText("Remove selected asset(s)");
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
                .addComponent(assets_assetListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assets_removeAssetBtn)
                .addContainerGap())
        );

        init_addAssetsByFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_add.png"))); // NOI18N
        init_addAssetsByFilesBtn.setText("Add assets by image files or dirs");
        init_addAssetsByFilesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_addAssetsByFilesBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        init_addAssetsByFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_addAssetsByFilesBtnActionPerformed(evt);
            }
        });

        init_setOutputFileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_gear.png"))); // NOI18N
        init_setOutputFileBtn.setText("Set / load output file");
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

        init_outputFileLbl.setEditable(false);
        init_outputFileLbl.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        init_outputFileLbl.setText("<no file specified>");

        jSeparator3.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(init_addAssetsByFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(init_setOutputFileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(init_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(init_setOutputFileBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_outputFileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_addAssetsByFilesBtn)
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
        shape_addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_addbtnActionPerformed(evt);
            }
        });

        shape_subBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/ic_remove.png"))); // NOI18N
        shape_subBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        shape_subBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        shape_subBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_subBtnActionPerformed(evt);
            }
        });

        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("<html>\n1 &bull; Set the output file<br/>\n2 &bull; Add some assets to the list<br/>\n3 &bull; Select an asset and define its collision body by clicking on the asset area.<br/><br/>\n\n<i>Note: You *have* to close the shape by clicking on the first point for the result to be saved.</i><br/><br/>\n\nZoom with mouse wheel, pan by holding the left mouse button.");

        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shape");
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

        shape_drawAssetChk.setSelected(true);
        shape_drawAssetChk.setText("Draw asset");
        shape_drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetChkActionPerformed(evt);
            }
        });

        shape_drawAssetOpacity50Chk.setText("...with opacity at 50%");
        shape_drawAssetOpacity50Chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetOpacity50ChkActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));

        jSeparator2.setForeground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_addbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_subBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(shape_drawAssetOpacity50Chk))
                    .addComponent(shape_drawAssetChk)
                    .addComponent(shape_drawShapeChk)
                    .addComponent(shape_drawPolysChk))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shape_clearBtn)
                        .addComponent(shape_addbtn))
                    .addComponent(shape_subBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawAssetChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawAssetOpacity50Chk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawShapeChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawPolysChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/box2deditor/gfx/logo.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
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
		if (AppContext.instance().outputFile == null) {
			JOptionPane.showMessageDialog(this, "Output file has not been set yet.");
			return;
		}
		
		String[] assets = promptAssetsByFiles();
		if (assets != null)
			for (String asset : assets)
				addAsset(asset, true);
	}//GEN-LAST:event_init_addAssetsByFilesBtnActionPerformed

	private void init_setOutputFileBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_init_setOutputFileBtnActionPerformed
		File file = promptOutputFile();
		if (file != null)
			setOutputFile(file, false);
	}//GEN-LAST:event_init_setOutputFileBtnActionPerformed

	private void export_saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_export_saveBtnActionPerformed
		File outputFile = AppContext.instance().outputFile;
		if (outputFile == null) {
			JOptionPane.showMessageDialog(this, "Output file has not been set yet.");
			return;
		}

		try {			
			AppContext.instance().exportToFile();
			JOptionPane.showMessageDialog(this, "Save successfully done !");

			int idx = assets_assetList.getSelectedIndex();
			loadAssets();
			assets_assetList.setSelectedIndex(idx);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Something went wrong while writing the file, sorry :/"
				+ "\n(nah, don't expect more details)");
		}
	}//GEN-LAST:event_export_saveBtnActionPerformed

	private void assets_assetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_assets_assetListValueChanged
		AppContext.instance().clearTempObjects();
		AppContext.instance().setCurrentName(null);
		App.instance().clearAsset();

		if (assets_assetList.isSelectionEmpty())
			return;

		int idx = assets_assetList.getSelectedIndex();
		if (isAssetValid(idx)) {
			String name = (String) assetsListModel.get(idx);
			String path = AppContext.instance().getFullPath(name);

			Vector2 size = App.instance().setAssetByFile(path);
			AppContext.instance().setCurrentSize(size);
			AppContext.instance().setCurrentName(name);
			AppContext.instance().loadCurrentModel();
		}
	}//GEN-LAST:event_assets_assetListValueChanged

	private void assets_removeAssetBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assets_removeAssetBtnActionPerformed
		if (!assets_assetList.isSelectionEmpty()) {
			int[] idxs = assets_assetList.getSelectedIndices();
			Arrays.sort(idxs);
			for (int i=idxs.length-1; i>=0; i--)
				removeAsset(idxs[i]);
		}
	}//GEN-LAST:event_assets_removeAssetBtnActionPerformed

	private void shape_clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_clearBtnActionPerformed
		AppContext.instance().clearTempObjects();
		AppContext.instance().saveCurrentModel();
	}//GEN-LAST:event_shape_clearBtnActionPerformed

	private void shape_drawShapeChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawShapeChkActionPerformed
		AppContext.instance().isShapeDrawn = shape_drawShapeChk.isSelected();
	}//GEN-LAST:event_shape_drawShapeChkActionPerformed

	private void shape_drawPolysChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawPolysChkActionPerformed
		AppContext.instance().arePolyDrawn = shape_drawPolysChk.isSelected();
	}//GEN-LAST:event_shape_drawPolysChkActionPerformed

	private void shape_drawAssetChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawAssetChkActionPerformed
		AppContext.instance().isAssetDrawn = shape_drawAssetChk.isSelected();
	}//GEN-LAST:event_shape_drawAssetChkActionPerformed

	private void shape_drawAssetOpacity50ChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawAssetOpacity50ChkActionPerformed
		AppContext.instance().isAssetDrawnWithOpacity50 = shape_drawAssetOpacity50Chk.isSelected();
	}//GEN-LAST:event_shape_drawAssetOpacity50ChkActionPerformed

	private void shape_addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_addbtnActionPerformed
		AppContext.instance().insertPointBetweenSelected();
	}//GEN-LAST:event_shape_addbtnActionPerformed

	private void shape_subBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_subBtnActionPerformed
		if (!AppContext.instance().removeSelectedPoints())
			JOptionPane.showMessageDialog(this,
				"You have to leave at least 3 points in the shape.\n"
				+ "Use the 'clear points' button instead.");
	}//GEN-LAST:event_shape_subBtnActionPerformed

	// -------------------------------------------------------------------------

	private static final String UNKNOWN_PREFIX = "[NOT FOUND] ";

	private void setOutputFile(File file, boolean force) {
		File oldFile = AppContext.instance().outputFile;
		AppContext.instance().outputFile = file;
		init_outputFileLbl.setText(file.getPath());

		if (oldFile != null)
			updateAssets(oldFile.getParent(), file.getParent());

		if (file.exists()) {
			if (!force) {
				int answer = JOptionPane.showConfirmDialog(this,
				"Selected file already exists. Do you want to load its content ?"
				+ "\nLoaded content will replace the current one.",
				"", JOptionPane.YES_NO_OPTION);

				if (answer == JOptionPane.YES_OPTION)
					loadAssets();

			} else {
				loadAssets();
			}
		}
	}

	private void addAsset(String name, boolean absolutePath) {
		String newName = absolutePath
			? AppContext.instance().getPathRelativeToOutputFile(name)
			: name;

		if (newName == null)
			return;

		String path = absolutePath
			? name
			: AppContext.instance().getFullPath(name);

		File file = new File(path);

		if (file.exists()) {
			if (!assetsListModel.contains(newName))
				assetsListModel.addElement(newName);
			AppContext.instance().addModel(newName);
		} else {
			if (!assetsListModel.contains(UNKNOWN_PREFIX + newName))
				assetsListModel.addElement(UNKNOWN_PREFIX + newName);
			AppContext.instance().addModel(newName);
		}
	}

	private void removeAsset(int idx) {
		String oldName = (String) assetsListModel.get(idx);
		AppContext.instance().removeModel(oldName);

		assetsListModel.remove(idx);
		if (assetsListModel.size() > idx)
			assets_assetList.setSelectedIndex(idx);
		else if (assetsListModel.size() > 0)
			assets_assetList.setSelectedIndex(idx-1);
	}

	private boolean isAssetValid(int idx) {
		String name = (String) assetsListModel.get(idx);
		if (name.startsWith(UNKNOWN_PREFIX))
			return false;

		String path = AppContext.instance().getFullPath(name);
		File file = new File(path);

		if (!file.exists()) {
			assetsListModel.set(idx, UNKNOWN_PREFIX + name);
			return false;
		}

		return true;
	}

	private void updateAssets(String oldRoot, String newRoot) {
		for (int i=assetsListModel.getSize()-1; i>=0; i--) {
			String oldName = (String) assetsListModel.get(i);

			if (oldName.startsWith(UNKNOWN_PREFIX)) {
				oldName = oldName.substring(UNKNOWN_PREFIX.length());
				File f = new File(newRoot, oldName);
				if (f.exists())
					assetsListModel.set(i, oldName);
			} else {
				String newName = new File(oldRoot, oldName).getPath();
				newName = AppContext.instance().getPathRelativeToOutputFile(newName);

				if (newName != null) {
					assetsListModel.set(i, newName);
					AppContext.instance().changeModelName(oldName, newName);
				} else {
					assetsListModel.set(i, UNKNOWN_PREFIX + oldName);
				}
			}
		}

		int idx = assets_assetList.getSelectedIndex();
		if (idx > -1) {
			assets_assetList.clearSelection();
			assets_assetList.setSelectedIndex(idx);
		}
	}

	private void loadAssets() {
		File outputFile = AppContext.instance().outputFile;
		if (outputFile == null || !outputFile.exists())
			return;

		try {
			AppContext.instance().importFromFile();
			assetsListModel.clear();
			for (String name : AppContext.instance().getNames())
				addAsset(name, false);

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this,
				"Something went wrong while reading the output "
				+ "file, sorry :/"
				+ "\n(nah, don't expect more details)");
		}
	}

	// -------------------------------------------------------------------------

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList assets_assetList;
    private javax.swing.JScrollPane assets_assetListScrollPane;
    private javax.swing.JButton assets_removeAssetBtn;
    private javax.swing.JPanel eastPanel;
    private javax.swing.JButton export_saveBtn;
    private javax.swing.JButton init_addAssetsByFilesBtn;
    private javax.swing.JTextField init_outputFileLbl;
    private javax.swing.JButton init_setOutputFileBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel renderPanel;
    private javax.swing.JButton shape_addbtn;
    private javax.swing.JButton shape_clearBtn;
    private javax.swing.JCheckBox shape_drawAssetChk;
    private javax.swing.JCheckBox shape_drawAssetOpacity50Chk;
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
	
    private String[] promptAssetsByFiles() {
		String startupPath = AppContext.instance().getRootDirectory();
		JFileChooser chooser = new JFileChooser(startupPath);
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		chooser.setMultiSelectionEnabled(true);
		chooser.setFileFilter(imageUiFilter);

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
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

	private File promptOutputFile() {
		File outputFile = AppContext.instance().outputFile;
		File startupDir = outputFile != null ? outputFile.getParentFile() : new File(".");
		if (!startupDir.isDirectory())
			startupDir = new File(".");

		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
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
