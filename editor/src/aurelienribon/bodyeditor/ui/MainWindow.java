package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.ui.components.ArStyle;
import aurelienribon.ui.components.PaintedPanel;
import aurelienribon.ui.components.TabPanel;
import aurelienribon.ui.css.Style;
import aurelienribon.ui.css.swing.SwingStyle;
import aurelienribon.utils.ui.SwingHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JPanel;
import res.Res;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class MainWindow extends javax.swing.JFrame {
    public MainWindow() {
		setContentPane(new PaintedPanel());
        initComponents();

		SwingStyle.init();
		ArStyle.init();
		Style.registerCssClasses(getContentPane(), ".rootPanel");
		Style.registerCssClasses(projectPanel, ".titledPanel", "#projectPanel");
		Style.registerCssClasses(optionsPanel, ".titledPanel", "#optionsPanel");
		Style.registerCssClasses(logoWebsiteLbl, ".brightlink");
		Style.registerCssClasses(logoHelpLbl, ".brightlink", ".bold");
		Style.apply(getContentPane(), new Style(Res.getUrl("css/style.css")));

		JPanel p2 = new JPanel(); p2.setBackground(Color.WHITE);

		objectsPanel.getModel().add(new RigidBodiesPanel(), "Rigid bodies", null, false);
		objectsPanel.getModel().add(p2, "Dynamic objects", null, false);
		objectsPanel.setHeaderLayout(TabPanel.LAYOUT_GRID);

		logoWebsiteLbl.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				SwingHelper.browse(MainWindow.this, "http://www.aurelienribon.com");
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override public void windowOpened(WindowEvent e) {
				Ctx.bodies.select(null);
				Ctx.objects.select(null);
			}
		});
    }

	public void setCanvas(Component canvas) {
		renderPanel.add(canvas, BorderLayout.CENTER);
		canvas.requestFocusInWindow();
	}

	// -------------------------------------------------------------------------
	// Generated Stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sidePanel = new javax.swing.JPanel();
        logoPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        logoWebsiteLbl = new javax.swing.JLabel();
        logoHelpLbl = new javax.swing.JLabel();
        projectPanel = new aurelienribon.bodyeditor.ui.ProjectPanel();
        objectsPanel = new aurelienribon.ui.components.TabPanel();
        renderPanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();
        rigidBodiesOptionsPanel1 = new aurelienribon.bodyeditor.ui.RigidBodiesOptionsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

        sidePanel.setOpaque(false);

        logoPanel.setOpaque(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/title.png"))); // NOI18N

        logoWebsiteLbl.setText("<html><p align=\"right\">2012 - Aurélien Ribon<br/>www.aurelienribon.com</p>");

        logoHelpLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_manual.png"))); // NOI18N
        logoHelpLbl.setText("Manual");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoHelpLbl)))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoHelpLbl))
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(objectsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(projectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(projectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(objectsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE))
        );

        renderPanel.setLayout(new java.awt.BorderLayout());

        optionsPanel.setLayout(new java.awt.CardLayout());
        optionsPanel.add(rigidBodiesOptionsPanel1, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(renderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(optionsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel logoHelpLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private aurelienribon.ui.components.TabPanel objectsPanel;
    private javax.swing.JPanel optionsPanel;
    private aurelienribon.bodyeditor.ui.ProjectPanel projectPanel;
    private javax.swing.JPanel renderPanel;
    private aurelienribon.bodyeditor.ui.RigidBodiesOptionsPanel rigidBodiesOptionsPanel1;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
