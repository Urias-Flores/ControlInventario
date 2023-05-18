package ViewsControllers.Panels.Preferencias;

import Controllers.AbonoJpaController;
import Models.Usuario;
import Resource.Conection;
import Resource.Utilities;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class DepositosViewController {
    
    private AbonoJpaController controller = null;
     
    private JComboBox Usuarios;
    private JComboBox ClienteProveedor;
    private JComboBox Intervalo;
    private JTable Depositos;
    private JLabel DepositosRecibidos;
    private JLabel DepositosRealizados;
    private JRadioButton VerResumen;
    private JRadioButton VerDepositos;
    private JLabel Cargando;
    
    
    private JComboBox DiaInicial;
    private JComboBox MesInicial;
    private JComboBox AnioInicial;
    
    private JComboBox DiaFinal;
    private JComboBox MesFinal;
    private JComboBox AnioFinal;

    private boolean isResumenMode = true;
    
    private DefaultTableModel model = new DefaultTableModel(){
      @Override
      public boolean isCellEditable(int row, int column){ return false; }
    };

    public DepositosViewController(JComboBox Usuarios, JComboBox ClienteProveedor, JComboBox Intervalo, JTable Depositos, 
            JLabel DepositosRecibidos, JLabel DepositosRealizados, JRadioButton VerResumen, JRadioButton VerDepositos, JLabel Cargando,
            JComboBox DiaInicial, JComboBox MesInicial, JComboBox AnioInicial, JComboBox DiaFinal, JComboBox MesFinal, JComboBox AnioFinal) {
        this.Usuarios = Usuarios;
        this.ClienteProveedor = ClienteProveedor;
        this.Intervalo = Intervalo;
        this.Depositos = Depositos;
        this.DepositosRecibidos = DepositosRecibidos;
        this.DepositosRealizados = DepositosRealizados;
        this.VerResumen = VerResumen;
        this.VerDepositos = VerDepositos;
        this.Cargando = Cargando;
        
        this.DiaInicial = DiaInicial;
        this.MesInicial = MesInicial;
        this.AnioInicial = AnioInicial;
        
        this.DiaFinal = DiaFinal;
        this.MesFinal = MesFinal;
        this.AnioFinal = AnioFinal;
        
        controller = new AbonoJpaController(Conection.createEntityManagerFactory());
        
        //Insertando modelo inicial
        setModelResume();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void Init(){
        setLoad(true);
        Runnable run = ()->{
            //Cargando lista de usuarios en combo box
            loadUsers();
            
            //Cargando lista de proveedores y clientes en combo box
            loadClientSupplier();
            
            //Cargando datos en tabla
            loadTable();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void applyChanges(){
        setLoad(true);
        Runnable run = () -> {
            loadTable();
            filter();
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    private void setModelResume(){
        model.setRowCount(0);
        String[] columns = {"Fecha", "Usuario", "Cliente/Proveedor", "Tipo", "Total"};
        model.setColumnIdentifiers(columns);
        
        Depositos.setModel(model);
        Depositos.getColumn("Fecha").setPreferredWidth(15);
        Depositos.getColumn("Usuario").setPreferredWidth(100);
        Depositos.getColumn("Cliente/Proveedor").setPreferredWidth(100);
        Depositos.getColumn("Tipo").setPreferredWidth(120);
        Depositos.getColumn("Total").setPreferredWidth(80);
        
        isResumenMode = true;
    }
    
    private void setModelDetail(){
        model.setRowCount(0);
        String[] columns = {"No. Abono", "No. Transacción", "Tipo", "Fecha", "Hora", "Usuario", "Cliente/Proveedor", "Total"};
        model.setColumnIdentifiers(columns);
        
        Depositos.setModel(model);
        Depositos.getColumn("No. Abono").setPreferredWidth(15);
        Depositos.getColumn("No. Transacción").setPreferredWidth(100);
        Depositos.getColumn("Tipo").setPreferredWidth(100);
        Depositos.getColumn("Fecha").setPreferredWidth(120);
        Depositos.getColumn("Hora").setPreferredWidth(80);
        Depositos.getColumn("Usuario").setPreferredWidth(80);
        Depositos.getColumn("Cliente/Proveedor").setPreferredWidth(80);
        Depositos.getColumn("Total").setPreferredWidth(80);
        
        isResumenMode = false;
    }
    
    private void loadUsers(){
        Usuarios.removeAllItems();
        Usuarios.addItem("-- Todos los usuarios --");
        List<Usuario> usuarios = Conection.createEntityManager().createNamedQuery("Usuario.findAll").getResultList();
        usuarios.forEach(Usuarios::addItem);
    }
    
    private void loadClientSupplier(){
        ClienteProveedor.removeAllItems();
        ClienteProveedor.addItem("-- Todos los clientes/proveedores --");
        List<Object> entidades = Conection.createEntityManagerFactory().createEntityManager()
                .createNativeQuery("SELECT * FROM viewproveedoresclientes").getResultList();
        entidades.forEach(ClienteProveedor::addItem);
    }
    
    private void loadTable(){
        model.setRowCount(0);
        if(VerResumen.isSelected()){
            if (!isResumenMode){ setModelResume(); }
            StoredProcedureQuery ps = Conection.createEntityManagerFactory().createEntityManager()
                    .createStoredProcedureQuery("psAbonosResumen")
                    .registerStoredProcedureParameter("Type", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("FechaInicial", Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);
            ps.setParameter("Type", Intervalo.getSelectedIndex());
            ps.setParameter("FechaInicial", generateInitialDate());
            ps.setParameter("FechaFinal", generateEndDate());
            List<Object[]> data = ps.getResultList();
            
            data.forEach(newData -> {
                model.addRow(newData);
            });
            
            updateSumResume();
        } else if(VerDepositos.isSelected()) {
            if (isResumenMode){ setModelDetail(); }
            StoredProcedureQuery ps = Conection.createEntityManagerFactory().createEntityManager()
                    .createStoredProcedureQuery("psAbonosDetallado")
                    .registerStoredProcedureParameter("Type", Integer.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("FechaInicial", Date.class, ParameterMode.IN)
                    .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);
            ps.setParameter("Type", Intervalo.getSelectedIndex());
            ps.setParameter("FechaInicial", generateInitialDate());
            ps.setParameter("FechaFinal", generateEndDate());
            List<Object[]> data = ps.getResultList();
            data.forEach(newData -> {
                model.addRow(newData);
            });
            updateSumResume();
        }
    }
    
    private void filter(){
        RowFilter<TableModel, String> filterUser = RowFilter
            .regexFilter(Usuarios.getSelectedIndex() > 0 ? Usuarios.getSelectedItem().toString() : "", isResumenMode ? 1 : 5);
        RowFilter<TableModel, String> filterClienteProveedor = RowFilter
                .regexFilter(ClienteProveedor.getSelectedIndex() > 0 ? ClienteProveedor.getSelectedItem().toString() : "", isResumenMode ? 2 : 6);

        List<RowFilter<TableModel, String>> filters = Arrays.asList(filterUser, filterClienteProveedor);
        TableRowSorter s = new TableRowSorter(Depositos.getModel());
        s.setRowFilter(RowFilter.andFilter(filters));
        Depositos.setRowSorter(s);
    }
    
    private void updateSumResume(){
        float totalDepositoCaja = 0;
        float totalDepositoProveedor = 0;
        
        int index = 0;
        while(index < Depositos.getRowCount()){
            float value = Float.parseFloat(Depositos.getValueAt(index, isResumenMode ? 4 : 7).toString());
            if(value > 0){
                totalDepositoCaja += value;
            } else {
                totalDepositoProveedor += (-value);
            }
            index++;
        }
        
        DepositosRecibidos.setText(getNumberFormat(totalDepositoCaja));
        DepositosRealizados.setText(getNumberFormat(totalDepositoProveedor));
    }
    
    private java.sql.Date generateInitialDate() {
        int diaInicio = DiaInicial.getSelectedIndex() + 1;
        int mesInicio = MesInicial.getSelectedIndex();
        int anioInicio = Integer.parseInt(AnioInicial.getSelectedItem().toString());

        return new java.sql.Date(anioInicio - 1900, mesInicio, diaInicio);
    }

    private java.sql.Date generateEndDate() {
        int diaFinal = DiaFinal.getSelectedIndex() + 1;
        int mesFinal = MesFinal.getSelectedIndex();
        int anioFinal = Integer.parseInt(AnioFinal.getSelectedItem().toString());

        return new java.sql.Date(anioFinal - 1900, mesFinal, diaFinal);
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
