package Views;

import Resource.Conection;
import Resource.LocalConection;
import Resource.NoJpaConection;
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
        setIconImage(new ImageIcon(getClass().getResource("/Icons/LogoV1.0-Fondo.png")).getImage());
        btnClose.addMouseListener(Utilities.getMLButtonClose());
        btnGuardar.addMouseListener(Utilities.getMLGeneralButton());
        
        txtNombre.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtCodigo.addFocusListener(Utilities.getFLPlaceHolderEfect());
        
        vc = new RecoverPasswordViewController(txtNombre, txtCodigo, lbEscribaContrasena, lbRepitaContrasena, txtContrasena, lbContrasenaIcon,txtRepetirContrasena, lbRepetirContrasenaIcon,lbError, btnGuardar);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
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
        jLabel5 = new javax.swing.JLabel();
        lbRepetirContrasenaIcon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbContrasenaIcon = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(3, 57, 103));

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/LogoV1.0.png"))); // NOI18N

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/Titulo.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setText("by ubsoft");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(168, 168, 168)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 261, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(631, 0, 570, 750));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(90, 90, 90));
        jLabel1.setText("Reestablecer contraseña");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 60, 500, -1));

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
        jLabel2.setForeground(new java.awt.Color(90, 90, 90));
        jLabel2.setText("Nombre de usuario");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 163, 460, -1));

        txtCodigo.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtCodigo.setForeground(new java.awt.Color(180, 180, 180));
        txtCodigo.setText("Escribe tu codigo de seguridad...");
        txtCodigo.setEnabled(false);
        jPanel1.add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 290, 460, 40));

        lbRepitaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbRepitaContrasena.setForeground(new java.awt.Color(90, 90, 90));
        lbRepitaContrasena.setText("Repita su nueva contraseña");
        jPanel1.add(lbRepitaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 440, 460, -1));

        txtRepetirContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtRepetirContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 480, 460, 40));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(90, 90, 90));
        jLabel4.setText("Codigo de seguridad");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 250, 460, -1));

        lbEscribaContrasena.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        lbEscribaContrasena.setForeground(new java.awt.Color(90, 90, 90));
        lbEscribaContrasena.setText("Escriba su nueva contraseña");
        jPanel1.add(lbEscribaContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 350, 460, -1));

        txtContrasena.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jPanel1.add(txtContrasena, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 390, 460, 40));

        btnGuardar.setBackground(new java.awt.Color(3, 57, 103));
        btnGuardar.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnGuardar.setText("Validar usuario");
        btnGuardar.setOpaque(true);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        jPanel1.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 590, 240, 40));

        lbError.setBackground(new java.awt.Color(255, 255, 255));
        lbError.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        lbError.setForeground(new java.awt.Color(255, 255, 255));
        lbError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbError.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lbError.setOpaque(true);
        jPanel1.add(lbError, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 535, 460, 38));

        lbOlvidaste.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbOlvidaste.setForeground(new java.awt.Color(90, 90, 90));
        lbOlvidaste.setText("¿Ya tienes una cuenta? Inicia sesion");
        lbOlvidaste.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbOlvidaste.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbOlvidasteMouseClicked(evt);
            }
        });
        jPanel1.add(lbOlvidaste, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 640, 310, 32));

        lbCrear.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbCrear.setForeground(new java.awt.Color(90, 90, 90));
        lbCrear.setText("¿No tienes una cuenta? Crea una.");
        lbCrear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbCrear.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCrearMouseClicked(evt);
            }
        });
        jPanel1.add(lbCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 680, 300, 32));

        txtNombre.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(180, 180, 180));
        txtNombre.setText("Escribe tu nombre de usuario...");
        jPanel1.add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 200, 460, 40));
        jPanel1.add(txtCargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 590, 40, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/usuario40px.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 200, 40, 40));

        lbRepetirContrasenaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/contrasena.png"))); // NOI18N
        jPanel1.add(lbRepetirContrasenaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 480, 40, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/codigoSeguridad.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 290, 40, 40));

        lbContrasenaIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/contrasena.png"))); // NOI18N
        jPanel1.add(lbContrasenaIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 390, 40, 40));

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
        Utilities.setRunProcess(false);
        if(Conection.createEntityManagerFactory().isOpen()){
            Conection.Disconnect(Conection.createEntityManagerFactory().createEntityManager());
        }
        new NoJpaConection().closeConec();
        new LocalConection().closeConection();
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RecoverPassword.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
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
