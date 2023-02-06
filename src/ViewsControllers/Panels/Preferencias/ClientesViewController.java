package ViewsControllers.Panels.Preferencias;

import Controllers.ClienteJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ClientesViewController {
    ClienteJpaController controller;
    
    JTextField Buscar;
    JTable Clientes;
    JLabel Total;
    
    JLabel Nombre;
    JLabel Documento;
    JLabel Correo;
    JLabel Numero;
    JTextArea Domicilio;

    public ClientesViewController(JTextField Buscar, JTable Clientes, JLabel Total,JLabel Nombre, JLabel Documento, JLabel Correo, JLabel Numero, JTextArea Domicilio) {
        this.Buscar = Buscar;
        this.Clientes = Clientes;
        this.Total = Total;
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Domicilio = Domicilio;
        
        controller = new ClienteJpaController(Conection.createEntityManagerFactory());
    }
    
    public void CargarClientes(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Cliente", "Nombre", "Documento", "Saldo"};
        model.setColumnIdentifiers(columns);
        
        List<Object[]> clientes = 
                controller.getEntityManager()
                        .createNativeQuery("SELECT ClienteID, Nombre, Documento, Saldo FROM Cliente").getResultList();
        
        clientes.forEach(cliente -> {
            cliente[3] = getNumberFormat(Float.parseFloat(cliente[3].toString()));
            model.addRow(cliente);
        });
        
        Clientes.setModel(model);
        
        Clientes.getColumn("No. Cliente").setPreferredWidth(120);
        Clientes.getColumn("Nombre").setPreferredWidth(700);
        Clientes.getColumn("Documento").setPreferredWidth(200);
        Clientes.getColumn("Saldo").setPreferredWidth(200);
        
        updateTotal();
    }
    
    public void CargarCliente(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            Cliente cliente = controller.findCliente(Integer.valueOf(Clientes.getValueAt(fila, 0).toString()));
            Nombre.setText(cliente.getNombre());
            Documento.setText(cliente.getDocumento());
            Correo.setText(cliente.getCorreoElectronico());
            Numero.setText(cliente.getNumeroTelefono());
            Domicilio.setText(cliente.getDomicilio());
        }
    }
    
    private void updateTotal(){
        float total = 0;
        for(int i = 0; i < Clientes.getRowCount(); i++){
            total += Float.parseFloat(Clientes.getValueAt(i, 3).toString().replace(",", ""));
        }
        
        Total.setText(getNumberFormat(total));
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Clientes.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1));
        Clientes.setRowSorter(s);
    }
    
    public void Edit(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyClienteDialog(Integer.parseInt(Clientes.getValueAt(fila, 0).toString()));
            CargarClientes();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void Delete(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de la eliminacion del cliente seleccionado?", Dialogs.WARNING_ICON)){
                try {
                    controller.destroy(Integer.valueOf(Clientes.getValueAt(fila, 0).toString()));
                    CargarClientes();
                    Dialogs.ShowMessageDialog("El cliente ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Ya existen datos enlazados a este cliente, no se pudo eliminar", Dialogs.ERROR_ICON);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void mostrarInformacionCuenta(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowCuentasClienteDialog(Integer.parseInt(Clientes.getValueAt(fila, 0).toString()));
            CargarClientes();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
