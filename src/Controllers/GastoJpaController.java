/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Usuario;
import Models.Arqueodetalle;
import Models.Gasto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
public class GastoJpaController implements Serializable {

    public GastoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Gasto gasto) {
        if (gasto.getArqueodetalleList() == null) {
            gasto.setArqueodetalleList(new ArrayList<Arqueodetalle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuarioID = gasto.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                gasto.setUsuarioID(usuarioID);
            }
            List<Arqueodetalle> attachedArqueodetalleList = new ArrayList<Arqueodetalle>();
            for (Arqueodetalle arqueodetalleListArqueodetalleToAttach : gasto.getArqueodetalleList()) {
                arqueodetalleListArqueodetalleToAttach = em.getReference(arqueodetalleListArqueodetalleToAttach.getClass(), arqueodetalleListArqueodetalleToAttach.getArqueoDetalleID());
                attachedArqueodetalleList.add(arqueodetalleListArqueodetalleToAttach);
            }
            gasto.setArqueodetalleList(attachedArqueodetalleList);
            em.persist(gasto);
            if (usuarioID != null) {
                usuarioID.getGastoList().add(gasto);
                usuarioID = em.merge(usuarioID);
            }
            for (Arqueodetalle arqueodetalleListArqueodetalle : gasto.getArqueodetalleList()) {
                Gasto oldGastoIDOfArqueodetalleListArqueodetalle = arqueodetalleListArqueodetalle.getGastoID();
                arqueodetalleListArqueodetalle.setGastoID(gasto);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
                if (oldGastoIDOfArqueodetalleListArqueodetalle != null) {
                    oldGastoIDOfArqueodetalleListArqueodetalle.getArqueodetalleList().remove(arqueodetalleListArqueodetalle);
                    oldGastoIDOfArqueodetalleListArqueodetalle = em.merge(oldGastoIDOfArqueodetalleListArqueodetalle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Gasto gasto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Gasto persistentGasto = em.find(Gasto.class, gasto.getGastoID());
            Usuario usuarioIDOld = persistentGasto.getUsuarioID();
            Usuario usuarioIDNew = gasto.getUsuarioID();
            List<Arqueodetalle> arqueodetalleListOld = persistentGasto.getArqueodetalleList();
            List<Arqueodetalle> arqueodetalleListNew = gasto.getArqueodetalleList();
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                gasto.setUsuarioID(usuarioIDNew);
            }
            List<Arqueodetalle> attachedArqueodetalleListNew = new ArrayList<Arqueodetalle>();
            for (Arqueodetalle arqueodetalleListNewArqueodetalleToAttach : arqueodetalleListNew) {
                arqueodetalleListNewArqueodetalleToAttach = em.getReference(arqueodetalleListNewArqueodetalleToAttach.getClass(), arqueodetalleListNewArqueodetalleToAttach.getArqueoDetalleID());
                attachedArqueodetalleListNew.add(arqueodetalleListNewArqueodetalleToAttach);
            }
            arqueodetalleListNew = attachedArqueodetalleListNew;
            gasto.setArqueodetalleList(arqueodetalleListNew);
            gasto = em.merge(gasto);
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getGastoList().remove(gasto);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getGastoList().add(gasto);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            for (Arqueodetalle arqueodetalleListOldArqueodetalle : arqueodetalleListOld) {
                if (!arqueodetalleListNew.contains(arqueodetalleListOldArqueodetalle)) {
                    arqueodetalleListOldArqueodetalle.setGastoID(null);
                    arqueodetalleListOldArqueodetalle = em.merge(arqueodetalleListOldArqueodetalle);
                }
            }
            for (Arqueodetalle arqueodetalleListNewArqueodetalle : arqueodetalleListNew) {
                if (!arqueodetalleListOld.contains(arqueodetalleListNewArqueodetalle)) {
                    Gasto oldGastoIDOfArqueodetalleListNewArqueodetalle = arqueodetalleListNewArqueodetalle.getGastoID();
                    arqueodetalleListNewArqueodetalle.setGastoID(gasto);
                    arqueodetalleListNewArqueodetalle = em.merge(arqueodetalleListNewArqueodetalle);
                    if (oldGastoIDOfArqueodetalleListNewArqueodetalle != null && !oldGastoIDOfArqueodetalleListNewArqueodetalle.equals(gasto)) {
                        oldGastoIDOfArqueodetalleListNewArqueodetalle.getArqueodetalleList().remove(arqueodetalleListNewArqueodetalle);
                        oldGastoIDOfArqueodetalleListNewArqueodetalle = em.merge(oldGastoIDOfArqueodetalleListNewArqueodetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = gasto.getGastoID();
                if (findGasto(id) == null) {
                    throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.");
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
            Gasto gasto;
            try {
                gasto = em.getReference(Gasto.class, id);
                gasto.getGastoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The gasto with id " + id + " no longer exists.", enfe);
            }
            Usuario usuarioID = gasto.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getGastoList().remove(gasto);
                usuarioID = em.merge(usuarioID);
            }
            List<Arqueodetalle> arqueodetalleList = gasto.getArqueodetalleList();
            for (Arqueodetalle arqueodetalleListArqueodetalle : arqueodetalleList) {
                arqueodetalleListArqueodetalle.setGastoID(null);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
            }
            em.remove(gasto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Gasto> findGastoEntities() {
        return findGastoEntities(true, -1, -1);
    }

    public List<Gasto> findGastoEntities(int maxResults, int firstResult) {
        return findGastoEntities(false, maxResults, firstResult);
    }

    private List<Gasto> findGastoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Gasto.class));
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

    public Gasto findGasto(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Gasto.class, id);
        } finally {
            em.close();
        }
    }

    public int getGastoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Gasto> rt = cq.from(Gasto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
