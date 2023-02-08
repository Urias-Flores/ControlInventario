package ViewsControllers.Dialogs;

import Resource.Conection;
import Resource.LocalDataController;
import Resource.NoJpaConection;
import Views.Dialogs.LoadDialog;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LoadDialogViewController {
    
    private LoadDialog Instance;
    private JLabel Icons;
    private JLabel Texto;
    private JProgressBar Barra;
    
    public LoadDialogViewController(LoadDialog Instance, JLabel Icons, JLabel Texto, JProgressBar Barra) {
        this.Instance = Instance;
        this.Icons = Icons;
        this.Texto = Texto;
        this.Barra = Barra;
      
        testConections();
    }
    
    private void testConections(){
        Runnable run = ()->{
            try {
                if(testAll()){
                    Thread.sleep(1000);
                    Instance.setVisible(false);
                }else{
                    Texto.setForeground(new Color(185, 0, 0));
                    Texto.setText("¡Cancelando apertura de sistema!");
                    Thread.sleep(1000);
                    System.exit(0);
                }
            } catch (InterruptedException ex) {
                System.err.println("Error: "+ex.getMessage());
            }
        };
        new Thread(run).start();
    }
    
    private boolean testAll() throws InterruptedException{
        if(!testNoJpaConection()){
            Texto.setText("Error al conectar con la base de datos");
            Thread.sleep(1000);
            return false;
        }
        loadBar(0, 33);
        if(!testJpaConection()){
            Texto.setText("Error al intentar crear la persistencia de datos");
            Thread.sleep(1000);
            return false;
        }
        loadBar(33, 66);
        if(!testFileLocalConection()){
            Texto.setText("Error en la busqueda de archivo de datos local");
            Thread.sleep(1000);
            return false;
        }
        loadBar(66, 100);
        Icons.setIcon(new ImageIcon(getClass().getResource("/Icons/Completado.png")));
        Texto.setText("¡Todo listo!");
        return true;
    }
    
    private boolean testNoJpaConection(){
        NoJpaConection conectionNoJpa = new NoJpaConection();
        if(conectionNoJpa.getconec() != null){
            Texto.setText("¡Conexión con el servidor establecida!");
            return true;
        }
        return false;
    }
    
    private boolean testJpaConection(){
        Object result = Conection.createEntityManager().createNativeQuery("SELECT 1").getSingleResult();
        if(result != null){
            Texto.setText("¡Conexión con pesistencia creada!");
            return true;
        }
        return false;
    }
    
    private boolean testFileLocalConection(){
        LocalDataController ldc = new LocalDataController();
        if(ldc.getValue("User") != null){
            Texto.setText("¡Carga de informacion local completada!");
            return true;
        }
        return false;
    }
    
    private void loadBar(int init, int end) throws InterruptedException{
        for(int i = init; i <= end; i++){
            Barra.setValue(i);
            Thread.sleep(15);
        }
    }
}
