package aurelienribon.bodyeditor;

import aurelienribon.bodyeditor.earclipping.Clipper.Polygonizers;
import aurelienribon.bodyeditor.renderpanel.RenderPanel;
import aurelienribon.utils.ui.SwingHelper;
import com.badlogic.gdx.math.Vector2;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class MainWindow extends javax.swing.JFrame {
	private final DefaultListModel assetsListModel;

	// -------------------------------------------------------------------------

    public MainWindow(final Component canvas) {
        initComponents();

		renderPanel.add(canvas, BorderLayout.CENTER);
		assetsListModel = new DefaultListModel();
		assets_assetList.setModel(assetsListModel);

		addComponentListener(new ComponentAdapter() {
			@Override public void componentShown(ComponentEvent e) {
				File outputFile = AppContext.instance().outputFile;
				if (outputFile != null)
					setOutputFile(outputFile, true);
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

        shapeModeBtnGrp = new javax.swing.ButtonGroup();
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
        init_outputFileLbl = new javax.swing.JTextField();
        init_setOutputFileBtn = new javax.swing.JButton();
        export_saveBtn = new javax.swing.JButton();
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
        jPanel6 = new javax.swing.JPanel();
        assets_removeAssetBtn = new javax.swing.JButton();
        assets_assetListScrollPane = new javax.swing.JScrollPane();
        assets_assetList = new javax.swing.JList();
        init_addAssetsByFilesBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

        renderPanelWrapper.setBackground(Theme.MAIN_BACKGROUND);

        renderPanel.setBackground(Theme.MAIN_ALT_BACKGROUND);
        renderPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Theme.SEPARATOR));
        renderPanel.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(Theme.MAIN_ALT_BACKGROUND);

        tools_insertPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_add.png"))); // NOI18N
        tools_insertPointsBtn.setText("Insert point(s)");
        tools_insertPointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tools_insertPointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        tools_insertPointsBtn.setOpaque(false);
        tools_insertPointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tools_insertPointsBtnActionPerformed(evt);
            }
        });

        tools_removePointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_remove.png"))); // NOI18N
        tools_removePointsBtn.setText("Remove point(s)");
        tools_removePointsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tools_removePointsBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        tools_removePointsBtn.setOpaque(false);
        tools_removePointsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tools_removePointsBtnActionPerformed(evt);
            }
        });

        tools_clearPointsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_delete.png"))); // NOI18N
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
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/title.png"))); // NOI18N

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
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12));
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

        init_outputFileLbl.setBackground(Theme.TEXTAREA_BACKGROUND);
        init_outputFileLbl.setEditable(false);
        init_outputFileLbl.setForeground(Theme.TEXTAREA_FOREGROUND);
        init_outputFileLbl.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        init_outputFileLbl.setText("<no file specified>");

        init_setOutputFileBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_gear.png"))); // NOI18N
        init_setOutputFileBtn.setText("Set / load output file");
        init_setOutputFileBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_setOutputFileBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        init_setOutputFileBtn.setOpaque(false);
        init_setOutputFileBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_setOutputFileBtnActionPerformed(evt);
            }
        });

        export_saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_save.png"))); // NOI18N
        export_saveBtn.setText("Save");
        export_saveBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        export_saveBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        export_saveBtn.setOpaque(false);
        export_saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                export_saveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(init_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(init_setOutputFileBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(export_saveBtn)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(init_setOutputFileBtn)
                    .addComponent(export_saveBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(init_outputFileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        configPanel.add(jPanel12, java.awt.BorderLayout.CENTER);

        optionsPanel.setOpaque(false);
        optionsPanel.setLayout(new java.awt.BorderLayout());

        jPanel2.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jPanel1.setOpaque(false);

        shape_drawShapeChk.setForeground(Theme.MAIN_FOREGROUND);
        shape_drawShapeChk.setSelected(true);
        shape_drawShapeChk.setText("Draw shape");
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
        shape_drawGridChk.setSelected(true);
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

        jPanel6.setBackground(Theme.MAIN_ALT_BACKGROUND);

        assets_removeAssetBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_delete.png"))); // NOI18N
        assets_removeAssetBtn.setText("Remove selection");
        assets_removeAssetBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        assets_removeAssetBtn.setMargin(new java.awt.Insets(2, 3, 2, 2));
        assets_removeAssetBtn.setOpaque(false);
        assets_removeAssetBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assets_removeAssetBtnActionPerformed(evt);
            }
        });

        assets_assetList.setBackground(Theme.TEXTAREA_BACKGROUND);
        assets_assetList.setForeground(Theme.TEXTAREA_FOREGROUND);
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

        init_addAssetsByFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aurelienribon/bodyeditor/gfx/ic_add.png"))); // NOI18N
        init_addAssetsByFilesBtn.setText("Add images files/dirs");
        init_addAssetsByFilesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        init_addAssetsByFilesBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        init_addAssetsByFilesBtn.setOpaque(false);
        init_addAssetsByFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                init_addAssetsByFilesBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(assets_assetListScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(init_addAssetsByFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(assets_removeAssetBtn)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(init_addAssetsByFilesBtn)
                    .addComponent(assets_removeAssetBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(assets_assetListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
                .addContainerGap())
        );

        imgsPanel.add(jPanel6, java.awt.BorderLayout.CENTER);

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
		RenderPanel.instance().clearAsset();

		if (assets_assetList.isSelectionEmpty())
			return;

		int idx = assets_assetList.getSelectedIndex();
		if (isAssetValid(idx)) {
			String name = (String) assetsListModel.get(idx);
			String path = AppContext.instance().getFullPath(name);

			Vector2 size = RenderPanel.instance().setAsset(path);
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

	private void tools_insertPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_insertPointsBtnActionPerformed
		AppContext.instance().insertPointBetweenSelected();
	}//GEN-LAST:event_tools_insertPointsBtnActionPerformed

	private void tools_removePointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_removePointsBtnActionPerformed
		AppContext.instance().removeSelectedPoints();
	}//GEN-LAST:event_tools_removePointsBtnActionPerformed

	private void tools_clearPointsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tools_clearPointsBtnActionPerformed
		AppContext.instance().clearTempObjects();
	}//GEN-LAST:event_tools_clearPointsBtnActionPerformed

	private void shape_enableSnapToGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_enableSnapToGridChkActionPerformed
		AppContext.instance().isSnapToGridEnabled = shape_enableSnapToGridChk.isSelected();
	}//GEN-LAST:event_shape_enableSnapToGridChkActionPerformed

	private void shape_drawGridChkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_drawGridChkActionPerformed
		AppContext.instance().isGridShown = shape_drawGridChk.isSelected();
	}//GEN-LAST:event_shape_drawGridChkActionPerformed

	private void shape_grigGapSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_shape_grigGapSpinnerStateChanged
		AppContext.instance().gridGap = (Integer)shape_grigGapSpinner.getValue();
	}//GEN-LAST:event_shape_grigGapSpinnerStateChanged

	private void shape_polygonizerCboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shape_polygonizerCboxActionPerformed
		AppContext.instance().polygonizer = Polygonizers.valueOf((String) shape_polygonizerCbox.getSelectedItem());
		AppContext.instance().saveCurrentModel();
	}//GEN-LAST:event_shape_polygonizerCboxActionPerformed

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
			for (String name : AppContext.instance().getModelNames())
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
    private javax.swing.JPanel configPanel;
    private javax.swing.JButton export_saveBtn;
    private javax.swing.JPanel imgsPanel;
    private javax.swing.JButton init_addAssetsByFilesBtn;
    private javax.swing.JTextField init_outputFileLbl;
    private javax.swing.JButton init_setOutputFileBtn;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel logoHelpLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private javax.swing.JPanel optionsPanel;
    private javax.swing.JPanel renderPanel;
    private javax.swing.JPanel renderPanelWrapper;
    private javax.swing.ButtonGroup shapeModeBtnGrp;
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

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
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
