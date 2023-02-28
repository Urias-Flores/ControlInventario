package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Cliente;
import Models.Usuario;
import Models.Solicituddetalle;
import java.util.ArrayList;
import java.util.List;
import Models.Arqueodetalle;
import Models.Solicitud;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Solicitud solicitud) {
        if (solicitud.getSolicituddetalleList() == null) {
            solicitud.setSolicituddetalleList(new ArrayList<>());
        }
        if (solicitud.getArqueodetalleList() == null) {
            solicitud.setArqueodetalleList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteID = solicitud.getClienteID();
            if (clienteID != null) {
                clienteID = em.getReference(clienteID.getClass(), clienteID.getClienteID());
                solicitud.setClienteID(clienteID);
            }
            Usuario usuarioID = solicitud.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                solicitud.setUsuarioID(usuarioID);
            }
            List<Solicituddetalle> attachedSolicituddetalleList = new ArrayList<>();
            for (Solicituddetalle solicituddetalleListSolicituddetalleToAttach : solicitud.getSolicituddetalleList()) {
                solicituddetalleListSolicituddetalleToAttach = em.getReference(solicituddetalleListSolicituddetalleToAttach.getClass(), solicituddetalleListSolicituddetalleToAttach.getSolicitudDetalleID());
                attachedSolicituddetalleList.add(solicituddetalleListSolicituddetalleToAttach);
            }
            solicitud.setSolicituddetalleList(attachedSolicituddetalleList);
            List<Arqueodetalle> attachedArqueodetalleList = new ArrayList<>();
            for (Arqueodetalle arqueodetalleListArqueodetalleToAttach : solicitud.getArqueodetalleList()) {
                arqueodetalleListArqueodetalleToAttach = em.getReference(arqueodetalleListArqueodetalleToAttach.getClass(), arqueodetalleListArqueodetalleToAttach.getArqueoDetalleID());
                attachedArqueodetalleList.add(arqueodetalleListArqueodetalleToAttach);
            }
            solicitud.setArqueodetalleList(attachedArqueodetalleList);
            em.persist(solicitud);
            if (clienteID != null) {
                clienteID.getSolicitudList().add(solicitud);
                clienteID = em.merge(clienteID);
            }
            if (usuarioID != null) {
                usuarioID.getSolicitudList().add(solicitud);
                usuarioID = em.merge(usuarioID);
            }
            for (Solicituddetalle solicituddetalleListSolicituddetalle : solicitud.getSolicituddetalleList()) {
                Solicitud oldSolicitudIDOfSolicituddetalleListSolicituddetalle = solicituddetalleListSolicituddetalle.getSolicitudID();
                solicituddetalleListSolicituddetalle.setSolicitudID(solicitud);
                solicituddetalleListSolicituddetalle = em.merge(solicituddetalleListSolicituddetalle);
                if (oldSolicitudIDOfSolicituddetalleListSolicituddetalle != null) {
                    oldSolicitudIDOfSolicituddetalleListSolicituddetalle.getSolicituddetalleList().remove(solicituddetalleListSolicituddetalle);
                    oldSolicitudIDOfSolicituddetalleListSolicituddetalle = em.merge(oldSolicitudIDOfSolicituddetalleListSolicituddetalle);
                }
            }
            for (Arqueodetalle arqueodetalleListArqueodetalle : solicitud.getArqueodetalleList()) {
                Solicitud oldSolicitudIDOfArqueodetalleListArqueodetalle = arqueodetalleListArqueodetalle.getSolicitudID();
                arqueodetalleListArqueodetalle.setSolicitudID(solicitud);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
                if (oldSolicitudIDOfArqueodetalleListArqueodetalle != null) {
                    oldSolicitudIDOfArqueodetalleListArqueodetalle.getArqueodetalleList().remove(arqueodetalleListArqueodetalle);
                    oldSolicitudIDOfArqueodetalleListArqueodetalle = em.merge(oldSolicitudIDOfArqueodetalleListArqueodetalle);
                }
            }
            em.flush();
            em.getTransaction().commit();
            return solicitud.getSolicitudID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getSolicitudID());
            Cliente clienteIDOld = persistentSolicitud.getClienteID();
            Cliente clienteIDNew = solicitud.getClienteID();
            Usuario usuarioIDOld = persistentSolicitud.getUsuarioID();
            Usuario usuarioIDNew = solicitud.getUsuarioID();
            List<Solicituddetalle> solicituddetalleListOld = persistentSolicitud.getSolicituddetalleList();
            List<Solicituddetalle> solicituddetalleListNew = solicitud.getSolicituddetalleList();
            List<Arqueodetalle> arqueodetalleListOld = persistentSolicitud.getArqueodetalleList();
            List<Arqueodetalle> arqueodetalleListNew = solicitud.getArqueodetalleList();
            List<String> illegalOrphanMessages = null;
            if(solicituddetalleListOld != null){
                for (Solicituddetalle solicituddetalleListOldSolicituddetalle : solicituddetalleListOld) {
                    if (!solicituddetalleListNew.contains(solicituddetalleListOldSolicituddetalle)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<>();
                        }
                        illegalOrphanMessages.add("You must retain Solicituddetalle " + solicituddetalleListOldSolicituddetalle + " since its solicitudID field is not nullable.");
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteIDNew != null) {
                clienteIDNew = em.getReference(clienteIDNew.getClass(), clienteIDNew.getClienteID());
                solicitud.setClienteID(clienteIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                solicitud.setUsuarioID(usuarioIDNew);
            }
            List<Solicituddetalle> attachedSolicituddetalleListNew = new ArrayList<>();
            if(solicituddetalleListNew != null){
                for (Solicituddetalle solicituddetalleListNewSolicituddetalleToAttach : solicituddetalleListNew) {
                    solicituddetalleListNewSolicituddetalleToAttach = em.getReference(solicituddetalleListNewSolicituddetalleToAttach.getClass(), solicituddetalleListNewSolicituddetalleToAttach.getSolicitudDetalleID());
                    attachedSolicituddetalleListNew.add(solicituddetalleListNewSolicituddetalleToAttach);
                }
            }
            solicituddetalleListNew = attachedSolicituddetalleListNew;
            solicitud.setSolicituddetalleList(solicituddetalleListNew);
            List<Arqueodetalle> attachedArqueodetalleListNew = new ArrayList<>();
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalleToAttach : arqueodetalleListNew) {
                    arqueodetalleListNewArqueodetalleToAttach = em.getReference(arqueodetalleListNewArqueodetalleToAttach.getClass(), arqueodetalleListNewArqueodetalleToAttach.getArqueoDetalleID());
                    attachedArqueodetalleListNew.add(arqueodetalleListNewArqueodetalleToAttach);
                }
            }
            arqueodetalleListNew = attachedArqueodetalleListNew;
            solicitud.setArqueodetalleList(arqueodetalleListNew);
            solicitud = em.merge(solicitud);
            if (clienteIDOld != null && !clienteIDOld.equals(clienteIDNew)) {
                clienteIDOld.getSolicitudList().remove(solicitud);
                clienteIDOld = em.merge(clienteIDOld);
            }
            if (clienteIDNew != null && !clienteIDNew.equals(clienteIDOld)) {
                clienteIDNew.getSolicitudList().add(solicitud);
                clienteIDNew = em.merge(clienteIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getSolicitudList().remove(solicitud);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getSolicitudList().add(solicitud);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            if(solicituddetalleListNew != null){
                for (Solicituddetalle solicituddetalleListNewSolicituddetalle : solicituddetalleListNew) {
                    if (!solicituddetalleListOld.contains(solicituddetalleListNewSolicituddetalle)) {
                        Solicitud oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle = solicituddetalleListNewSolicituddetalle.getSolicitudID();
                        solicituddetalleListNewSolicituddetalle.setSolicitudID(solicitud);
                        solicituddetalleListNewSolicituddetalle = em.merge(solicituddetalleListNewSolicituddetalle);
                        if (oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle != null && !oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle.equals(solicitud)) {
                            oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle.getSolicituddetalleList().remove(solicituddetalleListNewSolicituddetalle);
                            oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle = em.merge(oldSolicitudIDOfSolicituddetalleListNewSolicituddetalle);
                        }
                    }
                }
            }
            if(arqueodetalleListOld != null){
                for (Arqueodetalle arqueodetalleListOldArqueodetalle : arqueodetalleListOld) {
                    if (!arqueodetalleListNew.contains(arqueodetalleListOldArqueodetalle)) {
                        arqueodetalleListOldArqueodetalle.setSolicitudID(null);
                        arqueodetalleListOldArqueodetalle = em.merge(arqueodetalleListOldArqueodetalle);
                    }
                }
            }
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalle : arqueodetalleListNew) {
                    if (!arqueodetalleListOld.contains(arqueodetalleListNewArqueodetalle)) {
                        Solicitud oldSolicitudIDOfArqueodetalleListNewArqueodetalle = arqueodetalleListNewArqueodetalle.getSolicitudID();
                        arqueodetalleListNewArqueodetalle.setSolicitudID(solicitud);
                        arqueodetalleListNewArqueodetalle = em.merge(arqueodetalleListNewArqueodetalle);
                        if (oldSolicitudIDOfArqueodetalleListNewArqueodetalle != null && !oldSolicitudIDOfArqueodetalleListNewArqueodetalle.equals(solicitud)) {
                            oldSolicitudIDOfArqueodetalleListNewArqueodetalle.getArqueodetalleList().remove(arqueodetalleListNewArqueodetalle);
                            oldSolicitudIDOfArqueodetalleListNewArqueodetalle = em.merge(oldSolicitudIDOfArqueodetalleListNewArqueodetalle);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getSolicitudID();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getSolicitudID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicituddetalle> solicituddetalleListOrphanCheck = solicitud.getSolicituddetalleList();
            for (Solicituddetalle solicituddetalleListOrphanCheckSolicituddetalle : solicituddetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Solicitud (" + solicitud + ") cannot be destroyed since the Solicituddetalle " + solicituddetalleListOrphanCheckSolicituddetalle + " in its solicituddetalleList field has a non-nullable solicitudID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteID = solicitud.getClienteID();
            if (clienteID != null) {
                clienteID.getSolicitudList().remove(solicitud);
                clienteID = em.merge(clienteID);
            }
            Usuario usuarioID = solicitud.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getSolicitudList().remove(solicitud);
                usuarioID = em.merge(usuarioID);
            }
            List<Arqueodetalle> arqueodetalleList = solicitud.getArqueodetalleList();
            for (Arqueodetalle arqueodetalleListArqueodetalle : arqueodetalleList) {
                arqueodetalleListArqueodetalle.setSolicitudID(null);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
