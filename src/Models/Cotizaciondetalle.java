/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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

/**
 *
 * @author Dell
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizaciondetalle.findAll", query = "SELECT c FROM Cotizaciondetalle c"),
    @NamedQuery(name = "Cotizaciondetalle.findByCotizacionDetalleID", query = "SELECT c FROM Cotizaciondetalle c WHERE c.cotizacionDetalleID = :cotizacionDetalleID"),
    @NamedQuery(name = "Cotizaciondetalle.findByCantidad", query = "SELECT c FROM Cotizaciondetalle c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Cotizaciondetalle.findByPrecio", query = "SELECT c FROM Cotizaciondetalle c WHERE c.precio = :precio"),
    @NamedQuery(name = "Cotizaciondetalle.findByIsv", query = "SELECT c FROM Cotizaciondetalle c WHERE c.isv = :isv"),
    @NamedQuery(name = "Cotizaciondetalle.findByDescuento", query = "SELECT c FROM Cotizaciondetalle c WHERE c.descuento = :descuento")})
public class Cotizaciondetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer cotizacionDetalleID;
    @Basic(optional = false)
    private float cantidad;
    @Basic(optional = false)
    private float precio;
    @Basic(optional = false)
    private float isv;
    @Basic(optional = false)
    private float descuento;
    @JoinColumn(name = "CotizacionID", referencedColumnName = "CotizacionID")
    @ManyToOne(optional = false)
    private Cotizacion cotizacionID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private Producto productoID;

    public Cotizaciondetalle() {
    }

    public Cotizaciondetalle(Integer cotizacionDetalleID) {
        this.cotizacionDetalleID = cotizacionDetalleID;
    }

    public Cotizaciondetalle(Integer cotizacionDetalleID, float cantidad, float precio, float isv, float descuento) {
        this.cotizacionDetalleID = cotizacionDetalleID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.isv = isv;
        this.descuento = descuento;
    }

    public Integer getCotizacionDetalleID() {
        return cotizacionDetalleID;
    }

    public void setCotizacionDetalleID(Integer cotizacionDetalleID) {
        this.cotizacionDetalleID = cotizacionDetalleID;
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

    public Cotizacion getCotizacionID() {
        return cotizacionID;
    }

    public void setCotizacionID(Cotizacion cotizacionID) {
        this.cotizacionID = cotizacionID;
    }

    public Producto getProductoID() {
        return productoID;
    }

    public void setProductoID(Producto productoID) {
        this.productoID = productoID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cotizacionDetalleID != null ? cotizacionDetalleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizaciondetalle)) {
            return false;
        }
        Cotizaciondetalle other = (Cotizaciondetalle) object;
        if ((this.cotizacionDetalleID == null && other.cotizacionDetalleID != null) || (this.cotizacionDetalleID != null && !this.cotizacionDetalleID.equals(other.cotizacionDetalleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Cotizaciondetalle[ cotizacionDetalleID=" + cotizacionDetalleID + " ]";
    }
    
}
