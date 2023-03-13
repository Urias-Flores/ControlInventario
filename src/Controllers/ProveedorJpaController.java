package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Compra;
import java.util.ArrayList;
import java.util.List;
import Models.Abono;
import Models.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) {
        if (proveedor.getCompraList() == null) {
            proveedor.setCompraList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<>();
            for (Compra compraListCompraToAttach : proveedor.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getCompraID());
                attachedCompraList.add(compraListCompraToAttach);
            }
            proveedor.setCompraList(attachedCompraList);
            em.persist(proveedor);
            for (Compra compraListCompra : proveedor.getCompraList()) {
                Proveedor oldProveedorIDOfCompraListCompra = compraListCompra.getProveedorID();
                compraListCompra.setProveedorID(proveedor);
                compraListCompra = em.merge(compraListCompra);
                if (oldProveedorIDOfCompraListCompra != null) {
                    oldProveedorIDOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldProveedorIDOfCompraListCompra = em.merge(oldProveedorIDOfCompraListCompra);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getProveedorID());
            List<Compra> compraListOld = persistentProveedor.getCompraList();
            List<Compra> compraListNew = proveedor.getCompraList();
            List<Compra> attachedCompraListNew = new ArrayList<>();
            if(compraListNew != null){
                for (Compra compraListNewCompraToAttach : compraListNew) {
                    compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getCompraID());
                    attachedCompraListNew.add(compraListNewCompraToAttach);
                }
            }
            compraListNew = attachedCompraListNew;
            proveedor.setCompraList(compraListNew);
            proveedor = em.merge(proveedor);
            if(compraListOld != null){
                for (Compra compraListOldCompra : compraListOld) {
                    if (!compraListNew.contains(compraListOldCompra)) {
                        compraListOldCompra.setProveedorID(null);
                        compraListOldCompra = em.merge(compraListOldCompra);
                    }
                }
            }
            if(compraListNew != null){
                for (Compra compraListNewCompra : compraListNew) {
                    if (!compraListOld.contains(compraListNewCompra)) {
                        Proveedor oldProveedorIDOfCompraListNewCompra = compraListNewCompra.getProveedorID();
                        compraListNewCompra.setProveedorID(proveedor);
                        compraListNewCompra = em.merge(compraListNewCompra);
                        if (oldProveedorIDOfCompraListNewCompra != null && !oldProveedorIDOfCompraListNewCompra.equals(proveedor)) {
                            oldProveedorIDOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                            oldProveedorIDOfCompraListNewCompra = em.merge(oldProveedorIDOfCompraListNewCompra);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getProveedorID();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getProveedorID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<Compra> compraList = proveedor.getCompraList();
            for (Compra compraListCompra : compraList) {
                compraListCompra.setProveedorID(null);
                compraListCompra = em.merge(compraListCompra);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
