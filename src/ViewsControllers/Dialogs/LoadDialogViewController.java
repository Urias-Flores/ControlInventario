package ViewsControllers.Dialogs;

import Resource.Conection;
import Resource.NoJpaConection;
import Views.Dialogs.LoadDialog;
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
            NoJpaConection conectionNoJpa = new NoJpaConection();
            if(conectionNoJpa.getconec() != null){
                try {
                    Barra.setValue(60);
                    Object result = Conection.createEntityManager().createNativeQuery("SELECT 1").getSingleResult();
                    if(result != null){
                        Texto.setText("¡Conexion con el servidor establecida!");
                        Icons.setIcon(new ImageIcon(getClass().getResource("/Icons/Completado.png")));
                        Thread.sleep(1000);
                        Barra.setValue(100);
                        Thread.sleep(1000);
                        Instance.setVisible(false);
                    }
                } catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }else{
                try {
                    Texto.setText("¡No se pudo establecer la conexion!");
                    Icons.setIcon(new ImageIcon(getClass().getResource("/Icons/Error.png")));
                    Thread.sleep(1000);
                    Texto.setText("¡Cerrando!");
                    Thread.sleep(1000);
                    System.exit(0);
                } catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }
        };
        new Thread(run).start();
    }
}
