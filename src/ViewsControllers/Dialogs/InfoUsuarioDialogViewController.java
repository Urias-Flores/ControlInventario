package ViewsControllers.Dialogs;

import Controllers.UsuarioJpaController;
import Models.Usuario;
import Resource.Conection;
import javax.swing.JLabel;

public class InfoUsuarioDialogViewController {
    private JLabel Nombre;
    private JLabel Empleado;
    private JLabel Identidad;
    private JLabel Estado;
    private JLabel Cargo;

    public InfoUsuarioDialogViewController(JLabel Nombre, JLabel Empleado, JLabel Identidad, JLabel Estado, JLabel Cargo) {
        this.Nombre = Nombre;
        this.Empleado = Empleado;
        this.Identidad = Identidad;
        this.Estado = Estado;
        this.Cargo = Cargo;
    }
    
    public void cargarUsuario(int UsuarioID){
        Usuario usuario = new UsuarioJpaController(Conection.createEntityManagerFactory()).findUsuario(UsuarioID);
        
        Nombre.setText(usuario.getNombre());
        Empleado.setText(usuario.getEmpleadoID().getNombre()+" "+usuario.getEmpleadoID().getApellido());
        Identidad.setText(usuario.getEmpleadoID().getIdentidad());
        Estado.setText(usuario.getEstado() == 1 ? "Activo" : "Inactivo");
        Cargo.setText(usuario.getCargo().equals("A") ? "Administrador" : "Dependiente");
    }
}
