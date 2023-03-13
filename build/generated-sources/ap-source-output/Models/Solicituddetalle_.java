package Models;

import Models.Producto;
import Models.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-11T10:21:14")
@StaticMetamodel(Solicituddetalle.class)
public class Solicituddetalle_ { 

    public static volatile SingularAttribute<Solicituddetalle, Float> precio;
    public static volatile SingularAttribute<Solicituddetalle, Solicitud> solicitudID;
    public static volatile SingularAttribute<Solicituddetalle, Float> descuento;
    public static volatile SingularAttribute<Solicituddetalle, Producto> productoID;
    public static volatile SingularAttribute<Solicituddetalle, Float> cantidad;
    public static volatile SingularAttribute<Solicituddetalle, Float> isv;
    public static volatile SingularAttribute<Solicituddetalle, Integer> solicitudDetalleID;

}