package Models;

import Models.Compradetalle;
import Models.Proveedor;
import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-01-23T13:51:09")
@StaticMetamodel(Compra.class)
public class Compra_ { 

    public static volatile SingularAttribute<Compra, Date> fecha;
    public static volatile SingularAttribute<Compra, Date> fechaCompra;
    public static volatile SingularAttribute<Compra, String> estado;
    public static volatile SingularAttribute<Compra, Proveedor> proveedorID;
    public static volatile SingularAttribute<Compra, Date> hora;
    public static volatile SingularAttribute<Compra, Date> fechaVencimiento;
    public static volatile ListAttribute<Compra, Compradetalle> compradetalleList;
    public static volatile SingularAttribute<Compra, String> noFactura;
    public static volatile SingularAttribute<Compra, Usuario> usuarioID;
    public static volatile SingularAttribute<Compra, Integer> compraID;

}