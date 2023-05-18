package Views.Panels.Preferencias;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Preferencias.UsuarioViewController;

public class Usuarios extends javax.swing.JPanel {

    private UsuarioViewController vc;
    
    public Usuarios() {
        initComponents();
        txtBuscar.addFocusListener(Utilities.getFLPlaceHolderEfect());
        btnActualizar.addMouseListener(Utilities.getMLGrayButton());
        btnInformacion.addMouseListener(Utilities.getMLGrayButton());
        btnAgregar.addMouseListener(Utilities.getMLGeneralButton());
        btnModificar.addMouseListener(Utilities.getMLGeneralButton());
        btnEliminar.addMouseListener(Utilities.getMLGeneralButton());
        btnDesactivar.addMouseListener(Utilities.getMLGeneralButton());
        
        vc = new UsuarioViewController(txtBuscar, cmbCargos, lbCargando, tbUsuarios);
        addDesing();
    }

    private void addDesing(){
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar...");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnActualizar = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbUsuarios = new javax.swing.JTable();
        btnAgregar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnDesactivar = new javax.swing.JLabel();
        btnInformacion = new javax.swing.JLabel();
        lbCargando = new javax.swing.JLabel();
        cmbCargos = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar lista");
        btnActualizar.setOpaque(true);
        btnActualizar.setPreferredSize(new java.awt.Dimension(38, 38));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscar.png"))); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        tbUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbUsuarios.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tbUsuarios);

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Agregar usuario");
        btnAgregar.setToolTipText("Administrar marcas");
        btnAgregar.setOpaque(true);
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        btnModificar.setBackground(new java.awt.Color(3, 57, 103));
        btnModificar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnModificar.setText("Modificar usuario");
        btnModificar.setToolTipText("Administrar marcas");
        btnModificar.setOpaque(true);
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
            }
        });

        btnEliminar.setBackground(new java.awt.Color(3, 57, 103));
        btnEliminar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminar.setText("Eliminar");
        btnEliminar.setToolTipText("Administrar marcas");
        btnEliminar.setOpaque(true);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        btnDesactivar.setBackground(new java.awt.Color(3, 57, 103));
        btnDesactivar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnDesactivar.setForeground(new java.awt.Color(255, 255, 255));
        btnDesactivar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnDesactivar.setText("Activar/Desactivar");
        btnDesactivar.setToolTipText("Administrar marcas");
        btnDesactivar.setOpaque(true);
        btnDesactivar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDesactivarMouseClicked(evt);
            }
        });

        btnInformacion.setBackground(new java.awt.Color(255, 255, 255));
        btnInformacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnInformacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/informacion.png"))); // NOI18N
        btnInformacion.setToolTipText("Ver informacion completa");
        btnInformacion.setOpaque(true);
        btnInformacion.setPreferredSize(new java.awt.Dimension(38, 38));
        btnInformacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnInformacionMouseClicked(evt);
            }
        });

        lbCargando.setBackground(new java.awt.Color(255, 255, 255));
        lbCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbCargando.setToolTipText("Ver informacion completa");
        lbCargando.setOpaque(true);
        lbCargando.setPreferredSize(new java.awt.Dimension(38, 38));

        cmbCargos.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        cmbCargos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Todas los cargos --", "Administrador", "Dependiente" }));
        cmbCargos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCargosActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel3.setText("Filtrar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 260, Short.MAX_VALUE)
                        .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbCargos, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbCargando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(btnInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCargos))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDesactivar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        Dialogs.ShowAddUsuarioDialog();
        vc.updateData();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        vc.editUser();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        vc.deleteUser();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnDesactivarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDesactivarMouseClicked
        vc.disableUser();
    }//GEN-LAST:event_btnDesactivarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        vc.filter();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        vc.updateData();
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void btnInformacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformacionMouseClicked
        vc.showUserInformation();
    }//GEN-LAST:event_btnInformacionMouseClicked

    private void cmbCargosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCargosActionPerformed
        vc.filter();
    }//GEN-LAST:event_cmbCargosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnDesactivar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnInformacion;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JComboBox<String> cmbCargos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCargando;
    private javax.swing.JTable tbUsuarios;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
