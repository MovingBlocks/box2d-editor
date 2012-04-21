package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.IoManager;
import aurelienribon.ui.css.Style;
import aurelienribon.utils.notifications.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ProjectPanel extends javax.swing.JPanel {
    public ProjectPanel() {
        initComponents();

		Style.registerCssClasses(headerPanel, ".headerPanel");
		Style.registerCssClasses(saveBtn, ".bold");

		newBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {newProject();}});
		loadBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {loadProject();}});
		saveBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {saveProject();}});
		imgsBrowseBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {browseImgagesDir();}});

		saveBtn.setEnabled(false);
		imgsBrowseBtn.setEnabled(false);

		Ctx.io.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (propertyName.equals(IoManager.PROP_PROJECTFILE)) projectFileChanged();
				if (propertyName.equals(IoManager.PROP_IMAGESDIR)) imagesDirChanged();
			}
		});
    }

	private void newProject() {
		File dir = Ctx.io.getProjectFile();
		dir = dir != null ? dir.getParentFile() : new File(".");
		dir = dir != null ? dir : new File(".");

		JFileChooser chooser = new JFileChooser(dir);
		chooser.setDialogTitle("Select the new project file");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showSaveDialog(Ctx.window) == JFileChooser.APPROVE_OPTION) {
			Ctx.io.setProjectFile(chooser.getSelectedFile());
		}
	}

	private void loadProject() {
		File dir = Ctx.io.getProjectFile();
		dir = dir != null ? dir.getParentFile() : new File(".");
		dir = dir != null ? dir : new File(".");

		JFileChooser chooser = new JFileChooser(dir);
		chooser.setDialogTitle("Select the project to load");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showOpenDialog(Ctx.window) == JFileChooser.APPROVE_OPTION) {
			Ctx.io.setProjectFile(chooser.getSelectedFile());

			try {
				Ctx.io.importFromFile();
			} catch (IOException ex) {
				String msg = "Something went wrong while loading the selected file.\n\n"
					+ ex.getClass().getSimpleName() + " - " + ex.getMessage();
				JOptionPane.showMessageDialog(this, msg);
			} catch (JSONException ex) {
				String msg = "The selected file is either not compatible or corrupted.\nSorry.";
				JOptionPane.showMessageDialog(this, msg);
			}
		}
	}

	private void saveProject() {
		File file = Ctx.io.getProjectFile();

		if (file == null) {
			String msg = "Please create a new project first.";
			JOptionPane.showMessageDialog(Ctx.window, msg);
			return;
		}

		try {
			Ctx.io.exportToFile();
			JOptionPane.showMessageDialog(Ctx.window, "Save successfully done.");

		} catch (IOException ex) {
			String msg = "Something went wrong while saving.\n\n" + ex.getClass().getSimpleName() + " - " + ex.getMessage();
			JOptionPane.showMessageDialog(Ctx.window, msg);
		} catch (JSONException ex) {
			String msg = "Something went wrong while saving.\n\n" + ex.getClass().getSimpleName() + " - " + ex.getMessage();
			JOptionPane.showMessageDialog(Ctx.window, msg);
		}
	}

	private void browseImgagesDir() {
		JFileChooser chooser = new JFileChooser(Ctx.io.getImagesDir());
		chooser.setDialogTitle("Select the root directory of images");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showSaveDialog(Ctx.window) == JFileChooser.APPROVE_OPTION) {
			Ctx.io.setImagesDir(chooser.getSelectedFile());
		}
	}

	private void projectFileChanged() {
		if (Ctx.io.getProjectFile() != null) {
			saveBtn.setEnabled(true);
			prjPathField.setText(Ctx.io.getProjectFile().getPath());
		} else {
			saveBtn.setEnabled(false);
			prjPathField.setText("<create or load a project>");
		}

		Ctx.bodies.getModels().clear();
	}

	private void imagesDirChanged() {
		if (Ctx.io.getImagesDir() != null) {
			imgsBrowseBtn.setEnabled(true);
			imgsPathField.setText(Ctx.io.getImagesDir().getPath());
		} else {
			imgsBrowseBtn.setEnabled(false);
			imgsPathField.setText("");
		}
	}

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new aurelienribon.ui.components.PaintedPanel();
        jToolBar1 = new javax.swing.JToolBar();
        newBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        saveBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        prjPathField = new javax.swing.JTextField();
        imgsPathField = new javax.swing.JTextField();
        imgsBrowseBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        newBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_new.png"))); // NOI18N
        newBtn.setText("New project");
        newBtn.setFocusable(false);
        newBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        newBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(newBtn);

        loadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_open.png"))); // NOI18N
        loadBtn.setText("Load project");
        loadBtn.setFocusable(false);
        loadBtn.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        loadBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(loadBtn);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_save.png"))); // NOI18N
        saveBtn.setText("Save");
        jToolBar2.add(saveBtn);

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(headerPanel, java.awt.BorderLayout.NORTH);

        jPanel1.setOpaque(false);

        prjPathField.setColumns(20);
        prjPathField.setEditable(false);
        prjPathField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        prjPathField.setText("<create or load a project>");

        imgsPathField.setColumns(20);
        imgsPathField.setEditable(false);
        imgsPathField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        imgsBrowseBtn.setText("...");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Project file: ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Images root: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imgsPathField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imgsBrowseBtn))
                    .addComponent(prjPathField, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prjPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgsPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(imgsBrowseBtn)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {imgsBrowseBtn, imgsPathField, jLabel1, jLabel2, prjPathField});

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private aurelienribon.ui.components.PaintedPanel headerPanel;
    private javax.swing.JButton imgsBrowseBtn;
    private javax.swing.JTextField imgsPathField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JTextField prjPathField;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables

}
