package aurelienribon.bodyeditor.ui;

import aurelienribon.bodyeditor.Ctx;
import aurelienribon.bodyeditor.Settings;
import aurelienribon.ui.components.PaintedPanel;
import aurelienribon.ui.css.Style;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import res.Res;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class AutoTraceParamsDialog extends javax.swing.JDialog {
	private boolean result = false;

    public AutoTraceParamsDialog(javax.swing.JFrame parent) {
        super(parent, true);

		setContentPane(new PaintedPanel());
        initComponents();

		Style.registerCssClasses(getContentPane(), ".rootPanel", ".configPanel");
		Style.registerCssClasses(commentLabel, ".brightcomment");
		Style.apply(getContentPane(), new Style(Res.getUrl("css/style.css")));

		hullToleranceSlider.setValue((int) (Settings.autoTraceHullTolerance * 100));
		alphaToleranceSlider.setValue(Settings.autoTraceAlphaTolerance);
		multiPartDetectionChk.setSelected(Settings.autoTraceMultiPartDetection);
		holeDetectionChk.setSelected(Settings.autoTraceHoleDetection);

		okBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				Settings.autoTraceHullTolerance = hullToleranceSlider.getValue() / 100f;
				Settings.autoTraceAlphaTolerance = alphaToleranceSlider.getValue();
				Settings.autoTraceMultiPartDetection = multiPartDetectionChk.isSelected();
				Settings.autoTraceHoleDetection = holeDetectionChk.isSelected();
				dispose();
				result = true;
			}
		});

		cancelBtn.addActionListener(new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
    }

	public boolean prompt() {
		setVisible(true);
		dispose();
		return result;
	}

	// -------------------------------------------------------------------------
	// Generated stuff
	// -------------------------------------------------------------------------

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        paintedPanel1 = new aurelienribon.ui.components.PaintedPanel();
        hullToleranceSlider = new javax.swing.JSlider();
        alphaToleranceSlider = new javax.swing.JSlider();
        multiPartDetectionChk = new javax.swing.JCheckBox();
        holeDetectionChk = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        okBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        commentLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Auto-trace parameters");
        setResizable(false);

        hullToleranceSlider.setMajorTickSpacing(100);
        hullToleranceSlider.setMaximum(400);
        hullToleranceSlider.setMinimum(100);
        hullToleranceSlider.setMinorTickSpacing(10);
        hullToleranceSlider.setPaintTicks(true);
        hullToleranceSlider.setValue(400);

        alphaToleranceSlider.setMaximum(255);
        alphaToleranceSlider.setMinorTickSpacing(5);
        alphaToleranceSlider.setPaintTicks(true);
        alphaToleranceSlider.setValue(128);

        multiPartDetectionChk.setText("Multi-part detection");

        holeDetectionChk.setText("Hole detection");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Hull tolerance: ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Alpha tolerance: ");

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
                        .addComponent(hullToleranceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paintedPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaToleranceSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(paintedPanel1Layout.createSequentialGroup()
                        .addComponent(multiPartDetectionChk)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(paintedPanel1Layout.createSequentialGroup()
                        .addComponent(holeDetectionChk)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelBtn)))
                .addContainerGap())
        );

        paintedPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        paintedPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelBtn, okBtn});

        paintedPanel1Layout.setVerticalGroup(
            paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paintedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hullToleranceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(alphaToleranceSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(multiPartDetectionChk)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paintedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(holeDetectionChk)
                    .addComponent(okBtn)
                    .addComponent(cancelBtn))
                .addContainerGap())
        );

        paintedPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {alphaToleranceSlider, hullToleranceSlider, jLabel1, jLabel2});

        commentLabel.setText("<html>\nOnly check multi-part detection or hole detection if your image needs it.<br/>\nRemember that auto-trace is less precise than manually placed points.");
        commentLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/res/gfx/autoTrace.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paintedPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(commentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paintedPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(commentLabel)
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider alphaToleranceSlider;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JCheckBox holeDetectionChk;
    private javax.swing.JSlider hullToleranceSlider;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JCheckBox multiPartDetectionChk;
    private javax.swing.JButton okBtn;
    private aurelienribon.ui.components.PaintedPanel paintedPanel1;
    // End of variables declaration//GEN-END:variables

}
