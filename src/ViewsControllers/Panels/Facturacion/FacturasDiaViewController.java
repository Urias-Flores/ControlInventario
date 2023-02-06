
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
    private JTable VistaPreeliminar;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;
    private JLabel Cargando;

    public FacturasDiaViewController(JComboBox Clientes, JTable Facturas, JTable VistaPreeliminar, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel Cargando) {
        this.Clientes = Clientes;
        this.Facturas = Facturas;
        this.VistaPreeliminar = VistaPreeliminar;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;
        this.Cargando = Cargando;
    }
    
    public void CargarFacturas(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No.", "Cliente", "Usuario", "Hora", "Total"};
        model.setColumnIdentifiers(columns);
        
        Query query = Conection.createEntityManagerFactory().createEntityManager().createNativeQuery("SELECT *  FROM ViewFacturasDia");
        List<Object[]> facturas = query.getResultList();
        facturas.forEach(factura -> {
            factura[4] = getNumberFormat(Float.parseFloat(factura[4].toString()));
            model.addRow(factura);
        });
        
        Facturas.setModel(model);
        
        Facturas.getColumn("No.").setPreferredWidth(30);
        Facturas.getColumn("Cliente").setPreferredWidth(250);
        Facturas.getColumn("Usuario").setPreferredWidth(250);
        Facturas.getColumn("Hora").setPreferredWidth(70);
        Facturas.getColumn("Total").setPreferredWidth(90);
        
        cargarPrevia();
        Clear();
    }
    
    private void cargarPrevia(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Producto", "Cant.", "Precio", "Desc."};
        model.setColumnIdentifiers(columns);
        
        VistaPreeliminar.setModel(model);
        VistaPreeliminar.getColumn("Producto").setPreferredWidth(150);
        VistaPreeliminar.getColumn("Cant.").setPreferredWidth(30);
        VistaPreeliminar.getColumn("Precio").setPreferredWidth(70);
        VistaPreeliminar.getColumn("Desc.").setPreferredWidth(40);
    }
    
    public void CargarFactura(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Producto", "Cant.", "Precio", "Desc."};
        model.setColumnIdentifiers(columns);
        
        int fila = Facturas.getSelectedRow();
        if(fila >= 0){
            Venta venta = new VentaJpaController(Conection.createEntityManagerFactory()).findVenta(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));
            
            float subtotal = 0;
            float descuento = 0;
            
            List<Ventadetalle> ventadetalles = venta.getVentadetalleList();
            for(int i = 0; i < ventadetalles.size(); i++){
                Object[] row = {
                    ventadetalles.get(i).getProductoID().getDescripcion(),
                    ventadetalles.get(i).getCantidad(),
                    getNumberFormat(ventadetalles.get(i).getPrecio()),
                    getNumberFormat(ventadetalles.get(i).getDescuento()),
                };
                model.addRow(row);
                
                subtotal += ventadetalles.get(i).getCantidad() * ventadetalles.get(i).getPrecio();
                descuento += ventadetalles.get(i).getDescuento();
            }
            float importe = (subtotal - descuento)/1.15f;
            float isv = importe * 0.15f;
            float total = importe + isv;
            
            VistaPreeliminar.setModel(model);
            
            Subtotal.setText(getNumberFormat(subtotal));
            Descuento.setText(getNumberFormat(descuento));
            Importe.setText(getNumberFormat(importe));
            ISV.setText(getNumberFormat(isv));
            Total.setText(getNumberFormat(total));
            
            VistaPreeliminar.getColumn("Producto").setPreferredWidth(150);
            VistaPreeliminar.getColumn("Cant.").setPreferredWidth(30);
            VistaPreeliminar.getColumn("Precio").setPreferredWidth(70);
            VistaPreeliminar.getColumn("Desc.").setPreferredWidth(40);
        }
    }
    
    public void CargarClientes(){
        List<Cliente> clientes = new ClienteJpaController(Conection.createEntityManagerFactory()).findClienteEntities();
        clientes.forEach(cliente -> {
            Clientes.addItem(cliente);
        });
    }
    
    public void Filtrar(){
        if(Clientes.getSelectedIndex() > 0){
            TableRowSorter s = new TableRowSorter(Facturas.getModel());
            s.setRowFilter(RowFilter.regexFilter(Clientes.getSelectedItem().toString(), 1));
            Facturas.setRowSorter(s);
        }else{
            TableRowSorter s = new TableRowSorter(Facturas.getModel());
            s.setRowFilter(RowFilter.regexFilter("", 1));
            Facturas.setRowSorter(s);
        }
    }
    
    public void Imprimir(){
        int fila = Facturas.getSelectedRow();
        if(fila >= 0){
            Cargando.setIcon(new ImageIcon(getClass().getResource("/Icons/cargando32px.gif")));
            Runnable run = ()->{
                Reports reports = new Reports();
                reports.GenerateTickeVenta(Integer.parseInt(Facturas.getValueAt(fila, 0).toString()));
                Cargando.setIcon(null);
            };
            Thread thread = new Thread(run);
            thread.start();
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }   
    }
    
    public void ImprimirReporte(){
        Cargando.setIcon(new ImageIcon(getClass().getResource("/Icons/cargando32px.gif")));
        Runnable run = ()->{
            Reports reports = new Reports();
            reports.GenerateVentasReport(Utilities.getUsuarioActual().getNombre(), Utilities.getDate(), Utilities.getDate());
            Cargando.setIcon(null);
        };
        Thread thread = new Thread(run);
        thread.start();
    }
    
    public void Eliminar(){
        int fila = Facturas.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("No es recomendable la eliminacion de facturas.", 
                    "Esta accion puede ocacionar desorden en inventario real.", 
                    "Para continuar con la eliminacion escriba su contraseña.", Dialogs.WARNING_ICON)){
                VentaJpaController controller = new VentaJpaController(Conection.createEntityManagerFactory());
                DeleteVentaDetalle(fila);
                try {
                    controller.destroy(Integer.valueOf(Facturas.getValueAt(fila, 0).toString()));
                    CargarFacturas();
                    Clear();
                    Dialogs.ShowMessageDialog("La factura ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    System.err.println(ex.getMessage());
                    Dialogs.ShowMessageDialog("La factura este ligada a otros datos, no se pudo eliminar", Dialogs.ERROR_ICON);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private void DeleteVentaDetalle(int fila){
        VentadetalleJpaController controller = new VentadetalleJpaController(Conection.createEntityManagerFactory());
        List<Ventadetalle> ventadetalles = controller.findVentadetalleEntities();
        
        ventadetalles.forEach(ventadetalle ->{
            if(Objects.equals(ventadetalle.getVentaID().getVentaID(), Integer.valueOf(Facturas.getValueAt(fila, 0).toString()))){
                try {
                    controller.destroy(ventadetalle.getVentaDetalleID());
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FacturasDiaViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
    private void Clear(){
        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");
        cargarPrevia();
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
