package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Producto;
import Models.Venta;
import Models.Ventadetalle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class VentadetalleJpaController implements Serializable {

    public VentadetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ventadetalle ventadetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoID = ventadetalle.getProductoID();
            if (productoID != null) {
                productoID = em.getReference(productoID.getClass(), productoID.getProductoID());
                ventadetalle.setProductoID(productoID);
            }
            Venta ventaID = ventadetalle.getVentaID();
            if (ventaID != null) {
                ventaID = em.getReference(ventaID.getClass(), ventaID.getVentaID());
                ventadetalle.setVentaID(ventaID);
            }
            em.persist(ventadetalle);
            if (productoID != null) {
                productoID.getVentadetalleList().add(ventadetalle);
                productoID = em.merge(productoID);
            }
            if (ventaID != null) {
                ventaID.getVentadetalleList().add(ventadetalle);
                ventaID = em.merge(ventaID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ventadetalle ventadetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ventadetalle persistentVentadetalle = em.find(Ventadetalle.class, ventadetalle.getVentaDetalleID());
            Producto productoIDOld = persistentVentadetalle.getProductoID();
            Producto productoIDNew = ventadetalle.getProductoID();
            Venta ventaIDOld = persistentVentadetalle.getVentaID();
            Venta ventaIDNew = ventadetalle.getVentaID();
            if (productoIDNew != null) {
                productoIDNew = em.getReference(productoIDNew.getClass(), productoIDNew.getProductoID());
                ventadetalle.setProductoID(productoIDNew);
            }
            if (ventaIDNew != null) {
                ventaIDNew = em.getReference(ventaIDNew.getClass(), ventaIDNew.getVentaID());
                ventadetalle.setVentaID(ventaIDNew);
            }
            ventadetalle = em.merge(ventadetalle);
            if (productoIDOld != null && !productoIDOld.equals(productoIDNew)) {
                productoIDOld.getVentadetalleList().remove(ventadetalle);
                productoIDOld = em.merge(productoIDOld);
            }
            if (productoIDNew != null && !productoIDNew.equals(productoIDOld)) {
                productoIDNew.getVentadetalleList().add(ventadetalle);
                productoIDNew = em.merge(productoIDNew);
            }
            if (ventaIDOld != null && !ventaIDOld.equals(ventaIDNew)) {
                ventaIDOld.getVentadetalleList().remove(ventadetalle);
                ventaIDOld = em.merge(ventaIDOld);
            }
            if (ventaIDNew != null && !ventaIDNew.equals(ventaIDOld)) {
                ventaIDNew.getVentadetalleList().add(ventadetalle);
                ventaIDNew = em.merge(ventaIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ventadetalle.getVentaDetalleID();
                if (findVentadetalle(id) == null) {
                    throw new NonexistentEntityException("The ventadetalle with id " + id + " no longer exists.");
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
            Ventadetalle ventadetalle;
            try {
                ventadetalle = em.getReference(Ventadetalle.class, id);
                ventadetalle.getVentaDetalleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ventadetalle with id " + id + " no longer exists.", enfe);
            }
            Producto productoID = ventadetalle.getProductoID();
            if (productoID != null) {
                productoID.getVentadetalleList().remove(ventadetalle);
                productoID = em.merge(productoID);
            }
            Venta ventaID = ventadetalle.getVentaID();
            if (ventaID != null) {
                ventaID.getVentadetalleList().remove(ventadetalle);
                ventaID = em.merge(ventaID);
            }
            em.remove(ventadetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ventadetalle> findVentadetalleEntities() {
        return findVentadetalleEntities(true, -1, -1);
    }

    public List<Ventadetalle> findVentadetalleEntities(int maxResults, int firstResult) {
        return findVentadetalleEntities(false, maxResults, firstResult);
    }

    private List<Ventadetalle> findVentadetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ventadetalle.class));
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

    public Ventadetalle findVentadetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ventadetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentadetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ventadetalle> rt = cq.from(Ventadetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
