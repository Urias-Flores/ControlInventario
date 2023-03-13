package Models;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-11T10:21:14")
@StaticMetamodel(Notificacion.class)
public class Notificacion_ { 

    public static volatile SingularAttribute<Notificacion, Date> fecha;
    public static volatile SingularAttribute<Notificacion, String> contenido;
    public static volatile SingularAttribute<Notificacion, Character> estado;
    public static volatile SingularAttribute<Notificacion, Date> hora;
    public static volatile SingularAttribute<Notificacion, String> titulo;
    public static volatile SingularAttribute<Notificacion, Character> destino;
    public static volatile SingularAttribute<Notificacion, Integer> notificacionID;

}