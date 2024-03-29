package ViewsControllers.Panels.Control;

import Models.Categoria;
import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.Arrays;
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

public class ProductoViewController {

    private JTextField Buscar;
    private JComboBox Marcas;
    private JComboBox Categorias;
    private JTable Productos;
    private JLabel TotalProductos;
    private JLabel TotalMarcas;
    private JLabel TotalCategorias;
    private JLabel Cargando;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public ProductoViewController(JTextField Buscar, JComboBox Marcas, JComboBox Categorias, JTable Productos, JLabel TotalProcutos, JLabel TotalMarcas, JLabel TotalCategorias, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Marcas = Marcas;
        this.Categorias = Categorias;
        this.Productos = Productos;
        this.TotalProductos = TotalProcutos;
        this.TotalMarcas = TotalMarcas;
        this.TotalCategorias = TotalCategorias;
        this.Cargando = Cargando;
        
        //Cargando model de tabla en tabla de productos
        setModelTableProductos();
        
        //Cargando filtro
        addRowSorter();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void addRowSorter(){
        rowSorter.setModel(Productos.getModel());
    }

    //Task
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando marcas en combobox
            loadBrands();
            
            //Cargando categorias en combobox
            loadCategories();
            
            //Cargando producto en tabla
            loadProducts();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableProductos(){
        String[] columns = {"Codigo", "Descripcion", "Marca", "Categoria", "Barra", "Unidad"};
        model.setColumnIdentifiers(columns);
        
        Productos.setModel(model);
        Productos.getColumn("Codigo").setPreferredWidth(5);
        Productos.getColumn("Descripcion").setPreferredWidth(380);
        Productos.getColumn("Marca").setPreferredWidth(90);
        Productos.getColumn("Categoria").setPreferredWidth(120);
        Productos.getColumn("Barra").setPreferredWidth(100);
        Productos.getColumn("Unidad").setPreferredWidth(40);
    }
    
    private void loadProducts() {
        model.setRowCount(0);
        List<Producto> productos = Conection.createEntityManager().createNamedQuery("Producto.findAll").getResultList();
        productos.forEach(producto -> {
            Object[] row = {
                producto.getProductoID(),
                producto.getDescripcion(),
                producto.getMarcaID(),
                producto.getCategoriaID(),
                producto.getBarra(),
                producto.getUnidad()
            };
            model.addRow(row);
        });
        TotalProductos.setText(getNumberFormat(model.getRowCount()));
    }
    
    public void loadBrands() {
        Marcas.removeAllItems();
        Marcas.addItem(new Marca(0, "-- Todas las marcas --"));
        List<Marca> marcas = Conection.createEntityManager().createNamedQuery("Marca.findAll").getResultList();
        marcas.forEach(Marcas::addItem);
        TotalMarcas.setText(String.valueOf(marcas.size()));
    }

    public void loadCategories() {
        Categorias.removeAllItems();
        Categorias.addItem(new Categoria(0, "-- Todas las categorias --"));
        List<Categoria> categorias = Conection.createEntityManager().createNamedQuery("Categoria.findAll").getResultList();
        categorias.forEach(Categorias::addItem);
        TotalCategorias.setText(String.valueOf(categorias.size()));
    }
    
    public void filter() {
        List<RowFilter<TableModel, String>> wordsFilter = new LinkedList<>();
        if(!Buscar.getText().isEmpty() || !Buscar.getForeground().equals(new Color(180, 180, 180))){
            String[] words = Buscar.getText().split(" ");
            for(String word : words){
                wordsFilter.add(RowFilter.regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+word, 1, 4));
            }
        }
 
        RowFilter searchFilter = RowFilter.andFilter(wordsFilter);
        RowFilter brandFilter = RowFilter.regexFilter(Marcas.getSelectedIndex() > 0 ? Marcas.getSelectedItem().toString() : "", 2);
        RowFilter categoryFilter = RowFilter.regexFilter(Categorias.getSelectedIndex() > 0 ? Categorias.getSelectedItem().toString() : "", 3);
        
        List<RowFilter<TableModel, String>> filters = Arrays.asList(searchFilter, brandFilter, categoryFilter);
        rowSorter.setRowFilter(RowFilter.andFilter(filters));
        Productos.setRowSorter(rowSorter);
    }
    
    public void editProduct(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyProductDialog(Integer.parseInt(Productos.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccion un producto para modificar de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void showInfoProduct(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoProducto(Integer.parseInt(Productos.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione un producto de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
