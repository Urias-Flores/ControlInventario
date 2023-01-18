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
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FacturaViewController {

    private VentaJpaController controller;
    private CotizacionJpaController controllerCotizacion;

    private DefaultTableModel model = new DefaultTableModel();

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

    public FacturaViewController(JComboBox Clientes, JTextField RTN, JRadioButton Pagado, JRadioButton Pendiente, JTextField Barra, JTextField Cotizacion, JTable Ventas, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total) {
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

        controller = new VentaJpaController(Conection.CreateEntityManager());
        controllerCotizacion = new CotizacionJpaController(Conection.CreateEntityManager());
    }

    public void cargarProducto(Object[] values) {
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

    public void cargarPorCodigoBarras() {
        ProductoJpaController controllerProducto = new ProductoJpaController(Conection.CreateEntityManager());
        List<Producto> productos = controllerProducto.findProductoEntities();
        productos.forEach(producto -> {
            if (producto.getBarra().equals(Barra.getText())) {
                Object[] row = {
                    producto.getProductoID(),
                    producto.getDescripcion(),
                    producto.getUnidad(),
                    getNumberFormat(1f),
                    getNumberFormat(producto.getPrecioVenta()),
                    getNumberFormat(0f),
                    getNumberFormat(producto.getPrecioVenta()),};

                cargarProducto(row);
            }
        });
        Barra.setText("");
    }

    public void cargarCotizacion() {
        if (validateCotizacion()) {
            List<Cotizaciondetalle> cotizacionDetalles = new CotizaciondetalleJpaController(Conection.CreateEntityManager()).findCotizaciondetalleEntities();
            int currentCotizacionID = Integer.parseInt(Cotizacion.getText());

            cotizacionDetalles.forEach(cotizacionDetalle -> {
                int cotizacionID = cotizacionDetalle.getCotizacionID().getCotizacionID();
                if (cotizacionID == currentCotizacionID) {
                    Producto producto = new ProductoJpaController(Conection.CreateEntityManager())
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
                    cargarProducto(row);
                }
            });

            Cotizacion.setText("");
        }
    }

    private boolean validateCotizacion() {
        if (Cotizacion.getText().isEmpty() || Cotizacion.getForeground().equals(new Color(180, 180, 180))) {
            Dialogs.ShowMessageDialog("Introduzca el numero de cotizacion", Dialogs.ERROR_ICON);
            return false;
        }
        try {
            int NoCotizacion = Integer.parseInt(Cotizacion.getText());
        } catch (NumberFormatException ex) {
            Dialogs.ShowMessageDialog("El No de cotizacion debe ser un numero", Dialogs.ERROR_ICON);
            return false;
        }
        return true;
    }

    public void AgregarCliente() {
        Dialogs.ShowAddClienteDialog();
        Clientes.removeAllItems();
        Clientes.addItem(new Cliente(0, "-- Seleccione cliente --", "", "", "", "", 0));
        CargarClientes();
    }

    public void CargarClientes() {
        List<Cliente> clientes = new ClienteJpaController(Conection.CreateEntityManager()).findClienteEntities();
        clientes.forEach(Clientes::addItem);
    }

    public void InitTable() {
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

    public void DeleteVenta() {
        int fila = Ventas.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowOKCancelDialog("¿Desea eliminar la venta seleccionada?", Dialogs.WARNING_ICON)) {
                model.removeRow(fila);
                updateTotal();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccion una venta de la lista", Dialogs.ERROR_ICON);
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
        }
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }

    public boolean InsertVenta() {
        if (validate()) {
            Venta venta = CreateObjectVenta();
            int VentaID = controller.create(venta);
            List<Ventadetalle> ventas = createListVentaDetalle(VentaID);
            VentadetalleJpaController ventadetalleJpaController = new VentadetalleJpaController(Conection.CreateEntityManager());
            ventas.forEach(ventadetalleJpaController::create);

            Runnable run = () -> {
                Reports reports = new Reports();
                reports.GenerateTickeVenta(VentaID);
            };
            Thread thread = new Thread(run);
            thread.start();

            Clear();
            return true;
        }
        return false;
    }

    public boolean InsertCotizacion() {
        if (validate()) {
            Cotizacion cotizacion = CreateObjectCotizacion();
            int CotizacionID = controllerCotizacion.create(cotizacion);
            List<Cotizaciondetalle> cotizaciondetalles = createListCotizaciondetalle(CotizacionID);
            CotizaciondetalleJpaController cotizaciondetalleJpaController = new CotizaciondetalleJpaController(Conection.CreateEntityManager());
            cotizaciondetalles.forEach(cotizaciondetalleJpaController::create);

            Runnable run = () -> {
                Reports reports = new Reports();
                reports.GenerateTicketCotizacion(CotizacionID);
            };
            Thread thread = new Thread(run);
            thread.start();

            Clear();
            return true;
        }
        return false;
    }

    private Venta CreateObjectVenta() {
        Venta venta = new Venta();

        venta.setRtn(RTN.getForeground().equals(Color.BLACK) && !RTN.getText().isEmpty() ? RTN.getText() : null);

        if (Clientes.getSelectedIndex() < 2) {
            venta.setClienteID(new Cliente(1));
            venta.setEstado("P");
        } else {
            venta.setClienteID((Cliente) Clientes.getSelectedItem());
            venta.setEstado(Pagado.isSelected() ? "P" : "N");
        }

        venta.setUsuarioID(Utilities.getUsuarioActual());
        venta.setFecha(Utilities.getDate());
        venta.setHora(Utilities.getTime());

        return venta;
    }

    private List<Ventadetalle> createListVentaDetalle(int VentaID) {

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

    private Cotizacion CreateObjectCotizacion() {
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

    private List<Cotizaciondetalle> createListCotizaciondetalle(int CotizacionID) {

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

    private boolean validate() {
        if (Ventas.getRowCount() <= 0) {
            Dialogs.ShowMessageDialog("Para agregar la venta debe agregar al menos una producto", Dialogs.ERROR_ICON);
            return false;
        }
        if (Clientes.getSelectedIndex() == 0) {
            Dialogs.ShowMessageDialog("Para agregar la venta debe seleccionar un cliente", Dialogs.ERROR_ICON);
            return false;
        }
        if (Clientes.getSelectedIndex() > 1) {
            if (!Pagado.isSelected() && !Pendiente.isSelected()) {
                Dialogs.ShowMessageDialog("Para agregar la venta debe seleccionar el estado de la factura", Dialogs.ERROR_ICON);
                return false;
            }
        }
        return true;
    }

    private void Clear() {
        Clientes.setSelectedIndex(0);
        RTN.setText("Escriba el RTN...");
        RTN.setForeground(new Color(180, 180, 180));

        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");

        model = new DefaultTableModel();
        InitTable();
    }
}
