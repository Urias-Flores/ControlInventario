package ViewsControllers.Panels.Estadisticas;

import Models.Arqueodetalle;
import Models.Usuario;
import Models.Venta;
import Reports.Reports;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AccionesViewController {

    private JLabel Ventas;
    private JLabel Compras;
    private JTable Transacciones;
    private JComboBox Usuarios;
    private JComboBox ProveedoresClientes;
    private JLabel Cargando;
    private JComboBox TipoTransaccion;
    private JComboBox Intervalo;
    private JComboBox DiaInicial;
    private JComboBox MesInicial;
    private JComboBox AnioInicial;
    private JComboBox DiaFinal;
    private JComboBox MesFinal;
    private JComboBox AnioFinal;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public AccionesViewController(JLabel Ventas, JLabel Compras, JTable Transacciones, JComboBox Usuarios, JComboBox ProveedoresClientes, JLabel Cargando, JComboBox TipoTransaccion, JComboBox Intervalo, JComboBox DiaInicial, JComboBox MesInicial, JComboBox AnioInicial, JComboBox DiaFinal, JComboBox MesFinal, JComboBox AnioFinal) {
        this.Ventas = Ventas;
        this.Compras = Compras;
        this.Transacciones = Transacciones;
        this.Usuarios = Usuarios;
        this.ProveedoresClientes = ProveedoresClientes;
        this.Cargando = Cargando;
        this.TipoTransaccion = TipoTransaccion;
        this.Intervalo = Intervalo;
        this.DiaInicial = DiaInicial;
        this.MesInicial = MesInicial;
        this.AnioInicial = AnioInicial;
        this.DiaFinal = DiaFinal;
        this.MesFinal = MesFinal;
        this.AnioFinal = AnioFinal;

        //Cargando model de tabla
        setModelTable();
        
        //Cargando usuarios
        loadUsers();
        
        //Cargando usuarios y proveedores
        loadClientsSuppliers();
        
        //Inicializando carga de datos
        Init();
    }

    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void Init() {
        setLoad(true);
        Runnable run = () -> {
            //Cargando datos
            loadTable();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }

    private void setModelTable() {
        String[] Columns = {"No.", "Tipo", "Usuario", "Cliente/Proveedor", "Fecha", "Total"};
        model.setColumnIdentifiers(Columns);

        Transacciones.setModel(model);
        Transacciones.getColumn("No.").setPreferredWidth(10);
        Transacciones.getColumn("Tipo").setPreferredWidth(80);
        Transacciones.getColumn("Usuario").setPreferredWidth(100);
        Transacciones.getColumn("Cliente/Proveedor").setPreferredWidth(120);
        Transacciones.getColumn("Fecha").setPreferredWidth(80);
        Transacciones.getColumn("Total").setPreferredWidth(120);
    }

    //Inside Task
    private void loadTable() {
        model.setRowCount(0);
        StoredProcedureQuery sp = Conection.createEntityManagerFactory().createEntityManager()
                .createStoredProcedureQuery("ProcedureComprasVentasRegistros")
                .registerStoredProcedureParameter("registros", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);

        sp.setParameter("registros", TipoTransaccion.getSelectedIndex());
        sp.setParameter("tiempo", Intervalo.getSelectedIndex());
        if (Intervalo.getSelectedIndex() < 5) {
            sp.setParameter("FechaInicio", null);
            sp.setParameter("FechaFinal", null);
        } else {
            Date fechaInicio = generateInitialDate();
            Date fechaFinal = generateEndDate();

            sp.setParameter("FechaInicio", fechaInicio);
            sp.setParameter("FechaFinal", fechaFinal);

            System.err.println("Test Fecha Inicio: " + fechaInicio.toString());
            System.err.println("Test Fecha Final: " + fechaFinal.toString());
        }

        List<Object[]> transacciones = sp.getResultList();
        transacciones.forEach(transaccion -> {
            transaccion[0] = new DecimalFormat("00000000").format(transaccion[0]);
            transaccion[5] = getNumberFormat(Float.parseFloat(transaccion[5].toString()));
            model.addRow(transaccion);
        });

        Usuarios.setSelectedIndex(0);
        ProveedoresClientes.setSelectedIndex(0);
        loadTotalBySelection();
    }

    private void loadUsers() {
        setLoad(true);
        Runnable run = () -> {
            List<Usuario> usuarios = Conection.createEntityManagerFactory().createEntityManager()
                    .createNamedQuery("Usuario.findAll").getResultList();
            usuarios.forEach(Usuarios::addItem);
            setLoad(false);
        };
        new Thread(run).start();
    }

    private void loadClientsSuppliers() {
        setLoad(true);
        Runnable run = () -> {
            List<Object> entidades = Conection.createEntityManagerFactory().createEntityManager()
                    .createNativeQuery("SELECT * FROM viewproveedoresclientes").getResultList();
            entidades.forEach(ProveedoresClientes::addItem);
            setLoad(false);
        };
        new Thread(run).start();
    }

    public void filter() {
        TableRowSorter s = new TableRowSorter(Transacciones.getModel());
        List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        filters.add(RowFilter.regexFilter(Usuarios.getSelectedIndex() != 0 ? Usuarios.getSelectedItem().toString() : "" , 2));
        filters.add(RowFilter.regexFilter
        (ProveedoresClientes.getSelectedIndex() != 0 ? ProveedoresClientes.getSelectedItem().toString() : "", 3));
        s.setRowFilter(RowFilter.andFilter(filters));
        Transacciones.setRowSorter(s);
    }

    public void loadTotalBySelection() {
        StoredProcedureQuery sp = Conection.createEntityManagerFactory()
                .createEntityManager().createStoredProcedureQuery("ProcedureComprasVentas")
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);

        sp.setParameter("tiempo", Intervalo.getSelectedIndex());
        sp.setParameter("FechaInicio", generateInitialDate());
        sp.setParameter("FechaFinal", generateEndDate());

        List<Object[]> transacciones = sp.getResultList();

        float ventas = 0;
        float compras = 0;
        try {
            ventas = Float.parseFloat(transacciones.get(0)[0].toString());
            compras = Float.parseFloat(transacciones.get(1)[0].toString());

            Ventas.setText(getNumberFormat(ventas));
            Compras.setText(getNumberFormat(compras));
        } catch (NumberFormatException | NullPointerException ex) {
            System.out.println("Test (getTotalOfSP): Total = 0 or null");
        }
        Ventas.setText(getNumberFormat(ventas));
        Compras.setText(getNumberFormat(compras));
    }

    private Date generateInitialDate() {
        int diaInicio = DiaInicial.getSelectedIndex() + 1;
        int mesInicio = MesInicial.getSelectedIndex();
        int anioInicio = Integer.parseInt(AnioInicial.getSelectedItem().toString());

        return new Date(anioInicio - 1900, mesInicio, diaInicio);
    }

    private Date generateEndDate() {
        int diaFinal = DiaFinal.getSelectedIndex() + 1;
        int mesFinal = MesFinal.getSelectedIndex();
        int anioFinal = Integer.parseInt(AnioFinal.getSelectedItem().toString());

        return new Date(anioFinal - 1900, mesFinal, diaFinal);
    }

    public void showBillInformation() {
        int fila = Transacciones.getSelectedRow();
        if (fila >= 0) {
            int ID = Integer.parseInt(Transacciones.getValueAt(fila, 0).toString());

            if (Transacciones.getValueAt(fila, 1).toString().equals("Compra")) {
                Dialogs.ShowDetalleCompra(ID);
            } else {
                Dialogs.ShowDetalleFactura(ID);
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione una transaccion de la lista", fila);
        }
    }
    
    public void printBillInformation(){
        int fila = Transacciones.getSelectedRow();
        if(fila >= 0){
            String tipo = Transacciones.getValueAt(fila, 1).toString();
            if(Dialogs.ShowOKCancelDialog("¿Desea imprimir la "+tipo.toLowerCase()+" seleccionada?", Dialogs.WARNING_ICON)){
                int TransactionID = Integer.parseInt(Transacciones.getValueAt(fila, 0).toString());
                String type = Transacciones.getValueAt(fila, 1).toString();
     
                if(type.equalsIgnoreCase("Venta")){
                    setLoad(true);
                    Runnable run = () ->{
                        LocalDataController ldc = new LocalDataController();
                        float efectivoLocal = ldc.getTotal(TransactionID, "V");
                        Arqueodetalle arqueo = null;
                        try {
                            arqueo = (Arqueodetalle) Conection.createEntityManager()
                                .createNamedQuery("Arqueodetalle.findByVentaID")
                                .setParameter("facturaID", new Venta(TransactionID))
                                .getSingleResult();
                        } catch (NoResultException e) {
                            
                        }
                        Reports report = new Reports();
                        report.GenerateTickeVenta(TransactionID, 
                                efectivoLocal == -1 ? (arqueo != null ? arqueo.getEfectivo() : 0) : efectivoLocal);
                        setLoad(false); 
                    };
                    new Thread(run).start();
                } else{
                    setLoad(true);
                    Runnable run = () ->{
                        Reports report = new Reports();
                        report.GenerateTicketCompra(TransactionID);
                        setLoad(false);
                    };
                    new Thread(run).start();
                }
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
