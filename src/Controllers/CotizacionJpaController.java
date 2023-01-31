/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Cliente;
import Models.Cotizacion;
import Models.Usuario;
import Models.Cotizaciondetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CotizacionJpaController implements Serializable {

    public CotizacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Cotizacion cotizacion) {
        if (cotizacion.getCotizaciondetalleList() == null) {
            cotizacion.setCotizaciondetalleList(new ArrayList<Cotizaciondetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteID = cotizacion.getClienteID();
            if (clienteID != null) {
                clienteID = em.getReference(clienteID.getClass(), clienteID.getClienteID());
                cotizacion.setClienteID(clienteID);
            }
            Usuario usuarioID = cotizacion.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                cotizacion.setUsuarioID(usuarioID);
            }
            List<Cotizaciondetalle> attachedCotizaciondetalleList = new ArrayList<Cotizaciondetalle>();
            for (Cotizaciondetalle cotizaciondetalleListCotizaciondetalleToAttach : cotizacion.getCotizaciondetalleList()) {
                cotizaciondetalleListCotizaciondetalleToAttach = em.getReference(cotizaciondetalleListCotizaciondetalleToAttach.getClass(), cotizaciondetalleListCotizaciondetalleToAttach.getCotizacionDetalleID());
                attachedCotizaciondetalleList.add(cotizaciondetalleListCotizaciondetalleToAttach);
            }
            cotizacion.setCotizaciondetalleList(attachedCotizaciondetalleList);
            em.persist(cotizacion);
            if (clienteID != null) {
                clienteID.getCotizacionList().add(cotizacion);
                clienteID = em.merge(clienteID);
            }
            if (usuarioID != null) {
                usuarioID.getCotizacionList().add(cotizacion);
                usuarioID = em.merge(usuarioID);
            }
            for (Cotizaciondetalle cotizaciondetalleListCotizaciondetalle : cotizacion.getCotizaciondetalleList()) {
                Cotizacion oldCotizacionIDOfCotizaciondetalleListCotizaciondetalle = cotizaciondetalleListCotizaciondetalle.getCotizacionID();
                cotizaciondetalleListCotizaciondetalle.setCotizacionID(cotizacion);
                cotizaciondetalleListCotizaciondetalle = em.merge(cotizaciondetalleListCotizaciondetalle);
                if (oldCotizacionIDOfCotizaciondetalleListCotizaciondetalle != null) {
                    oldCotizacionIDOfCotizaciondetalleListCotizaciondetalle.getCotizaciondetalleList().remove(cotizaciondetalleListCotizaciondetalle);
                    oldCotizacionIDOfCotizaciondetalleListCotizaciondetalle = em.merge(oldCotizacionIDOfCotizaciondetalleListCotizaciondetalle);
                }
            }
            em.flush();
            em.getTransaction().commit();
            return cotizacion.getCotizacionID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cotizacion cotizacion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cotizacion persistentCotizacion = em.find(Cotizacion.class, cotizacion.getCotizacionID());
            Cliente clienteIDOld = persistentCotizacion.getClienteID();
            Cliente clienteIDNew = cotizacion.getClienteID();
            Usuario usuarioIDOld = persistentCotizacion.getUsuarioID();
            Usuario usuarioIDNew = cotizacion.getUsuarioID();
            List<Cotizaciondetalle> cotizaciondetalleListOld = persistentCotizacion.getCotizaciondetalleList();
            List<Cotizaciondetalle> cotizaciondetalleListNew = cotizacion.getCotizaciondetalleList();
            List<String> illegalOrphanMessages = null;
            for (Cotizaciondetalle cotizaciondetalleListOldCotizaciondetalle : cotizaciondetalleListOld) {
                if (!cotizaciondetalleListNew.contains(cotizaciondetalleListOldCotizaciondetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cotizaciondetalle " + cotizaciondetalleListOldCotizaciondetalle + " since its cotizacionID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteIDNew != null) {
                clienteIDNew = em.getReference(clienteIDNew.getClass(), clienteIDNew.getClienteID());
                cotizacion.setClienteID(clienteIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                cotizacion.setUsuarioID(usuarioIDNew);
            }
            List<Cotizaciondetalle> attachedCotizaciondetalleListNew = new ArrayList<Cotizaciondetalle>();
            for (Cotizaciondetalle cotizaciondetalleListNewCotizaciondetalleToAttach : cotizaciondetalleListNew) {
                cotizaciondetalleListNewCotizaciondetalleToAttach = em.getReference(cotizaciondetalleListNewCotizaciondetalleToAttach.getClass(), cotizaciondetalleListNewCotizaciondetalleToAttach.getCotizacionDetalleID());
                attachedCotizaciondetalleListNew.add(cotizaciondetalleListNewCotizaciondetalleToAttach);
            }
            cotizaciondetalleListNew = attachedCotizaciondetalleListNew;
            cotizacion.setCotizaciondetalleList(cotizaciondetalleListNew);
            cotizacion = em.merge(cotizacion);
            if (clienteIDOld != null && !clienteIDOld.equals(clienteIDNew)) {
                clienteIDOld.getCotizacionList().remove(cotizacion);
                clienteIDOld = em.merge(clienteIDOld);
            }
            if (clienteIDNew != null && !clienteIDNew.equals(clienteIDOld)) {
                clienteIDNew.getCotizacionList().add(cotizacion);
                clienteIDNew = em.merge(clienteIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getCotizacionList().remove(cotizacion);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getCotizacionList().add(cotizacion);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            for (Cotizaciondetalle cotizaciondetalleListNewCotizaciondetalle : cotizaciondetalleListNew) {
                if (!cotizaciondetalleListOld.contains(cotizaciondetalleListNewCotizaciondetalle)) {
                    Cotizacion oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle = cotizaciondetalleListNewCotizaciondetalle.getCotizacionID();
                    cotizaciondetalleListNewCotizaciondetalle.setCotizacionID(cotizacion);
                    cotizaciondetalleListNewCotizaciondetalle = em.merge(cotizaciondetalleListNewCotizaciondetalle);
                    if (oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle != null && !oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle.equals(cotizacion)) {
                        oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle.getCotizaciondetalleList().remove(cotizaciondetalleListNewCotizaciondetalle);
                        oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle = em.merge(oldCotizacionIDOfCotizaciondetalleListNewCotizaciondetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cotizacion.getCotizacionID();
                if (findCotizacion(id) == null) {
                    throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.");
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
            Cotizacion cotizacion;
            try {
                cotizacion = em.getReference(Cotizacion.class, id);
                cotizacion.getCotizacionID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cotizacion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cotizaciondetalle> cotizaciondetalleListOrphanCheck = cotizacion.getCotizaciondetalleList();
            for (Cotizaciondetalle cotizaciondetalleListOrphanCheckCotizaciondetalle : cotizaciondetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cotizacion (" + cotizacion + ") cannot be destroyed since the Cotizaciondetalle " + cotizaciondetalleListOrphanCheckCotizaciondetalle + " in its cotizaciondetalleList field has a non-nullable cotizacionID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteID = cotizacion.getClienteID();
            if (clienteID != null) {
                clienteID.getCotizacionList().remove(cotizacion);
                clienteID = em.merge(clienteID);
            }
            Usuario usuarioID = cotizacion.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getCotizacionList().remove(cotizacion);
                usuarioID = em.merge(usuarioID);
            }
            em.remove(cotizacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cotizacion> findCotizacionEntities() {
        return findCotizacionEntities(true, -1, -1);
    }

    public List<Cotizacion> findCotizacionEntities(int maxResults, int firstResult) {
        return findCotizacionEntities(false, maxResults, firstResult);
    }

    private List<Cotizacion> findCotizacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cotizacion.class));
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

    public Cotizacion findCotizacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cotizacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCotizacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cotizacion> rt = cq.from(Cotizacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
