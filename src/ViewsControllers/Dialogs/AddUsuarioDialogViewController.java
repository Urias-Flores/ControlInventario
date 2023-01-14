package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Controllers.UsuarioJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Models.Usuario;
import Resource.Conection;
import Resource.Security;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddUsuarioDialogViewController {
    private UsuarioJpaController controller;
    
    private JTextField Nombre;
    private JComboBox Cargo;
    private JTextField Empleado;
    private JLabel Error;
    
    private Usuario currentUsuario = new Usuario();

    public AddUsuarioDialogViewController(JTextField Nombre, JComboBox Cargo,JTextField Empleado ,JLabel Error) {
        this.Nombre = Nombre;
        this.Cargo = Cargo;
        this.Empleado = Empleado;
        this.Error = Error;
        
        controller = new UsuarioJpaController(Conection.CreateEntityManager());
    }
    
    public boolean Insert(){
        if(validate()){
            Usuario usuario = CreateObjectUsuario();
            controller.create(usuario);
            return true;
        }
        return false;
    }
    
    public boolean Edit(){
        if(validate()){
            try {
                Usuario usuario = CreateObjectUsuario();
                controller.edit(usuario);
                return true;
            } catch (NonexistentEntityException ex) {
                Dialogs.ShowMessageDialog("Los datos de este usuario se encuentran enlzados a otros. No se puedo editar", Dialogs.ERROR_ICON);
            } catch (Exception ex) {
                System.err.println("Test: "+ex.getMessage());
                Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
            }
        }
        return false;
    }
    
    public void setEditing(int UsuarioID){
        currentUsuario = controller.findUsuario(UsuarioID);
        
        Nombre.setText(currentUsuario.getNombre());
        Nombre.setName(String.valueOf(UsuarioID));
        Nombre.setForeground(Color.BLACK);
        Empleado.setText(currentUsuario.getEmpleadoID().getNombre()+" "+currentUsuario.getEmpleadoID().getApellido());
        Empleado.setName(String.valueOf(currentUsuario.getEmpleadoID().getEmpleadoID()));
        Empleado.setForeground(Color.BLACK);
        Cargo.setSelectedIndex(currentUsuario.getCargo().equals("A") ? 1 : 2);
    }
    
    public Usuario CreateObjectUsuario(){
        
        Usuario usuario = new Usuario();
        double randomValue = 100000 + Math.random() * 999999;
        Empleado empleado = new EmpleadoJpaController(Conection.CreateEntityManager())
                .findEmpleado(Integer.valueOf(Empleado.getName()));
        
        //Verificar si se esta editando
        if(Nombre.getName() != null){
            usuario = currentUsuario;
            usuario.setUsuarioID(Integer.valueOf(Nombre.getName()));
        }
        
        //En caso de que no se este editando
        if(Nombre.getName() == null){
            usuario.setContrasena(Security.generateStrongPasswordHash(String.valueOf(randomValue).substring(0, 6)));
            usuario.setToken(null);
            usuario.setEstado(0);
        }
        
        usuario.setEmpleadoID(empleado);
        usuario.setNombre(Nombre.getText());
        usuario.setCargo(Cargo.getSelectedIndex() == 1 ? "A" : "D");
        
        return usuario;
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El nombre de usuario es obligatorio");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        
        if(Cargo.getSelectedIndex() == 0){
            Error.setText("Debe seleccionar el cargo del usuario");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        
        if(Empleado.getText().isEmpty() || Empleado.getForeground().equals(new Color(180, 180, 180)) || Empleado.getName() == null){
            Error.setText("La seleccion de un empleado es obligatoria");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        return true;
    }
}
