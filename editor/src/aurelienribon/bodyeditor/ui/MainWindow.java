package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.IoManager;
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
				File outputFile = IoManager.instance().getProjectFile();
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
				if (IoManager.instance().getProjectFile() != null)
					cfg_outputFileLbl.setText(IoManager.instance().getProjectFile().getPath());
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
        manageAssetsPanel = new aurelienribon.bodyeditor.ui.ObjectsPanel();
        renderPanelWrapper = new javax.swing.JPanel();
        renderPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        rigidBodyOptionsPanel1 = new aurelienribon.bodyeditor.ui.RigidBodyOptionsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
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
                    .addComponent(cfg_outputFileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                    .addGroup(configPanelLayout.createSequentialGroup()
                        .addComponent(cfg_newProjectBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
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
        groupBorder2.setTitle("Bodies & Objects");
        manageAssetsPanel.setBorder(groupBorder2);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(manageAssetsPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
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
                .addComponent(manageAssetsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(sidePanel, java.awt.BorderLayout.WEST);

        renderPanelWrapper.setBackground(Theme.MAIN_BACKGROUND);

        renderPanel.setBackground(Theme.MAIN_ALT_BACKGROUND);
        renderPanel.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, Theme.SEPARATOR));
        renderPanel.setLayout(new java.awt.BorderLayout());

        bottomPanel.setOpaque(false);

        javax.swing.GroupLayout bottomPanelLayout = new javax.swing.GroupLayout(bottomPanel);
        bottomPanel.setLayout(bottomPanelLayout);
        bottomPanelLayout.setHorizontalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rigidBodyOptionsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
        );
        bottomPanelLayout.setVerticalGroup(
            bottomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rigidBodyOptionsPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout renderPanelWrapperLayout = new javax.swing.GroupLayout(renderPanelWrapper);
        renderPanelWrapper.setLayout(renderPanelWrapperLayout);
        renderPanelWrapperLayout.setHorizontalGroup(
            renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, renderPanelWrapperLayout.createSequentialGroup()
                .addGroup(renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                    .addComponent(bottomPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        renderPanelWrapperLayout.setVerticalGroup(
            renderPanelWrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, renderPanelWrapperLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bottomPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(renderPanelWrapper, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void cfg_newProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_newProjectBtnActionPerformed
		NewProjectDialog dialog = new NewProjectDialog(this, true);
		SwingHelper.centerInWindow(this, dialog);
		dialog.setVisible(true);
	}//GEN-LAST:event_cfg_newProjectBtnActionPerformed

	private void cfg_loadProjectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_loadProjectBtnActionPerformed
		File outputFile = IoManager.instance().getProjectFile();
		File startupDir = outputFile != null ? outputFile.getParentFile() : new File(".");
		if (!startupDir.isDirectory())
			startupDir = new File(".");

		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			IoManager.instance().setProjectFile(selectedFile);
			try {
				IoManager.instance().importFromOutputFile();
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(this, "Something went wrong while loading the selected file.");
			}
		}
	}//GEN-LAST:event_cfg_loadProjectBtnActionPerformed

	private void cfg_saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cfg_saveBtnActionPerformed
		File outputFile = IoManager.instance().getProjectFile();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JButton cfg_loadProjectBtn;
    private javax.swing.JButton cfg_newProjectBtn;
    private javax.swing.JTextField cfg_outputFileLbl;
    private javax.swing.JButton cfg_saveBtn;
    private javax.swing.JPanel configPanel;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel logoHelpLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private aurelienribon.bodyeditor.ui.ObjectsPanel manageAssetsPanel;
    private javax.swing.JPanel renderPanel;
    private javax.swing.JPanel renderPanelWrapper;
    private aurelienribon.bodyeditor.ui.RigidBodyOptionsPanel rigidBodyOptionsPanel1;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
