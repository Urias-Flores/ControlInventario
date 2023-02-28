package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Cotizacion;
import Models.Cotizaciondetalle;
import Models.Producto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CotizaciondetalleJpaController implements Serializable {

    public CotizaciondetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cotizaciondetalle cotizaciondetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizacion cotizacionID = cotizaciondetalle.getCotizacionID();
            if (cotizacionID != null) {
                cotizacionID = em.getReference(cotizacionID.getClass(), cotizacionID.getCotizacionID());
                cotizaciondetalle.setCotizacionID(cotizacionID);
            }
            Producto productoID = cotizaciondetalle.getProductoID();
            if (productoID != null) {
                productoID = em.getReference(productoID.getClass(), productoID.getProductoID());
                cotizaciondetalle.setProductoID(productoID);
            }
            em.persist(cotizaciondetalle);
            if (cotizacionID != null) {
                cotizacionID.getCotizaciondetalleList().add(cotizaciondetalle);
                cotizacionID = em.merge(cotizacionID);
            }
            if (productoID != null) {
                productoID.getCotizaciondetalleList().add(cotizaciondetalle);
                productoID = em.merge(productoID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizaciondetalle cotizaciondetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizaciondetalle persistentCotizaciondetalle = em.find(Cotizaciondetalle.class, cotizaciondetalle.getCotizacionDetalleID());
            Cotizacion cotizacionIDOld = persistentCotizaciondetalle.getCotizacionID();
            Cotizacion cotizacionIDNew = cotizaciondetalle.getCotizacionID();
            Producto productoIDOld = persistentCotizaciondetalle.getProductoID();
            Producto productoIDNew = cotizaciondetalle.getProductoID();
            if (cotizacionIDNew != null) {
                cotizacionIDNew = em.getReference(cotizacionIDNew.getClass(), cotizacionIDNew.getCotizacionID());
                cotizaciondetalle.setCotizacionID(cotizacionIDNew);
            }
            if (productoIDNew != null) {
                productoIDNew = em.getReference(productoIDNew.getClass(), productoIDNew.getProductoID());
                cotizaciondetalle.setProductoID(productoIDNew);
            }
            cotizaciondetalle = em.merge(cotizaciondetalle);
            if (cotizacionIDOld != null && !cotizacionIDOld.equals(cotizacionIDNew)) {
                cotizacionIDOld.getCotizaciondetalleList().remove(cotizaciondetalle);
                cotizacionIDOld = em.merge(cotizacionIDOld);
            }
            if (cotizacionIDNew != null && !cotizacionIDNew.equals(cotizacionIDOld)) {
                cotizacionIDNew.getCotizaciondetalleList().add(cotizaciondetalle);
                cotizacionIDNew = em.merge(cotizacionIDNew);
            }
            if (productoIDOld != null && !productoIDOld.equals(productoIDNew)) {
                productoIDOld.getCotizaciondetalleList().remove(cotizaciondetalle);
                productoIDOld = em.merge(productoIDOld);
            }
            if (productoIDNew != null && !productoIDNew.equals(productoIDOld)) {
                productoIDNew.getCotizaciondetalleList().add(cotizaciondetalle);
                productoIDNew = em.merge(productoIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cotizaciondetalle.getCotizacionDetalleID();
                if (findCotizaciondetalle(id) == null) {
                    throw new NonexistentEntityException("The cotizaciondetalle with id " + id + " no longer exists.");
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
            Cotizaciondetalle cotizaciondetalle;
            try {
                cotizaciondetalle = em.getReference(Cotizaciondetalle.class, id);
                cotizaciondetalle.getCotizacionDetalleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizaciondetalle with id " + id + " no longer exists.", enfe);
            }
            Cotizacion cotizacionID = cotizaciondetalle.getCotizacionID();
            if (cotizacionID != null) {
                cotizacionID.getCotizaciondetalleList().remove(cotizaciondetalle);
                cotizacionID = em.merge(cotizacionID);
            }
            Producto productoID = cotizaciondetalle.getProductoID();
            if (productoID != null) {
                productoID.getCotizaciondetalleList().remove(cotizaciondetalle);
                productoID = em.merge(productoID);
            }
            em.remove(cotizaciondetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotizaciondetalle> findCotizaciondetalleEntities() {
        return findCotizaciondetalleEntities(true, -1, -1);
    }

    public List<Cotizaciondetalle> findCotizaciondetalleEntities(int maxResults, int firstResult) {
        return findCotizaciondetalleEntities(false, maxResults, firstResult);
    }

    private List<Cotizaciondetalle> findCotizaciondetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizaciondetalle.class));
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

    public Cotizaciondetalle findCotizaciondetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizaciondetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizaciondetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizaciondetalle> rt = cq.from(Cotizaciondetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
