package ViewsControllers.Dialogs;

import Controllers.ClienteJpaController;
import Controllers.VentaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
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
            StoredProcedureQuery sp = Conection.CreateEntityManager().createEntityManager().createStoredProcedureQuery("ProcedureFacturaCliente")
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
                Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar", fila);
            }
        }
    }
    
    public void updateTotal(){
        float total = 0;
        for(int i = 0; i < Cuentas.getRowCount(); i++){
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
