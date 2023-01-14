package ViewsControllers.Panels.Preferencias;

import Controllers.ProveedorJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Proveedor;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProveedoresViewController {
    ProveedorJpaController controller;
    
    JTextField Buscar;
    JTable Proveedores;
    
    JLabel Nombre;
    JLabel RTN;
    JLabel Correo;
    JLabel Numero;
    JTextArea Domicilio;

    public ProveedoresViewController(JTextField Buscar, JTable Proveedores, JLabel Nombre, JLabel RTN, JLabel Correo, JLabel Numero) {
        this.Buscar = Buscar;
        this.Proveedores = Proveedores;
        this.Nombre = Nombre;
        this.RTN = RTN;
        this.Correo = Correo;
        this.Numero = Numero;
        
        controller = new ProveedorJpaController(Conection.CreateEntityManager());
    }
    
    public void CargarProveedores(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Proveedor", "Nombre", "RTN"};
        model.setColumnIdentifiers(columns);
        List<Proveedor> proveedores = new ProveedorJpaController(Conection.CreateEntityManager()).findProveedorEntities();
        
        proveedores.forEach(proveedor -> {
            Object[] row = {proveedor.getProveedorID(), proveedor.getNombre(), proveedor.getRtn()};
            model.addRow(row);
        });
        
        Proveedores.setModel(model);
        
        Proveedores.getColumn("No. Proveedor").setPreferredWidth(140);
        Proveedores.getColumn("Nombre").setPreferredWidth(700);
        Proveedores.getColumn("RTN").setPreferredWidth(200);
    }
    
    public void CargarProveedor(){
        int fila = Proveedores.getSelectedRow();
        if(fila >= 0){
            Proveedor proveedor = controller.findProveedor(Integer.valueOf(Proveedores.getValueAt(fila, 0).toString()));
            Nombre.setText(proveedor.getNombre());
            RTN.setText(proveedor.getRtn());
            Correo.setText(proveedor.getCorreoElectronico());
            Numero.setText(proveedor.getNumeroTelefono());
        }
    }
    
    public void Edit(){
        int fila = Proveedores.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyProveedorDialog(Integer.parseInt(Proveedores.getValueAt(fila, 0).toString()));
            CargarProveedores();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un proveedor de la lisa", Dialogs.ERROR_ICON);
        }
    }
    
    public void Delete(){
       int fila = Proveedores.getSelectedRow();
       if(fila >= 0){
           if(Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar el proveedor seleccionado?", Dialogs.WARNING_ICON)){
                try {
                    controller.destroy(Integer.valueOf(Proveedores.getValueAt(fila, 0).toString()));
                    CargarProveedores();
                    Dialogs.ShowMessageDialog("El proveedor ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    Dialogs.ShowMessageDialog("Proveedore no pudo ser eliminada, exista una relacion con este dato", Dialogs.ERROR_ICON);
                }
           }
       }else{
           Dialogs.ShowMessageDialog("Seleccione un proveedore de la lista", Dialogs.ERROR_ICON);
       }
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Proveedores.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1));
        Proveedores.setRowSorter(s);
    }
}
