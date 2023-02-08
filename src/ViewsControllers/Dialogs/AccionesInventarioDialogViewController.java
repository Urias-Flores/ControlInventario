package ViewsControllers.Dialogs;

import Controllers.InventarioJpaController;
import Controllers.InventariodetalleaccionesJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.sql.Time;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AccionesInventarioDialogViewController {
    private JTextField Buscar;
    private JComboBox Tipo;
    private JTable Acciones;
    private JLabel Cargando;
    
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };
    
    public AccionesInventarioDialogViewController(JTextField Buscar, JComboBox Tipo, JTable Acciones, JLabel Cargando) {
        this.Buscar = Buscar;
        this.Tipo = Tipo;
        this.Acciones = Acciones;
        this.Cargando = Cargando;
        //Cargando model de tabla en tabla de acciones
        setModelTableForAcciones();
        
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
        Runnable run = () -> {
            //Cargando de datos de acciones
            loadActions();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void nombreFuncion(){
        
    }
    
    public void updateData(){
        Init();
    }
    
    private void setModelTableForAcciones(){
        String[] columns = {"Cd.", "Producto", "Usuario", "Fecha", "Accion", "Ext. previa", "Cant. modificada"};
        model.setColumnIdentifiers(columns);
        
        Acciones.setModel(model);
        Acciones.getColumn("Cd.").setPreferredWidth(40);
        Acciones.getColumn("Producto").setPreferredWidth(450);
        Acciones.getColumn("Usuario").setPreferredWidth(110);
        Acciones.getColumn("Fecha").setPreferredWidth(70);
        Acciones.getColumn("Accion").setPreferredWidth(80);
        Acciones.getColumn("Ext. previa").setPreferredWidth(70);
        Acciones.getColumn("Cant. modificada").setPreferredWidth(70);
    }
    
    private void loadActions(){
        model.setRowCount(0);
        Query query = Conection.createEntityManager()
                .createNativeQuery("select * from viewaccionesinventario ORDER BY Fecha desc , Hora desc;");
        List<Object[]> acciones = query.getResultList();
        acciones.forEach((accion) ->{
            Object[] row = {
                accion[0],
                accion[1],
                accion[2],
                accion[3],
                accion[6] = accion[6].toString().equals("E") ? "Eliminación" : "Modificación",
                accion[7],
                accion[8]
            };
            
            model.addRow(row);
        });
    }
    
    public void filter(){
        List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        
        filters.add(RowFilter.regexFilter(Buscar.getForeground().equals(Color.BLACK) ? "(?i)"+Buscar.getText() : "", 0, 1));
        filters.add(RowFilter.regexFilter(Tipo.getSelectedIndex() == 0 ? "" : Tipo.getSelectedItem().toString(), 4));
        
        TableRowSorter s = new TableRowSorter(Acciones.getModel());
        s.setRowFilter(RowFilter.andFilter(filters));
        Acciones.setRowSorter(s);
    }
    
    public void showCompleteInformation(){
        int fila = Acciones.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoInventarioAccion(Integer.parseInt(Acciones.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione una accion de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void EliminarAccion(){
        int fila = Acciones.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de eliminar la accion seleccionada?", Dialogs.WARNING_ICON)){
                
                setLoad(true);
                Runnable run = () -> {
                    try {
                        
                        InventariodetalleaccionesJpaController controllerDetalle = new InventariodetalleaccionesJpaController
                        (Conection.createEntityManagerFactory());
                        int accionInventarioID = Integer.parseInt(Acciones.getValueAt(fila, 0).toString());
                        
                        if(validateDeleteAction(controllerDetalle, accionInventarioID)){
                            controllerDetalle.destroy(Integer.valueOf(Acciones.getValueAt(fila, 0).toString()));
                            Init();
                            Dialogs.ShowMessageDialog("La accion ha sido eliminada exitosamente", Dialogs.COMPLETE_ICON);
                        }
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                    }
                    setLoad(false);
                };
                new Thread(run).start();
                
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una accion de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    private boolean validateDeleteAction(InventariodetalleaccionesJpaController controllerDetalle, int accionInventarioID){
        
        Inventariodetalleacciones inventariodetalle = controllerDetalle.findInventariodetalleacciones(accionInventarioID);
        Producto producto = inventariodetalle.getProductoID();
        
            List<Inventario> listInventory = producto.getInventarioList();
        if(!listInventory.isEmpty()){
            Inventario inventario = listInventory.get(0);

            Date horaAccion = inventariodetalle.getHora();
            int hora = horaAccion.getHours();
            int minuto = horaAccion.getMinutes();

            Time horaRecurrente = Utilities.getTime();
            int horaActual = horaRecurrente.getHours();
            int minutoActual = horaRecurrente.getMinutes();

            //Comparacion de cantidad en inventario real con cantidad resultado de modificacion
            if(inventario.getCantidad() == inventariodetalle.getCantidadModificada() + inventariodetalle.getExistenciaPrevia()){
                Dialogs.ShowMessageDialog("Ya se realizaron transacciones desde creacion de accion", Dialogs.ERROR_ICON);
                return false;
            }
            //Verificacion que sea del mismo dia
            if(!inventariodetalle.getFecha().equals(Utilities.getDate())){
                Dialogs.ShowMessageDialog("El tiempo de eliminacion (fecha) de esta accion a sido superado", Dialogs.ERROR_ICON);
                return false;
            }
            //Verificando que sean de la misma hora
            if(horaActual > hora && minutoActual > minuto){
                Dialogs.ShowMessageDialog("El tiempo de eliminacion (hora) de esta accion a sido superado", Dialogs.ERROR_ICON);
                return false;
            }
        }
        return true;
    }
}
