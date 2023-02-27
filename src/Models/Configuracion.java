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
    @NamedQuery(name = "Configuracion.findAll", query = "SELECT c FROM Configuracion c"),
    @NamedQuery(name = "Configuracion.findByConfiguracionID", query = "SELECT c FROM Configuracion c WHERE c.configuracionID = :configuracionID"),
    @NamedQuery(name = "Configuracion.findByNombre", query = "SELECT c FROM Configuracion c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Configuracion.findByDato", query = "SELECT c FROM Configuracion c WHERE c.dato = :dato"),
    @NamedQuery(name = "Configuracion.findByExtra", query = "SELECT c FROM Configuracion c WHERE c.extra = :extra")})
public class Configuracion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer configuracionID;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private String dato;
    private String extra;

    public Configuracion() {
    }

    public Configuracion(Integer configuracionID) {
        this.configuracionID = configuracionID;
    }

    public Configuracion(Integer configuracionID, String nombre, String dato) {
        this.configuracionID = configuracionID;
        this.nombre = nombre;
        this.dato = dato;
    }

    public Integer getConfiguracionID() {
        return configuracionID;
    }

    public void setConfiguracionID(Integer configuracionID) {
        this.configuracionID = configuracionID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (configuracionID != null ? configuracionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Configuracion)) {
            return false;
        }
        Configuracion other = (Configuracion) object;
        if ((this.configuracionID == null && other.configuracionID != null) || (this.configuracionID != null && !this.configuracionID.equals(other.configuracionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Configuracion[ configuracionID=" + configuracionID + " ]";
    }
    
}
