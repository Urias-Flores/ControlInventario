package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Arqueo;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Usuario;
import Models.Arqueodetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ArqueoJpaController implements Serializable {

    public ArqueoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Arqueo arqueo) {
        if (arqueo.getArqueodetalleList() == null) {
            arqueo.setArqueodetalleList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioID = arqueo.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                arqueo.setUsuarioID(usuarioID);
            }
            List<Arqueodetalle> attachedArqueodetalleList = new ArrayList<>();
            for (Arqueodetalle arqueodetalleListArqueodetalleToAttach : arqueo.getArqueodetalleList()) {
                arqueodetalleListArqueodetalleToAttach = em.getReference(arqueodetalleListArqueodetalleToAttach.getClass(), arqueodetalleListArqueodetalleToAttach.getArqueoDetalleID());
                attachedArqueodetalleList.add(arqueodetalleListArqueodetalleToAttach);
            }
            arqueo.setArqueodetalleList(attachedArqueodetalleList);
            em.persist(arqueo);
            if (usuarioID != null) {
                usuarioID.getArqueoList().add(arqueo);
                usuarioID = em.merge(usuarioID);
            }
            for (Arqueodetalle arqueodetalleListArqueodetalle : arqueo.getArqueodetalleList()) {
                Arqueo oldArqueoIDOfArqueodetalleListArqueodetalle = arqueodetalleListArqueodetalle.getArqueoID();
                arqueodetalleListArqueodetalle.setArqueoID(arqueo);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
                if (oldArqueoIDOfArqueodetalleListArqueodetalle != null) {
                    oldArqueoIDOfArqueodetalleListArqueodetalle.getArqueodetalleList().remove(arqueodetalleListArqueodetalle);
                    oldArqueoIDOfArqueodetalleListArqueodetalle = em.merge(oldArqueoIDOfArqueodetalleListArqueodetalle);
                }
            }
            em.flush();
            em.getTransaction().commit();
            return arqueo.getArqueoID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Arqueo arqueo) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Arqueo persistentArqueo = em.find(Arqueo.class, arqueo.getArqueoID());
            Usuario usuarioIDOld = persistentArqueo.getUsuarioID();
            Usuario usuarioIDNew = arqueo.getUsuarioID();
            List<Arqueodetalle> arqueodetalleListOld = persistentArqueo.getArqueodetalleList();
            List<Arqueodetalle> arqueodetalleListNew = arqueo.getArqueodetalleList();
            List<String> illegalOrphanMessages = null;
            if(arqueodetalleListOld != null){
                for (Arqueodetalle arqueodetalleListOldArqueodetalle : arqueodetalleListOld) {
                    if (!arqueodetalleListNew.contains(arqueodetalleListOldArqueodetalle)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<>();
                        }
                        illegalOrphanMessages.add("You must retain Arqueodetalle " + arqueodetalleListOldArqueodetalle + " since its arqueoID field is not nullable.");
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                arqueo.setUsuarioID(usuarioIDNew);
            }
            List<Arqueodetalle> attachedArqueodetalleListNew = new ArrayList<>();
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalleToAttach : arqueodetalleListNew) {
                    arqueodetalleListNewArqueodetalleToAttach = em.getReference(arqueodetalleListNewArqueodetalleToAttach.getClass(), arqueodetalleListNewArqueodetalleToAttach.getArqueoDetalleID());
                    attachedArqueodetalleListNew.add(arqueodetalleListNewArqueodetalleToAttach);
                }
            }
            arqueodetalleListNew = attachedArqueodetalleListNew;
            arqueo.setArqueodetalleList(arqueodetalleListNew);
            arqueo = em.merge(arqueo);
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getArqueoList().remove(arqueo);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getArqueoList().add(arqueo);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalle : arqueodetalleListNew) {
                    if (!arqueodetalleListOld.contains(arqueodetalleListNewArqueodetalle)) {
                        Arqueo oldArqueoIDOfArqueodetalleListNewArqueodetalle = arqueodetalleListNewArqueodetalle.getArqueoID();
                        arqueodetalleListNewArqueodetalle.setArqueoID(arqueo);
                        arqueodetalleListNewArqueodetalle = em.merge(arqueodetalleListNewArqueodetalle);
                        if (oldArqueoIDOfArqueodetalleListNewArqueodetalle != null && !oldArqueoIDOfArqueodetalleListNewArqueodetalle.equals(arqueo)) {
                            oldArqueoIDOfArqueodetalleListNewArqueodetalle.getArqueodetalleList().remove(arqueodetalleListNewArqueodetalle);
                            oldArqueoIDOfArqueodetalleListNewArqueodetalle = em.merge(oldArqueoIDOfArqueodetalleListNewArqueodetalle);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = arqueo.getArqueoID();
                if (findArqueo(id) == null) {
                    throw new NonexistentEntityException("The arqueo with id " + id + " no longer exists.");
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
            Arqueo arqueo;
            try {
                arqueo = em.getReference(Arqueo.class, id);
                arqueo.getArqueoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The arqueo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Arqueodetalle> arqueodetalleListOrphanCheck = arqueo.getArqueodetalleList();
            for (Arqueodetalle arqueodetalleListOrphanCheckArqueodetalle : arqueodetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Arqueo (" + arqueo + ") cannot be destroyed since the Arqueodetalle " + arqueodetalleListOrphanCheckArqueodetalle + " in its arqueodetalleList field has a non-nullable arqueoID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario usuarioID = arqueo.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getArqueoList().remove(arqueo);
                usuarioID = em.merge(usuarioID);
            }
            em.remove(arqueo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Arqueo> findArqueoEntities() {
        return findArqueoEntities(true, -1, -1);
    }

    public List<Arqueo> findArqueoEntities(int maxResults, int firstResult) {
        return findArqueoEntities(false, maxResults, firstResult);
    }

    private List<Arqueo> findArqueoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Arqueo.class));
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

    public Arqueo findArqueo(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Arqueo.class, id);
        } finally {
            em.close();
        }
    }

    public int getArqueoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Arqueo> rt = cq.from(Arqueo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
