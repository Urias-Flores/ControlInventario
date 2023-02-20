
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDownload {
    public static void main(String[] main){
     
        try {
            String url = "https://github.com/Urias-Flores/UnventoryInstaller/raw/main/Unventory.exe";
            String file = "Unventory.exe";
            URLConnection conec = new URL(url).openConnection();
            
            conec.connect();
            System.out.println("Conexion realizada");
            
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(file);
            
            System.out.println("Iniciando descarga");
            int totalDescarga = conec.getContentLength();
            System.out.println("Tamaño: "+conec.getContentLength()/1000000+" Mb");
            
            getVersion();
            existNewVersion();
            //startDownload(in, out);
            //printPorcent(totalDescarga, file);
            
            //in.close();
            //out.close();
           
        } catch (MalformedURLException ex) {
            System.err.println("Url ingresado no valida");
        } catch (IOException ex) {
            System.err.println("Sin conexion a internet");
        }
    }
    
    private static void startDownload(InputStream in, OutputStream out){
        Runnable run = () -> {
            try {
                int b = 0;
                while (b != -1) {
                    b = in.read();
                    if(b != -1){
                        out.write(b);
                    }
                }
                System.out.println("Descarga completa");
                in.close();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(TestDownload.class.getName()).log(Level.SEVERE, null, ex);
            }
        };
        new Thread(run).start();
    }
    
    private static void updatePorcent(int tamano, String path){
        Runnable run = () -> {
            File file = new File(path);
            while( file.length() < tamano ){
                int porcent = (int) ((100 * file.length()) / tamano);
                System.out.println("Porcentaje: "+porcent);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(TestDownload.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        new Thread(run).start();
    }
    
    private static void getVersion(){
        try {
            String url = "https://raw.githubusercontent.com/Urias-Flores/UnventoryInstaller/main/version.txt";
            String file = "version_.txt";
            URLConnection conec = new URL(url).openConnection();
            
            conec.connect();
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(file);

            int b = 0;
            while (b != -1) {
                b = in.read();
                if(b != -1){
                    out.write(b);
                }
            }
            
            in.close();
            out.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(TestDownload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static boolean existNewVersion(){
        try {
            InputStream actualVersion = new FileInputStream("version.txt");
            InputStream newVersion = new FileInputStream("version_.txt");
            
            Scanner actual = new Scanner(actualVersion);
            Scanner newV = new Scanner(newVersion);
            
            if(actual.hasNextLine()){
                System.out.println("Version Actual: "+actual.nextLine());
                if(newV.hasNextLine()){
                    System.out.println("Version Online: "+newV.nextLine());
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestDownload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
