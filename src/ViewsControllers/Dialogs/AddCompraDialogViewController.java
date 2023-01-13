package ViewsControllers.Dialogs;

import Controllers.ProductoJpaController;
import Models.Producto;
import Resource.Conection;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AddCompraDialogViewController {
    
    private JTextField Buscar;
    private JTable Productos;
    private JTextField DescuentoPorcentaje;
    private JTextField DescuentoLempiras;
    private JTextField Precio;
    private JTextField Cantidad;
    private JTextField Subtotal;
    private JLabel Error;

    public AddCompraDialogViewController(JTextField Buscar, JTable Productos, JTextField DescuentoProcentaje, JTextField DescuentoLempiras, JTextField Precio, JTextField Cantidad, JTextField Subtotal, JLabel Error) {
        this.Buscar = Buscar;
        this.Productos = Productos;
        this.DescuentoPorcentaje = DescuentoProcentaje;
        this.DescuentoLempiras = DescuentoLempiras;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
        this.Error = Error;
    }
    
    public void CargarProductos(){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"Codigo", "Descripcion", "Unidad", "Precio base"};
        model.setColumnIdentifiers(columns);
        
        List<Producto> productos = new ProductoJpaController(Conection.CreateEntityManager()).findProductoEntities();
        productos.forEach(producto -> {
            Object[] row = {producto.getProductoID(), producto.getDescripcion(), producto.getUnidad(), producto.getPrecioCompra()};
            model.addRow(row);
        });
        
        Productos.setModel(model);
        Productos.getColumn("Codigo").setPreferredWidth(70);
        Productos.getColumn("Descripcion").setPreferredWidth(320);
        Productos.getColumn("Unidad").setPreferredWidth(80);
        Productos.getColumn("Precio base").setPreferredWidth(90);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter(Productos.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1));
        Productos.setRowSorter(s);
    }
    
    public void cargarProducto(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            DescuentoLempiras.setText(getNumberFormat(0f));
            DescuentoPorcentaje.setText(getNumberFormat(0f));
            Precio.setText(getNumberFormat(Float.parseFloat(Productos.getValueAt(fila, 3).toString())));
            Cantidad.setText(getNumberFormat(1f));
            float Total = Float.parseFloat(Productos.getValueAt(fila, 3).toString()) * Float.parseFloat(Cantidad.getText());
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
            Subtotal.setText(getNumberFormat(subtotal)+" Lps.");
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
                Productos.getValueAt(Productos.getSelectedRow(), 2),
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
                Error.setText("El descueto en porcentaje debe de ser un numero");
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
                Error.setText("El descueto en lempiras debe de ser un numero");
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
