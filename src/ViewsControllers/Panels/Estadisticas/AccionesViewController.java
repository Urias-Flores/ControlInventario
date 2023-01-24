package ViewsControllers.Panels.Estadisticas;

import Models.Usuario;
import Resource.Conection;
import Views.Dialogs.Dialogs;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AccionesViewController {

    JLabel Ventas;
    JLabel Compras;
    JTable Transacciones;
    JComboBox Usuarios;
    JComboBox ProveedoresClientes;
    JComboBox TipoTransaccion;
    JComboBox Intervalo;
    JComboBox DiaInicial;
    JComboBox MesInicial;
    JComboBox AnioInicial;
    JComboBox DiaFinal;
    JComboBox MesFinal;
    JComboBox AnioFinal;

    public AccionesViewController(JLabel Ventas, JLabel Compras, JTable Transacciones, JComboBox Usuarios, JComboBox ProveedoresClientes, JComboBox TipoTransaccion, JComboBox Intervalo, JComboBox DiaInicial, JComboBox MesInicial, JComboBox AnioInicial, JComboBox DiaFinal, JComboBox MesFinal, JComboBox AnioFinal) {
        this.Ventas = Ventas;
        this.Compras = Compras;
        this.Transacciones = Transacciones;
        this.Usuarios = Usuarios;
        this.ProveedoresClientes = ProveedoresClientes;
        this.TipoTransaccion = TipoTransaccion;
        this.Intervalo = Intervalo;
        this.DiaInicial = DiaInicial;
        this.MesInicial = MesInicial;
        this.AnioInicial = AnioInicial;
        this.DiaFinal = DiaFinal;
        this.MesFinal = MesFinal;
        this.AnioFinal = AnioFinal;

        cargarUsuarios();
        cargarProveedoresClientes();
    }

    public void CargarTabla() {
        DefaultTableModel model = new DefaultTableModel();
        String[] Columns = {"No.", "Tipo", "Usuario", "Cliente/Proveedor", "Fecha", "Total"};
        model.setColumnIdentifiers(Columns);

        StoredProcedureQuery sp = Conection.CreateEntityManager().createEntityManager()
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
            Date fechaInicio = generateDateInicial();
            Date fechaFinal = generateDateFinal();

            sp.setParameter("FechaInicio", fechaInicio);
            sp.setParameter("FechaFinal", fechaFinal);

            System.err.println("Test Fecha Inicio: " + fechaInicio.toString());
            System.err.println("Test Fecha Final: " + fechaFinal.toString());
        }

        List<Object[]> transacciones = sp.getResultList();
        transacciones.forEach(transaccion -> {
            transaccion[5] = getNumberFormat(Float.parseFloat(transaccion[5].toString()));
            model.addRow(transaccion);
        });

        Transacciones.setModel(model);
        Usuarios.setSelectedIndex(0);
        ProveedoresClientes.setSelectedIndex(0);
        setColumnsWidths();
        CargarTotalesPorSeleccion();
    }

    private void cargarUsuarios() {
        List<Usuario> usuarios = Conection.CreateEntityManager().createEntityManager()
                .createNamedQuery("Usuario.findAll").getResultList();
        usuarios.forEach(Usuarios::addItem);
    }

    private void cargarProveedoresClientes() {
        List<Object> entidades = Conection.CreateEntityManager().createEntityManager()
                .createNativeQuery("SELECT * FROM viewproveedoresclientes").getResultList();
        entidades.forEach(ProveedoresClientes::addItem);
    }

    public void filtrarUsuario() {
        TableRowSorter s = new TableRowSorter(Transacciones.getModel());
        if(Usuarios.getSelectedIndex() != 0){
            s.setRowFilter(RowFilter.regexFilter(Usuarios.getSelectedItem().toString(), 2));
        }else{
            s.setRowFilter(RowFilter.regexFilter("", 2));
        }
        Transacciones.setRowSorter(s);
    }

    public void filtrarClienteProveedor() {
        TableRowSorter s = new TableRowSorter(Transacciones.getModel());
        if(ProveedoresClientes.getSelectedIndex() != 0){
            s.setRowFilter(RowFilter.regexFilter(ProveedoresClientes.getSelectedItem().toString(), 3));
        }else{
            s.setRowFilter(RowFilter.regexFilter("", 3));
        }
        Transacciones.setRowSorter(s);
    }

    public void CargarTotalesPorSeleccion() {
        StoredProcedureQuery sp = Conection.CreateEntityManager()
                .createEntityManager().createStoredProcedureQuery("ProcedureComprasVentas")
                .registerStoredProcedureParameter("tiempo", Integer.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaInicio", Date.class, ParameterMode.IN)
                .registerStoredProcedureParameter("FechaFinal", Date.class, ParameterMode.IN);

        sp.setParameter("tiempo", Intervalo.getSelectedIndex());
        sp.setParameter("FechaInicio", generateDateInicial());
        sp.setParameter("FechaFinal", generateDateFinal());

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

    private Date generateDateInicial() {
        int diaInicio = DiaInicial.getSelectedIndex() + 1;
        int mesInicio = MesInicial.getSelectedIndex();
        int anioInicio = Integer.parseInt(AnioInicial.getSelectedItem().toString());

        return new Date(anioInicio - 1900, mesInicio, diaInicio);
    }

    private Date generateDateFinal() {
        int diaFinal = DiaFinal.getSelectedIndex() + 1;
        int mesFinal = MesFinal.getSelectedIndex();
        int anioFinal = Integer.parseInt(AnioFinal.getSelectedItem().toString());

        return new Date(anioFinal - 1900, mesFinal, diaFinal);
    }

    private void setColumnsWidths() {
        Transacciones.getColumn("No.").setPreferredWidth(10);
        Transacciones.getColumn("Tipo").setPreferredWidth(80);
        Transacciones.getColumn("Usuario").setPreferredWidth(100);
        Transacciones.getColumn("Cliente/Proveedor").setPreferredWidth(120);
        Transacciones.getColumn("Fecha").setPreferredWidth(80);
        Transacciones.getColumn("Total").setPreferredWidth(120);
    }

    public void ShowInfoFactura() {
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

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
