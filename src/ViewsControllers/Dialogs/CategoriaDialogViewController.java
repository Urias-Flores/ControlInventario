package ViewsControllers.Dialogs;

import Controllers.CategoriaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Categoria;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CategoriaDialogViewController {

    private CategoriaJpaController controller;

    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Categorias;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public CategoriaDialogViewController(JTextField Buscar, JLabel Cargando, JTable Tabla) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Categorias = Tabla;
        
        controller = new CategoriaJpaController(Conection.createEntityManagerFactory());
        
        //Cargando modelo de tabla ne tabla de categorias
        setModelTableCategories();
        
        //Cargando filtro de busqueda en tabla
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
            loadCategories();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableCategories(){
        String[] headerStrings = {"Codigo", "Nombre"};
        model.setColumnIdentifiers(headerStrings);
        
        Categorias.setModel(model);
        Categorias.getColumn("Codigo").setPreferredWidth(70);
        Categorias.getColumn("Nombre").setPreferredWidth(500);
    }
    
    private void addRowSorter(){
        rowSorter.setModel(Categorias.getModel());
    }

    private void loadCategories() {
        List<Categoria> categorias = new CategoriaJpaController(Conection.createEntityManagerFactory()).findCategoriaEntities();
        categorias.forEach(marca -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(marca.getCategoriaID());
            row.add(marca.getNombre());
            model.addRow(row.toArray());
        });
    }

    public void search() {
        rowSorter.setModel(Categorias.getModel());
        rowSorter.setRowFilter
        (RowFilter.regexFilter(Buscar.getText().isEmpty() || Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1, 2));
        Categorias.setRowSorter(rowSorter);
    }
    
    public void deleteCategory() {
        int fila = Categorias.getSelectedRow();
        if (fila > 0) {
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar la marca seleccionada?", Dialogs.DELETE_ICON)) {
                try {
                    controller.destroy(Integer.valueOf(Categorias.getValueAt(fila, 0).toString()));
                    loadCategories();
                    Dialogs.ShowMessageDialog("La categoria ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("ps.. Puede que exista un producto registrado con esta categoria", Dialogs.ERROR_ICON);
                }
            }else{
                Dialogs.ShowMessageDialog("La eliminacion de la categoria ah sido cancelada", Dialogs.ERROR_ICON);
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una categoria de la lista", Dialogs.ERROR_ICON);
        }
    }
}
