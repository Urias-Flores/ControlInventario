package ViewsControllers.Dialogs;

import Controllers.CategoriaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Categoria;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CategoriaDialogViewController {

    CategoriaJpaController controller;

    JTextField Buscar;
    JTable Tabla;

    public CategoriaDialogViewController(JTextField Buscar, JTable Tabla) {
        this.Buscar = Buscar;
        this.Tabla = Tabla;
        controller = new CategoriaJpaController(Conection.CreateEntityManager());
    }

    public void cargarCategorias() {
        DefaultTableModel model = new DefaultTableModel();
        String[] headerStrings = {"Codigo", "Nombre"};

        model.setColumnIdentifiers(headerStrings);
        List<Categoria> categorias = new CategoriaJpaController(Conection.CreateEntityManager()).findCategoriaEntities();

        categorias.forEach(marca -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(marca.getCategoriaID());
            row.add(marca.getNombre());
            model.addRow(row.toArray());
        });

        Tabla.setModel(model);
        Utilities.EditarTableHeader(Tabla);
        Tabla.getColumn("Codigo").setPreferredWidth(70);
        Tabla.getColumn("Nombre").setPreferredWidth(500);
    }

    public void Buscar() {
        TableRowSorter s = new TableRowSorter();
        s.setModel(Tabla.getModel());
        s.setRowFilter(RowFilter.regexFilter(Buscar.getText(), 1, 2));
        Tabla.setRowSorter(s);
    }
    
    public void Eliminar() {
        int fila = Tabla.getSelectedRow();
        if (fila > 0) {
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar la marca seleccionada?", Dialogs.DELETE_ICON)) {
                try {
                    controller.destroy(Integer.valueOf(Tabla.getValueAt(fila, 0).toString()));
                    cargarCategorias();
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
