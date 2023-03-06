package Models;

import Models.Arqueodetalle;
import Models.Usuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-06T10:50:12")
@StaticMetamodel(Gasto.class)
public class Gasto_ { 

    public static volatile SingularAttribute<Gasto, String> descripcion;
    public static volatile SingularAttribute<Gasto, Float> total;
    public static volatile ListAttribute<Gasto, Arqueodetalle> arqueodetalleList;
    public static volatile SingularAttribute<Gasto, Integer> gastoID;
    public static volatile SingularAttribute<Gasto, Usuario> usuarioID;

}