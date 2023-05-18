package Views.Panels.Control;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Control.ProductoViewController;
import java.util.ArrayList;
import javax.swing.JLabel;

public class Productos extends javax.swing.JPanel {

    private ProductoViewController vc;
    
    public Productos() {
        initComponents();
        
        ArrayList<JLabel> listButtons = new ArrayList<>();
        listButtons.add(btnAdmCategorias);
        listButtons.add(btnAdmMarcas);
        listButtons.add(btnAgregar);
        listButtons.add(btnModificar);
        btnActualizar.addMouseListener(Utilities.getMLGrayButton());
        btnInformacion.addMouseListener(Utilities.getMLGrayButton());
        txtBuscar.addFocusListener(Utilities.getFLSearchTextField());

        listButtons.forEach((btn) -> {
            btn.addMouseListener(Utilities.getMLGeneralButton());
        });

        vc = new ProductoViewController(txtBuscar, cmbMarcas, cmbCategorias, tbProductos, 
                                        lbTotalProductos, lbTotalMarcas, lbTotalCategorias, txtCargando);
        addDesing();
    }
    
    private void addDesing(){
        txtBuscar.putClientProperty("JTextField.placeholderText", "Buscar...");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnAdmMarcas = new javax.swing.JLabel();
        btnAdmCategorias = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnInformacion = new javax.swing.JLabel();
        txtCargando = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbMarcas = new javax.swing.JComboBox<>();
        cmbCategorias = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbTotalProductos = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lbTotalMarcas = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        lbTotalCategorias = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 0, new java.awt.Color(220, 220, 220)));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 220, 220)));

        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductos.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tbProductos);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscar.png"))); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        btnAdmMarcas.setBackground(new java.awt.Color(3, 57, 103));
        btnAdmMarcas.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAdmMarcas.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmMarcas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdmMarcas.setText("Adm. marcas");
        btnAdmMarcas.setToolTipText("Administrar marcas");
        btnAdmMarcas.setOpaque(true);
        btnAdmMarcas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdmMarcasMouseClicked(evt);
            }
        });

        btnAdmCategorias.setBackground(new java.awt.Color(3, 57, 103));
        btnAdmCategorias.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAdmCategorias.setForeground(new java.awt.Color(255, 255, 255));
        btnAdmCategorias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAdmCategorias.setText("Adm. categorias");
        btnAdmCategorias.setToolTipText("administrar categorias");
        btnAdmCategorias.setOpaque(true);
        btnAdmCategorias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAdmCategoriasMouseClicked(evt);
            }
        });

        btnActualizar.setBackground(new java.awt.Color(255, 255, 255));
        btnActualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnActualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/actualizar.png"))); // NOI18N
        btnActualizar.setToolTipText("Actualizar");
        btnActualizar.setOpaque(true);
        btnActualizar.setPreferredSize(new java.awt.Dimension(38, 38));
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnActualizarMouseClicked(evt);
            }
        });

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Agregar producto");
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
        btnModificar.setText("Modificar producto");
        btnModificar.setToolTipText("Administrar marcas");
        btnModificar.setOpaque(true);
        btnModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnModificarMouseClicked(evt);
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

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel3.setText("Filtrar:");

        cmbMarcas.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        cmbMarcas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Todas las marcas --" }));
        cmbMarcas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMarcasActionPerformed(evt);
            }
        });

        cmbCategorias.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        cmbCategorias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Todas las categoria --" }));
        cmbCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbCategoriasActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(220, 220, 220)));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel1.setPreferredSize(new java.awt.Dimension(302, 102));

        jLabel6.setBackground(new java.awt.Color(250, 250, 250));
        jLabel6.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(10, 150, 50));
        jLabel6.setText("Número total de productos");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lbTotalProductos.setBackground(new java.awt.Color(250, 250, 250));
        lbTotalProductos.setFont(new java.awt.Font("Roboto", 0, 60)); // NOI18N
        lbTotalProductos.setForeground(new java.awt.Color(10, 150, 50));
        lbTotalProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/producto60px.png"))); // NOI18N
        lbTotalProductos.setText("0.00");
        lbTotalProductos.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbTotalProductos.setIconTextGap(10);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(lbTotalProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel3.setPreferredSize(new java.awt.Dimension(302, 102));

        jLabel7.setBackground(new java.awt.Color(250, 250, 250));
        jLabel7.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(10, 150, 50));
        jLabel7.setText("Número total de marcas");
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lbTotalMarcas.setBackground(new java.awt.Color(250, 250, 250));
        lbTotalMarcas.setFont(new java.awt.Font("Roboto", 0, 60)); // NOI18N
        lbTotalMarcas.setForeground(new java.awt.Color(10, 150, 50));
        lbTotalMarcas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/marca.png"))); // NOI18N
        lbTotalMarcas.setText("0.00");
        lbTotalMarcas.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbTotalMarcas.setIconTextGap(10);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(lbTotalMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230)));
        jPanel4.setPreferredSize(new java.awt.Dimension(302, 102));

        jLabel8.setBackground(new java.awt.Color(250, 250, 250));
        jLabel8.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(10, 150, 50));
        jLabel8.setText("Número total de categorías");
        jLabel8.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        lbTotalCategorias.setBackground(new java.awt.Color(250, 250, 250));
        lbTotalCategorias.setFont(new java.awt.Font("Roboto", 0, 60)); // NOI18N
        lbTotalCategorias.setForeground(new java.awt.Color(10, 150, 50));
        lbTotalCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/categoria.png"))); // NOI18N
        lbTotalCategorias.setText("0.00");
        lbTotalCategorias.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lbTotalCategorias.setIconTextGap(10);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                    .addComponent(lbTotalCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTotalCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAdmMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAdmCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(txtCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbMarcas)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnAdmCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdmMarcasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmMarcasMouseClicked
        Dialogs.ShowMarcasDialog();
    }//GEN-LAST:event_btnAdmMarcasMouseClicked

    private void btnAdmCategoriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAdmCategoriasMouseClicked
        Dialogs.ShowCategoriasDialog();
    }//GEN-LAST:event_btnAdmCategoriasMouseClicked

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        Dialogs.ShowAddProductoDialog();
        vc.updateData();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        vc.updateData();
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        vc.filter();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        vc.editProduct();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnInformacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformacionMouseClicked
        vc.showInfoProduct();
    }//GEN-LAST:event_btnInformacionMouseClicked

    private void cmbMarcasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMarcasActionPerformed
        vc.filter();
    }//GEN-LAST:event_cmbMarcasActionPerformed

    private void cmbCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbCategoriasActionPerformed
        vc.filter();
    }//GEN-LAST:event_cmbCategoriasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JLabel btnAdmCategorias;
    private javax.swing.JLabel btnAdmMarcas;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnInformacion;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JComboBox<String> cmbCategorias;
    private javax.swing.JComboBox<String> cmbMarcas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTotalCategorias;
    private javax.swing.JLabel lbTotalMarcas;
    private javax.swing.JLabel lbTotalProductos;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtCargando;
    // End of variables declaration//GEN-END:variables
}
