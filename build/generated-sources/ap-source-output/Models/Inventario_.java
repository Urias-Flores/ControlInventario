package Models;

import Models.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Inventario.class)
public class Inventario_ { 

    public static volatile SingularAttribute<Inventario, Integer> inventarioID;
    public static volatile SingularAttribute<Inventario, Producto> productoID;
    public static volatile SingularAttribute<Inventario, Integer> cantidad;

}