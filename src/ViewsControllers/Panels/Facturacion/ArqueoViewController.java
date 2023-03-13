package ViewsControllers.Panels.Facturacion;

import Controllers.UsuarioJpaController;
import Models.Usuario;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ArqueoViewController {
    private JComboBox Usuarios;
    private JComboBox Tiempo;
    private JLabel Descuadre;
    private JLabel Cargando;
    
    private JTable Arqueos;
  
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public ArqueoViewController(JLabel Descuadre, JLabel Cargando, JComboBox Usuarios, JComboBox Tiempo, JTable Arqueos) {
        this.Descuadre = Descuadre;
        this.Cargando = Cargando;
        this.Usuarios = Usuarios;
        this.Tiempo = Tiempo;
        this.Arqueos = Arqueos;
        
        //Agregando modelo de tabla
        setTableModel();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    private void Init(){
        setLoad(true);
        Runnable run = () -> {
            //Cargando usuarios
            loadUsers();
            
            //Cargando arqueos
            loadArqueos();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    private void setTableModel(){
       String[] columns = {"No.", "Fecha", "Hora", "Usuario", "Saldo Inicial", "Saldo Final Sistema", "Saldo Final Usuario"};
       model.setColumnIdentifiers(columns);
      
       Arqueos.setModel(model);
       Arqueos.getColumn("No.").setPreferredWidth(10);
       Arqueos.getColumn("Fecha").setPreferredWidth(60);
       Arqueos.getColumn("Hora").setPreferredWidth(60);
       Arqueos.getColumn("Usuario").setPreferredWidth(60);
       Arqueos.getColumn("Saldo Inicial").setPreferredWidth(150);
       Arqueos.getColumn("Saldo Final Sistema").setPreferredWidth(150);
       Arqueos.getColumn("Saldo Final Usuario").setPreferredWidth(150);
    }
    
    private void loadUsers(){
        Usuarios.removeAllItems();
        Usuarios.addItem("-- Todos los usuarios --");
        List<Usuario> usuarios = new UsuarioJpaController(Conection.createEntityManagerFactory()).findUsuarioEntities();
        usuarios.forEach(Usuarios::addItem);
    }
    
    private void loadArqueos(){
        model.setRowCount(0);
        StoredProcedureQuery sp = Conection.createEntityManager().createStoredProcedureQuery("psLoadArching")
                .registerStoredProcedureParameter("Usuario", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("Tiempo", Integer.TYPE, ParameterMode.IN);
        
        List<Object[]> arqueos = sp.setParameter("Usuario", Usuarios.getSelectedIndex() > 0 ? Usuarios.getSelectedItem().toString() : null)
                           .setParameter("Tiempo", Tiempo.getSelectedIndex()).getResultList();
        
        arqueos.forEach(arqueo ->{
            Object[] row = {
                arqueo[0],
                new SimpleDateFormat("dd/MM/yyyy").format(arqueo[1]),
                new SimpleDateFormat("HH:mm:ss").format(arqueo[2]), 
                arqueo[3],
                getNumberFormat(Float.parseFloat(arqueo[4].toString())), 
                getNumberFormat(Float.parseFloat(arqueo[5].toString())),
                getNumberFormat(Float.parseFloat(arqueo[6].toString()))
            };
            model.addRow(row);
        });
        updateDescuadre();
    }
    
    private void updateDescuadre(){
        float descuadre = 0;
        int index = 0;
        while(index < Arqueos.getRowCount()){
            float finalSistema = Float.parseFloat(Arqueos.getValueAt(index, 5).toString().replace(",", ""));
            float finalUsuario = Float.parseFloat(Arqueos.getValueAt(index, 6).toString().replace(",", ""));
            if(finalSistema >= finalUsuario){
                descuadre += finalSistema - finalUsuario;
            }
            index++;
        }
        Descuadre.setText(getNumberFormat(descuadre));
    }
    
    public void updateData(){
        Init();
    }
    
    public void showArqueo(){
        int fila = Arqueos.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowInfoArqueo(Integer.parseInt(Arqueos.getValueAt(fila, 0).toString()));
        } else {
            Dialogs.ShowMessageDialog("Seleccione un arqueo", Dialogs.ERROR_ICON);
        }
    }
    
    public void printArchingDetail(){
        int fila = Arqueos.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Desea enviar a imprimir el reporte seleccionado?", fila)){
                setLoad(true);
                Runnable run = () -> {
                    Reports reports = new Reports();
                    reports.GenerateTicketArqueo(Integer.parseInt(Arqueos.getValueAt(fila, 0).toString()));
                    setLoad(false);
                };
                new Thread(run).start();
            } else {
                Dialogs.ShowMessageDialog("Seleccione un arqueo de la lista", Dialogs.ERROR_ICON);
            }
        }
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
