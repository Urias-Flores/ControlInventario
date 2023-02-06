
package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Models.Empleado;
import Resource.Conection;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EmpleadosDialogViewController {
    
    JTextField Buscar;
    JTable Empleados;

    public EmpleadosDialogViewController(JTextField Buscar, JTable Empleados) {
        this.Buscar = Buscar;
        this.Empleados = Empleados;
    }
    
    public void CargarEmpleados(){
        
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Empleado", "Nombre", "Identidad"};
        model.setColumnIdentifiers(columns);
        
        List<Empleado> empleados = new EmpleadoJpaController(Conection.createEntityManagerFactory()).findEmpleadoEntities();
        empleados.forEach(empleado ->{
            Object[] row = {empleado.getEmpleadoID(), empleado.getNombre()+" "+empleado.getApellido(), empleado.getIdentidad()};
            model.addRow(row);
        });
        
        Empleados.setModel(model);
        
        Empleados.getColumn("No. Empleado").setPreferredWidth(70);
        Empleados.getColumn("Nombre").setPreferredWidth(300);
        Empleados.getColumn("Identidad").setPreferredWidth(200);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Empleados.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1));
        Empleados.setRowSorter(s);
    }
}
