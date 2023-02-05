
package ViewsControllers.Panels.Control;

import Controllers.CategoriaJpaController;
import Controllers.MarcaJpaController;
import Models.Categoria;
import Models.Marca;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class InventarioViewController {
    
    private JTextField Buscar;
    private JTable Inventario;
    private JComboBox<Marca> Marcas;
    private JComboBox<Categoria> Categorias;
    private JLabel Total;
    private JLabel CantidadTotal;
    
    private JLabel Cargando;

    public InventarioViewController(JTextField Buscar, JTable Inventario, JComboBox Marcas, JComboBox Categorias, JLabel Total, JLabel CantidadTotal, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Inventario = Inventario;
        this.Marcas = Marcas;
        this.Categorias = Categorias;
        this.Total = Total;
        this.CantidadTotal = CantidadTotal;
        this.Cargando = Cargando;
    }
    
    public void CargarInventario(){
        Cargando.setIcon(new ImageIcon(getClass().getResource(Utilities.getLoadingImage())));
        Runnable run = ()->{
            DefaultTableModel model = new DefaultTableModel();
            String[] columns = {"No. Inventario", "Cd.", "Descripcion", "Marca", "Categoria", "Precio/Compra","Existencia"};
            model.setColumnIdentifiers(columns);

            Query query = Conection.CreateEntityManager().createEntityManager().createNativeQuery("SELECT * FROM ViewInventario");
            List<Object[]> l = query.getResultList();

            l.forEach(inventario -> {
                model.addRow(inventario);
            });

            Inventario.setModel(model);

            Inventario.getColumn("No. Inventario").setPreferredWidth(70);
            Inventario.getColumn("Cd.").setPreferredWidth(30);
            Inventario.getColumn("Descripcion").setPreferredWidth(450);
            Inventario.getColumn("Marca").setPreferredWidth(100);
            Inventario.getColumn("Categoria").setPreferredWidth(130);
            Inventario.getColumn("Precio/Compra").setPreferredWidth(80);
            Inventario.getColumn("Existencia").setPreferredWidth(40);

            updateTotales();
            
            Cargando.setIcon(null);
        };
        new Thread(run).start();
    }
    
    private void updateTotales(){
        float total = 0;
        float cantidadTotal = 0;
        
        for(int i = 0; i < Inventario.getRowCount(); i ++){
            total += (Float.parseFloat(Inventario.getValueAt(i, 5).toString()) * Float.parseFloat(Inventario.getValueAt(i, 6).toString()));
            cantidadTotal += Float.parseFloat(Inventario.getValueAt(i, 6).toString());
        }
        
        Total.setText(getNumberFormat(total));
        CantidadTotal.setText(String.valueOf(cantidadTotal));
    }
    
    public void CargarMarcas(){
        MarcaJpaController marcaJpaController = new MarcaJpaController(Conection.CreateEntityManager());
        List<Marca> marcas = marcaJpaController.findMarcaEntities();
        Marcas.removeAllItems();
        Marcas.addItem(new Marca(0, "-- Todas las marcas --"));
        marcas.forEach(Marcas::addItem);
    }
    
    public void CargarCategorias(){
        CategoriaJpaController categoriaJpaController = new CategoriaJpaController(Conection.CreateEntityManager());
        List<Categoria> categorias = categoriaJpaController.findCategoriaEntities();
        Categorias.removeAllItems();
        Categorias.addItem(new Categoria(0, "-- Todas las categorias --"));
        categorias.forEach(Categorias::addItem);
    }
    
    public void Buscar(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Inventario.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 2));
        Inventario.setRowSorter(s);
    }
    
    public void FiltrarMarcas(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Inventario.getModel());
        if(Marcas.getSelectedIndex() > 0){
            s.setRowFilter(RowFilter.regexFilter(Marcas.getSelectedItem().toString() , 3));
        }else{
            s.setRowFilter(RowFilter.regexFilter("" , 3));
        }
        Inventario.setRowSorter(s);
    }
    
    public void FiltrarCategorias(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Inventario.getModel());
        if(Categorias.getSelectedIndex() > 0){
            s.setRowFilter(RowFilter.regexFilter(Categorias.getSelectedItem().toString() , 4));
        }else{
            s.setRowFilter(RowFilter.regexFilter("" , 4));
        }
        Inventario.setRowSorter(s);
    }
    
    public void ImprimirInventario(){
        Reports report = new Reports();
        try {
            report.GenerateInventarioReport(Utilities.getUsuarioActual().getNombre());
        } catch (PrinterException ex) {
            Logger.getLogger(InventarioViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ModificarExistencia(){
        int fila = Inventario.getSelectedRow();
        if(fila >= 0){
            int InventarioID = Integer.parseInt(Inventario.getValueAt(fila, 0).toString());
            Dialogs.ShowAddInventarioDetalle(InventarioID);
            CargarInventario();
        }else{
            Dialogs.ShowMessageDialog("Seleccione una elemento de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void EliminarInventario(){
        int fila = Inventario.getSelectedRow();
        if(fila >= 0){
            float Cantidad = Float.parseFloat(Inventario.getValueAt(fila, 6).toString());
            if(Cantidad == 0){
                int InventarioID = Integer.parseInt(Inventario.getValueAt(fila, 0).toString());
                Dialogs.ShowAddInventarioDetalleForDelete(InventarioID);
                CargarInventario();     
            }else{
                Dialogs.ShowMessageDialog("Unicamente se pueden eliminar productos con cantidad 0", Dialogs.ERROR_ICON);
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una elemento de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
