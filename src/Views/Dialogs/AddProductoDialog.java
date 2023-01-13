
package Views.Dialogs;

import Resource.Utilities;
import ViewsControllers.Dialogs.AddProductDialogViewController;
import java.awt.Color;

public class AddProductoDialog extends javax.swing.JDialog {

    private int X, Y;
    private AddProductDialogViewController vc;
    private boolean editing = false;
    
    public AddProductoDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        btnClose.addMouseListener(Utilities.getMLButtonCloseBlue());
        btnAgregarMarca.addMouseListener(Utilities.getMLGrayButton());
        btnAgregarCategoria.addMouseListener(Utilities.getMLGrayButton());
        btnListaMarca.addMouseListener(Utilities.getMLGrayButton());
        btnListaCategoria.addMouseListener(Utilities.getMLGrayButton());
        btnAgregar.addMouseListener(Utilities.getMLGeneralButton());
        txtError.setBackground(Color.white);
        
        txtBarra.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtUnidad.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtCantidadMinima.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtPrecioCompra.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtPrecioVenta.addFocusListener(Utilities.getFLPlaceHolderEfect());
        
        vc = new AddProductDialogViewController(txtDescripcion, txtMarca, txtCategoria, txtBarra, txtUnidad, txtCantidadMinima, txtPrecioCompra, txtPrecioVenta, txtError);
    }

    public void EditigingMode(int ProductoID){
        vc.setProductoToEdit(ProductoID);
        btnAgregar.setText("Guardar");
        editing = true;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pnBarra = new javax.swing.JPanel();
        btnClose = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDescripcion = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtMarca = new javax.swing.JTextField();
        btnAgregarMarca = new javax.swing.JLabel();
        btnListaMarca = new javax.swing.JLabel();
        txtCategoria = new javax.swing.JTextField();
        btnAgregarCategoria = new javax.swing.JLabel();
        btnListaCategoria = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBarra = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtUnidad = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JLabel();
        txtError = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtCantidadMinima = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtPrecioVenta = new javax.swing.JTextField();

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

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        jLabel1.setText("Agregar nuevo producto");

        jLabel2.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel2.setText("Descripcion:");

        txtDescripcion.setColumns(20);
        txtDescripcion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtDescripcion.setRows(5);
        jScrollPane1.setViewportView(txtDescripcion);

        jLabel3.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel3.setText("Marca:");

        txtMarca.setEditable(false);
        txtMarca.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(180, 180, 180));
        txtMarca.setText("Seleccione una marca...");

        btnAgregarMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregarAzul.png"))); // NOI18N
        btnAgregarMarca.setToolTipText("Agregar marca");
        btnAgregarMarca.setOpaque(true);
        btnAgregarMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMarcaMouseClicked(evt);
            }
        });

        btnListaMarca.setBackground(new java.awt.Color(255, 255, 255));
        btnListaMarca.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnListaMarca.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscarEnLista.png"))); // NOI18N
        btnListaMarca.setToolTipText("Lista de marcas");
        btnListaMarca.setOpaque(true);
        btnListaMarca.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListaMarcaMouseClicked(evt);
            }
        });

        txtCategoria.setEditable(false);
        txtCategoria.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCategoria.setForeground(new java.awt.Color(180, 180, 180));
        txtCategoria.setText("Seleccione una categoria...");

        btnAgregarCategoria.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregarAzul.png"))); // NOI18N
        btnAgregarCategoria.setToolTipText("Agregar categoria");
        btnAgregarCategoria.setOpaque(true);
        btnAgregarCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarCategoriaMouseClicked(evt);
            }
        });

        btnListaCategoria.setBackground(new java.awt.Color(255, 255, 255));
        btnListaCategoria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnListaCategoria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscarEnLista.png"))); // NOI18N
        btnListaCategoria.setToolTipText("Lista de categorias");
        btnListaCategoria.setOpaque(true);
        btnListaCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnListaCategoriaMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel8.setText("Categoria:");

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel9.setText("Codigo de barras:");

        txtBarra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtBarra.setForeground(new java.awt.Color(180, 180, 180));
        txtBarra.setText("Escriba el codigo de barras...");

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel10.setText("Tipo de unidad del producto:");

        txtUnidad.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtUnidad.setForeground(new java.awt.Color(180, 180, 180));
        txtUnidad.setText("Ingrese el tipo de unidad ejm. Libras.");

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel11.setText("Precio de compra:");

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel12.setText("Precio de venta:");

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Agregar");
        btnAgregar.setOpaque(true);
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        txtError.setBackground(new java.awt.Color(185, 0, 0));
        txtError.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 255, 255));
        txtError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtError.setText("Texto de error");
        txtError.setOpaque(true);

        jLabel13.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel13.setText("Cantidad minima en inventario");

        txtCantidadMinima.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCantidadMinima.setForeground(new java.awt.Color(180, 180, 180));
        txtCantidadMinima.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtCantidadMinima.setText("0.00");
        txtCantidadMinima.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadMinimaFocusLost(evt);
            }
        });

        txtPrecioCompra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPrecioCompra.setForeground(new java.awt.Color(180, 180, 180));
        txtPrecioCompra.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtPrecioCompra.setText("0.00");
        txtPrecioCompra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioCompraFocusLost(evt);
            }
        });

        txtPrecioVenta.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtPrecioVenta.setForeground(new java.awt.Color(180, 180, 180));
        txtPrecioVenta.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtPrecioVenta.setText("0.00");
        txtPrecioVenta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPrecioVentaFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnBarra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMarca)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnListaMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCategoria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnListaCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBarra)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnidad, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtCantidadMinima, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                                    .addComponent(txtPrecioCompra))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtPrecioVenta))))))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMarca)
                    .addComponent(btnAgregarMarca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListaMarca, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCategoria)
                    .addComponent(btnAgregarCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnListaCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidad, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCantidadMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPrecioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
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
        if(editing){
            if(vc.Edit()){
                this.setVisible(false);
                Dialogs.ShowMessageDialog("El producto ha sido modificado exitosamente", Dialogs.COMPLETE_ICON);
            }
        }else{
            if(vc.Insert()){
                this.setVisible(false);
                Dialogs.ShowMessageDialog("El producto ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnAgregarMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMarcaMouseClicked
        Dialogs.ShowAddMarcaDialog();
    }//GEN-LAST:event_btnAgregarMarcaMouseClicked

    private void btnAgregarCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarCategoriaMouseClicked
        Dialogs.ShowAddCategoriaDialog();
    }//GEN-LAST:event_btnAgregarCategoriaMouseClicked

    private void btnListaMarcaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaMarcaMouseClicked
        vc.cargarMarca();
    }//GEN-LAST:event_btnListaMarcaMouseClicked

    private void btnListaCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnListaCategoriaMouseClicked
        vc.cargarCategoria();
    }//GEN-LAST:event_btnListaCategoriaMouseClicked

    private void txtCantidadMinimaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadMinimaFocusLost
        vc.setNumberFormat(txtCantidadMinima);
    }//GEN-LAST:event_txtCantidadMinimaFocusLost

    private void txtPrecioCompraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioCompraFocusLost
        vc.setNumberFormat(txtPrecioCompra);
    }//GEN-LAST:event_txtPrecioCompraFocusLost

    private void txtPrecioVentaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPrecioVentaFocusLost
        vc.setNumberFormat(txtPrecioVenta);
    }//GEN-LAST:event_txtPrecioVentaFocusLost

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
            java.util.logging.Logger.getLogger(AddProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProductoDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            AddProductoDialog dialog = new AddProductoDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btnAgregarCategoria;
    private javax.swing.JLabel btnAgregarMarca;
    private javax.swing.JLabel btnClose;
    private javax.swing.JLabel btnListaCategoria;
    private javax.swing.JLabel btnListaMarca;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnBarra;
    private javax.swing.JTextField txtBarra;
    private javax.swing.JTextField txtCantidadMinima;
    private javax.swing.JTextField txtCategoria;
    private javax.swing.JTextArea txtDescripcion;
    private javax.swing.JLabel txtError;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPrecioVenta;
    private javax.swing.JTextField txtUnidad;
    // End of variables declaration//GEN-END:variables
}
