package Models;

import Models.Compra;
import Models.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-05T15:48:43")
@StaticMetamodel(Compradetalle.class)
public class Compradetalle_ { 

    public static volatile SingularAttribute<Compradetalle, Float> precio;
    public static volatile SingularAttribute<Compradetalle, Float> descuento;
    public static volatile SingularAttribute<Compradetalle, Producto> productoID;
    public static volatile SingularAttribute<Compradetalle, Integer> compraDetalleID;
    public static volatile SingularAttribute<Compradetalle, Float> cantidad;
    public static volatile SingularAttribute<Compradetalle, Float> isv;
    public static volatile SingularAttribute<Compradetalle, Compra> compraID;

}