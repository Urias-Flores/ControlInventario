package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Venta;
import java.util.ArrayList;
import java.util.List;
import Models.Abono;
import Models.Cliente;
import Models.Cotizacion;
import Models.Solicitud;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
            cliente.setVentaList(new ArrayList<>());
        }
        if (cliente.getCotizacionList() == null) {
            cliente.setCotizacionList(new ArrayList<>());
        }
        if (cliente.getSolicitudList() == null) {
            cliente.setSolicitudList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedVentaList = new ArrayList<>();
            if(cliente.getVentaList() != null){
                for (Venta ventaListVentaToAttach : cliente.getVentaList()) {
                    ventaListVentaToAttach = em.getReference(ventaListVentaToAttach.getClass(), ventaListVentaToAttach.getVentaID());
                    attachedVentaList.add(ventaListVentaToAttach);
                }
            }
            cliente.setVentaList(attachedVentaList);
            List<Cotizacion> attachedCotizacionList = new ArrayList<>();
            if(cliente.getCotizacionList() != null){
                for (Cotizacion cotizacionListCotizacionToAttach : cliente.getCotizacionList()) {
                    cotizacionListCotizacionToAttach = em.getReference(cotizacionListCotizacionToAttach.getClass(), cotizacionListCotizacionToAttach.getCotizacionID());
                    attachedCotizacionList.add(cotizacionListCotizacionToAttach);
                }
            }
            cliente.setCotizacionList(attachedCotizacionList);
            List<Solicitud> attachedSolicitudList = new ArrayList<>();
            if(cliente.getSolicitudList() != null){
                for (Solicitud solicitudListSolicitudToAttach : cliente.getSolicitudList()) {
                    solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getSolicitudID());
                    attachedSolicitudList.add(solicitudListSolicitudToAttach);
                }
            }
            cliente.setSolicitudList(attachedSolicitudList);
            em.persist(cliente);
            
            if(cliente.getVentaList() != null){
                for (Venta ventaListVenta : cliente.getVentaList()) {
                    Cliente oldClienteIDOfVentaListVenta = ventaListVenta.getClienteID();
                    ventaListVenta.setClienteID(cliente);
                    ventaListVenta = em.merge(ventaListVenta);
                    if (oldClienteIDOfVentaListVenta != null) {
                        oldClienteIDOfVentaListVenta.getVentaList().remove(ventaListVenta);
                        oldClienteIDOfVentaListVenta = em.merge(oldClienteIDOfVentaListVenta);
                    }
                }
            }
            
            if(cliente.getCotizacionList() != null){
                for (Cotizacion cotizacionListCotizacion : cliente.getCotizacionList()) {
                    Cliente oldClienteIDOfCotizacionListCotizacion = cotizacionListCotizacion.getClienteID();
                    cotizacionListCotizacion.setClienteID(cliente);
                    cotizacionListCotizacion = em.merge(cotizacionListCotizacion);
                    if (oldClienteIDOfCotizacionListCotizacion != null) {
                        oldClienteIDOfCotizacionListCotizacion.getCotizacionList().remove(cotizacionListCotizacion);
                        oldClienteIDOfCotizacionListCotizacion = em.merge(oldClienteIDOfCotizacionListCotizacion);
                    }
                }
            }
            
            if(cliente.getSolicitudList() != null){
                for (Solicitud solicitudListSolicitud : cliente.getSolicitudList()) {
                    Cliente oldClienteIDOfSolicitudListSolicitud = solicitudListSolicitud.getClienteID();
                    solicitudListSolicitud.setClienteID(cliente);
                    solicitudListSolicitud = em.merge(solicitudListSolicitud);
                    if (oldClienteIDOfSolicitudListSolicitud != null) {
                        oldClienteIDOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                        oldClienteIDOfSolicitudListSolicitud = em.merge(oldClienteIDOfSolicitudListSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getClienteID());
            List<Venta> ventaListOld = persistentCliente.getVentaList();
            List<Venta> ventaListNew = cliente.getVentaList();
            List<Cotizacion> cotizacionListOld = persistentCliente.getCotizacionList();
            List<Cotizacion> cotizacionListNew = cliente.getCotizacionList();
            List<Solicitud> solicitudListOld = persistentCliente.getSolicitudList();
            List<Solicitud> solicitudListNew = cliente.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its clienteID field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Venta> attachedVentaListNew = new ArrayList<>();
            for (Venta ventaListNewVentaToAttach : ventaListNew) {
                ventaListNewVentaToAttach = em.getReference(ventaListNewVentaToAttach.getClass(), ventaListNewVentaToAttach.getVentaID());
                attachedVentaListNew.add(ventaListNewVentaToAttach);
            }
            ventaListNew = attachedVentaListNew;
            cliente.setVentaList(ventaListNew);
            List<Cotizacion> attachedCotizacionListNew = new ArrayList<>();
            for (Cotizacion cotizacionListNewCotizacionToAttach : cotizacionListNew) {
                cotizacionListNewCotizacionToAttach = em.getReference(cotizacionListNewCotizacionToAttach.getClass(), cotizacionListNewCotizacionToAttach.getCotizacionID());
                attachedCotizacionListNew.add(cotizacionListNewCotizacionToAttach);
            }
            cotizacionListNew = attachedCotizacionListNew;
            cliente.setCotizacionList(cotizacionListNew);
            List<Solicitud> attachedSolicitudListNew = new ArrayList<>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getSolicitudID());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            cliente.setSolicitudList(solicitudListNew);
            cliente = em.merge(cliente);
            for (Venta ventaListOldVenta : ventaListOld) {
                if (!ventaListNew.contains(ventaListOldVenta)) {
                    ventaListOldVenta.setClienteID(null);
                    ventaListOldVenta = em.merge(ventaListOldVenta);
                }
            }
            for (Venta ventaListNewVenta : ventaListNew) {
                if (!ventaListOld.contains(ventaListNewVenta)) {
                    Cliente oldClienteIDOfVentaListNewVenta = ventaListNewVenta.getClienteID();
                    ventaListNewVenta.setClienteID(cliente);
                    ventaListNewVenta = em.merge(ventaListNewVenta);
                    if (oldClienteIDOfVentaListNewVenta != null && !oldClienteIDOfVentaListNewVenta.equals(cliente)) {
                        oldClienteIDOfVentaListNewVenta.getVentaList().remove(ventaListNewVenta);
                        oldClienteIDOfVentaListNewVenta = em.merge(oldClienteIDOfVentaListNewVenta);
                    }
                }
            }
            for (Cotizacion cotizacionListOldCotizacion : cotizacionListOld) {
                if (!cotizacionListNew.contains(cotizacionListOldCotizacion)) {
                    cotizacionListOldCotizacion.setClienteID(null);
                    cotizacionListOldCotizacion = em.merge(cotizacionListOldCotizacion);
                }
            }
            for (Cotizacion cotizacionListNewCotizacion : cotizacionListNew) {
                if (!cotizacionListOld.contains(cotizacionListNewCotizacion)) {
                    Cliente oldClienteIDOfCotizacionListNewCotizacion = cotizacionListNewCotizacion.getClienteID();
                    cotizacionListNewCotizacion.setClienteID(cliente);
                    cotizacionListNewCotizacion = em.merge(cotizacionListNewCotizacion);
                    if (oldClienteIDOfCotizacionListNewCotizacion != null && !oldClienteIDOfCotizacionListNewCotizacion.equals(cliente)) {
                        oldClienteIDOfCotizacionListNewCotizacion.getCotizacionList().remove(cotizacionListNewCotizacion);
                        oldClienteIDOfCotizacionListNewCotizacion = em.merge(oldClienteIDOfCotizacionListNewCotizacion);
                    }
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Cliente oldClienteIDOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getClienteID();
                    solicitudListNewSolicitud.setClienteID(cliente);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldClienteIDOfSolicitudListNewSolicitud != null && !oldClienteIDOfSolicitudListNewSolicitud.equals(cliente)) {
                        oldClienteIDOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldClienteIDOfSolicitudListNewSolicitud = em.merge(oldClienteIDOfSolicitudListNewSolicitud);
                    }
                }
            }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<Solicitud> solicitudListOrphanCheck = cliente.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Cliente (" + cliente + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable clienteID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
