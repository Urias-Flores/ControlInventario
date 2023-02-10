package Views.Dialogs;

import Resource.Utilities;
import ViewsControllers.Dialogs.InfoUsuarioDialogViewController;

public class InfoUsuarioDialog extends javax.swing.JDialog {

    private int X, Y;
    private InfoUsuarioDialogViewController vc;
    
    public InfoUsuarioDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        btnClose1.addMouseListener(Utilities.getMLButtonCloseBlue());
        vc = new InfoUsuarioDialogViewController(lbNombre, lbEmpleado, lbIdentidad, lbEstado, lbCargo);
    }
    
    public void loadUser(int UsuarioID){
        vc.cargarUsuario(UsuarioID);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnBarra1 = new javax.swing.JPanel();
        btnClose1 = new javax.swing.JLabel();
        lbTitulo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbEmpleado = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbEstado = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbCargo = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbIdentidad = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setOpacity(0.97F);
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

        btnClose1.setBackground(new java.awt.Color(3, 57, 103));
        btnClose1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/closeWhite.png"))); // NOI18N
        btnClose1.setOpaque(true);
        btnClose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnClose1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnBarra1Layout = new javax.swing.GroupLayout(pnBarra1);
        pnBarra1.setLayout(pnBarra1Layout);
        pnBarra1Layout.setHorizontalGroup(
            pnBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnBarra1Layout.createSequentialGroup()
                .addGap(0, 612, Short.MAX_VALUE)
                .addComponent(btnClose1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnBarra1Layout.setVerticalGroup(
            pnBarra1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        lbTitulo.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        lbTitulo.setText("Informacion completa de usuario");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel2.setText("Nombre de usuario");

        lbNombre.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbNombre.setText("Nombre...");

        lbEmpleado.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbEmpleado.setText("Nombre...");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel5.setText("Empleado autorizado para su uso");

        lbEstado.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbEstado.setText("Nombre...");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel6.setText("Estado actual");

        lbCargo.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbCargo.setText("Nombre...");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel7.setText("Cargo o nivel de acceso en sistema");

        lbIdentidad.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbIdentidad.setText("Nombre...");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel9.setText("Identidad del empleado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBarra1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCargo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbIdentidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnBarra1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNombre)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEmpleado)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbIdentidad)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbEstado)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbCargo)
                .addGap(0, 39, Short.MAX_VALUE))
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

    private void btnClose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnClose1MouseClicked
        this.setVisible(false);
    }//GEN-LAST:event_btnClose1MouseClicked

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
            java.util.logging.Logger.getLogger(InfoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoUsuarioDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            InfoUsuarioDialog dialog = new InfoUsuarioDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnClose1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbCargo;
    private javax.swing.JLabel lbEmpleado;
    private javax.swing.JLabel lbEstado;
    private javax.swing.JLabel lbIdentidad;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbTitulo;
    private javax.swing.JPanel pnBarra1;
    // End of variables declaration//GEN-END:variables
}
