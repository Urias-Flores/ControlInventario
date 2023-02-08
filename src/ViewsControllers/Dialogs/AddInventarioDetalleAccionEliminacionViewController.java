
package ViewsControllers.Dialogs;

import Controllers.InventarioJpaController;
import Controllers.InventariodetalleaccionesJpaController;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddInventarioDetalleEliminacion;
import Views.Dialogs.Dialogs;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class AddInventarioDetalleAccionEliminacionViewController {
    private AddInventarioDetalleEliminacion Instance;
    private JTextArea Descripcion;
    private JLabel Error;
    private JLabel Cargando;
    
    private int InventarioID;
    
    public AddInventarioDetalleAccionEliminacionViewController(AddInventarioDetalleEliminacion Instance,JTextArea Descripcion, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Descripcion = Descripcion;
        this.Error = Error;
        this.Cargando = Cargando;
    }

    public void setInventarioID(int InventarioID) {
        this.InventarioID = InventarioID;
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void insertAction(){
        if(validate()){
            setLoad(true);
            Runnable run = ()->{
                InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.createEntityManagerFactory());
                Inventariodetalleacciones inventariodetalle = createObjectInventoryDetail();
                controller.create(inventariodetalle);
                
                setLoad(false);
                Instance.setVisible(false);
                Dialogs.ShowMessageDialog("¡La eliminación ha sido exitosa!", Dialogs.COMPLETE_ICON);
            };
            new Thread(run).start();
        }
    }
    
    private Inventariodetalleacciones createObjectInventoryDetail(){
        Inventariodetalleacciones inventariodetalle = new Inventariodetalleacciones();
        Inventario inventario = new InventarioJpaController(Conection.createEntityManagerFactory()).findInventario(InventarioID);
        
        if(inventario != null){
            inventariodetalle.setProductoID(inventario.getProductoID());
            inventariodetalle.setUsuarioID(Utilities.getUsuarioActual());
            inventariodetalle.setFecha(Utilities.getDate());
            inventariodetalle.setHora(Utilities.getTime());
            inventariodetalle.setDescripcion(Descripcion.getText());
            inventariodetalle.setAccion("E");
            inventariodetalle.setExistenciaPrevia(0);
            inventariodetalle.setCantidadModificada(0);
        }
        
        return inventariodetalle;
    }
    
    private boolean validate(){
        if(Descripcion.getText().isEmpty()){
            Error.setText("La justifica de eliminación es obligatoria");
            return false;
        }
        if(Descripcion.getText().length() > 65535){
            Error.setText("La justificacion de eliminación supera el limite de caracteres");
        }
        return true;
    }
}
