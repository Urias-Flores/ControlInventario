package Models;

import Models.Producto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-21T10:37:30")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile SingularAttribute<Marca, Integer> marcaID;
    public static volatile ListAttribute<Marca, Producto> productoList;
    public static volatile SingularAttribute<Marca, String> nombre;

}