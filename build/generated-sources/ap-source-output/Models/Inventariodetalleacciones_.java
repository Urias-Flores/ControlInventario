package Models;

import Models.Producto;
import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-02-05T09:12:32")
@StaticMetamodel(Inventariodetalleacciones.class)
public class Inventariodetalleacciones_ { 

    public static volatile SingularAttribute<Inventariodetalleacciones, String> descripcion;
    public static volatile SingularAttribute<Inventariodetalleacciones, String> accion;
    public static volatile SingularAttribute<Inventariodetalleacciones, Date> fecha;
    public static volatile SingularAttribute<Inventariodetalleacciones, Date> hora;
    public static volatile SingularAttribute<Inventariodetalleacciones, Producto> productoID;
    public static volatile SingularAttribute<Inventariodetalleacciones, Integer> inventarioDetalleAccionesID;
    public static volatile SingularAttribute<Inventariodetalleacciones, Float> existenciaPrevia;
    public static volatile SingularAttribute<Inventariodetalleacciones, Usuario> usuarioID;
    public static volatile SingularAttribute<Inventariodetalleacciones, Float> cantidadModificada;

}