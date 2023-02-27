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
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s"),
    @NamedQuery(name = "Solicitud.findBySolicitudID", query = "SELECT s FROM Solicitud s WHERE s.solicitudID = :solicitudID"),
    @NamedQuery(name = "Solicitud.findByEstado", query = "SELECT s FROM Solicitud s WHERE s.estado = :estado"),
    @NamedQuery(name = "Solicitud.findByRtn", query = "SELECT s FROM Solicitud s WHERE s.rtn = :rtn"),
    @NamedQuery(name = "Solicitud.findByFecha", query = "SELECT s FROM Solicitud s WHERE s.fecha = :fecha"),
    @NamedQuery(name = "Solicitud.findByHora", query = "SELECT s FROM Solicitud s WHERE s.hora = :hora")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer solicitudID;
    @Basic(optional = false)
    private String estado;
    private String rtn;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date hora;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "solicitudID")
    private List<Solicituddetalle> solicituddetalleList;
    @OneToMany(mappedBy = "solicitudID")
    private List<Arqueodetalle> arqueodetalleList;
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteID")
    @ManyToOne(optional = false)
    private Cliente clienteID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Solicitud() {
    }

    public Solicitud(Integer solicitudID) {
        this.solicitudID = solicitudID;
    }

    public Solicitud(Integer solicitudID, String estado, Date fecha, Date hora) {
        this.solicitudID = solicitudID;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getSolicitudID() {
        return solicitudID;
    }

    public void setSolicitudID(Integer solicitudID) {
        this.solicitudID = solicitudID;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
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
    public List<Solicituddetalle> getSolicituddetalleList() {
        return solicituddetalleList;
    }

    public void setSolicituddetalleList(List<Solicituddetalle> solicituddetalleList) {
        this.solicituddetalleList = solicituddetalleList;
    }

    @XmlTransient
    public List<Arqueodetalle> getArqueodetalleList() {
        return arqueodetalleList;
    }

    public void setArqueodetalleList(List<Arqueodetalle> arqueodetalleList) {
        this.arqueodetalleList = arqueodetalleList;
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
        hash += (solicitudID != null ? solicitudID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.solicitudID == null && other.solicitudID != null) || (this.solicitudID != null && !this.solicitudID.equals(other.solicitudID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Solicitud[ solicitudID=" + solicitudID + " ]";
    }
    
}
