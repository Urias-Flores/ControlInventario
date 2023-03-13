
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestOsCommand {
    public static void main(String args[]) throws InterruptedException{
        try {
            Process process = Runtime.getRuntime().exec("NET START MYSQL80");
            process.waitFor();
            InputStream in = process.getInputStream();
            OutputStream out = new FileOutputStream("out.txt");
            
            int b;
            while((b = in.read()) != -1){
                System.err.println("b = "+b);
                if(b != -1){
                    out.write(b);
                }
            }
            
            System.out.println("Listo!!");
        } catch (IOException ex) {
            Logger.getLogger(TestOsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
