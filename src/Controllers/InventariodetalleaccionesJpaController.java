/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import Models.Inventariodetalleacciones;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Producto;
import Models.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
public class InventariodetalleaccionesJpaController implements Serializable {

    public InventariodetalleaccionesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Inventariodetalleacciones inventariodetalleacciones) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto productoID = inventariodetalleacciones.getProductoID();
            if (productoID != null) {
                productoID = em.getReference(productoID.getClass(), productoID.getProductoID());
                inventariodetalleacciones.setProductoID(productoID);
            }
            Usuario usuarioID = inventariodetalleacciones.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                inventariodetalleacciones.setUsuarioID(usuarioID);
            }
            em.persist(inventariodetalleacciones);
            if (productoID != null) {
                productoID.getInventariodetalleaccionesList().add(inventariodetalleacciones);
                productoID = em.merge(productoID);
            }
            if (usuarioID != null) {
                usuarioID.getInventariodetalleaccionesList().add(inventariodetalleacciones);
                usuarioID = em.merge(usuarioID);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Inventariodetalleacciones inventariodetalleacciones) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Inventariodetalleacciones persistentInventariodetalleacciones = em.find(Inventariodetalleacciones.class, inventariodetalleacciones.getInventarioDetalleAccionesID());
            Producto productoIDOld = persistentInventariodetalleacciones.getProductoID();
            Producto productoIDNew = inventariodetalleacciones.getProductoID();
            Usuario usuarioIDOld = persistentInventariodetalleacciones.getUsuarioID();
            Usuario usuarioIDNew = inventariodetalleacciones.getUsuarioID();
            if (productoIDNew != null) {
                productoIDNew = em.getReference(productoIDNew.getClass(), productoIDNew.getProductoID());
                inventariodetalleacciones.setProductoID(productoIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                inventariodetalleacciones.setUsuarioID(usuarioIDNew);
            }
            inventariodetalleacciones = em.merge(inventariodetalleacciones);
            if (productoIDOld != null && !productoIDOld.equals(productoIDNew)) {
                productoIDOld.getInventariodetalleaccionesList().remove(inventariodetalleacciones);
                productoIDOld = em.merge(productoIDOld);
            }
            if (productoIDNew != null && !productoIDNew.equals(productoIDOld)) {
                productoIDNew.getInventariodetalleaccionesList().add(inventariodetalleacciones);
                productoIDNew = em.merge(productoIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getInventariodetalleaccionesList().remove(inventariodetalleacciones);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getInventariodetalleaccionesList().add(inventariodetalleacciones);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = inventariodetalleacciones.getInventarioDetalleAccionesID();
                if (findInventariodetalleacciones(id) == null) {
                    throw new NonexistentEntityException("The inventariodetalleacciones with id " + id + " no longer exists.");
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
            Inventariodetalleacciones inventariodetalleacciones;
            try {
                inventariodetalleacciones = em.getReference(Inventariodetalleacciones.class, id);
                inventariodetalleacciones.getInventarioDetalleAccionesID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventariodetalleacciones with id " + id + " no longer exists.", enfe);
            }
            Producto productoID = inventariodetalleacciones.getProductoID();
            if (productoID != null) {
                productoID.getInventariodetalleaccionesList().remove(inventariodetalleacciones);
                productoID = em.merge(productoID);
            }
            Usuario usuarioID = inventariodetalleacciones.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getInventariodetalleaccionesList().remove(inventariodetalleacciones);
                usuarioID = em.merge(usuarioID);
            }
            em.remove(inventariodetalleacciones);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Inventariodetalleacciones> findInventariodetalleaccionesEntities() {
        return findInventariodetalleaccionesEntities(true, -1, -1);
    }

    public List<Inventariodetalleacciones> findInventariodetalleaccionesEntities(int maxResults, int firstResult) {
        return findInventariodetalleaccionesEntities(false, maxResults, firstResult);
    }

    private List<Inventariodetalleacciones> findInventariodetalleaccionesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Inventariodetalleacciones.class));
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

    public Inventariodetalleacciones findInventariodetalleacciones(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Inventariodetalleacciones.class, id);
        } finally {
            em.close();
        }
    }

    public int getInventariodetalleaccionesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Inventariodetalleacciones> rt = cq.from(Inventariodetalleacciones.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
