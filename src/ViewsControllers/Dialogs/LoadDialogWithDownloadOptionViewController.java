package ViewsControllers.Dialogs;

import Resource.Conection;
import Resource.LocalConection;
import Resource.LocalDataController;
import Resource.NoJpaConection;
import Resource.UpdateDataController;
import Views.Dialogs.LoadDialogWithDownloadOption;
import java.awt.Color;
import java.awt.Desktop;
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
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class LoadDialogWithDownloadOptionViewController {
    
    private LoadDialogWithDownloadOption Instance;
    private JLabel Icons;
    private JLabel Texto;
    private JProgressBar Barra;
    private JLabel Porcentaje;
    private JLabel Version;
    
    public LoadDialogWithDownloadOptionViewController(LoadDialogWithDownloadOption Instance, JLabel Icons, JLabel Texto, JProgressBar Barra, JLabel Porcentaje,JLabel Version) {
        this.Instance = Instance;
        this.Icons = Icons;
        this.Texto = Texto;
        this.Barra = Barra;
        this.Porcentaje = Porcentaje;
        this.Version = Version;
        
        loadVersion();
        testConections();
    }
    
    private void loadVersion(){
        try {
            Scanner s = new Scanner(new File("version.txt"));
            s.hasNextLine();
            Version.setText("version "+s.nextLine());
        } catch (FileNotFoundException ex) {
            Texto.setText("No se encontro el archivo de version");
        }
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
    
    //Comprobando existencia de nuevaversion
    private boolean testConectionVersion() throws InterruptedException{
        Texto.setText("Comprobando conexión a internet...");
        Thread.sleep(1000);
        
        int result = updateVersion(); //<- Enviando a actualizar archivo que contiene el numero de la ultima version
        System.err.println("Resultado: "+result);
        Thread.sleep(1000);
        if(result == 0){ //<- En caso de que se alla podido descargar archivo que contiene la ultima verion retorna 0
            if(existNewVersion()){ //<- comparando version de archivo de descargado con archivo de version en sistema
                
                //Si la version del sistema es distintan a la version descargada mandar a actualizar archivo de actualizacion
                if(getUpdateFile() == 0){ 
                    // Se envia actualizar archivo que contiene informacion de la actualizacion
                    //(archivos que se actualizaran o nuevos archivos que agregar)
                    updateAll(); //<-Este metodo los leera del archivo de actualizacion
                }
            }
        }
        return true;
    }
    
    private int updateVersion() throws InterruptedException{
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
            Scanner actual = new Scanner(new File("version.txt"));
            Scanner newV = new Scanner(new File("version_.txt"));
            
            Texto.setText("Comprobando nueva version...");
            actual.hasNextLine();
            newV.hasNext();
            
            String versionActual = actual.nextLine();
            String versionRecurrente = newV.nextLine();

            System.out.println(versionActual + " < - > "+versionRecurrente);

            if(!versionActual.equalsIgnoreCase(versionRecurrente)){
                File versionFile = new File("version.txt");
                File currentVersionFile = new File("version_.txt");
                
                InputStream in = new FileInputStream(currentVersionFile);
                OutputStream out = new FileOutputStream(versionFile);
                int b = 0;
                while(b != -1){
                    b = in.read();
                    if(b != -1){
                        out.write(b);
                    }
                }
                return true;
            }
        } catch (FileNotFoundException ex) {
            Texto.setText("Archivos de versiones no encontrados");
            return false;
        } catch (IOException ex) {
            Texto.setText("Error al escribir archivo de version");
            return false;
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
        ArrayList<String[]> updates = new UpdateDataController().getValues(); //<- este metodo devuelve arreglo con informacion de cambio
        //Ejemplo: update[0] = "File"  o "Query" en caso de que se deseen hacer cambios en base de datos
        //update[1] = "reports/abono.jasper" o "actualizandoCliente" este contendria la ruta del archivo a modificar o agregar
        //update[2] = "www.github.com/Urias...." o "Alter table clientes..." contendria la direccion de descarga del archivo o query a ejecutar
        
        if(updates != null){ //<- Comporbando que realmente existan actualizaciones
            if(!updates.isEmpty()){ //<- Comporbando de nuevo por si acaso
                int contador = 1; //El contador es unicamente para saber por que numero de descarga va, en caso de ser varios
                for(String[] update : updates){ //Recorriendo lista de arreglos que contiene la informacion detallada arriba
                    Texto.setText("Descargando... ("+contador+"/"+updates.size()+")"); //Muestra el mensaje ejemp:  Descargando... (2/5) 
                    switch (update[0]) {
                        case "File":
                            //Comprobando que sea un archivo de actualizacion
                            updateFile(update[1], update[2]);//Este metodo recibe el nombre o direccion del archivo, y url de descarga
                            break;
                        case "Query":
                            //En el caso de que querer modificar la estructura de la base de datos
                            executeQuery(update[2]); //Aqui enviamos a executar el Query enviando el query obtenido del archivo de actualizacion
                            break;
                        case "LocalQuery":
                            //En el caso de que querer modificar la estructura de la base de datos local
                            executeLocalQuery(update[2]);
                            break;
                        default:
                            break;
                    }
                    contador += 1;
                }
                
                //Esto simplemente iterara para ver si se actualizo el programa como tal, para reiniciar con las actualizaciones aplicadas 
                updates.forEach(update -> {
                    if (update[1].equalsIgnoreCase("Unventory.exe")) {
                        Texto.setText("Reiniciando...");
                        try {
                            Desktop desktop = Desktop.getDesktop();
                            desktop.open(new File("Unventory.exe"));
                            Thread.sleep(1000);
                        } catch (InterruptedException | IOException ex) {}
                        System.exit(0);
                    }
                });
                
                //Se envia a eliminar archivo de informacion de actualizacion
                new File("update.db").delete();
            }
        }
    }
    
    private int updateFile(String Name, String URL){
        try {
            //Aqui cargo el url de descarga
            URLConnection conec = new URL(URL).openConnection();
            
            conec.connect();
            InputStream in = conec.getInputStream();
            OutputStream out = new FileOutputStream(Name);
            int tamanoArchivo = conec.getContentLength();
            
            //Aqui envio a actualizar en otro hilo
            initDownload(in, out);
            
            //Aqui actualizo el oprcentaje de descarga tomando el tamaño del archivo y relacionandolo al tamaño descargado 
            //hasta el momento obtengo el porcentaje
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
        try {
            PreparedStatement ps = new NoJpaConection().getconec().prepareStatement(Query);
            ps.execute();
            System.out.println("Executando: "+Query);
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
            Texto.setText("Error al ejecutar comando en base de datos");
        }
    }
    
    private void executeLocalQuery(String Query){
        try {
            PreparedStatement ps = new LocalConection().getconec().prepareStatement(Query);
            ps.execute();
            System.out.println("Executando: "+Query);
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
            Texto.setText("Error al ejecutar comando en base de datos");
        }
    }
    
    private void initDownload(InputStream in, OutputStream out){
        //Este runnable demarca la apertura de un nuevo hilo en java
        Runnable run = () -> { //Aqui se crea todo lo que se enviara a ejecutar en segundo plano
            try {
                //Aqui es donde se comienza a escribi el archivo descargado
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
        new Thread(run).start(); // y aqui se envia a ejecutar ese hilo
    }
    
    private void updatePorcent(int tamano, String path){
        File file = new File(path);
        //Este ira actualizando el porcentaje descargado y saldra del while una vez que el tamaño del archivo descargando alcanze 
        //el tamaño esperado
        while( file.length() < tamano ){
            int porcent = (int) ((100 * file.length()) / tamano);
            try {
                Barra.setValue(porcent);
                Porcentaje.setText(String.valueOf(porcent)+"%");
                Thread.sleep(15); //Este ira actualizando el porcentaje cada 15 milisegundos
            } catch (InterruptedException ex) { }
        }
    }
    
    private void loadBar(int init, int end) throws InterruptedException{
        for(int i = init; i <= end; i++){
            Barra.setValue(i);
            Porcentaje.setText(String.valueOf(i)+"%");
            Thread.sleep(15);
        }
    }
}
