package ViewsControllers.Panels.Control;

import Models.Categoria;
import Models.Marca;
import Reports.Reports;
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

public class InventarioViewController {

    private JTextField Buscar;
    private JTable Inventario;
    private JComboBox<Marca> Marcas;
    private JComboBox<Categoria> Categorias;
    private JLabel CostoTotal;
    private JLabel ValorTotal;
    private JLabel CantidadTotal;
    
    private JLabel Cargando;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column)
        { return false; }
    };

    public InventarioViewController(JTextField Buscar, JTable Inventario, JComboBox Marcas, JComboBox Categorias, JLabel CostoTotal, JLabel ValorTotal, JLabel CantidadTotal, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Inventario = Inventario;
        this.Marcas = Marcas;
        this.Categorias = Categorias;
        this.CostoTotal = CostoTotal;
        this.ValorTotal = ValorTotal;
        this.CantidadTotal = CantidadTotal;
        this.Cargando = Cargando;
        
        //Cargando modelo de tabla en tabla de inventario
        setModelTableInventory();
        
        //Agregando objeto de filtrado a tabla
        addRowSorter();
        
        //Inializando carga de datos
        Init();
    }

    public void setLoad(boolean status) {
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(status ? icon : null);
    }

    private void Init() {
        setLoad(true);
        Runnable run = () -> {
            //Cargando marcas en combobox
            loadBrands();
            
            //Cargando categorias en combobox
            loadCategories();
            
            //Cargando inventario actual
            loadInventory();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void addRowSorter(){
        rowSorter.setModel(Inventario.getModel());
    }
    
    private void setModelTableInventory(){
        String[] columns = {"Cd.", "Descripcion", "Marca", "Categoria", "Precio/Compra", "Precio/Venta", "Existencia"};
        model.setColumnIdentifiers(columns);
        
        Inventario.setModel(model);
        Inventario.getColumn("Cd.").setPreferredWidth(30);
        Inventario.getColumn("Descripcion").setPreferredWidth(450);
        Inventario.getColumn("Marca").setPreferredWidth(100);
        Inventario.getColumn("Categoria").setPreferredWidth(130);
        Inventario.getColumn("Precio/Compra").setPreferredWidth(80);
        Inventario.getColumn("Precio/Venta").setPreferredWidth(80);
        Inventario.getColumn("Existencia").setPreferredWidth(40);
    }

    public void loadInventory() {
        model.setRowCount(0);
        List<Object[]> listInventory = Conection.createEntityManager().createNativeQuery("SELECT * FROM ViewInventario").getResultList();
        listInventory.forEach(model::addRow);
        updateTotales();
    }

    private void updateTotales() {
        float costoTotal = 0;
        float valorTotal = 0;
        float cantidadTotal = 0;

        int counter = 0;
        while (counter < Inventario.getRowCount()) {
            float precioCompra = Float.parseFloat(Inventario.getValueAt(counter, 4).toString());
            float precioVenta = Float.parseFloat(Inventario.getValueAt(counter, 5).toString());
            float cantidad = Float.parseFloat(Inventario.getValueAt(counter, 6).toString());
            
            costoTotal += precioCompra * cantidad;
            valorTotal += precioVenta * cantidad;
            cantidadTotal += cantidad;
            counter++;
        }

        CostoTotal.setText(getNumberFormat(costoTotal));
        ValorTotal.setText(getNumberFormat(valorTotal));
        CantidadTotal.setText(getNumberFormat(cantidadTotal));
    }

    public void loadBrands() {
        Marcas.removeAllItems();
        Marcas.addItem(new Marca(0, "-- Todas las marcas --"));
        List<Marca> marcas = Conection.createEntityManager().createNamedQuery("Marca.findAll").getResultList();
        marcas.forEach(Marcas::addItem);
    }

    public void loadCategories() {
        Categorias.removeAllItems();
        Categorias.addItem(new Categoria(0, "-- Todas las categorias --"));
        List<Categoria> categorias = Conection.createEntityManager().createNamedQuery("Categoria.findAll").getResultList();
        categorias.forEach(Categorias::addItem);
    }

    public void filter(){
        List<RowFilter<TableModel, String>> searchfilters = new LinkedList<>();
        if(!Buscar.getText().isEmpty() || !Buscar.getForeground().equals(new Color(180, 180, 180))){
            String[] words = Buscar.getText().split(" ");
            for(String word : words){
                searchfilters.add(RowFilter.regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+word, 1, 2));
            }
        }
        RowFilter wordsFilter = RowFilter.andFilter(searchfilters);
        RowFilter brandsFilter = RowFilter.regexFilter(Marcas.getSelectedIndex() > 0 ? Marcas.getSelectedItem().toString() : "", 2);
        RowFilter categoryFilter = RowFilter.regexFilter(Categorias.getSelectedIndex() > 0 ? Categorias.getSelectedItem().toString() : "", 2);
        
        List<RowFilter<TableModel, String>> filters = Arrays.asList(wordsFilter, brandsFilter, categoryFilter);
        rowSorter.setRowFilter(RowFilter.andFilter(filters));
        Inventario.setRowSorter(rowSorter);
    }

    public void printReportInventory() {
        if(Dialogs.ShowOKCancelDialog("¿Desea imprimir el reporte de inventario ahora?", Dialogs.WARNING_ICON)){
            Runnable run = () -> {
                Reports report = new Reports();
                report.GenerateInventarioReport(Utilities.getUsuarioActual().getNombre());
            };
            new Thread(run).start();
        }
    }

    public void modifyExistence() {
        int fila = Inventario.getSelectedRow();
        if (fila >= 0) {
            int InventarioID = Integer.parseInt(Inventario.getValueAt(fila, 0).toString());
            Dialogs.ShowAddInventarioDetalle(InventarioID);
            loadInventory();
        } else {
            Dialogs.ShowMessageDialog("Seleccione un elemento de la lista", Dialogs.ERROR_ICON);
        }
    }

    public void deleteInventory() {
        int fila = Inventario.getSelectedRow();
        if (fila >= 0) {
            float Cantidad = Float.parseFloat(Inventario.getValueAt(fila, 6).toString());
            if (Cantidad == 0) {
                int InventarioID = Integer.parseInt(Inventario.getValueAt(fila, 0).toString());
                Dialogs.ShowAddInventarioDetalleForDelete(InventarioID);
                loadInventory();
            } else {
                Dialogs.ShowMessageDialog("Únicamente se pueden eliminar productos con cantidad 0", Dialogs.ERROR_ICON);
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione una elemento de la lista", Dialogs.ERROR_ICON);
        }
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
