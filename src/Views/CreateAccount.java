package Views;

import Resource.Conection;
import Resource.LocalConection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.CreateAccountViewController;
import javax.swing.ImageIcon;

public class CreateAccount extends javax.swing.JFrame {

    private int X, Y;
    private int Status = 0;
    private CreateAccountViewController vc;
    
    public CreateAccount() {
        initComponents();
        btnClose.addMouseListener(Utilities.getMLButtonClose());
        btnCambiar.addMouseListener(Utilities.getMLGeneralButton());
        txtNombre.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtCodigo.addFocusListener(Utilities.getFLPlaceHolderEfect());
        
        vc = new CreateAccountViewController(txtNombre, txtCodigo, lbEscribaContrasena, lbContrasenaIcon,txtContrasena, lbRepitaContrasena, lbRepetirContrasenaIcon,txtRepetirContrasena, btnCambiar, lbError);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnCambiar = new javax.swing.JLabel();
        lbOlvidaste = new javax.swing.JLabel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        lbError = new javax.swing.JLabel();
        txtCargando = new javax.swing.JLabel();
        lbCrear = new javax.swing.JLabel();
        txtRepetirContrasena = new javax.swing.JPasswordField();
        lbRepitaContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        lbEscribaContrasena = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbContrasenaIcon = new javax.swing.JLabel();
        lbRepetirContrasenaIcon = new javax.swing.JLabel();

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
        jLabel1.setForeground(new java.awt.Color(90, 90, 90));
        jLabel1.setText("Crea tu nueva cuenta");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 60, 460, -1));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(90, 90, 90));
        jLabel2.setText("Nombre de usuario otorgado por administrador");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 163, 460, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(180, 180, 180));
        txtCodigo.setText("Escribe tu codigo de seguridad...");
        txtCodigo.setEnabled(false);
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 290, 460, 40));

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(90, 90, 90));
        jLabel3.setText("Codigo de seguridad");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 254, 460, -1));

        btnCambiar.setBackground(new java.awt.Color(3, 57, 103));
        btnCambiar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnCambiar.setForeground(new java.awt.Color(255, 255, 255));
        btnCambiar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCambiar.setText("Verificar nombre");
        btnCambiar.setOpaque(true);
        btnCambiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCambiarMouseClicked(evt);
            }
        });
        jPanel1.add(btnCambiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 590, 240, 40));

        lbOlvidaste.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbOlvidaste.setForeground(new java.awt.Color(90, 90, 90));
        lbOlvidaste.setText("¿olvidaste tu contraseña?");
        lbOlvidaste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbOlvidaste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOlvidasteMouseClicked(evt);
            }
        });
        jPanel1.add(lbOlvidaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 650, 460, 32));

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
        jPanel1.add(lbError, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 535, 460, 38));

        txtCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 590, 40, 40));

        lbCrear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbCrear.setForeground(new java.awt.Color(90, 90, 90));
        lbCrear.setText("¿Ya tienes una cuenta? Inicia sesion.");
        lbCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCrearMouseClicked(evt);
            }
        });
        jPanel1.add(lbCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 690, 460, 32));

        txtRepetirContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtRepetirContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 480, 460, 40));

        lbRepitaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbRepitaContrasena.setForeground(new java.awt.Color(90, 90, 90));
        lbRepitaContrasena.setText("Repita su nueva contraseña");
        jPanel1.add(lbRepitaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 440, 460, -1));

        txtContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 390, 460, 40));

        lbEscribaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbEscribaContrasena.setForeground(new java.awt.Color(90, 90, 90));
        lbEscribaContrasena.setText("Escriba su nueva contraseña");
        jPanel1.add(lbEscribaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 350, 460, -1));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(180, 180, 180));
        txtNombre.setText("Escribe tu nombre de usuario otorgado...");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 196, 460, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuario40px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 200, 40, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/codigoSeguridad.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 290, 40, 40));

        lbContrasenaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/contrasena.png"))); // NOI18N
        jPanel1.add(lbContrasenaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 390, 40, 40));

        lbRepetirContrasenaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/contrasena.png"))); // NOI18N
        jPanel1.add(lbRepetirContrasenaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 480, 40, 40));

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

    private void btnCambiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCambiarMouseClicked
        switch (Status) {
            case 0:
                
                txtCargando.setIcon(new ImageIcon(getClass().getResource("/Icons/cargando32px.gif")));
                Runnable run = ()->{
                    Status += vc.verificarUsuario();
                    txtCargando.setIcon(null);
                };
                Thread thread = new Thread(run);
                thread.start();
                
                break;
            case 1:
                Status += vc.verificarCodigo();
                break;
            case 2:
                
                if(vc.crearCuenta()){
                    this.setVisible(false);
                    Dialogs.ShowMessageDialog("La cuenta ha sido creada exitosamente", Dialogs.COMPLETE_ICON);
                    Login login = new Login();
                    login.setVisible(true);
                }
                
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_btnCambiarMouseClicked

    private void lbOlvidasteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOlvidasteMouseClicked
        RecoverPassword recoverPassword = new RecoverPassword();
        recoverPassword.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbOlvidasteMouseClicked

    private void pnBarraMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - X, y - Y);
    }//GEN-LAST:event_pnBarraMouseDragged

    private void pnBarraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnBarraMousePressed
        X = evt.getX();
        Y = evt.getY();
    }//GEN-LAST:event_pnBarraMousePressed

    private void lbCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCrearMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbCrearMouseClicked

    private void btnCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCloseMouseClicked
        Conection.Disconnect(Conection.CreateEntityManager().createEntityManager());
        new LocalConection().closeConection();
        System.exit(0);
    }//GEN-LAST:event_btnCloseMouseClicked

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
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new CreateAccount().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnCambiar;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbContrasenaIcon;
    private javax.swing.JLabel lbCrear;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbEscribaContrasena;
    private javax.swing.JLabel lbOlvidaste;
    private javax.swing.JLabel lbRepetirContrasenaIcon;
    private javax.swing.JLabel lbRepitaContrasena;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JLabel txtCargando;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtRepetirContrasena;
    // End of variables declaration//GEN-END:variables
}
