
import Controllers.ConfiguracionJpaController;
import Models.Configuracion;
import Resource.Code;
import Resource.Conection;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class TestConfiguracion {
    public static void main(String[] args){
        ConfiguracionJpaController config = new ConfiguracionJpaController(Conection.createEntityManagerFactory());
        
        Configuracion configuracion = config.findConfiguracion(1);
        Code code = new Code();
        
//        configuracion.setDato(code.codeString("admcontrol504@gmail.com"));
//        configuracion.setExtra(code.codeString("zxzsswlmpagelzcu"));
//        
//        try {
//            config.edit(configuracion);
            
        System.out.println(code.decodeString(configuracion.getDato()));
        System.out.println(code.decodeString(configuracion.getExtra()));
//        } catch (Exception ex) {
//            Logger.getLogger(TestConfiguracion.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
}
