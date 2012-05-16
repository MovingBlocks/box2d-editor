package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.ui.components.PaintedPanel;
import aurelienribon.ui.css.Style;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import org.apache.commons.io.FilenameUtils;
import res.Res;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RepairImagePathsDialog extends javax.swing.JDialog {
	private String commonPath;

    public RepairImagePathsDialog(javax.swing.JFrame parent) {
        super(parent, true);

		setContentPane(new PaintedPanel());
        initComponents();

		Style.registerCssClasses(getContentPane(), ".rootPanel", ".configPanel");
		Style.apply(getContentPane(), new Style(Res.getUrl("css/style.css")));

		okBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				apply();
				dispose();
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		init();
    }

	private void init() {
		for (RigidBodyModel model : Ctx.bodies.getModels()) {
			String path = FilenameUtils.getFullPathNoEndSeparator(model.getImagePath());
			path = FilenameUtils.normalizeNoEndSeparator(path, true);

			if (commonPath == null) {
				commonPath = path;
			} else {
				String[] pathParts = path.split("/");
				String[] commonPathParts = commonPath.split("/");

				for (int i=0; i<pathParts.length && i<commonPathParts.length; i++) {
					if (!pathParts[i].equals(commonPathParts[i])) {
						commonPath = "";
						for (int ii=0; ii<i; ii++)
							commonPath = FilenameUtils.concat(commonPath, commonPathParts[i]);
						break;
					}
				}
			}
		}

		commonPathField.setText(commonPath);
		commonPathField.selectAll();
		commonPathField.requestFocusInWindow();
	}

	private void apply() {
		String newCommonpath = commonPathField.getText();

		for (RigidBodyModel model : Ctx.bodies.getModels()) {
			String path = FilenameUtils.getFullPath(model.getImagePath());
			String name = FilenameUtils.getName(model.getImagePath());
			path = newCommonpath + path.substring(commonPath.length());
			model.setImagePath(new File(path, name).getPath());
		}
	}

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paintedPanel1 = new aurelienribon.ui.components.PaintedPanel();
        jLabel1 = new javax.swing.JLabel();
        commonPathField = new javax.swing.JTextField();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Repair image paths");
        setResizable(false);

        jLabel1.setText("Images common path: ");

        commonPathField.setText("...");

        okBtn.setText("Ok");

        cancelBtn.setText("Cancel");

        javax.swing.GroupLayout paintedPanel1Layout = new javax.swing.GroupLayout(paintedPanel1);
        paintedPanel1.setLayout(paintedPanel1Layout);
        paintedPanel1Layout.setHorizontalGroup(
            paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paintedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(commonPathField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paintedPanel1Layout.createSequentialGroup()
                        .addGap(0, 178, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );

        paintedPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        paintedPanel1Layout.setVerticalGroup(
            paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(commonPathField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelBtn;
    private javax.swing.JTextField commonPathField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton okBtn;
    private aurelienribon.ui.components.PaintedPanel paintedPanel1;
    // End of variables declaration//GEN-END:variables

}
