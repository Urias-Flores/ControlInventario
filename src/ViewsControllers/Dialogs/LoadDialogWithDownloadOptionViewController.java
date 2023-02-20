package ViewsControllers.Dialogs;

import Resource.Conection;
import Resource.LocalDataController;
import Resource.NoJpaConection;
import Resource.UpdateDataController;
import Views.Dialogs.LoadDialogWithDownloadOption;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LoadDialogWithDownloadOptionViewController {
    
    private LoadDialogWithDownloadOption Instance;
    private JLabel Icons;
    private JLabel Texto;
    private JProgressBar Barra;
    
    public LoadDialogWithDownloadOptionViewController(LoadDialogWithDownloadOption Instance, JLabel Icons, JLabel Texto, JProgressBar Barra) {
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
        testConectionVersion();
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
    
    private boolean testConectionVersion() throws InterruptedException{
        Texto.setText("Comprobando conexión a internet...");
        System.err.println("Pase por aqui");
        Thread.sleep(1000);
        int result = updateVersion();
        Thread.sleep(1000);
        if(result == 0){
            if(existNewVersion()){
                if(getUpdateFile() == 0){
                    updateAll();
                }
            }
        }
        return true;
    }
    
    private int updateVersion(){
        try {
            String url = "https://raw.githubusercontent.com/Urias-Flores/UnventoryInstaller/main/version_.txt";
            String file = "version_.txt";
            URLConnection conec = new URL(url).openConnection();

            conec.connect();
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(file);
            Texto.setText("Descargando version...");
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
            Texto.setText("URL de descarga de version no valida");
            return -2;
        } catch (IOException ex) {
            Texto.setText("Sin conexion a internet");
            return -1;
        }
        return 0;
    }
    
    private boolean existNewVersion(){
        try {
            InputStream actualVersion = new FileInputStream("version.txt");
            InputStream newVersion = new FileInputStream("version_.txt");
            
            Scanner actual = new Scanner(actualVersion);
            Scanner newV = new Scanner(newVersion);
            
            actualVersion.close();
            newVersion.close();
            
            Texto.setText("Comprobando nueva version...");
            if(actual.hasNextLine()){
                if(newV.hasNextLine()){
                    String versionActual = actual.nextLine();
                    String versionRecurrente = newV.nextLine();
                    if(!versionActual.equalsIgnoreCase(versionRecurrente)){
                        FileWriter escritor = new FileWriter(new File("version.txt"), true);
                        escritor.write(versionRecurrente);
                        escritor.close();
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Texto.setText("Archivos de versiones no encontrados");
            return false;
        } catch (IOException ex) {
            Logger.getLogger(LoadDialogWithDownloadOptionViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    private int getUpdateFile(){
        try {
            String url = "https://github.com/Urias-Flores/UnventoryInstaller/raw/main/update.db";
            String file = "update.db";
            URLConnection conec = new URL(url).openConnection();
            conec.connect();
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(file);
            Texto.setText("Descargando archivo de actualización...");
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
            Texto.setText("URL de descarga de archivo de actualización no valida");
            return -2;
        } catch (IOException ex) {
            Texto.setText("Se perdio la conexion al obtener archivo de actualización");
            return -1;
        }
        return 0;
    }
    
    private void updateAll(){
        ArrayList<String[]> updates = new UpdateDataController().getValues();
        if(updates != null){
            if(!updates.isEmpty()){
                updates.forEach(update -> {
                    if(update[0].equals("File")){
                        updateFile(update[1], update[2]);
                    }else{
                        executeQuery(update[2]);
                    }
                });
                
                updates.forEach(update -> {
                    if (update[1].equalsIgnoreCase("Unventory.exe")) {
                        System.exit(0);
                    }
                });
                new File("update.db").delete();
            }
        }
    }
    
    private int updateFile(String Name, String URL){
        try {
            URLConnection conec = new URL(URL).openConnection();

            conec.connect();
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(Name);
            int tamanoArchivo = conec.getContentLength();
            
            initDownload(in, out);
            updatePorcent(tamanoArchivo, Name);
            
            
        } catch (MalformedURLException ex) {
            Texto.setText("Archivo "+Name+" no pudo ser descargado, url no valido");
            return -2;
        } catch (IOException ex) {
            Texto.setText("Archivo "+Name+" no pudo ser descargado, sin conexión");
            return -1;
        }
        return 0;
    }
    
    private void executeQuery(String Query){
        
    }
    
    private void initDownload(InputStream in, OutputStream out){
        Runnable run = () -> {
            try {
                int b = 0;
                while (b != -1) {
                    b = in.read();
                    if(b != -1){
                        out.write(b);
                    }
                }
                
                in.close();
                out.close();
            } catch (IOException ex) {
                Texto.setText("Error al almacenar archivo descargado");
            }
        };
        new Thread(run).start();
    }
    
    private void updatePorcent(int tamano, String path){
        File file = new File(path);
        while( file.length() < tamano ){
            int porcent = (int) ((100 * file.length()) / tamano);
            System.out.println("Porcentaje: "+porcent);
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {

            }
        }
    }
    
    private void loadBar(int init, int end) throws InterruptedException{
        for(int i = init; i <= end; i++){
            Barra.setValue(i);
            Thread.sleep(15);
        }
    }
}
