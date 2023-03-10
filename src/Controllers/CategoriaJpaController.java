package Controllers;

import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Categoria;
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

public class CategoriaJpaController implements Serializable {

    public CategoriaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categoria categoria) {
        if (categoria.getProductoList() == null) {
            categoria.setProductoList(new ArrayList<Producto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Producto> attachedProductoList = new ArrayList<Producto>();
            for (Producto productoListProductoToAttach : categoria.getProductoList()) {
                productoListProductoToAttach = em.getReference(productoListProductoToAttach.getClass(), productoListProductoToAttach.getProductoID());
                attachedProductoList.add(productoListProductoToAttach);
            }
            categoria.setProductoList(attachedProductoList);
            em.persist(categoria);
            for (Producto productoListProducto : categoria.getProductoList()) {
                Categoria oldCategoriaIDOfProductoListProducto = productoListProducto.getCategoriaID();
                productoListProducto.setCategoriaID(categoria);
                productoListProducto = em.merge(productoListProducto);
                if (oldCategoriaIDOfProductoListProducto != null) {
                    oldCategoriaIDOfProductoListProducto.getProductoList().remove(productoListProducto);
                    oldCategoriaIDOfProductoListProducto = em.merge(oldCategoriaIDOfProductoListProducto);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categoria categoria) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria persistentCategoria = em.find(Categoria.class, categoria.getCategoriaID());
            List<Producto> productoListOld = persistentCategoria.getProductoList();
            List<Producto> productoListNew = categoria.getProductoList();
            List<String> illegalOrphanMessages = null;
            if(productoListOld != null){
                for (Producto productoListOldProducto : productoListOld) {
                    if (!productoListNew.contains(productoListOldProducto)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<>();
                        }
                        illegalOrphanMessages.add("You must retain Producto " + productoListOldProducto + " since its categoriaID field is not nullable.");
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
            categoria.setProductoList(productoListNew);
            categoria = em.merge(categoria);
            if(productoListNew != null){
                for (Producto productoListNewProducto : productoListNew) {
                    if (!productoListOld.contains(productoListNewProducto)) {
                        Categoria oldCategoriaIDOfProductoListNewProducto = productoListNewProducto.getCategoriaID();
                        productoListNewProducto.setCategoriaID(categoria);
                        productoListNewProducto = em.merge(productoListNewProducto);
                        if (oldCategoriaIDOfProductoListNewProducto != null && !oldCategoriaIDOfProductoListNewProducto.equals(categoria)) {
                            oldCategoriaIDOfProductoListNewProducto.getProductoList().remove(productoListNewProducto);
                            oldCategoriaIDOfProductoListNewProducto = em.merge(oldCategoriaIDOfProductoListNewProducto);
                        }
                    }
                }
            }
            em.getTransaction().commit();
        } catch (IllegalOrphanException ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categoria.getCategoriaID();
                if (findCategoria(id) == null) {
                    throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.");
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
            Categoria categoria;
            try {
                categoria = em.getReference(Categoria.class, id);
                categoria.getCategoriaID();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categoria with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Producto> productoListOrphanCheck = categoria.getProductoList();
            for (Producto productoListOrphanCheckProducto : productoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Categoria (" + categoria + ") cannot be destroyed since the Producto " + productoListOrphanCheckProducto + " in its productoList field has a non-nullable categoriaID field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(categoria);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categoria> findCategoriaEntities() {
        return findCategoriaEntities(true, -1, -1);
    }

    public List<Categoria> findCategoriaEntities(int maxResults, int firstResult) {
        return findCategoriaEntities(false, maxResults, firstResult);
    }

    private List<Categoria> findCategoriaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categoria.class));
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

    public Categoria findCategoria(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categoria> rt = cq.from(Categoria.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
