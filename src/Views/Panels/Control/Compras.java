
package Views.Panels.Control;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Control.ComprasViewController;

public class Compras extends javax.swing.JPanel {

    private ComprasViewController vc;
    
    public Compras() {
        initComponents();
        
        btnAgregarProveedor.addMouseListener(Utilities.getMLGrayButton());
        btnAgregarCarrito.addMouseListener(Utilities.getMLGrayButton());
        btnEditar.addMouseListener(Utilities.getMLGrayButton());
        btnEliminarCarrito.addMouseListener(Utilities.getMLGrayButton());
        btnEliminarTodo.addMouseListener(Utilities.getMLGrayButton());
        btnAgregar.addMouseListener(Utilities.getMLGeneralButton());
        txtCodigoBarra.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtFactura.addFocusListener(Utilities.getFLPlaceHolderEfect());
        bgEstado.add(rbPagada);
        bgEstado.add(rbPendiente);
        
        
        vc = new ComprasViewController(cmbProveedores, txtFactura, cmbDiaCompra, 
                cmbMesCompra, cmbAnioCompra, cmbDiaVencimiento, cmbMesVencimiento, 
                cmbAnioVencimiento, rbPagada, rbPendiente, txtCodigoBarra, 
                tbCompras, txtSubtotal, txtDescuento, txtImporte, txtISV, 
                txtTotal, lbCargando);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgEstado = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbProveedores = new javax.swing.JComboBox<>();
        btnAgregarCarrito = new javax.swing.JLabel();
        btnEliminarCarrito = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbCompras = new javax.swing.JTable();
        btnAgregar = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtISV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        btnAgregarProveedor = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rbPagada = new javax.swing.JRadioButton();
        rbPendiente = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        cmbDiaCompra = new javax.swing.JComboBox<>();
        cmbMesCompra = new javax.swing.JComboBox<>();
        cmbAnioCompra = new javax.swing.JComboBox<>();
        cmbDiaVencimiento = new javax.swing.JComboBox<>();
        cmbMesVencimiento = new javax.swing.JComboBox<>();
        cmbAnioVencimiento = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JLabel();
        btnEliminarTodo = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtCodigoBarra = new javax.swing.JTextField();
        lbCargando = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel2.setText("Lista de productos agregados");

        jLabel1.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel1.setText("Proveedor:");

        cmbProveedores.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbProveedores.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione proveedor --" }));
        cmbProveedores.setPreferredSize(new java.awt.Dimension(246, 38));
        cmbProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbProveedoresActionPerformed(evt);
            }
        });

        btnAgregarCarrito.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarCarrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregarCarrito.png"))); // NOI18N
        btnAgregarCarrito.setToolTipText("Agregar compra");
        btnAgregarCarrito.setOpaque(true);
        btnAgregarCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarCarritoMouseClicked(evt);
            }
        });

        btnEliminarCarrito.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarCarrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminarCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/eliminarCarrito.png"))); // NOI18N
        btnEliminarCarrito.setToolTipText("Eliminar compra");
        btnEliminarCarrito.setOpaque(true);
        btnEliminarCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarCarritoMouseClicked(evt);
            }
        });

        tbCompras.setModel(new javax.swing.table.DefaultTableModel(
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
        tbCompras.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tbCompras);

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Agregar compra");
        btnAgregar.setToolTipText("Administrar marcas");
        btnAgregar.setOpaque(true);
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotal.setText("0.00");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Total en compra:");
        jLabel3.setPreferredSize(new java.awt.Dimension(151, 38));

        txtISV.setEditable(false);
        txtISV.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtISV.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtISV.setText("0.00");

        jLabel4.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("ISV (15%):");

        txtImporte.setEditable(false);
        txtImporte.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtImporte.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Importe ISV (15%):");

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDescuento.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Descuento:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSubtotal.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Sub-Total:");

        jLabel6.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel6.setText("No. de factura");

        txtFactura.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtFactura.setForeground(new java.awt.Color(180, 180, 180));
        txtFactura.setText("12345...");

        btnAgregarProveedor.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarProveedor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarProveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregarAzul.png"))); // NOI18N
        btnAgregarProveedor.setToolTipText("Agregar nuevo proveedor");
        btnAgregarProveedor.setOpaque(true);
        btnAgregarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarProveedorMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel9.setText("Forma de pago:");

        rbPagada.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        rbPagada.setText("Contado");
        rbPagada.setEnabled(false);
        rbPagada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPagadaActionPerformed(evt);
            }
        });

        rbPendiente.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        rbPendiente.setText("Credito");
        rbPendiente.setEnabled(false);
        rbPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPendienteActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel10.setText("Fecha de compra real:");

        cmbDiaCompra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbDiaCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));

        cmbMesCompra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbMesCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        cmbAnioCompra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N

        cmbDiaVencimiento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbDiaVencimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" }));
        cmbDiaVencimiento.setEnabled(false);

        cmbMesVencimiento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbMesVencimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        cmbMesVencimiento.setEnabled(false);

        cmbAnioVencimiento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbAnioVencimiento.setEnabled(false);

        jLabel11.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel11.setText("Fecha vencimiento:");

        btnEditar.setBackground(new java.awt.Color(255, 255, 255));
        btnEditar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editarCompra.png"))); // NOI18N
        btnEditar.setToolTipText("Agregar compra");
        btnEditar.setOpaque(true);
        btnEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarMouseClicked(evt);
            }
        });

        btnEliminarTodo.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarTodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/eliminarTodo.png"))); // NOI18N
        btnEliminarTodo.setToolTipText("Eliminar compra");
        btnEliminarTodo.setOpaque(true);
        btnEliminarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarTodoMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Roboto", 0, 20)); // NOI18N
        jLabel12.setText("Codigo de barra");

        txtCodigoBarra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCodigoBarra.setForeground(new java.awt.Color(180, 180, 180));
        txtCodigoBarra.setText("871849329...");
        txtCodigoBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoBarraActionPerformed(evt);
            }
        });

        lbCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarProveedor)))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cmbDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbMesCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbAnioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rbPagada)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbPendiente))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cmbDiaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbMesVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAnioVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(btnAgregarCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarTodo))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtISV, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lbCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(rbPendiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbPagada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbMesCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAnioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cmbDiaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbMesVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbAnioVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCodigoBarra)))
                    .addComponent(btnEliminarCarrito, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAgregarCarrito, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtISV, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtDescuento)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        vc.insertBuy();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnAgregarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarCarritoMouseClicked
        Object[] values = Dialogs.ShowAddCompraDialog();
        if(values != null){
            vc.loadProduct(values);
            vc.updateTotal();
        }
    }//GEN-LAST:event_btnAgregarCarritoMouseClicked

    private void btnEliminarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarCarritoMouseClicked
        vc.deleteBuy();
    }//GEN-LAST:event_btnEliminarCarritoMouseClicked

    private void btnAgregarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarProveedorMouseClicked
        Dialogs.ShowAddProveedorDialog();
        vc.updateData();
    }//GEN-LAST:event_btnAgregarProveedorMouseClicked

    private void cmbProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbProveedoresActionPerformed
        if(cmbProveedores.getSelectedIndex() == 0){
            setEnableEstado(false);
        }else{
            setEnableEstado(true);
        }
    }//GEN-LAST:event_cmbProveedoresActionPerformed

    private void btnEditarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarMouseClicked
        vc.editItemValue();
    }//GEN-LAST:event_btnEditarMouseClicked

    private void btnEliminarTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarTodoMouseClicked
        vc.deleteAllBuys();
    }//GEN-LAST:event_btnEliminarTodoMouseClicked

    private void txtCodigoBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoBarraActionPerformed
        vc.loadbyBarCode();
    }//GEN-LAST:event_txtCodigoBarraActionPerformed

    private void rbPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPendienteActionPerformed
        setEnableDuaDate(true);
    }//GEN-LAST:event_rbPendienteActionPerformed

    private void rbPagadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPagadaActionPerformed
        setEnableDuaDate(false);
    }//GEN-LAST:event_rbPagadaActionPerformed

    private void setEnableEstado(boolean estado){
        rbPagada.setEnabled(estado);
        rbPendiente.setEnabled(estado);
        bgEstado.clearSelection();
    }
    
    private void setEnableDuaDate(boolean state){
        cmbDiaVencimiento.setEnabled(state);
        cmbMesVencimiento.setEnabled(state);
        cmbAnioVencimiento.setEnabled(state);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEstado;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnAgregarCarrito;
    private javax.swing.JLabel btnAgregarProveedor;
    private javax.swing.JLabel btnEditar;
    private javax.swing.JLabel btnEliminarCarrito;
    private javax.swing.JLabel btnEliminarTodo;
    private javax.swing.JComboBox<String> cmbAnioCompra;
    private javax.swing.JComboBox<String> cmbAnioVencimiento;
    private javax.swing.JComboBox<String> cmbDiaCompra;
    private javax.swing.JComboBox<String> cmbDiaVencimiento;
    private javax.swing.JComboBox<String> cmbMesCompra;
    private javax.swing.JComboBox<String> cmbMesVencimiento;
    private javax.swing.JComboBox<String> cmbProveedores;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCargando;
    private javax.swing.JRadioButton rbPagada;
    private javax.swing.JRadioButton rbPendiente;
    private javax.swing.JTable tbCompras;
    private javax.swing.JTextField txtCodigoBarra;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtISV;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
