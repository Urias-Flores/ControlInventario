package Views.Panels.Facturacion;

import Resource.Utilities;
import Views.Dialogs.Dialogs;
import ViewsControllers.Panels.Facturacion.FacturaViewController;
import java.util.Arrays;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Facturar extends javax.swing.JPanel {

    private FacturaViewController vc;

    public Facturar() {
        initComponents(); 
        
        //Agregando efectos a botones y placeholders a campos de escritura
        List<JLabel> grayButtons = Arrays.asList(btnBuscarCliente, btnAgregarCliente, btnAgregarCarrito, btnEditarCarrito, btnEliminarCarrito, btnEliminarTodo);
        grayButtons.forEach(button -> {button.addMouseListener(Utilities.getMLGrayButton());});
        List<JLabel> generalButton = Arrays.asList(btnAgregar, btnSolicitar, btnCotizar, btnIniciarDia);
        generalButton.forEach(button -> {button.addMouseListener(Utilities.getMLGeneralButton());});
        List<JTextField> fields = Arrays.asList(txtRTN, txtBarra, txtNoCotizacion);
        fields.forEach(field -> {field.addFocusListener(Utilities.getFLPlaceHolderEfect());});

        vc = new FacturaViewController(lbCargando, txtCliente, txtRTN,
                cmbFormaPago, txtBarra,
                txtNoCotizacion, tbVentas, txtSubtotal,
                txtDescuento, txtImporte, txtISV, txtTotal, btnIniciarDia);
        addDesing();
    }
    
    private void addDesing(){
        txtBarra.putClientProperty("JTextField.placeholderText", "Ingrese el código de barras...");
        txtCliente.putClientProperty("JTextField.placeholderText", "Seleccione un cliente...");
        txtRTN.putClientProperty("JTextField.placeholderText", "Seleccione un cliente...");
        txtNoCotizacion.putClientProperty("JTextField.placeholderText", "Ingrese el número de cotización...");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgEstado = new javax.swing.ButtonGroup();
        jLabel6 = new javax.swing.JLabel();
        txtRTN = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
        btnBuscarCliente = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(90, 90, 90));
        jLabel6.setText("RTN de cliente");

        txtRTN.setEditable(false);
        txtRTN.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtRTN.setForeground(new java.awt.Color(180, 180, 180));
        txtRTN.setText("Seleccione un cliente...");
        txtRTN.setPreferredSize(new java.awt.Dimension(148, 38));

        jLabel1.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(90, 90, 90));
        jLabel1.setText("Cliente");

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
        txtDescuento.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        txtDescuento.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtDescuento.setText("0.00");

        jLabel7.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(90, 90, 90));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setText("Descuento:");

        txtSubtotal.setEditable(false);
        txtSubtotal.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        txtSubtotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtSubtotal.setText("0.00");

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtTotal.setText("0.00");

        jLabel8.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(90, 90, 90));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setText("Sub-Total:");

        jLabel3.setFont(new java.awt.Font("Roboto", 1, 26)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(90, 90, 90));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel3.setText("Total:");

        txtISV.setEditable(false);
        txtISV.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        txtISV.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtISV.setText("0.00");
        txtISV.setMinimumSize(new java.awt.Dimension(80, 38));

        jLabel4.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(90, 90, 90));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel4.setText("ISV (15%):");

        txtImporte.setEditable(false);
        txtImporte.setFont(new java.awt.Font("Roboto", 0, 22)); // NOI18N
        txtImporte.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        txtImporte.setText("0.00");

        jLabel5.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(90, 90, 90));
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
        btnAgregarCliente.setToolTipText("Agregar cliente");
        btnAgregarCliente.setOpaque(true);
        btnAgregarCliente.setPreferredSize(new java.awt.Dimension(38, 38));
        btnAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAgregarClienteMouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(90, 90, 90));
        jLabel9.setText("Forma de pago");

        txtBarra.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtBarra.setPreferredSize(new java.awt.Dimension(148, 38));
        txtBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBarraActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(90, 90, 90));
        jLabel10.setText("Código de barras");

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
        jLabel11.setForeground(new java.awt.Color(90, 90, 90));
        jLabel11.setText("Cargar cotización:");

        txtNoCotizacion.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtNoCotizacion.setForeground(new java.awt.Color(180, 180, 180));
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

        jLabel12.setFont(new java.awt.Font("Roboto", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(90, 90, 90));
        jLabel12.setText("Realizar cambios");

        btnBuscarCliente.setBackground(new java.awt.Color(255, 255, 255));
        btnBuscarCliente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnBuscarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/buscarEnLista.png"))); // NOI18N
        btnBuscarCliente.setToolTipText("Buscar cliente");
        btnBuscarCliente.setOpaque(true);
        btnBuscarCliente.setPreferredSize(new java.awt.Dimension(38, 38));
        btnBuscarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBuscarClienteMouseClicked(evt);
            }
        });

        txtCliente.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnAgregarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtRTN, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregarCarrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEditarCarrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarCarrito)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEliminarTodo))
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1)
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
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 272, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnEliminarTodo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEditarCarrito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAgregarCarrito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnEliminarCarrito)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCliente)))))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtRTN, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cmbFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNoCotizacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtImporte, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescuento, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(txtISV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTotal))
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

    private void btnBuscarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBuscarClienteMouseClicked
        vc.loadClient();
    }//GEN-LAST:event_btnBuscarClienteMouseClicked

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        vc.verifyClienteSelected();
    }//GEN-LAST:event_txtClienteKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgEstado;
    private javax.swing.JLabel btnAgregar;
    private javax.swing.JLabel btnAgregarCarrito;
    private javax.swing.JLabel btnAgregarCliente;
    private javax.swing.JLabel btnBuscarCliente;
    private javax.swing.JLabel btnCotizar;
    private javax.swing.JLabel btnEditarCarrito;
    private javax.swing.JLabel btnEliminarCarrito;
    private javax.swing.JLabel btnEliminarTodo;
    private javax.swing.JLabel btnIniciarDia;
    private javax.swing.JLabel btnSolicitar;
    private javax.swing.JComboBox<String> cmbFormaPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtISV;
    private javax.swing.JTextField txtImporte;
    private javax.swing.JTextField txtNoCotizacion;
    private javax.swing.JTextField txtRTN;
    private javax.swing.JTextField txtSubtotal;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
