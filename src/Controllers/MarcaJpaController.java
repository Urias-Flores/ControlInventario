package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Marca;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Models.Producto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class MarcaJpaController implements Serializable {

    public MarcaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Marca marca) {
        if (marca.getProductoList() == null) {
            marca.setProductoList(new ArrayList<>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<>();
            for (Producto productoListProductoToAttach : marca.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoID());
                attachedProductoList.add(productoListProductoToAttach);
            }
            marca.setProductoList(attachedProductoList);
            em.persist(marca);
            for (Producto productoListProducto : marca.getProductoList()) {
                Marca oldMarcaIDOfProductoListProducto = productoListProducto.getMarcaID();
                productoListProducto.setMarcaID(marca);
                productoListProducto = em.merge(productoListProducto);
                if (oldMarcaIDOfProductoListProducto != null) {
                    oldMarcaIDOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldMarcaIDOfProductoListProducto = em.merge(oldMarcaIDOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Marca marca) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Marca persistentMarca = em.find(Marca.class, marca.getMarcaID());
            List<Producto> productoListOld = persistentMarca.getProductoList();
            List<Producto> productoListNew = marca.getProductoList();
            List<String> illegalOrphanMessages = null;
            if(productoListOld != null){
                for (Producto productoListOldProducto : productoListOld) {
                    if (!productoListNew.contains(productoListOldProducto)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<>();
                        }
                        illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its marcaID field is not nullable.");
                    }
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Producto> attachedProductoListNew = new ArrayList<>();
            if(productoListNew != null){
                for (Producto productoListNewProductoToAttach : productoListNew) {
                    productoListNewProductoToAttach = em.getReference(productoListNewProductoToAttach.getClass(), productoListNewProductoToAttach.getProductoID());
                    attachedProductoListNew.add(productoListNewProductoToAttach);
                }
            }
            productoListNew = attachedProductoListNew;
            marca.setProductoList(productoListNew);
            marca = em.merge(marca);
            if(productoListNew != null){
                for (Producto productoListNewProducto : productoListNew) {
                    if (!productoListOld.contains(productoListNewProducto)) {
                        Marca oldMarcaIDOfProductoListNewProducto = productoListNewProducto.getMarcaID();
                        productoListNewProducto.setMarcaID(marca);
                        productoListNewProducto = em.merge(productoListNewProducto);
                        if (oldMarcaIDOfProductoListNewProducto != null && !oldMarcaIDOfProductoListNewProducto.equals(marca)) {
                            oldMarcaIDOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                            oldMarcaIDOfProductoListNewProducto = em.merge(oldMarcaIDOfProductoListNewProducto);
                        }
                    }
                }    
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = marca.getMarcaID();
                if (findMarca(id) == null) {
                    throw new NonexistentEntityException("The marca with id " + id + " no longer exists.");
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
            Marca marca;
            try {
                marca = em.getReference(Marca.class, id);
                marca.getMarcaID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The marca with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = marca.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<>();
                }
                illegalOrphanMessages.add("This Marca (" + marca + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable marcaID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(marca);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Marca> findMarcaEntities() {
        return findMarcaEntities(true, -1, -1);
    }

    public List<Marca> findMarcaEntities(int maxResults, int firstResult) {
        return findMarcaEntities(false, maxResults, firstResult);
    }

    private List<Marca> findMarcaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Marca.class));
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

    public Marca findMarca(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Marca.class, id);
        } finally {
            em.close();
        }
    }

    public int getMarcaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Marca> rt = cq.from(Marca.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
