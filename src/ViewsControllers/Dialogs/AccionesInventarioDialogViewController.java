package ViewsControllers.Dialogs;

import Controllers.InventariodetalleaccionesJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.util.List;
import javax.persistence.Query;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AccionesInventarioDialogViewController {
    JTextField Buscar;
    JTable Acciones;

    public AccionesInventarioDialogViewController(JTextField Buscar, JTable Acciones) {
        this.Buscar = Buscar;
        this.Acciones = Acciones;
    }
    
    public void CargarAcciones(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Cd.", "Producto", "Usuario", "Fecha", "Accion", "Ext. previa", "Cant. modificada"};
        model.setColumnIdentifiers(columns);
        
        Query query = Conection.createEntityManager().createNativeQuery("Select * FROM ViewAccionesInventario");
        List<Object[]> acciones = query.getResultList();
        acciones.forEach((accion) ->{
            Object[] row = {
                accion[0],
                accion[1],
                accion[2],
                accion[3],
                accion[6] = accion[6].toString().equals("E") ? "Eliminacion" : "Modificacion",
                accion[7],
                accion[8]
            };
            
            model.addRow(row);
        });
        
        Acciones.setModel(model);
        
        Acciones.getColumn("Cd.").setPreferredWidth(40);
        Acciones.getColumn("Producto").setPreferredWidth(450);
        Acciones.getColumn("Usuario").setPreferredWidth(110);
        Acciones.getColumn("Fecha").setPreferredWidth(70);
        Acciones.getColumn("Accion").setPreferredWidth(80);
        Acciones.getColumn("Ext. previa").setPreferredWidth(70);
        Acciones.getColumn("Cant. modificada").setPreferredWidth(70);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Acciones.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 0, 1, 4));
        Acciones.setRowSorter(s);
    }
    
    public void InformacionCompletaAccion(){
        int fila = Acciones.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoInventarioAccion(Integer.parseInt(Acciones.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione una accion de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void EliminarAccion(){
        int fila = Acciones.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de eliminar la accion seleccionada?", Dialogs.WARNING_ICON)){
                InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.createEntityManagerFactory());
                try {
                    controller.destroy(Integer.valueOf(Acciones.getValueAt(fila, 0).toString()));
                    CargarAcciones();
                    Dialogs.ShowMessageDialog("La accion ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException ex) {
                    System.err.println("Error: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ups... ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una accion de la lista", Dialogs.ERROR_ICON);
        }
    }
}
