package ViewsControllers.Dialogs;

import Controllers.ArqueoJpaController;
import Controllers.ArqueodetalleJpaController;
import Models.Arqueo;
import Models.Arqueodetalle;
import Models.Gasto;
import Models.Solicitud;
import Models.Venta;
import Reports.Reports;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.AddCierreDialog;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddCierreDialogViewController {
    
    private AddCierreDialog Instance;
    private JTextField SaldoInicial;
    private JTextField Facturado;
    private JTextField Efectivo;
    private JTextField Cambio;
    private JTextField SaldoFinal;
    private JTextField SaldoReal;
    private JLabel Cargando;
    
    private JTable Arqueos;
  
    private boolean isExisting = false;
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };
    
    public void setExist(boolean isExist){
        this.isExisting = isExist;
    }

    public AddCierreDialogViewController(AddCierreDialog Instance, JTextField SaldoInicial, JTable Arqueos, JTextField Facturado, 
            JTextField Efectivo, JTextField Cambio, JTextField SaldoFinal, JTextField SaldoReal, JLabel Cargando) {
        this.Instance = Instance;
        this.SaldoInicial = SaldoInicial;
        this.Arqueos = Arqueos;
        this.Facturado = Facturado;
        this.Efectivo = Efectivo;
        this.Cambio = Cambio;
        this.SaldoFinal = SaldoFinal;
        this.SaldoReal = SaldoReal;
        this.Cargando = Cargando;
        
        //Cargando modelo de datos
        setModelTable();
        
        //Inicializando carga de datos
        if(!isExisting){
            Init();
        }
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void setModelTable(){
        String[] column = { "No.", "Tipo", "Total", "Efectivo", "Cambio" };
        model.setColumnIdentifiers(column);
        Arqueos.setModel(model);
    }
    
    public void loadExisting(int ArqueoID){
        Arqueo arqueo = new ArqueoJpaController(Conection.createEntityManagerFactory()).findArqueo(ArqueoID);
        if(arqueo != null){
            SaldoInicial.setText(getNumberFormat(arqueo.getSaldoInicial()));
            SaldoFinal.setText(getNumberFormat(arqueo.getSaldoFinalSistema()));
            SaldoReal.setText(getNumberFormat(arqueo.getSaldoFinalUsuario()));
            
            List<Arqueodetalle> arqueodetalles = arqueo.getArqueodetalleList();
            if(arqueodetalles != null){
                float totalFacturado = 0;
                float totalEfectivo = 0;
                float totalCambio = 0;
                DecimalFormat df = new DecimalFormat("00000000");
                for(Arqueodetalle arqueodetalle : arqueodetalles){
                    Object[] row = {
                        //Cargando no. de factura o solicitud o gasto segun sea el que este disponible
                        arqueodetalle.getFacturaID() != null ? df.format(arqueodetalle.getFacturaID().getVentaID())
                            : arqueodetalle.getSolicitudID() != null ? df.format(arqueodetalle.getSolicitudID().getSolicitudID())
                            : arqueodetalle.getGastoID() != null ? df.format(arqueodetalle.getGastoID().getGastoID()) : df.format(0),
                        
                        //Cargando el tipo segun la disponibilidad del numero
                        arqueodetalle.getFacturaID() != null ? "Venta"
                            : arqueodetalle.getSolicitudID() != null ? "Solicitud"
                            : arqueodetalle.getGastoID() != null ? "Gasto" : 0,
                        
                        //Cargando total de facturacion
                        getNumberFormat(arqueodetalle.getEfectivo() - arqueodetalle.getCambio()),
                        getNumberFormat(arqueodetalle.getEfectivo()),
                        getNumberFormat(arqueodetalle.getCambio())
                    };
                    
                    totalFacturado += arqueodetalle.getEfectivo() - arqueodetalle.getCambio();
                    totalEfectivo += arqueodetalle.getEfectivo();
                    totalCambio += arqueodetalle.getCambio();
                    
                    model.addRow(row);
                }
                Facturado.setText(getNumberFormat(totalFacturado));
                Efectivo.setText(getNumberFormat(totalEfectivo));
                Cambio.setText(getNumberFormat(totalCambio));
            }
        }
    }
    
    private void Init(){
        LocalDataController ldc = new LocalDataController();
        SaldoInicial.setText(getNumberFormat(ldc.getInitSaldo()));
        float totalFacturado = 0;
        float totalEfectivo = 0;
        float totalCambio = 0;
        
        ArrayList<Object[]> arqueos = ldc.getArqueos();
        if(!arqueos.isEmpty()){
            for(Object[] arqueo : arqueos){
                float total = Float.parseFloat(arqueo[2].toString());
                float efectivo = Float.parseFloat(arqueo[3].toString());
                float cambio = Float.parseFloat(arqueo[4].toString());
                arqueo[0] = new DecimalFormat("00000000").format(arqueo[0]);
                arqueo[2] = getNumberFormat(total);
                arqueo[3] = getNumberFormat(efectivo);
                arqueo[4] = getNumberFormat(cambio);
                
                model.addRow(arqueo);
                
                totalFacturado += total;
                totalEfectivo += efectivo;
                totalCambio += cambio;
            }
        }
        
        Facturado.setText(getNumberFormat(totalFacturado));
        Efectivo.setText(getNumberFormat(totalEfectivo));
        Cambio.setText(getNumberFormat(totalCambio));
        SaldoFinal.setText(String.valueOf(getNumberFormat(totalFacturado + ldc.getInitSaldo())));
    }
    
    //Taks
    public void saveCloseDay(){
        float saldoReal = Float.parseFloat(SaldoReal.getText().replace(",", ""));
        if(validate()){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de realizar cierre ahora?", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () -> {
                    LocalDataController ldc = new LocalDataController();
                    
                    //Cerrando arqueo de caja, registrara el valor final en carga
                    ldc.closeDay(saldoReal);
                    
                    //Insertando arqueo final en tabla de arqueos de base de datos
                    Arqueo arqueo = createObjectArqueo();
                    int ArqueoID = new ArqueoJpaController(Conection.createEntityManagerFactory()).create(arqueo);
                    
                    //Insertando lista de detalles del arqueo
                    ArqueodetalleJpaController controllerDetalles = new ArqueodetalleJpaController(Conection.createEntityManagerFactory());
                    List<Arqueodetalle> arqueodetalles = createListArqueoDetalle(ArqueoID);
                    arqueodetalles.forEach(controllerDetalles::create);
                    
                    Instance.setVisible(false);
                    if(Dialogs.ShowOKCancelDialog("¿Enviar a imprimir cierre de ventas ahora?", Dialogs.WARNING_ICON)){
                        Runnable runnner = () -> {
                            Reports reports = new Reports();
                            reports.GenerateTicketArqueo(ArqueoID);
                        };
                        new Thread(runnner).start();
                    }
                    Dialogs.ShowMessageDialog("El cierre se ha guardado exitosamente", Dialogs.COMPLETE_ICON);
                    setLoad(false);
                };
                new Thread(run).start();
            }
        }
    }
    
    private Arqueo createObjectArqueo(){
        Arqueo arqueo = new Arqueo();
        
        arqueo.setUsuarioID(Utilities.getUsuarioActual());
        arqueo.setFecha(Utilities.getDate());
        arqueo.setHora(Utilities.getTime());
        arqueo.setSaldoInicial(Float.parseFloat(SaldoInicial.getText().replace(",", "")));
        arqueo.setSaldoFinalSistema(Float.parseFloat(SaldoFinal.getText().replace(",", "")));
        arqueo.setSaldoFinalUsuario(Float.parseFloat(SaldoReal.getText().replace(",", "")));
        
        return arqueo;
    }
    
    private List<Arqueodetalle> createListArqueoDetalle(int ArqueoID){
        List<Arqueodetalle> list = new ArrayList<>();
        
        int contador = 0;
        while(contador < Arqueos.getRowCount()){
        
            Arqueodetalle arqueodetalle = new Arqueodetalle();
            
            String typeTransaction = Arqueos.getValueAt(contador, 1).toString();
            int TransaccionID = Integer.parseInt(Arqueos.getValueAt(contador, 0).toString());
            
            arqueodetalle.setArqueoID(new Arqueo(ArqueoID));
            arqueodetalle.setFacturaID(typeTransaction.equalsIgnoreCase("Venta") ? new Venta(TransaccionID) : null);
            arqueodetalle.setSolicitudID(typeTransaction.equalsIgnoreCase("Solicitud") ? new Solicitud(TransaccionID) : null);
            arqueodetalle.setGastoID(typeTransaction.equalsIgnoreCase("Gasto") ? new Gasto(TransaccionID) : null);
            arqueodetalle.setEfectivo(Float.parseFloat(Arqueos.getValueAt(contador, 3).toString().replace(",", "")));
            arqueodetalle.setCambio(Float.parseFloat(Arqueos.getValueAt(contador, 4).toString().replace(",", "")));
            
            list.add(arqueodetalle);
            contador++;
        }
        
        return list;
    }
    
    private boolean validate(){
        if(SaldoReal.getText().isEmpty()){
            Dialogs.ShowMessageDialog("El total real es obligatorio", Dialogs.ERROR_ICON);
            return false;
        }
        try {
            float saldoReal = Float.parseFloat(SaldoReal.getText().replace(",", ""));
            if(saldoReal <= 0){
                Dialogs.ShowMessageDialog("El saldo real debe ser mayor a cero", Dialogs.ERROR_ICON);
                return false;
            }
        } catch (NumberFormatException e) {
            Dialogs.ShowMessageDialog("El saldo real debe contener unicamente numeros", Dialogs.ERROR_ICON);
            return false;
        }
        return true;
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
