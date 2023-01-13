/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dell
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Inventariodetalleacciones.findAll", query = "SELECT i FROM Inventariodetalleacciones i"),
    @NamedQuery(name = "Inventariodetalleacciones.findByInventarioDetalleAccionesID", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.inventarioDetalleAccionesID = :inventarioDetalleAccionesID"),
    @NamedQuery(name = "Inventariodetalleacciones.findByFecha", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.fecha = :fecha"),
    @NamedQuery(name = "Inventariodetalleacciones.findByHora", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.hora = :hora"),
    @NamedQuery(name = "Inventariodetalleacciones.findByAccion", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.accion = :accion"),
    @NamedQuery(name = "Inventariodetalleacciones.findByExistenciaPrevia", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.existenciaPrevia = :existenciaPrevia"),
    @NamedQuery(name = "Inventariodetalleacciones.findByCantidadModificada", query = "SELECT i FROM Inventariodetalleacciones i WHERE i.cantidadModificada = :cantidadModificada")})
public class Inventariodetalleacciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer inventarioDetalleAccionesID;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Lob
    private String descripcion;
    @Basic(optional = false)
    private String accion;
    @Basic(optional = false)
    private float existenciaPrevia;
    @Basic(optional = false)
    private float cantidadModificada;
    @JoinColumn(name = "ProductoID", referencedColumnName = "ProductoID")
    @ManyToOne(optional = false)
    private Producto productoID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Inventariodetalleacciones() {
    }

    public Inventariodetalleacciones(Integer inventarioDetalleAccionesID) {
        this.inventarioDetalleAccionesID = inventarioDetalleAccionesID;
    }

    public Inventariodetalleacciones(Integer inventarioDetalleAccionesID, Date fecha, Date hora, String descripcion, String accion, float existenciaPrevia, float cantidadModificada) {
        this.inventarioDetalleAccionesID = inventarioDetalleAccionesID;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
        this.accion = accion;
        this.existenciaPrevia = existenciaPrevia;
        this.cantidadModificada = cantidadModificada;
    }

    public Integer getInventarioDetalleAccionesID() {
        return inventarioDetalleAccionesID;
    }

    public void setInventarioDetalleAccionesID(Integer inventarioDetalleAccionesID) {
        this.inventarioDetalleAccionesID = inventarioDetalleAccionesID;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public float getExistenciaPrevia() {
        return existenciaPrevia;
    }

    public void setExistenciaPrevia(float existenciaPrevia) {
        this.existenciaPrevia = existenciaPrevia;
    }

    public float getCantidadModificada() {
        return cantidadModificada;
    }

    public void setCantidadModificada(float cantidadModificada) {
        this.cantidadModificada = cantidadModificada;
    }

    public Producto getProductoID() {
        return productoID;
    }

    public void setProductoID(Producto productoID) {
        this.productoID = productoID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventarioDetalleAccionesID != null ? inventarioDetalleAccionesID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Inventariodetalleacciones)) {
            return false;
        }
        Inventariodetalleacciones other = (Inventariodetalleacciones) object;
        if ((this.inventarioDetalleAccionesID == null && other.inventarioDetalleAccionesID != null) || (this.inventarioDetalleAccionesID != null && !this.inventarioDetalleAccionesID.equals(other.inventarioDetalleAccionesID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Inventariodetalleacciones[ inventarioDetalleAccionesID=" + inventarioDetalleAccionesID + " ]";
    }
    
}
