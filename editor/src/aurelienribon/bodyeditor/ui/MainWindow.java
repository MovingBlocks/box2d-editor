package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.ui.components.ArStyle;
import aurelienribon.ui.components.PaintedPanel;
import aurelienribon.ui.components.TabPanel;
import aurelienribon.ui.css.Style;
import aurelienribon.ui.css.swing.SwingStyle;
import aurelienribon.utils.io.HttpUtils;
import aurelienribon.utils.ui.SwingHelper;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import javax.swing.Timer;
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
		Style.registerCssClasses(logoManualLbl, ".brightlink", ".bold");
		Style.registerCssClasses(versionPanel, ".versionPanel");
		Style.registerCssClasses(versionLabel, ".versionLabel");
		Style.apply(getContentPane(), new Style(Res.getUrl("css/style.css")));

		objectsPanel.getModel().add(new RigidBodiesPanel(), "Rigid bodies", null, false);
		objectsPanel.getModel().add(new DynamicObjectsPanel(), "Dynamic objects", null, false);
		objectsPanel.setHeaderLayout(TabPanel.LAYOUT_GRID);

		logoWebsiteLbl.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				SwingHelper.browse(MainWindow.this, "http://www.aurelienribon.com");
			}
		});

		logoManualLbl.addMouseListener(new MouseAdapter() {
			@Override public void mouseClicked(MouseEvent e) {
				SwingHelper.browse(MainWindow.this, "http://www.aurelienribon.com/blog/projects/physics-body-editor/");
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override public void windowOpened(WindowEvent e) {
				Ctx.bodies.select(null);
				Ctx.objects.select(null);
			}
		});

		checkUpdates();
    }

	public void setCanvas(Component canvas) {
		renderPanel.add(canvas, BorderLayout.CENTER);
		canvas.requestFocusInWindow();
	}

	private void checkUpdates() {
		final String version = "2.9.2";
		versionLabel.setText("v" + version + " (checking for updates)");
		versionLabel.setIcon(Res.getImage("gfx/ic_loading.gif"));

		URL tmpUrl;
		try {tmpUrl = new URL("http://www.aurelienribon.com/physics-body-editor/versions.txt");}
		catch (MalformedURLException ex) {throw new RuntimeException(ex);}

		final URL url = tmpUrl;
		final ByteArrayOutputStream stream = new ByteArrayOutputStream();

		final HttpUtils.Callback callback = new HttpUtils.Callback() {
			@Override public void canceled() {}
			@Override public void updated(int length, int totalLength) {}
			@Override public void completed() {
				try {testUpdate(version, stream.toString("UTF-8"));}
				catch (UnsupportedEncodingException ex) {throw new RuntimeException(ex);}
			}
			@Override public void error(IOException ex) {
				versionLabel.setText("v" + version + " (connection error)");
				versionLabel.setIcon(Res.getImage("gfx/ic_error.png"));
			}
		};

		Timer timer = new Timer(1000, new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				HttpUtils.downloadAsync(url, stream, callback);
			}
		});

		timer.setRepeats(false);
		timer.start();
	}

	private void testUpdate(String version, String str) {
		List<String> versions = Arrays.asList(str.split("\n"));
		int versionIdx = versions.indexOf(version);

		MouseListener mouseListener = new MouseAdapter() {
			@Override public void mousePressed(MouseEvent e) {
				SwingHelper.browse(MainWindow.this, "http://code.google.com/p/box2d-editor/");
			}
		};

		if (versionIdx == 0) {
			versionLabel.setText("v" + version + " (latest version)");
			versionLabel.setIcon(Res.getImage("gfx/ic_ok.png"));
		} else if (versionIdx > 0) {
			versionLabel.setText("v" + version + " (new version available: v" + versions.get(0) + ")");
			versionLabel.setIcon(Res.getImage("gfx/ic_warning.png"));
			versionLabel.addMouseListener(mouseListener);
			Style.unregisterAllCssClasses(versionLabel);
			Style.registerCssClasses(versionLabel, ".darklink");
			Style.apply(versionLabel, new Style(Res.getUrl("css/style.css")));
		} else {
			versionLabel.setText("v" + version + " (invalid update file)");
			versionLabel.setIcon(Res.getImage("gfx/ic_error.png"));
		}
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
        logoManualLbl = new javax.swing.JLabel();
        projectPanel = new aurelienribon.bodyeditor.ui.ProjectPanel();
        jPanel1 = new javax.swing.JPanel();
        objectsPanel = new aurelienribon.ui.components.TabPanel();
        versionPanel = new javax.swing.JPanel();
        versionLabel = new javax.swing.JLabel();
        renderPanel = new javax.swing.JPanel();
        optionsPanel = new javax.swing.JPanel();
        rigidBodiesOptionsPanel1 = new aurelienribon.bodyeditor.ui.RigidBodiesOptionsPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor");

        sidePanel.setOpaque(false);

        logoPanel.setOpaque(false);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/title.png"))); // NOI18N

        logoWebsiteLbl.setText("<html><p align=\"right\">2012 - Aurelien Ribon<br/>www.aurelienribon.com</p>");

        logoManualLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/ic_manual.png"))); // NOI18N
        logoManualLbl.setText("Manual");

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoManualLbl)))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addComponent(logoWebsiteLbl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoManualLbl))
            .addComponent(jLabel10)
        );

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.BorderLayout());
        jPanel1.add(objectsPanel, java.awt.BorderLayout.CENTER);

        versionPanel.setLayout(new java.awt.BorderLayout());

        versionLabel.setText("...");
        versionPanel.add(versionLabel, java.awt.BorderLayout.CENTER);

        jPanel1.add(versionPanel, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(projectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(projectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel logoManualLbl;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel logoWebsiteLbl;
    private aurelienribon.ui.components.TabPanel objectsPanel;
    private javax.swing.JPanel optionsPanel;
    private aurelienribon.bodyeditor.ui.ProjectPanel projectPanel;
    private javax.swing.JPanel renderPanel;
    private aurelienribon.bodyeditor.ui.RigidBodiesOptionsPanel rigidBodiesOptionsPanel1;
    private javax.swing.JPanel sidePanel;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JPanel versionPanel;
    // End of variables declaration//GEN-END:variables
}
