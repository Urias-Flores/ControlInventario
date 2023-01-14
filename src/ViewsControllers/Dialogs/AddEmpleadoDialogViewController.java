package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddEmpleadoDialogViewController {

    EmpleadoJpaController controller;

    JTextField Nombre;
    JTextField Apellido;
    JTextField Identidad;
    JTextField Correo;
    JTextField Numero;
    JComboBox<String> Dia;
    JComboBox<String> Mes;
    JComboBox<String> Anio;
    JTextArea Domicilio;
    JLabel Error;

    public AddEmpleadoDialogViewController(JTextField Nombre, JTextField Apellido, JTextField Identidad, JTextField Correo, JTextField Numero, JComboBox<String> Dia, JComboBox<String> Mes, JComboBox<String> Anio, JTextArea Domicilio, JLabel Error) {
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

        controller = new EmpleadoJpaController(Conection.CreateEntityManager());
    }

    public boolean Insert() {
        if (validate()) {
            Empleado empleado = CreateObjectEmpleado();
            controller.create(empleado);
            return true;
        }
        return false;
    }

    public boolean Edit() {
        if (validate()) {
            Empleado empleado = CreateObjectEmpleado();
            try {
                controller.edit(empleado);
                return true;
            } catch (NonexistentEntityException ex) {
                Dialogs.ShowMessageDialog("Un dato de este empleado esta ligado a otros. No se pudo editar", Dialogs.ERROR_ICON);
            } catch (Exception ex) {
                System.out.println("Test: "+ex.getMessage());
                Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
            }
        }
        return false;
    }
    
    public void setEditing(int EmpleadoID){
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
    }

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
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if (Apellido.getText().isEmpty() || Apellido.getForeground().equals(new Color(180, 180, 180))) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if (Identidad.getText().isEmpty() || Identidad.getForeground().equals(new Color(180, 180, 180))) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if (Correo.getText().isEmpty() || Correo.getForeground().equals(new Color(180, 180, 180))) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if (Numero.getText().isEmpty() || Numero.getForeground().equals(new Color(180, 180, 180))) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        if (Domicilio.getText().isEmpty() || Domicilio.getForeground().equals(new Color(180, 180, 180))) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El nombre del empleado es obligatorio");
            return false;
        }
        return true;
    }
}
