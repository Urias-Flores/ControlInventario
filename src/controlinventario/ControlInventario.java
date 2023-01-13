package controlinventario;

import Resource.Conection;
import Views.Login;
import javax.persistence.Query;
import main.Theme;

public class ControlInventario {

    public static void main(String[] args) {
        Theme.setup();
        Query query = Conection.CreateEntityManager().createEntityManager().createNativeQuery("SELECT 1");
        int result = Integer.parseInt(query.getResultList().get(0).toString());
        if(result == 1){
            new Login().setVisible(true);
        }else{
            System.err.println("La conexion con el servidor no se pudo establecer");
        }
    }
}
