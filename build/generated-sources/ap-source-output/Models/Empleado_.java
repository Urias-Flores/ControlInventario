package Models;

import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Empleado.class)
public class Empleado_ { 

    public static volatile SingularAttribute<Empleado, String> numeroTelefonico;
    public static volatile SingularAttribute<Empleado, String> domicilio;
    public static volatile ListAttribute<Empleado, Usuario> usuarioList;
    public static volatile SingularAttribute<Empleado, Date> fechaNacimiento;
    public static volatile SingularAttribute<Empleado, Integer> empleadoID;
    public static volatile SingularAttribute<Empleado, String> apellido;
    public static volatile SingularAttribute<Empleado, String> identidad;
    public static volatile SingularAttribute<Empleado, String> nombre;
    public static volatile SingularAttribute<Empleado, String> correoElectronico;

}