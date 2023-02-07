package ViewsControllers.Panels.Control;

import Controllers.CategoriaJpaController;
import Controllers.MarcaJpaController;
import Models.Categoria;
import Models.Marca;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private JLabel Total;
    private JLabel CantidadTotal;
    
    private JLabel Cargando;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column)
        { return false; }
    };

    public InventarioViewController(JTextField Buscar, JTable Inventario, JComboBox Marcas, JComboBox Categorias, JLabel Total, JLabel CantidadTotal, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Inventario = Inventario;
        this.Marcas = Marcas;
        this.Categorias = Categorias;
        this.Total = Total;
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
        String[] columns = {"No. Inventario", "Cd.", "Descripcion", "Marca", "Categoria", "Precio/Compra", "Existencia"};
        model.setColumnIdentifiers(columns);
        
        Inventario.setModel(model);
        Inventario.getColumn("No. Inventario").setPreferredWidth(70);
        Inventario.getColumn("Cd.").setPreferredWidth(30);
        Inventario.getColumn("Descripcion").setPreferredWidth(450);
        Inventario.getColumn("Marca").setPreferredWidth(100);
        Inventario.getColumn("Categoria").setPreferredWidth(130);
        Inventario.getColumn("Precio/Compra").setPreferredWidth(80);
        Inventario.getColumn("Existencia").setPreferredWidth(40);
    }

    public void loadInventory() {
        model.setRowCount(0);
        List<Object[]> l = Conection.createEntityManager().createNativeQuery("SELECT * FROM ViewInventario").getResultList();
        l.forEach(model::addRow);
        updateTotales();
    }

    private void updateTotales() {
        float total = 0;
        float cantidadTotal = 0;

        int counter = 0;
        while (counter < Inventario.getRowCount()) {
            total += (Float.parseFloat(Inventario.getValueAt(counter, 5).toString()) * Float.parseFloat(Inventario.getValueAt(counter, 6).toString()));
            cantidadTotal += Float.parseFloat(Inventario.getValueAt(counter, 6).toString());
            counter++;
        }

        Total.setText(getNumberFormat(total));
        CantidadTotal.setText(String.valueOf(cantidadTotal));
    }

    public void loadBrands() {
        Marcas.removeAllItems();
        Marcas.addItem(new Marca(0, "-- Todas las marcas --"));
        MarcaJpaController marcaJpaController = new MarcaJpaController(Conection.createEntityManagerFactory());
        List<Marca> marcas = marcaJpaController.findMarcaEntities();
        marcas.forEach(Marcas::addItem);
    }

    public void loadCategories() {
        Categorias.removeAllItems();
        Categorias.addItem(new Categoria(0, "-- Todas las categorias --"));
        CategoriaJpaController categoriaJpaController = new CategoriaJpaController(Conection.createEntityManagerFactory());
        List<Categoria> categorias = categoriaJpaController.findCategoriaEntities();
        categorias.forEach(Categorias::addItem);
    }

    public void filter(){
        RowFilter searchFilter = 
                RowFilter.regexFilter(
                        (Buscar.getText().isEmpty() || Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText()) , 2);
        RowFilter brandFilter = RowFilter.regexFilter(Marcas.getSelectedIndex() > 0 ? Marcas.getSelectedItem().toString() : "", 3);
        RowFilter categoryFilter = RowFilter.regexFilter(Categorias.getSelectedIndex() > 0 ? Categorias.getSelectedItem().toString() : "", 4);
        
        List<RowFilter<TableModel, String>> filters = Arrays.asList(searchFilter, brandFilter, categoryFilter);
        rowSorter.setRowFilter(RowFilter.andFilter(filters));
        Inventario.setRowSorter(rowSorter);
    }

    public void printReportInventory() {
        Reports report = new Reports();
        try {
            report.GenerateInventarioReport(Utilities.getUsuarioActual().getNombre());
        } catch (PrinterException ex) {
            Logger.getLogger(InventarioViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifyExistence() {
        int fila = Inventario.getSelectedRow();
        if (fila >= 0) {
            int InventarioID = Integer.parseInt(Inventario.getValueAt(fila, 0).toString());
            Dialogs.ShowAddInventarioDetalle(InventarioID);
            loadInventory();
        } else {
            Dialogs.ShowMessageDialog("Seleccione una elemento de la lista", Dialogs.ERROR_ICON);
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
                Dialogs.ShowMessageDialog("Unicamente se pueden eliminar productos con cantidad 0", Dialogs.ERROR_ICON);
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
