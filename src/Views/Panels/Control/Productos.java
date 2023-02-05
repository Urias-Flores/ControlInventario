package Views.Panels.Control;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Control.ProductoViewController;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Productos extends javax.swing.JPanel {

    private ProductoViewController vc;
    
    public Productos() {
        initComponents();
        Cargar();
    }
    
    private void Cargar(){
        ArrayList<JLabel> listButtons = new ArrayList<>();
        listButtons.add(btnAdmCategorias);
        listButtons.add(btnAdmMarcas);
        listButtons.add(btnAgregar);
        listButtons.add(btnEliminar);
        listButtons.add(btnModificar);
        btnActualizar.addMouseListener(Utilities.getMLGrayButton());
        btnInformacion.addMouseListener(Utilities.getMLGrayButton());
        txtBuscar.addFocusListener(Utilities.getFLSearchTextField());

        listButtons.forEach((btn) -> {
            btn.addMouseListener(Utilities.getMLGeneralButton());
        });

        vc = new ProductoViewController(txtBuscar, tbProductos, txtCargando);
        vc.cargarProductos();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnAdmMarcas = new javax.swing.JLabel();
        btnAdmCategorias = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JLabel();
        btnModificar = new javax.swing.JLabel();
        btnInformacion = new javax.swing.JLabel();
        txtCargando = new javax.swing.JLabel();

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
        jScrollPane1.setViewportView(tbProductos);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscar.png"))); // NOI18N

        txtBuscar.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        txtBuscar.setForeground(new java.awt.Color(180, 180, 180));
        txtBuscar.setText("Buscar...");
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 22)); // NOI18N
        jLabel2.setText("Lista de productos agregados");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(54, 54, 54)
                                .addComponent(btnAdmMarcas, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAdmCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 187, Short.MAX_VALUE)
                                .addComponent(txtCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtBuscar)
                    .addComponent(btnAdmMarcas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnInformacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 551, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        vc.cargarProductos();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        vc.Eliminar();
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnActualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseClicked
        vc.cargarProductos();
    }//GEN-LAST:event_btnActualizarMouseClicked

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        vc.buscar();
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnModificarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnModificarMouseClicked
        vc.Editar();
    }//GEN-LAST:event_btnModificarMouseClicked

    private void btnInformacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnInformacionMouseClicked
        vc.ShowInfoProducto();
    }//GEN-LAST:event_btnInformacionMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnActualizar;
    private javax.swing.JLabel btnAdmCategorias;
    private javax.swing.JLabel btnAdmMarcas;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnEliminar;
    private javax.swing.JLabel btnInformacion;
    private javax.swing.JLabel btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtCargando;
    // End of variables declaration//GEN-END:variables
}
