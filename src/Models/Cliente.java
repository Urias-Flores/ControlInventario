package Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByClienteID", query = "SELECT c FROM Cliente c WHERE c.clienteID = :clienteID"),
    @NamedQuery(name = "Cliente.findByNombre", query = "SELECT c FROM Cliente c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Cliente.findByDocumento", query = "SELECT c FROM Cliente c WHERE c.documento = :documento"),
    @NamedQuery(name = "Cliente.findByCorreoElectronico", query = "SELECT c FROM Cliente c WHERE c.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Cliente.findByNumeroTelefono", query = "SELECT c FROM Cliente c WHERE c.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Cliente.findByDomicilio", query = "SELECT c FROM Cliente c WHERE c.domicilio = :domicilio"),
    @NamedQuery(name = "Cliente.findBySaldo", query = "SELECT c FROM Cliente c WHERE c.saldo = :saldo")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer clienteID;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private String documento;
    @Basic(optional = false)
    private String correoElectronico;
    @Basic(optional = false)
    private String numeroTelefono;
    @Basic(optional = false)
    private String domicilio;
    @Basic(optional = false)
    private float saldo;
    @OneToMany(mappedBy = "clienteID")
    private List<Venta> ventaList;
    @OneToMany(mappedBy = "clienteID")
    private List<Abono> abonoList;
    @OneToMany(mappedBy = "clienteID")
    private List<Cotizacion> cotizacionList;

    public Cliente() {
    }

    public Cliente(Integer clienteID) {
        this.clienteID = clienteID;
    }

    public Cliente(Integer clienteID, String nombre, String documento, String correoElectronico, String numeroTelefono, String domicilio, float saldo) {
        this.clienteID = clienteID;
        this.nombre = nombre;
        this.documento = documento;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.domicilio = domicilio;
        this.saldo = saldo;
    }

    public Integer getClienteID() {
        return clienteID;
    }

    public void setClienteID(Integer clienteID) {
        this.clienteID = clienteID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Venta> getVentaList() {
        return ventaList;
    }

    public void setVentaList(List<Venta> ventaList) {
        this.ventaList = ventaList;
    }

    @XmlTransient
    public List<Abono> getAbonoList() {
        return abonoList;
    }

    public void setAbonoList(List<Abono> abonoList) {
        this.abonoList = abonoList;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (clienteID != null ? clienteID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.clienteID == null && other.clienteID != null) || (this.clienteID != null && !this.clienteID.equals(other.clienteID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
