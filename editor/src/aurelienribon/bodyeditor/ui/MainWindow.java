package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.AppManager;
import aurelienribon.bodyeditor.AssetsManager;
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

		renderPanel.add(canvas, BorderLayout.CENTER);
		AppManager.instance().setRenderCanvas(canvas);
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
        titlePanel1 = new aurelienribon.utils.ui.TitlePanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        cfg_outputFileLbl = new javax.swing.JTextField();
        cfg_newProjectBtn = new javax.swing.JButton();
        cfg_loadProjectBtn = new javax.swing.JButton();
        cfg_saveBtn = new javax.swing.JButton();
        optionsPanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
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
        titlePanel3 = new aurelienribon.utils.ui.TitlePanel();
        jLabel8 = new javax.swing.JLabel();
        imgsPanel = new javax.swing.JPanel();
        titlePanel2 = new aurelienribon.utils.ui.TitlePanel();
        jLabel7 = new javax.swing.JLabel();
        manageAssetsPanel = new aurelienribon.bodyeditor.ui.ManageAssetsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

        renderPanelWrapper.setBackground(Theme.MAIN_BACKGROUND);

        renderPanel.setBackground(Theme.MAIN_ALT_BACKGROUND);
        renderPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Theme.SEPARATOR));
        renderPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(Theme.MAIN_ALT_BACKGROUND);

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel2.setForeground(Theme.MAIN_ALT_FOREGROUND);
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
                .addContainerGap(249, Short.MAX_VALUE))
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE))
                .addContainerGap())
        );
        renderPanelWrapperLayout.setVerticalGroup(
            renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, renderPanelWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(renderPanelWrapper, java.awt.BorderLayout.CENTER);

        sidePanel.setBackground(Theme.MAIN_BACKGROUND);

        logoPanel.setBackground(Theme.MAIN_BACKGROUND);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/ui/gfx/title.png"))); // NOI18N

        logoWebsiteLbl.setForeground(Theme.MAIN_FOREGROUND);
        logoWebsiteLbl.setText("<html> <p align=\"right\"> 2011 - Aurélien Ribon<br/> <font color=\"#6eccff\">www.aurelienribon.com</font> </p>");

        logoHelpLbl.setFont(new java.awt.Font("Tahoma", 1, 11));
        logoHelpLbl.setForeground(Theme.MAIN_FOREGROUND);
        logoHelpLbl.setText("<html>\n<p align=\"right\">\n<font color=\"#6eccff\">Open help</font>\n</p>");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
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

        configPanel.setOpaque(false);
        configPanel.setLayout(new java.awt.BorderLayout());

        titlePanel1.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jLabel5.setBackground(Theme.HEADER_FOREGROUND);
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Configuration");
        jLabel5.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));

        javax.swing.GroupLayout titlePanel1Layout = new javax.swing.GroupLayout(titlePanel1);
        titlePanel1.setLayout(titlePanel1Layout);
        titlePanel1Layout.setHorizontalGroup(
            titlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        titlePanel1Layout.setVerticalGroup(
            titlePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5)
        );

        configPanel.add(titlePanel1, java.awt.BorderLayout.NORTH);

        jPanel12.setBackground(Theme.MAIN_ALT_BACKGROUND);

        cfg_outputFileLbl.setBackground(Theme.TEXTAREA_BACKGROUND);
        cfg_outputFileLbl.setEditable(false);
        cfg_outputFileLbl.setForeground(Theme.TEXTAREA_FOREGROUND);
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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cfg_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(cfg_newProjectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cfg_loadProjectBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cfg_saveBtn)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cfg_saveBtn)
                    .addComponent(cfg_newProjectBtn)
                    .addComponent(cfg_loadProjectBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cfg_outputFileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        configPanel.add(jPanel12, java.awt.BorderLayout.CENTER);

        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jPanel1.setOpaque(false);

        shape_drawShapeChk.setForeground(Theme.MAIN_FOREGROUND);
        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shapes");
        shape_drawShapeChk.setOpaque(false);
        shape_drawShapeChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawShapeChkActionPerformed(evt);
            }
        });

        shape_drawAssetOpacity50Chk.setForeground(Theme.MAIN_FOREGROUND);
        shape_drawAssetOpacity50Chk.setText("...with opacity at 50%");
        shape_drawAssetOpacity50Chk.setOpaque(false);
        shape_drawAssetOpacity50Chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetOpacity50ChkActionPerformed(evt);
            }
        });

        shape_drawAssetChk.setForeground(Theme.MAIN_FOREGROUND);
        shape_drawAssetChk.setSelected(true);
        shape_drawAssetChk.setText("Draw asset");
        shape_drawAssetChk.setOpaque(false);
        shape_drawAssetChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawAssetChkActionPerformed(evt);
            }
        });

        shape_drawPolysChk.setForeground(Theme.MAIN_FOREGROUND);
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
            .addComponent(shape_drawShapeChk)
            .addComponent(shape_drawPolysChk)
            .addComponent(shape_drawAssetChk)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(shape_drawAssetOpacity50Chk))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(shape_drawAssetChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawAssetOpacity50Chk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawShapeChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shape_drawPolysChk))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, Theme.SEPARATOR));
        jPanel4.setOpaque(false);

        jLabel6.setForeground(Theme.MAIN_FOREGROUND);
        jLabel6.setText("Polygonizer:");

        jLabel9.setForeground(Theme.MAIN_FOREGROUND);
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

        shape_drawGridChk.setForeground(Theme.MAIN_FOREGROUND);
        shape_drawGridChk.setText("Draw grid");
        shape_drawGridChk.setOpaque(false);
        shape_drawGridChk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shape_drawGridChkActionPerformed(evt);
            }
        });

        shape_enableSnapToGridChk.setForeground(Theme.MAIN_FOREGROUND);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_grigGapSpinner))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(shape_polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(shape_enableSnapToGridChk)
                    .addComponent(shape_drawGridChk)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
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
                    .addComponent(shape_polygonizerCbox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel1, jPanel4});

        optionsPanel.add(jPanel2, java.awt.BorderLayout.CENTER);

        titlePanel3.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jLabel8.setBackground(Theme.HEADER_FOREGROUND);
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Options");
        jLabel8.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));

        javax.swing.GroupLayout titlePanel3Layout = new javax.swing.GroupLayout(titlePanel3);
        titlePanel3.setLayout(titlePanel3Layout);
        titlePanel3Layout.setHorizontalGroup(
            titlePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        titlePanel3Layout.setVerticalGroup(
            titlePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8)
        );

        optionsPanel.add(titlePanel3, java.awt.BorderLayout.NORTH);

        imgsPanel.setOpaque(false);
        imgsPanel.setLayout(new java.awt.BorderLayout());

        titlePanel2.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jLabel7.setBackground(Theme.HEADER_FOREGROUND);
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Images");
        jLabel7.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 0, 0, 0));

        javax.swing.GroupLayout titlePanel2Layout = new javax.swing.GroupLayout(titlePanel2);
        titlePanel2.setLayout(titlePanel2Layout);
        titlePanel2Layout.setHorizontalGroup(
            titlePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(285, Short.MAX_VALUE))
        );
        titlePanel2Layout.setVerticalGroup(
            titlePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7)
        );

        imgsPanel.add(titlePanel2, java.awt.BorderLayout.NORTH);
        imgsPanel.add(manageAssetsPanel, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(imgsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(configPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(configPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imgsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
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
	}//GEN-LAST:event_tools_insertPointsBtnActionPerformed

	private void tools_removePointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_removePointsBtnActionPerformed
		AppManager.instance().removeSelectedPoints();
	}//GEN-LAST:event_tools_removePointsBtnActionPerformed

	private void tools_clearPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_clearPointsBtnActionPerformed
		AssetsManager.instance().getSelectedAsset().clear();
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
		AssetsManager.instance().getSelectedAsset().computePolygons();
		AppManager.instance().getRenderPanel().createBody();
	}//GEN-LAST:event_shape_polygonizerCboxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cfg_loadProjectBtn;
    private javax.swing.JButton cfg_newProjectBtn;
    private javax.swing.JTextField cfg_outputFileLbl;
    private javax.swing.JButton cfg_saveBtn;
    private javax.swing.JPanel configPanel;
    private javax.swing.JPanel imgsPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel logoHelpLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private aurelienribon.bodyeditor.ui.ManageAssetsPanel manageAssetsPanel;
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
    private aurelienribon.utils.ui.TitlePanel titlePanel1;
    private aurelienribon.utils.ui.TitlePanel titlePanel2;
    private aurelienribon.utils.ui.TitlePanel titlePanel3;
    private javax.swing.JButton tools_clearPointsBtn;
    private javax.swing.JButton tools_insertPointsBtn;
    private javax.swing.JButton tools_removePointsBtn;
    // End of variables declaration//GEN-END:variables
}
