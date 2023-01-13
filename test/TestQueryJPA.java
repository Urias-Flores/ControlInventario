import Resource.Conection;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class TestQueryJPA {
    public static void main(String[] args){
        EntityManagerFactory emf =  Conection.CreateEntityManager();
        EntityManager em = emf.createEntityManager();
        Query query = em.createNativeQuery("SELECT * FROM categoria");
        
        
        List<Object[]> l = query.getResultList();
        
        l.forEach(object -> {
            System.err.println(Arrays.toString(object));
        });
    }
}
