
package Views.Dialogs;

import Resource.Utilities;
import ViewsControllers.Dialogs.InfoProductoDialogViewController;

public class InfoProductoDialog extends javax.swing.JDialog {

    private int X, Y;
    private InfoProductoDialogViewController vc;
    
    public InfoProductoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        btnClose.addMouseListener(Utilities.getMLButtonCloseBlue());
        
        vc = new InfoProductoDialogViewController(txtCodigo, txtDescripcion, txtMarca, txtCategoria, txtBarra, txtUnidad, txtCantidadMinima, txtPrecioCompra, txtPrecioVenta);
    }
    
    public void cargarProducto(int ProductoID){
        vc.cargarProducto(ProductoID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnBarra1 = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBarra = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCantidadMinima = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        pnBarra1.setBackground(new java.awt.Color(3, 57, 103));
        pnBarra1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnBarra1MouseDragged(evt);
            }
        });
        pnBarra1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnBarra1MousePressed(evt);
            }
        });

        btnClose.setBackground(new java.awt.Color(3, 57, 103));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/closeWhite.png"))); // NOI18N
        btnClose.setOpaque(true);
        btnClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCloseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnBarra1Layout = new javax.swing.GroupLayout(pnBarra1);
        pnBarra1.setLayout(pnBarra1Layout);
        pnBarra1Layout.setHorizontalGroup(
            pnBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBarra1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnBarra1Layout.setVerticalGroup(
            pnBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        lbTitulo.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        lbTitulo.setText("Informacion completa");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel2.setText("Codigo de producto");

        txtCodigo.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtCodigo.setText("0");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel3.setText("Descripcion");

        jScrollPane1.setBorder(null);

        txtDescripcion.setEditable(false);
        txtDescripcion.setBackground(new java.awt.Color(255, 255, 255));
        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtDescripcion.setLineWrap(true);
        txtDescripcion.setRows(2);
        txtDescripcion.setTabSize(4);
        txtDescripcion.setText("Aqui va la descripcion\n");
        txtDescripcion.setWrapStyleWord(true);
        txtDescripcion.setBorder(null);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel4.setText("Marca");

        txtMarca.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtMarca.setText("Tuberias de honduras");

        txtCategoria.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtCategoria.setText("Herramientas electricas");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel5.setText("Categoria");

        txtBarra.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtBarra.setText("021829015");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel6.setText("Codigo de barra");

        txtUnidad.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtUnidad.setText("Libras");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel7.setText("Unidades");

        txtCantidadMinima.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtCantidadMinima.setText("10.50");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel8.setText("Cantidad minima");

        txtPrecioCompra.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtPrecioCompra.setText("10.50");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel9.setText("Precio de compra");

        txtPrecioVenta.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        txtPrecioVenta.setText("10.50");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel10.setText("Precio de venta");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrecioVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidadMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnBarra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCategoria)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCantidadMinima)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioCompra))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPrecioVenta)))
                .addGap(52, 52, 52))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void pnBarra1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarra1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - X, y - Y);
    }//GEN-LAST:event_pnBarra1MouseDragged

    private void pnBarra1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarra1MousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_pnBarra1MousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InfoProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InfoProductoDialog dialog = new InfoProductoDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnBarra1;
    private javax.swing.JLabel txtBarra;
    private javax.swing.JLabel txtCantidadMinima;
    private javax.swing.JLabel txtCategoria;
    private javax.swing.JLabel txtCodigo;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtMarca;
    private javax.swing.JLabel txtPrecioCompra;
    private javax.swing.JLabel txtPrecioVenta;
    private javax.swing.JLabel txtUnidad;
    // End of variables declaration//GEN-END:variables
}
