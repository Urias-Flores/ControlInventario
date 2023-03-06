package ViewsControllers.Dialogs;

import Reports.Reports;
import Resource.LocalDataController;
import Views.Dialogs.AddCierreDialog;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
    
    private JTable Arqueos;
    
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) { return false; }
    };

    public AddCierreDialogViewController(AddCierreDialog Instance, JTextField SaldoInicial, JTable Arqueos, JTextField Facturado, JTextField Efectivo, JTextField Cambio, JTextField SaldoFinal, JTextField SaldoReal) {
        this.Instance = Instance;
        this.SaldoInicial = SaldoInicial;
        this.Arqueos = Arqueos;
        this.Facturado = Facturado;
        this.Efectivo = Efectivo;
        this.Cambio = Cambio;
        this.SaldoFinal = SaldoFinal;
        this.SaldoReal = SaldoReal;
        
        //Cargando modelo de datos
        setModelTable();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setModelTable(){
        String[] column = { "No. Factura", "Tipo","Total", "Efectivo", "Cambio" };
        model.setColumnIdentifiers(column);
        Arqueos.setModel(model);
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
    
    public void saveCloseDay(){
        float saldoReal = Float.parseFloat(SaldoReal.getText().replace(",", ""));
        if(validate()){
            if(Dialogs.ShowOKCancelDialog("¿Esta seguro de realizar cierre ahora?", Dialogs.WARNING_ICON)){
                LocalDataController ldc = new LocalDataController();
                
                Instance.setVisible(false);
                if(Dialogs.ShowOKCancelDialog("¿Enviar a imprimir cierre de ventas ahora?", Dialogs.WARNING_ICON)){
                    Runnable run = () -> {
                        float saldoInicial = Float.parseFloat(SaldoInicial.getText().replace(",", ""));
                        Reports reports = new Reports();
                        reports.GenerateTicketCloseDay(saldoInicial);
                    };
                    new Thread(run).start();
                }
                ldc.closeDay(saldoReal);
                Dialogs.ShowMessageDialog("El cierre se ha guardado exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
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
