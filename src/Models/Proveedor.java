package Models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByProveedorID", query = "SELECT p FROM Proveedor p WHERE p.proveedorID = :proveedorID"),
    @NamedQuery(name = "Proveedor.findByNombre", query = "SELECT p FROM Proveedor p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Proveedor.findByRtn", query = "SELECT p FROM Proveedor p WHERE p.rtn = :rtn"),
    @NamedQuery(name = "Proveedor.findByCorreoElectronico", query = "SELECT p FROM Proveedor p WHERE p.correoElectronico = :correoElectronico"),
    @NamedQuery(name = "Proveedor.findByNumeroTelefono", query = "SELECT p FROM Proveedor p WHERE p.numeroTelefono = :numeroTelefono"),
    @NamedQuery(name = "Proveedor.findBySaldo", query = "SELECT p FROM Proveedor p WHERE p.saldo = :saldo")})
public class Proveedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer proveedorID;
    @Basic(optional = false)
    private String nombre;
    @Basic(optional = false)
    private String rtn;
    @Basic(optional = false)
    private String correoElectronico;
    @Basic(optional = false)
    private String numeroTelefono;
    @Basic(optional = false)
    private float saldo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedorID")
    private List<Compra> compraList;

    public Proveedor() {
    }

    public Proveedor(Integer proveedorID) {
        this.proveedorID = proveedorID;
    }

    public Proveedor(Integer proveedorID, String nombre, String rtn, String correoElectronico, String numeroTelefono, float saldo) {
        this.proveedorID = proveedorID;
        this.nombre = nombre;
        this.rtn = rtn;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.saldo = saldo;
    }

    public Integer getProveedorID() {
        return proveedorID;
    }

    public void setProveedorID(Integer proveedorID) {
        this.proveedorID = proveedorID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRtn() {
        return rtn;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
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

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proveedorID != null ? proveedorID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.proveedorID == null && other.proveedorID != null) || (this.proveedorID != null && !this.proveedorID.equals(other.proveedorID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
