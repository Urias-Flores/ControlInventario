package ViewsControllers.Panels.Facturacion;

import Controllers.ClienteJpaController;
import Controllers.SolicitudJpaController;
import Controllers.VentaJpaController;
import Controllers.VentadetalleJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Models.Solicitud;
import Models.Solicituddetalle;
import Models.Venta;
import Models.Ventadetalle;
import Reports.Reports;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.NoJpaConection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class FacturasDiaViewController {

    private JComboBox<Cliente> Clientes;
    private JComboBox Tipo;
    private JTable Facturas;
    private JLabel TotalVentas;
    private JLabel Ganancias;
    private JTable VistaPreeliminar;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;
    private JLabel Cargando;

    //Creando modelo de tabla utilizado para cargar las facturas del dia
    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    
    //Creando modelo de tabla utilizado para cargar las vista previa de las facturas
    private DefaultTableModel modelPrevia = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    public FacturasDiaViewController(JComboBox Clientes, JComboBox Tipo, JTable Facturas, JLabel TotalVentas, JLabel Ganancias,JTable VistaPreeliminar, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel Cargando) {
        this.Clientes = Clientes;
        this.Tipo = Tipo;
        this.Facturas = Facturas;
        this.TotalVentas = TotalVentas;
        this.Ganancias = Ganancias;
        this.VistaPreeliminar = VistaPreeliminar;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;
        this.Cargando = Cargando;

        //Cargando modelo en tabla de factura
        setModelTableBills();

        //Cargando modelo en tabla de vista previa de facturas
        setModelTablePreview();

        //Inicializando carga de datos
        Init();
    }

    private void setLoad(boolean state) {
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }

    //Task
    private void Init() {
        setLoad(true);
        Runnable run = () -> {
            //Cargando datos  a tabla de factura
            loadBills();

            //Actualizando total de ventas
            updateTotalSales();

            //Cargando clientes en combobox
            loadClients();

            //Limpiando datos de vista previa en caso de recarga
            clear();

            setLoad(false);
        };
        new Thread(run).start();
    }

    //Task
    public void updateData() {
        Init();
    }

    //Ajusta las headers de la tabla
    private void setModelTableBills() {
        String[] columns = {"No.", "Tipo", "Cliente", "Usuario", "Hora", "Total"};
        model.setColumnIdentifiers(columns);

        Facturas.setModel(model);
        Facturas.getColumn("No.").setPreferredWidth(100);
        Facturas.getColumn("Tipo").setPreferredWidth(80);
        Facturas.getColumn("Cliente").setPreferredWidth(220);
        Facturas.getColumn("Usuario").setPreferredWidth(220);
        Facturas.getColumn("Hora").setPreferredWidth(90);
        Facturas.getColumn("Total").setPreferredWidth(90);
    }

    //Cargar las facturas diferenciando entre si es una Venta normal o una no oficial
    private void loadBills() {
        model.setRowCount(0);
        Query query = Conection.createEntityManagerFactory().createEntityManager().createNativeQuery("SELECT *  FROM ViewFacturasDia ORDER BY Hora desc");
        List<Object[]> facturas = query.getResultList();
        float ganancia = 0;
        if(facturas != null){
            for( Object[] factura : facturas ){
                factura[5] = getNumberFormat(Float.parseFloat(factura[5].toString()));
                model.addRow(factura);
                
                if(factura[1].toString().equals("Venta")){
                    List<Ventadetalle> ventadetalles = Conection.createEntityManager().createNamedQuery("Ventadetalle.findByVentaID")
                        .setParameter("ventaID", new Venta(Integer.valueOf(factura[0].toString())))
                        .getResultList();
                    if(ventadetalles != null){
                        for(Ventadetalle ventadetalle : ventadetalles){
                            ganancia += (ventadetalle.getPrecio() - ventadetalle.getProductoID().getPrecioCompra()) * ventadetalle.getCantidad();
                        }
                    }
                }else{
                    List<Solicituddetalle> solicituddetalles = Conection.createEntityManager().createNamedQuery("Solicituddetalle.findBySolicitudID")
                        .setParameter("solicitudID", new Solicitud(Integer.valueOf(factura[0].toString())))
                        .getResultList();
                    if(solicituddetalles != null){
                        for(Solicituddetalle solicituddetalle : solicituddetalles){
                            ganancia += (solicituddetalle.getPrecio() - solicituddetalle.getProductoID().getPrecioCompra()) * solicituddetalle.getCantidad();
                        }
                    }
                }
            }
        }

        Ganancias.setText(getNumberFormat(ganancia));
    }

    private void setModelTablePreview() {
        String[] columns = {"Producto", "Cant.", "Precio", "Desc."};
        modelPrevia.setColumnIdentifiers(columns);

        VistaPreeliminar.setModel(modelPrevia);
        VistaPreeliminar.getColumn("Producto").setPreferredWidth(150);
        VistaPreeliminar.getColumn("Cant.").setPreferredWidth(30);
        VistaPreeliminar.getColumn("Precio").setPreferredWidth(70);
        VistaPreeliminar.getColumn("Desc.").setPreferredWidth(40);
    }

    //Task
    //Carga la vista preva de la factura que se selecciona en la tabla
    public void loadBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {
            modelPrevia.setRowCount(0);

            setLoad(true);
            Runnable run = () -> {
                float subtotal = 0;
                float descuento = 0;
                if(Facturas.getValueAt(fila, 1).toString().equals("Venta")){
                    Venta venta = new VentaJpaController(Conection.createEntityManagerFactory())
                        .findVenta(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));

                    List<Ventadetalle> ventadetalles = venta.getVentadetalleList();
                    if(ventadetalles != null){
                        for (Ventadetalle ventadetalle : ventadetalles) {
                            Object[] row = {
                                ventadetalle.getProductoID().getDescripcion(),
                                ventadetalle.getCantidad(),
                                getNumberFormat(ventadetalle.getPrecio()),
                                getNumberFormat(ventadetalle.getDescuento()),};
                            modelPrevia.addRow(row);

                            subtotal += ventadetalle.getCantidad() * ventadetalle.getPrecio();
                            descuento += ventadetalle.getDescuento();
                        }  
                    }
                } else {
                    Solicitud solicitud = new SolicitudJpaController(Conection.createEntityManagerFactory())
                        .findSolicitud(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));

                    List<Solicituddetalle> solicituddetalles = solicitud.getSolicituddetalleList();
                    if(solicituddetalles != null){
                        for (Solicituddetalle solicituddetalle : solicituddetalles) {
                            Object[] row = {
                                solicituddetalle.getProductoID().getDescripcion(),
                                solicituddetalle.getCantidad(),
                                getNumberFormat(solicituddetalle.getPrecio()),
                                getNumberFormat(solicituddetalle.getDescuento()),};
                            modelPrevia.addRow(row);

                            subtotal += solicituddetalle.getCantidad() * solicituddetalle.getPrecio();
                            descuento += solicituddetalle.getDescuento();
                        }
                    }
                }
                
                float importe = (subtotal - descuento) / 1.15f;
                float isv = importe * 0.15f;
                float total = importe + isv;

                Subtotal.setText(getNumberFormat(subtotal));
                Descuento.setText(getNumberFormat(descuento));
                Importe.setText(getNumberFormat(importe));
                ISV.setText(getNumberFormat(isv));
                Total.setText(getNumberFormat(total));

                setLoad(false);
            };
            new Thread(run).start();

        }
    }
    
    //Actualiza los campos de la vista previa con los datos relacionados a la factura seleccionada
    private void updateTotalSales() {
        float totalSales = 0;
        int counter = 0;
        while (counter < Facturas.getRowCount()) {
            totalSales += Float.parseFloat(Facturas.getValueAt(counter, 5).toString().replace(",", ""));
            counter++;
        }
        TotalVentas.setText(getNumberFormat(totalSales));
    }

    //Carga la lista de clientes en el combobox
    private void loadClients() {
        Clientes.removeAllItems();
        Clientes.addItem(new Cliente(0, "-- Todos los clientes --", 0));
        Cliente consumidorFinal = new ClienteJpaController(Conection.createEntityManagerFactory()).findCliente(1);
        Clientes.addItem(consumidorFinal);

        List<Cliente> clientes = Conection.createEntityManager().createNamedQuery("Cliente.findAll").getResultList();
        clientes.forEach(cliente -> {
            if (cliente.getClienteID() != 1) {
                Clientes.addItem(cliente);
            }
        });
    }

    //Filtar la tabla para mostrar las datos segun cliente y tipo de transaccion (Venta, Solicitud)
    public void filter() {
        List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        filters.add(RowFilter.regexFilter(Clientes.getSelectedIndex() == 0 || Clientes.getSelectedIndex() == -1 ? "" :Clientes.getSelectedItem().toString(), 2));
        filters.add(RowFilter.regexFilter(Tipo.getSelectedIndex() == 0 ? "" : Tipo.getSelectedItem().toString() , 1));
        
        TableRowSorter s = new TableRowSorter(Facturas.getModel());
        s.setRowFilter(RowFilter.andFilter(filters));
        Facturas.setRowSorter(s);
    }

    //Task
    //Envia a imprimir las factura seleccionada
    public void printBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de querer reimprimir la factura seleccionada?", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () -> {
                    Reports reports = new Reports();
                    LocalDataController ldc = new LocalDataController();

                    int transaccionID = Integer.parseInt(Facturas.getValueAt(fila, 0).toString());
                    if(Facturas.getValueAt(fila, 1).toString().equals("Venta")){
                        reports.GenerateTickeVenta(transaccionID, null, ldc.getTotal(transaccionID, "V"));
                    }else{
                        reports.GenerateTicketSolicitud(transaccionID, ldc.getTotal(transaccionID, "S"));
                    }
                    setLoad(false);
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }

    //Task
    //Envia a imprimir el reporte de ventas del dia
    public void printSalesReport() {
        Cargando.setIcon(new ImageIcon(getClass().getResource(Utilities.getLoadingImage())));
        Runnable run = () -> {
            Reports reports = new Reports();
            reports.GenerateVentasReport(Utilities.getUsuarioActual().getNombre(), Utilities.getDate(), Utilities.getDate());
            Cargando.setIcon(null);
        };
        Thread thread = new Thread(run);
        thread.start();
    }

    //Task
    //Elimina la factura seleccionada de la tabla
    public void deleteBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowEnterPasswordDialog("No es recomendable la eliminación de facturas.",
                    "Esta acción puede ocacionar desorden en inventario real.",
                    "Para continuar con la eliminacion escriba su contraseña.", Dialogs.WARNING_ICON)) {
                LocalDataController ldc = new LocalDataController();
                setLoad(true);
                Runnable run = () -> {
                    VentaJpaController controller = new VentaJpaController(Conection.createEntityManagerFactory());
                    deleteBillDetail(fila);
                    try {
                        int VentaID = Integer.parseInt(Facturas.getValueAt(fila, 0).toString());
                        String TipoTransaccion = Facturas.getValueAt(fila, 1).toString().substring(0, 1);
                        //Eliminando factura
                        controller.destroy(VentaID);
                        //Eliminando el detalle de arqueo
                        ldc.deleteArqueoDetalle(VentaID, TipoTransaccion);
                        //Actualizando el numero de factura al numero de factura que se elimina para que
                        //se vuelva a recrear
                        if(TipoTransaccion.equals("V")){
                            resetBillNumber(VentaID);
                        }
                        //Actualizando tabla
                        Init();
                        Dialogs.ShowMessageDialog("La factura ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (IllegalOrphanException | NonexistentEntityException ex) {
                        System.err.println(ex.getMessage());
                        Dialogs.ShowMessageDialog("La factura este ligada a otros datos, no pudo ser eliminada", Dialogs.ERROR_ICON);
                    }

                    setLoad(false);
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    /**
     * 
     * @param BillNumber recibe el numero de factura al que se desea que se reestablezca luego de la eliminacion
     */
    private void resetBillNumber(int BillNumber){
        try {
            PreparedStatement ps = new NoJpaConection().getconec().prepareStatement("ALTER TABLE Venta auto_increment = ?");
            ps.setInt(1, BillNumber);
            ps.execute();
            ps.close();
            System.out.println("Se actualizo el número de factura recurrente");
        } catch (SQLException ex) {
            Logger.getLogger(FacturasDiaViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Elimina los detalle de la factura seleccionada en la tabla para eliminar dicha factura
     * @param fila Recibe el numero de fila de la tabla seleccionada
     */
    private void deleteBillDetail(int fila) {
        VentadetalleJpaController controller = new VentadetalleJpaController(Conection.createEntityManagerFactory());
        List<Ventadetalle> ventadetalles = controller.findVentadetalleEntities();

        ventadetalles.forEach(ventadetalle -> {
            if (Objects.equals(ventadetalle.getVentaID().getVentaID(), Integer.valueOf(Facturas.getValueAt(fila, 0).toString()))) {
                try {
                    controller.destroy(ventadetalle.getVentaDetalleID());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FacturasDiaViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    //Limpia todos los campos de la vista
    private void clear() {
        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");
        modelPrevia.setRowCount(0);
    }

    //
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
