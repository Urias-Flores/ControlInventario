package ViewsControllers;

import Controllers.UsuarioJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Usuario;
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
    private JLabel lbContrasenaIcon;
    private JPasswordField Contrasena;
    private JLabel lbRepetirContrasena;
    private JLabel lbRepetirContrasenaIcon;
    private JPasswordField RepetirContrasena;
    private JLabel Boton;
    private JLabel Error;
    
    private String CodigoActual = "";
    private Usuario usuarioActual = null;

    public CreateAccountViewController(JTextField Nombre, JTextField Codigo, JLabel lbContrasena, JLabel lbContrasenaIcon, JPasswordField Contrasena, JLabel lbRepetirContrasena, JLabel lbRepetirContrasenaIcon, JPasswordField RepetirContrasena, JLabel Boton, JLabel Error) {
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.lbContrasena = lbContrasena;
        this.lbContrasenaIcon = lbContrasenaIcon;
        this.Contrasena = Contrasena;
        this.lbRepetirContrasena = lbRepetirContrasena;
        this.lbRepetirContrasenaIcon = lbRepetirContrasenaIcon;
        this.RepetirContrasena = RepetirContrasena;
        this.Boton = Boton;
        this.Error = Error;
        
        setVisibleContrasena(false);
    }

    
    
    private void setVisibleContrasena(boolean state){
        lbContrasena.setVisible(state);
        lbContrasenaIcon.setVisible(state);
        Contrasena.setVisible(state);
        lbRepetirContrasena.setVisible(state);
        lbRepetirContrasenaIcon.setVisible(state);
        RepetirContrasena.setVisible(state);
    }
    
    public int verificarUsuario(){
        if(validate()){
            sendEmail();
            setTokenOnUser();
            Nombre.setEnabled(false);
            Codigo.setEnabled(true);
            Codigo.requestFocus();
            Boton.setText("Verificar codigo");
            Error.setText("El codigo ha sido enviado exitosamente");
            Error.setBackground(new Color(33, 146, 65));
            return 1;
        }
        Error.setBackground(new Color(185, 0, 0));
        return 0;
    }
    
    private boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El nombre de usuario es obligatorio");
            return false;
        }
        if(!validateNombreExist()){
            Error.setText("El nombre de usuario ingresado no existe");
            return false;
        }
        if(!validateUserIsNew()){
            Error.setText("El usuario ingresado es un usuario existente o esta desactivado");
            return false;
        }
        return true;
    }
    
    public int verificarCodigo(){
        if(validateToken()){
            if(usuarioActual.getToken().equals(Codigo.getText())){
                Codigo.setEnabled(false);
                setVisibleContrasena(true);
                
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
            
            UsuarioJpaController controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
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
        UsuarioJpaController controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
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
        
        email.SendEmail(usuarioActual.getEmpleadoID().getCorreoElectronico(), Asunto, Mensaje);
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
    
    private boolean validateNombreExist(){
        UsuarioJpaController controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
        List<Usuario> usuarios = controller.findUsuarioEntities();
        for(Usuario usuario : usuarios){
            if(usuario.getNombre().equals(Nombre.getText())){
                usuarioActual = usuario;
                return true;
            }
        }
        return false;
    }
    
    private boolean validateUserIsNew(){
        UsuarioJpaController controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
        List<Usuario> usuarios = controller.findUsuarioEntities();
        for(Usuario usuario : usuarios){
            if(usuario.getNombre().equals(Nombre.getText())){
                return usuario.getEstado() == 0 && usuario.getToken() == null;
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
