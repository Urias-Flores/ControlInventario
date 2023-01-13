package ViewsControllers;

import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AjustesConexionViewController {
    private JTextField DirreccionIP;
    private JTextField Puerto;
    private JTextField Usuario;
    private JPasswordField Contrasena;
    
    private JLabel Error;

    public AjustesConexionViewController(JTextField DirreccionIP, JTextField Puerto, JTextField Usuario, JPasswordField Contrasena, JLabel Error) {
        this.DirreccionIP = DirreccionIP;
        this.Puerto = Puerto;
        this.Usuario = Usuario;
        this.Contrasena = Contrasena;
        this.Error = Error;
    }
    
    public boolean GuardarCambios(){
        if(validate()){
            boolean result = Conection.alterConnection(DirreccionIP.getText(), Puerto.getText(), Usuario.getText(), String.valueOf(Contrasena.getPassword()));
            if(result){
                return true;
            }
            Dialogs.ShowMessageDialog("No se ha podido actualizar los datos de conexion", Dialogs.ERROR_ICON);
        }
        return false;
    }
    
    private boolean validate(){
        if(DirreccionIP.getText().isEmpty() || DirreccionIP.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La direccion IP es obligatoria");
            return false;
        }
        if(Puerto.getText().isEmpty() || Puerto.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El puerto de conexion es obligatoria");
            return false;
        }
        if(Usuario.getText().isEmpty() || Usuario.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El usuario es obligatorio");
            return false;
        }
        if(String.valueOf(Contrasena.getPassword()).isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La contraseña de acceso es obligatoria");
            return false;
        }
        return true;
    }
}
