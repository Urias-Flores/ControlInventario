package ViewsControllers.Dialogs;

import Resource.LocalDataController;
import Views.Dialogs.AddArqueoDetalleDialog;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddArqueoDetalleDialogViewController {
    
    private AddArqueoDetalleDialog Instance;
    
    private JTextField TotalFactura;
    private JTextField TotalEfectivo;
    private JTextField TotalCambio;
    
    private JLabel Error;
    private JLabel Cargando;

    private int FacturaID = 0;
    private boolean isCredit = false;
    
    public AddArqueoDetalleDialogViewController(AddArqueoDetalleDialog Instance, JTextField TotalFactura, JTextField TotalEfectivo, JTextField TotalCambio, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.TotalFactura = TotalFactura;
        this.TotalEfectivo = TotalEfectivo;
        this.TotalCambio = TotalCambio;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    public void setBillInformation(int FacturaID, boolean isCredit, float Total){
        this.FacturaID = FacturaID;
        this.isCredit = isCredit;
        TotalFactura.setText(getNumberFormat(Total));
    }
    
    public float addPayBill(){
        LocalDataController ldc = new LocalDataController();
        if(validate()){
            float totalFactura = Float.parseFloat(TotalFactura.getText().replace(",", ""));
            float totalEfectivo = Float.parseFloat(TotalEfectivo.getText().replace(",", ""));
            float totalCambio = Float.parseFloat(TotalCambio.getText().replace(",", ""));
            ldc.insertArqueoDetalle(FacturaID, totalFactura, totalEfectivo, totalCambio);
            return totalEfectivo;
        }
        return 0;
    }
    
    public void updateCambio(){
        if(validate()){
            Error.setBackground(Color.white);
            float totalFactura = Float.parseFloat(TotalFactura.getText().replace(",", ""));
            float totalEfectivo = Float.parseFloat(TotalEfectivo.getText().replace(",", ""));

            float totalCambio = totalEfectivo - totalFactura;
            TotalCambio.setText(getNumberFormat(totalCambio));  
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    private boolean validate(){
        float totalFactura;
        float totalEfectivo;
        if(TotalFactura.getText().isEmpty()){
            Error.setText("El total no puede estar vacio");
            return false;
        }
        try {
            totalFactura = Float.parseFloat(TotalFactura.getText().replace(",", ""));
            if(totalFactura == 0){
                Error.setText("El total debe de ser mayor que cero");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("El total debe de ser un numero");
            return false;
        }
        
        if(TotalEfectivo.getText().isEmpty()){
            Error.setText("El total no puede estar vacio");
            return false;
        }
        try {
            totalEfectivo = Float.parseFloat(TotalEfectivo.getText().replace(",", ""));
            if(!isCredit && totalEfectivo == 0){
                Error.setText("El total debe de ser mayor que cero");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("El total de efectivo debe de ser un numero");
            return false;
        }
        if(totalEfectivo < totalFactura){
            Error.setText("El efectivo ingresado no pueder menor que el total de factura");
            return false;    
        }
        return true;
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
