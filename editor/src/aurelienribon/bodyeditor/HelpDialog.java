package aurelienribon.bodyeditor;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class HelpDialog extends javax.swing.JDialog {
    public HelpDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Physics Body Editor - Help");

        jPanel1.setBackground(Theme.MAIN_BACKGROUND);

        jPanel4.setBackground(Theme.MAIN_ALT_BACKGROUND);

        jLabel2.setForeground(Theme.MAIN_ALT_FOREGROUND);
        jLabel2.setText("<html>\n<b>Tutorial</b><br/>\n1. Set the output file,<br/>\n2. Add some images to the list,<br/>\n3. Select an image and define its collision shape(s)<br/><br/>\n\n<font color=\"#FF8888\">Don't forget to save your work before closing the application! ;-)</font><br/><br/>\n\n<b>Controls</b><br/>\nCreate a shape with &lt;ctrl&gt;+&lt;left clics&gt;<br/>\nEdit a shape by dragging its vertices by holding &lt;left mouse button&gt;<br/>\nTest the collisions by holding &lt;shift&gt; and dragging the &lt;left mouse button&gt;<br/>\nZoom with &lt;mouse scroll&gt;<br/>\nPan by dragging the &lt;right mouse button&gt;<br/><br/>\n\n<font color=\"#FF8888\">Mac OS users: if &lt;ctrl&gt; or &lt;shift&gt; is not recognized, you can also hold &lt;C&gt; or &lt;S&gt; (respectively).</font><br/><br/>\n\n<b>Hints</b><br/>\nIf you need to insert a vertex between two others, select these two vertices, and clic on the \"insert vertices\" button. A new vertex will be inserted between each pair of selected vertices.");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
