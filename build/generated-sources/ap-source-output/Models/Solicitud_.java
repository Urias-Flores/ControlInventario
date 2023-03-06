package Models;

import Models.Arqueodetalle;
import Models.Cliente;
import Models.Solicituddetalle;
import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-06T10:50:12")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile SingularAttribute<Solicitud, Date> fecha;
    public static volatile SingularAttribute<Solicitud, Integer> solicitudID;
    public static volatile SingularAttribute<Solicitud, String> estado;
    public static volatile ListAttribute<Solicitud, Arqueodetalle> arqueodetalleList;
    public static volatile SingularAttribute<Solicitud, Cliente> clienteID;
    public static volatile SingularAttribute<Solicitud, Date> hora;
    public static volatile SingularAttribute<Solicitud, String> rtn;
    public static volatile ListAttribute<Solicitud, Solicituddetalle> solicituddetalleList;
    public static volatile SingularAttribute<Solicitud, Usuario> usuarioID;

}