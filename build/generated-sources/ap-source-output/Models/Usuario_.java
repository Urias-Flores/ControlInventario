package Models;

import Models.Compra;
import Models.Cotizacion;
import Models.Empleado;
import Models.Inventariodetalleacciones;
import Models.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-30T10:36:31")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> estado;
    public static volatile ListAttribute<Usuario, Venta> ventaList;
    public static volatile ListAttribute<Usuario, Cotizacion> cotizacionList;
    public static volatile SingularAttribute<Usuario, Empleado> empleadoID;
    public static volatile ListAttribute<Usuario, Inventariodetalleacciones> inventariodetalleaccionesList;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, String> cargo;
    public static volatile ListAttribute<Usuario, Compra> compraList;
    public static volatile SingularAttribute<Usuario, Integer> usuarioID;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> token;

}