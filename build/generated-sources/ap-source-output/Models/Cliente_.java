package Models;

import Models.Abono;
import Models.Cotizacion;
import Models.Solicitud;
import Models.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-03-06T10:50:12")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> domicilio;
    public static volatile SingularAttribute<Cliente, Integer> clienteID;
    public static volatile ListAttribute<Cliente, Cotizacion> cotizacionList;
    public static volatile ListAttribute<Cliente, Venta> ventaList;
    public static volatile ListAttribute<Cliente, Abono> abonoList;
    public static volatile SingularAttribute<Cliente, String> documento;
    public static volatile SingularAttribute<Cliente, Float> saldo;
    public static volatile ListAttribute<Cliente, Solicitud> solicitudList;
    public static volatile SingularAttribute<Cliente, String> nombre;
    public static volatile SingularAttribute<Cliente, String> rtn;
    public static volatile SingularAttribute<Cliente, String> correoElectronico;
    public static volatile SingularAttribute<Cliente, String> numeroTelefono;

}