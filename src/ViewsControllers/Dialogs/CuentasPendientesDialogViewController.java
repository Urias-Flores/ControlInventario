package ViewsControllers.Dialogs;

import Controllers.ClienteJpaController;
import Controllers.CompraJpaController;
import Controllers.ProveedorJpaController;
import Controllers.VentaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Models.Compra;
import Models.Proveedor;
import Models.Venta;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CuentasPendientesDialogViewController {
    private JLabel Nombre;
    private JTable Cuentas;
    private JLabel Total;
    
    private int Cliente;
    private int Proveedor;

    public void setCliente(int Cliente) {
        this.Cliente = Cliente;
    }

    public void setProveedor(int Proveedor) {
        this.Proveedor = Proveedor;
    }

    
    
    public CuentasPendientesDialogViewController(JLabel Nombre, JTable Cuentas, JLabel Total) {
        this.Nombre = Nombre;
        this.Cuentas = Cuentas;
        this.Total = Total;
    }
    
    public void CargarCliente(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Factura", "Fecha", "Hora", "Total"};
        model.setColumnIdentifiers(columns);
        
        Cliente cliente = new ClienteJpaController(Conection.CreateEntityManager()).findCliente(Cliente);
        
        if(cliente != null){
            Nombre.setText(cliente.getNombre());
            StoredProcedureQuery sp = Conection.CreateEntityManager().createEntityManager()
                    .createStoredProcedureQuery("ProcedureFacturaCliente")
                    .registerStoredProcedureParameter("Cliente", Integer.class, ParameterMode.IN);
            sp.setParameter("Cliente", Cliente);
            
            List<Object[]> facturas = sp.getResultList();
            facturas.forEach(factura -> {
                if(factura[3] != null){
                    factura[3] = getNumberFormat(Float.parseFloat(factura[3].toString()));
                }
                model.addRow(factura);
            });
            
            Cuentas.setModel(model);
            
            Cuentas.getColumn("No. Factura").setPreferredWidth(55);
            Cuentas.getColumn("Fecha").setPreferredWidth(80);
            Cuentas.getColumn("Hora").setPreferredWidth(80);
            Cuentas.getColumn("Total").setPreferredWidth(130);
            
            updateTotal();
        }
    }
    
    public void CargarProveedor(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Compra", "Fecha", "Hora", "Total"};
        model.setColumnIdentifiers(columns);
        
        Proveedor proveedor = new ProveedorJpaController(Conection.CreateEntityManager()).findProveedor(Proveedor);
        
        if(proveedor != null){
            Nombre.setText(proveedor.getNombre());
            StoredProcedureQuery sp = Conection.CreateEntityManager().createEntityManager()
                    .createStoredProcedureQuery("ProcedureFacturaProveedor")
                    .registerStoredProcedureParameter("Proveedor", Integer.class, ParameterMode.IN);
            sp.setParameter("Proveedor", Proveedor);
            
            List<Object[]> facturas = sp.getResultList();
            facturas.forEach(factura -> {
                if(factura[3] != null){
                    factura[3] = getNumberFormat(Float.parseFloat(factura[3].toString()));
                }
                model.addRow(factura);
            });
            
            Cuentas.setModel(model);
            
            Cuentas.getColumn("No. Compra").setPreferredWidth(55);
            Cuentas.getColumn("Fecha").setPreferredWidth(80);
            Cuentas.getColumn("Hora").setPreferredWidth(80);
            Cuentas.getColumn("Total").setPreferredWidth(130);
            
            updateTotal();
        }
    }
    
    public void cargarDetallesFactura(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowDetalleFactura(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void pagarFactura(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada una factura", 
                    "con un valor de: "+Cuentas.getValueAt(fila, 3).toString()+" Lps. por medidas de seguridad", 
                    "Por favor ingrese su contraseña de usuario para pagar.", Dialogs.WARNING_ICON)){
                int VentaID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                VentaJpaController controllerVenta = new VentaJpaController(Conection.CreateEntityManager());
                Venta venta = controllerVenta.findVenta(VentaID);
                venta.setEstado("P");

                try {
                    controllerVenta.edit(venta);
                    CargarCliente();
                    Dialogs.ShowMessageDialog("Factura ha sido marcada como pagada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    System.err.println("Test: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar", Dialogs.ERROR_ICON);
                }
            }
        }
    }
    
    public void pagarCompra(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada una compra", 
                    "con un valor de: "+Cuentas.getValueAt(fila, 3).toString()+" Lps. por medidas de seguridad", 
                    "Por favor ingrese su contraseña de usuario para pagar.", Dialogs.WARNING_ICON)){
                int CompraID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                CompraJpaController controllerCompra = new CompraJpaController(Conection.CreateEntityManager());
                Compra compra = controllerCompra.findCompra(CompraID);
                compra.setEstado("P");

                try {
                    controllerCompra.edit(compra);
                    CargarCliente();
                    Dialogs.ShowMessageDialog("La compra ha sido marcada como pagada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    System.err.println("Test: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar", Dialogs.ERROR_ICON);
                }
            }
        }
    }
    
    public void pagarFacturas(){
        if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada todas las facturas", 
                    "pendientes de pago con un valor total de: "+Total.getText()+" Lps. ", 
                    "por seguridad por favor ingrese su contraseña para pagar.", Dialogs.WARNING_ICON)){
            boolean state = true;
            
            int fila = 0;
            
            while (fila < Cuentas.getRowCount()){
                int VentaID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                VentaJpaController controllerVenta = new VentaJpaController(Conection.CreateEntityManager());
                Venta venta = controllerVenta.findVenta(VentaID);
                venta.setEstado("P");
                
                try {
                    controllerVenta.edit(venta);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    System.err.println("Test: "+ex.getMessage());
                    state = false;
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar la factura", Dialogs.ERROR_ICON);
                }
                fila++;
            }
            
            if(state){
                CargarCliente();
                Dialogs.ShowMessageDialog("Las facturas han sido marcadas como pagadas exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
    }
    
    public void pagarCompras(){
        if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada todas las compras", 
                    "pendientes de pago con un valor total de: "+Total.getText()+" Lps. ", 
                    "por seguridad por favor ingrese su contraseña para pagar.", Dialogs.WARNING_ICON)){
            boolean state = true;
            
            int fila = 0;
            
            while (fila < Cuentas.getRowCount()){
                int CompraID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                CompraJpaController controllerCompra = new CompraJpaController(Conection.CreateEntityManager());
                Compra compra = controllerCompra.findCompra(CompraID);
                compra.setEstado("P");
                
                try {
                    controllerCompra.edit(compra);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    System.err.println("Test: "+ex.getMessage());
                    state = false;
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar la factura", Dialogs.ERROR_ICON);
                }
                fila++;
            }
            
            if(state){
                CargarCliente();
                Dialogs.ShowMessageDialog("Las facturas han sido marcadas como pagadas exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
    }
    
    public void updateTotal(){
        float total = 0;
        for(int i = 0; i < Cuentas.getModel().getRowCount(); i++){
            if(Cuentas.getValueAt(i, 3) != null){
                total += Float.parseFloat(Cuentas.getValueAt(i, 3).toString().replace(",", ""));
            }
        }
        Total.setText(getNumberFormat(total));
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
