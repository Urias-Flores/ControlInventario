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
    @NamedQuery(name = "Solicituddetalle.findAll", query = "SELECT s FROM Solicituddetalle s"),
    @NamedQuery(name = "Solicituddetalle.findBySolicitudID", query = "SELECT s FROM Solicituddetalle s WHERE s.solicitudID = :solicitudID"),
    @NamedQuery(name = "Solicituddetalle.findBySolicitudDetalleID", query = "SELECT s FROM Solicituddetalle s WHERE s.solicitudDetalleID = :solicitudDetalleID"),
    @NamedQuery(name = "Solicituddetalle.findByCantidad", query = "SELECT s FROM Solicituddetalle s WHERE s.cantidad = :cantidad"),
    @NamedQuery(name = "Solicituddetalle.findByPrecio", query = "SELECT s FROM Solicituddetalle s WHERE s.precio = :precio"),
    @NamedQuery(name = "Solicituddetalle.findByIsv", query = "SELECT s FROM Solicituddetalle s WHERE s.isv = :isv"),
    @NamedQuery(name = "Solicituddetalle.findByDescuento", query = "SELECT s FROM Solicituddetalle s WHERE s.descuento = :descuento")})
public class Solicituddetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer solicitudDetalleID;
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
    @JoinColumn(name = "SolicitudID", referencedColumnName = "SolicitudID")
    @ManyToOne(optional = false)
    private Solicitud solicitudID;

    public Solicituddetalle() {
    }

    public Solicituddetalle(Integer solicitudDetalleID) {
        this.solicitudDetalleID = solicitudDetalleID;
    }

    public Solicituddetalle(Integer solicitudDetalleID, float cantidad, float precio, float isv, float descuento) {
        this.solicitudDetalleID = solicitudDetalleID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.isv = isv;
        this.descuento = descuento;
    }

    public Integer getSolicitudDetalleID() {
        return solicitudDetalleID;
    }

    public void setSolicitudDetalleID(Integer solicitudDetalleID) {
        this.solicitudDetalleID = solicitudDetalleID;
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

    public Solicitud getSolicitudID() {
        return solicitudID;
    }

    public void setSolicitudID(Solicitud solicitudID) {
        this.solicitudID = solicitudID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (solicitudDetalleID != null ? solicitudDetalleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicituddetalle)) {
            return false;
        }
        Solicituddetalle other = (Solicituddetalle) object;
        if ((this.solicitudDetalleID == null && other.solicitudDetalleID != null) || (this.solicitudDetalleID != null && !this.solicitudDetalleID.equals(other.solicitudDetalleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Solicituddetalle[ solicitudDetalleID=" + solicitudDetalleID + " ]";
    }
    
}
