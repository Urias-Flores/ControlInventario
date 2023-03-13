package ViewsControllers.Panels.Facturacion;

import Controllers.GastoJpaController;
import Controllers.UsuarioJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Usuario;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class GastoViewController {
    
    private JLabel TotalGasto;
    private JLabel Cargando;
    private JTable Gastos;
    private JComboBox Usuarios;
    private JComboBox Tiempo;
    
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; } 
    };

    public GastoViewController(JLabel TotalGasto, JLabel Cargando, JTable Gastos, JComboBox Usuarios, JComboBox Tiempo) {
        this.TotalGasto = TotalGasto;
        this.Cargando = Cargando;
        this.Gastos = Gastos;
        this.Usuarios = Usuarios;
        this.Tiempo = Tiempo;
        
        //Agregando modelo de tabla
        setModelTable();
        
        //Inicializando las carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando usuarios en combobox
            loadUsers();
            
            //Realizando carga de datos de gasto
            loadExpenses();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTable(){
        String[] columns = {"No.", "Fecha", "Hora", "Usuario",  "Total"};
        model.setColumnIdentifiers(columns);
        
        Gastos.setModel(model);
        Gastos.getColumn("No.").setPreferredWidth(10);
        Gastos.getColumn("Fecha").setPreferredWidth(60);
        Gastos.getColumn("Hora").setPreferredWidth(60);
        Gastos.getColumn("Usuario").setPreferredWidth(60);
        Gastos.getColumn("Total").setPreferredWidth(250);
    }
    
    private void loadUsers(){
        Usuarios.removeAllItems();
        Usuarios.addItem("-- Todos los usuarios --");
        List<Usuario> usuarios = new UsuarioJpaController(Conection.createEntityManagerFactory()).findUsuarioEntities();
        usuarios.forEach(Usuarios::addItem);
    }
    
    private void loadExpenses(){
        model.setRowCount(0);
        StoredProcedureQuery sp = Conection.createEntityManager().createStoredProcedureQuery("psLoadExpenses")
                .registerStoredProcedureParameter("Usuario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Tiempo", Integer.TYPE, ParameterMode.IN);
        
        List<Object[]> gastos = sp.setParameter("Usuario", Usuarios.getSelectedIndex() > 0 ? Usuarios.getSelectedItem().toString() : null)
                           .setParameter("Tiempo", Tiempo.getSelectedIndex()).getResultList();
        
        gastos.forEach(gasto ->{
            Object[] row = {
                gasto[0],
                new SimpleDateFormat("dd/MM/yyyy").format(gasto[1]),
                new SimpleDateFormat("HH:mm:ss").format(gasto[2]), 
                gasto[3],
                getNumberFormat(Float.parseFloat(gasto[4].toString())),
            };
            model.addRow(row);
        });
    }
    
    public void editExpense(){
        int fila = Gastos.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog(
                    "La edición de gastos no es recomendable puede ocasionar", 
                    "desbalances en las cuentas y arqueos previos.", 
                    "Si desea continuar con la edición ingrese su contraseña.", Dialogs.WARNING_ICON)){
                Dialogs.ShowModifyExpenseDialog(Integer.parseInt(Gastos.getValueAt(fila, 0).toString()));
                Init();
            }
            
        } else {
            Dialogs.ShowMessageDialog("Seleccione un gasto de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void deleteExpense(){
        int fila = Gastos.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog(
                    "La eliminación de gastos no es recomendable puede ocasionar", 
                    "desbalances en las cuentas y arqueos previos.", 
                    "Si desea continuar con la eliminación ingrese su contraseña.", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () -> {
                    try {
                        int GastoID = Integer.parseInt(Gastos.getValueAt(fila, 0).toString());
                        new GastoJpaController(Conection.createEntityManagerFactory()).destroy(GastoID);
                        
                        LocalDataController ldc = new LocalDataController();
                        ldc.deleteArqueoDetalle(GastoID, "G");
                        
                        Init();
                        setLoad(false);
                        Dialogs.ShowMessageDialog("El gasto ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        System.err.println("Error al eliminar gasto: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ha ocurrido un error al eliminar el gasto", Dialogs.ERROR_ICON);
                    }   
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione un gasto de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void addExpense(){
        LocalDataController ldc = new LocalDataController();
        if(ldc.validateInitDay()){
            Dialogs.ShowAddExpenseDialog();
            Init();
        }else{
            Dialogs.ShowMessageDialog("El dia de facturacion aun no ha sido iniciado", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
