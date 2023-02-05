package ViewsControllers.Panels.Control;

import Controllers.ProductoJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProductoViewController {

    private ProductoJpaController controller;

    private JTextField Buscar;
    private JTable Productos;
    
    private JLabel Cargando;

    public ProductoViewController(JTextField Buscar, JTable Productos, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Productos = Productos;
        this.Cargando = Cargando;
        
        controller = new ProductoJpaController(Conection.CreateEntityManager());
    }

    public void cargarProductos() {
        Cargando.setIcon(new ImageIcon(getClass().getResource(Utilities.getLoadingImage())));
        Runnable run = ()->{
            DefaultTableModel model = new DefaultTableModel();
            String[] columns = {"Codigo", "Descripcion", "Marca", "Categoria", "Barra", "Unidad"};
            model.setColumnIdentifiers(columns);
            List<Producto> productos = new ProductoJpaController(Conection.CreateEntityManager()).findProductoEntities();

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

            Productos.setModel(model);

            Productos.getColumn("Codigo").setPreferredWidth(5);
            Productos.getColumn("Descripcion").setPreferredWidth(380);
            Productos.getColumn("Marca").setPreferredWidth(90);
            Productos.getColumn("Categoria").setPreferredWidth(120);
            Productos.getColumn("Barra").setPreferredWidth(100);
            Productos.getColumn("Unidad").setPreferredWidth(40);
            
            Cargando.setIcon(null);
        };
        new Thread(run).start();
        
    }

    public void buscar() {
        TableRowSorter s = new TableRowSorter();
        s.setModel(Productos.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 0, 1, 4));
        Productos.setRowSorter(s);
    }

    public void Eliminar() {
        int fila = Productos.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar el producto seleccionado?", Dialogs.WARNING_ICON)) {
                try {
                    controller.destroy(Integer.valueOf(Productos.getValueAt(fila, 0).toString()));
                    cargarProductos();
                    Dialogs.ShowMessageDialog("El producto ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    System.err.println(ex.getMessage());
                    Dialogs.ShowMessageDialog("Ups.. Puede que existan facturas con este producto registrado", Dialogs.ERROR_ICON);
                }
            }else{
                Dialogs.ShowMessageDialog("La eliminacion fue cancelada", Dialogs.ERROR_ICON);
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccion un producto de la lista de productos", Dialogs.ERROR_ICON);
        }
    }
    
    public void Editar(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyProductDialog(Integer.parseInt(Productos.getValueAt(fila, 0).toString()));
            cargarProductos();
        }else{
            Dialogs.ShowMessageDialog("Seleccion un producto para modificar de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void ShowInfoProducto(){
        int fila = Productos.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoProducto(Integer.parseInt(Productos.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione un producto de la lista", Dialogs.ERROR_ICON);
        }
    }
}
