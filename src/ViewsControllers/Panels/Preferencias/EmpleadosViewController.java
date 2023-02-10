package ViewsControllers.Panels.Preferencias;

import Controllers.EmpleadoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EmpleadosViewController {
    private EmpleadoJpaController controller;
    
    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Empleados;
    
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {  return false; }
    };

    public EmpleadosViewController(JTextField Buscar, JLabel Cargando, JTable Empleados) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Empleados = Empleados;
        
        controller = new EmpleadoJpaController(Conection.createEntityManagerFactory());
        
        //Cargando model de tabla en tabla de empleados
        setModelTableEMployees();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando datos de empleados
            loadEmployees();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableEMployees(){
        String[] columns = {"No. Empleado", "Nombre completo", "No. Identidad"};
        model.setColumnIdentifiers(columns);
        
        Empleados.setModel(model);
        Empleados.getColumn("No. Empleado").setPreferredWidth(120);
        Empleados.getColumn("Nombre completo").setPreferredWidth(350);
        Empleados.getColumn("No. Identidad").setPreferredWidth(200);
    }
    
    //Inside Task
    private void loadEmployees(){
        model.setRowCount(0);
        List<Empleado> empleados = controller.findEmpleadoEntities();
        empleados.forEach(empleado ->{
            Object[] row = {
                empleado.getEmpleadoID(), 
                empleado.getNombre() + " " + empleado.getApellido(), 
                empleado.getIdentidad()
            };
            model.addRow(row);
        });
    }
    
    public void search(){
        TableRowSorter s = new TableRowSorter(Empleados.getModel());
        s.setRowFilter(RowFilter.regexFilter
        (Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1, 2, 3));
        Empleados.setRowSorter(s);
    }
    
    public void infoEmployee(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoEmpleadoDialog(Integer.parseInt(Empleados.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void editEmployee(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyEmpleadoDialog(Integer.parseInt(Empleados.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void deleteEmployee(){
        int fila = Empleados.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("La eliminacion de un empleado puede causar la inconsistencia", 
                    "de datos dentro de la base de datos.", 
                    "Para continuar con la eliminacion escriba su contraseña", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () -> {
                    try {
                        controller.destroy(Integer.valueOf(Empleados.getValueAt(fila, 0).toString()));
                        Init();
                        
                        setLoad(false);
                        Dialogs.ShowMessageDialog("El empleado a sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (IllegalOrphanException | NonexistentEntityException ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Este empleado se encuentra enlazado a otros datos", fila);
                    }
                };
                new Thread(run).start();
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un empleado de la lista", Dialogs.ERROR_ICON);
        }
    }
}
