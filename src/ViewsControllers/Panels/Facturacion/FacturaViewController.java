package ViewsControllers.Panels.Facturacion;

import Controllers.ClienteJpaController;
import Controllers.CotizacionJpaController;
import Controllers.CotizaciondetalleJpaController;
import Controllers.ProductoJpaController;
import Controllers.SolicitudJpaController;
import Controllers.SolicituddetalleJpaController;
import Controllers.VentaJpaController;
import Controllers.VentadetalleJpaController;
import Models.Cliente;
import Models.Cotizacion;
import Models.Cotizaciondetalle;
import Models.Producto;
import Models.Solicitud;
import Models.Solicituddetalle;
import Models.Venta;
import Models.Ventadetalle;
import Reports.Reports;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FacturaViewController {

    private VentaJpaController controller;
    private CotizacionJpaController controllerCotizacion;
    private SolicitudJpaController controllerSolicitud;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    private JComboBox<Cliente> Clientes;
    private JTextField RTN;

    private JComboBox FormaPago;

    private JTextField Barra;
    private JTextField Cotizacion;

    private JTable Ventas;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;

    private JLabel dayState;
    private JLabel Cargando;

    private boolean isDayInit = false;

    public FacturaViewController(JLabel Cargando, JComboBox Clientes, JTextField RTN, JComboBox FormaPago, JTextField Barra, JTextField Cotizacion, JTable Ventas, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel dayState) {
        this.Cargando = Cargando;
        this.dayState = dayState;
        this.Clientes = Clientes;
        this.RTN = RTN;
        this.FormaPago = FormaPago;
        this.Barra = Barra;
        this.Cotizacion = Cotizacion;
        this.Ventas = Ventas;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;

        controller = new VentaJpaController(Conection.createEntityManagerFactory());
        controllerCotizacion = new CotizacionJpaController(Conection.createEntityManagerFactory());
        controllerSolicitud = new SolicitudJpaController(Conection.createEntityManagerFactory());
        
        //Cargando modelo de tabla para factura
        setModelTable();

        //Verificando la inicializacion de dia
        verifyDayInit();

        //Inicializando carga de datos
        Init();
    }

    private void setLoad(boolean state) {
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }

    private void Init() {
        setLoad(true);
        Runnable run = () -> {
            loadClients();
            setLoad(false);
        };
        new Thread(run).start();
    }

    private void setModelTable() {
        String[] columns = {"Codigo", "Producto", "Unidades", "Cantidad", "Precio", "Descuento", "Subtotal"};
        model.setColumnIdentifiers(columns);

        Ventas.setModel(model);
        Ventas.getColumn("Codigo").setPreferredWidth(60);
        Ventas.getColumn("Producto").setPreferredWidth(710);
        Ventas.getColumn("Unidades").setPreferredWidth(70);
        Ventas.getColumn("Cantidad").setPreferredWidth(70);
        Ventas.getColumn("Precio").setPreferredWidth(120);
        Ventas.getColumn("Descuento").setPreferredWidth(120);
        Ventas.getColumn("Subtotal").setPreferredWidth(120);
    }

    private void verifyDayInit() {
        LocalDataController ldc = new LocalDataController();
        //Verificando si el dia esta iniciado para actualizacion de estado local y modificacion de texto de boton
        if (ldc.validateInitDay()) {
            this.isDayInit = true;
            dayState.setText("Realizar cierre");
        } else {
            this.isDayInit = false;
            dayState.setText("Iniciar dia");
        }
    }

    public void initDay() {
        //Verificando si el dia esta o no, iniciado para mostrar dialogo de cierre o de inicio de dia respectivamente
        if (!isDayInit) {
            Dialogs.ShowInitDayDialog();
        } else {
            Dialogs.ShowCloseDayDialog();
        }
        //Enviando a actualizar estado de dia iniciado
        verifyDayInit();
    }

    private void loadClients() {
        //Agregando unicamente comsumidor final para que este siempre al inicio de la lista
        Cliente ConsumidorFinal = new ClienteJpaController(Conection.createEntityManagerFactory()).findCliente(1);
        Clientes.addItem(ConsumidorFinal);
        
        //Agregando los demas clientes en orden alfabetico
        List<Cliente> clientes = Conection.createEntityManager().createNamedQuery("Cliente.findAll").getResultList();
        clientes.forEach(cliente -> {
            //Verifificando que el cliente comsumidor final no se vualva agregar
            if (cliente.getClienteID() != 1) {
                Clientes.addItem(cliente);
            }
        });
    }

    public void addClient() {
        //Mostrando dialogo para agregar un nuevo cliente y luego actualizando lista de clientes
        Dialogs.ShowAddClienteDialog();
        Clientes.removeAllItems();
        Clientes.addItem(new Cliente(0, "-- Seleccione cliente --", 0));
        Init();
    }
    
    public void setRTN(){
        //Modificando campo de RTN  segun RTN del cliente seleccionado
        if(Clientes.getSelectedIndex() >= 2 ){
            Cliente cliente = (Cliente) Clientes.getSelectedItem();
            RTN.setText(cliente.getRtn() != null ? cliente.getRtn() : "Cliente sin RTN");
            RTN.setForeground(cliente.getRtn() != null ? Color.black : new Color(180, 180, 180));
        }
    }

    /**
     * 
     * @param values recibe una arreglo con los datos de un item de compra 
     * como [ProductoID, Nombre, Unidad, Cantidad, Precio, Descuento y subtotal]
     */
    public void loadProduct(Object[] values) {
        boolean NoexistRow = true;
        /*
        Recorriendo lista de productos a agregar a la factura provenientes de la carga de una cotizacion o 
        la carga de producto por medio de codigo de barras, tomando en cuanta la posibilidad de que el producto ya exista en
        la lista de productos ya agregados
        */
        
        //En caso de que exista el producto se agrega la cantidad extra en la lista
        for (int i = 0; i < Ventas.getRowCount(); i++) {
            if (Ventas.getValueAt(i, 0).toString().equals(values[0].toString())) {
                float cantidadNew = Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", "")) + Float.parseFloat(values[3].toString().replace(",", ""));
                float descuentoNew = Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", "")) + Float.parseFloat(values[5].toString().replace(",", ""));
                float SubtotalNew = Float.parseFloat(Ventas.getValueAt(i, 6).toString().replace(",", "")) + Float.parseFloat(values[6].toString().replace(",", ""));
                Ventas.setValueAt(getNumberFormat(cantidadNew), i, 3);
                Ventas.setValueAt(getNumberFormat(descuentoNew), i, 5);
                Ventas.setValueAt(getNumberFormat(SubtotalNew), i, 6);
                NoexistRow = false;
            }
        }
        
        //En caso de que el producto no exista se agrega a la lista
        if (NoexistRow) {
            model.addRow(values);
        }
        updateTotal();
    }

    public void editItemValues() {
        //Editando elemento de las lista de productos agregados a la factura
        int fila = Ventas.getSelectedRow();
        if (fila >= 0) {
            //Creando arreglo para la edicion en del producto en dialogo de edicion
            Object[] values = {
                Ventas.getValueAt(fila, 0).toString(),
                Ventas.getValueAt(fila, 1).toString(),
                Ventas.getValueAt(fila, 2).toString(),
                Ventas.getValueAt(fila, 3).toString(),
                Ventas.getValueAt(fila, 4).toString(),
                Ventas.getValueAt(fila, 5).toString(),};
            Object[] newValues = Dialogs.ShowEditVentaDialog(values);

            //Editando valores de las lista
            if (newValues != null && newValues[0] != null) {
                Ventas.setValueAt(getNumberFormat(Float.parseFloat(newValues[0].toString())), fila, 3);
                Ventas.setValueAt(getNumberFormat(Float.parseFloat(newValues[1].toString())), fila, 5);
                Ventas.setValueAt(getNumberFormat(Float.parseFloat(newValues[2].toString())), fila, 6);
                updateTotal();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione un producto de la lista", Dialogs.ERROR_ICON);
        }
    }

    //Task
    public void loadProductByBarCode() {
        setLoad(true);
        Runnable run = () -> {
            //Buscando en la lista de productos por medio del codigo de barras
            //Obteniendo y guardando en una lista los productos que coincidan con el codigo de barra indicado
            List<Producto> productos = Conection.createEntityManager()
                .createNamedQuery("Producto.findByBarra")
                .setParameter("barra", Barra.getText())
                .getResultList();

            //Verificando que la lista no este vacia
            if(!productos.isEmpty()){
                //Obteniendo el primer producto de la lista
                Producto producto = productos.get(0);
                /*
                Verificando nuevamente la coincidencia del codigo de barra del producto
                con el codigo de barra ingresado
                */
                if (producto.getBarra().equals(Barra.getText())) {
                    Object[] row = {
                        producto.getProductoID(),
                        producto.getDescripcion(),
                        producto.getUnidad(),
                        getNumberFormat(1f),
                        getNumberFormat(producto.getPrecioVenta()),
                        getNumberFormat(0f),
                        getNumberFormat(producto.getPrecioVenta())
                    };

                    //Obteniendo la cantidad del producto de inventario
                    Query query = Conection.createEntityManager()
                            .createNativeQuery("SELECT cantidad FROM inventario WHERE ProductoID = " + producto.getProductoID());
                    List values = query.getResultList();

                    float existenciaProducto = Float.parseFloat(values.get(0).toString());
                    if (existenciaProducto <= 0) {
                        Dialogs.ShowMessageDialog("El producto no cuenta con existencia en inventario", Dialogs.ERROR_ICON);
                    //En caso de que exista el producto en inventario lo agrega a la lista de productos
                    } else {
                        loadProduct(row);
                    }
                }
            }
            Barra.setText("");
            setLoad(false);
        };
        new Thread(run).start();
    }

    //Task
    public void loadQuote() {
        if ( validateQuote() ) {
            setLoad(true);
            Runnable run = () -> {
                //Buscando cotizacion por Numero de cotizacion ingresado
                int currentCotizacionID = Integer.parseInt(Cotizacion.getText());
                Cotizacion cotizacion = new CotizacionJpaController(Conection.createEntityManagerFactory())
                        .findCotizacion(currentCotizacionID);
                
                //En caso de que la cotizacion exista
                if(cotizacion != null){
                    //Obteniendo detalles de la cotizacion
                    List<Cotizaciondetalle> cotizacionDetalles = cotizacion.getCotizaciondetalleList();
                    
                    //Recorriendo detalles de la cotizacion para agregar productos a la factura
                    cotizacionDetalles.forEach(cotizacionDetalle -> {
                        Producto producto = new ProductoJpaController(Conection.createEntityManagerFactory())
                                .findProducto(cotizacionDetalle.getProductoID().getProductoID());
                        Object[] row = {
                            cotizacionDetalle.getProductoID().getProductoID(),
                            cotizacionDetalle.getProductoID().getDescripcion(),
                            cotizacionDetalle.getProductoID().getUnidad(),
                            getNumberFormat(cotizacionDetalle.getCantidad()),
                            getNumberFormat(producto.getPrecioVenta()),
                            getNumberFormat(cotizacionDetalle.getDescuento()),
                            getNumberFormat(cotizacionDetalle.getCantidad() * producto.getPrecioVenta() - cotizacionDetalle.getDescuento())
                        };
                        loadProduct(row);
                    }); 
                }
                Cotizacion.setText("");
                setLoad(false);
            };
            new Thread(run).start();
        }
    }

    private boolean validateQuote() {
        //Validando que el campo no este vacio
        if (Cotizacion.getText().isEmpty() || Cotizacion.getForeground().equals(new Color(180, 180, 180))) {
            Dialogs.ShowMessageDialog("Introduzca el numero de cotizacion", Dialogs.ERROR_ICON);
            return false;
        }
        
        //Validando tipo de dato del numero de cotizacion
        try {
            int NoCotizacion = Integer.parseInt(Cotizacion.getText());
            
            //Validando que el numero de cotizaion sea mayor a 0
            if(NoCotizacion < 0){
                Dialogs.ShowMessageDialog("El Numero de cotizacion debe ser mayor a 0", Dialogs.ERROR_ICON);
                return false;
            }
        } catch (NumberFormatException ex) {
            Dialogs.ShowMessageDialog("El Numero de cotizacion debe ser un numero", Dialogs.ERROR_ICON);
            return false;
        }
        return true;
    }

    public void deleteSale() {
        int fila = Ventas.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowOKCancelDialog("¿Desea eliminar la venta seleccionada de la factura?", Dialogs.WARNING_ICON)) {
                model.removeRow(fila);
                updateTotal();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccion una venta de la lista", Dialogs.ERROR_ICON);
        }
    }

    public void deleteAllSales() {
        if (model.getRowCount() > 0) {
            if (Dialogs.ShowOKCancelDialog("¿Desea eliminar todas la ventas de la factura?", Dialogs.WARNING_ICON)) {
                model.setRowCount(0);
                updateTotal();
            }
        }
    }

    public void updateTotal() {
        int numberRows = Ventas.getRowCount();
        
        //Verificando que la tabla no este vacia
        if (numberRows > 0) {
            //Incializando valores en 0
            float subtotal = 0;
            float descuento = 0;

            for (int i = 0; i < numberRows; i++) {
                //Obteniendo valores de la tabla
                float cantidad = Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", ""));
                float precio = Float.parseFloat(Ventas.getValueAt(i, 4).toString().replace(",", ""));
                float descuentoUnitario = Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", ""));

                //Realizando la sumatoria de los valores
                subtotal += cantidad * precio;
                descuento += descuentoUnitario;
            }
            
            //Calculando valores de importe, impuesto y el total
            float importe = (subtotal - descuento) / 1.15f;
            float isv = importe * 0.15f;
            float total = importe + isv;

            //Escribiendo valores resultando en campos
            Subtotal.setText(getNumberFormat(subtotal));
            Descuento.setText(getNumberFormat(descuento));
            Importe.setText(getNumberFormat(importe));
            ISV.setText(getNumberFormat(isv));
            Total.setText(getNumberFormat(total));
        
        //En caso de que la tabla este vacia todo se setea en 0
        } else {
            Subtotal.setText(getNumberFormat(0));
            Descuento.setText(getNumberFormat(0));
            Importe.setText(getNumberFormat(0));
            ISV.setText(getNumberFormat(0));
            Total.setText(getNumberFormat(0));
        }
    }

    public void InsertSale() {
        //Validando venta
        if (validate(false)) {
            if (isDayInit) {
                setLoad(true);
                Runnable run = () -> {
                    //Insertando venta
                    //Obteniendo ID de venta en caso de que exista algun factura vacio
                    int VentaID = validateEmptySale();
                    Venta venta = createObjectSale();
                    if(VentaID == 0){
                        //En caso de que no exista una venta vacia creara una nueva
                        try {
                            venta.setVentaID(controller.create(venta));
                        } catch (Exception e) {
                            Dialogs.ShowMessageDialog("Ha ocurrido un error inesperado.", Dialogs.ERROR_ICON);
                            return;
                        }
                    }
                    
                    //Insertando detalles de la factura
                    List<Ventadetalle> ventas = createListSaleDetails(VentaID != 0 ? VentaID : venta.getVentaID());
                    VentadetalleJpaController ventadetalleJpaController = new VentadetalleJpaController(Conection.createEntityManagerFactory());
                    ventas.forEach(ventadetalleJpaController::create);

                    //Agregando informacion de arqueo
                    float total = Float.parseFloat(Total.getText().replace(",", ""));
                    Map<Integer, String> formaPago = new HashMap<>();
                    formaPago.put(0, "CN"); //Contado
                    formaPago.put(1, "CR"); //Credito
                    formaPago.put(2, "TD"); //Tarjeta o deposito
                    float efectivo = Dialogs.ShowArqueoDialog(VentaID != 0 ? VentaID : venta.getVentaID(), formaPago.get(FormaPago.getSelectedIndex()), "V", total);

                    setLoad(false);
                    Dialogs.ShowMessageDialog("La factura ha sido ingresada exitosamente", Dialogs.COMPLETE_ICON);

                    //Enviando a imprimir ticket de venta
                    if (Dialogs.ShowOKCancelDialog("¿Desea enviar a imprimir la factura ahora?", Dialogs.WARNING_ICON)) {
                        setLoad(true);
                        Runnable runnable = () -> {
                            Reports reports = new Reports();
                            reports.GenerateTickeVenta(VentaID != 0 ? VentaID : venta.getVentaID(), efectivo);
                            setLoad(false);
                        };
                        new Thread(runnable).start();
                    }
                    clear();
                    setLoad(false);
                };
                new Thread(run).start();
            } else {
                Dialogs.ShowMessageDialog("El día de facturaciín aún no ha sido iniciado", Dialogs.ERROR_ICON);
            }
        }
    }

    public void InsertRequest() {
        if (validate(false)) {
            if (isDayInit) {
                setLoad(true);
                Runnable run = () -> {
                    //Insertando solicitud
                    Solicitud solicitud = createObjectRequest();
                    int SolicitudID = controllerSolicitud.create(solicitud);

                    //Insertando detalles de la factura
                    List<Solicituddetalle> solicitudes = createListRequestDetails(SolicitudID);
                    SolicituddetalleJpaController solicituddetalleJpaController = new SolicituddetalleJpaController(Conection.createEntityManagerFactory());
                    solicitudes.forEach(solicituddetalleJpaController::create);

                    //Agregando informacion de arqueo
                    float total = Float.parseFloat(Total.getText().replace(",", ""));
                    Map<Integer, String> formaPago = new HashMap<>();
                    formaPago.put(0, "CN"); //Contado
                    formaPago.put(1, "CR"); //Credito
                    formaPago.put(2, "TD"); //Tarjeta o deposito
                    float efectivo = Dialogs.ShowArqueoDialog(SolicitudID, formaPago.get(FormaPago.getSelectedIndex()), "S", total);

                    setLoad(false);
                    Dialogs.ShowMessageDialog("La factura ha sido ingresada exitosamente", Dialogs.COMPLETE_ICON);

                    //Enviando a imprimir ticket de solicitud
                    if (Dialogs.ShowOKCancelDialog("¿Desea enviar a imprimir la factura ahora?", Dialogs.WARNING_ICON)) {
                        setLoad(true);
                        Runnable runnable = () -> {
                            Reports reports = new Reports();
                            reports.GenerateTicketSolicitud(SolicitudID, efectivo);
                            setLoad(false);
                        };
                        new Thread(runnable).start();
                    }
                    clear();
                    setLoad(false);
                };
                new Thread(run).start();
            } else {
                Dialogs.ShowMessageDialog("El dia de facturacion aun no ha sido iniciado", Dialogs.ERROR_ICON);
            }
        }
    }

    public void InsertQuote() {
        if (validate(true)) {

            setLoad(true);
            Runnable run = () -> {
                //Insertando cotizacion
                Cotizacion cotizacion = createObjectQuote();
                int CotizacionID = controllerCotizacion.create(cotizacion);

                //Insertanto detalles de cotizacion
                List<Cotizaciondetalle> cotizaciondetalles = createListQuoteDetails(CotizacionID);
                CotizaciondetalleJpaController cotizaciondetalleJpaController = new CotizaciondetalleJpaController(Conection.createEntityManagerFactory());
                cotizaciondetalles.forEach(cotizaciondetalleJpaController::create);

                //Enviando a imprimir ticket de cotizacion
                setLoad(true);
                Runnable runnable = () ->{
                    Reports reports = new Reports();
                    reports.GenerateTicketCotizacion(CotizacionID);
                    setLoad(false);
                };
                new Thread(runnable).start();
                clear();
                setLoad(false);
            };
            new Thread(run).start();
            
        }
    }

    /**
     * Crea un objeto de tipo Venta
     * @return retorna el objeto de tipo <b>Venta</b> con todos los datos agregados en la vista
     */
    private Venta createObjectSale() {
        Venta venta = new Venta();

        venta.setRtn(RTN.getForeground().equals(Color.BLACK) && !RTN.getText().isEmpty() ? RTN.getText() : null);
        venta.setClienteID((Cliente) Clientes.getSelectedItem());
        if (Clientes.getSelectedIndex() < 2) {
            venta.setEstado("P");
        } else {
            venta.setEstado(FormaPago.getSelectedIndex() == 0 || FormaPago.getSelectedIndex() == 2 ? "P" : "N");
        }
        venta.setUsuarioID(Utilities.getUsuarioActual());
        venta.setFecha(Utilities.getDate());
        venta.setHora(Utilities.getTime());

        return venta;
    }

    /**
     * @param SolicitudID recibe el ID de la venta para agregarlo a la lista de productos agregados en la tabla
     * @return retorna la lista de productos agregados a la tabla como una lista de objetos
     */
    private List<Ventadetalle> createListSaleDetails(int VentaID) {
        ArrayList<Ventadetalle> list = new ArrayList<>();
        for (int i = 0; i < Ventas.getRowCount(); i++) {
            Ventadetalle ventaDetalle = new Ventadetalle();

            ventaDetalle.setVentaID(new Venta(VentaID));
            ventaDetalle.setProductoID(new Producto(Integer.valueOf(Ventas.getValueAt(i, 0).toString())));
            ventaDetalle.setCantidad(Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", "")));
            ventaDetalle.setPrecio(Float.parseFloat(Ventas.getValueAt(i, 4).toString().replace(",", "")));
            ventaDetalle.setDescuento(Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", "")));
            ventaDetalle.setIsv(0.15f);

            list.add(ventaDetalle);
        }

        return list;
    }

    /**
     * Crea un objeto de tipo Solicitud
     * @return retorna el objeto de tipo <b>Solicitud</b> con todos los datos agregados en la vista
     */
    private Solicitud createObjectRequest() {
        Solicitud solicitud = new Solicitud();

        solicitud.setClienteID((Cliente) Clientes.getSelectedItem());
        if (Clientes.getSelectedIndex() < 2) {
            solicitud.setEstado("P");
        } else {
            solicitud.setEstado(FormaPago.getSelectedIndex() == 0 || FormaPago.getSelectedIndex() == 2 ? "P" : "N");
        }
        solicitud.setRtn(RTN.getForeground().equals(Color.BLACK) && !RTN.getText().isEmpty() ? RTN.getText() : null);
        solicitud.setUsuarioID(Utilities.getUsuarioActual());
        solicitud.setFecha(Utilities.getDate());
        solicitud.setHora(Utilities.getTime());

        return solicitud;
    }

    /**
     * 
     * @param SolicitudID recibe el ID de la solicitud para agregarlo a la lista de productos agregados en la tabla
     * @return retorna la lista de productos agregados a la tabla como una lista de objetos
     */
    private ArrayList<Solicituddetalle> createListRequestDetails(int SolicitudID) {
        ArrayList<Solicituddetalle> list = new ArrayList<>();
        for (int i = 0; i < Ventas.getRowCount(); i++) {
            Solicituddetalle solicitudDetalle = new Solicituddetalle();

            solicitudDetalle.setSolicitudID(new Solicitud(SolicitudID));
            solicitudDetalle.setProductoID(new Producto(Integer.valueOf(Ventas.getValueAt(i, 0).toString())));
            solicitudDetalle.setCantidad(Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", "")));
            solicitudDetalle.setPrecio(Float.parseFloat(Ventas.getValueAt(i, 4).toString().replace(",", "")));
            solicitudDetalle.setDescuento(Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", "")));
            solicitudDetalle.setIsv(0.15f);
            list.add(solicitudDetalle);
        }
        return list;
    }

    /**
     * Crea un objeto de tipo Cotizacion
     * @return retorna el objeto de tipo <b>Cotizacion</b> con todos los datos agregados en la vista
     */
    private Cotizacion createObjectQuote() {
        Cotizacion cotizacion = new Cotizacion();

        cotizacion.setClienteID((Cliente) Clientes.getSelectedItem());
        cotizacion.setUsuarioID(Utilities.getUsuarioActual());
        cotizacion.setFecha(Utilities.getDate());
        cotizacion.setHora(Utilities.getTime());

        return cotizacion;
    }

    /**
     * 
     * @param CotizacionID recibe el ID de la cotizacion para ser agregado a la lista de productos
     * @return retorna la lista de productos que han sido agregados a la tabla como una lista de objetos
     */
    private List<Cotizaciondetalle> createListQuoteDetails(int CotizacionID) {
        ArrayList<Cotizaciondetalle> list = new ArrayList<>();
        for (int i = 0; i < Ventas.getRowCount(); i++) {
            Cotizaciondetalle cotizacionDetalle = new Cotizaciondetalle();

            cotizacionDetalle.setCotizacionID(new Cotizacion(CotizacionID));
            cotizacionDetalle.setProductoID(new Producto(Integer.valueOf(Ventas.getValueAt(i, 0).toString())));
            cotizacionDetalle.setCantidad(Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", "")));
            cotizacionDetalle.setPrecio(Float.parseFloat(Ventas.getValueAt(i, 4).toString().replace(",", "")));
            cotizacionDetalle.setDescuento(Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", "")));
            cotizacionDetalle.setIsv(0.15f);

            list.add(cotizacionDetalle);
        }

        return list;
    }
    
    /**
     *
     * @return retorna un numero de factura de alguna factura que se haya almacenado vacia, 
     * en caso de que no exista alguna factura vacia retoronaria 0
     */
    private int validateEmptySale(){
        List<Venta> ventas = controller.findVentaEntities(50, controller.getVentaCount()-50);
        if(ventas != null){
            for(Venta venta : ventas){
                if(venta.getVentadetalleList() == null || venta.getVentadetalleList().isEmpty()){
                    return venta.getVentaID();
                } 
            }
        }
        return 0;
    }
    
    /**
     * 
     * @param isQuote recibe si el tipo de transaccion es couta o venta normal, en caso de ser recibir true
     * @return retorna true en el caso en que todos los datos ingredados sean validos
     */
    private boolean validate(boolean isQuote) {
        String action = isQuote ? "imprimir" : "agregar";
        String type = isQuote ? "cotizacion" : "venta";
        if (Ventas.getRowCount() <= 0) {
            Dialogs.ShowMessageDialog("Para " + action + " la " + type + " debe agregar al menos un producto", Dialogs.ERROR_ICON);
            return false;
        }
        if (Clientes.getSelectedIndex() == 0) {
            Dialogs.ShowMessageDialog("Para " + action + " la " + type + " debe seleccionar un cliente", Dialogs.ERROR_ICON);
            return false;
        }

        return true;
    }

    /**
     * Limpia todos los componentes en la vista, como la seleccion del cliente en el comboBox, la seleccion del metodo de pago
     * y los productos agregados a la factura
     */
    private void clear() {
        Clientes.setSelectedIndex(0);
        RTN.setText("Escriba el RTN...");
        RTN.setForeground(new Color(180, 180, 180));

        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");

        model.setRowCount(0);
    }

    /**
     * 
     * @param Value recibe un valor como 1 ó 1000.1, es decir de tipo flotante
     * @return Retorna un el valor recibido y lo formatea a 1.00 y 1,000.10 respectivamente
     */
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}