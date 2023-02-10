
package ViewsControllers.Dialogs;

import Models.Empleado;
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

public class EmpleadosDialogViewController {
    
    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Empleados;

    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };
    
    public EmpleadosDialogViewController(JTextField Buscar, JLabel Cargando, JTable Empleados) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Empleados = Empleados;
        
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
            loadEmployee();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableModel(){
        String[] columns = {"No. Empleado", "Nombre", "Identidad"};
        model.setColumnIdentifiers(columns);
        
        Empleados.setModel(model);
        Empleados.getColumn("No. Empleado").setPreferredWidth(70);
        Empleados.getColumn("Nombre").setPreferredWidth(300);
        Empleados.getColumn("Identidad").setPreferredWidth(200);
    }
    
    private void loadEmployee(){
        model.setRowCount(0);
        List<Empleado> empleados = Conection.createEntityManager()
                .createNamedQuery("Empleado.findAll").getResultList();
        empleados.forEach(empleado ->{
            Object[] row = {empleado.getEmpleadoID(), empleado.getNombre()+" "+empleado.getApellido(), empleado.getIdentidad()};
            model.addRow(row);
        });
    }
    
    public void search(){
        TableRowSorter s = new TableRowSorter(Empleados.getModel());
        s.setRowFilter(RowFilter.regexFilter
        (Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 0, 1, 2));
        Empleados.setRowSorter(s);
    }
}
