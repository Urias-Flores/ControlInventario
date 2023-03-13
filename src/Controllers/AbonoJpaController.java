package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import Models.Abono;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Compra;
import Models.Usuario;
import Models.Venta;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class AbonoJpaController implements Serializable {

    public AbonoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Abono abono) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra compraID = abono.getCompraID();
            if (compraID != null) {
                compraID = em.getReference(compraID.getClass(), compraID.getCompraID());
                abono.setCompraID(compraID);
            }
            Usuario usuarioID = abono.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                abono.setUsuarioID(usuarioID);
            }
            Venta ventaID = abono.getVentaID();
            if (ventaID != null) {
                ventaID = em.getReference(ventaID.getClass(), ventaID.getVentaID());
                abono.setVentaID(ventaID);
            }
            em.persist(abono);
            if (compraID != null) {
                compraID.getAbonoList().add(abono);
                compraID = em.merge(compraID);
            }
            if (usuarioID != null) {
                usuarioID.getAbonoList().add(abono);
                usuarioID = em.merge(usuarioID);
            }
            if (ventaID != null) {
                ventaID.getAbonoList().add(abono);
                ventaID = em.merge(ventaID);
            }
            em.flush();
            em.getTransaction().commit();
            return abono.getAbonoID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Abono abono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Abono persistentAbono = em.find(Abono.class, abono.getAbonoID());
            Compra compraIDOld = persistentAbono.getCompraID();
            Compra compraIDNew = abono.getCompraID();
            Usuario usuarioIDOld = persistentAbono.getUsuarioID();
            Usuario usuarioIDNew = abono.getUsuarioID();
            Venta ventaIDOld = persistentAbono.getVentaID();
            Venta ventaIDNew = abono.getVentaID();
            if (compraIDNew != null) {
                compraIDNew = em.getReference(compraIDNew.getClass(), compraIDNew.getCompraID());
                abono.setCompraID(compraIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                abono.setUsuarioID(usuarioIDNew);
            }
            if (ventaIDNew != null) {
                ventaIDNew = em.getReference(ventaIDNew.getClass(), ventaIDNew.getVentaID());
                abono.setVentaID(ventaIDNew);
            }
            abono = em.merge(abono);
            if (compraIDOld != null && !compraIDOld.equals(compraIDNew)) {
                compraIDOld.getAbonoList().remove(abono);
                compraIDOld = em.merge(compraIDOld);
            }
            if (compraIDNew != null && !compraIDNew.equals(compraIDOld)) {
                compraIDNew.getAbonoList().add(abono);
                compraIDNew = em.merge(compraIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getAbonoList().remove(abono);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getAbonoList().add(abono);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            if (ventaIDOld != null && !ventaIDOld.equals(ventaIDNew)) {
                ventaIDOld.getAbonoList().remove(abono);
                ventaIDOld = em.merge(ventaIDOld);
            }
            if (ventaIDNew != null && !ventaIDNew.equals(ventaIDOld)) {
                ventaIDNew.getAbonoList().add(abono);
                ventaIDNew = em.merge(ventaIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = abono.getAbonoID();
                if (findAbono(id) == null) {
                    throw new NonexistentEntityException("The abono with id " + id + " no longer exists.");
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
            Abono abono;
            try {
                abono = em.getReference(Abono.class, id);
                abono.getAbonoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The abono with id " + id + " no longer exists.", enfe);
            }
            Compra compraID = abono.getCompraID();
            if (compraID != null) {
                compraID.getAbonoList().remove(abono);
                compraID = em.merge(compraID);
            }
            Usuario usuarioID = abono.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getAbonoList().remove(abono);
                usuarioID = em.merge(usuarioID);
            }
            Venta ventaID = abono.getVentaID();
            if (ventaID != null) {
                ventaID.getAbonoList().remove(abono);
                ventaID = em.merge(ventaID);
            }
            em.remove(abono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Abono> findAbonoEntities() {
        return findAbonoEntities(true, -1, -1);
    }

    public List<Abono> findAbonoEntities(int maxResults, int firstResult) {
        return findAbonoEntities(false, maxResults, firstResult);
    }

    private List<Abono> findAbonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Abono.class));
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

    public Abono findAbono(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Abono.class, id);
        } finally {
            em.close();
        }
    }

    public int getAbonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Abono> rt = cq.from(Abono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
