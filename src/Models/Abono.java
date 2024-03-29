package Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abono.findAll", query = "SELECT a FROM Abono a"),
    @NamedQuery(name = "Abono.findByAbonoID", query = "SELECT a FROM Abono a WHERE a.abonoID = :abonoID"),
    @NamedQuery(name = "Abono.findByVentaID", query = "SELECT a FROM Abono a WHERE a.ventaID = :ventaID"),
    @NamedQuery(name = "Abono.findBySolicitudID", query = "SELECT a FROM Abono a WHERE a.solicitudID = :solicitudID"),
    @NamedQuery(name = "Abono.findByCompraID", query = "SELECT a FROM Abono a WHERE a.compraID = :compraID"),
    @NamedQuery(name = "Abono.findByFecha", query = "SELECT a FROM Abono a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Abono.findByHora", query = "SELECT a FROM Abono a WHERE a.hora = :hora"),
    @NamedQuery(name = "Abono.findByTipo", query = "SELECT a FROM Abono a WHERE a.tipo = :tipo"),
    @NamedQuery(name = "Abono.findByTotal", query = "SELECT a FROM Abono a WHERE a.total = :total")})
public class Abono implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer abonoID;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    private String tipo;
    @Basic(optional = false)
    private float total;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;
    @JoinColumn(name = "CompraID", referencedColumnName = "CompraID")
    @ManyToOne
    private Compra compraID;
    @JoinColumn(name = "VentaID", referencedColumnName = "VentaID")
    @ManyToOne
    private Venta ventaID;
    @JoinColumn(name = "SolicitudID", referencedColumnName = "SolicitudID")
    @ManyToOne
    private Solicitud solicitudID;

    public Abono() {
    }

    public Abono(Integer abonoID) {
        this.abonoID = abonoID;
    }

    public Abono(Integer abonoID, Date fecha, Date hora, String tipo, float total) {
        this.abonoID = abonoID;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.total = total;
    }

    public Integer getAbonoID() {
        return abonoID;
    }

    public void setAbonoID(Integer abonoID) {
        this.abonoID = abonoID;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Compra getCompraID() {
        return compraID;
    }

    public void setCompraID(Compra compraID) {
        this.compraID = compraID;
    }

    public Usuario getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Usuario usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Venta getVentaID() {
        return ventaID;
    }

    public void setVentaID(Venta ventaID) {
        this.ventaID = ventaID;
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
        hash += (abonoID != null ? abonoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abono)) {
            return false;
        }
        Abono other = (Abono) object;
        if ((this.abonoID == null && other.abonoID != null) || (this.abonoID != null && !this.abonoID.equals(other.abonoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Abono[ abonoID=" + abonoID + " ]";
    }
    
}
