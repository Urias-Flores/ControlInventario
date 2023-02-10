package ViewsControllers.Panels.Preferencias;

import Controllers.ClienteJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ClientesViewController {
    private ClienteJpaController controller;
    
    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Clientes;
    private JLabel Total;
    
    private JLabel Nombre;
    private JLabel Documento;
    private JLabel Correo;
    private JLabel Numero;
    private JTextArea Domicilio;
    
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public ClientesViewController(JTextField Buscar, JLabel Cargando, JTable Clientes, JLabel Total,JLabel Nombre, JLabel Documento, JLabel Correo, JLabel Numero, JTextArea Domicilio) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Clientes = Clientes;
        this.Total = Total;
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Domicilio = Domicilio;
        
        controller = new ClienteJpaController(Conection.createEntityManagerFactory());
        
        //Cargando modelo de tabla en tabla de clientes
        setModelTableClients();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando tabla de clientes
            loadClients();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableClients(){
        String[] columns = {"No. Cliente", "Nombre", "Documento", "Saldo"};
        model.setColumnIdentifiers(columns);
        
        Clientes.setModel(model);
        Clientes.getColumn("No. Cliente").setPreferredWidth(130);
        Clientes.getColumn("Nombre").setPreferredWidth(700);
        Clientes.getColumn("Documento").setPreferredWidth(200);
        Clientes.getColumn("Saldo").setPreferredWidth(200);
    }
    
    //Inside task only
    private void loadClients(){
        model.setRowCount(0);
        List<Object[]> clientes = Conection.createEntityManager()
                .createNativeQuery("SELECT ClienteID, Nombre, Documento, Saldo FROM cliente").getResultList();
        clientes.forEach(cliente -> {
            cliente[3] = getNumberFormat(Float.parseFloat(cliente[3].toString()));
            model.addRow(cliente);
        });
        updateTotal();
    }
    
    
    //Task
    public void loadClient(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            setLoad(true);
            Runnable run = () ->{
                Cliente cliente = controller.findCliente(Integer.valueOf(Clientes.getValueAt(fila, 0).toString()));
                Nombre.setText(cliente.getNombre());
                Documento.setText(cliente.getDocumento());
                Correo.setText(cliente.getCorreoElectronico());
                Numero.setText(cliente.getNumeroTelefono());
                Domicilio.setText(cliente.getDomicilio());
                setLoad(false);
            };
            new Thread(run).start();
        }
    }
    
    private void updateTotal(){
        float total = 0;
        for(int i = 0; i < Clientes.getRowCount(); i++){
            total += Float.parseFloat(Clientes.getValueAt(i, 3).toString().replace(",", ""));
        }
        Total.setText(getNumberFormat(total));
    }
    
    public void search(){
        TableRowSorter rowSorter = new TableRowSorter(Clientes.getModel());
        rowSorter.setRowFilter(RowFilter.
                regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1));
        Clientes.setRowSorter(rowSorter);
    }
    
    public void editClient(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyClienteDialog(Integer.parseInt(Clientes.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void deleteClient(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de la eliminacion del cliente seleccionado?", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () -> {
                    try {
                        controller.destroy(Integer.valueOf(Clientes.getValueAt(fila, 0).toString()));
                        Init();
                        setLoad(false);
                        Dialogs.ShowMessageDialog("El cliente ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        Dialogs.ShowMessageDialog("Ya existen datos enlazados a este cliente, no se pudo eliminar", Dialogs.ERROR_ICON);
                    }
                };
                new Thread(run).start();
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void showClientInformation(){
        int fila = Clientes.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowCuentasClienteDialog(Integer.parseInt(Clientes.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un cliente de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
