package Models;

import Models.Cliente;
import Models.Cotizaciondetalle;
import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Cotizacion.class)
public class Cotizacion_ { 

    public static volatile SingularAttribute<Cotizacion, Date> fecha;
    public static volatile SingularAttribute<Cotizacion, Integer> cotizacionID;
    public static volatile SingularAttribute<Cotizacion, Cliente> clienteID;
    public static volatile SingularAttribute<Cotizacion, Date> hora;
    public static volatile ListAttribute<Cotizacion, Cotizaciondetalle> cotizaciondetalleList;
    public static volatile SingularAttribute<Cotizacion, Usuario> usuarioID;

}