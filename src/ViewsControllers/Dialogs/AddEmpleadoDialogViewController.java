package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddEmpleadoDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddEmpleadoDialogViewController {

    private EmpleadoJpaController controller;

    private AddEmpleadoDialog Instance;
    private JTextField Nombre;
    private JTextField Apellido;
    private JTextField Identidad;
    private JTextField Correo;
    private JTextField Numero;
    private JComboBox<String> Dia;
    private JComboBox<String> Mes;
    private JComboBox<String> Anio;
    private JTextArea Domicilio;
    private JLabel Error;
    private JLabel Cargando;

    public AddEmpleadoDialogViewController(AddEmpleadoDialog Instance, JTextField Nombre, JTextField Apellido, JTextField Identidad, JTextField Correo, JTextField Numero, JComboBox<String> Dia, JComboBox<String> Mes, JComboBox<String> Anio, JTextArea Domicilio, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Identidad = Identidad;
        this.Correo = Correo;
        this.Numero = Numero;
        this.Dia = Dia;
        this.Mes = Mes;
        this.Anio = Anio;
        this.Domicilio = Domicilio;
        this.Error = Error;
        this.Cargando = Cargando;

        controller = new EmpleadoJpaController(Conection.createEntityManagerFactory());
    }

    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void Insert() {
        if (validate()) {
            setLoad(true);
            Runnable run = () ->{
                List<Empleado> empleados = controller.findEmpleadoEntities();
                if(validateRepitData(empleados)){
                    Empleado empleado = CreateObjectEmpleado();
                    controller.create(empleado);

                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("El empleado ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                } else { Error.setBackground(new Color(185, 0, 0)); }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }

    //Task
    public void Edit() {
        if (validate()) {
            setLoad(true);
            Runnable run = () ->{
                List<Empleado> empleados = controller.findEmpleadoEntities();
                if(validateRepitData(empleados)){
                    try {
                        Empleado empleado = CreateObjectEmpleado();
                        controller.edit(empleado);

                        setLoad(false);
                        Instance.setVisible(false);
                        Dialogs.ShowMessageDialog("El empleado ha sido modificado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        System.out.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Un dato de este empleado esta ligado a otros. No se pudo editar", Dialogs.ERROR_ICON);
                    } catch (Exception ex) {
                        setLoad(false);
                        System.out.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                    }
                } else { Error.setBackground(new Color(185, 0, 0)); }
                
                setLoad(false);
            };
            new Thread(run).start();
        }
    }
    
    //Task
    public void setEditing(int EmpleadoID){
        setLoad(true);
        Runnable run = () -> {
            Empleado empleado = controller.findEmpleado(EmpleadoID);
            Nombre.setText(empleado.getNombre());
            Nombre.setName(String.valueOf(empleado.getEmpleadoID()));
            Nombre.setForeground(Color.BLACK);
            Apellido.setText(empleado.getApellido());
            Apellido.setForeground(Color.BLACK);
            Identidad.setText(empleado.getIdentidad());
            Identidad.setForeground(Color.BLACK);
            Correo.setText(empleado.getCorreoElectronico());
            Correo.setForeground(Color.BLACK);
            Numero.setText(empleado.getNumeroTelefonico());
            Numero.setForeground(Color.BLACK);
            Domicilio.setText(empleado.getDomicilio());
            Domicilio.setForeground(Color.BLACK);
            Dia.setSelectedIndex(empleado.getFechaNacimiento().getDate()-1);
            Mes.setSelectedIndex(empleado.getFechaNacimiento().getMonth());
            Anio.setSelectedItem(String.valueOf(empleado.getFechaNacimiento().getYear() + 1900));
            
            setLoad(false);
        };
        new Thread(run).start();
    }

    //Inside Task
    private Empleado CreateObjectEmpleado() {
        Empleado empleado = new Empleado();
        if (Nombre.getName() != null) {
            empleado.setEmpleadoID(Integer.valueOf(Nombre.getName()));
        }
        empleado.setNombre(Nombre.getText());
        empleado.setApellido(Apellido.getText());
        empleado.setIdentidad(Identidad.getText());
        empleado.setCorreoElectronico(Correo.getText());
        empleado.setNumeroTelefonico(Numero.getText());
        empleado.setFechaNacimiento(new Date(
                Integer.parseInt(Anio.getSelectedItem().toString()) - 1900,
                Mes.getSelectedIndex(),
                Dia.getSelectedIndex() + 1));
        empleado.setDomicilio(Domicilio.getText());

        return empleado;
    }

    private boolean validate() {
        if (Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 80){
            Error.setText("El nombre del empleado debe contener menos de 80 caracteres");
            return false;
        }
        
        if (Apellido.getText().isEmpty() || Apellido.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("El apellido del empleado es obligatorio");
            return false;
        }
        if(Apellido.getText().length() > 80){
            Error.setText("El apellido del empleado debe contener menos de 80 caracteres");
            return false;
        }
        
        if (Identidad.getText().isEmpty() || Identidad.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("La identidad del empleado es obligatorio");
            return false;
        }
        if(Identidad.getText().length() > 20){
            Error.setText("La identidad del empleado debe contener menos de 20 caracteres");
            return false;
        }
        if(Identidad.getText().length() < 13){
            Error.setText("La identidad del empleado debe contener al menos 13 caracteres");
            return false;
        }
        
        if (Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("El correo electronico del empleado es obligatorio");
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
        
        if (Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("El numero telefonico del empleado es obligatorio");
            return false;
        }
        if(Numero.getText().length() > 8){
            Error.setText("El numero telefonico debe contener menos de 8 caracteres");
            return false;
        }
        if(Numero.getText().length() < 8){
            Error.setText("El numero telefonico debe contener al menos de 8 caracteres");
            return false;
        }
        
        if (Domicilio.getText().isEmpty() || Domicilio.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("El domicilio del empleado es obligatorio");
            return false;
        }
        if(Domicilio.getText().length() > 300){
            Error.setText("El domicilio del empleado debe contener menos de 300 caracteres");
            return false;
        }
        return true;
    }
    
    private boolean validateRepitData(List<Empleado> empleados){
        if(existDocument(empleados)){
            Error.setText("Ya existe un empleado registrado con la identidad ingresada");
            return false;
        }
        if(existEmail(empleados)){
            Error.setText("Ya existe un empleado registrado con el correo electronico ingresado");
            return false;
        }
        if(existNumberPhone(empleados)){
            Error.setText("Ya existe un empleado registrado con el numero telefonico ingresado");
            return false;
        }
        return true;
    }
    
    private boolean existDocument(List<Empleado> empleados){
        if(!empleados.isEmpty()){
            for(Empleado empleado : empleados){
                if(Identidad.getText().equalsIgnoreCase(empleado.getIdentidad())){
                    if(Nombre.getName() != null){
                        int EmpleadoID = Integer.parseInt(Nombre.getName());
                        if(EmpleadoID != empleado.getEmpleadoID()){
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }   
        }
        return false;
    }
    
    private boolean existEmail(List<Empleado> empleados){
        if(!empleados.isEmpty()){
            for(Empleado empleado : empleados){
                if(Correo.getText().equalsIgnoreCase(empleado.getCorreoElectronico())){
                    if(Nombre.getName() != null){
                        int EmpleadoID = Integer.parseInt(Nombre.getName());
                        if(EmpleadoID != empleado.getEmpleadoID()){
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }   
        }
        return false;
    }
    
    private boolean existNumberPhone(List<Empleado> empleados){
        if(!empleados.isEmpty()){
            for(Empleado empleado : empleados){
                if(Numero.getText().equalsIgnoreCase(empleado.getNumeroTelefonico())){
                    if(Nombre.getName() != null){
                        int EmpleadoID = Integer.parseInt(Nombre.getName());
                        if(EmpleadoID != empleado.getEmpleadoID()){
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }   
        }
        return false;
    }
}
