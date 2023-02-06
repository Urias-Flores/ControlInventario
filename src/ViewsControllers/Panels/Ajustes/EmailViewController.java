package ViewsControllers.Panels.Ajustes;

import Controllers.ConfiguracionJpaController;
import Resource.LocalDataController;
import Controllers.UsuarioJpaController;
import Models.Configuracion;
import Models.Usuario;
import Resource.Code;
import Resource.Conection;
import Resource.Email;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class EmailViewController {
    private JRadioButton PorDefecto;
    private JRadioButton Personalizado;
    
    private JTextField Email;
    private JPasswordField Password;
    
    private JLabel Error;
    private JLabel Cargando;

    public EmailViewController(JRadioButton PorDefecto, JRadioButton Personalizado, JTextField Email, JPasswordField Password, JLabel Error, JLabel Cargando) {
        this.PorDefecto = PorDefecto;
        this.Personalizado = Personalizado;
        this.Email = Email;
        this.Password = Password;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    public void Init(){
        LocalDataController ldc = new LocalDataController();
        String value = ldc.getValue("PersonalizeEmail");
        
        if(!Boolean.parseBoolean(value)){
            PorDefecto.setSelected(true);
            setEmailOfDataBase();
        }else{
            Personalizado.setSelected(true);
            setEmailOfLocalData(ldc);
            Password.setText("Aqui va la contraseña");
        }
    }
    
    public void changeState(){
        if(PorDefecto.isSelected()){
            setEmailOfDataBase();
            Email.setEnabled(false);
            Password.setEnabled(false);
        }else{
            LocalDataController ldc = new LocalDataController();
            setEmailOfLocalData(ldc);
            Email.setEnabled(true);
            Password.setEnabled(true);
        }
    }
    
    public void saveChange(){
        if(validate()){
            LocalDataController ldc = new LocalDataController();
            if(Personalizado.isSelected()){
                if(sendTestEmail()){
                    Code code = new Code();
                    
                    ldc.UpdateData("PersonalizeEmail", "true");
                    ldc.UpdateData("Email", code.codeString(Email.getText()));
                    ldc.UpdateData("Password", code.codeString(String.valueOf(Password.getPassword())));
                    Dialogs.ShowMessageDialog("Los cambios han sido aplicados exitosamente", Dialogs.COMPLETE_ICON);
                }else{
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("Error los cambios no pudieron aplicarse");
                }
            }else{
                ldc.UpdateData("PersonalizeEmail", "false");
                ldc.UpdateData("Email", null);
                    ldc.UpdateData("Password", null);
                Dialogs.ShowMessageDialog("Los cambios han sido aplicados exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
    }
    
    private boolean sendTestEmail(){
        Email email = new Email();
        Usuario usuario = new UsuarioJpaController(Conection.createEntityManagerFactory()).findUsuario(Utilities.getUsuarioActual().getUsuarioID());
        String message = email.generateTryAccountMessage(usuario.getEmpleadoID().getNombre(), usuario.getNombre());
        
        return email.SendEmail(usuario.getEmpleadoID().getCorreoElectronico(), "Modificacion de envio de correos", message);
    }
    
    private boolean validate(){
        if(Email.getText().isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El correo electronico es obligatorio");
            return false;
        }
        if(!Email.getText().contains("@") || !Email.getText().contains(".")){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El correo electronico ingresado no es valido");
            return false;
        }
        if(String.valueOf(Password.getPassword()).isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La contraseña es obligatorio");
            return false;
        }
        if(Personalizado.isSelected()){
            if(String.valueOf(Password.getPassword()).length() != 16){
                Error.setBackground(new Color(185, 0, 0));
                Error.setText("La contraseña debe contener 16 caracteres");
                return false;
            }
        }
        return true;
    }
    
    private void setEmailOfDataBase(){
        ConfiguracionJpaController configController = new ConfiguracionJpaController(Conection.createEntityManagerFactory());
        Configuracion config = configController.findConfiguracion(1);

        Code code = new Code();

        String email = code.decodeString(config.getDato());

        Email.setText(email);
        Email.setEnabled(false);
        Password.setEnabled(false);
    }
    
    private void setEmailOfLocalData(LocalDataController ldc){
        String email = ldc.getValue("Email");
        Email.setText(email == null ? "" : new Code().decodeString(email));
    }
}
