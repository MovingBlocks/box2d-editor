package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.models.RigidBodyModel;
import aurelienribon.ui.components.PaintedPanel;
import aurelienribon.ui.css.Style;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.java.balloontip.BalloonTip;
import res.Res;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class RigidBodiesCreationDialog extends javax.swing.JDialog {
	private BalloonTip tip;

    public RigidBodiesCreationDialog(java.awt.Frame parent) {
        super(parent, true);

		setContentPane(new PaintedPanel());
        initComponents();

		Style.registerCssClasses(getContentPane(), ".rootPanel", ".configPanel");
		Style.registerCssClasses(orLbl1, ".bigLabel");
		Style.registerCssClasses(orLbl2, ".bigLabel");
		Style.apply(getContentPane(), new Style(Res.getUrl("css/style.css")));

		b1CreateBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {createEmpty();}});
		b2CreateBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {createFromImage();}});
		b3CreateBtn.addActionListener(new ActionListener() {@Override public void actionPerformed(ActionEvent e) {createFromImages();}});

		b1NameField.addMouseListener(selectOnFocusMouseListener);
		b1NameField.addKeyListener(updateOnTypeKeyListener);
		b1NameField.addFocusListener(updateOnSelectFocusListener);
		b2NameField.addMouseListener(selectOnFocusMouseListener);
		b2NameField.addKeyListener(updateOnTypeKeyListener);
		b2NameField.addFocusListener(updateOnSelectFocusListener);

		b2NameField.requestFocusInWindow();
		b2NameField.selectAll();

		update();
    }

	private void update() {
		if (tip != null) {tip.closeBalloon(); tip = null;}

		if (b1NameField.isFocusOwner()) {
			String name = b1NameField.getText().trim();
			b2NameField.setText(name);
			b1CreateBtn.setEnabled(!name.equals("") && Ctx.bodies.getModel(name) == null);
			b2CreateBtn.setEnabled(!name.equals("") && Ctx.bodies.getModel(name) == null);

			if (name.equals("")) {
				tip = new BalloonTip(b1NameField, "You need to set a name");
				tip.setCloseButton(null);
				tip.setVisible(true);
			} else if (Ctx.bodies.getModel(name) != null) {
				tip = new BalloonTip(b1NameField, "Name already in use");
				tip.setCloseButton(null);
				tip.setVisible(true);
			}

		} else if (b2NameField.isFocusOwner()) {
			String name = b2NameField.getText().trim();
			b1NameField.setText(name);
			b1CreateBtn.setEnabled(!name.equals("") && Ctx.bodies.getModel(name) == null);
			b2CreateBtn.setEnabled(!name.equals("") && Ctx.bodies.getModel(name) == null);

			if (name.equals("")) {
				tip = new BalloonTip(b2NameField, "You need to set a name");
				tip.setCloseButton(null);
				tip.setVisible(true);
			} else if (Ctx.bodies.getModel(name) != null) {
				tip = new BalloonTip(b2NameField, "Name already in use");
				tip.setCloseButton(null);
				tip.setVisible(true);
			}
		}
	}

	private void createEmpty() {
		RigidBodyModel model = new RigidBodyModel();
		model.setName(b1NameField.getText());
		Ctx.bodies.getModels().add(model);
		Ctx.bodies.select(model);
		dispose();
	}

	private void createFromImage() {
		JFileChooser chooser = new JFileChooser(Ctx.io.getProjectDir());
		chooser.setDialogTitle("Select the image associated to the new model");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg"));
		chooser.setMultiSelectionEnabled(false);

		if (chooser.showSaveDialog(Ctx.window) == JFileChooser.APPROVE_OPTION) {
			RigidBodyModel model = new RigidBodyModel();
			model.setName(b2NameField.getText());
			model.setImagePath(Ctx.io.buildImagePath(chooser.getSelectedFile()));
			Ctx.bodies.getModels().add(model);
			Ctx.bodies.select(model);
			dispose();
		}
	}

	private void createFromImages() {
		JFileChooser chooser = new JFileChooser(Ctx.io.getProjectDir());
		chooser.setDialogTitle("Select the images for the new models");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setFileFilter(new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg"));
		chooser.setMultiSelectionEnabled(true);

		if (chooser.showSaveDialog(Ctx.window) == JFileChooser.APPROVE_OPTION) {
			for (File file : chooser.getSelectedFiles()) {
				String name = file.getName();
				String origName = name;
				int i = 0;
				while(Ctx.bodies.getModel(name) != null) {
					name = origName + "-" + (++i);
				}

				RigidBodyModel model = new RigidBodyModel();
				model.setName(name);
				model.setImagePath(Ctx.io.buildImagePath(file));
				Ctx.bodies.getModels().add(model);
				Ctx.bodies.select(model);
			}

			dispose();
		}
	}

	private final MouseListener selectOnFocusMouseListener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
			JTextField field = (JTextField) e.getSource();
			if (!field.isFocusOwner()) field.selectAll();
		}
	};

	private final KeyListener updateOnTypeKeyListener = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent e) {
			update();
		}
	};

	private final FocusListener updateOnSelectFocusListener = new FocusAdapter() {
		@Override
		public void focusGained(FocusEvent e) {
			update();
		}
	};

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        paintedPanel3 = new aurelienribon.ui.components.PaintedPanel();
        b2NameField = new javax.swing.JTextField();
        b2CreateBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        orLbl1 = new javax.swing.JLabel();
        paintedPanel1 = new aurelienribon.ui.components.PaintedPanel();
        b1NameField = new javax.swing.JTextField();
        b1CreateBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        orLbl2 = new javax.swing.JLabel();
        paintedPanel2 = new aurelienribon.ui.components.PaintedPanel();
        b3CreateBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New rigid body");
        setResizable(false);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/newBody.png"))); // NOI18N
        getContentPane().add(jLabel4, java.awt.BorderLayout.WEST);

        jPanel1.setOpaque(false);

        b2NameField.setText("Name");

        b2CreateBtn.setText("Create body from image");

        jLabel3.setText("<html> Creates a new body associated to an image.<br/>You are still able to change the image later.</html>");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout paintedPanel3Layout = new javax.swing.GroupLayout(paintedPanel3);
        paintedPanel3.setLayout(paintedPanel3Layout);
        paintedPanel3Layout.setHorizontalGroup(
            paintedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paintedPanel3Layout.createSequentialGroup()
                        .addComponent(b2NameField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b2CreateBtn))
                    .addComponent(jLabel3))
                .addContainerGap())
        );
        paintedPanel3Layout.setVerticalGroup(
            paintedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(b2NameField, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(b2CreateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        orLbl1.setText("- OR -");

        b1NameField.setText("Name");

        b1CreateBtn.setText("Create new empty body");

        jLabel1.setText("<html> Creates a new empty body with no associated image.<br/>You are still able to associate an image with it later.</html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout paintedPanel1Layout = new javax.swing.GroupLayout(paintedPanel1);
        paintedPanel1.setLayout(paintedPanel1Layout);
        paintedPanel1Layout.setHorizontalGroup(
            paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paintedPanel1Layout.createSequentialGroup()
                        .addComponent(b1NameField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b1CreateBtn))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        paintedPanel1Layout.setVerticalGroup(
            paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b1NameField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1CreateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        paintedPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {b1CreateBtn, b1NameField});

        orLbl2.setText("- OR -");

        b3CreateBtn.setText("Create bodies from images");

        jLabel2.setText("<html> Creates multiple bodies associated to selected images.<br/>You are still able to change the images later.</html>");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout paintedPanel2Layout = new javax.swing.GroupLayout(paintedPanel2);
        paintedPanel2.setLayout(paintedPanel2Layout);
        paintedPanel2Layout.setHorizontalGroup(
            paintedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                    .addComponent(b3CreateBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        paintedPanel2Layout.setVerticalGroup(
            paintedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(b3CreateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintedPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(paintedPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orLbl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orLbl2, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                    .addComponent(paintedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintedPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLbl1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(paintedPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1CreateBtn;
    private javax.swing.JTextField b1NameField;
    private javax.swing.JButton b2CreateBtn;
    private javax.swing.JTextField b2NameField;
    private javax.swing.JButton b3CreateBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel orLbl1;
    private javax.swing.JLabel orLbl2;
    private aurelienribon.ui.components.PaintedPanel paintedPanel1;
    private aurelienribon.ui.components.PaintedPanel paintedPanel2;
    private aurelienribon.ui.components.PaintedPanel paintedPanel3;
    // End of variables declaration//GEN-END:variables

}
