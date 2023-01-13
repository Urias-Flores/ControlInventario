package ViewsControllers;

import Controllers.LocalDataController;
import Resource.Utilities;
import Views.Main;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginViewController {
    private JFrame Instance;
    private JTextField Nombre;
    private JPasswordField Contrasena;
    private JCheckBox Recordarme;
    private JLabel Error;
    private JLabel IniciarSesion;
    private JLabel Olvidaste;

    public LoginViewController(JFrame Instance, JTextField Nombre, JPasswordField Contrasena, JCheckBox Recordarme, JLabel Error, JLabel IniciarSesion, JLabel Olvidaste) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.Contrasena = Contrasena;
        this.Recordarme = Recordarme;
        this.Error = Error;
        this.IniciarSesion = IniciarSesion;
        this.Olvidaste = Olvidaste;
    }
    
    public void CargarUsuario(){
        LocalDataController ldc = new LocalDataController();
        if(ldc.getValue("RemenberState").equals("true")){
            Nombre.setForeground(Color.BLACK);
            Nombre.setText(ldc.getValue("User"));
            Recordarme.setSelected(true);
        }
    }
    
    public void IniciarSesion(){
        if(valite()){
            ActualizarRecordarme();
            int resultado = Utilities.IniciarSesion(Nombre.getText(), String.valueOf(Contrasena.getPassword()));
            switch (resultado) {
                case -2:
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El usuario ingresado se encuentra desactivado");
                    break;
                case -1:
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El usuario ingresado no existe");
                    break;
                case 0:
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("La contraseña es incorrecta");
                    break;
                default:
                    Main m = new Main();
                    Utilities.setUsuarioActual(resultado, Nombre.getText());
                    m.setVisible(true);
                    m.Cargar(Instance, Nombre.getText());
                    Instance.setVisible(false);
                    break;
            }
        }
    }
    
    private void ActualizarRecordarme(){
        LocalDataController ldc = new LocalDataController();
        if(Recordarme.isSelected()){
            ldc.UpdateData("User", Nombre.getText());
            ldc.UpdateData("RemenberState", "true");
        }else{
            ldc.UpdateData("RemenberState", "false");
        }
    }
    
    private boolean valite(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre de usuario es obligatorio");
            return false;
        }
        if(Contrasena.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La contraseña es obligatoria");
            return false;
        }
        return true;
    }
}
