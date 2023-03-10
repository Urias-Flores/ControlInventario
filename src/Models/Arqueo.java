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
    @NamedQuery(name = "Arqueo.findAll", query = "SELECT a FROM Arqueo a ORDER BY a.fecha DESC"),
    @NamedQuery(name = "Arqueo.findByArqueoID", query = "SELECT a FROM Arqueo a WHERE a.arqueoID = :arqueoID"),
    @NamedQuery(name = "Arqueo.findByFecha", query = "SELECT a FROM Arqueo a WHERE a.fecha = :fecha"),
    @NamedQuery(name = "Arqueo.findByHora", query = "SELECT a FROM Arqueo a WHERE a.hora = :hora"),
    @NamedQuery(name = "Arqueo.findBySaldoInicial", query = "SELECT a FROM Arqueo a WHERE a.saldoInicial = :saldoInicial"),
    @NamedQuery(name = "Arqueo.findBySaldoFinalSistema", query = "SELECT a FROM Arqueo a WHERE a.saldoFinalSistema = :saldoFinalSistema"),
    @NamedQuery(name = "Arqueo.findBySaldoFinalUsuario", query = "SELECT a FROM Arqueo a WHERE a.saldoFinalUsuario = :saldoFinalUsuario")})
public class Arqueo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer arqueoID;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    private float saldoInicial;
    @Basic(optional = false)
    private float saldoFinalSistema;
    @Basic(optional = false)
    private float saldoFinalUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arqueoID")
    private List<Arqueodetalle> arqueodetalleList;
    @JoinColumn(name = "UsuarioID", referencedColumnName = "UsuarioID")
    @ManyToOne(optional = false)
    private Usuario usuarioID;

    public Arqueo() {
    }

    public Arqueo(Integer arqueoID) {
        this.arqueoID = arqueoID;
    }

    public Arqueo(Integer arqueoID, float saldoInicial, float saldoFinalSistema, float saldoFinalUsuario) {
        this.arqueoID = arqueoID;
        this.saldoInicial = saldoInicial;
        this.saldoFinalSistema = saldoFinalSistema;
        this.saldoFinalUsuario = saldoFinalUsuario;
    }

    public Integer getArqueoID() {
        return arqueoID;
    }

    public void setArqueoID(Integer arqueoID) {
        this.arqueoID = arqueoID;
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

    public float getSaldoInicial() {
        return saldoInicial;
    }

    public void setSaldoInicial(float saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    public float getSaldoFinalSistema() {
        return saldoFinalSistema;
    }

    public void setSaldoFinalSistema(float saldoFinalSistema) {
        this.saldoFinalSistema = saldoFinalSistema;
    }

    public float getSaldoFinalUsuario() {
        return saldoFinalUsuario;
    }

    public void setSaldoFinalUsuario(float saldoFinalUsuario) {
        this.saldoFinalUsuario = saldoFinalUsuario;
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
        hash += (arqueoID != null ? arqueoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arqueo)) {
            return false;
        }
        Arqueo other = (Arqueo) object;
        if ((this.arqueoID == null && other.arqueoID != null) || (this.arqueoID != null && !this.arqueoID.equals(other.arqueoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ModelTest.Arqueo[ arqueoID=" + arqueoID + " ]";
    }
    
}
