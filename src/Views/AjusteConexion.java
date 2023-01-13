package Views;

import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.AjustesConexionViewController;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class AjusteConexion extends javax.swing.JFrame {

    private int X, Y;
    private AjustesConexionViewController vc;

    public AjusteConexion() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Icons/logo.png")).getImage());
        btnClose.addMouseListener(Utilities.getMLButtonClose());
        btnGuadar.addMouseListener(Utilities.getMLGeneralButton());
        btnCancelar.addMouseListener(Utilities.getMLGeneralButton());
        
        txtDireccion.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtPuerto.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtUsuario.addFocusListener(Utilities.getFLPlaceHolderEfect());
        
        vc = new AjustesConexionViewController(txtDireccion, txtPuerto, txtUsuario, txtContrasena, lbError);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        btnGuadar = new javax.swing.JLabel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lbError = new javax.swing.JLabel();
        txtCargando = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JLabel();
        txtPuerto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(3, 57, 103));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 570, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 0, 570, 750));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 42)); // NOI18N
        jLabel1.setText("Ajustes de conexion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 495, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setText("Direccion IP:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 163, 240, -1));

        txtDireccion.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtDireccion.setForeground(new java.awt.Color(180, 180, 180));
        txtDireccion.setText("192.132.0.7...");
        jPanel1.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 196, 240, 40));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel3.setText("Contraseña");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 344, 495, -1));

        txtContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 380, 495, 40));

        btnGuadar.setBackground(new java.awt.Color(3, 57, 103));
        btnGuadar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnGuadar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuadar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuadar.setText("Guardar cambios");
        btnGuadar.setOpaque(true);
        btnGuadar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuadarMouseClicked(evt);
            }
        });
        jPanel1.add(btnGuadar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 500, 238, 40));

        pnBarra.setBackground(new java.awt.Color(255, 255, 255));
        pnBarra.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(220, 220, 220)));
        pnBarra.setPreferredSize(new java.awt.Dimension(1120, 36));
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

        btnClose.setBackground(new java.awt.Color(255, 255, 255));
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/closeBlue.png"))); // NOI18N
        btnClose.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(220, 220, 220)));
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
            .addGroup(pnBarraLayout.createSequentialGroup()
                .addComponent(btnClose)
                .addGap(0, 1167, Short.MAX_VALUE))
        );
        pnBarraLayout.setVerticalGroup(
            pnBarraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        jPanel1.add(pnBarra, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 36));

        lbError.setBackground(new java.awt.Color(255, 255, 255));
        lbError.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbError.setForeground(new java.awt.Color(255, 255, 255));
        lbError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbError.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbError.setOpaque(true);
        jPanel1.add(lbError, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 440, 495, 38));

        txtCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 40, 40));

        btnCancelar.setBackground(new java.awt.Color(3, 57, 103));
        btnCancelar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCancelar.setText("Cancelar");
        btnCancelar.setOpaque(true);
        btnCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 500, 238, 40));

        txtPuerto.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtPuerto.setForeground(new java.awt.Color(180, 180, 180));
        txtPuerto.setText("1001...");
        jPanel1.add(txtPuerto, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 196, 230, 40));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel4.setText("Puerto:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 163, 230, -1));

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel5.setText("Usuario:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 250, 230, -1));

        txtUsuario.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(180, 180, 180));
        txtUsuario.setText("Escriba nombre de usuario de db...");
        jPanel1.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 284, 495, 40));

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

    private void btnGuadarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuadarMouseClicked
        if(vc.GuardarCambios()){
            try {
                Dialogs.ShowMessageDialog("Los cambiar han sido aplicados, se reiniciara la aplicacion", Dialogs.COMPLETE_ICON);
                Desktop deskotp = Desktop.getDesktop();
                deskotp.open(new File("./dist/ControlInventario.jar"));
                System.exit(0);
            } catch (IOException ex) {
                Logger.getLogger(AjusteConexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGuadarMouseClicked

    private void pnBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_pnBarraMousePressed

    private void pnBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - X, y - Y);
    }//GEN-LAST:event_pnBarraMouseDragged

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        Conection.Disconnect(Conection.CreateEntityManager().createEntityManager());
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void btnCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelarMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_btnCancelarMouseClicked
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (UIManager.getSystemLookAndFeelClassName().equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AjusteConexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new AjusteConexion().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCancelar;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnGuadar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbError;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JLabel txtCargando;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtPuerto;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
