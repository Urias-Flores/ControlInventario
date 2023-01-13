package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Compra;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Proveedor;
import Models.Usuario;
import Models.Compradetalle;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Compra compra) {
        if (compra.getCompradetalleList() == null) {
            compra.setCompradetalleList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedorID = compra.getProveedorID();
            if (proveedorID != null) {
                proveedorID = em.getReference(proveedorID.getClass(), proveedorID.getProveedorID());
                compra.setProveedorID(proveedorID);
            }
            Usuario usuarioID = compra.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                compra.setUsuarioID(usuarioID);
            }
            List<Compradetalle> attachedCompradetalleList = new ArrayList<Compradetalle>();
            for (Compradetalle compradetalleListCompradetalleToAttach : compra.getCompradetalleList()) {
                compradetalleListCompradetalleToAttach = em.getReference(compradetalleListCompradetalleToAttach.getClass(), compradetalleListCompradetalleToAttach.getCompraDetalleID());
                attachedCompradetalleList.add(compradetalleListCompradetalleToAttach);
            }
            compra.setCompradetalleList(attachedCompradetalleList);
            em.persist(compra);
            if (proveedorID != null) {
                proveedorID.getCompraList().add(compra);
                proveedorID = em.merge(proveedorID);
            }
            if (usuarioID != null) {
                usuarioID.getCompraList().add(compra);
                usuarioID = em.merge(usuarioID);
            }
            for (Compradetalle compradetalleListCompradetalle : compra.getCompradetalleList()) {
                Compra oldCompraIDOfCompradetalleListCompradetalle = compradetalleListCompradetalle.getCompraID();
                compradetalleListCompradetalle.setCompraID(compra);
                compradetalleListCompradetalle = em.merge(compradetalleListCompradetalle);
                if (oldCompraIDOfCompradetalleListCompradetalle != null) {
                    oldCompraIDOfCompradetalleListCompradetalle.getCompradetalleList().remove(compradetalleListCompradetalle);
                    oldCompraIDOfCompradetalleListCompradetalle = em.merge(oldCompraIDOfCompradetalleListCompradetalle);
                }
            }
            em.flush();
            em.getTransaction().commit();
            return compra.getCompraID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getCompraID());
            Proveedor proveedorIDOld = persistentCompra.getProveedorID();
            Proveedor proveedorIDNew = compra.getProveedorID();
            Usuario usuarioIDOld = persistentCompra.getUsuarioID();
            Usuario usuarioIDNew = compra.getUsuarioID();
            List<Compradetalle> compradetalleListOld = persistentCompra.getCompradetalleList();
            List<Compradetalle> compradetalleListNew = compra.getCompradetalleList();
            List<String> illegalOrphanMessages = null;
            for (Compradetalle compradetalleListOldCompradetalle : compradetalleListOld) {
                if (!compradetalleListNew.contains(compradetalleListOldCompradetalle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Compradetalle " + compradetalleListOldCompradetalle + " since its compraID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorIDNew != null) {
                proveedorIDNew = em.getReference(proveedorIDNew.getClass(), proveedorIDNew.getProveedorID());
                compra.setProveedorID(proveedorIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                compra.setUsuarioID(usuarioIDNew);
            }
            List<Compradetalle> attachedCompradetalleListNew = new ArrayList<Compradetalle>();
            for (Compradetalle compradetalleListNewCompradetalleToAttach : compradetalleListNew) {
                compradetalleListNewCompradetalleToAttach = em.getReference(compradetalleListNewCompradetalleToAttach.getClass(), compradetalleListNewCompradetalleToAttach.getCompraDetalleID());
                attachedCompradetalleListNew.add(compradetalleListNewCompradetalleToAttach);
            }
            compradetalleListNew = attachedCompradetalleListNew;
            compra.setCompradetalleList(compradetalleListNew);
            compra = em.merge(compra);
            if (proveedorIDOld != null && !proveedorIDOld.equals(proveedorIDNew)) {
                proveedorIDOld.getCompraList().remove(compra);
                proveedorIDOld = em.merge(proveedorIDOld);
            }
            if (proveedorIDNew != null && !proveedorIDNew.equals(proveedorIDOld)) {
                proveedorIDNew.getCompraList().add(compra);
                proveedorIDNew = em.merge(proveedorIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getCompraList().remove(compra);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getCompraList().add(compra);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            for (Compradetalle compradetalleListNewCompradetalle : compradetalleListNew) {
                if (!compradetalleListOld.contains(compradetalleListNewCompradetalle)) {
                    Compra oldCompraIDOfCompradetalleListNewCompradetalle = compradetalleListNewCompradetalle.getCompraID();
                    compradetalleListNewCompradetalle.setCompraID(compra);
                    compradetalleListNewCompradetalle = em.merge(compradetalleListNewCompradetalle);
                    if (oldCompraIDOfCompradetalleListNewCompradetalle != null && !oldCompraIDOfCompradetalleListNewCompradetalle.equals(compra)) {
                        oldCompraIDOfCompradetalleListNewCompradetalle.getCompradetalleList().remove(compradetalleListNewCompradetalle);
                        oldCompraIDOfCompradetalleListNewCompradetalle = em.merge(oldCompraIDOfCompradetalleListNewCompradetalle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compra.getCompraID();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getCompraID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Compradetalle> compradetalleListOrphanCheck = compra.getCompradetalleList();
            for (Compradetalle compradetalleListOrphanCheckCompradetalle : compradetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Compradetalle " + compradetalleListOrphanCheckCompradetalle + " in its compradetalleList field has a non-nullable compraID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedorID = compra.getProveedorID();
            if (proveedorID != null) {
                proveedorID.getCompraList().remove(compra);
                proveedorID = em.merge(proveedorID);
            }
            Usuario usuarioID = compra.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getCompraList().remove(compra);
                usuarioID = em.merge(usuarioID);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
