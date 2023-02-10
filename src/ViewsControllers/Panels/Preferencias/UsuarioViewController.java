package ViewsControllers.Panels.Preferencias;

import Controllers.UsuarioJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Usuario;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
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

public class UsuarioViewController {

    private UsuarioJpaController controller;

    private JTextField Buscar;
    private JComboBox Cargo;
    private JLabel Cargando;
    private JTable Usuarios;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public UsuarioViewController(JTextField Buscar, JComboBox Cargo, JLabel Cargando, JTable Usuarios) {
        this.Buscar = Buscar;
        this.Cargo = Cargo;
        this.Cargando = Cargando;
        this.Usuarios = Usuarios;

        controller = new UsuarioJpaController(Conection.createEntityManagerFactory());

        //Cargando modelo de tabla
        setModelTable();

        //Inicializando carga de datos
        Init();
    }

    private void setLoad(boolean state) {
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }

    //Task
    private void Init() {
        setLoad(true);
        Runnable run = () -> {
            //Cargando datos de usuarios
            loadUsers();

            setLoad(false);
        };
        new Thread(run).start();
    }

    public void updateData() {
        Init();
    }

    private void setModelTable() {
        String[] columns = {"No. Usuario", "Nombre de usuario", "Estado", "Cargo en sistema"};
        model.setColumnIdentifiers(columns);

        Usuarios.setModel(model);
        Usuarios.getColumn("No. Usuario").setPreferredWidth(120);
        Usuarios.getColumn("Nombre de usuario").setPreferredWidth(450);
        Usuarios.getColumn("Estado").setPreferredWidth(190);
        Usuarios.getColumn("Cargo en sistema").setPreferredWidth(210);
    }

    //Inside Task
    private void loadUsers() {
        model.setRowCount(0);
        List<Object[]> usuarios = Conection.createEntityManagerFactory().createEntityManager()
                .createNativeQuery("SELECT UsuarioID, Nombre, Estado, Cargo FROM Usuario").getResultList();
        usuarios.forEach(usuario -> {
            usuario[2] = usuario[2].toString().equals("1") ? "Activo" : "Inactivo";
            usuario[3] = usuario[3].toString().equals("A") ? "Administrador" : "Dependiente";
            model.addRow(usuario);
        });
    }

    public void filter() {List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        filters.add(RowFilter.regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 0, 1));
        filters.add(RowFilter.regexFilter(Cargo.getSelectedIndex() == 0 ? "" : Cargo.getSelectedItem().toString(), 3));
        
        TableRowSorter s = new TableRowSorter(Usuarios.getModel());
        s.setRowFilter(RowFilter.andFilter(filters));
        Usuarios.setRowSorter(s);
    }

    public void editUser() {
        int fila = Usuarios.getSelectedRow();
        if (fila >= 0) {
            Dialogs.ShowModifyUsuarioDialog(Integer.parseInt(Usuarios.getValueAt(fila, 0).toString()));
            Init();
        } else {
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }

    //Task
    public void deleteUser() {
        int fila = Usuarios.getSelectedRow();
        if (fila >= 0) {
            if (Dialogs.ShowEnterPasswordDialog("Esta a punto de realizar la eliminacion de un usuario.",
                    "No se podra eliminar en caso de estar enlazado a otros datos.",
                    "Para continuar escriba su contraseña de usuario.", Dialogs.WARNING_ICON)) {
                
                setLoad(true);
                Runnable run = () -> {
                    try {
                        controller.destroy(Integer.valueOf(Usuarios.getValueAt(fila, 0).toString()));
                        Init();
                        
                        setLoad(false);
                        Dialogs.ShowMessageDialog("El usuario ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (IllegalOrphanException | NonexistentEntityException ex) {
                        setLoad(false);
                        Dialogs.ShowMessageDialog("Los datos de este usuario estan enlazados. No pudo ser eliminado", Dialogs.ERROR_ICON);
                    }
                    setLoad(false);
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }

    public void showUserInformation() {
        int fila = Usuarios.getSelectedRow();
        if (fila >= 0) {
            Dialogs.ShowInfoUsuarioDialog(Integer.parseInt(Usuarios.getValueAt(fila, 0).toString()));
        } else {
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }

    //Task
    public void disableUser() {
        int fila = Usuarios.getSelectedRow();
        if (fila >= 0) {
            int State = Usuarios.getValueAt(fila, 2).toString().equals("Activo") ? 0 : 1;
            String text = State == 1 ? "activacion" : "desactivacion";
            if (Dialogs.ShowOKCancelDialog("¿Esta seguro de la "+text+" del usuario seleccionado?", Dialogs.WARNING_ICON)) {
                setLoad(true);
                Runnable run = () ->{
                    try {
                        Usuario usuario = controller.findUsuario(Integer.valueOf(Usuarios.getValueAt(fila, 0).toString()));
                        usuario.setEstado(State);
                        controller.edit(usuario);
                        Init();
                        setLoad(false);
                        Dialogs.ShowMessageDialog("El usuario ha sido desactivado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (IllegalOrphanException | NonexistentEntityException ex) {
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Los datos de este usuario estan enlazados a otros. No se pudo desactivar", Dialogs.ERROR_ICON);
                    } catch (Exception ex) {
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                    }
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione un usuario de la lista", Dialogs.ERROR_ICON);
        }
    }
}
