package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Compra;
import Models.Compradetalle;
import Models.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CompradetalleJpaController implements Serializable {

    public CompradetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compradetalle compradetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compraID = compradetalle.getCompraID();
            if (compraID != null) {
                compraID = em.getReference(compraID.getClass(), compraID.getCompraID());
                compradetalle.setCompraID(compraID);
            }
            Producto productoID = compradetalle.getProductoID();
            if (productoID != null) {
                productoID = em.getReference(productoID.getClass(), productoID.getProductoID());
                compradetalle.setProductoID(productoID);
            }
            em.persist(compradetalle);
            if (compraID != null) {
                compraID.getCompradetalleList().add(compradetalle);
                compraID = em.merge(compraID);
            }
            if (productoID != null) {
                productoID.getCompradetalleList().add(compradetalle);
                productoID = em.merge(productoID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compradetalle compradetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compradetalle persistentCompradetalle = em.find(Compradetalle.class, compradetalle.getCompraDetalleID());
            Compra compraIDOld = persistentCompradetalle.getCompraID();
            Compra compraIDNew = compradetalle.getCompraID();
            Producto productoIDOld = persistentCompradetalle.getProductoID();
            Producto productoIDNew = compradetalle.getProductoID();
            if (compraIDNew != null) {
                compraIDNew = em.getReference(compraIDNew.getClass(), compraIDNew.getCompraID());
                compradetalle.setCompraID(compraIDNew);
            }
            if (productoIDNew != null) {
                productoIDNew = em.getReference(productoIDNew.getClass(), productoIDNew.getProductoID());
                compradetalle.setProductoID(productoIDNew);
            }
            compradetalle = em.merge(compradetalle);
            if (compraIDOld != null && !compraIDOld.equals(compraIDNew)) {
                compraIDOld.getCompradetalleList().remove(compradetalle);
                compraIDOld = em.merge(compraIDOld);
            }
            if (compraIDNew != null && !compraIDNew.equals(compraIDOld)) {
                compraIDNew.getCompradetalleList().add(compradetalle);
                compraIDNew = em.merge(compraIDNew);
            }
            if (productoIDOld != null && !productoIDOld.equals(productoIDNew)) {
                productoIDOld.getCompradetalleList().remove(compradetalle);
                productoIDOld = em.merge(productoIDOld);
            }
            if (productoIDNew != null && !productoIDNew.equals(productoIDOld)) {
                productoIDNew.getCompradetalleList().add(compradetalle);
                productoIDNew = em.merge(productoIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compradetalle.getCompraDetalleID();
                if (findCompradetalle(id) == null) {
                    throw new NonexistentEntityException("The compradetalle with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compradetalle compradetalle;
            try {
                compradetalle = em.getReference(Compradetalle.class, id);
                compradetalle.getCompraDetalleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compradetalle with id " + id + " no longer exists.", enfe);
            }
            Compra compraID = compradetalle.getCompraID();
            if (compraID != null) {
                compraID.getCompradetalleList().remove(compradetalle);
                compraID = em.merge(compraID);
            }
            Producto productoID = compradetalle.getProductoID();
            if (productoID != null) {
                productoID.getCompradetalleList().remove(compradetalle);
                productoID = em.merge(productoID);
            }
            em.remove(compradetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compradetalle> findCompradetalleEntities() {
        return findCompradetalleEntities(true, -1, -1);
    }

    public List<Compradetalle> findCompradetalleEntities(int maxResults, int firstResult) {
        return findCompradetalleEntities(false, maxResults, firstResult);
    }

    private List<Compradetalle> findCompradetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compradetalle.class));
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

    public Compradetalle findCompradetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compradetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompradetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compradetalle> rt = cq.from(Compradetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
