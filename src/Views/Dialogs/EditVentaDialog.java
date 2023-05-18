package Views.Dialogs;

import Resource.Utilities;
import ViewsControllers.Dialogs.EditVentaDialogViewController;
import java.awt.Color;

public class EditVentaDialog extends javax.swing.JDialog {

    private int X, Y;
    private Object[] Values;
    private EditVentaDialogViewController vc;
    private boolean isCompra = false;
    
    public Object[] getValues(){
        return Values;
    }
    
    public EditVentaDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnClose.addMouseListener(Utilities.getMLButtonCloseBlue());
        btnAgregar.addMouseListener(Utilities.getMLGeneralButton());
        txtError.setBackground(Color.white);
        
        vc = new EditVentaDialogViewController(lbCodigo, lbDescripcion, lbUnidades, txtExistencia, txtDescuentoPorcentaje, txtDescuentoLempiras, txtPrecio, txtCantidad, txtSubtotal, txtError);
    }
    
    public void setValues(Object[] values){
        vc.setValues(values);
    }
    
    public void setCompraState(){
        txtExistencia.setVisible(false);
        lbExistencia.setVisible(false);
        txtPrecio.setEditable(true);
        isCompra = true;
    }
    
    public void setVentaState(){
        vc.setIsVenta(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtSubtotal = new javax.swing.JTextField();
        txtError = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbExistencia = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        txtDescuentoLempiras = new javax.swing.JTextField();
        txtDescuentoPorcentaje = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbDescripcion = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbUnidades = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        pnBarra.setBackground(new java.awt.Color(3, 57, 103));
        pnBarra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                pnBarraMouseDragged(evt);
            }
        });
        pnBarra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnBarraMousePressed(evt);
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

        javax.swing.GroupLayout pnBarraLayout = new javax.swing.GroupLayout(pnBarra);
        pnBarra.setLayout(pnBarraLayout);
        pnBarraLayout.setHorizontalGroup(
            pnBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBarraLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnBarraLayout.setVerticalGroup(
            pnBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("Precio:");

        txtPrecio.setEditable(false);
        txtPrecio.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPrecio.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPrecio.setText("0.00");
        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioKeyReleased(evt);
            }
        });

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSubtotal.setText("0.00");
        txtSubtotal.setPreferredSize(new java.awt.Dimension(80, 38));

        txtError.setBackground(new java.awt.Color(185, 0, 0));
        txtError.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 255, 255));
        txtError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtError.setText("Texto de error");
        txtError.setOpaque(true);

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Descuento (Lps.):");

        lbExistencia.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        lbExistencia.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lbExistencia.setText("Existencia:");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Descuento (%):");

        txtExistencia.setEditable(false);
        txtExistencia.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtExistencia.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtExistencia.setText("0.00");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel6.setText("Cantidad:");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Subtotal:");

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Editar factura");
        btnAgregar.setToolTipText("Administrar marcas");
        btnAgregar.setOpaque(true);
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        txtCantidad.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCantidad.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtCantidad.setText("0.00");
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
        });

        txtDescuentoLempiras.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtDescuentoLempiras.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuentoLempiras.setText("0.00");
        txtDescuentoLempiras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoLempirasKeyReleased(evt);
            }
        });

        txtDescuentoPorcentaje.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtDescuentoPorcentaje.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescuentoPorcentaje.setText("0.00");
        txtDescuentoPorcentaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDescuentoPorcentajeKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel1.setText("Codigo de producto");

        lbCodigo.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbCodigo.setText("10001");

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel3.setText("Descripcion");

        lbDescripcion.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbDescripcion.setText("Aqui va la descripcion del producto");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel11.setText("Unidades");

        lbUnidades.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbUnidades.setText("Libras");

        jLabel13.setFont(new java.awt.Font("Roboto", 1, 30)); // NOI18N
        jLabel13.setText("Editar poducto de la factura");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBarra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUnidades)
                            .addComponent(jLabel11)
                            .addComponent(lbDescripcion)
                            .addComponent(jLabel3)
                            .addComponent(lbCodigo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtDescuentoLempiras, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                            .addComponent(txtDescuentoPorcentaje)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lbExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtExistencia)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                                    .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCantidad)))
                            .addComponent(txtError, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCodigo)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbDescripcion)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUnidades)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbExistencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescuentoPorcentaje, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescuentoLempiras, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
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

    private void pnBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - X, y - Y);
    }//GEN-LAST:event_pnBarraMouseDragged

    private void pnBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_pnBarraMousePressed

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        if(isCompra){
            Values = vc.getValuesforBuy();
        }else{
            vc.setIsVenta(true);
            Values = vc.getValuesforSale();
        }
        
        if(Values != null){
            this.setVisible(false);
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased
        vc.updateSubtotal();
    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtDescuentoLempirasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoLempirasKeyReleased
        vc.updateLempirasPorcent();
        vc.updateSubtotal();
    }//GEN-LAST:event_txtDescuentoLempirasKeyReleased

    private void txtDescuentoPorcentajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescuentoPorcentajeKeyReleased
        vc.updatePorcentLempiras();
        vc.updateSubtotal();
    }//GEN-LAST:event_txtDescuentoPorcentajeKeyReleased

    private void txtPrecioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyReleased
        vc.updateSubtotal();
    }//GEN-LAST:event_txtPrecioKeyReleased

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
            java.util.logging.Logger.getLogger(EditVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditVentaDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            EditVentaDialog dialog = new EditVentaDialog(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDescripcion;
    private javax.swing.JLabel lbExistencia;
    private javax.swing.JLabel lbUnidades;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtDescuentoLempiras;
    private javax.swing.JTextField txtDescuentoPorcentaje;
    private javax.swing.JLabel txtError;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtSubtotal;
    // End of variables declaration//GEN-END:variables
}
