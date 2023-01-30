package ViewsControllers.Panels.Ajustes;

import Controllers.LocalDataController;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EmpresaViewController {
    
    private JTextField Nombre;
    private JTextField RTN;
    private JTextField Telefono;
    private JTextField Direccion;
    
    private JLabel lbNombre;
    private JLabel lbRTN;
    private JLabel lbTelefono;
    private JLabel lbDireccion;
    
    private JLabel Error;

    public EmpresaViewController(JTextField Nombre, JTextField RTN, JTextField Telefono, JTextField Direccion, JLabel lbNombre, JLabel lbRTN, JLabel lbTelefono, JLabel lbDireccion, JLabel Error) {
        this.Nombre = Nombre;
        this.RTN = RTN;
        this.Telefono = Telefono;
        this.Direccion = Direccion;
        this.lbNombre = lbNombre;
        this.lbRTN = lbRTN;
        this.lbTelefono = lbTelefono;
        this.lbDireccion = lbDireccion;
        this.Error = Error;
    }
    
    public void cargarInformacionActual(){
        LocalDataController ldc = new LocalDataController();
        
        lbNombre.setText(ldc.getValue("Company"));
        lbRTN.setText(ldc.getValue("RTN"));
        lbTelefono.setText(ldc.getValue("NumberPhone"));
        lbDireccion.setText(ldc.getValue("Address"));
    }

    public boolean guardarInformacion(){
        if(validate()){
            LocalDataController ldc = new LocalDataController();
            
            ldc.UpdateData("Company", Nombre.getText());
            ldc.UpdateData("RTN", RTN.getText());
            ldc.UpdateData("NumberPhone", Telefono.getText());
            ldc.UpdateData("Address", Direccion.getText());
            clear();
            return true;
        }
        Error.setBackground(new Color(185, 0, 0));
        return false;
    }
    
    private boolean validate(){
        Color emptyColor = new Color(180, 180, 180);
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(emptyColor)){
            Error.setText("El nombre de la empresa es obligatorio");
            return false;
        }
        if(RTN.getText().isEmpty() || RTN.getForeground().equals(emptyColor)){
            Error.setText("El RTN de la empresa es obligatorio");
            return false;
        }
        if(Telefono.getText().isEmpty() || Telefono.getForeground().equals(emptyColor)){
            Error.setText("El Numero telefonico de la empresa es obligatorio");
            return false;
        }
        if(Direccion.getText().isEmpty() || Direccion.getForeground().equals(emptyColor)){
            Error.setText("El Direccion de la empresa es obligatorio");
            return false;
        }
        return true;
    }
    
    private void clear(){
        Color emptyColor = new Color(180, 180, 180);
        Nombre.setText("Escribe el nombre de la empresa...");
        Nombre.setForeground(emptyColor);
        RTN.setText("Escribe el RTN de la empresa...");
        RTN.setForeground(emptyColor);
        Telefono.setText("Escribe el numero de telefono de la empresa...");
        Telefono.setForeground(emptyColor);
        Direccion.setText("Escribe el direccion principal de la empresa...");
        Direccion.setForeground(emptyColor);
        Error.setBackground(Color.white);
    }
}
