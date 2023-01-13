/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cotizacion.findAll", query = "SELECT c FROM Cotizacion c"),
    @NamedQuery(name = "Cotizacion.findByCotizacionID", query = "SELECT c FROM Cotizacion c WHERE c.cotizacionID = :cotizacionID"),
    @NamedQuery(name = "Cotizacion.findByFecha", query = "SELECT c FROM Cotizacion c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cotizacion.findByHora", query = "SELECT c FROM Cotizacion c WHERE c.hora = :hora")})
public class Cotizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer cotizacionID;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cotizacionID")
    private List<Cotizaciondetalle> cotizaciondetalleList;
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteID")
    @ManyToOne
    private Cliente clienteID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Cotizacion() {
    }

    public Cotizacion(Integer cotizacionID) {
        this.cotizacionID = cotizacionID;
    }

    public Cotizacion(Integer cotizacionID, Date fecha, Date hora) {
        this.cotizacionID = cotizacionID;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getCotizacionID() {
        return cotizacionID;
    }

    public void setCotizacionID(Integer cotizacionID) {
        this.cotizacionID = cotizacionID;
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

    @XmlTransient
    public List<Cotizaciondetalle> getCotizaciondetalleList() {
        return cotizaciondetalleList;
    }

    public void setCotizaciondetalleList(List<Cotizaciondetalle> cotizaciondetalleList) {
        this.cotizaciondetalleList = cotizaciondetalleList;
    }

    public Cliente getClienteID() {
        return clienteID;
    }

    public void setClienteID(Cliente clienteID) {
        this.clienteID = clienteID;
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
        hash += (cotizacionID != null ? cotizacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cotizacion)) {
            return false;
        }
        Cotizacion other = (Cotizacion) object;
        if ((this.cotizacionID == null && other.cotizacionID != null) || (this.cotizacionID != null && !this.cotizacionID.equals(other.cotizacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Cotizacion[ cotizacionID=" + cotizacionID + " ]";
    }
    
}
