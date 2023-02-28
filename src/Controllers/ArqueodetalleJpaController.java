package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Arqueo;
import Models.Arqueodetalle;
import Models.Gasto;
import Models.Solicitud;
import Models.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ArqueodetalleJpaController implements Serializable {

    public ArqueodetalleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Arqueodetalle arqueodetalle) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Arqueo arqueoID = arqueodetalle.getArqueoID();
            if (arqueoID != null) {
                arqueoID = em.getReference(arqueoID.getClass(), arqueoID.getArqueoID());
                arqueodetalle.setArqueoID(arqueoID);
            }
            Gasto gastoID = arqueodetalle.getGastoID();
            if (gastoID != null) {
                gastoID = em.getReference(gastoID.getClass(), gastoID.getGastoID());
                arqueodetalle.setGastoID(gastoID);
            }
            Solicitud solicitudID = arqueodetalle.getSolicitudID();
            if (solicitudID != null) {
                solicitudID = em.getReference(solicitudID.getClass(), solicitudID.getSolicitudID());
                arqueodetalle.setSolicitudID(solicitudID);
            }
            Venta facturaID = arqueodetalle.getFacturaID();
            if (facturaID != null) {
                facturaID = em.getReference(facturaID.getClass(), facturaID.getVentaID());
                arqueodetalle.setFacturaID(facturaID);
            }
            em.persist(arqueodetalle);
            if (arqueoID != null) {
                arqueoID.getArqueodetalleList().add(arqueodetalle);
                arqueoID = em.merge(arqueoID);
            }
            if (gastoID != null) {
                gastoID.getArqueodetalleList().add(arqueodetalle);
                gastoID = em.merge(gastoID);
            }
            if (solicitudID != null) {
                solicitudID.getArqueodetalleList().add(arqueodetalle);
                solicitudID = em.merge(solicitudID);
            }
            if (facturaID != null) {
                facturaID.getArqueodetalleList().add(arqueodetalle);
                facturaID = em.merge(facturaID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arqueodetalle arqueodetalle) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Arqueodetalle persistentArqueodetalle = em.find(Arqueodetalle.class, arqueodetalle.getArqueoDetalleID());
            Arqueo arqueoIDOld = persistentArqueodetalle.getArqueoID();
            Arqueo arqueoIDNew = arqueodetalle.getArqueoID();
            Gasto gastoIDOld = persistentArqueodetalle.getGastoID();
            Gasto gastoIDNew = arqueodetalle.getGastoID();
            Solicitud solicitudIDOld = persistentArqueodetalle.getSolicitudID();
            Solicitud solicitudIDNew = arqueodetalle.getSolicitudID();
            Venta facturaIDOld = persistentArqueodetalle.getFacturaID();
            Venta facturaIDNew = arqueodetalle.getFacturaID();
            if (arqueoIDNew != null) {
                arqueoIDNew = em.getReference(arqueoIDNew.getClass(), arqueoIDNew.getArqueoID());
                arqueodetalle.setArqueoID(arqueoIDNew);
            }
            if (gastoIDNew != null) {
                gastoIDNew = em.getReference(gastoIDNew.getClass(), gastoIDNew.getGastoID());
                arqueodetalle.setGastoID(gastoIDNew);
            }
            if (solicitudIDNew != null) {
                solicitudIDNew = em.getReference(solicitudIDNew.getClass(), solicitudIDNew.getSolicitudID());
                arqueodetalle.setSolicitudID(solicitudIDNew);
            }
            if (facturaIDNew != null) {
                facturaIDNew = em.getReference(facturaIDNew.getClass(), facturaIDNew.getVentaID());
                arqueodetalle.setFacturaID(facturaIDNew);
            }
            arqueodetalle = em.merge(arqueodetalle);
            if (arqueoIDOld != null && !arqueoIDOld.equals(arqueoIDNew)) {
                arqueoIDOld.getArqueodetalleList().remove(arqueodetalle);
                arqueoIDOld = em.merge(arqueoIDOld);
            }
            if (arqueoIDNew != null && !arqueoIDNew.equals(arqueoIDOld)) {
                arqueoIDNew.getArqueodetalleList().add(arqueodetalle);
                arqueoIDNew = em.merge(arqueoIDNew);
            }
            if (gastoIDOld != null && !gastoIDOld.equals(gastoIDNew)) {
                gastoIDOld.getArqueodetalleList().remove(arqueodetalle);
                gastoIDOld = em.merge(gastoIDOld);
            }
            if (gastoIDNew != null && !gastoIDNew.equals(gastoIDOld)) {
                gastoIDNew.getArqueodetalleList().add(arqueodetalle);
                gastoIDNew = em.merge(gastoIDNew);
            }
            if (solicitudIDOld != null && !solicitudIDOld.equals(solicitudIDNew)) {
                solicitudIDOld.getArqueodetalleList().remove(arqueodetalle);
                solicitudIDOld = em.merge(solicitudIDOld);
            }
            if (solicitudIDNew != null && !solicitudIDNew.equals(solicitudIDOld)) {
                solicitudIDNew.getArqueodetalleList().add(arqueodetalle);
                solicitudIDNew = em.merge(solicitudIDNew);
            }
            if (facturaIDOld != null && !facturaIDOld.equals(facturaIDNew)) {
                facturaIDOld.getArqueodetalleList().remove(arqueodetalle);
                facturaIDOld = em.merge(facturaIDOld);
            }
            if (facturaIDNew != null && !facturaIDNew.equals(facturaIDOld)) {
                facturaIDNew.getArqueodetalleList().add(arqueodetalle);
                facturaIDNew = em.merge(facturaIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = arqueodetalle.getArqueoDetalleID();
                if (findArqueodetalle(id) == null) {
                    throw new NonexistentEntityException("The arqueodetalle with id " + id + " no longer exists.");
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
            Arqueodetalle arqueodetalle;
            try {
                arqueodetalle = em.getReference(Arqueodetalle.class, id);
                arqueodetalle.getArqueoDetalleID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arqueodetalle with id " + id + " no longer exists.", enfe);
            }
            Arqueo arqueoID = arqueodetalle.getArqueoID();
            if (arqueoID != null) {
                arqueoID.getArqueodetalleList().remove(arqueodetalle);
                arqueoID = em.merge(arqueoID);
            }
            Gasto gastoID = arqueodetalle.getGastoID();
            if (gastoID != null) {
                gastoID.getArqueodetalleList().remove(arqueodetalle);
                gastoID = em.merge(gastoID);
            }
            Solicitud solicitudID = arqueodetalle.getSolicitudID();
            if (solicitudID != null) {
                solicitudID.getArqueodetalleList().remove(arqueodetalle);
                solicitudID = em.merge(solicitudID);
            }
            Venta facturaID = arqueodetalle.getFacturaID();
            if (facturaID != null) {
                facturaID.getArqueodetalleList().remove(arqueodetalle);
                facturaID = em.merge(facturaID);
            }
            em.remove(arqueodetalle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arqueodetalle> findArqueodetalleEntities() {
        return findArqueodetalleEntities(true, -1, -1);
    }

    public List<Arqueodetalle> findArqueodetalleEntities(int maxResults, int firstResult) {
        return findArqueodetalleEntities(false, maxResults, firstResult);
    }

    private List<Arqueodetalle> findArqueodetalleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arqueodetalle.class));
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

    public Arqueodetalle findArqueodetalle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arqueodetalle.class, id);
        } finally {
            em.close();
        }
    }

    public int getArqueodetalleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arqueodetalle> rt = cq.from(Arqueodetalle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
