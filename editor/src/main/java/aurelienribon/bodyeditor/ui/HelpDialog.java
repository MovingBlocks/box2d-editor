package aurelienribon.bodyeditor.ui;

import javax.swing.JFrame;

/**
 * @author Aurelien Ribon | http://www.aurelienribon.com/
 */
public class HelpDialog extends javax.swing.JDialog {
    public HelpDialog(JFrame frame) {
        super(frame, true);
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

        jLabel2.setText("<html> <b>Tutorial</b><br/> 1. Set the output file,<br/> 2. Add some images to the list,<br/> 3. Select an image and define its collision shape(s)<br/><br/>  <i>Note: if your output file extension is \".xml\" or \".json\", then saves will be done in XML or JSON format. For any other extension, saves will be done in binary.</i><br/><br/>  <font color=\"#FF8888\">Don't forget to save your work before closing the application! ;-)</font><br/><br/>  <b>Controls</b><br/> Create a shape with &lt;ctrl&gt;+&lt;left clics&gt;<br/> Edit a shape by dragging its vertices by holding &lt;left mouse button&gt;<br/> Test the collisions by holding &lt;shift&gt; and dragging the &lt;left mouse button&gt;<br/> Zoom with &lt;mouse scroll&gt;<br/> Pan by dragging the &lt;right mouse button&gt;<br/><br/>  <font color=\"#FF8888\">Mac OS users: if &lt;ctrl&gt; or &lt;shift&gt; is not recognized, you can also hold &lt;C&gt; or &lt;S&gt; (respectively).</font><br/><br/>  <b>Hints</b><br/> If you need to insert a vertex between two others, select these two vertices, and clic on the \"insert vertices\" button. A new vertex will be inserted between each pair of selected vertices.");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
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
