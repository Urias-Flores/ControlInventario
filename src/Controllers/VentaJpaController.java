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
import Models.Arqueodetalle;
import java.util.ArrayList;
import java.util.List;
import Models.Abono;
import Models.Venta;
import Models.Ventadetalle;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class VentaJpaController implements Serializable {

    public VentaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public int create(Venta venta) {
        if (venta.getArqueodetalleList() == null) {
            venta.setArqueodetalleList(new ArrayList<>());
        }
        if (venta.getAbonoList() == null) {
            venta.setAbonoList(new ArrayList<>());
        }
        if (venta.getVentadetalleList() == null) {
            venta.setVentadetalleList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente clienteID = venta.getClienteID();
            if (clienteID != null) {
                clienteID = em.getReference(clienteID.getClass(), clienteID.getClienteID());
                venta.setClienteID(clienteID);
            }
            Usuario usuarioID = venta.getUsuarioID();
            if (usuarioID != null) {
                usuarioID = em.getReference(usuarioID.getClass(), usuarioID.getUsuarioID());
                venta.setUsuarioID(usuarioID);
            }
            List<Arqueodetalle> attachedArqueodetalleList = new ArrayList<>();
            for (Arqueodetalle arqueodetalleListArqueodetalleToAttach : venta.getArqueodetalleList()) {
                arqueodetalleListArqueodetalleToAttach = em.getReference(arqueodetalleListArqueodetalleToAttach.getClass(), arqueodetalleListArqueodetalleToAttach.getArqueoDetalleID());
                attachedArqueodetalleList.add(arqueodetalleListArqueodetalleToAttach);
            }
            venta.setArqueodetalleList(attachedArqueodetalleList);
            List<Abono> attachedAbonoList = new ArrayList<>();
            for (Abono abonoListAbonoToAttach : venta.getAbonoList()) {
                abonoListAbonoToAttach = em.getReference(abonoListAbonoToAttach.getClass(), abonoListAbonoToAttach.getAbonoID());
                attachedAbonoList.add(abonoListAbonoToAttach);
            }
            venta.setAbonoList(attachedAbonoList);
            List<Ventadetalle> attachedVentadetalleList = new ArrayList<>();
            for (Ventadetalle ventadetalleListVentadetalleToAttach : venta.getVentadetalleList()) {
                ventadetalleListVentadetalleToAttach = em.getReference(ventadetalleListVentadetalleToAttach.getClass(), ventadetalleListVentadetalleToAttach.getVentaDetalleID());
                attachedVentadetalleList.add(ventadetalleListVentadetalleToAttach);
            }
            venta.setVentadetalleList(attachedVentadetalleList);
            em.persist(venta);
            if (clienteID != null) {
                clienteID.getVentaList().add(venta);
                clienteID = em.merge(clienteID);
            }
            if (usuarioID != null) {
                usuarioID.getVentaList().add(venta);
                usuarioID = em.merge(usuarioID);
            }
            for (Arqueodetalle arqueodetalleListArqueodetalle : venta.getArqueodetalleList()) {
                Venta oldFacturaIDOfArqueodetalleListArqueodetalle = arqueodetalleListArqueodetalle.getFacturaID();
                arqueodetalleListArqueodetalle.setFacturaID(venta);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
                if (oldFacturaIDOfArqueodetalleListArqueodetalle != null) {
                    oldFacturaIDOfArqueodetalleListArqueodetalle.getArqueodetalleList().remove(arqueodetalleListArqueodetalle);
                    oldFacturaIDOfArqueodetalleListArqueodetalle = em.merge(oldFacturaIDOfArqueodetalleListArqueodetalle);
                }
            }
            for (Abono abonoListAbono : venta.getAbonoList()) {
                Venta oldVentaIDOfAbonoListAbono = abonoListAbono.getVentaID();
                abonoListAbono.setVentaID(venta);
                abonoListAbono = em.merge(abonoListAbono);
                if (oldVentaIDOfAbonoListAbono != null) {
                    oldVentaIDOfAbonoListAbono.getAbonoList().remove(abonoListAbono);
                    oldVentaIDOfAbonoListAbono = em.merge(oldVentaIDOfAbonoListAbono);
                }
            }
            for (Ventadetalle ventadetalleListVentadetalle : venta.getVentadetalleList()) {
                Venta oldVentaIDOfVentadetalleListVentadetalle = ventadetalleListVentadetalle.getVentaID();
                ventadetalleListVentadetalle.setVentaID(venta);
                ventadetalleListVentadetalle = em.merge(ventadetalleListVentadetalle);
                if (oldVentaIDOfVentadetalleListVentadetalle != null) {
                    oldVentaIDOfVentadetalleListVentadetalle.getVentadetalleList().remove(ventadetalleListVentadetalle);
                    oldVentaIDOfVentadetalleListVentadetalle = em.merge(oldVentaIDOfVentadetalleListVentadetalle);
                }
            }
            em.flush();
            em.getTransaction().commit();
            return venta.getVentaID();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Venta venta) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Venta persistentVenta = em.find(Venta.class, venta.getVentaID());
            Cliente clienteIDOld = persistentVenta.getClienteID();
            Cliente clienteIDNew = venta.getClienteID();
            Usuario usuarioIDOld = persistentVenta.getUsuarioID();
            Usuario usuarioIDNew = venta.getUsuarioID();
            List<Arqueodetalle> arqueodetalleListOld = persistentVenta.getArqueodetalleList();
            List<Arqueodetalle> arqueodetalleListNew = venta.getArqueodetalleList();
            List<Abono> abonoListOld = persistentVenta.getAbonoList();
            List<Abono> abonoListNew = venta.getAbonoList();
            List<Ventadetalle> ventadetalleListOld = persistentVenta.getVentadetalleList();
            List<Ventadetalle> ventadetalleListNew = venta.getVentadetalleList();
            List<String> illegalOrphanMessages = null;
            if(ventadetalleListOld != null){
                for (Ventadetalle ventadetalleListOldVentadetalle : ventadetalleListOld) {
                    if (!ventadetalleListNew.contains(ventadetalleListOldVentadetalle)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<>();
                        }
                        illegalOrphanMessages.add("You must retain Ventadetalle " + ventadetalleListOldVentadetalle + " since its ventaID field is not nullable.");
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteIDNew != null) {
                clienteIDNew = em.getReference(clienteIDNew.getClass(), clienteIDNew.getClienteID());
                venta.setClienteID(clienteIDNew);
            }
            if (usuarioIDNew != null) {
                usuarioIDNew = em.getReference(usuarioIDNew.getClass(), usuarioIDNew.getUsuarioID());
                venta.setUsuarioID(usuarioIDNew);
            }
            List<Arqueodetalle> attachedArqueodetalleListNew = new ArrayList<>();
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalleToAttach : arqueodetalleListNew) {
                    arqueodetalleListNewArqueodetalleToAttach = em.getReference(arqueodetalleListNewArqueodetalleToAttach.getClass(), arqueodetalleListNewArqueodetalleToAttach.getArqueoDetalleID());
                    attachedArqueodetalleListNew.add(arqueodetalleListNewArqueodetalleToAttach);
                }
            }
            arqueodetalleListNew = attachedArqueodetalleListNew;
            venta.setArqueodetalleList(arqueodetalleListNew);
            List<Abono> attachedAbonoListNew = new ArrayList<>();
            if(abonoListNew != null){
                for (Abono abonoListNewAbonoToAttach : abonoListNew) {
                    abonoListNewAbonoToAttach = em.getReference(abonoListNewAbonoToAttach.getClass(), abonoListNewAbonoToAttach.getAbonoID());
                    attachedAbonoListNew.add(abonoListNewAbonoToAttach);
                }
            }
            abonoListNew = attachedAbonoListNew;
            venta.setAbonoList(abonoListNew);
            List<Ventadetalle> attachedVentadetalleListNew = new ArrayList<>();
            if(ventadetalleListNew != null){
                for (Ventadetalle ventadetalleListNewVentadetalleToAttach : ventadetalleListNew) {
                    ventadetalleListNewVentadetalleToAttach = em.getReference(ventadetalleListNewVentadetalleToAttach.getClass(), ventadetalleListNewVentadetalleToAttach.getVentaDetalleID());
                    attachedVentadetalleListNew.add(ventadetalleListNewVentadetalleToAttach);
                } 
            }
            ventadetalleListNew = attachedVentadetalleListNew;
            venta.setVentadetalleList(ventadetalleListNew);
            venta = em.merge(venta);
            if (clienteIDOld != null && !clienteIDOld.equals(clienteIDNew)) {
                clienteIDOld.getVentaList().remove(venta);
                clienteIDOld = em.merge(clienteIDOld);
            }
            if (clienteIDNew != null && !clienteIDNew.equals(clienteIDOld)) {
                clienteIDNew.getVentaList().add(venta);
                clienteIDNew = em.merge(clienteIDNew);
            }
            if (usuarioIDOld != null && !usuarioIDOld.equals(usuarioIDNew)) {
                usuarioIDOld.getVentaList().remove(venta);
                usuarioIDOld = em.merge(usuarioIDOld);
            }
            if (usuarioIDNew != null && !usuarioIDNew.equals(usuarioIDOld)) {
                usuarioIDNew.getVentaList().add(venta);
                usuarioIDNew = em.merge(usuarioIDNew);
            }
            if(arqueodetalleListOld != null){
                for (Arqueodetalle arqueodetalleListOldArqueodetalle : arqueodetalleListOld) {
                    if (!arqueodetalleListNew.contains(arqueodetalleListOldArqueodetalle)) {
                        arqueodetalleListOldArqueodetalle.setFacturaID(null);
                        arqueodetalleListOldArqueodetalle = em.merge(arqueodetalleListOldArqueodetalle);
                    }
                } 
            }
            if(arqueodetalleListNew != null){
                for (Arqueodetalle arqueodetalleListNewArqueodetalle : arqueodetalleListNew) {
                    if (!arqueodetalleListOld.contains(arqueodetalleListNewArqueodetalle)) {
                        Venta oldFacturaIDOfArqueodetalleListNewArqueodetalle = arqueodetalleListNewArqueodetalle.getFacturaID();
                        arqueodetalleListNewArqueodetalle.setFacturaID(venta);
                        arqueodetalleListNewArqueodetalle = em.merge(arqueodetalleListNewArqueodetalle);
                        if (oldFacturaIDOfArqueodetalleListNewArqueodetalle != null && !oldFacturaIDOfArqueodetalleListNewArqueodetalle.equals(venta)) {
                            oldFacturaIDOfArqueodetalleListNewArqueodetalle.getArqueodetalleList().remove(arqueodetalleListNewArqueodetalle);
                            oldFacturaIDOfArqueodetalleListNewArqueodetalle = em.merge(oldFacturaIDOfArqueodetalleListNewArqueodetalle);
                        }
                    }
                }
            }
            if(abonoListOld != null){
                for (Abono abonoListOldAbono : abonoListOld) {
                    if (!abonoListNew.contains(abonoListOldAbono)) {
                        abonoListOldAbono.setVentaID(null);
                        abonoListOldAbono = em.merge(abonoListOldAbono);
                    }
                } 
            }
            if(abonoListNew != null){
                for (Abono abonoListNewAbono : abonoListNew) {
                    if (!abonoListOld.contains(abonoListNewAbono)) {
                        Venta oldVentaIDOfAbonoListNewAbono = abonoListNewAbono.getVentaID();
                        abonoListNewAbono.setVentaID(venta);
                        abonoListNewAbono = em.merge(abonoListNewAbono);
                        if (oldVentaIDOfAbonoListNewAbono != null && !oldVentaIDOfAbonoListNewAbono.equals(venta)) {
                            oldVentaIDOfAbonoListNewAbono.getAbonoList().remove(abonoListNewAbono);
                            oldVentaIDOfAbonoListNewAbono = em.merge(oldVentaIDOfAbonoListNewAbono);
                        }
                    }
                }
            }
            if(ventadetalleListNew != null){
                for (Ventadetalle ventadetalleListNewVentadetalle : ventadetalleListNew) {
                    if (!ventadetalleListOld.contains(ventadetalleListNewVentadetalle)) {
                        Venta oldVentaIDOfVentadetalleListNewVentadetalle = ventadetalleListNewVentadetalle.getVentaID();
                        ventadetalleListNewVentadetalle.setVentaID(venta);
                        ventadetalleListNewVentadetalle = em.merge(ventadetalleListNewVentadetalle);
                        if (oldVentaIDOfVentadetalleListNewVentadetalle != null && !oldVentaIDOfVentadetalleListNewVentadetalle.equals(venta)) {
                            oldVentaIDOfVentadetalleListNewVentadetalle.getVentadetalleList().remove(ventadetalleListNewVentadetalle);
                            oldVentaIDOfVentadetalleListNewVentadetalle = em.merge(oldVentaIDOfVentadetalleListNewVentadetalle);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = venta.getVentaID();
                if (findVenta(id) == null) {
                    throw new NonexistentEntityException("The venta with id " + id + " no longer exists.");
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
            Venta venta;
            try {
                venta = em.getReference(Venta.class, id);
                venta.getVentaID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venta with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Ventadetalle> ventadetalleListOrphanCheck = venta.getVentadetalleList();
            for (Ventadetalle ventadetalleListOrphanCheckVentadetalle : ventadetalleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Venta (" + venta + ") cannot be destroyed since the Ventadetalle " + ventadetalleListOrphanCheckVentadetalle + " in its ventadetalleList field has a non-nullable ventaID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente clienteID = venta.getClienteID();
            if (clienteID != null) {
                clienteID.getVentaList().remove(venta);
                clienteID = em.merge(clienteID);
            }
            Usuario usuarioID = venta.getUsuarioID();
            if (usuarioID != null) {
                usuarioID.getVentaList().remove(venta);
                usuarioID = em.merge(usuarioID);
            }
            List<Arqueodetalle> arqueodetalleList = venta.getArqueodetalleList();
            for (Arqueodetalle arqueodetalleListArqueodetalle : arqueodetalleList) {
                arqueodetalleListArqueodetalle.setFacturaID(null);
                arqueodetalleListArqueodetalle = em.merge(arqueodetalleListArqueodetalle);
            }
            List<Abono> abonoList = venta.getAbonoList();
            for (Abono abonoListAbono : abonoList) {
                abonoListAbono.setVentaID(null);
                abonoListAbono = em.merge(abonoListAbono);
            }
            em.remove(venta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Venta> findVentaEntities() {
        return findVentaEntities(true, -1, -1);
    }

    public List<Venta> findVentaEntities(int maxResults, int firstResult) {
        return findVentaEntities(false, maxResults, firstResult);
    }

    private List<Venta> findVentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venta.class));
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

    public Venta findVenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venta.class, id);
        } finally {
            em.close();
        }
    }

    public int getVentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venta> rt = cq.from(Venta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
