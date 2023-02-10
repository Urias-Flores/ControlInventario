/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Dell
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getUsuarioList() == null) {
            empleado.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : empleado.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getUsuarioID());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            empleado.setUsuarioList(attachedUsuarioList);
            em.persist(empleado);
            for (Usuario usuarioListUsuario : empleado.getUsuarioList()) {
                Empleado oldEmpleadoIDOfUsuarioListUsuario = usuarioListUsuario.getEmpleadoID();
                usuarioListUsuario.setEmpleadoID(empleado);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldEmpleadoIDOfUsuarioListUsuario != null) {
                    oldEmpleadoIDOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldEmpleadoIDOfUsuarioListUsuario = em.merge(oldEmpleadoIDOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getEmpleadoID());
            List<Usuario> usuarioListOld = persistentEmpleado.getUsuarioList();
            List<Usuario> usuarioListNew = empleado.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            if(usuarioListOld != null){
                for (Usuario usuarioListOldUsuario : usuarioListOld) {
                    if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its empleadoID field is not nullable.");
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            if(usuarioListNew != null){
                for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                    usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getUsuarioID());
                    attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
                }
            }
            
            usuarioListNew = attachedUsuarioListNew;
            empleado.setUsuarioList(usuarioListNew);
            empleado = em.merge(empleado);
            if(usuarioListNew != null){
                for (Usuario usuarioListNewUsuario : usuarioListNew) {
                    if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                        Empleado oldEmpleadoIDOfUsuarioListNewUsuario = usuarioListNewUsuario.getEmpleadoID();
                        usuarioListNewUsuario.setEmpleadoID(empleado);
                        usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                        if (oldEmpleadoIDOfUsuarioListNewUsuario != null && !oldEmpleadoIDOfUsuarioListNewUsuario.equals(empleado)) {
                            oldEmpleadoIDOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                            oldEmpleadoIDOfUsuarioListNewUsuario = em.merge(oldEmpleadoIDOfUsuarioListNewUsuario);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getEmpleadoID();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getEmpleadoID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Usuario> usuarioListOrphanCheck = empleado.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable empleadoID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
