/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.NonexistentEntityException;
import Models.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Venta;
import java.util.ArrayList;
import java.util.List;
import Models.Cotizacion;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getVentaList() == null) {
            cliente.setVentaList(new ArrayList<Venta>());
        }
        if (cliente.getCotizacionList() == null) {
            cliente.setCotizacionList(new ArrayList<Cotizacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedVentaList = new ArrayList<Venta>();
            for (Venta ventaListVentaToAttach : cliente.getVentaList()) {
                ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getVentaID());
                attachedVentaList.add(ventaListVentaToAttach);
            }
            cliente.setVentaList(attachedVentaList);
            List<Cotizacion> attachedCotizacionList = new ArrayList<Cotizacion>();
            for (Cotizacion cotizacionListCotizacionToAttach : cliente.getCotizacionList()) {
                cotizacionListCotizacionToAttach = em.getReference(cotizacionListCotizacionToAttach.getClass(), cotizacionListCotizacionToAttach.getCotizacionID());
                attachedCotizacionList.add(cotizacionListCotizacionToAttach);
            }
            cliente.setCotizacionList(attachedCotizacionList);
            em.persist(cliente);
            for (Venta ventaListVenta : cliente.getVentaList()) {
                Cliente oldClienteIDOfVentaListVenta = ventaListVenta.getClienteID();
                ventaListVenta.setClienteID(cliente);
                ventaListVenta = em.merge(ventaListVenta);
                if (oldClienteIDOfVentaListVenta != null) {
                    oldClienteIDOfVentaListVenta.getVentaList().remove(ventaListVenta);
                    oldClienteIDOfVentaListVenta = em.merge(oldClienteIDOfVentaListVenta);
                }
            }
            for (Cotizacion cotizacionListCotizacion : cliente.getCotizacionList()) {
                Cliente oldClienteIDOfCotizacionListCotizacion = cotizacionListCotizacion.getClienteID();
                cotizacionListCotizacion.setClienteID(cliente);
                cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
                if (oldClienteIDOfCotizacionListCotizacion != null) {
                    oldClienteIDOfCotizacionListCotizacion.getCotizacionList().remove(cotizacionListCotizacion);
                    oldClienteIDOfCotizacionListCotizacion = em.merge(oldClienteIDOfCotizacionListCotizacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getClienteID());
            List<Venta> ventaListNew = cliente.getVentaList();
            List<Cotizacion> cotizacionListNew = cliente.getCotizacionList();
            List<Venta> attachedVentaListNew = new ArrayList<Venta>();
            
            ventaListNew = attachedVentaListNew;
            cliente.setVentaList(ventaListNew);
            List<Cotizacion> attachedCotizacionListNew = new ArrayList<Cotizacion>();
            
            cotizacionListNew = attachedCotizacionListNew;
            cliente.setCotizacionList(cotizacionListNew);
            cliente = em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cliente.getClienteID();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getClienteID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Venta> ventaList = cliente.getVentaList();
            for (Venta ventaListVenta : ventaList) {
                ventaListVenta.setClienteID(null);
                ventaListVenta = em.merge(ventaListVenta);
            }
            List<Cotizacion> cotizacionList = cliente.getCotizacionList();
            for (Cotizacion cotizacionListCotizacion : cotizacionList) {
                cotizacionListCotizacion.setClienteID(null);
                cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
