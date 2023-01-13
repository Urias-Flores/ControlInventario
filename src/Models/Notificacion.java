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
import javax.persistence.Lob;
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
    @NamedQuery(name = "Notificacion.findAll", query = "SELECT n FROM Notificacion n"),
    @NamedQuery(name = "Notificacion.findByNotificacionID", query = "SELECT n FROM Notificacion n WHERE n.notificacionID = :notificacionID"),
    @NamedQuery(name = "Notificacion.findByFecha", query = "SELECT n FROM Notificacion n WHERE n.fecha = :fecha"),
    @NamedQuery(name = "Notificacion.findByHora", query = "SELECT n FROM Notificacion n WHERE n.hora = :hora"),
    @NamedQuery(name = "Notificacion.findByTitulo", query = "SELECT n FROM Notificacion n WHERE n.titulo = :titulo"),
    @NamedQuery(name = "Notificacion.findByEstado", query = "SELECT n FROM Notificacion n WHERE n.estado = :estado"),
    @NamedQuery(name = "Notificacion.findByDestino", query = "SELECT n FROM Notificacion n WHERE n.destino = :destino")})
public class Notificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer notificacionID;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    private String titulo;
    @Basic(optional = false)
    @Lob
    private String contenido;
    @Basic(optional = false)
    private Character estado;
    @Basic(optional = false)
    private Character destino;

    public Notificacion() {
    }

    public Notificacion(Integer notificacionID) {
        this.notificacionID = notificacionID;
    }

    public Notificacion(Integer notificacionID, Date fecha, Date hora, String titulo, String contenido, Character estado, Character destino) {
        this.notificacionID = notificacionID;
        this.fecha = fecha;
        this.hora = hora;
        this.titulo = titulo;
        this.contenido = contenido;
        this.estado = estado;
        this.destino = destino;
    }

    public Integer getNotificacionID() {
        return notificacionID;
    }

    public void setNotificacionID(Integer notificacionID) {
        this.notificacionID = notificacionID;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Character getEstado() {
        return estado;
    }

    public void setEstado(Character estado) {
        this.estado = estado;
    }

    public Character getDestino() {
        return destino;
    }

    public void setDestino(Character destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificacionID != null ? notificacionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notificacion)) {
            return false;
        }
        Notificacion other = (Notificacion) object;
        if ((this.notificacionID == null && other.notificacionID != null) || (this.notificacionID != null && !this.notificacionID.equals(other.notificacionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Notificacion[ notificacionID=" + notificacionID + " ]";
    }
    
}
