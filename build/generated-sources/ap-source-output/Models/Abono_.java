package Models;

import Models.Compra;
import Models.Solicitud;
import Models.Usuario;
import Models.Venta;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Abono.class)
public class Abono_ { 

    public static volatile SingularAttribute<Abono, Date> fecha;
    public static volatile SingularAttribute<Abono, String> tipo;
    public static volatile SingularAttribute<Abono, Float> total;
    public static volatile SingularAttribute<Abono, Solicitud> solicitudID;
    public static volatile SingularAttribute<Abono, Date> hora;
    public static volatile SingularAttribute<Abono, Venta> ventaID;
    public static volatile SingularAttribute<Abono, Integer> abonoID;
    public static volatile SingularAttribute<Abono, Usuario> usuarioID;
    public static volatile SingularAttribute<Abono, Compra> compraID;

}