package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Models.Empleado;
import Resource.Conection;
import Resource.Utilities;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class InfoEmpleadoDialogViewController {
    private EmpleadoJpaController controller;
    
    private JLabel Nombre;
    private JLabel Apellido;
    private JLabel Identidad;
    private JLabel Correo;
    private JLabel Numero;
    private JLabel FechaNacimiento;
    private JTextArea Domicilio;

    public InfoEmpleadoDialogViewController(JLabel Nombre, JLabel Apellido, JLabel Identidad, JLabel Correo, JLabel Numero, JLabel FechaNacimiento, JTextArea Domicilio) {
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Identidad = Identidad;
        this.Correo = Correo;
        this.Numero = Numero;
        this.FechaNacimiento = FechaNacimiento;
        this.Domicilio = Domicilio;
        
        controller = new EmpleadoJpaController(Conection.createEntityManagerFactory());
    }
    
    //Task
    public void loadEmployee(int EmpleadoID){
        Runnable run = () ->{
            Empleado empleado = controller.findEmpleado(EmpleadoID);
            Nombre.setText(empleado.getNombre());
            Apellido.setText(empleado.getApellido());
            Identidad.setText(empleado.getIdentidad());
            Correo.setText(empleado.getCorreoElectronico());
            Numero.setText(empleado.getNumeroTelefonico());
            Domicilio.setText(empleado.getDomicilio());
            FechaNacimiento.setText
            (empleado.getFechaNacimiento().getDate()+" de "+Utilities.getMonth(empleado.getFechaNacimiento().getMonth())
            +" del "+(empleado.getFechaNacimiento().getYear()+1900));
        };
        new Thread(run).start();
    }
}
