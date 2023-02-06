package ViewsControllers.Dialogs;

import Controllers.InventariodetalleaccionesJpaController;
import Models.Inventariodetalleacciones;
import Resource.Conection;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class InfoInventarioAccionViewController {
    private JLabel NoAccion;
    private JLabel TipoAccion;
    private JLabel Fecha;
    private JLabel Hora;
    private JLabel Usuario;
    private JLabel Empleado;
    private JLabel Producto;
    private JTextArea Descripcion;
    private JLabel ExistenciaPrevia;
    private JLabel CantidadModificada;
    private JLabel CantidadResultante;

    public InfoInventarioAccionViewController(JLabel NoAccion, JLabel TipoAccion, JLabel Fecha, JLabel Hora, JLabel Usuario, JLabel Empleado, JLabel Producto, JTextArea Descripcion, JLabel ExistenciaPrevia, JLabel CantidadModificada, JLabel CantidadResultante) {
        this.NoAccion = NoAccion;
        this.TipoAccion = TipoAccion;
        this.Fecha = Fecha;
        this.Hora = Hora;
        this.Usuario = Usuario;
        this.Empleado = Empleado;
        this.Producto = Producto;
        this.Descripcion = Descripcion;
        this.ExistenciaPrevia = ExistenciaPrevia;
        this.CantidadModificada = CantidadModificada;
        this.CantidadResultante = CantidadResultante;
    }
    
    public void CargarAccion(int AccionID){
        InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.createEntityManagerFactory());
        Inventariodetalleacciones accion = controller.findInventariodetalleacciones(AccionID);
        
        NoAccion.setText(String.valueOf(accion.getInventarioDetalleAccionesID()));
        TipoAccion.setText(accion.getAccion().equals("E") ? "Eliminacion" : "Modificacion");
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
        Fecha.setText(sdf.format(accion.getFecha()));
        
        sdf = new SimpleDateFormat("HH:mm:ss");
        Hora.setText(sdf.format(accion.getHora()));
        
        Usuario.setText(accion.getUsuarioID().getNombre());
        Empleado.setText(accion.getUsuarioID().getEmpleadoID().getNombre() +" "+ accion.getUsuarioID().getEmpleadoID().getApellido());
        Producto.setText(accion.getProductoID().getDescripcion() +", "+accion.getProductoID().getMarcaID().getNombre());
        Descripcion.setText(accion.getDescripcion());
        ExistenciaPrevia.setText(String.valueOf(accion.getExistenciaPrevia()));
        CantidadModificada.setText(String.valueOf(accion.getCantidadModificada()));
        
        float cantidadResultante = accion.getExistenciaPrevia() + accion.getCantidadModificada();
        CantidadResultante.setText(String.valueOf(cantidadResultante));
    }
}
