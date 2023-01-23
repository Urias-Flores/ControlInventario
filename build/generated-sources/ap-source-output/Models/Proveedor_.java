package Models;

import Models.Compra;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-23T13:51:09")
@StaticMetamodel(Proveedor.class)
public class Proveedor_ { 

    public static volatile SingularAttribute<Proveedor, Integer> proveedorID;
    public static volatile SingularAttribute<Proveedor, Float> saldo;
    public static volatile ListAttribute<Proveedor, Compra> compraList;
    public static volatile SingularAttribute<Proveedor, String> nombre;
    public static volatile SingularAttribute<Proveedor, String> rtn;
    public static volatile SingularAttribute<Proveedor, String> correoElectronico;
    public static volatile SingularAttribute<Proveedor, String> numeroTelefono;

}