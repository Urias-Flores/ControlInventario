/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dell
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gasto.findAll", query = "SELECT g FROM Gasto g"),
    @NamedQuery(name = "Gasto.findByGastoID", query = "SELECT g FROM Gasto g WHERE g.gastoID = :gastoID"),
    @NamedQuery(name = "Gasto.findByDescripcion", query = "SELECT g FROM Gasto g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "Gasto.findByTotal", query = "SELECT g FROM Gasto g WHERE g.total = :total")})
public class Gasto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer gastoID;
    @Basic(optional = false)
    private String descripcion;
    @Basic(optional = false)
    private float total;
    @OneToMany(mappedBy = "gastoID")
    private List<Arqueodetalle> arqueodetalleList;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Gasto() {
    }

    public Gasto(Integer gastoID) {
        this.gastoID = gastoID;
    }

    public Gasto(Integer gastoID, String descripcion, float total) {
        this.gastoID = gastoID;
        this.descripcion = descripcion;
        this.total = total;
    }

    public Integer getGastoID() {
        return gastoID;
    }

    public void setGastoID(Integer gastoID) {
        this.gastoID = gastoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @XmlTransient
    public List<Arqueodetalle> getArqueodetalleList() {
        return arqueodetalleList;
    }

    public void setArqueodetalleList(List<Arqueodetalle> arqueodetalleList) {
        this.arqueodetalleList = arqueodetalleList;
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
        hash += (gastoID != null ? gastoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gasto)) {
            return false;
        }
        Gasto other = (Gasto) object;
        if ((this.gastoID == null && other.gastoID != null) || (this.gastoID != null && !this.gastoID.equals(other.gastoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Gasto[ gastoID=" + gastoID + " ]";
    }
    
}
