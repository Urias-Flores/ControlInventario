package ViewsControllers.Dialogs;

import Controllers.ProveedorJpaController;
import Models.Proveedor;
import Resource.Conection;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddProveedorDialogViewController {
    ProveedorJpaController controller;
    
    JTextField Nombre;
    JTextField RNT;
    JTextField Correo;
    JTextField Numero;
    JLabel Error;

    public AddProveedorDialogViewController(JTextField Nombre, JTextField RNT, JTextField Correo, JTextField Numero, JLabel Error) {
        this.Nombre = Nombre;
        this.RNT = RNT;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Error = Error;
        controller = new ProveedorJpaController(Conection.createEntityManagerFactory());
    }
    
    public boolean Insert(){
        if(validate()){
            Proveedor proveedor = createObjectProveedor();
            controller.create(proveedor);
            return true;
        }
        return false;
    }
    
    public boolean Edit(){
        if(validate()){
            try {
                Proveedor proveedor = createObjectProveedor();
                controller.edit(proveedor);
                return true;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return false;
            }
        }
        return false;
    }
    
    public Proveedor createObjectProveedor(){
        Proveedor proveedor = new Proveedor();
        if(Nombre.getName() != null){
            proveedor.setProveedorID(Integer.valueOf(Nombre.getName()));
        }
        proveedor.setNombre(Nombre.getText());
        proveedor.setRtn(RNT.getText());
        proveedor.setCorreoElectronico(Correo.getText().toLowerCase());
        proveedor.setNumeroTelefono(Numero.getText());
        
        return proveedor;
    }
    
    public void setEditing(int ProveedorID){
        Proveedor proveedor = controller.findProveedor(ProveedorID);
        Nombre.setText(proveedor.getNombre());
        Nombre.setName(String.valueOf(proveedor.getProveedorID()));
        Nombre.setForeground(Color.BLACK);
        RNT.setText(proveedor.getRtn());
        RNT.setForeground(Color.BLACK);
        Correo.setText(proveedor.getCorreoElectronico());
        Correo.setForeground(Color.BLACK);
        Numero.setText(proveedor.getNumeroTelefono());
        Numero.setForeground(Color.BLACK);
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del proveedor es obligatorio");
            return false;
        }
        if(RNT.getText().isEmpty() || RNT.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El RTN del proveedor es obligatorio");
            return false;
        }
        if(Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El correo electronico del proveedor es obligatorio");
            return false;
        }
        if(Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El numero telefonico del proveedor es obligatorio");
            return false;
        }
        return true;
    }
}
