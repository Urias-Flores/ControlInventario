package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Producto;
import Models.Solicitud;
import Models.Solicituddetalle;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SolicituddetalleJpaController implements Serializable {

    public SolicituddetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicituddetalle solicituddetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoID = solicituddetalle.getProductoID();
            if (productoID != null) {
                productoID = em.getReference(productoID.getClass(), productoID.getProductoID());
                solicituddetalle.setProductoID(productoID);
            }
            Solicitud solicitudID = solicituddetalle.getSolicitudID();
            if (solicitudID != null) {
                solicitudID = em.getReference(solicitudID.getClass(), solicitudID.getSolicitudID());
                solicituddetalle.setSolicitudID(solicitudID);
            }
            em.persist(solicituddetalle);
            if (productoID != null) {
                productoID.getSolicituddetalleList().add(solicituddetalle);
                productoID = em.merge(productoID);
            }
            if (solicitudID != null) {
                solicitudID.getSolicituddetalleList().add(solicituddetalle);
                solicitudID = em.merge(solicitudID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicituddetalle solicituddetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicituddetalle persistentSolicituddetalle = em.find(Solicituddetalle.class, solicituddetalle.getSolicitudDetalleID());
            Producto productoIDOld = persistentSolicituddetalle.getProductoID();
            Producto productoIDNew = solicituddetalle.getProductoID();
            Solicitud solicitudIDOld = persistentSolicituddetalle.getSolicitudID();
            Solicitud solicitudIDNew = solicituddetalle.getSolicitudID();
            if (productoIDNew != null) {
                productoIDNew = em.getReference(productoIDNew.getClass(), productoIDNew.getProductoID());
                solicituddetalle.setProductoID(productoIDNew);
            }
            if (solicitudIDNew != null) {
                solicitudIDNew = em.getReference(solicitudIDNew.getClass(), solicitudIDNew.getSolicitudID());
                solicituddetalle.setSolicitudID(solicitudIDNew);
            }
            solicituddetalle = em.merge(solicituddetalle);
            if (productoIDOld != null && !productoIDOld.equals(productoIDNew)) {
                productoIDOld.getSolicituddetalleList().remove(solicituddetalle);
                productoIDOld = em.merge(productoIDOld);
            }
            if (productoIDNew != null && !productoIDNew.equals(productoIDOld)) {
                productoIDNew.getSolicituddetalleList().add(solicituddetalle);
                productoIDNew = em.merge(productoIDNew);
            }
            if (solicitudIDOld != null && !solicitudIDOld.equals(solicitudIDNew)) {
                solicitudIDOld.getSolicituddetalleList().remove(solicituddetalle);
                solicitudIDOld = em.merge(solicitudIDOld);
            }
            if (solicitudIDNew != null && !solicitudIDNew.equals(solicitudIDOld)) {
                solicitudIDNew.getSolicituddetalleList().add(solicituddetalle);
                solicitudIDNew = em.merge(solicitudIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicituddetalle.getSolicitudDetalleID();
                if (findSolicituddetalle(id) == null) {
                    throw new NonexistentEntityException("The solicituddetalle with id " + id + " no longer exists.");
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
            Solicituddetalle solicituddetalle;
            try {
                solicituddetalle = em.getReference(Solicituddetalle.class, id);
                solicituddetalle.getSolicitudDetalleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicituddetalle with id " + id + " no longer exists.", enfe);
            }
            Producto productoID = solicituddetalle.getProductoID();
            if (productoID != null) {
                productoID.getSolicituddetalleList().remove(solicituddetalle);
                productoID = em.merge(productoID);
            }
            Solicitud solicitudID = solicituddetalle.getSolicitudID();
            if (solicitudID != null) {
                solicitudID.getSolicituddetalleList().remove(solicituddetalle);
                solicitudID = em.merge(solicitudID);
            }
            em.remove(solicituddetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicituddetalle> findSolicituddetalleEntities() {
        return findSolicituddetalleEntities(true, -1, -1);
    }

    public List<Solicituddetalle> findSolicituddetalleEntities(int maxResults, int firstResult) {
        return findSolicituddetalleEntities(false, maxResults, firstResult);
    }

    private List<Solicituddetalle> findSolicituddetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicituddetalle.class));
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

    public Solicituddetalle findSolicituddetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicituddetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicituddetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicituddetalle> rt = cq.from(Solicituddetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
