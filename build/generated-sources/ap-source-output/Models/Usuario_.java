package Models;

import Models.Abono;
import Models.Arqueo;
import Models.Compra;
import Models.Cotizacion;
import Models.Empleado;
import Models.Gasto;
import Models.Inventariodetalleacciones;
import Models.Solicitud;
import Models.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-06T10:50:12")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Integer> estado;
    public static volatile ListAttribute<Usuario, Cotizacion> cotizacionList;
    public static volatile ListAttribute<Usuario, Abono> abonoList;
    public static volatile SingularAttribute<Usuario, Empleado> empleadoID;
    public static volatile ListAttribute<Usuario, Inventariodetalleacciones> inventariodetalleaccionesList;
    public static volatile ListAttribute<Usuario, Gasto> gastoList;
    public static volatile ListAttribute<Usuario, Compra> compraList;
    public static volatile ListAttribute<Usuario, Solicitud> solicitudList;
    public static volatile SingularAttribute<Usuario, Integer> usuarioID;
    public static volatile SingularAttribute<Usuario, String> nombre;
    public static volatile SingularAttribute<Usuario, String> token;
    public static volatile ListAttribute<Usuario, Venta> ventaList;
    public static volatile ListAttribute<Usuario, Arqueo> arqueoList;
    public static volatile SingularAttribute<Usuario, String> contrasena;
    public static volatile SingularAttribute<Usuario, String> cargo;

}