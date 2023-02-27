
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHilos {
    public static void main(String[] args){
        Runnable hilo = () -> {
            for(int i = 0; i < 100; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestHilos.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Hilo 1");
            }
        };
        new Thread(hilo).start();
        
        Runnable hilo2 = () -> {
            for(int i = 0; i < 100; i++){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestHilos.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Hilo 2");
            }
        };
        new Thread(hilo2).start();
        
        System.out.println("Terminado");
    }
}
