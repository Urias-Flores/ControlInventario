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

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Venta.findAll", query = "SELECT v FROM Venta v"),
    @NamedQuery(name = "Venta.findByVentaID", query = "SELECT v FROM Venta v WHERE v.ventaID = :ventaID"),
    @NamedQuery(name = "Venta.findByEstado", query = "SELECT v FROM Venta v WHERE v.estado = :estado"),
    @NamedQuery(name = "Venta.findByRtn", query = "SELECT v FROM Venta v WHERE v.rtn = :rtn"),
    @NamedQuery(name = "Venta.findByFecha", query = "SELECT v FROM Venta v WHERE v.fecha = :fecha"),
    @NamedQuery(name = "Venta.findByHora", query = "SELECT v FROM Venta v WHERE v.hora = :hora")})
public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer ventaID;
    @Basic(optional = false)
    private String estado;
    private String rtn;
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @JoinColumn(name = "ClienteID", referencedColumnName = "ClienteID")
    @ManyToOne
    private Cliente clienteID;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;
    @OneToMany(mappedBy = "facturaID")
    private List<Arqueodetalle> arqueodetalleList;
    @OneToMany(mappedBy = "ventaID")
    private List<Abono> abonoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ventaID")
    private List<Ventadetalle> ventadetalleList;

    public Venta() {
    }

    public Venta(Integer ventaID) {
        this.ventaID = ventaID;
    }

    public Venta(Integer ventaID, String estado, Date fecha, Date hora) {
        this.ventaID = ventaID;
        this.estado = estado;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Integer getVentaID() {
        return ventaID;
    }

    public void setVentaID(Integer ventaID) {
        this.ventaID = ventaID;
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

    @XmlTransient
    public List<Arqueodetalle> getArqueodetalleList() {
        return arqueodetalleList;
    }

    public void setArqueodetalleList(List<Arqueodetalle> arqueodetalleList) {
        this.arqueodetalleList = arqueodetalleList;
    }

    @XmlTransient
    public List<Abono> getAbonoList() {
        return abonoList;
    }

    public void setAbonoList(List<Abono> abonoList) {
        this.abonoList = abonoList;
    }

    @XmlTransient
    public List<Ventadetalle> getVentadetalleList() {
        return ventadetalleList;
    }

    public void setVentadetalleList(List<Ventadetalle> ventadetalleList) {
        this.ventadetalleList = ventadetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ventaID != null ? ventaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Venta)) {
            return false;
        }
        Venta other = (Venta) object;
        if ((this.ventaID == null && other.ventaID != null) || (this.ventaID != null && !this.ventaID.equals(other.ventaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Models.Venta[ ventaID=" + ventaID + " ]";
    }
    
}
