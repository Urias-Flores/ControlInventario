package Models;

import Models.Categoria;
import Models.Compradetalle;
import Models.Cotizaciondetalle;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Models.Marca;
import Models.Solicituddetalle;
import Models.Ventadetalle;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-05-18T13:38:44")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, String> descripcion;
    public static volatile ListAttribute<Producto, Ventadetalle> ventadetalleList;
    public static volatile SingularAttribute<Producto, Integer> productoID;
    public static volatile ListAttribute<Producto, Compradetalle> compradetalleList;
    public static volatile ListAttribute<Producto, Inventariodetalleacciones> inventariodetalleaccionesList;
    public static volatile ListAttribute<Producto, Solicituddetalle> solicituddetalleList;
    public static volatile SingularAttribute<Producto, Categoria> categoriaID;
    public static volatile SingularAttribute<Producto, String> barra;
    public static volatile SingularAttribute<Producto, String> unidad;
    public static volatile SingularAttribute<Producto, Float> cantidadMinima;
    public static volatile SingularAttribute<Producto, Marca> marcaID;
    public static volatile SingularAttribute<Producto, Float> precioCompra;
    public static volatile ListAttribute<Producto, Cotizaciondetalle> cotizaciondetalleList;
    public static volatile ListAttribute<Producto, Inventario> inventarioList;
    public static volatile SingularAttribute<Producto, Float> precioVenta;

}