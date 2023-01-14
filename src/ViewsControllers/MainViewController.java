package ViewsControllers;

import Resource.Utilities;
import Views.Login;
import Views.Panels.Control.Compras;
import Views.Panels.Control.Inventario;
import Views.Panels.Control.Productos;
import Views.Panels.Estadisticas.Resumenes;
import Views.Panels.Facturacion.Acciones;
import Views.Panels.Facturacion.Facturar;
import Views.Panels.Facturacion.FacturasDia;
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
    private JFrame Login;
    private JLabel UsuarioActual;
    private JLabel Facturacion;
    private JLabel Control;
    private JLabel Estadisticas;
    private JLabel Preferencias;
    private JLabel Ajustes;
    private JLabel CerrarSesion;
    private JTabbedPane Principal;
    private int PanelActivo = 0;

    public MainViewController(JFrame Instance, JFrame Login, JLabel UsuarioActual, JLabel Facturacion, JLabel Control, JLabel Estadisticas, JLabel Preferencias, JLabel Ajustes, JLabel CerrarSesion, JTabbedPane Principal) {
        this.Instance = Instance;
        this.Login = Login;
        this.UsuarioActual = UsuarioActual;
        this.Facturacion = Facturacion;
        this.Control = Control;
        this.Estadisticas = Estadisticas;
        this.Preferencias = Preferencias;
        this.Ajustes = Ajustes;
        this.CerrarSesion = CerrarSesion;
        this.Principal = Principal;    
    }
    
    public void cargarUsuario(String Usuario){
        UsuarioActual.setText(Usuario);
    }
    
    public void CargarPanel(int Panel){
        if(Panel != PanelActivo){
            Principal.removeAll();
            switch(Panel){
                case 1:

                    Facturar facturar = new Facturar();
                    FacturasDia facturasDia = new FacturasDia();
                    Acciones acciones = new Acciones();
                    
                    Icon FacturarIcon = new ImageIcon(getClass().getResource("/Icons/factura.png"));
                    Icon FacturaDiaIcon = new ImageIcon(getClass().getResource("/Icons/facturaDia.png"));
                    Icon AccionesIcon = new ImageIcon(getClass().getResource("/Icons/acciones.png"));

                    Principal.addTab("Facturar", FacturarIcon, facturar);
                    Principal.addTab("Facturas del dia", FacturaDiaIcon, facturasDia);
                    Principal.addTab("Acciones", AccionesIcon, acciones);
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
                    
                    Icon ResumenIcon = new ImageIcon(getClass().getResource("/Icons/resumenes.png"));
                    Icon TransaccionesIcon = new ImageIcon(getClass().getResource("/Icons/transacciones.png"));

                    Principal.addTab("Resumenes", ResumenIcon, resumenes);
                    Principal.addTab("Transacciones", TransaccionesIcon, null);
                    
                    break;
                case 4:
                    
                    Usuarios usuario = new Usuarios();
                    Empleados empleados = new Empleados();
                    Proveedores proveedores = new Proveedores();
                    Clientes clientes = new Clientes();

                    Icon UsuarioIcon = new ImageIcon(getClass().getResource("/Icons/usuarios.png"));
                    Icon EmpleadoIcon = new ImageIcon(getClass().getResource("/Icons/empleados.png"));
                    Icon ClienteIcon = new ImageIcon(getClass().getResource("/Icons/clientes.png"));
                    Icon ProveedorIcon = new ImageIcon(getClass().getResource("/Icons/proveedores.png"));

                    Principal.addTab("Clientes", ClienteIcon, clientes);
                    Principal.addTab("Proveedores", ProveedorIcon, proveedores);
                    Principal.addTab("Empleados", EmpleadoIcon, empleados);
                    Principal.addTab("Usuarios", UsuarioIcon, usuario);
                    
                    break;
                case 5:

                    Icon AjustesIcon = new ImageIcon(getClass().getResource("/Icons/ajustes.png"));

                    Principal.addTab("Ajustes", AjustesIcon, null);
                    
                    break;
            }
            PanelActivo = Panel;
        }
    }
    
    public void cerrasSesion(){
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
    
    public void activarBoton(JLabel label){
        desactivarBotones();
        label.setEnabled(true);
    }
}
