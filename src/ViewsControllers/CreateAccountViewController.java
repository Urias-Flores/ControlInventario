package ViewsControllers;

import Controllers.ConfiguracionJpaController;
import Controllers.UsuarioJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Configuracion;
import Models.Usuario;
import Resource.Code;
import Resource.Conection;
import Resource.Email;
import Resource.Security;
import java.awt.Color;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class CreateAccountViewController {
    private JTextField Nombre;
    private JTextField Codigo;
    private JLabel lbContrasena;
    private JPasswordField Contrasena;
    private JLabel lbRepetirContrasena;
    private JPasswordField RepetirContrasena;
    private JLabel Boton;
    private JLabel Error;
    
    private String CodigoActual = "";
    private Usuario usuarioActual = null;

    public CreateAccountViewController(JTextField Nombre, JTextField Codigo, JLabel lbContrasena, JPasswordField Contrasena, JLabel lbRepetirContrasena, JPasswordField RepetirContrasena, JLabel Boton, JLabel Error) {
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.lbContrasena = lbContrasena;
        this.Contrasena = Contrasena;
        this.lbRepetirContrasena = lbRepetirContrasena;
        this.RepetirContrasena = RepetirContrasena;
        this.Boton = Boton;
        this.Error = Error;
        
        lbContrasena.setVisible(false);
        Contrasena.setVisible(false);
        lbRepetirContrasena.setVisible(false);
        RepetirContrasena.setVisible(false);
    }
    
    public int verificarUsuario(){
        if(validateNombre()){
            if(validateNombreExist()){
                sendEmail();
                setTokenOnUser();
                Nombre.setEnabled(false);
                Codigo.setEnabled(true);
                Codigo.requestFocus();
                Boton.setText("Verificar codigo");
                Error.setText("El codigo ha sido enviado exitosamente");
                Error.setBackground(new Color(33, 146, 65));
                return 1;
            }else{
                Error.setText("El usuario ingresado no existe");
                Error.setBackground(new Color(185, 0, 0));
            }
        }
        return 0;
    }
    
    public int verificarCodigo(){
        if(validateToken()){
            if(usuarioActual.getToken().equals(Codigo.getText())){
                Codigo.setEnabled(false);
                lbContrasena.setVisible(true);
                Contrasena.setVisible(true);
                lbRepetirContrasena.setVisible(true);
                RepetirContrasena.setVisible(true);
                
                Boton.setText("Crear cuenta");
                Error.setText("El codigo ha sido verificado");
                Error.setBackground(new Color(33, 146, 65));
                
                return 1;
            }
            Error.setText("El codigo ingresado no es valido");
            Error.setBackground(new Color(185, 0, 0));
        }
        return 0;
    }
    
    public boolean crearCuenta(){
        if(validateContrasenas()){
            usuarioActual.setContrasena(Security.generateStrongPasswordHash(String.valueOf(Contrasena.getPassword())));
            usuarioActual.setToken(null);
            usuarioActual.setEstado(1);
            
            UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
            try {
                controller.edit(usuarioActual);
                return true;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(CreateAccountViewController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (Exception ex) {
                Logger.getLogger(CreateAccountViewController.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }
        return false;
    }
    
    private void setTokenOnUser(){
        usuarioActual.setToken(CodigoActual);
        UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
        try {
            controller.edit(usuarioActual);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CreateAccountViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CreateAccountViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void sendEmail(){
        Email email = new Email();
        String[] values = getValuesForEmail();
        String Mensaje = email.generateCreateAccountMessage(values[0], values[1], values[2]);
        String Asunto = "Creacion de cuenta";
        
        String[] information = getInformationForSendEmail();
        
        email.SendEmail(information[0], information[1], usuarioActual.getEmpleadoID().getCorreoElectronico(), Asunto, Mensaje);
    }
    
    private String[] getValuesForEmail(){
        double randomValue = 100000 + Math.random() * 999999;
        CodigoActual = String.valueOf(randomValue).substring(0, 6);
        String[] values = {
            usuarioActual.getEmpleadoID().getNombre()+" "+usuarioActual.getEmpleadoID().getApellido(),
            Nombre.getText(),
            CodigoActual
        };
        
        return values;
    }
    
    private String[] getInformationForSendEmail(){
        Code code = new Code();
        Configuracion configuracion = new ConfiguracionJpaController(Conection.CreateEntityManager()).findConfiguracion(1);
        
        String email = code.decodeString(configuracion.getDato());
        String contrasena = code.decodeString(configuracion.getExtra());
        
        String[] values = {email, contrasena};
        return values;
    }
    
    private boolean validateNombre(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre de usuario es obligatorio");
            return false;
        }
        return true;
    }
    
    private boolean validateNombreExist(){
        UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
        List<Usuario> usuarios = controller.findUsuarioEntities();
        for(Usuario usuario : usuarios){
            if(usuario.getNombre().equals(Nombre.getText())){
                usuarioActual = usuario;
                return true;
            }
        }
        return false;
    }
    
    private boolean validateToken(){
        if(Codigo.getText().isEmpty() || Codigo.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El codigo de seguridad es obligatorio");
            return false;
        }
        return true;
    }
    
    public boolean validateContrasenas(){
        if(String.valueOf(Contrasena.getPassword()).length() < 8){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La contraseña debe contener al menos 8 caracteres");
            return false;
        }
        if(!String.valueOf(Contrasena.getPassword()).equals(String.valueOf(RepetirContrasena.getPassword()))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("Las contraseñas ingresadas no coinciden");
            return false;
        }   
        return true;
    }
}
