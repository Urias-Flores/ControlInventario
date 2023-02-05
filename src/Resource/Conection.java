package Resource;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conection {
    public static EntityManagerFactory emf = null;
    
    public static EntityManagerFactory CreateEntityManager(){
        try{
            if(emf == null){
                emf = Persistence.createEntityManagerFactory("ControlInventarioPU");
                System.out.println("Test external conection: Enlace de conexion con servidor creado");
            }
        }catch(Exception ex){
            System.err.print("Error: "+ex.getMessage());
        }
        return emf;
    }
    
    public static void Disconnect(EntityManager em){
        if(em != null){
            em.close();
            emf.close();
            System.out.println("Test external conection: La conexion con el servidor ha sido cerrada");
        }
    }
}
