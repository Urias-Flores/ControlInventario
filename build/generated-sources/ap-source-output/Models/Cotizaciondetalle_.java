package Models;

import Models.Cotizacion;
import Models.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Cotizaciondetalle.class)
public class Cotizaciondetalle_ { 

    public static volatile SingularAttribute<Cotizaciondetalle, Float> precio;
    public static volatile SingularAttribute<Cotizaciondetalle, Cotizacion> cotizacionID;
    public static volatile SingularAttribute<Cotizaciondetalle, Float> descuento;
    public static volatile SingularAttribute<Cotizaciondetalle, Producto> productoID;
    public static volatile SingularAttribute<Cotizaciondetalle, Integer> cotizacionDetalleID;
    public static volatile SingularAttribute<Cotizaciondetalle, Float> cantidad;
    public static volatile SingularAttribute<Cotizaciondetalle, Float> isv;

}