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

/**
 *
 * @author Dell
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p"),
    @NamedQuery(name = "Producto.findByProductoID", query = "SELECT p FROM Producto p WHERE p.productoID = :productoID"),
    @NamedQuery(name = "Producto.findByDescripcion", query = "SELECT p FROM Producto p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Producto.findByBarra", query = "SELECT p FROM Producto p WHERE p.barra = :barra"),
    @NamedQuery(name = "Producto.findByUnidad", query = "SELECT p FROM Producto p WHERE p.unidad = :unidad"),
    @NamedQuery(name = "Producto.findByCantidadMinima", query = "SELECT p FROM Producto p WHERE p.cantidadMinima = :cantidadMinima"),
    @NamedQuery(name = "Producto.findByPrecioCompra", query = "SELECT p FROM Producto p WHERE p.precioCompra = :precioCompra"),
    @NamedQuery(name = "Producto.findByPrecioVenta", query = "SELECT p FROM Producto p WHERE p.precioVenta = :precioVenta")})
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer productoID;
    @Basic(optional = false)
    private String descripcion;
    private String barra;
    @Basic(optional = false)
    private String unidad;
    @Basic(optional = false)
    private float cantidadMinima;
    @Basic(optional = false)
    private float precioCompra;
    @Basic(optional = false)
    private float precioVenta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoID")
    private List<Inventariodetalleacciones> inventariodetalleaccionesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoID")
    private List<Compradetalle> compradetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoID")
    private List<Cotizaciondetalle> cotizaciondetalleList;
    @JoinColumn(name = "CategoriaID", referencedColumnName = "CategoriaID")
    @ManyToOne(optional = false)
    private Categoria categoriaID;
    @JoinColumn(name = "MarcaID", referencedColumnName = "MarcaID")
    @ManyToOne(optional = false)
    private Marca marcaID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoID")
    private List<Ventadetalle> ventadetalleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productoID")
    private List<Inventario> inventarioList;

    public Producto() {
    }

    public Producto(Integer productoID) {
        this.productoID = productoID;
    }

    public Producto(Integer productoID, String descripcion, String unidad, float cantidadMinima, float precioCompra, float precioVenta) {
        this.productoID = productoID;
        this.descripcion = descripcion;
        this.unidad = unidad;
        this.cantidadMinima = cantidadMinima;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
    }

    public Integer getProductoID() {
        return productoID;
    }

    public void setProductoID(Integer productoID) {
        this.productoID = productoID;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public float getCantidadMinima() {
        return cantidadMinima;
    }

    public void setCantidadMinima(float cantidadMinima) {
        this.cantidadMinima = cantidadMinima;
    }

    public float getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(float precioCompra) {
        this.precioCompra = precioCompra;
    }

    public float getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(float precioVenta) {
        this.precioVenta = precioVenta;
    }

    @XmlTransient
    public List<Inventariodetalleacciones> getInventariodetalleaccionesList() {
        return inventariodetalleaccionesList;
    }

    public void setInventariodetalleaccionesList(List<Inventariodetalleacciones> inventariodetalleaccionesList) {
        this.inventariodetalleaccionesList = inventariodetalleaccionesList;
    }

    @XmlTransient
    public List<Compradetalle> getCompradetalleList() {
        return compradetalleList;
    }

    public void setCompradetalleList(List<Compradetalle> compradetalleList) {
        this.compradetalleList = compradetalleList;
    }

    @XmlTransient
    public List<Cotizaciondetalle> getCotizaciondetalleList() {
        return cotizaciondetalleList;
    }

    public void setCotizaciondetalleList(List<Cotizaciondetalle> cotizaciondetalleList) {
        this.cotizaciondetalleList = cotizaciondetalleList;
    }

    public Categoria getCategoriaID() {
        return categoriaID;
    }

    public void setCategoriaID(Categoria categoriaID) {
        this.categoriaID = categoriaID;
    }

    public Marca getMarcaID() {
        return marcaID;
    }

    public void setMarcaID(Marca marcaID) {
        this.marcaID = marcaID;
    }

    @XmlTransient
    public List<Ventadetalle> getVentadetalleList() {
        return ventadetalleList;
    }

    public void setVentadetalleList(List<Ventadetalle> ventadetalleList) {
        this.ventadetalleList = ventadetalleList;
    }

    @XmlTransient
    public List<Inventario> getInventarioList() {
        return inventarioList;
    }

    public void setInventarioList(List<Inventario> inventarioList) {
        this.inventarioList = inventarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productoID != null ? productoID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Producto)) {
            return false;
        }
        Producto other = (Producto) object;
        if ((this.productoID == null && other.productoID != null) || (this.productoID != null && !this.productoID.equals(other.productoID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
