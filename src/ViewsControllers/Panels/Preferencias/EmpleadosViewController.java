package ViewsControllers.Panels.Preferencias;

import Controllers.EmpleadoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EmpleadosViewController {
    EmpleadoJpaController controller;
    
    JTextField Buscar;
    JTable Empleados;

    public EmpleadosViewController(JTextField Buscar, JTable Empleados) {
        this.Buscar = Buscar;
        this.Empleados = Empleados;
        
        controller = new EmpleadoJpaController(Conection.createEntityManagerFactory());
    }
    
    public void CargarEmpleados(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Empleado", "Nombre completo", "No. Identidad"};
        model.setColumnIdentifiers(columns);
        
        List<Empleado> empleados = new EmpleadoJpaController(Conection.createEntityManagerFactory()).findEmpleadoEntities();
        empleados.forEach(empleado ->{
            Object[] row = {
                empleado.getEmpleadoID(), 
                empleado.getNombre() + " " + empleado.getApellido(), 
                empleado.getIdentidad()
            };
            model.addRow(row);
        });
        
        Empleados.setModel(model);
        
        Empleados.getColumn("No. Empleado").setPreferredWidth(120);
        Empleados.getColumn("Nombre completo").setPreferredWidth(350);
        Empleados.getColumn("No. Identidad").setPreferredWidth(200);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Empleados.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1, 2));
        Empleados.setRowSorter(s);
    }
    
    public void Info(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoEmpleadoDialog(Integer.parseInt(Empleados.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void Edit(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyEmpleadoDialog(Integer.parseInt(Empleados.getValueAt(fila, 0).toString()));
            CargarEmpleados();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void Delete(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("La eliminacion de un empleado puede causar la inconsistencia", 
                    "de datos dentro de la base de datos.", "Para continuar con la eliminacion escriba su contraseña", Dialogs.WARNING_ICON)){
                try {
                    controller.destroy(Integer.valueOf(Empleados.getValueAt(fila, 0).toString()));
                    CargarEmpleados();
                    Dialogs.ShowMessageDialog("El empleado a sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Este empleado se encuentra enlazado a otros datos", fila);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
}
