package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.bodyeditor.EarClippingManager.Polygonizers;
import aurelienribon.bodyeditor.OptionsManager;
import aurelienribon.utils.notifications.ChangeListener;
import aurelienribon.utils.ui.SwingHelper;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class MainWindow extends javax.swing.JFrame {
    public MainWindow(final Component canvas) {
        initComponents();
		Theme.apply(getContentPane());

		renderPanel.add(canvas, BorderLayout.CENTER);
		canvas.requestFocusInWindow();

		addComponentListener(new ComponentAdapter() {
			@Override public void componentShown(ComponentEvent e) {
				File outputFile = IoManager.instance().getOutputFile();
				if (outputFile != null) {
					try {
						IoManager.instance().importFromOutputFile();
					} catch (IOException ex) {
						JOptionPane.showMessageDialog(MainWindow.this,
							"Something went wrong while loading the selected file.");
					}
				}
			}
		});

		IoManager.instance().addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				cfg_outputFileLbl.setText("");
				if (IoManager.instance().getOutputFile() != null)
					cfg_outputFileLbl.setText(IoManager.instance().getOutputFile().getPath());
			}
		});

		logoWebsiteLbl.addMouseListener(cursorHandler);
		logoHelpLbl.addMouseListener(cursorHandler);

		logoWebsiteLbl.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				SwingHelper.browse(MainWindow.this, "http://www.aurelienribon.com");
			}
		});

		logoHelpLbl.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				HelpDialog dialog = new HelpDialog(MainWindow.this, true);
				SwingHelper.centerInWindow(MainWindow.this, dialog);
				dialog.setVisible(true);
			}
		});
    }

	private final MouseListener cursorHandler = new MouseAdapter() {
		@Override public void mouseEntered(MouseEvent e) {
			setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}

		@Override public void mouseExited(MouseEvent e) {
			setCursor(Cursor.getDefaultCursor());
		}
	};

	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        renderPanelWrapper = new javax.swing.JPanel();
        renderPanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        tools_insertPointsBtn = new javax.swing.JButton();
        tools_removePointsBtn = new javax.swing.JButton();
        tools_clearPointsBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        sidePanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        logoWebsiteLbl = new javax.swing.JLabel();
        logoHelpLbl = new javax.swing.JLabel();
        configPanel = new javax.swing.JPanel();
        cfg_outputFileLbl = new javax.swing.JTextField();
        cfg_newProjectBtn = new javax.swing.JButton();
        cfg_loadProjectBtn = new javax.swing.JButton();
        cfg_saveBtn = new javax.swing.JButton();
        optionsPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        shape_drawShapeChk = new javax.swing.JCheckBox();
        shape_drawAssetOpacity50Chk = new javax.swing.JCheckBox();
        shape_drawAssetChk = new javax.swing.JCheckBox();
        shape_drawPolysChk = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        shape_grigGapSpinner = new javax.swing.JSpinner();
        shape_polygonizerCbox = new javax.swing.JComboBox();
        shape_drawGridChk = new javax.swing.JCheckBox();
        shape_enableSnapToGridChk = new javax.swing.JCheckBox();
        manageAssetsPanel = new aurelienribon.bodyeditor.ui.ObjectsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

        renderPanelWrapper.setBackground(Theme.MAIN_BACKGROUND);

        renderPanel.setBackground(Theme.MAIN_ALT_BACKGROUND);
        renderPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Theme.SEPARATOR));
        renderPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(new aurelienribon.utils.ui.GroupBorder());

        tools_insertPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_add.png"))); // NOI18N
        tools_insertPointsBtn.setText("Insert point(s)");
        tools_insertPointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tools_insertPointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        tools_insertPointsBtn.setOpaque(false);
        tools_insertPointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tools_insertPointsBtnActionPerformed(evt);
            }
        });

        tools_removePointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_remove.png"))); // NOI18N
        tools_removePointsBtn.setText("Remove point(s)");
        tools_removePointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tools_removePointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        tools_removePointsBtn.setOpaque(false);
        tools_removePointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tools_removePointsBtnActionPerformed(evt);
            }
        });

        tools_clearPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_delete.png"))); // NOI18N
        tools_clearPointsBtn.setText("Clear all points");
        tools_clearPointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tools_clearPointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        tools_clearPointsBtn.setOpaque(false);
        tools_clearPointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tools_clearPointsBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Tools");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(tools_insertPointsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tools_removePointsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tools_clearPointsBtn)
                .addContainerGap(239, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tools_insertPointsBtn)
                    .addComponent(tools_removePointsBtn)
                    .addComponent(tools_clearPointsBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout renderPanelWrapperLayout = new javax.swing.GroupLayout(renderPanelWrapper);
        renderPanelWrapper.setLayout(renderPanelWrapperLayout);
        renderPanelWrapperLayout.setHorizontalGroup(
            renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, renderPanelWrapperLayout.createSequentialGroup()
                .addGroup(renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        renderPanelWrapperLayout.setVerticalGroup(
            renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, renderPanelWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(renderPanelWrapper, java.awt.BorderLayout.CENTER);

        sidePanel.setBackground(Theme.MAIN_BACKGROUND);

        logoPanel.setOpaque(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/title.png"))); // NOI18N

        logoWebsiteLbl.setForeground(Theme.MAIN_FOREGROUND);
        logoWebsiteLbl.setText("<html> <p align=\"right\"> 2011 - Aurelien Ribon<br/> <font color=\"#6eccff\">www.aurelienribon.com</font> </p>");

        logoHelpLbl.setFont(new java.awt.Font("Tahoma", 1, 11));
        logoHelpLbl.setForeground(Theme.MAIN_FOREGROUND);
        logoHelpLbl.setText("<html>\n<p align=\"right\">\n<font color=\"#6eccff\">Open help</font>\n</p>");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoHelpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logoPanelLayout.createSequentialGroup()
                        .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                        .addComponent(logoHelpLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        aurelienribon.utils.ui.GroupBorder groupBorder1 = new aurelienribon.utils.ui.GroupBorder();
        groupBorder1.setTitle("Configuration");
        configPanel.setBorder(groupBorder1);

        cfg_outputFileLbl.setEditable(false);
        cfg_outputFileLbl.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        cfg_outputFileLbl.setText("<no file specified>");

        cfg_newProjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_new.png"))); // NOI18N
        cfg_newProjectBtn.setText("New project");
        cfg_newProjectBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfg_newProjectBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        cfg_newProjectBtn.setOpaque(false);
        cfg_newProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cfg_newProjectBtnActionPerformed(evt);
            }
        });

        cfg_loadProjectBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_open.png"))); // NOI18N
        cfg_loadProjectBtn.setText("Load project");
        cfg_loadProjectBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfg_loadProjectBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        cfg_loadProjectBtn.setOpaque(false);
        cfg_loadProjectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cfg_loadProjectBtnActionPerformed(evt);
            }
        });

        cfg_saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/ic_save.png"))); // NOI18N
        cfg_saveBtn.setText("Save");
        cfg_saveBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cfg_saveBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        cfg_saveBtn.setOpaque(false);
        cfg_saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cfg_saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout configPanelLayout = new javax.swing.GroupLayout(configPanel);
        configPanel.setLayout(configPanelLayout);
        configPanelLayout.setHorizontalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cfg_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addGroup(configPanelLayout.createSequentialGroup()
                        .addComponent(cfg_newProjectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cfg_loadProjectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cfg_saveBtn)))
                .addContainerGap())
        );
        configPanelLayout.setVerticalGroup(
            configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cfg_saveBtn)
                    .addComponent(cfg_newProjectBtn)
                    .addComponent(cfg_loadProjectBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cfg_outputFileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        aurelienribon.utils.ui.GroupBorder groupBorder2 = new aurelienribon.utils.ui.GroupBorder();
        groupBorder2.setTitle("Options");
        optionsPanel.setBorder(groupBorder2);

        jPanel1.setOpaque(false);

        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shapes");
        shape_drawShapeChk.setOpaque(false);
        shape_drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawShapeChkActionPerformed(evt);
            }
        });

        shape_drawAssetOpacity50Chk.setText("...with opacity at 50%");
        shape_drawAssetOpacity50Chk.setOpaque(false);
        shape_drawAssetOpacity50Chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetOpacity50ChkActionPerformed(evt);
            }
        });

        shape_drawAssetChk.setSelected(true);
        shape_drawAssetChk.setText("Draw asset");
        shape_drawAssetChk.setOpaque(false);
        shape_drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetChkActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(shape_drawShapeChk)
                    .addComponent(shape_drawPolysChk)
                    .addComponent(shape_drawAssetChk)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(shape_drawAssetOpacity50Chk)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_drawAssetChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawAssetOpacity50Chk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawShapeChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawPolysChk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, Theme.SEPARATOR));
        jPanel4.setOpaque(false);

        jLabel6.setText("Polygonizer:");

        jLabel9.setText("Grid gap (pixels):");

        shape_grigGapSpinner.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(10), Integer.valueOf(1), null, Integer.valueOf(1)));
        shape_grigGapSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                shape_grigGapSpinnerStateChanged(evt);
            }
        });

        shape_polygonizerCbox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "BAYAZIT", "EWJORDAN" }));
        shape_polygonizerCbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_polygonizerCboxActionPerformed(evt);
            }
        });

        shape_drawGridChk.setText("Draw grid");
        shape_drawGridChk.setOpaque(false);
        shape_drawGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawGridChkActionPerformed(evt);
            }
        });

        shape_enableSnapToGridChk.setText("Enable snap-to-grid");
        shape_enableSnapToGridChk.setOpaque(false);
        shape_enableSnapToGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_enableSnapToGridChkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_grigGapSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_polygonizerCbox, 0, 90, Short.MAX_VALUE))
                    .addComponent(shape_enableSnapToGridChk)
                    .addComponent(shape_drawGridChk))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(shape_drawGridChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_enableSnapToGridChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shape_grigGapSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(shape_polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout optionsPanelLayout = new javax.swing.GroupLayout(optionsPanel);
        optionsPanel.setLayout(optionsPanelLayout);
        optionsPanelLayout.setHorizontalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(optionsPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        optionsPanelLayout.setVerticalGroup(
            optionsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        aurelienribon.utils.ui.GroupBorder groupBorder3 = new aurelienribon.utils.ui.GroupBorder();
        groupBorder3.setTitle("Images");
        manageAssetsPanel.setBorder(groupBorder3);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(manageAssetsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(manageAssetsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(sidePanel, java.awt.BorderLayout.WEST);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void cfg_newProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_newProjectBtnActionPerformed
		NewProjectDialog dialog = new NewProjectDialog(this, true);
		SwingHelper.centerInWindow(this, dialog);
		dialog.setVisible(true);
	}//GEN-LAST:event_cfg_newProjectBtnActionPerformed

	private void cfg_loadProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_loadProjectBtnActionPerformed
		File outputFile = IoManager.instance().getOutputFile();
		File startupDir = outputFile != null ? outputFile.getParentFile() : new File(".");
		if (!startupDir.isDirectory())
			startupDir = new File(".");

		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			IoManager.instance().setOutputFile(selectedFile);
			try {
				IoManager.instance().importFromOutputFile();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Something went wrong while loading the selected file.");
			}
		}
	}//GEN-LAST:event_cfg_loadProjectBtnActionPerformed

	private void cfg_saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_saveBtnActionPerformed
		File outputFile = IoManager.instance().getOutputFile();
		if (outputFile == null) {
			JOptionPane.showMessageDialog(this, "Output file has not been set yet.");
			return;
		}
		try {
			IoManager.instance().exportToOutputFile();
			JOptionPane.showMessageDialog(this, "Saving successfully done.");
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(this, "Something went wrong while saving.\n\n"
				+ ex.getMessage());
		}
	}//GEN-LAST:event_cfg_saveBtnActionPerformed

	private void shape_drawShapeChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawShapeChkActionPerformed
		OptionsManager.instance().areShapesDrawn = shape_drawShapeChk.isSelected();
	}//GEN-LAST:event_shape_drawShapeChkActionPerformed

	private void shape_drawPolysChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawPolysChkActionPerformed
		OptionsManager.instance().arePolyDrawn = shape_drawPolysChk.isSelected();
	}//GEN-LAST:event_shape_drawPolysChkActionPerformed

	private void shape_drawAssetChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawAssetChkActionPerformed
		OptionsManager.instance().isAssetDrawn = shape_drawAssetChk.isSelected();
	}//GEN-LAST:event_shape_drawAssetChkActionPerformed

	private void shape_drawAssetOpacity50ChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawAssetOpacity50ChkActionPerformed
		OptionsManager.instance().isAssetDrawnWithOpacity50 = shape_drawAssetOpacity50Chk.isSelected();
	}//GEN-LAST:event_shape_drawAssetOpacity50ChkActionPerformed

	private void tools_insertPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_insertPointsBtnActionPerformed
		AppManager.instance().insertPointBetweenSelected();
		ObjectsManager.instance().getSelectedBody().computePolygons();
	}//GEN-LAST:event_tools_insertPointsBtnActionPerformed

	private void tools_removePointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_removePointsBtnActionPerformed
		AppManager.instance().removeSelectedPoints();
		ObjectsManager.instance().getSelectedBody().computePolygons();
	}//GEN-LAST:event_tools_removePointsBtnActionPerformed

	private void tools_clearPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_clearPointsBtnActionPerformed
		ObjectsManager.instance().getSelectedBody().clear();
		AppManager.instance().getRenderPanel().createBody();
	}//GEN-LAST:event_tools_clearPointsBtnActionPerformed

	private void shape_enableSnapToGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_enableSnapToGridChkActionPerformed
		OptionsManager.instance().isSnapToGridEnabled = shape_enableSnapToGridChk.isSelected();
	}//GEN-LAST:event_shape_enableSnapToGridChkActionPerformed

	private void shape_drawGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawGridChkActionPerformed
		OptionsManager.instance().isGridShown = shape_drawGridChk.isSelected();
	}//GEN-LAST:event_shape_drawGridChkActionPerformed

	private void shape_grigGapSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_shape_grigGapSpinnerStateChanged
		OptionsManager.instance().gridGap = (Integer)shape_grigGapSpinner.getValue();
	}//GEN-LAST:event_shape_grigGapSpinnerStateChanged

	private void shape_polygonizerCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_polygonizerCboxActionPerformed
		OptionsManager.instance().polygonizer = Polygonizers.valueOf((String) shape_polygonizerCbox.getSelectedItem());
		ObjectsManager.instance().getSelectedBody().computePolygons();
		AppManager.instance().getRenderPanel().createBody();
	}//GEN-LAST:event_shape_polygonizerCboxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cfg_loadProjectBtn;
    private javax.swing.JButton cfg_newProjectBtn;
    private javax.swing.JTextField cfg_outputFileLbl;
    private javax.swing.JButton cfg_saveBtn;
    private javax.swing.JPanel configPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logoHelpLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private aurelienribon.bodyeditor.ui.ObjectsPanel manageAssetsPanel;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JPanel renderPanel;
    private javax.swing.JPanel renderPanelWrapper;
    private javax.swing.JCheckBox shape_drawAssetChk;
    private javax.swing.JCheckBox shape_drawAssetOpacity50Chk;
    private javax.swing.JCheckBox shape_drawGridChk;
    private javax.swing.JCheckBox shape_drawPolysChk;
    private javax.swing.JCheckBox shape_drawShapeChk;
    private javax.swing.JCheckBox shape_enableSnapToGridChk;
    private javax.swing.JSpinner shape_grigGapSpinner;
    private javax.swing.JComboBox shape_polygonizerCbox;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JButton tools_clearPointsBtn;
    private javax.swing.JButton tools_insertPointsBtn;
    private javax.swing.JButton tools_removePointsBtn;
    // End of variables declaration//GEN-END:variables
}
