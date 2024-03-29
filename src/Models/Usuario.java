package Models;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.nombre"),
    @NamedQuery(name = "Usuario.findByUsuarioID", query = "SELECT u FROM Usuario u WHERE u.usuarioID = :usuarioID"),
    @NamedQuery(name = "Usuario.findByNombre", query = "SELECT u FROM Usuario u WHERE u.nombre = :nombre"),
    @NamedQuery(name = "Usuario.findByContrasena", query = "SELECT u FROM Usuario u WHERE u.contrasena = :contrasena"),
    @NamedQuery(name = "Usuario.findByToken", query = "SELECT u FROM Usuario u WHERE u.token = :token"),
    @NamedQuery(name = "Usuario.findByEstado", query = "SELECT u FROM Usuario u WHERE u.estado = :estado"),
    @NamedQuery(name = "Usuario.findByCargo", query = "SELECT u FROM Usuario u WHERE u.cargo = :cargo")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer usuarioID;
    @Basic(optional = false)
    private String nombre;
    private String contrasena;
    private String token;
    private Integer estado;
    @Basic(optional = false)
    private String cargo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Inventariodetalleacciones> inventariodetalleaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Compra> compraList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Venta> ventaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Abono> abonoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Gasto> gastoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Cotizacion> cotizacionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Solicitud> solicitudList;
    @JoinColumn(name = "EmpleadoID", referencedColumnName = "EmpleadoID")
    @ManyToOne(optional = false)
    private Empleado empleadoID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioID")
    private List<Arqueo> arqueoList;

    public Usuario() {
    }

    public Usuario(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public Usuario(Integer usuarioID, String nombre, String cargo) {
        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.cargo = cargo;
    }

    public Integer getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(Integer usuarioID) {
        this.usuarioID = usuarioID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @XmlTransient
    public List<Inventariodetalleacciones> getInventariodetalleaccionesList() {
        return inventariodetalleaccionesList;
    }

    public void setInventariodetalleaccionesList(List<Inventariodetalleacciones> inventariodetalleaccionesList) {
        this.inventariodetalleaccionesList = inventariodetalleaccionesList;
    }

    @XmlTransient
    public List<Compra> getCompraList() {
        return compraList;
    }

    public void setCompraList(List<Compra> compraList) {
        this.compraList = compraList;
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
    public List<Gasto> getGastoList() {
        return gastoList;
    }

    public void setGastoList(List<Gasto> gastoList) {
        this.gastoList = gastoList;
    }

    @XmlTransient
    public List<Cotizacion> getCotizacionList() {
        return cotizacionList;
    }

    public void setCotizacionList(List<Cotizacion> cotizacionList) {
        this.cotizacionList = cotizacionList;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    public Empleado getEmpleadoID() {
        return empleadoID;
    }

    public void setEmpleadoID(Empleado empleadoID) {
        this.empleadoID = empleadoID;
    }

    @XmlTransient
    public List<Arqueo> getArqueoList() {
        return arqueoList;
    }

    public void setArqueoList(List<Arqueo> arqueoList) {
        this.arqueoList = arqueoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioID != null ? usuarioID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuarioID == null && other.usuarioID != null) || (this.usuarioID != null && !this.usuarioID.equals(other.usuarioID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
