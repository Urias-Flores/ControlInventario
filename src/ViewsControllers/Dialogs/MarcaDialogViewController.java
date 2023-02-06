package ViewsControllers.Dialogs;

import Controllers.MarcaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Marca;
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

public class MarcaDialogViewController {

    MarcaJpaController marcaJpaController;

    JTextField Buscar;
    JTable Tabla;

    public MarcaDialogViewController(JTextField Buscar, JTable Tabla) {
        this.Buscar = Buscar;
        this.Tabla = Tabla;
        marcaJpaController = new MarcaJpaController(Conection.createEntityManagerFactory());
    }

    public void cargarMarcas() {
        DefaultTableModel model = new DefaultTableModel();
        String[] headerStrings = {"Codigo", "Nombre"};

        model.setColumnIdentifiers(headerStrings);
        List<Marca> marcas = marcaJpaController.findMarcaEntities();

        marcas.forEach(marca -> {
            ArrayList<Object> row = new ArrayList<>();
            row.add(marca.getMarcaID());
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
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar la marca seleccionada?", Dialogs.WARNING_ICON)) {
                try {
                    marcaJpaController.destroy(Integer.valueOf(Tabla.getValueAt(fila, 0).toString()));
                    cargarMarcas();
                    Dialogs.ShowMessageDialog("La marca ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Ups.. Puede que exista un producto registrado con esta marca", Dialogs.ERROR_ICON);
                }
            }else{
                Dialogs.ShowMessageDialog("La eliminacion de la marca ha sido cancelada", Dialogs.ERROR_ICON);
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una marca de la lista", Dialogs.ERROR_ICON);
        }
    }
}
