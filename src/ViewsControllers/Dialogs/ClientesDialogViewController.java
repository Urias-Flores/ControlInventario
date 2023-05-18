
package ViewsControllers.Dialogs;

import Models.Cliente;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ClientesDialogViewController {
    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Clientes;

    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };
    
    public ClientesDialogViewController(JTextField Buscar, JLabel Cargando, JTable Empleados) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Clientes = Empleados;
        
        //Cargando model de tabla de empleados
        setModelTableModel();
        
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
            //Cargando lista de empleados
            loadClients();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableModel(){
        String[] columns = {"No.", "Nombre", "Documento", "R.T.N", "Domicilio"};
        model.setColumnIdentifiers(columns);
        
        Clientes.setModel(model);
        Clientes.getColumn("No.").setPreferredWidth(30);
        Clientes.getColumn("Nombre").setPreferredWidth(200);
        Clientes.getColumn("Documento").setPreferredWidth(120);
        Clientes.getColumn("R.T.N").setPreferredWidth(120);
        Clientes.getColumn("Domicilio").setPreferredWidth(220);
    }
    
    private void loadClients(){
        model.setRowCount(0);
        List<Cliente> clientes = Conection.createEntityManager()
                .createNamedQuery("Cliente.findAll").getResultList();
        
        //Agregando consumidor final al principio
        Cliente consumidorFinal = (Cliente) Conection.createEntityManager().createNamedQuery("Cliente.findByClienteID")
                .setParameter("clienteID", 1)
                .getSingleResult();
        
        Object[] consumidorFinalRow = {
            consumidorFinal.getClienteID(), 
            consumidorFinal, 
            consumidorFinal.getDocumento(),
            consumidorFinal.getRtn(),
            consumidorFinal.getDomicilio()
        };
        model.addRow(consumidorFinalRow);
        
        //Agregando demas clientes
        clientes.forEach(cliente ->{
            if(cliente.getClienteID() != 1){
                Object[] row = {
                    cliente.getClienteID(), 
                    cliente, 
                    cliente.getDocumento(),
                    cliente.getRtn(),
                    cliente.getDomicilio()
                };
                model.addRow(row); 
            }  
        });
    }
    
    public void search(){
        TableRowSorter s = new TableRowSorter(Clientes.getModel());
        s.setRowFilter(RowFilter.regexFilter
        (Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 0, 1, 2, 3, 4));
        Clientes.setRowSorter(s);
    }
}
