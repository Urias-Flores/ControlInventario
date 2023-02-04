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

public class RecoverPasswordViewController {
    private JTextField Nombre;
    private JTextField Codigo;
    private JLabel ContrasenaLabel;
    private JLabel RepetirContrasenaLabel;
    private JPasswordField Contrasena;
    private JLabel ContrasenaIcon;
    private JPasswordField RepetirContrasena;
    private JLabel RepetirContrasenaIcon;
    private JLabel Error;
    private JLabel Cambiar;
    private String codigoActual = "";

    public RecoverPasswordViewController(JTextField Nombre, JTextField Codigo, JLabel ContrasenaLabel, JLabel RepetirContrasenaLabel, JPasswordField Contrasena, JLabel ContrasenaIcon, JPasswordField RepetirContrasena, JLabel RepetirContrasenaIcon, JLabel Error, JLabel Cambiar) {
        this.Nombre = Nombre;
        this.Codigo = Codigo;
        this.ContrasenaLabel = ContrasenaLabel;
        this.RepetirContrasenaLabel = RepetirContrasenaLabel;
        this.Contrasena = Contrasena;
        this.ContrasenaIcon = ContrasenaIcon;
        this.RepetirContrasena = RepetirContrasena;
        this.RepetirContrasenaIcon = RepetirContrasenaIcon;
        this.Error = Error;
        this.Cambiar = Cambiar;
        
        setVisibleContrasena(false);
    }
    
    public int EnviarCodigo(){
        if(validateNombre()){
            if(validateUsuarioExiste()){
                Nombre.setEnabled(false);
                Codigo.setEnabled(true);
                Codigo.requestFocus();
                Cambiar.setText("Verificar codigo");
                
                
                Runnable run = ()->{
                    SendEmail();
                    setCodigoUsuario();
                    
                };
                Thread thread = new Thread(run);
                thread.start();
                
                
                
                Error.setBackground(new Color(33, 146, 65));
                Error.setText("El codigo de seguridad ha sido enviado");
                
                return 1;
            }
        }
        return 0;
    }
    
    public int verificarCodigo(){
        if(validateCodigo()){
            Usuario usuario = getUsuario();
            if(usuario.getToken().equals(Codigo.getText())){
                Codigo.setEnabled(false);
                ContrasenaLabel.setVisible(true);
                Contrasena.setVisible(true);
                RepetirContrasena.setVisible(true);
                RepetirContrasenaLabel.setVisible(true);
                Cambiar.setText("Guardar contraseña");
                Error.setBackground(new Color(33, 146, 65));
                Error.setText("El codigo ha sido verificado");
                
                return 1;
            }else{
                Error.setText("El codigo de seguridad es incorrecto");
                Error.setBackground(new Color(185, 0, 0));
            }
        }
        return 0;
    }
    
    public boolean guardarContrasena(){
        if(validateContrasenas()){
            Usuario usuario = getUsuario();
            usuario.setContrasena(Security.generateStrongPasswordHash(String.valueOf(Contrasena.getPassword())));
            usuario.setToken(null);
            UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
            try {
                controller.edit(usuario);
                return true;
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(RecoverPasswordViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(RecoverPasswordViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    private void setCodigoUsuario(){
        Usuario usuario = getUsuario();
        usuario.setToken(codigoActual);
        
        UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
        try {
            controller.edit(usuario);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(RecoverPasswordViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RecoverPasswordViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void SendEmail(){
        Email email = new Email();
        String[] values = getValuesForUsuario(getUsuario());
        String Mensaje = email.generateRecoverAccountMessage(values[0], values[1], values[2]);
        
        email.SendEmail(
                getUsuario().getEmpleadoID().getCorreoElectronico(), 
                "Reestablece tu contraseña", 
                Mensaje
        );
    }
    
    private String[] getValuesForUsuario(Usuario usuario){
        double randomValue = 100000 + Math.random() * 999999;
        codigoActual = String.valueOf(randomValue).substring(0, 6);
        String[] values = {
            usuario.getEmpleadoID().getNombre()+" "+usuario.getEmpleadoID().getApellido(),
            usuario.getNombre(),
            codigoActual
        };    
        return values;
    }
    
    private Usuario getUsuario(){
        UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
        List<Usuario> usuarios = controller.findUsuarioEntities();
        for(Usuario usuario : usuarios){
            if(usuario.getNombre().equals(Nombre.getText())){
                return usuario;
            }
        }
        return null;
    }
    
    private void setVisibleContrasena(boolean Estado){
        Contrasena.setVisible(Estado);
        ContrasenaIcon.setVisible(Estado);
        RepetirContrasena.setVisible(Estado);
        RepetirContrasenaIcon.setVisible(Estado);
        ContrasenaLabel.setVisible(Estado);
        RepetirContrasenaLabel.setVisible(Estado);
    }
    
    private boolean validateNombre(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre de usuario es obligatorio");
            return false;
        }
        return true;
    }
    
    private boolean validateCodigo(){
        if(Codigo.getText().isEmpty() || Codigo.getForeground().equals(new Color(180, 180, 80))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El codigo de seguridad es obligatorio");
            return false;
        }
        return true;
    }
    
    private boolean validateContrasenas(){
        if(String.valueOf(Contrasena.getPassword()).isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La nueva contraseña es obligatoria");
            return false;
        }
        if(String.valueOf(RepetirContrasena.getPassword()).isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La repeticion de la contraseña es obligatoria");
            return false;
        }
        if(!String.valueOf(Contrasena.getPassword()).equals(String.valueOf(RepetirContrasena.getPassword()))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("Las contraseña ingresadas no coinciden");
            return false;
        }
        return true;
    }
    
    private boolean validateUsuarioExiste(){
        UsuarioJpaController controller = new UsuarioJpaController(Conection.CreateEntityManager());
        List<Usuario> usuarios = controller.findUsuarioEntities();
        for(Usuario usuario : usuarios){
            if(usuario.getNombre().equals(Nombre.getText())){
                return true;
            }
        }
        Error.setBackground(new Color(185, 0, 0));
        Error.setText("El usuario ingresado no existe");
        return false;
    }
}
