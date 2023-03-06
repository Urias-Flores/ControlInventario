package Views.Panels.Facturacion;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Facturacion.FacturaViewController;
import java.awt.Color;

public class Facturar extends javax.swing.JPanel {

    private FacturaViewController vc;

    public Facturar() {
        initComponents();

        btnAgregarCliente.addMouseListener(Utilities.getMLGrayButton());
        btnAgregarCarrito.addMouseListener(Utilities.getMLGrayButton());
        btnEditarCarrito.addMouseListener(Utilities.getMLGrayButton());
        btnEliminarCarrito.addMouseListener(Utilities.getMLGrayButton());
        btnEliminarTodo.addMouseListener(Utilities.getMLGrayButton());

        btnAgregar.addMouseListener(Utilities.getMLGeneralButton());
        btnSolicitar.addMouseListener(Utilities.getMLGeneralButton());
        btnCotizar.addMouseListener(Utilities.getMLGeneralButton());
        btnIniciarDia.addMouseListener(Utilities.getMLGeneralButton());

        txtRTN.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtBarra.addFocusListener(Utilities.getFLPlaceHolderEfect());
        txtNoCotizacion.addFocusListener(Utilities.getFLPlaceHolderEfect());

        vc = new FacturaViewController(lbCargando, cmbCliente, txtRTN,
                cmbFormaPago, txtBarra,
                txtNoCotizacion, tbVentas, txtSubtotal,
                txtDescuento, txtImporte, txtISV, txtTotal, btnIniciarDia);
        
        //Para garantizar el funcionamiento pre-solicitud
        btnSolicitar.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgEstado = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtRTN = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cmbCliente = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVentas = new javax.swing.JTable();
        btnAgregar = new javax.swing.JLabel();
        txtDescuento = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSubtotal = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtISV = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtImporte = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnAgregarCarrito = new javax.swing.JLabel();
        btnEliminarCarrito = new javax.swing.JLabel();
        btnAgregarCliente = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtBarra = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btnCotizar = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNoCotizacion = new javax.swing.JTextField();
        btnEditarCarrito = new javax.swing.JLabel();
        btnEliminarTodo = new javax.swing.JLabel();
        lbCargando = new javax.swing.JLabel();
        btnIniciarDia = new javax.swing.JLabel();
        btnSolicitar = new javax.swing.JLabel();
        cmbFormaPago = new javax.swing.JComboBox<>();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Roboto", 1, 28)); // NOI18N
        jLabel2.setText("Facturar venta");

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel6.setText("RTN de cliente:");

        txtRTN.setEditable(false);
        txtRTN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtRTN.setForeground(new java.awt.Color(180, 180, 180));
        txtRTN.setText("Seleccione un cliente...");
        txtRTN.setPreferredSize(new java.awt.Dimension(148, 38));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setText("Cliente:");

        cmbCliente.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-- Seleccione cliente --" }));
        cmbCliente.setPreferredSize(new java.awt.Dimension(246, 38));
        cmbCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbClienteActionPerformed(evt);
            }
        });

        tbVentas.setModel(new javax.swing.table.DefaultTableModel(
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
        tbVentas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tbVentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tbVentas.setShowHorizontalLines(true);
        jScrollPane1.setViewportView(tbVentas);

        btnAgregar.setBackground(new java.awt.Color(3, 57, 103));
        btnAgregar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnAgregar.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregar.setText("Facturar");
        btnAgregar.setToolTipText("Administrar marcas");
        btnAgregar.setOpaque(true);
        btnAgregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarMouseClicked(evt);
            }
        });

        txtDescuento.setEditable(false);
        txtDescuento.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDescuento.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Descuento:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSubtotal.setText("0.00");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotal.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Sub-Total:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Total:");

        txtISV.setEditable(false);
        txtISV.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtISV.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtISV.setText("0.00");

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("ISV (15%):");

        txtImporte.setEditable(false);
        txtImporte.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtImporte.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setText("Importe ISV (15%):");

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

        btnAgregarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnAgregarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnAgregarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/agregarAzul.png"))); // NOI18N
        btnAgregarCliente.setToolTipText("Agregar proveedor");
        btnAgregarCliente.setOpaque(true);
        btnAgregarCliente.setPreferredSize(new java.awt.Dimension(38, 38));
        btnAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarClienteMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel9.setText("Forma de pago:");

        txtBarra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtBarra.setForeground(new java.awt.Color(180, 180, 180));
        txtBarra.setText("9817329732...");
        txtBarra.setPreferredSize(new java.awt.Dimension(148, 38));
        txtBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarraActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel10.setText("Codigo de barras");

        btnCotizar.setBackground(new java.awt.Color(3, 57, 103));
        btnCotizar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnCotizar.setForeground(new java.awt.Color(255, 255, 255));
        btnCotizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnCotizar.setText("Imprimir cotizacion");
        btnCotizar.setToolTipText("Administrar marcas");
        btnCotizar.setOpaque(true);
        btnCotizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCotizarMouseClicked(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel11.setText("Cargar cotizacion:");
        jLabel11.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        txtNoCotizacion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtNoCotizacion.setForeground(new java.awt.Color(180, 180, 180));
        txtNoCotizacion.setText("Escriba el No. de cotizacion...");
        txtNoCotizacion.setPreferredSize(new java.awt.Dimension(148, 38));
        txtNoCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoCotizacionActionPerformed(evt);
            }
        });

        btnEditarCarrito.setBackground(new java.awt.Color(255, 255, 255));
        btnEditarCarrito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEditarCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/editarCompra.png"))); // NOI18N
        btnEditarCarrito.setToolTipText("Editar compra");
        btnEditarCarrito.setOpaque(true);
        btnEditarCarrito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEditarCarritoMouseClicked(evt);
            }
        });

        btnEliminarTodo.setBackground(new java.awt.Color(255, 255, 255));
        btnEliminarTodo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnEliminarTodo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/eliminarTodo.png"))); // NOI18N
        btnEliminarTodo.setToolTipText("Eliminar todo");
        btnEliminarTodo.setOpaque(true);
        btnEliminarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarTodoMouseClicked(evt);
            }
        });

        lbCargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnIniciarDia.setBackground(new java.awt.Color(3, 57, 103));
        btnIniciarDia.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnIniciarDia.setForeground(new java.awt.Color(255, 255, 255));
        btnIniciarDia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnIniciarDia.setText("Iniciar dia");
        btnIniciarDia.setToolTipText("Administrar marcas");
        btnIniciarDia.setOpaque(true);
        btnIniciarDia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnIniciarDiaMouseClicked(evt);
            }
        });

        btnSolicitar.setBackground(new java.awt.Color(3, 57, 103));
        btnSolicitar.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        btnSolicitar.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnSolicitar.setText("Solicitar compra");
        btnSolicitar.setToolTipText("Administrar marcas");
        btnSolicitar.setOpaque(true);
        btnSolicitar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSolicitarMouseClicked(evt);
            }
        });

        cmbFormaPago.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        cmbFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Contado", "Credito", "Tarjeta o Deposito" }));
        cmbFormaPago.setEnabled(false);
        cmbFormaPago.setPreferredSize(new java.awt.Dimension(246, 38));
        cmbFormaPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFormaPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cmbCliente, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRTN, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbFormaPago, 0, 258, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBarra, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregarCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditarCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarCarrito)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarTodo))
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1371, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnIniciarDia, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCotizar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNoCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDescuento, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtISV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEliminarCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditarCarrito)
                                    .addComponent(btnAgregarCarrito))
                                .addComponent(btnEliminarTodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cmbFormaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtImporte, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtSubtotal)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtISV, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDescuento)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCotizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnIniciarDia, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSolicitar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarMouseClicked
        vc.InsertSale();
    }//GEN-LAST:event_btnAgregarMouseClicked

    private void btnAgregarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarCarritoMouseClicked
        Object[] values = Dialogs.ShowAddVentaDialog();
        if (values != null && values.length > 0) {
            vc.loadProduct(values);
        }
    }//GEN-LAST:event_btnAgregarCarritoMouseClicked

    private void btnEliminarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarCarritoMouseClicked
        vc.deleteSale();
    }//GEN-LAST:event_btnEliminarCarritoMouseClicked

    private void btnAgregarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAgregarClienteMouseClicked
        vc.addClient();
    }//GEN-LAST:event_btnAgregarClienteMouseClicked

    private void cmbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClienteActionPerformed
        switch (cmbCliente.getSelectedIndex()) {
            case 0:
                txtRTN.setText("Seleccione un cliente...");
                txtRTN.setForeground(new Color(180, 180 , 180));
                cmbFormaPago.setEnabled(false);
                break;
            case 1:
                txtRTN.setText("Sin RTN");
                txtRTN.setForeground(new Color(180, 180 , 180));
                cmbFormaPago.setEnabled(false);
                break;
            default:
                vc.setRTN();
                cmbFormaPago.setEnabled(true);
                break;
        }
    }//GEN-LAST:event_cmbClienteActionPerformed

    private void txtBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBarraActionPerformed
        vc.loadProductByBarCode();
    }//GEN-LAST:event_txtBarraActionPerformed

    private void btnCotizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCotizarMouseClicked
        vc.InsertQuote();
    }//GEN-LAST:event_btnCotizarMouseClicked

    private void txtNoCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoCotizacionActionPerformed
        vc.loadQuote();
    }//GEN-LAST:event_txtNoCotizacionActionPerformed

    private void btnEditarCarritoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarCarritoMouseClicked
        vc.editItemValues();
    }//GEN-LAST:event_btnEditarCarritoMouseClicked

    private void btnEliminarTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarTodoMouseClicked
        vc.deleteAllSales();
    }//GEN-LAST:event_btnEliminarTodoMouseClicked

    private void btnIniciarDiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnIniciarDiaMouseClicked
        vc.initDay();
    }//GEN-LAST:event_btnIniciarDiaMouseClicked

    private void btnSolicitarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSolicitarMouseClicked
        vc.InsertRequest();
    }//GEN-LAST:event_btnSolicitarMouseClicked

    private void cmbFormaPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFormaPagoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbFormaPagoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEstado;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnAgregarCarrito;
    private javax.swing.JLabel btnAgregarCliente;
    private javax.swing.JLabel btnCotizar;
    private javax.swing.JLabel btnEditarCarrito;
    private javax.swing.JLabel btnEliminarCarrito;
    private javax.swing.JLabel btnEliminarTodo;
    private javax.swing.JLabel btnIniciarDia;
    private javax.swing.JLabel btnSolicitar;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTable tbVentas;
    private javax.swing.JTextField txtBarra;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtISV;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNoCotizacion;
    private javax.swing.JTextField txtRTN;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
