package ViewsControllers.Dialogs;

import Controllers.InventarioJpaController;
import Controllers.InventariodetalleaccionesJpaController;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddInventarioDetalleViewController {
    private JTextArea Descripcion;
    private JTextField Cantidad;
    private JTextField ExistenciaPrevia;
    private JTextField ExistenciaResultante;
    private JLabel Error;
    
    private int ProductoID;

    public AddInventarioDetalleViewController
        (JTextArea Descripcion, JTextField Cantidad, JTextField ExistenciaPrevia, JTextField ExistenciaResultante, JLabel Error) {
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.ExistenciaPrevia = ExistenciaPrevia;
        this.ExistenciaResultante = ExistenciaResultante;
        this.Error = Error;
    }
        
    public boolean InsertInventarioDetalle(){
        if(validateDescripcion()){
            if(validate()){
                InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.CreateEntityManager());
                Inventariodetalleacciones inventarioDetalle = createObjectInventarioDetalle();
                
                controller.create(inventarioDetalle);
                return true;
            }
        }
        return false;
    }
    
    public Inventariodetalleacciones createObjectInventarioDetalle(){
        Inventariodetalleacciones inventarioDetalle = new Inventariodetalleacciones();
        
        inventarioDetalle.setProductoID(new Producto(ProductoID));
        inventarioDetalle.setUsuarioID(Utilities.getUsuarioActual());
        inventarioDetalle.setFecha(Utilities.getDate());
        inventarioDetalle.setHora(Utilities.getTime());
        inventarioDetalle.setDescripcion(Descripcion.getText());
        inventarioDetalle.setAccion("M");
        inventarioDetalle.setExistenciaPrevia(Float.parseFloat(ExistenciaPrevia.getText()));
        inventarioDetalle.setCantidadModificada(Float.parseFloat(Cantidad.getText()));
        
        return inventarioDetalle;
    }
    
    public void CargarCantidad(int InventarioID){
        Object cantidad = Conection.CreateEntityManager().createEntityManager()
                .createNativeQuery("SELECT Cantidad FROM inventario WHERE InventarioID = "+InventarioID)
                .getSingleResult();
        
        Inventario inventario = new InventarioJpaController(Conection.CreateEntityManager()).findInventario(InventarioID);
        ProductoID = inventario.getProductoID().getProductoID();
        ExistenciaPrevia.setText(getNumberFormat(Float.parseFloat(cantidad.toString())));
    }
    
    public void updateCantidad(){
        if(validate()){
            float cantidadPrevia = Float.parseFloat(ExistenciaPrevia.getText().replace(",", ""));
            float cantidadResultante = Float.parseFloat(ExistenciaResultante.getText().replace(",", ""));
            
            Cantidad.setForeground(Color.black);
            Cantidad.setText(getNumberFormat(cantidadResultante - cantidadPrevia));
        }
    }
    
    public void updateExistenciaResultante(){
        if(validate()){
            float cantidadPrevia = Float.parseFloat(ExistenciaPrevia.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            
            ExistenciaResultante.setForeground(Color.black);
            ExistenciaResultante.setText(getNumberFormat(cantidadPrevia + cantidad));
        }
    }
    
    private boolean validateDescripcion(){
        if(Descripcion.getText().isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La justificacion de la modificacion es obligatoria");
            return false;
        }
        return true;
    }
    
    private boolean validate(){
        if(Cantidad.getText().isEmpty() || Cantidad.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La cantidad de modificacion es obligatoria");
            return false;
        }else{
            Error.setBackground(Color.white);
        }
        try {
            float cantidad = Float.parseFloat(Cantidad.getText());
            Error.setBackground(Color.white);
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La cantidad de modificacion debe ser un numero");
            return false;
        }
        
        if(ExistenciaResultante.getText().isEmpty() || ExistenciaResultante.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La existencia resultante de modificacion es obligatoria");
            return false;
        }else{
            Error.setBackground(Color.white);
        }
        try {
            float existenciaResultante = Float.parseFloat(ExistenciaResultante.getText());
            Error.setBackground(Color.white);
            if(existenciaResultante < 0){
                Error.setBackground(new Color(185, 0, 0));
                Error.setText("La existencia resultante debe ser mayor a 0");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La existencia resultante debe ser un numero");
            return false;
        }
        
        return true;
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
