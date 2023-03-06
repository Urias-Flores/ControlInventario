package Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ventadetalle.findAll", query = "SELECT v FROM Ventadetalle v"),
    @NamedQuery(name = "Ventadetalle.findByVentaID", query = "SELECT v FROM Ventadetalle v WHERE v.ventaID = :ventaID"),
    @NamedQuery(name = "Ventadetalle.findByVentaDetalleID", query = "SELECT v FROM Ventadetalle v WHERE v.ventaDetalleID = :ventaDetalleID"),
    @NamedQuery(name = "Ventadetalle.findByCantidad", query = "SELECT v FROM Ventadetalle v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "Ventadetalle.findByPrecio", query = "SELECT v FROM Ventadetalle v WHERE v.precio = :precio"),
    @NamedQuery(name = "Ventadetalle.findByIsv", query = "SELECT v FROM Ventadetalle v WHERE v.isv = :isv"),
    @NamedQuery(name = "Ventadetalle.findByDescuento", query = "SELECT v FROM Ventadetalle v WHERE v.descuento = :descuento")})
public class Ventadetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer ventaDetalleID;
    @Basic(optional = false)
    private float cantidad;
    @Basic(optional = false)
    private float precio;
    @Basic(optional = false)
    private float isv;
    @Basic(optional = false)
    private float descuento;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private Producto productoID;
    @JoinColumn(name = "VentaID", referencedColumnName = "VentaID")
    @ManyToOne(optional = false)
    private Venta ventaID;

    public Ventadetalle() {
    }

    public Ventadetalle(Integer ventaDetalleID) {
        this.ventaDetalleID = ventaDetalleID;
    }

    public Ventadetalle(Integer ventaDetalleID, float cantidad, float precio, float isv, float descuento) {
        this.ventaDetalleID = ventaDetalleID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.isv = isv;
        this.descuento = descuento;
    }

    public Integer getVentaDetalleID() {
        return ventaDetalleID;
    }

    public void setVentaDetalleID(Integer ventaDetalleID) {
        this.ventaDetalleID = ventaDetalleID;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getIsv() {
        return isv;
    }

    public void setIsv(float isv) {
        this.isv = isv;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public Producto getProductoID() {
        return productoID;
    }

    public void setProductoID(Producto productoID) {
        this.productoID = productoID;
    }

    public Venta getVentaID() {
        return ventaID;
    }

    public void setVentaID(Venta ventaID) {
        this.ventaID = ventaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaDetalleID != null ? ventaDetalleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ventadetalle)) {
            return false;
        }
        Ventadetalle other = (Ventadetalle) object;
        if ((this.ventaDetalleID == null && other.ventaDetalleID != null) || (this.ventaDetalleID != null && !this.ventaDetalleID.equals(other.ventaDetalleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Ventadetalle[ ventaDetalleID=" + ventaDetalleID + " ]";
    }
    
}
