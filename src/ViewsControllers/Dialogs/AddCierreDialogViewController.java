package ViewsControllers.Dialogs;

import Resource.LocalDataController;
import Views.Dialogs.AddCierreDialog;
import Views.Dialogs.Dialogs;
import java.awt.Dialog;
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
        String[] column = { "No. Factura", "Total", "Efectivo", "Cambio" };
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
                float total = Float.parseFloat(arqueo[1].toString());
                float efectivo = Float.parseFloat(arqueo[2].toString());
                float cambio = Float.parseFloat(arqueo[3].toString());
                
                arqueo[1] = getNumberFormat(total);
                arqueo[2] = getNumberFormat(efectivo);
                arqueo[3] = getNumberFormat(cambio);
                
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
            LocalDataController ldc = new LocalDataController();
            ldc.closeDay(saldoReal);
            
            Instance.setVisible(false);
            Dialogs.ShowMessageDialog("El cierre se ha guardado exitosamente", Dialogs.COMPLETE_ICON);
        }
    }
    
    private boolean validate(){
        if(SaldoReal.getText().isEmpty()){
            Dialogs.ShowMessageDialog("Error, No se puede realizar arqueo con numeros negativos", Dialogs.ERROR_ICON);
            return false;
        }
        try {
            float saldoReal = Float.parseFloat(SaldoReal.getText().replace(",", ""));
            if(saldoReal == 0){
                if(!Dialogs.ShowOKCancelDialog("¿Desea guardar arqueo con un saldo final de 0?", Dialogs.WARNING_ICON)){
                    return false;
                }
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
