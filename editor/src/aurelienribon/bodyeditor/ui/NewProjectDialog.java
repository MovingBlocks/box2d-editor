package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.ObjectsManager;
import aurelienribon.bodyeditor.IoManager;
import java.io.File;
import javax.swing.JFileChooser;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class NewProjectDialog extends javax.swing.JDialog {
    public NewProjectDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
		Theme.apply(getContentPane());

		if (IoManager.instance().getOutputFile() != null) {
			prjFileField.setText(IoManager.instance().getOutputFile().getPath());
			updateRadios(IoManager.instance().getOutputFile().getPath());
		}
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        formatButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        binaryFormatRadio = new javax.swing.JRadioButton();
        xmlFormatRadio = new javax.swing.JRadioButton();
        jsonFormatRadio = new javax.swing.JRadioButton();
        prjFileField = new javax.swing.JTextField();
        browseBtn = new javax.swing.JButton();
        clearAssetsChk = new javax.swing.JCheckBox();
        okBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor - New project");

        jPanel1.setBackground(Theme.MAIN_BACKGROUND);

        aurelienribon.utils.ui.GroupBorder groupBorder1 = new aurelienribon.utils.ui.GroupBorder();
        groupBorder1.setTitle("New Project");
        jPanel3.setBorder(groupBorder1);

        jLabel3.setForeground(Theme.MAIN_ALT_FOREGROUND);
        jLabel3.setText("<html>\nPlease select a project file.<br/>\nIf the file does not exist, it will be created, else, if will be overwritten.<br/><br/>\n\n<b>About formats</b><br/>\n- Binary format is the fastest to be loaded in your games (especially on embedded devices such as Android targets). However, it can't be edited easily by hand.<br/>\n- XML format is quite slow to be loaded, but being a text file, it can be easily manipulated in any text editor.<br/>\n- JSON format is much faster than XML to be loaded since it is more compact. And being text-based, it can still be edited in a text editor.<br/><br/>\n\n<b>About options</b><br/>\nIf you have already loaded some assets, they will be kept int he list by default. If you want to restart everything, check the \"clear assets\" box.");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jPanel4.setOpaque(false);

        jLabel2.setForeground(Theme.MAIN_ALT_FOREGROUND);
        jLabel2.setText("Project file:");

        formatButtonGroup.add(binaryFormatRadio);
        binaryFormatRadio.setForeground(Theme.MAIN_ALT_FOREGROUND);
        binaryFormatRadio.setSelected(true);
        binaryFormatRadio.setText("Binary format");
        binaryFormatRadio.setOpaque(false);
        binaryFormatRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                binaryFormatRadioActionPerformed(evt);
            }
        });

        formatButtonGroup.add(xmlFormatRadio);
        xmlFormatRadio.setForeground(Theme.MAIN_ALT_FOREGROUND);
        xmlFormatRadio.setText("XML format");
        xmlFormatRadio.setOpaque(false);
        xmlFormatRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xmlFormatRadioActionPerformed(evt);
            }
        });

        formatButtonGroup.add(jsonFormatRadio);
        jsonFormatRadio.setForeground(Theme.MAIN_ALT_FOREGROUND);
        jsonFormatRadio.setText("JSON format");
        jsonFormatRadio.setOpaque(false);
        jsonFormatRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jsonFormatRadioActionPerformed(evt);
            }
        });

        browseBtn.setText("...");
        browseBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        browseBtn.setOpaque(false);
        browseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseBtnActionPerformed(evt);
            }
        });

        clearAssetsChk.setForeground(Theme.MAIN_ALT_FOREGROUND);
        clearAssetsChk.setText("Clear all existing assets (if any)");
        clearAssetsChk.setOpaque(false);

        okBtn.setText("Ok");
        okBtn.setMargin(new java.awt.Insets(2, 5, 2, 5));
        okBtn.setOpaque(false);
        okBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(clearAssetsChk)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(binaryFormatRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xmlFormatRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jsonFormatRadio))
                            .addComponent(prjFileField, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseBtn))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(407, Short.MAX_VALUE)
                .addComponent(okBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(prjFileField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(browseBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(binaryFormatRadio)
                    .addComponent(xmlFormatRadio)
                    .addComponent(jsonFormatRadio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearAssetsChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okBtn))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

	private void browseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseBtnActionPerformed
		File outputFile = IoManager.instance().getOutputFile();
		File startupDir = outputFile != null ? outputFile.getParentFile() : new File(".");
		if (!startupDir.isDirectory())
			startupDir = new File(".");

		JFileChooser chooser = new JFileChooser(startupDir);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setMultiSelectionEnabled(false);
		chooser.setDialogTitle("Choose an existing file or create a new one");

		if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = chooser.getSelectedFile();
			prjFileField.setText(selectedFile.getPath());
			updateRadios(selectedFile.getPath());
		}
	}//GEN-LAST:event_browseBtnActionPerformed

	private void okBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okBtnActionPerformed
		String path = prjFileField.getText();
		if (!path.equals(""))
			IoManager.instance().setOutputFile(new File(path));
		if (clearAssetsChk.isSelected())
			ObjectsManager.instance().getBodiesList().clear();
		dispose();
	}//GEN-LAST:event_okBtnActionPerformed

	private void binaryFormatRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_binaryFormatRadioActionPerformed
		String path = prjFileField.getText();
		if (path.equals(""))
			return;
		
		String ext = FilenameUtils.getExtension(path);
		if (ext.equalsIgnoreCase("xml") || ext.equalsIgnoreCase("json")) {
			path = FilenameUtils.getFullPath(path) + FilenameUtils.getBaseName(path) + ".bin";
			prjFileField.setText(path);
		}
	}//GEN-LAST:event_binaryFormatRadioActionPerformed

	private void xmlFormatRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xmlFormatRadioActionPerformed
		String path = prjFileField.getText();
		if (path.equals(""))
			return;
		
		String ext = FilenameUtils.getExtension(path);
		if (!ext.equalsIgnoreCase("xml")) {
			path = FilenameUtils.getFullPath(path) + FilenameUtils.getBaseName(path) + ".xml";
			prjFileField.setText(path);
		}
	}//GEN-LAST:event_xmlFormatRadioActionPerformed

	private void jsonFormatRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jsonFormatRadioActionPerformed
		String path = prjFileField.getText();
		if (path.equals(""))
			return;

		String ext = FilenameUtils.getExtension(path);
		if (!ext.equalsIgnoreCase("json")) {
			path = FilenameUtils.getFullPath(path) + FilenameUtils.getBaseName(path) + ".json";
			prjFileField.setText(path);
		}
	}//GEN-LAST:event_jsonFormatRadioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton binaryFormatRadio;
    private javax.swing.JButton browseBtn;
    private javax.swing.JCheckBox clearAssetsChk;
    private javax.swing.ButtonGroup formatButtonGroup;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jsonFormatRadio;
    private javax.swing.JButton okBtn;
    private javax.swing.JTextField prjFileField;
    private javax.swing.JRadioButton xmlFormatRadio;
    // End of variables declaration//GEN-END:variables

	private void updateRadios(String path) {
		String ext = FilenameUtils.getExtension(path);
		if (ext.equalsIgnoreCase("xml")) {
			xmlFormatRadio.setSelected(true);
		} else if (ext.equalsIgnoreCase("json")) {
			jsonFormatRadio.setSelected(true);
		} else {
			binaryFormatRadio.setSelected(true);
		}
	}
}
