package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.utils.notifications.ChangeListener;
import aurelienribon.utils.ui.SwingHelper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class ProjectPanel extends javax.swing.JPanel {
    public ProjectPanel() {
        initComponents();

		newBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {newProject();}});
		loadBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {loadProject();}});
		saveBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {saveProject();}});

		Ctx.io.addChangeListener(new ChangeListener() {
			@Override public void propertyChanged(Object source, String propertyName) {
				if (Ctx.io.getProjectFile() != null) {
					pathLabel.setText(Ctx.io.getProjectFile().getPath());
				} else {
					pathLabel.setText("");
				}
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

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        newBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();
        saveBtn = new javax.swing.JButton();
        pathLabel = new javax.swing.JTextField();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);

        newBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_new.png"))); // NOI18N
        newBtn.setText("New project");

        loadBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_open.png"))); // NOI18N
        loadBtn.setText("Load project");

        saveBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_save.png"))); // NOI18N
        saveBtn.setText("Save");

        pathLabel.setEditable(false);
        pathLabel.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        pathLabel.setText("<no file specified>");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pathLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveBtn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveBtn)
                    .addComponent(newBtn)
                    .addComponent(loadBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pathLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JTextField pathLabel;
    private javax.swing.JButton saveBtn;
    // End of variables declaration//GEN-END:variables

}
