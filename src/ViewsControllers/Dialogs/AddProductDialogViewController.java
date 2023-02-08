package ViewsControllers.Dialogs;

import Controllers.ProductoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Categoria;
import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddProductoDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddProductDialogViewController {
    
    private ProductoJpaController controller = new ProductoJpaController(Conection.createEntityManagerFactory());
    
    private AddProductoDialog Instance;
    private JTextArea Descripcion;
    private JTextField Marca;
    private JTextField Categoria;
    private JTextField Barra;
    private JTextField Unidad;
    private JTextField CantidadMinimo;
    private JTextField PrecioCompra;
    private JTextField PrecioVenta;
    private JLabel Error;
    private JLabel Cargando;
        
    private int ProductoID = 0;
    private boolean Editing = false;

    public AddProductDialogViewController(AddProductoDialog Instance, JTextArea Descripcion, JTextField Marca, JTextField Categoria, JTextField Barra, JTextField Unidad, JTextField CantidadMinimo, JTextField PrecioCompra, JTextField PrecioVenta, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Descripcion = Descripcion;
        this.Marca = Marca;
        this.Categoria = Categoria;
        this.Barra = Barra;
        this.Unidad = Unidad;
        this.CantidadMinimo = CantidadMinimo;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    public void loadBrand(){
        Object[] values = Dialogs.ShowSelectMarcaDialog();
        if(Integer.parseInt(values[0].toString()) > -1 && !values[1].toString().isEmpty()){
            Marca.setText(values[1].toString());
            Marca.setName(values[0].toString());
            Marca.setForeground(Color.black);
        }
    }
    
    public void loadCatgory(){
        Object[] values = Dialogs.ShowSelectCategoriaDialog();
        if(Integer.parseInt(values[0].toString()) > -1 && !values[1].toString().isEmpty()){
            Categoria.setText(values[1].toString());
            Categoria.setName(values[0].toString());
            Categoria.setForeground(Color.black);
        }
    }
    
    //Task
    public void setProductToEdit(int ProductoID){
        this.ProductoID = ProductoID;
        
        setLoad(true);
        Runnable run = () -> {
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
            setLoad(false);
        };
        new Thread(run).start();
        
    }
    
    public void save(){
        if(!Editing){ Insert(); } else { Edit(); }
    }
    
    //Task
    private void Insert(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                List<Producto> productos = controller.findProductoEntities();
                if(!validateBarCodeExist(productos)){
                    if(!validateDescriptioneExist(productos)){
                        Producto producto = createObjectProduct();
                        controller.create(producto);

                        Instance.setVisible(false);
                        Dialogs.ShowMessageDialog("¡El producto ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                    } else {
                        Error.setBackground(new Color(185, 0, 0));
                        Error.setText("La descripción del producto ya ha sido utilizada para otro de la misma marca");
                    }
                }else{
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El código de barra ingresado ya esta registrado en otro producto");
                }
                
                setLoad(false);
            };
            new Thread(run).start();
        }else{ Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    private void Edit() {
        if(validate()){
            setLoad(true);
            Runnable run = () ->{
                List<Producto> productos = controller.findProductoEntities();
                if(!validateBarCodeExist(productos)){
                    if(!validateDescriptioneExist(productos)){
                        try {
                            Producto producto = createObjectProduct();
                            controller.edit(producto);

                            Instance.setVisible(false);
                            Dialogs.ShowMessageDialog("", Dialogs.COMPLETE_ICON);
                        } catch (NonexistentEntityException | IllegalOrphanException ex) {
                            System.err.println("Error: "+ex.getMessage());
                            setLoad(false);
                            Dialogs.ShowMessageDialog("El producto esta ligado a otros datos, no pudo ser modificado", Dialogs.ERROR_ICON);
                        } catch (Exception ex) {
                            System.err.println("Error: "+ex.getMessage());
                            setLoad(false);
                            Dialogs.ShowMessageDialog("Ha ocurrido un error inesperado al modificar producto", Dialogs.ERROR_ICON);
                        }                        
                    } else {
                        Error.setBackground(new Color(185, 0, 0));
                        Error.setText("La descripción del producto ya ha sido utilizada para otro de la misma marca");
                    }
                } else { 
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El código de barra ingresado ya esta registrado en otro producto");
                }
                
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    private Producto createObjectProduct(){
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
    
    public boolean validate(){
        if(Descripcion.getText().isEmpty() || Descripcion.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("La descripcion del producto es obligatoria");
            return false;
        }
        
        if(Marca.getName() == null || Marca.getName().isEmpty() || Marca.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("La seleccion de una marca de producto es obligatoria");
            return false;
        }
        
        if(Categoria.getName() == null || Categoria.getName().isEmpty() || Categoria.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("La seleccion de una categoria de producto es obligatoria");
            return false;
        }
        
        if(Unidad.getText().isEmpty() || Unidad.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El tipo de unidad del producto es obligatorio");
            return false;
        }
        
        //Validacion de cantidad
        if(CantidadMinimo.getText().isEmpty() || CantidadMinimo.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("La cantidad minima en inventario es obligatoria");
            return false;
        }
        try {
            float cantidadMinima = Float.parseFloat(CantidadMinimo.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setText("La cantidad minima debe ser un numero");
            return false;
        }
        if(Float.parseFloat(CantidadMinimo.getText().replace(",", "")) < 0){
            Error.setText("La cantidad minima debe ser mayor o igual a cero");
            return false;
        }
        
        //Validacion de precio de compra
        if(PrecioCompra.getText().isEmpty()){
            Error.setText("El precio de compra del producto es obligatorio");
            return false;
        }
        try {
            float precioCompra = Float.parseFloat(PrecioCompra.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setText("La precio de compra debe ser un numero");
            return false;
        }
        if(Float.parseFloat(PrecioCompra.getText().replace(",", "")) <= 0){
            Error.setText("El precio de compra del producto debe ser mayor a cero");
        }
        
        //Validacion de precio de venta
        if(PrecioVenta.getText().isEmpty()){
            Error.setText("El precio de venta del producto es obligatorio");
            return false;
        }
        try {
            float precioVenta = Float.parseFloat(PrecioVenta.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setText("La precio de venta debe ser un numero");
            return false;
        }
        if(Float.parseFloat(PrecioVenta.getText().replace(",", "")) <= 0){
            Error.setText("El precio de venta del producto debe ser mayor a cero");
        }
        
        return true;
    }
    
    //Validacion de codigo de barras repetido
    private boolean validateBarCodeExist(List<Producto> productos){
        boolean barcodeExist = false;
        
        if(!productos.isEmpty()){
            if(!Barra.getText().isEmpty() || !Barra.getForeground().equals(new Color(180, 180, 180))){
                for(Producto producto : productos){
                    if(Barra.getText().equalsIgnoreCase(producto.getBarra())){
                        if(ProductoID == 0){
                            barcodeExist = true;
                        }else{
                            if(ProductoID != producto.getProductoID()){
                                barcodeExist = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        
        return barcodeExist;
    }
    
    //Validacion de descripciones iguales en productos
    private boolean validateDescriptioneExist(List<Producto> productos){
        boolean descriptionExist = false;
        
        if(!productos.isEmpty()){
            for(Producto producto : productos){
                if(Descripcion.getText().equalsIgnoreCase(producto.getDescripcion())){
                    if(Objects.equals(producto.getMarcaID().getMarcaID(), Integer.valueOf(Marca.getName()))){
                        descriptionExist = true;
                    }
                }
            }
        }
        
        return descriptionExist;
    }
    
    public void setNumberFormat(JTextField textField){
        try {
            float valor = Float.parseFloat(textField.getText());
            textField.setText(getNumberFormat(valor));
            Error.setBackground(Color.white);
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
