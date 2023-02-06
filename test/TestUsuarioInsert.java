
import Controllers.UsuarioJpaController;
import Models.Empleado;
import Models.Usuario;
import Resource.Conection;
import Resource.Security;

public class TestUsuarioInsert {
    
    public static void main(String[] args){
        Usuario usuario = new Usuario();
        usuario.setNombre("Mario");
        usuario.setEmpleadoID(new Empleado(2));
        usuario.setContrasena(Security.generateStrongPasswordHash("alone2020"));
        usuario.setToken(null);
        usuario.setEstado(1);
        
        UsuarioJpaController controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
        controller.create(usuario);
    }
    
}
