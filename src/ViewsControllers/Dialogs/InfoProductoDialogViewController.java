package ViewsControllers.Dialogs;

import Controllers.ProductoJpaController;
import Models.Producto;
import Resource.Conection;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class InfoProductoDialogViewController {
    
    private JLabel Codigo;
    private JTextArea Descripcion;
    private JLabel Marca;
    private JLabel Categoria;
    private JLabel CodigoBarra;
    private JLabel Unidad;
    private JLabel CantidadMinima;
    private JLabel PrecioCompra;
    private JLabel PrecioVenta;

    public InfoProductoDialogViewController(JLabel Codigo, JTextArea Descripcion, JLabel Marca, JLabel Categoria, JLabel CodigoBarra, JLabel Unidad, JLabel CantidadMinima, JLabel PrecioCompra, JLabel PrecioVenta) {
        this.Codigo = Codigo;
        this.Descripcion = Descripcion;
        this.Marca = Marca;
        this.Categoria = Categoria;
        this.CodigoBarra = CodigoBarra;
        this.Unidad = Unidad;
        this.CantidadMinima = CantidadMinima;
        this.PrecioCompra = PrecioCompra;
        this.PrecioVenta = PrecioVenta;
    }

    //Task
    public void loadProduct(int ProductoID){
        Runnable run  = () -> {
            Producto producto = new ProductoJpaController(Conection.createEntityManagerFactory()).findProducto(ProductoID);
        
            Codigo.setText(producto.getProductoID().toString());
            Descripcion.setText(producto.getDescripcion());
            Marca.setText(producto.getMarcaID().getNombre());
            Categoria.setText(producto.getCategoriaID().getNombre());
            CodigoBarra.setText(producto.getBarra());
            Unidad.setText(producto.getUnidad());
            CantidadMinima.setText(getNumberFormat(producto.getCantidadMinima()));
            PrecioCompra.setText(getNumberFormat(producto.getPrecioCompra()));
            PrecioVenta.setText(getNumberFormat(producto.getPrecioVenta()));
        };
        new Thread(run).start();
        
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
