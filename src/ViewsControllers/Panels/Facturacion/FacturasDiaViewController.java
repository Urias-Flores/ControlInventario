package ViewsControllers.Panels.Facturacion;

import Controllers.ClienteJpaController;
import Controllers.VentaJpaController;
import Controllers.VentadetalleJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Models.Venta;
import Models.Ventadetalle;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
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
import javax.swing.table.TableRowSorter;

public class FacturasDiaViewController {

    private JComboBox<Cliente> Clientes;
    private JTable Facturas;
    private JLabel TotalVentas;
    private JTable VistaPreeliminar;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;
    private JLabel Cargando;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };
    private DefaultTableModel modelPrevia = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    };

    public FacturasDiaViewController(JComboBox Clientes, JTable Facturas, JLabel TotalVentas, JTable VistaPreeliminar, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel Cargando) {
        this.Clientes = Clientes;
        this.Facturas = Facturas;
        this.TotalVentas = TotalVentas;
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

    private void setModelTableBills() {
        String[] columns = {"No.", "Cliente", "Usuario", "Hora", "Total"};
        model.setColumnIdentifiers(columns);

        Facturas.setModel(model);

        Facturas.getColumn("No.").setPreferredWidth(30);
        Facturas.getColumn("Cliente").setPreferredWidth(250);
        Facturas.getColumn("Usuario").setPreferredWidth(250);
        Facturas.getColumn("Hora").setPreferredWidth(70);
        Facturas.getColumn("Total").setPreferredWidth(90);
    }

    private void loadBills() {
        model.setRowCount(0);
        Query query = Conection.createEntityManagerFactory().createEntityManager().createNativeQuery("SELECT *  FROM ViewFacturasDia");
        List<Object[]> facturas = query.getResultList();
        facturas.forEach(factura -> {
            factura[4] = getNumberFormat(Float.parseFloat(factura[4].toString()));
            model.addRow(factura);
        });
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

    public void loadBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {
            modelPrevia.setRowCount(0);

            setLoad(true);
            Runnable run = () -> {
                float subtotal = 0;
                float descuento = 0;

                Venta venta = new VentaJpaController(Conection.createEntityManagerFactory())
                        .findVenta(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));

                List<Ventadetalle> ventadetalles = venta.getVentadetalleList();
                for (int i = 0; i < ventadetalles.size(); i++) {
                    Object[] row = {
                        ventadetalles.get(i).getProductoID().getDescripcion(),
                        ventadetalles.get(i).getCantidad(),
                        getNumberFormat(ventadetalles.get(i).getPrecio()),
                        getNumberFormat(ventadetalles.get(i).getDescuento()),};
                    modelPrevia.addRow(row);

                    subtotal += ventadetalles.get(i).getCantidad() * ventadetalles.get(i).getPrecio();
                    descuento += ventadetalles.get(i).getDescuento();
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

    private void updateTotalSales() {
        float totalSales = 0;
        
        int counter = 0;
        while(counter < Facturas.getRowCount()){
            totalSales += Float.parseFloat(Facturas.getValueAt(counter, 4).toString().replace(",", ""));
            counter++;
        }
        
        TotalVentas.setText(getNumberFormat(totalSales));
    }

    private void loadClients() {
        List<Cliente> clientes = new ClienteJpaController(Conection.createEntityManagerFactory()).findClienteEntities();
        clientes.forEach(cliente -> {
            Clientes.addItem(cliente);
        });
    }

    public void filterClients() {
        if (Clientes.getSelectedIndex() > 0) {
            TableRowSorter s = new TableRowSorter(Facturas.getModel());
            s.setRowFilter(RowFilter.regexFilter(Clientes.getSelectedItem().toString(), 1));
            Facturas.setRowSorter(s);
        } else {
            TableRowSorter s = new TableRowSorter(Facturas.getModel());
            s.setRowFilter(RowFilter.regexFilter("", 1));
            Facturas.setRowSorter(s);
        }
    }

    //Task
    public void printBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {

            setLoad(true);
            Runnable run = () -> {
                Reports reports = new Reports();
                reports.GenerateTickeVenta(Integer.parseInt(Facturas.getValueAt(fila, 0).toString()));
                setLoad(false);
            };
            new Thread(run).start();

        } else {
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }

    //Task
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
    public void deleteBill() {
        int fila = Facturas.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowEnterPasswordDialog("No es recomendable la eliminación de facturas.",
                    "Esta acción puede ocacionar desorden en inventario real.",
                    "Para continuar con la eliminacion escriba su contraseña.", Dialogs.WARNING_ICON)) {

                setLoad(true);
                Runnable run = () -> {
                    VentaJpaController controller = new VentaJpaController(Conection.createEntityManagerFactory());
                    deleteBillDetail(fila);
                    try {
                        controller.destroy(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));
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

    private void clear() {
        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");
        modelPrevia.setRowCount(0);
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
