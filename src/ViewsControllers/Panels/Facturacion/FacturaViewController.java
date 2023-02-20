package ViewsControllers.Panels.Facturacion;

import Controllers.ClienteJpaController;
import Controllers.CotizacionJpaController;
import Controllers.CotizaciondetalleJpaController;
import Controllers.ProductoJpaController;
import Controllers.VentaJpaController;
import Controllers.VentadetalleJpaController;
import Models.Cliente;
import Models.Cotizacion;
import Models.Cotizaciondetalle;
import Models.Producto;
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
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FacturaViewController {

    private VentaJpaController controller;
    private CotizacionJpaController controllerCotizacion;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    private JComboBox<Cliente> Clientes;
    private JTextField RTN;

    private JRadioButton Pagado;
    private JRadioButton Pendiente;

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

    public FacturaViewController(JLabel Cargando, JComboBox Clientes, JTextField RTN, JRadioButton Pagado, JRadioButton Pendiente, JTextField Barra, JTextField Cotizacion, JTable Ventas, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel dayState) {
        this.Cargando = Cargando;
        this.dayState = dayState;
        this.Clientes = Clientes;
        this.RTN = RTN;
        this.Pagado = Pagado;
        this.Pendiente = Pendiente;
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
    
    private void verifyDayInit(){
        LocalDataController ldc = new LocalDataController();
        if(ldc.validateInitDay()){
            this.isDayInit = true;
            dayState.setText("Realizar cierre");
        } else {
            this.isDayInit = false;
            dayState.setText("Iniciar dia");
        }
    }
    
    public void initDay(){
        if(!isDayInit){
            Dialogs.ShowInitDayDialog();
            verifyDayInit();
        } else {
            Dialogs.ShowCloseDayDialog();
            verifyDayInit();
        }
    }

    private void loadClients() {
        List<Cliente> clientes = new ClienteJpaController(Conection.createEntityManagerFactory()).findClienteEntities();
        clientes.forEach(Clientes::addItem);
    }

    public void addClient() {
        Dialogs.ShowAddClienteDialog();
        Clientes.removeAllItems();
        Clientes.addItem(new Cliente(0, "-- Seleccione cliente --", "", "", "", "", 0));
        Init();
    }

    public void loadProduct(Object[] values) {
        boolean NoexistRow = true;
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
        if (NoexistRow) {
            model.addRow(values);
        }
        updateTotal();
    }

    public void editItemValues() {
        int fila = Ventas.getSelectedRow();
        if (fila >= 0) {
            Object[] values = {
                Ventas.getValueAt(fila, 0).toString(),
                Ventas.getValueAt(fila, 1).toString(),
                Ventas.getValueAt(fila, 2).toString(),
                Ventas.getValueAt(fila, 3).toString(),
                Ventas.getValueAt(fila, 4).toString(),
                Ventas.getValueAt(fila, 5).toString(),};

            Object[] newValues = Dialogs.ShowEditVentaDialog(values);

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

            //ProductoJpaController controllerProducto = new ProductoJpaController(Conection.createEntityManagerFactory());
            //List<Producto> productos = controllerProducto.findProductoEntities();
            
            Producto producto = (Producto) Conection.createEntityManager()
                    .createNamedQuery("Producto.findByBarra")
                    .setParameter("barra", Barra.getText())
                    .getSingleResult();
            
            //productos.forEach(producto -> {
                if (producto.getBarra() != null) {
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

                        Query query = Conection.createEntityManager()
                                .createNativeQuery("SELECT cantidad FROM inventario WHERE ProductoID = " + producto.getProductoID());
                        List values = query.getResultList();

                        float existenciaProducto = Float.parseFloat(values.get(0).toString());
                        if (existenciaProducto <= 0) {
                            Dialogs.ShowMessageDialog("El producto no cuenta con existencia en inventario", Dialogs.ERROR_ICON);
                        } else {
                            loadProduct(row);
                        }
                    }
                }
            //});
            Barra.setText("");
            setLoad(false);
        };
        new Thread(run).start();
    }

    //Task
    public void loadQuote() {
        if (validateQuote()) {

            setLoad(true);
            Runnable run = () -> {
                
                List<Cotizaciondetalle> cotizacionDetalles = new CotizaciondetalleJpaController(Conection.createEntityManagerFactory()).findCotizaciondetalleEntities();
                int currentCotizacionID = Integer.parseInt(Cotizacion.getText());

                cotizacionDetalles.forEach(cotizacionDetalle -> {
                    int cotizacionID = cotizacionDetalle.getCotizacionID().getCotizacionID();
                    if (cotizacionID == currentCotizacionID) {
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
                    }
                });

                Cotizacion.setText("");
                setLoad(false);
            };
            new Thread(run).start();
        }
    }

    private boolean validateQuote() {
        if (Cotizacion.getText().isEmpty() || Cotizacion.getForeground().equals(new Color(180, 180, 180))) {
            Dialogs.ShowMessageDialog("Introduzca el numero de cotizacion", Dialogs.ERROR_ICON);
            return false;
        }
        try {
            int NoCotizacion = Integer.parseInt(Cotizacion.getText());
        } catch (NumberFormatException ex) {
            Dialogs.ShowMessageDialog("El Numero de cotizacion debe ser un numerico", Dialogs.ERROR_ICON);
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
        if (numberRows > 0) {
            float subtotal = 0;
            float descuento = 0;

            for (int i = 0; i < numberRows; i++) {
                float cantidad = Float.parseFloat(Ventas.getValueAt(i, 3).toString().replace(",", ""));
                float precio = Float.parseFloat(Ventas.getValueAt(i, 4).toString().replace(",", ""));
                float descuentoUnitario = Float.parseFloat(Ventas.getValueAt(i, 5).toString().replace(",", ""));

                subtotal += cantidad * precio;
                descuento += descuentoUnitario;
            }
            float importe = (subtotal - descuento) / 1.15f;
            float isv = importe * 0.15f;
            float total = importe + isv;

            Subtotal.setText(getNumberFormat(subtotal));
            Descuento.setText(getNumberFormat(descuento));
            Importe.setText(getNumberFormat(importe));
            ISV.setText(getNumberFormat(isv));
            Total.setText(getNumberFormat(total));
        } else {
            Subtotal.setText(getNumberFormat(0));
            Descuento.setText(getNumberFormat(0));
            Importe.setText(getNumberFormat(0));
            ISV.setText(getNumberFormat(0));
            Total.setText(getNumberFormat(0));
        }
    }

    public void InsertSale() {
        if (validate(false)) {
            if(isDayInit){
                setLoad(true);
                Runnable run = () -> {
                    //Insertando venta
                    Venta venta = CreateObjectSale();
                    int VentaID = controller.create(venta);

                    //Insertando detalles de la factura
                    List<Ventadetalle> ventas = createListSaleDetails(VentaID);
                    VentadetalleJpaController ventadetalleJpaController = new VentadetalleJpaController(Conection.createEntityManagerFactory());
                    ventas.forEach(ventadetalleJpaController::create);

                    //Agregando informacion de arqueo
                    float total = Float.parseFloat(Total.getText().replace(",", ""));
                    float efectivo = Dialogs.ShowArqueoDialog(VentaID, venta.getEstado().equals("N"), total);

                    setLoad(false);
                    Dialogs.ShowMessageDialog("La factura ha sido ingresada exitosamente", Dialogs.COMPLETE_ICON);

                    //Enviando a imprimir ticket de venta
                    if (Dialogs.ShowOKCancelDialog("¿Desea enviar a imprimir la factura ahora?", Dialogs.WARNING_ICON)) {
                        setLoad(true);
                        Runnable runnable = () ->{
                            Reports reports = new Reports();
                            reports.GenerateTickeVenta(VentaID, efectivo);
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
                Cotizacion cotizacion = CreateObjectQuote();
                int CotizacionID = controllerCotizacion.create(cotizacion);
                
                //Insertanto detalles de cotizacion
                List<Cotizaciondetalle> cotizaciondetalles = createListQuoteDetails(CotizacionID);
                CotizaciondetalleJpaController cotizaciondetalleJpaController = new CotizaciondetalleJpaController(Conection.createEntityManagerFactory());
                cotizaciondetalles.forEach(cotizaciondetalleJpaController::create);

                //Enviando a imprimir ticket de cotizacion
                Reports reports = new Reports();
                reports.GenerateTicketCotizacion(CotizacionID);

                clear();
                setLoad(false);
            };
            new Thread(run).start();
            
        }
    }

    private Venta CreateObjectSale() {
        Venta venta = new Venta();

        venta.setRtn(RTN.getForeground().equals(Color.BLACK) && !RTN.getText().isEmpty() ? RTN.getText() : null);
        venta.setClienteID((Cliente) Clientes.getSelectedItem());
        if (Clientes.getSelectedIndex() < 2) {
            venta.setEstado("P");
        } else {
            venta.setEstado(Pagado.isSelected() ? "P" : "N");
        }

        venta.setUsuarioID(Utilities.getUsuarioActual());
        venta.setFecha(Utilities.getDate());
        venta.setHora(Utilities.getTime());

        return venta;
    }

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

    private Cotizacion CreateObjectQuote() {
        Cotizacion cotizacion = new Cotizacion();

        if (Clientes.getSelectedIndex() < 2) {
            cotizacion.setClienteID(new Cliente(1));
        } else {
            cotizacion.setClienteID((Cliente) Clientes.getSelectedItem());
        }

        cotizacion.setUsuarioID(Utilities.getUsuarioActual());
        cotizacion.setFecha(Utilities.getDate());
        cotizacion.setHora(Utilities.getTime());

        return cotizacion;
    }

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

    private boolean validate(boolean isQuote) {
        String action = isQuote ? "imprimir" : "agregar";
        String type = isQuote ? "cotizacion" : "venta";
        
        if (Ventas.getRowCount() <= 0) {
            Dialogs.ShowMessageDialog("Para "+ action +" la "+ type +" debe agregar al menos un producto", Dialogs.ERROR_ICON);
            return false;
        }
        if (Clientes.getSelectedIndex() == 0) {
            Dialogs.ShowMessageDialog("Para "+ action +" la "+ type +" debe seleccionar un cliente", Dialogs.ERROR_ICON);
            return false;
        }
        if(!isQuote){
            if (Clientes.getSelectedIndex() > 1) {
                if (!Pagado.isSelected() && !Pendiente.isSelected()) {
                    Dialogs.ShowMessageDialog("Para agregar la venta debe seleccionar la forma de pago", Dialogs.ERROR_ICON);
                    return false;
                }
            }
        }
        
        return true;
    }

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

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
