package ViewsControllers.Panels.Preferencias;

import Controllers.ProveedorJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Proveedor;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ProveedoresViewController {
    private ProveedorJpaController controller;
    
    private JTextField Buscar;
    private JLabel Cargando;
    private JTable Proveedores;
    private JLabel Total;
    
    private JLabel Nombre;
    private JLabel RTN;
    private JLabel Correo;
    private JLabel Numero;

    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };
    
    public ProveedoresViewController(JTextField Buscar, JLabel Cargando, JTable Proveedores, JLabel Total, JLabel Nombre, JLabel RTN, JLabel Correo, JLabel Numero) {
        this.Buscar = Buscar;
        this.Cargando = Cargando;
        this.Proveedores = Proveedores;
        this.Total = Total;
        this.Nombre = Nombre;
        this.RTN = RTN;
        this.Correo = Correo;
        this.Numero = Numero;
        
        controller = new ProveedorJpaController(Conection.createEntityManagerFactory());
        
        //Cargando modelo de tabla en tabla de proveedores 
        setModelTableSuppliers();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    private void Init(){
        setLoad(true);
        Runnable run = () ->{
            //Cagando datos de proveedores
            loadSuppliers();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableSuppliers(){
        String[] columns = {"No. Proveedor", "Nombre", "RTN", "Saldo"};
        model.setColumnIdentifiers(columns);
        
        Proveedores.setModel(model);
        Proveedores.getColumn("No. Proveedor").setPreferredWidth(175);
        Proveedores.getColumn("Nombre").setPreferredWidth(550);
        Proveedores.getColumn("RTN").setPreferredWidth(200);
        Proveedores.getColumn("Saldo").setPreferredWidth(150);
    }
    
    //Inside Task
    private void loadSuppliers(){
        model.setRowCount(0);
        List<Object[]> proveedores = Conection.createEntityManagerFactory().createEntityManager()
                .createNativeQuery("SELECT ProveedorID, Nombre, RTN, SALDO FROM Proveedor").getResultList();
        proveedores.forEach(proveedor -> {
            proveedor[3] = getNumberFormat(Float.parseFloat(proveedor[3].toString()));
            model.addRow(proveedor);
        });
        updateTotal();
    }
    
    //Task
    public void loadSupplier(){
        int fila = Proveedores.getSelectedRow();
        if(fila >= 0){
            setLoad(true);
            Runnable run = () ->{
                Proveedor proveedor = controller.findProveedor(Integer.valueOf(Proveedores.getValueAt(fila, 0).toString()));
                Nombre.setText(proveedor.getNombre());
                RTN.setText(proveedor.getRtn());
                Correo.setText(proveedor.getCorreoElectronico());
                Numero.setText(proveedor.getNumeroTelefono());
                
                setLoad(false);
            };
            new Thread(run).start();
        }
    }
    
    private void updateTotal(){
        float total = 0;
        for(int i = 0; i < Proveedores.getRowCount(); i++){
            total += Float.parseFloat(Proveedores.getValueAt(i, 3).toString().replace(",", ""));
        }
        Total.setText(getNumberFormat(total));
    }
    
    public void editSupplier(){
        int fila = Proveedores.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowModifyProveedorDialog(Integer.parseInt(Proveedores.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un proveedor de la lisa", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void deleteSupplier(){
       int fila = Proveedores.getSelectedRow();
       if(fila >= 0){
           if(Dialogs.ShowOKCancelDialog("¿Esta seguro que desea eliminar el proveedor seleccionado?", Dialogs.WARNING_ICON)){
                try {
                    controller.destroy(Integer.valueOf(Proveedores.getValueAt(fila, 0).toString()));
                    Init();
                    Dialogs.ShowMessageDialog("El proveedor ha sido eliminado exitosamente", Dialogs.COMPLETE_ICON);
                } catch (NonexistentEntityException ex) {
                    Dialogs.ShowMessageDialog("Proveedore no pudo ser eliminada, exista una relacion con este dato", Dialogs.ERROR_ICON);
                }
           }
       }else{
           Dialogs.ShowMessageDialog("Seleccione un proveedore de la lista", Dialogs.ERROR_ICON);
       }
    }
    
    public void search(){
        TableRowSorter s = new TableRowSorter();
        s.setModel(Proveedores.getModel());
        s.setRowFilter(RowFilter.regexFilter
        (Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)"+Buscar.getText(), 1));
        Proveedores.setRowSorter(s);
    }
    
    public void showSupplierInformation(){
        int fila = Proveedores.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowCuentasProveedorDialog(Integer.parseInt(Proveedores.getValueAt(fila, 0).toString()));
            Init();
        }else{
            Dialogs.ShowMessageDialog("Seleccione un proveedor de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
