package ViewsControllers;

import Resource.LocalDataController;
import Resource.Utilities;
import Views.Login;
import Views.Panels.Ajustes.Email;
import Views.Panels.Ajustes.Empresa;
import Views.Panels.Ajustes.Reportes;
import Views.Panels.Control.Compras;
import Views.Panels.Control.Inventario;
import Views.Panels.Control.Productos;
import Views.Panels.Estadisticas.Resumenes;
import Views.Panels.Estadisticas.Acciones;
import Views.Panels.Facturacion.Arqueo;
import Views.Panels.Facturacion.Facturar;
import Views.Panels.Facturacion.FacturasDia;
import Views.Panels.Facturacion.Gasto;
import Views.Panels.Preferencias.Clientes;
import Views.Panels.Preferencias.Empleados;
import Views.Panels.Preferencias.Proveedores;
import Views.Panels.Preferencias.Usuarios;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class MainViewController {
    private JFrame Instance;
    private JLabel UsuarioActual;
    private JLabel Notificaciones;
    private JLabel Facturacion;
    private JLabel Control;
    private JLabel Estadisticas;
    private JLabel Preferencias;
    private JLabel Ajustes;
    private JLabel CerrarSesion;
    private JTabbedPane Principal;
    private int PanelActivo = 0;

    public MainViewController(JFrame Instance, JLabel UsuarioActual, JLabel Notificaciones,JLabel Facturacion, JLabel Control, JLabel Estadisticas, JLabel Preferencias, JLabel Ajustes, JLabel CerrarSesion, JTabbedPane Principal) {
        this.Instance = Instance;
        this.UsuarioActual = UsuarioActual;
        this.Notificaciones = Notificaciones;
        this.Facturacion = Facturacion;
        this.Control = Control;
        this.Estadisticas = Estadisticas;
        this.Preferencias = Preferencias;
        this.Ajustes = Ajustes;
        this.CerrarSesion = CerrarSesion;
        this.Principal = Principal;
        
        //Cargando nombre del usuario actual en la barra lateral
        loadUser(Utilities.getUsuarioActual().getNombre());
        
        //Activando tarea para el chequeo de cambios en la notificaciones
        updateNotifications();
    }
    
    private void loadUser(String Usuario){
        UsuarioActual.setText(Usuario);
    }
    
    public void loadPanel(int Panel){
        if(Panel != PanelActivo){
            Principal.removeAll();
            switch(Panel){
                case 1:

                    Facturar facturar = new Facturar();
                    FacturasDia facturasDia = new FacturasDia();
                    Gasto gastos = new Gasto();
                    Arqueo arqueos = new Arqueo();
                    
                    Icon FacturarIcon = new ImageIcon(getClass().getResource("/Icons/factura.png"));
                    Icon FacturaDiaIcon = new ImageIcon(getClass().getResource("/Icons/facturaDia.png"));
                    Icon ArqueoIcon = new ImageIcon(getClass().getResource("/Icons/arqueo.png"));
                    Icon GastoIcon = new ImageIcon(getClass().getResource("/Icons/gasto.png"));
                    
                    Principal.addTab("Facturar", FacturarIcon, facturar);
                    Principal.addTab("Facturas del dia", FacturaDiaIcon, facturasDia);
                    Principal.addTab("Gastos", GastoIcon, gastos);
                    Principal.addTab("Arqueos", ArqueoIcon, arqueos);
                    break;
                case 2:
                    
                    Productos productos = new Productos();
                    Inventario inventario = new Inventario();
                    Compras compras = new Compras();

                    Icon InventarioIcon = new ImageIcon(getClass().getResource("/Icons/inventario.png"));
                    Icon ProductoIcon = new ImageIcon(getClass().getResource("/Icons/producto.png"));
                    Icon CompraIcon = new ImageIcon(getClass().getResource("/Icons/compra.png"));

                    Principal.addTab("Inventario", InventarioIcon, inventario);
                    Principal.addTab("Productos", ProductoIcon, productos);
                    Principal.addTab("Facturar compra", CompraIcon, compras);
                    
                    break;
                case 3:

                    Resumenes resumenes = new Resumenes();
                    Acciones acciones = new Acciones();
                    
                    Icon ResumenIcon = new ImageIcon(getClass().getResource("/Icons/grafica.png"));
                    Icon TransaccionesIcon = new ImageIcon(getClass().getResource("/Icons/transacciones.png"));

                    Principal.addTab("Graficas", ResumenIcon, resumenes);
                    Principal.addTab("Transacciones", TransaccionesIcon, acciones);
                    
                    break;
                case 4:
                    Proveedores proveedores = new Proveedores();
                    Clientes clientes = new Clientes();

                    Icon UsuarioIcon = new ImageIcon(getClass().getResource("/Icons/usuarios.png"));
                    Icon EmpleadoIcon = new ImageIcon(getClass().getResource("/Icons/empleados.png"));
                    Icon ClienteIcon = new ImageIcon(getClass().getResource("/Icons/clientes.png"));
                    Icon ProveedorIcon = new ImageIcon(getClass().getResource("/Icons/proveedores.png"));
                    
                    Principal.addTab("Clientes", ClienteIcon, clientes);
                    Principal.addTab("Proveedores", ProveedorIcon, proveedores);
                    
                    if(Utilities.getUsuarioActual().getCargo().equals("A")){
                        Usuarios usuario = new Usuarios();
                        Empleados empleados = new Empleados();
                        Principal.addTab("Empleados", EmpleadoIcon, empleados);
                        Principal.addTab("Usuarios", UsuarioIcon, usuario);
                    }
                    
                    break;
                case 5:
                    //Para ajustes generales, en caso de requerirse a futuro
                    //General general = new General();
                    Reportes reportes = new Reportes();
                    Email email = new Email();
                    Empresa empresa = new Empresa();
                    
                    //Icon AjustesGeneralIcon = new ImageIcon(getClass().getResource("/Icons/ajustes.png"));
                    Icon AjustesReportesIcon = new ImageIcon(getClass().getResource("/Icons/reporte.png"));
                    Icon AjustesEmailIcon = new ImageIcon(getClass().getResource("/Icons/correo.png"));
                    Icon AjustesEmpresaIcon = new ImageIcon(getClass().getResource("/Icons/empresa.png"));
                    
                    //Principal.addTab("General", AjustesGeneralIcon, general);
                    Principal.addTab("Reportes", AjustesReportesIcon, reportes);
                    Principal.addTab("Correo electronico", AjustesEmailIcon, email);
                    Principal.addTab("Empresa", AjustesEmpresaIcon, empresa);
                    
                    break;
            }
            PanelActivo = Panel;
        }
    }
    
    public void cerrasSesion(){
        Utilities.setRunProcess(false);
        Login login = new Login();
        login.setVisible(true);
        Instance.setVisible(false);
    }
    
    public void desactivarBotones(){
        Facturacion.setEnabled(false); Facturacion.setForeground(new Color(230, 230, 230));
        Control.setEnabled(false); Control.setForeground(new Color(230, 230, 230));
        Estadisticas.setEnabled(false); Estadisticas.setForeground(new Color(230, 230, 230));
        Preferencias.setEnabled(false); Preferencias.setForeground(new Color(230, 230, 230));
        Ajustes.setEnabled(false); Ajustes.setForeground(new Color(230, 230, 230));
        CerrarSesion.setEnabled(false); CerrarSesion.setForeground(new Color(230, 230, 230));
    }
    
    public void enableButton(JLabel label){
        desactivarBotones();
        label.setEnabled(true);
    }
    
    private void updateNotifications(){
        Utilities.setRunProcess(true);
        Runnable run = ()->{
            while(Utilities.isRunProcess()){
                Utilities.setStateProcess(true);
                try {
                    LocalDataController ldc = new LocalDataController();
                    if(ldc.checkCambios()){
                        Notificaciones.setIcon(new ImageIcon(getClass().getResource("/Icons/notificacionesActiva.png")));
                        ldc.updateNotificaciones();
                    }
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }
            Utilities.setStateProcess(false);
        };
        new Thread(run).start();
    }
    
    public void waitCloseProcess(){
        Runnable run = ()->{
            while(true){
                try {
                    if(!Utilities.isStateProcess()){
                        System.exit(0);
                    }
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }
        };
        new Thread(run).start();
    }
}
