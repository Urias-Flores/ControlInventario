package ViewsControllers.Dialogs;

import Controllers.ProveedorJpaController;
import Models.Proveedor;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddProveedorDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddProveedorDialogViewController {
    private ProveedorJpaController controller;
    
    private AddProveedorDialog Instance;
    private JTextField Nombre;
    private JTextField RNT;
    private JTextField Correo;
    private JTextField Numero;
    private JLabel Error;
    private JLabel Cargando;

    public AddProveedorDialogViewController(AddProveedorDialog Instance, JTextField Nombre, JTextField RNT, JTextField Correo, JTextField Numero, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.RNT = RNT;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Error = Error;
        this.Cargando = Cargando;
        
        controller = new ProveedorJpaController(Conection.createEntityManagerFactory());
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void insertSupplier(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                List<Proveedor> proveedores = controller.findProveedorEntities();
                if(validateRepitData(proveedores)){
                    Proveedor proveedor = createSupplierObject();
                    controller.create(proveedor);

                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("El proveedor ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                } else { Error.setBackground(new Color(185, 0, 0)); }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    public void editSupplier(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                try {
                    List<Proveedor> proveedores = controller.findProveedorEntities();
                    if(validateRepitData(proveedores)){
                        Proveedor proveedor = createSupplierObject();
                        controller.edit(proveedor);

                        setLoad(false);
                        Instance.setVisible(false);
                        Dialogs.ShowMessageDialog("El proveedor ha sido modificado exitosamente", Dialogs.COMPLETE_ICON);
                    } else { Error.setBackground(new Color(185, 0, 0)); }
                } catch (Exception ex) {
                    setLoad(false);
                    System.err.println("Error: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ha ocurrido un error al modificar proveedor", Dialogs.ERROR_ICON);
                }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Inside Task
    public Proveedor createSupplierObject(){
        Proveedor proveedor = new Proveedor();
        if(Nombre.getName() != null){
            proveedor = controller.findProveedor(Integer.valueOf(Nombre.getName()));
        }
        proveedor.setNombre(Nombre.getText());
        proveedor.setRtn(RNT.getText());
        proveedor.setCorreoElectronico(Correo.getText().toLowerCase());
        proveedor.setNumeroTelefono(Numero.getText());
        
        return proveedor;
    }
    
    //Task
    public void setEditing(int ProveedorID){
        setLoad(true);
        Runnable run = () -> {
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
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El nombre del proveedor es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 100){
            Error.setText("El nombre del proveedor debe contener menos de 100 caracteres");
            return false;
        }
        
        if(RNT.getText().isEmpty() || RNT.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El RTN del proveedor es obligatorio");
            return false;
        }
        if(RNT.getText().length() > 15){
            Error.setText("El RTN del proveedore debe contener menos de 15 caracteres");
            return false;
        }
        if(RNT.getText().length() < 13){
            Error.setText("El RTN del proveedor debe contener al menos 13 caracteres");
            return false;
        }
        
        if(Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El correo electronico del proveedor es obligatorio");
            return false;
        }
        if(Correo.getText().length() > 80){
            Error.setText("El correo electronico debe contener menos de 80 caracteres");
            return false;
        }
        if(!Correo.getText().contains("@") || !Correo.getText().contains(".")){
            Error.setText("El correo electronico ingresado no es valido");
            return false;
        }
        
        if(Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El numero telefonico del proveedor es obligatorio");
            return false;
        }
        if(Numero.getText().length() < 8){
            Error.setText("El numero telefonico debe contener al menos 8 caracteres");
            return false;
        }
        if(Numero.getText().length() > 20){
            Error.setText("El numero telefonico debe contener menos de 20 caracteres");
            return false;
        }
        return true;
    }
    
    private boolean validateRepitData(List<Proveedor> proveedores){
        if(existRTN(proveedores)){
            Error.setText("Ya existe un proveedor registrado con el RTN ingresado");
            return false;
        }
        if(existEmail(proveedores)){
            Error.setText("Ya existe un proveedor registrado con el correo electronico ingresado");
            return false;
        }
        if(existNumberPhone(proveedores)){
            Error.setText("Ya existe un proveedor registrado con el numero telefonico ingresado");
            return false;
        }
        return true;
    }
    
    private boolean existRTN(List<Proveedor> proveedores){
        if(!proveedores.isEmpty()){
            for(Proveedor proveedor : proveedores){
                if(RNT.getText().equals(proveedor.getRtn())){
                    if(Nombre.getName() != null){
                        int ProveedorID = Integer.parseInt(Nombre.getName());
                        if(ProveedorID != proveedor.getProveedorID()){
                            return true;
                        }
                    } else { return true; }
                }
            }
        }
        return false;
    }
    
    private boolean existEmail(List<Proveedor> proveedores){
        if(!proveedores.isEmpty()){
            for(Proveedor proveedor : proveedores){
                if(Correo.getText().equals(proveedor.getCorreoElectronico())){
                    if(Nombre.getName() != null){
                        int ProveedorID = Integer.parseInt(Nombre.getName());
                        if(ProveedorID != proveedor.getProveedorID()){
                            return true;
                        }
                    } else { return true; }
                }
            }
        }
        return false;
    }
    
    private boolean existNumberPhone(List<Proveedor> proveedores){
        if(!proveedores.isEmpty()){
            for(Proveedor proveedor : proveedores){
                if(Numero.getText().equals(proveedor.getNumeroTelefono())){
                    if(Nombre.getName() != null){
                        int ProveedorID = Integer.parseInt(Nombre.getName());
                        if(ProveedorID != proveedor.getProveedorID()){
                            return true;
                        }
                    } else { return true; }
                }
            }
        }
        return false;
    }
}
