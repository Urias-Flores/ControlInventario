package Models;

import Models.Producto;
import Models.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-24T15:45:37")
@StaticMetamodel(Ventadetalle.class)
public class Ventadetalle_ { 

    public static volatile SingularAttribute<Ventadetalle, Float> precio;
    public static volatile SingularAttribute<Ventadetalle, Float> descuento;
    public static volatile SingularAttribute<Ventadetalle, Producto> productoID;
    public static volatile SingularAttribute<Ventadetalle, Venta> ventaID;
    public static volatile SingularAttribute<Ventadetalle, Float> cantidad;
    public static volatile SingularAttribute<Ventadetalle, Float> isv;
    public static volatile SingularAttribute<Ventadetalle, Integer> ventaDetalleID;

}