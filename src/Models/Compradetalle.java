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
    @NamedQuery(name = "Compradetalle.findAll", query = "SELECT c FROM Compradetalle c"),
    @NamedQuery(name = "Compradetalle.findByCompraDetalleID", query = "SELECT c FROM Compradetalle c WHERE c.compraDetalleID = :compraDetalleID"),
    @NamedQuery(name = "Compradetalle.findByCantidad", query = "SELECT c FROM Compradetalle c WHERE c.cantidad = :cantidad"),
    @NamedQuery(name = "Compradetalle.findByPrecio", query = "SELECT c FROM Compradetalle c WHERE c.precio = :precio"),
    @NamedQuery(name = "Compradetalle.findByIsv", query = "SELECT c FROM Compradetalle c WHERE c.isv = :isv"),
    @NamedQuery(name = "Compradetalle.findByDescuento", query = "SELECT c FROM Compradetalle c WHERE c.descuento = :descuento")})
public class Compradetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer compraDetalleID;
    @Basic(optional = false)
    private float cantidad;
    @Basic(optional = false)
    private float precio;
    @Basic(optional = false)
    private float isv;
    @Basic(optional = false)
    private float descuento;
    @JoinColumn(name = "CompraID", referencedColumnName = "CompraID")
    @ManyToOne(optional = false)
    private Compra compraID;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private Producto productoID;

    public Compradetalle() {
    }

    public Compradetalle(Integer compraDetalleID) {
        this.compraDetalleID = compraDetalleID;
    }

    public Compradetalle(Integer compraDetalleID, float cantidad, float precio, float isv, float descuento) {
        this.compraDetalleID = compraDetalleID;
        this.cantidad = cantidad;
        this.precio = precio;
        this.isv = isv;
        this.descuento = descuento;
    }

    public Integer getCompraDetalleID() {
        return compraDetalleID;
    }

    public void setCompraDetalleID(Integer compraDetalleID) {
        this.compraDetalleID = compraDetalleID;
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

    public Compra getCompraID() {
        return compraID;
    }

    public void setCompraID(Compra compraID) {
        this.compraID = compraID;
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
        hash += (compraDetalleID != null ? compraDetalleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compradetalle)) {
            return false;
        }
        Compradetalle other = (Compradetalle) object;
        if ((this.compraDetalleID == null && other.compraDetalleID != null) || (this.compraDetalleID != null && !this.compraDetalleID.equals(other.compraDetalleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Compradetalle[ compraDetalleID=" + compraDetalleID + " ]";
    }
    
}
