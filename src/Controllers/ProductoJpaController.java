package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Categoria;
import Models.Marca;
import Models.Inventariodetalleacciones;
import java.util.ArrayList;
import java.util.List;
import Models.Compradetalle;
import Models.Cotizaciondetalle;
import Models.Ventadetalle;
import Models.Inventario;
import Models.Producto;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProductoJpaController implements Serializable {

    public ProductoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Producto producto) {
        if (producto.getInventariodetalleaccionesList() == null) {
            producto.setInventariodetalleaccionesList(new ArrayList<Inventariodetalleacciones>());
        }
        if (producto.getCompradetalleList() == null) {
            producto.setCompradetalleList(new ArrayList<Compradetalle>());
        }
        if (producto.getCotizaciondetalleList() == null) {
            producto.setCotizaciondetalleList(new ArrayList<Cotizaciondetalle>());
        }
        if (producto.getVentadetalleList() == null) {
            producto.setVentadetalleList(new ArrayList<Ventadetalle>());
        }
        if (producto.getInventarioList() == null) {
            producto.setInventarioList(new ArrayList<Inventario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaID = producto.getCategoriaID();
            if (categoriaID != null) {
                categoriaID = em.getReference(categoriaID.getClass(), categoriaID.getCategoriaID());
                producto.setCategoriaID(categoriaID);
            }
            Marca marcaID = producto.getMarcaID();
            if (marcaID != null) {
                marcaID = em.getReference(marcaID.getClass(), marcaID.getMarcaID());
                producto.setMarcaID(marcaID);
            }
            List<Inventariodetalleacciones> attachedInventariodetalleaccionesList = new ArrayList<Inventariodetalleacciones>();
            for (Inventariodetalleacciones inventariodetalleaccionesListInventariodetalleaccionesToAttach : producto.getInventariodetalleaccionesList()) {
                inventariodetalleaccionesListInventariodetalleaccionesToAttach = em.getReference(inventariodetalleaccionesListInventariodetalleaccionesToAttach.getClass(), inventariodetalleaccionesListInventariodetalleaccionesToAttach.getInventarioDetalleAccionesID());
                attachedInventariodetalleaccionesList.add(inventariodetalleaccionesListInventariodetalleaccionesToAttach);
            }
            producto.setInventariodetalleaccionesList(attachedInventariodetalleaccionesList);
            List<Compradetalle> attachedCompradetalleList = new ArrayList<Compradetalle>();
            for (Compradetalle compradetalleListCompradetalleToAttach : producto.getCompradetalleList()) {
                compradetalleListCompradetalleToAttach = em.getReference(compradetalleListCompradetalleToAttach.getClass(), compradetalleListCompradetalleToAttach.getCompraDetalleID());
                attachedCompradetalleList.add(compradetalleListCompradetalleToAttach);
            }
            producto.setCompradetalleList(attachedCompradetalleList);
            List<Cotizaciondetalle> attachedCotizaciondetalleList = new ArrayList<Cotizaciondetalle>();
            for (Cotizaciondetalle cotizaciondetalleListCotizaciondetalleToAttach : producto.getCotizaciondetalleList()) {
                cotizaciondetalleListCotizaciondetalleToAttach = em.getReference(cotizaciondetalleListCotizaciondetalleToAttach.getClass(), cotizaciondetalleListCotizaciondetalleToAttach.getCotizacionDetalleID());
                attachedCotizaciondetalleList.add(cotizaciondetalleListCotizaciondetalleToAttach);
            }
            producto.setCotizaciondetalleList(attachedCotizaciondetalleList);
            List<Ventadetalle> attachedVentadetalleList = new ArrayList<Ventadetalle>();
            for (Ventadetalle ventadetalleListVentadetalleToAttach : producto.getVentadetalleList()) {
                ventadetalleListVentadetalleToAttach = em.getReference(ventadetalleListVentadetalleToAttach.getClass(), ventadetalleListVentadetalleToAttach.getVentaDetalleID());
                attachedVentadetalleList.add(ventadetalleListVentadetalleToAttach);
            }
            producto.setVentadetalleList(attachedVentadetalleList);
            List<Inventario> attachedInventarioList = new ArrayList<Inventario>();
            for (Inventario inventarioListInventarioToAttach : producto.getInventarioList()) {
                inventarioListInventarioToAttach = em.getReference(inventarioListInventarioToAttach.getClass(), inventarioListInventarioToAttach.getInventarioID());
                attachedInventarioList.add(inventarioListInventarioToAttach);
            }
            producto.setInventarioList(attachedInventarioList);
            em.persist(producto);
            if (categoriaID != null) {
                categoriaID.getProductoList().add(producto);
                categoriaID = em.merge(categoriaID);
            }
            if (marcaID != null) {
                marcaID.getProductoList().add(producto);
                marcaID = em.merge(marcaID);
            }
            for (Inventariodetalleacciones inventariodetalleaccionesListInventariodetalleacciones : producto.getInventariodetalleaccionesList()) {
                Producto oldProductoIDOfInventariodetalleaccionesListInventariodetalleacciones = inventariodetalleaccionesListInventariodetalleacciones.getProductoID();
                inventariodetalleaccionesListInventariodetalleacciones.setProductoID(producto);
                inventariodetalleaccionesListInventariodetalleacciones = em.merge(inventariodetalleaccionesListInventariodetalleacciones);
                if (oldProductoIDOfInventariodetalleaccionesListInventariodetalleacciones != null) {
                    oldProductoIDOfInventariodetalleaccionesListInventariodetalleacciones.getInventariodetalleaccionesList().remove(inventariodetalleaccionesListInventariodetalleacciones);
                    oldProductoIDOfInventariodetalleaccionesListInventariodetalleacciones = em.merge(oldProductoIDOfInventariodetalleaccionesListInventariodetalleacciones);
                }
            }
            for (Compradetalle compradetalleListCompradetalle : producto.getCompradetalleList()) {
                Producto oldProductoIDOfCompradetalleListCompradetalle = compradetalleListCompradetalle.getProductoID();
                compradetalleListCompradetalle.setProductoID(producto);
                compradetalleListCompradetalle = em.merge(compradetalleListCompradetalle);
                if (oldProductoIDOfCompradetalleListCompradetalle != null) {
                    oldProductoIDOfCompradetalleListCompradetalle.getCompradetalleList().remove(compradetalleListCompradetalle);
                    oldProductoIDOfCompradetalleListCompradetalle = em.merge(oldProductoIDOfCompradetalleListCompradetalle);
                }
            }
            for (Cotizaciondetalle cotizaciondetalleListCotizaciondetalle : producto.getCotizaciondetalleList()) {
                Producto oldProductoIDOfCotizaciondetalleListCotizaciondetalle = cotizaciondetalleListCotizaciondetalle.getProductoID();
                cotizaciondetalleListCotizaciondetalle.setProductoID(producto);
                cotizaciondetalleListCotizaciondetalle = em.merge(cotizaciondetalleListCotizaciondetalle);
                if (oldProductoIDOfCotizaciondetalleListCotizaciondetalle != null) {
                    oldProductoIDOfCotizaciondetalleListCotizaciondetalle.getCotizaciondetalleList().remove(cotizaciondetalleListCotizaciondetalle);
                    oldProductoIDOfCotizaciondetalleListCotizaciondetalle = em.merge(oldProductoIDOfCotizaciondetalleListCotizaciondetalle);
                }
            }
            for (Ventadetalle ventadetalleListVentadetalle : producto.getVentadetalleList()) {
                Producto oldProductoIDOfVentadetalleListVentadetalle = ventadetalleListVentadetalle.getProductoID();
                ventadetalleListVentadetalle.setProductoID(producto);
                ventadetalleListVentadetalle = em.merge(ventadetalleListVentadetalle);
                if (oldProductoIDOfVentadetalleListVentadetalle != null) {
                    oldProductoIDOfVentadetalleListVentadetalle.getVentadetalleList().remove(ventadetalleListVentadetalle);
                    oldProductoIDOfVentadetalleListVentadetalle = em.merge(oldProductoIDOfVentadetalleListVentadetalle);
                }
            }
            for (Inventario inventarioListInventario : producto.getInventarioList()) {
                Producto oldProductoIDOfInventarioListInventario = inventarioListInventario.getProductoID();
                inventarioListInventario.setProductoID(producto);
                inventarioListInventario = em.merge(inventarioListInventario);
                if (oldProductoIDOfInventarioListInventario != null) {
                    oldProductoIDOfInventarioListInventario.getInventarioList().remove(inventarioListInventario);
                    oldProductoIDOfInventarioListInventario = em.merge(oldProductoIDOfInventarioListInventario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Producto producto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto persistentProducto = em.find(Producto.class, producto.getProductoID());
            Categoria categoriaIDOld = persistentProducto.getCategoriaID();
            Categoria categoriaIDNew = producto.getCategoriaID();
            Marca marcaIDOld = persistentProducto.getMarcaID();
            Marca marcaIDNew = producto.getMarcaID();
            List<Inventariodetalleacciones> inventariodetalleaccionesListNew = producto.getInventariodetalleaccionesList();
            List<Compradetalle> compradetalleListNew = producto.getCompradetalleList();
            List<Cotizaciondetalle> cotizaciondetalleListNew = producto.getCotizaciondetalleList();
            List<Ventadetalle> ventadetalleListNew = producto.getVentadetalleList();
            List<Inventario> inventarioListNew = producto.getInventarioList();
            List<String> illegalOrphanMessages = null;
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaIDNew != null) {
                categoriaIDNew = em.getReference(categoriaIDNew.getClass(), categoriaIDNew.getCategoriaID());
                producto.setCategoriaID(categoriaIDNew);
            }
            if (marcaIDNew != null) {
                marcaIDNew = em.getReference(marcaIDNew.getClass(), marcaIDNew.getMarcaID());
                producto.setMarcaID(marcaIDNew);
            }
            
            producto.setInventariodetalleaccionesList(inventariodetalleaccionesListNew);
            
            producto.setCompradetalleList(compradetalleListNew);
            
            producto.setCotizaciondetalleList(cotizaciondetalleListNew);
            
            producto.setVentadetalleList(ventadetalleListNew);
            
            producto.setInventarioList(inventarioListNew);
            producto = em.merge(producto);
            if (categoriaIDOld != null && !categoriaIDOld.equals(categoriaIDNew)) {
                categoriaIDOld.getProductoList().remove(producto);
                categoriaIDOld = em.merge(categoriaIDOld);
            }
            if (categoriaIDNew != null && !categoriaIDNew.equals(categoriaIDOld)) {
                categoriaIDNew.getProductoList().add(producto);
                categoriaIDNew = em.merge(categoriaIDNew);
            }
            if (marcaIDOld != null && !marcaIDOld.equals(marcaIDNew)) {
                marcaIDOld.getProductoList().remove(producto);
                marcaIDOld = em.merge(marcaIDOld);
            }
            if (marcaIDNew != null && !marcaIDNew.equals(marcaIDOld)) {
                marcaIDNew.getProductoList().add(producto);
                marcaIDNew = em.merge(marcaIDNew);
            }
            
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = producto.getProductoID();
                if (findProducto(id) == null) {
                    throw new NonexistentEntityException("The producto with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto;
            try {
                producto = em.getReference(Producto.class, id);
                producto.getProductoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The producto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Inventariodetalleacciones> inventariodetalleaccionesListOrphanCheck = producto.getInventariodetalleaccionesList();
            for (Inventariodetalleacciones inventariodetalleaccionesListOrphanCheckInventariodetalleacciones : inventariodetalleaccionesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Inventariodetalleacciones " + inventariodetalleaccionesListOrphanCheckInventariodetalleacciones + " in its inventariodetalleaccionesList field has a non-nullable productoID field.");
            }
            List<Compradetalle> compradetalleListOrphanCheck = producto.getCompradetalleList();
            for (Compradetalle compradetalleListOrphanCheckCompradetalle : compradetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Compradetalle " + compradetalleListOrphanCheckCompradetalle + " in its compradetalleList field has a non-nullable productoID field.");
            }
            List<Cotizaciondetalle> cotizaciondetalleListOrphanCheck = producto.getCotizaciondetalleList();
            for (Cotizaciondetalle cotizaciondetalleListOrphanCheckCotizaciondetalle : cotizaciondetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Cotizaciondetalle " + cotizaciondetalleListOrphanCheckCotizaciondetalle + " in its cotizaciondetalleList field has a non-nullable productoID field.");
            }
            List<Ventadetalle> ventadetalleListOrphanCheck = producto.getVentadetalleList();
            for (Ventadetalle ventadetalleListOrphanCheckVentadetalle : ventadetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Ventadetalle " + ventadetalleListOrphanCheckVentadetalle + " in its ventadetalleList field has a non-nullable productoID field.");
            }
            List<Inventario> inventarioListOrphanCheck = producto.getInventarioList();
            for (Inventario inventarioListOrphanCheckInventario : inventarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Producto (" + producto + ") cannot be destroyed since the Inventario " + inventarioListOrphanCheckInventario + " in its inventarioList field has a non-nullable productoID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria categoriaID = producto.getCategoriaID();
            if (categoriaID != null) {
                categoriaID.getProductoList().remove(producto);
                categoriaID = em.merge(categoriaID);
            }
            Marca marcaID = producto.getMarcaID();
            if (marcaID != null) {
                marcaID.getProductoList().remove(producto);
                marcaID = em.merge(marcaID);
            }
            em.remove(producto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Producto> findProductoEntities() {
        return findProductoEntities(true, -1, -1);
    }

    public List<Producto> findProductoEntities(int maxResults, int firstResult) {
        return findProductoEntities(false, maxResults, firstResult);
    }

    private List<Producto> findProductoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Producto.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Producto findProducto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Producto.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Producto> rt = cq.from(Producto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
