package Views;

import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.RecoverPasswordViewController;
import javax.swing.ImageIcon;

public class RecoverPassword extends javax.swing.JFrame {

    private int X, Y;
    private RecoverPasswordViewController vc;
    private int Status = 0;
    
    public RecoverPassword() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Icons/logo.png")).getImage());
        btnClose.addMouseListener(Utilities.getMLButtonClose());
        btnGuardar.addMouseListener(Utilities.getMLGeneralButton());
        
        txtNombre.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtCodigo.addFocusListener(Utilities.getFLPlaceHolderEfect());
        
        vc = new RecoverPasswordViewController(txtNombre, txtCodigo, lbEscribaContrasena, lbRepitaContrasena, txtContrasena, txtRepetirContrasena, lbError, btnGuardar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        lbRepitaContrasena = new javax.swing.JLabel();
        txtRepetirContrasena = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        lbEscribaContrasena = new javax.swing.JLabel();
        txtContrasena = new javax.swing.JPasswordField();
        btnGuardar = new javax.swing.JLabel();
        lbError = new javax.swing.JLabel();
        lbOlvidaste = new javax.swing.JLabel();
        lbCrear = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtCargando = new javax.swing.JLabel();

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
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 0, 570, 750));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 42)); // NOI18N
        jLabel1.setText("Reestablecer contraseña");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 495, -1));

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

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setText("Nombre de usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 163, 495, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(180, 180, 180));
        txtCodigo.setText("Escribe tu codigo de seguridad...");
        txtCodigo.setEnabled(false);
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 290, 495, 40));

        lbRepitaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbRepitaContrasena.setText("Repita su nueva contraseña");
        jPanel1.add(lbRepitaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 440, 495, -1));

        txtRepetirContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtRepetirContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 480, 495, 40));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel4.setText("Codigo de seguridad");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 250, 495, -1));

        lbEscribaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbEscribaContrasena.setText("Escriba su nueva contraseña");
        jPanel1.add(lbEscribaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 350, 495, -1));

        txtContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 390, 495, 40));

        btnGuardar.setBackground(new java.awt.Color(3, 57, 103));
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setText("Enviar codigo");
        btnGuardar.setOpaque(true);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 590, 238, 40));

        lbError.setBackground(new java.awt.Color(255, 255, 255));
        lbError.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbError.setForeground(new java.awt.Color(255, 255, 255));
        lbError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbError.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbError.setOpaque(true);
        jPanel1.add(lbError, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 535, 490, 38));

        lbOlvidaste.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbOlvidaste.setText("¿Ya tienes una cuenta? Inicia sesion");
        lbOlvidaste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbOlvidaste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOlvidasteMouseClicked(evt);
            }
        });
        jPanel1.add(lbOlvidaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 640, 310, 32));

        lbCrear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbCrear.setText("¿No tienes una cuenta? Crea una.");
        lbCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCrearMouseClicked(evt);
            }
        });
        jPanel1.add(lbCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 680, 300, 32));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(180, 180, 180));
        txtNombre.setText("Escribe tu nombre de usuario...");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 200, 495, 40));
        jPanel1.add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 590, 40, 40));

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
        Conection.Disconnect(Conection.CreateEntityManager().createEntityManager());
        System.exit(0);
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

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        switch (Status) {
            case 0:
                
                txtCargando.setIcon(new ImageIcon(getClass().getResource("/Icons/cargando32px.gif")));
                Runnable run = () -> {
                    Status += vc.EnviarCodigo();
                    txtCargando.setIcon(null);
                };
                Thread runnable = new Thread(run);
                runnable.start();
                
                break;
            case 1:
                
                Status += vc.verificarCodigo();
                
                break;
            case 2:
                if(vc.guardarContrasena()){
                    this.setVisible(false);
                    Dialogs.ShowMessageDialog("La contraseña ha sido cambiada exitosamente", Dialogs.COMPLETE_ICON);
                    Login login = new Login();
                    login.setVisible(true);
                }
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_btnGuardarMouseClicked

    private void lbOlvidasteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbOlvidasteMouseClicked
        Login login = new Login();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbOlvidasteMouseClicked

    private void lbCrearMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCrearMouseClicked
        CreateAccount createAccount = new CreateAccount();
        createAccount.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_lbCrearMouseClicked

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
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new RecoverPassword().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbCrear;
    private javax.swing.JLabel lbError;
    private javax.swing.JLabel lbEscribaContrasena;
    private javax.swing.JLabel lbOlvidaste;
    private javax.swing.JLabel lbRepitaContrasena;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JLabel txtCargando;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtRepetirContrasena;
    // End of variables declaration//GEN-END:variables
}
