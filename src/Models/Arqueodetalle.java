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
    @NamedQuery(name = "Arqueodetalle.findAll", query = "SELECT a FROM Arqueodetalle a"),
    @NamedQuery(name = "Arqueodetalle.findByArqueoDetalleID", query = "SELECT a FROM Arqueodetalle a WHERE a.arqueoDetalleID = :arqueoDetalleID"),
    @NamedQuery(name = "Arqueodetalle.findByEfectivo", query = "SELECT a FROM Arqueodetalle a WHERE a.efectivo = :efectivo"),
    @NamedQuery(name = "Arqueodetalle.findByCambio", query = "SELECT a FROM Arqueodetalle a WHERE a.cambio = :cambio")})
public class Arqueodetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer arqueoDetalleID;
    @Basic(optional = false)
    private float efectivo;
    @Basic(optional = false)
    private float cambio;
    @JoinColumn(name = "ArqueoID", referencedColumnName = "ArqueoID")
    @ManyToOne(optional = false)
    private Arqueo arqueoID;
    @JoinColumn(name = "GastoID", referencedColumnName = "GastoID")
    @ManyToOne
    private Gasto gastoID;
    @JoinColumn(name = "SolicitudID", referencedColumnName = "SolicitudID")
    @ManyToOne
    private Solicitud solicitudID;
    @JoinColumn(name = "FacturaID", referencedColumnName = "VentaID")
    @ManyToOne
    private Venta facturaID;

    public Arqueodetalle() {
    }

    public Arqueodetalle(Integer arqueoDetalleID) {
        this.arqueoDetalleID = arqueoDetalleID;
    }

    public Arqueodetalle(Integer arqueoDetalleID, float efectivo, float cambio) {
        this.arqueoDetalleID = arqueoDetalleID;
        this.efectivo = efectivo;
        this.cambio = cambio;
    }

    public Integer getArqueoDetalleID() {
        return arqueoDetalleID;
    }

    public void setArqueoDetalleID(Integer arqueoDetalleID) {
        this.arqueoDetalleID = arqueoDetalleID;
    }

    public float getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(float efectivo) {
        this.efectivo = efectivo;
    }

    public float getCambio() {
        return cambio;
    }

    public void setCambio(float cambio) {
        this.cambio = cambio;
    }

    public Arqueo getArqueoID() {
        return arqueoID;
    }

    public void setArqueoID(Arqueo arqueoID) {
        this.arqueoID = arqueoID;
    }

    public Gasto getGastoID() {
        return gastoID;
    }

    public void setGastoID(Gasto gastoID) {
        this.gastoID = gastoID;
    }

    public Solicitud getSolicitudID() {
        return solicitudID;
    }

    public void setSolicitudID(Solicitud solicitudID) {
        this.solicitudID = solicitudID;
    }

    public Venta getFacturaID() {
        return facturaID;
    }

    public void setFacturaID(Venta facturaID) {
        this.facturaID = facturaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (arqueoDetalleID != null ? arqueoDetalleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arqueodetalle)) {
            return false;
        }
        Arqueodetalle other = (Arqueodetalle) object;
        if ((this.arqueoDetalleID == null && other.arqueoDetalleID != null) || (this.arqueoDetalleID != null && !this.arqueoDetalleID.equals(other.arqueoDetalleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Arqueodetalle[ arqueoDetalleID=" + arqueoDetalleID + " ]";
    }
    
}
