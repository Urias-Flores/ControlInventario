package Models;

import Models.Arqueodetalle;
import Models.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-11T10:21:14")
@StaticMetamodel(Arqueo.class)
public class Arqueo_ { 

    public static volatile SingularAttribute<Arqueo, Date> fecha;
    public static volatile SingularAttribute<Arqueo, Float> saldoFinalUsuario;
    public static volatile ListAttribute<Arqueo, Arqueodetalle> arqueodetalleList;
    public static volatile SingularAttribute<Arqueo, Float> saldoFinalSistema;
    public static volatile SingularAttribute<Arqueo, Integer> arqueoID;
    public static volatile SingularAttribute<Arqueo, Date> hora;
    public static volatile SingularAttribute<Arqueo, Float> saldoInicial;
    public static volatile SingularAttribute<Arqueo, Usuario> usuarioID;

}