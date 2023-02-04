package ViewsControllers.Dialogs;

import Controllers.ProductoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Categoria;
import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProductDialogViewController {
    
    ProductoJpaController controller = new ProductoJpaController(Conection.CreateEntityManager());
    
    JTextArea Descripcion;
    JTextField Marca;
    JTextField Categoria;
    JTextField Barra;
    JTextField Unidad;
    JTextField CantidadMinimo;
    JTextField PrecioCompra;
    JTextField PrecioVenta;
    JLabel Error;
        
    boolean Editing = false;

    public AddProductDialogViewController(JTextArea Descripcion, JTextField Marca, JTextField Categoria, JTextField Barra, JTextField Unidad, JTextField CantidadMinimo, JTextField PrecioCompra, JTextField PrecioVenta, JLabel Error) {
        this.Descripcion = Descripcion;
        this.Marca = Marca;
        this.Categoria = Categoria;
        this.Barra = Barra;
        this.Unidad = Unidad;
        this.CantidadMinimo = CantidadMinimo;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
        this.Error = Error;
    }
    
    public void cargarMarca(){
        Object[] values = Dialogs.ShowSelectMarcaDialog();
        if(Integer.parseInt(values[0].toString()) > -1 && !values[1].toString().isEmpty()){
            Marca.setText(values[1].toString());
            Marca.setName(values[0].toString());
            Marca.setForeground(Color.black);
        }
    }
    
    public void setProductoToEdit(int ProductoID){
        Producto producto = controller.findProducto(ProductoID);
        if(producto != null){
            Editing = true;
            Descripcion.setText(producto.getDescripcion());
            Descripcion.setName(String.valueOf(ProductoID));
            Marca.setName(producto.getMarcaID().getMarcaID().toString());
            Marca.setText(producto.getMarcaID().getNombre());
            Marca.setForeground(Color.BLACK);
            Categoria.setName(producto.getCategoriaID().getCategoriaID().toString());
            Categoria.setText(producto.getCategoriaID().getNombre());
            Categoria.setForeground(Color.BLACK);
            Barra.setText(producto.getBarra());
            Barra.setForeground(Color.BLACK);
            Unidad.setText(producto.getUnidad());
            Unidad.setForeground(Color.BLACK);
            CantidadMinimo.setText(String.valueOf(producto.getCantidadMinima()));
            CantidadMinimo.setForeground(Color.black);
            PrecioCompra.setText(String.valueOf(producto.getPrecioCompra()));
            PrecioCompra.setForeground(Color.black);
            PrecioVenta.setText(String.valueOf(producto.getPrecioVenta()));
            PrecioVenta.setForeground(Color.black);
        }
    }
    
    public void cargarCategoria(){
        Object[] values = Dialogs.ShowSelectCategoriaDialog();
        if(Integer.parseInt(values[0].toString()) > -1 && !values[1].toString().isEmpty()){
            Categoria.setText(values[1].toString());
            Categoria.setName(values[0].toString());
            Categoria.setForeground(Color.black);
        }
    }
    
    public boolean Insert(){
        if(Validate()){
            Producto producto = CrearObjectoProducto();
            controller.create(producto);
            return true;
        }
        return false;
    }
    
    public boolean Edit() {
        if(Validate()){
            try {
                Producto producto = CrearObjectoProducto();
                controller.edit(producto);
                return true;
            } catch (NonexistentEntityException | IllegalOrphanException ex) {
                System.err.println("Error: "+ex.getMessage());
                Dialogs.ShowMessageDialog("El producto esta ligado a otros datos, no puso ser monificado", Dialogs.ERROR_ICON);
                return false;
            } catch (Exception ex) {
                System.err.println("Error: "+ex.getMessage());
                Dialogs.ShowMessageDialog("Ha ocurrido un error inesperado al actualizar producto", Dialogs.ERROR_ICON);
                return false;
            }
        }
        return false;
    }
    
    private Producto CrearObjectoProducto(){
        Producto producto = new Producto();
        
        if(Descripcion.getName() != null){
                producto.setProductoID(Integer.valueOf(Descripcion.getName()));
        }
        producto.setDescripcion(Descripcion.getText());
        producto.setMarcaID(new Marca(Integer.valueOf(Marca.getName()), Marca.getText()));
        producto.setCategoriaID(new Categoria(Integer.valueOf(Categoria.getName()), Categoria.getText()));
        producto.setBarra(Barra.getForeground().equals(new Color(180, 180, 180)) || Barra.getText().isEmpty() ? null : Barra.getText());
        producto.setUnidad(Unidad.getText());
        producto.setCantidadMinima(Float.parseFloat(CantidadMinimo.getText().replace(",", "")));
        producto.setPrecioCompra(Float.parseFloat(PrecioCompra.getText().replace(",", "")));
        producto.setPrecioVenta(Float.parseFloat(PrecioVenta.getText().replace(",", "")));
        
        return producto;
    }
    
    public boolean Validate(){
        if(Descripcion.getText().isEmpty() || Descripcion.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La descripcion del producto es obligatoria");
            return false;
        }
        
        if(Marca.getName() == null || Marca.getName().isEmpty() || Marca.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La seleccion de una marca de producto es obligatoria");
            return false;
        }
        
        if(Categoria.getName() == null || Categoria.getName().isEmpty() || Categoria.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La seleccion de una categoria de producto es obligatoria");
            return false;
        }
        
        if(Unidad.getText().isEmpty() || Unidad.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El tipo de unidad del producto es obligatorio");
            return false;
        }
        
        //Validacion de cantidad
        if(CantidadMinimo.getText().isEmpty() || CantidadMinimo.getForeground().equals(new Color(180, 180, 180))){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La cantidad minima en inventario es obligatoria");
            return false;
        }
        try {
            float cantidadMinima = Float.parseFloat(CantidadMinimo.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La cantidad minima debe ser un numero");
            return false;
        }
        if(Float.parseFloat(CantidadMinimo.getText().replace(",", "")) < 0){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La cantidad minima debe ser mayor o igual a cero");
            return false;
        }
        
        //Validacion de precio de compra
        if(PrecioCompra.getText().isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El precio de compra del producto es obligatorio");
            return false;
        }
        try {
            float precioCompra = Float.parseFloat(PrecioCompra.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La precio de compra debe ser un numero");
            return false;
        }
        if(Float.parseFloat(PrecioCompra.getText().replace(",", "")) <= 0){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El precio de compra del producto debe ser mayor a cero");
        }
        
        //Validacion de precio de venta
        if(PrecioVenta.getText().isEmpty()){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El precio de venta del producto es obligatorio");
            return false;
        }
        try {
            float precioVenta = Float.parseFloat(PrecioVenta.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("La precio de venta debe ser un numero");
            return false;
        }
        if(Float.parseFloat(PrecioVenta.getText().replace(",", "")) <= 0){
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El precio de venta del producto debe ser mayor a cero");
        }
        
        return true;
    }
    
    public void setNumberFormat(JTextField textField){
        try {
            float valor = Float.parseFloat(textField.getText());
            textField.setText(getNumberFormat(valor));
        } catch (NumberFormatException e) {
            Error.setBackground(new Color(185, 0, 0));
            Error.setText("El valor ingresado debe ser un numero");
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
