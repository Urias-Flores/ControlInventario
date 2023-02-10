package ViewsControllers.Dialogs;

import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AddCompraDialogViewController {
    
    private JTextField Buscar;
    private JComboBox Marcas;
    private JTable Productos;
    private JTextField DescuentoPorcentaje;
    private JTextField DescuentoLempiras;
    private JTextField Precio;
    private JTextField Cantidad;
    private JTextField Subtotal;
    private JLabel Error;
    private JLabel Cargando;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public AddCompraDialogViewController(JTextField Buscar, JComboBox Marcas,JTable Productos, JTextField DescuentoProcentaje, JTextField DescuentoLempiras, JTextField Precio, JTextField Cantidad, JTextField Subtotal, JLabel Error, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Marcas = Marcas;
        this.Productos = Productos;
        this.DescuentoPorcentaje = DescuentoProcentaje;
        this.DescuentoLempiras = DescuentoLempiras;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
        this.Error = Error;
        this.Cargando = Cargando;
        
        //Inhabilitando la edicion de datos
        setEditableFields(false);
        
        //Cargano modelo de tabla en tabla de productos
        setModelTableProducts();
        
        //Agregando modelo en filtro de busqueda
        addRowSorter();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando marcas en combobox
            loadBrands();
            
            //Cargando productos en tabla
            loadProducts();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void addRowSorter(){
        rowSorter.setModel(Productos.getModel());
    }
    
    private void setModelTableProducts(){
        String[] columns = {"Codigo", "Descripcion", "Marca","Unidad", "Precio base"};
        model.setColumnIdentifiers(columns);
        
        Productos.setModel(model);
        Productos.getColumn("Codigo").setPreferredWidth(50);
        Productos.getColumn("Descripcion").setPreferredWidth(320);
        Productos.getColumn("Marca").setPreferredWidth(100);
        Productos.getColumn("Unidad").setPreferredWidth(80);
        Productos.getColumn("Precio base").setPreferredWidth(90);
    }
    
    private void loadProducts(){
        model.setRowCount(0);
        List<Producto> productos = Conection.createEntityManager().createNamedQuery("Producto.findAll").getResultList();
        if(!productos.isEmpty()){
            productos.forEach(producto -> {
                Object[] row = {
                    producto.getProductoID(), 
                    producto.getDescripcion(),
                    producto.getMarcaID().getNombre(),
                    producto.getUnidad(), 
                    producto.getPrecioCompra()
                };
                model.addRow(row);
            });
        }
    }
    
    private void loadBrands(){
        Marcas.removeAllItems();
        Marcas.addItem("-- Todas la marcas --");
        List<Marca> marcas = Conection.createEntityManagerFactory().createEntityManager()
            .createNamedQuery("Marca.findAll")
            .getResultList();
        marcas.forEach(Marcas::addItem);
    }
    
    public void filter(){
        List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        filters.add(RowFilter.regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1));
        filters.add(RowFilter.regexFilter(Marcas.getSelectedIndex() <= 0 ? "" : Marcas.getSelectedItem().toString(), 2));
        rowSorter.setRowFilter(RowFilter.andFilter(filters));
        Productos.setRowSorter(rowSorter);
        clear();
        setEditableFields(false);
    }
    
    public void loadProduct(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            setEditableFields(true);
            DescuentoLempiras.setText(getNumberFormat(0f));
            DescuentoPorcentaje.setText(getNumberFormat(0f));
            Precio.setText(getNumberFormat(Float.parseFloat(Productos.getValueAt(fila, 4).toString())));
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
    
    private void clear() {
        DescuentoPorcentaje.setText("0.00");
        DescuentoLempiras.setText("0.00");
        Precio.setText("0.00");
        Cantidad.setText("0.00");
        Subtotal.setText("0.00");
    }
    
    private void setEditableFields(boolean status) {
        Precio.setEditable(status);
        DescuentoPorcentaje.setEditable(status);
        DescuentoLempiras.setEditable(status);
        Cantidad.setEditable(status);
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
