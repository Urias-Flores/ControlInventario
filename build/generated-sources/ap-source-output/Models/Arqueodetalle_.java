package Models;

import Models.Arqueo;
import Models.Gasto;
import Models.Solicitud;
import Models.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Arqueodetalle.class)
public class Arqueodetalle_ { 

    public static volatile SingularAttribute<Arqueodetalle, Float> cambio;
    public static volatile SingularAttribute<Arqueodetalle, Integer> arqueoDetalleID;
    public static volatile SingularAttribute<Arqueodetalle, Solicitud> solicitudID;
    public static volatile SingularAttribute<Arqueodetalle, Venta> facturaID;
    public static volatile SingularAttribute<Arqueodetalle, Arqueo> arqueoID;
    public static volatile SingularAttribute<Arqueodetalle, Float> efectivo;
    public static volatile SingularAttribute<Arqueodetalle, Gasto> gastoID;

}