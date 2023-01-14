
package ViewsControllers.Dialogs;

import Controllers.InventarioJpaController;
import Controllers.InventariodetalleaccionesJpaController;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Models.Usuario;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AddInventarioDetalleAccionEliminacionViewController {
    private JTextArea Descripcion;
    private JLabel Error;

    private int InventarioID;
    
    public AddInventarioDetalleAccionEliminacionViewController(JTextArea Descripcion, JLabel Error) {
        this.Descripcion = Descripcion;
        this.Error = Error;
    }

    public void setInventarioID(int InventarioID) {
        this.InventarioID = InventarioID;
    }
    
    public boolean InsertAccion(){
        if(validate()){
            InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.CreateEntityManager());
            Inventariodetalleacciones inventariodetalle = createObjectInventarioDetalle();
            
            controller.create(inventariodetalle);
            
            return true;
        }
        return false;
    }
    
    private Inventariodetalleacciones createObjectInventarioDetalle(){
        Inventariodetalleacciones inventariodetalle = new Inventariodetalleacciones();
        Inventario inventario = new InventarioJpaController(Conection.CreateEntityManager()).findInventario(InventarioID);
        
        inventariodetalle.setProductoID(inventario.getProductoID());
        inventariodetalle.setUsuarioID(Utilities.getUsuarioActual());
        inventariodetalle.setFecha(Utilities.getDate());
        inventariodetalle.setHora(Utilities.getTime());
        inventariodetalle.setDescripcion(Descripcion.getText());
        inventariodetalle.setAccion("E");
        inventariodetalle.setExistenciaPrevia(0);
        inventariodetalle.setCantidadModificada(0);
        
        return inventariodetalle;
    }
    
    private boolean validate(){
        if(Descripcion.getText().isEmpty()){
            Error.setText("La justifica de eliminacion es obligatoria");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        return true;
    }
}
