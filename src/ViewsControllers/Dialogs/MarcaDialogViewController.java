package ViewsControllers.Dialogs;

import Controllers.MarcaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Marca;
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

public class MarcaDialogViewController {

    private MarcaJpaController marcaJpaController;

    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Marcas;
    
    private TableRowSorter rowSorter = new TableRowSorter();
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public MarcaDialogViewController(JTextField Buscar, JLabel Cargando,JTable Tabla) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Marcas = Tabla;
        marcaJpaController = new MarcaJpaController(Conection.createEntityManagerFactory());
        
        //Cargando model de tabla en tabla de marcas
        setModelTableBrands();
        
        //Agragon modelo de tabla al filtro de busqueda
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
            //Cargando marcas en la tabla
            loadBrands();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }    
    
    private void setModelTableBrands(){
        String[] columns = {"Codigo", "Nombre"};
        model.setColumnIdentifiers(columns);
        
        Marcas.setModel(model);
        Marcas.getColumn("Codigo").setPreferredWidth(70);
        Marcas.getColumn("Nombre").setPreferredWidth(500);
    }
    
    private void addRowSorter(){
        rowSorter.setModel(Marcas.getModel());
    }
            
    private void loadBrands() {
        List<Marca> marcas = marcaJpaController.findMarcaEntities();
        marcas.forEach(marca -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(marca.getMarcaID());
            row.add(marca.getNombre());
            model.addRow(row.toArray());
        });
    }

    public void search() {
        rowSorter.setRowFilter
        (RowFilter.regexFilter
        (Buscar.getText().isEmpty() || Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1, 2));
        Marcas.setRowSorter(rowSorter);
    }
     
    //task
    public void deleteBrand() {
        int fila = Marcas.getSelectedRow();
        if (fila > 0) {
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar la marca seleccionada?", Dialogs.WARNING_ICON)) {
                try {
                    marcaJpaController.destroy(Integer.valueOf(Marcas.getValueAt(fila, 0).toString()));
                    loadBrands();
                    Dialogs.ShowMessageDialog("La marca ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Ups.. Puede que exista un producto registrado con esta marca", Dialogs.ERROR_ICON);
                }
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una marca de la lista", Dialogs.ERROR_ICON);
        }
    }
}
