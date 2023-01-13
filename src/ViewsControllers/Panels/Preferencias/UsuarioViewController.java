package ViewsControllers.Panels.Preferencias;

import Controllers.UsuarioJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Usuario;
import Resource.Conection;
import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class UsuarioViewController {
    private UsuarioJpaController controller;
    
    private JTextField Buscar;
    private JTable Usuarios;

    public UsuarioViewController(JTextField Buscar, JTable Usuarios) {
        this.Buscar = Buscar;
        this.Usuarios = Usuarios;
        
        controller = new UsuarioJpaController(Conection.CreateEntityManager());
    }
    
    public void CargarUsuarios(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Usuario", "Nombre de usuario", "Empleado autorizado", "Estado"};
        model.setColumnIdentifiers(columns);
        
        List<Usuario> usuarios = new UsuarioJpaController(Conection.CreateEntityManager()).findUsuarioEntities();
        usuarios.forEach(usuario -> {
            Object[] row = {
                usuario.getUsuarioID(), 
                usuario.getNombre(), 
                usuario.getEmpleadoID().getNombre()+" "+usuario.getEmpleadoID().getApellido(), 
                usuario.getEstado() == 1 ? "Activo" : "Inactivo"
            };
            model.addRow(row);
        });
        
        Usuarios.setModel(model);
        
        Usuarios.getColumn("No. Usuario").setPreferredWidth(120);
        Usuarios.getColumn("Nombre de usuario").setPreferredWidth(450);
        Usuarios.getColumn("Empleado autorizado").setPreferredWidth(550);
        Usuarios.getColumn("Estado").setPreferredWidth(100);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Usuarios.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 2, 3));
        Usuarios.setRowSorter(s);
    }
    
    public void Edit(){
        int fila = Usuarios.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyUsuarioDialog(Integer.parseInt(Usuarios.getValueAt(fila, 0).toString()));
            CargarUsuarios();
        }
        else{
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void Delete(){
        int fila = Usuarios.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("Esta a punto de realizar la eliminacion de un usuario.", 
                    "No se podra eliminar en caso de estar enlazado a otros datos.", 
                    "Para continuar escriba su contraseña de usuario.", Dialogs.WARNING_ICON)){
                try {
                    controller.destroy(Integer.valueOf(Usuarios.getValueAt(fila, 0).toString()));
                    CargarUsuarios();
                    Dialogs.ShowMessageDialog("El usuario ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Los datos de este usuario estan enlazados a otros. No se pudo eliminar", Dialogs.ERROR_ICON);
                }
            }    
        }else{
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void Disable(){
        int fila = Usuarios.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("Esta seguro de la desactivacion/Activacion del usuario seleccionado", Dialogs.WARNING_ICON)){
                try {
                    Usuario usuario = controller.findUsuario(Integer.valueOf(Usuarios.getValueAt(fila, 0).toString()));
                    int State = Usuarios.getValueAt(fila, 3).toString().equals("Activo") ? 0 : 1;
                    usuario.setEstado(State);
                    controller.edit(usuario);
                    CargarUsuarios();
                    Dialogs.ShowMessageDialog("El usuario ha sido desactivado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Los datos de este usuario estan enlazados a otros. No se pudo eliminar", Dialogs.ERROR_ICON);
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }
}