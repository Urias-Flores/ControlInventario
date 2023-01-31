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
import Models.Compra;
import java.util.ArrayList;
import java.util.List;
import Models.Abono;
import Models.Proveedor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
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
            proveedor.setCompraList(new ArrayList<Compra>());
        }
        if (proveedor.getAbonoList() == null) {
            proveedor.setAbonoList(new ArrayList<Abono>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : proveedor.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getCompraID());
                attachedCompraList.add(compraListCompraToAttach);
            }
            proveedor.setCompraList(attachedCompraList);
            List<Abono> attachedAbonoList = new ArrayList<Abono>();
            for (Abono abonoListAbonoToAttach : proveedor.getAbonoList()) {
                abonoListAbonoToAttach = em.getReference(abonoListAbonoToAttach.getClass(), abonoListAbonoToAttach.getAbonoID());
                attachedAbonoList.add(abonoListAbonoToAttach);
            }
            proveedor.setAbonoList(attachedAbonoList);
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
            for (Abono abonoListAbono : proveedor.getAbonoList()) {
                Proveedor oldProveedorIDOfAbonoListAbono = abonoListAbono.getProveedorID();
                abonoListAbono.setProveedorID(proveedor);
                abonoListAbono = em.merge(abonoListAbono);
                if (oldProveedorIDOfAbonoListAbono != null) {
                    oldProveedorIDOfAbonoListAbono.getAbonoList().remove(abonoListAbono);
                    oldProveedorIDOfAbonoListAbono = em.merge(oldProveedorIDOfAbonoListAbono);
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
            List<Abono> abonoListOld = persistentProveedor.getAbonoList();
            List<Abono> abonoListNew = proveedor.getAbonoList();
            List<Compra> attachedCompraListNew = new ArrayList<Compra>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getCompraID());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            proveedor.setCompraList(compraListNew);
            List<Abono> attachedAbonoListNew = new ArrayList<Abono>();
            for (Abono abonoListNewAbonoToAttach : abonoListNew) {
                abonoListNewAbonoToAttach = em.getReference(abonoListNewAbonoToAttach.getClass(), abonoListNewAbonoToAttach.getAbonoID());
                attachedAbonoListNew.add(abonoListNewAbonoToAttach);
            }
            abonoListNew = attachedAbonoListNew;
            proveedor.setAbonoList(abonoListNew);
            proveedor = em.merge(proveedor);
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    compraListOldCompra.setProveedorID(null);
                    compraListOldCompra = em.merge(compraListOldCompra);
                }
            }
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
            for (Abono abonoListOldAbono : abonoListOld) {
                if (!abonoListNew.contains(abonoListOldAbono)) {
                    abonoListOldAbono.setProveedorID(null);
                    abonoListOldAbono = em.merge(abonoListOldAbono);
                }
            }
            for (Abono abonoListNewAbono : abonoListNew) {
                if (!abonoListOld.contains(abonoListNewAbono)) {
                    Proveedor oldProveedorIDOfAbonoListNewAbono = abonoListNewAbono.getProveedorID();
                    abonoListNewAbono.setProveedorID(proveedor);
                    abonoListNewAbono = em.merge(abonoListNewAbono);
                    if (oldProveedorIDOfAbonoListNewAbono != null && !oldProveedorIDOfAbonoListNewAbono.equals(proveedor)) {
                        oldProveedorIDOfAbonoListNewAbono.getAbonoList().remove(abonoListNewAbono);
                        oldProveedorIDOfAbonoListNewAbono = em.merge(oldProveedorIDOfAbonoListNewAbono);
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
            List<Abono> abonoList = proveedor.getAbonoList();
            for (Abono abonoListAbono : abonoList) {
                abonoListAbono.setProveedorID(null);
                abonoListAbono = em.merge(abonoListAbono);
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
