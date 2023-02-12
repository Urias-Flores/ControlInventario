package Models;

import Models.Abono;
import Models.Cliente;
import Models.Usuario;
import Models.Ventadetalle;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-12T08:19:08")
@StaticMetamodel(Venta.class)
public class Venta_ { 

    public static volatile SingularAttribute<Venta, Date> fecha;
    public static volatile SingularAttribute<Venta, String> estado;
    public static volatile ListAttribute<Venta, Ventadetalle> ventadetalleList;
    public static volatile SingularAttribute<Venta, Cliente> clienteID;
    public static volatile SingularAttribute<Venta, Date> hora;
    public static volatile ListAttribute<Venta, Abono> abonoList;
    public static volatile SingularAttribute<Venta, Integer> ventaID;
    public static volatile SingularAttribute<Venta, String> rtn;
    public static volatile SingularAttribute<Venta, Usuario> usuarioID;

}