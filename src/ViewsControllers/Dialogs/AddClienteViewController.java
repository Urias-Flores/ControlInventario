package ViewsControllers.Dialogs;

import Controllers.ClienteJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddClienteDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import javax.persistence.NoResultException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddClienteViewController {
    
    private ClienteJpaController controller;
    
    private AddClienteDialog Instance;
    private JTextField Nombre;
    private JTextField Documento;
    private JTextField RTN;
    private JTextField Correo;
    private JTextField Numero;
    private JTextArea Domicilio;
    private JLabel Error;
    private JLabel Cargando;

    public AddClienteViewController(AddClienteDialog Instance, JTextField Nombre, JTextField Documento, JTextField RTN, JTextField Correo, JTextField Numero, JTextArea Domicilio, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.Documento = Documento;
        this.RTN = RTN;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Domicilio = Domicilio;
        this.Error = Error;
        this.Cargando = Cargando;
        
        controller = new ClienteJpaController(Conection.createEntityManagerFactory());
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void insertClient(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                List<Cliente> clientes = controller.findClienteEntities();
                if(validateRepitData(clientes)){
                    Cliente cliente = createClientObject();
                    controller.create(cliente);

                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("El cliente ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                } else { Error.setBackground(new Color(185, 0, 0)); }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    public void setEditCliente(int ClienteID){
        setLoad(true);
        Runnable run = () -> {
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
            
            setLoad(false);
        };
        new Thread(run).start();
        
    }
    
    //Task
    public void editClient(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                List<Cliente> clientes = controller.findClienteEntities();
                if(validateRepitData(clientes)){
                    try {
                        Cliente cliente = createClientObject();
                        controller.edit(cliente);

                        setLoad(false);
                        Instance.setVisible(false);
                        Dialogs.ShowMessageDialog("El cliente ha sido modificado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Error, No se ha podido modificar el cliente", Dialogs.ERROR_ICON);
                    } catch (Exception ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Error, No se ha podido modificar el cliente", Dialogs.ERROR_ICON);
                    }
                    setLoad(false);
                } else { Error.setBackground(new Color(185, 0, 0)); }
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Insied Task
    private Cliente createClientObject(){
        Cliente cliente = new Cliente();
        if(Nombre.getName() != null){
            cliente = controller.findCliente(Integer.valueOf(Nombre.getName()));
        }
        cliente.setNombre(Nombre.getText());
        
        cliente.setDocumento
        (Documento.getText().isEmpty() || Documento.getForeground().equals(new Color(180, 180, 180)) ? null : Documento.getText());
        
        cliente.setRtn
        (RTN.getText().isEmpty() || RTN.getForeground().equals(new Color(180, 180, 180)) ? null : RTN.getText());
        
        cliente.setCorreoElectronico
        (Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180)) ? null : Correo.getText());
        
        cliente.setNumeroTelefono
        (Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180)) ? null : Numero.getText());
        
        cliente.setDomicilio
        (Domicilio.getText().isEmpty() ? null : Domicilio.getText());
        return cliente;
    }
    
    private boolean validate(){
        //Validando campo de nombre
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El nombre del cliente es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 60){
            Error.setText("El nombre debe contener menos de 60 caracteres");
            return false;
        }
        //Cancenlando uso de validaciones para ingreso de datos
        //Validando campo del documento
//        if(Documento.getText().isEmpty() || Documento.getForeground().equals(new Color(180, 180, 180))){
//            Error.setText("El documento del cliente es obligatorio");
//            return false;
//        }
//        if(Documento.getText().length() > 15 || Documento.getText().length() < 13){
//            Error.setText("El documento debe contener entre 13 y 15 caracteres");
//            return false;
//        }
//        
//        //Validando correo electronico
//        if(Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))){
//            Error.setText("El correo electronico del cliente es obligatorio");
//            return false;
//        }
//        if(Correo.getText().length() > 60){
//            Error.setText("El correo electronico debe contener menos de 60 caracteres");
//            return false;
//        }
//        if(!Correo.getText().contains("@") || !Correo.getText().contains(".")){
//            Error.setText("El correo electronico ingresado no es valido");
//            return false;
//        }
//        
//        //Validando numero telfonico
//        if(Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))){
//            Error.setText("El numero telefonico del cliente es obligatorio");
//            return false;
//        }
//        if(Numero.getText().length() > 8){
//            Error.setText("El numero telefonico debe contener menos de 8 numeros");
//            return false;
//        }
//        if(Numero.getText().length() < 8){
//            Error.setText("El numero telefonico debe contener al menos 8 numeros");
//            return false;
//        }
//        
//        //Validando campo de domicilio
//        if(Domicilio.getText().isEmpty() || Domicilio.getForeground().equals(new Color(180, 180, 180))){
//            Error.setText("El domicilio del cliente es obligatorio");
//            return false;
//        }
//        if(Domicilio.getText().length() > 150){
//            Error.setText("El domicilio debe contener menos de 150 caracteres");
//            return false;
//        }
        return true;
    }
    
    //Insiede Task
    private boolean validateRepitData(List<Cliente> clientes){
        if(existDocument()){
            Error.setText("Ya existe un cliente con el numero de documento ingresado");
            return false;
        }
        if(existRTN()){
            Error.setText("Ya existe un cliente con el RTN ingresado");
            return false;
        }
        if(existEmail()){
            Error.setText("Ya existe un cliente con el correo electronico ingresado");
            return false;
        }
        if(exisNumberPhone()){
            Error.setText("Ya existe un cliente con el numero telefonico ingresado");
            return false;
        }
        return true;
    }
    
    //Inside Task
    private boolean existDocument(){
        if(Documento.getForeground().equals(Color.black) && !Documento.getText().isEmpty()){
            try {
                Cliente cliente = (Cliente) Conection.createEntityManager().createNamedQuery("Cliente.findByDocumento")
                    .setParameter("documento", Documento.getText())
                    .getSingleResult();
                
                return cliente != null;
            } catch (NoResultException ex) {
                return false;
            }
        }
        return false;
    }
    
    private boolean existRTN(){
        if(RTN.getForeground().equals(Color.black) && !RTN.getText().isEmpty()){
            try {
                Cliente cliente = (Cliente) Conection.createEntityManager().createNamedQuery("Cliente.findByRtn")
                    .setParameter("rtn", RTN.getText())
                    .getSingleResult();
                
                return cliente != null;
            } catch (NoResultException ex) {
                return false;
            }
        }
        return false;
    }
    
    //Inside Task
    private boolean existEmail(){
        if(Correo.getForeground().equals(Color.black) && !Correo.getText().isEmpty()){
            try {
                Cliente cliente = (Cliente) Conection.createEntityManager().createNamedQuery("Cliente.findByCorreoElectronico")
                    .setParameter("correoElectronico", Correo.getText())
                    .getSingleResult();
                
                return cliente != null;
            } catch (NoResultException ex) {
                return false;
            }
        }
        return false;
    }
    
    //Inside Task
    private boolean exisNumberPhone(){
        if(Numero.getForeground().equals(Color.black) && !Numero.getText().isEmpty()){
            try {
                Cliente cliente = (Cliente) Conection.createEntityManager().createNamedQuery("Cliente.findByNumeroTelefono")
                    .setParameter("numeroTelefono", Numero.getText())
                    .getSingleResult();
                
                return cliente != null;
            } catch (NoResultException ex) {
                return false;
            }
        }
        return false;
    }
}
