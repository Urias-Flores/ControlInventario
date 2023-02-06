package ViewsControllers.Dialogs;

import Controllers.ClienteJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddClienteViewController {
    
    ClienteJpaController controller;
    
    JTextField Nombre;
    JTextField Documento;
    JTextField Correo;
    JTextField Numero;
    JTextArea Domicilio;
    JLabel Error;

    public AddClienteViewController(JTextField Nombre, JTextField Documento, JTextField Correo, JTextField Numero, JTextArea Domicilio, JLabel Error) {
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Domicilio = Domicilio;
        this.Error = Error;
        
        controller = new ClienteJpaController(Conection.createEntityManagerFactory());
    }
    
    public boolean Insert(){
        if(validate()){
            Cliente cliente = CreateObjectCLiente();
            controller.create(cliente);
            return true;
        }
        return false;
    }
    
    public void setEditCliente(int ClienteID){
        Cliente cliente = controller.findCliente(ClienteID);
        Nombre.setText(cliente.getNombre());
        Nombre.setName(String.valueOf(cliente.getClienteID()));
        Nombre.setForeground(Color.BLACK);
        Documento.setText(cliente.getDocumento());
        Documento.setForeground(Color.BLACK);
        Correo.setText(cliente.getCorreoElectronico());
        Correo.setForeground(Color.BLACK);
        Numero.setText(cliente.getNumeroTelefono());
        Numero.setForeground(Color.BLACK);
        Domicilio.setText(cliente.getDomicilio());
        Domicilio.setForeground(Color.BLACK);
    }
    
    public boolean Edit(){
        if(validate()){
            try {
                Cliente cliente = CreateObjectCLiente();
                controller.edit(cliente);
            } catch (NonexistentEntityException ex) {
                Dialogs.ShowMessageDialog("Ups.. Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                return false;
            } catch (Exception ex) {
                Logger.getLogger(AddClienteViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }
    
    public Cliente CreateObjectCLiente(){
        Cliente cliente = new Cliente();
        if(Nombre.getName() != null){
            cliente.setClienteID(Integer.valueOf(Nombre.getName()));
        }
        cliente.setNombre(Nombre.getText());
        cliente.setDocumento(Documento.getText());
        cliente.setCorreoElectronico(Correo.getText());
        cliente.setNumeroTelefono(Numero.getText());
        cliente.setDomicilio(Domicilio.getText());
        return cliente;
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del cliente es obligatorio");
            return false;
        }
        if(Documento.getText().isEmpty() || Documento.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El documento del cliente es obligatorio");
            return false;
        }
        if(Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El correo electronico del cliente es obligatorio");
            return false;
        }
        if(Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El numero telefonico del cliente es obligatorio");
            return false;
        }
        if(Domicilio.getText().isEmpty() || Domicilio.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El domicilio del cliente es obligatorio");
            return false;
        }
        return true;
    }
}
