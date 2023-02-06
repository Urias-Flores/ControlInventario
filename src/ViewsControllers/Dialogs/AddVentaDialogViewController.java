package ViewsControllers.Dialogs;

import Controllers.MarcaJpaController;
import Controllers.ProductoJpaController;
import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AddVentaDialogViewController {
    
    private JTextField Buscar;
    private JComboBox Marcas;
    private JTable Productos;
    private JTextField Existencia;
    private JTextField DescuentoPorcentaje;
    private JTextField DescuentoLempiras;
    private JTextField Precio;
    private JTextField Cantidad;
    private JTextField Subtotal;
    private JLabel Error;
    private JLabel Cargando;

    public AddVentaDialogViewController(JTextField Buscar, JComboBox Marcas, JLabel Cargando, JTable Productos, JTextField Existencia, JTextField DescuentoPorcentaje, JTextField DescuentoLempiras, JTextField Precio, JTextField Cantidad, JTextField Subtotal, JLabel Error) {
        this.Buscar = Buscar;
        this.Marcas = Marcas;
        this.Productos = Productos;
        this.Existencia = Existencia;
        this.DescuentoPorcentaje = DescuentoPorcentaje;
        this.DescuentoLempiras = DescuentoLempiras;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    public void CargarProductos(){
        Cargando.setIcon(new ImageIcon(getClass().getResource(Utilities.getLoadingImage())));
        Runnable run = ()->{
            DefaultTableModel model = new DefaultTableModel();
            String[] columns = {"Codigo", "Descripcion", "Marca", "Unidad", "Precio"};
            model.setColumnIdentifiers(columns);

            List<Producto> productos = new ProductoJpaController(Conection.createEntityManagerFactory()).findProductoEntities();
            productos.forEach(producto -> {
                Object[] row = {
                    producto.getProductoID(), 
                    producto.getDescripcion(),
                    producto.getMarcaID().getNombre(),
                    producto.getUnidad(), 
                    producto.getPrecioVenta()
                };

                model.addRow(row);
            });

            Productos.setModel(model);
            Productos.getColumn("Codigo").setPreferredWidth(50);
            Productos.getColumn("Descripcion").setPreferredWidth(320);
            Productos.getColumn("Marca").setPreferredWidth(100);
            Productos.getColumn("Unidad").setPreferredWidth(80);
            Productos.getColumn("Precio").setPreferredWidth(90);
            
            Cargando.setIcon(null);
        };
        new Thread(run).start();
    }
    
    public void CargarMarcas(){
        Cargando.setIcon(new ImageIcon(getClass().getResource(Utilities.getLoadingImage())));
        Runnable run = ()->{
            List<Marca> marcas = Conection.createEntityManagerFactory().createEntityManager()
                .createNamedQuery("Marca.findAll")
                .getResultList();
            marcas.forEach(Marcas::addItem);
            Cargando.setIcon(null);
        };
        new Thread(run).start(); 
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Productos.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1));
        Productos.setRowSorter(s);
    }
    
    public void FiltrarMarcas(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Productos.getModel());
        if(Marcas.getSelectedIndex() > 0){
            s.setRowFilter(RowFilter.regexFilter(Marcas.getSelectedItem().toString() , 2));
        }else{
            s.setRowFilter(RowFilter.regexFilter("" , 2));
        }
        Productos.setRowSorter(s);
    }
    
    public void cargarProducto(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            Query query = Conection.createEntityManagerFactory().createEntityManager()
                    .createNativeQuery("SELECT cantidad FROM inventario WHERE ProductoID = "+Integer.valueOf(Productos.getValueAt(fila, 0).toString()));
            List values = query.getResultList();
            float existenciaProducto = Float.parseFloat(values.get(0).toString());
            
            if(existenciaProducto <= 0){
                Error.setBackground(new Color(185, 0, 0));
                Error.setText("No hay existencia del producto seleccionado");
            }else{
                Error.setBackground(Color.white);
            }
            
            DescuentoLempiras.setText(getNumberFormat(0f));
            DescuentoPorcentaje.setText(getNumberFormat(0f));
            Existencia.setText(getNumberFormat(existenciaProducto));
            Precio.setText(getNumberFormat(Float.parseFloat(Productos.getValueAt(fila, 4).toString().replace(",", ""))));
            Cantidad.setText(getNumberFormat(1f));
            float Total = Float.parseFloat(Productos.getValueAt(fila, 4).toString()) * Float.parseFloat(Cantidad.getText());
            Subtotal.setText(getNumberFormat(Total));
        }
    }
    
    public void updateSubtotal(){
        if(validate()){
            Error.setBackground(Color.white);
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float subtotal = (precio * cantidad) - descuento;
            Subtotal.setText(getNumberFormat(subtotal));
        }
    }
    
    public void updatePorcentajeLempiras(){
        if(validate()){
            float descuento = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float DescuentoEnLempiras = (precio * cantidad) * (descuento * 0.01f);
            DescuentoLempiras.setText(getNumberFormat(DescuentoEnLempiras));
        }
    }
    
    public void updateLempirasPorcentajes(){
        if(validate()){
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float DescuentoEnPorcentaje = (descuento) / ((precio * cantidad) / 100);
            DescuentoPorcentaje.setText(getNumberFormat(DescuentoEnPorcentaje));
        }
    }
    
    public Object[] getValues(){
        
        if(validate()){
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float subtotal = (cantidad * precio) - descuento;
            
            Object[] values  = {
                Productos.getValueAt(Productos.getSelectedRow(), 0),
                Productos.getValueAt(Productos.getSelectedRow(), 1),
                Productos.getValueAt(Productos.getSelectedRow(), 3),
                getNumberFormat(cantidad),
                getNumberFormat(precio),
                getNumberFormat(descuento),
                getNumberFormat(subtotal)
            };
            return values;
        }
        return null;
    }
    
    private boolean validate(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            try{
                float descuentoPorcentaje = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
                if(descuentoPorcentaje < 0 && descuentoPorcentaje > 101){
                    Error.setText("El descuento en porcentaje debe de ser mayor a cero y menor a 100");
                    Error.setBackground(new Color(185, 0, 0));
                    return false;
                }
            }catch(NumberFormatException ex){
                Error.setText("El descuento en porcentaje debe de ser un numero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
            
            try{
                float descuentoLempiras = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
                if(descuentoLempiras < 0){
                    Error.setText("El descuento debe de ser mayor a cero");
                    Error.setBackground(new Color(185, 0, 0));
                    return false;
                }
            }catch(NumberFormatException ex){
                Error.setText("El descuento en lempiras debe de ser un numero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
            
            try{
                float precio = Float.parseFloat(Precio.getText().replace(",", ""));
                if(precio <= 0){
                    Error.setText("El precio debe de ser mayor a cero");
                    Error.setBackground(new Color(185, 0, 0));
                    return false;
                }
            }catch(NumberFormatException ex){
                Error.setText("El precio debe de ser un numero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
            
            try{
                float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
                if(cantidad <= 0){
                    Error.setText("La Cantidad debe de ser mayor a cero");
                    Error.setBackground(new Color(185, 0, 0));
                    return false;
                }
                float existencia = Float.parseFloat(Existencia.getText().replace(",", " "));
                if(cantidad > existencia){
                    Error.setText("La cantidad debe de ser menor a la existencia actual");
                    Error.setBackground(new Color(185, 0, 0));
                    return false;
                }
            }catch(NumberFormatException ex){
                Error.setText("La cantidad del producto debe de ser un numero");
                Error.setBackground(new Color(185, 0, 0));
                return false;
            }
            
        }else{
            Error.setText("Seleccione un producto de la lista");
            Error.setBackground(new Color(185, 0, 0));
            return false;
        }
        return true;
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
