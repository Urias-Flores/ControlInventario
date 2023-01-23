package Views;

import Resource.Conection;
import Resource.LocalConection;
import Resource.Utilities;
import ViewsControllers.LoginViewController;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

public class Login extends javax.swing.JFrame {

    private int X, Y;
    private LoginViewController loginVC;

    public Login() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Icons/logo.png")).getImage());
        btnClose.addMouseListener(Utilities.getMLButtonClose());
        btnIniciarSesion.addMouseListener(Utilities.getMLGeneralButton());
        txtNombre.addFocusListener(Utilities.getFLPlaceHolderEfect());
        loginVC = new LoginViewController(this, txtNombre, txtContrasena, cbRecordarme, lbError, btnIniciarSesion, lbOlvidaste);
        loginVC.CargarUsuario();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        btnIniciarSesion = new javax.swing.JLabel();
        cbRecordarme = new javax.swing.JCheckBox();
        lbOlvidaste = new javax.swing.JLabel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lbError = new javax.swing.JLabel();
        txtCargando = new javax.swing.JLabel();
        lbCrear = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

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
        jLabel1.setForeground(new java.awt.Color(3, 56, 103));
        jLabel1.setText("Iniciar Sesion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 60, 460, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(3, 56, 103));
        jLabel2.setText("Nombre de usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 163, 460, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(180, 180, 180));
        txtNombre.setText("Escribe tu nombre de usuario...");
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 196, 460, 40));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(3, 56, 103));
        jLabel3.setText("Contraseña");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 254, 460, -1));

        txtContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContrasenaActionPerformed(evt);
            }
        });
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 287, 460, 40));

        btnIniciarSesion.setBackground(new java.awt.Color(3, 57, 103));
        btnIniciarSesion.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnIniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarSesion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIniciarSesion.setText("Iniciar sesion");
        btnIniciarSesion.setOpaque(true);
        btnIniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarSesionMouseClicked(evt);
            }
        });
        jPanel1.add(btnIniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, 238, 40));

        cbRecordarme.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cbRecordarme.setForeground(new java.awt.Color(3, 56, 103));
        cbRecordarme.setText("Recordarme");
        jPanel1.add(cbRecordarme, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 340, 460, 38));

        lbOlvidaste.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbOlvidaste.setForeground(new java.awt.Color(3, 56, 103));
        lbOlvidaste.setText("¿olvidaste tu contraseña?");
        lbOlvidaste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbOlvidaste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOlvidasteMouseClicked(evt);
            }
        });
        jPanel1.add(lbOlvidaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 238, 32));

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
        jPanel1.add(lbError, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 382, 490, 38));

        txtCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 40, 40));

        lbCrear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbCrear.setForeground(new java.awt.Color(3, 56, 103));
        lbCrear.setText("¿No tienes una cuenta? Crea una.");
        lbCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCrearMouseClicked(evt);
            }
        });
        jPanel1.add(lbCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 550, 300, 32));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/contrasena.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 287, 40, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuario40px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 196, 40, 40));

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

    private void btnIniciarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarSesionMouseClicked
        IniciarSesion();
    }//GEN-LAST:event_btnIniciarSesionMouseClicked

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
        new LocalConection().closeConection();
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        IniciarSesion();
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContrasenaActionPerformed
        IniciarSesion();
    }//GEN-LAST:event_txtContrasenaActionPerformed

    private void lbOlvidasteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOlvidasteMouseClicked
        RecoverPassword recoverPassword = new RecoverPassword();
        recoverPassword.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbOlvidasteMouseClicked

    private void lbCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCrearMouseClicked
        CreateAccount createAccount = new CreateAccount();
        createAccount.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbCrearMouseClicked

    public void IniciarSesion() {
        txtCargando.setIcon(new ImageIcon(getClass().getResource("/Icons/cargando32px.gif")));
        Runnable run = () -> {
            loginVC.IniciarSesion();
            txtCargando.setIcon(null);
        };
        Thread runnable = new Thread(run);
        runnable.start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (UIManager.getSystemLookAndFeelClassName().equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (javax.swing.UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnIniciarSesion;
    private javax.swing.JCheckBox cbRecordarme;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCrear;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbOlvidaste;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JLabel txtCargando;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
