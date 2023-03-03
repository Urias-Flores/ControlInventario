package ViewsControllers.Dialogs;

import Resource.LocalDataController;
import Resource.Utilities;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddArqueoDetalleDialogViewController {
    
    private JTextField TotalFactura;
    private JTextField TotalEfectivo;
    private JTextField TotalCambio;
    
    private JLabel Error;
    private JLabel Cargando;

    private int FacturaID = 0;
    private String payType = "";
    private String transactionType = "";
    
    public AddArqueoDetalleDialogViewController(JTextField TotalFactura, JTextField TotalEfectivo, JTextField TotalCambio, 
                                                JLabel Error, JLabel Cargando) {
        this.TotalFactura = TotalFactura;
        this.TotalEfectivo = TotalEfectivo;
        this.TotalCambio = TotalCambio;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    public void setBillInformation(int FacturaID, String Type, float Total, String TransactionType){
        this.FacturaID = FacturaID;
        this.payType = Type;
        this.transactionType = TransactionType;
        TotalFactura.setText(getNumberFormat(Total));
    }
    
    public float addPayBill(){
        LocalDataController ldc = new LocalDataController();
        if(validate()){
            float totalFactura = Float.parseFloat(TotalFactura.getText().replace(",", ""));
            float totalEfectivo = Float.parseFloat(TotalEfectivo.getText().replace(",", ""));
            float totalCambio = Float.parseFloat(TotalCambio.getText().replace(",", ""));
            ldc.insertArqueoDetalle(FacturaID, totalFactura, totalEfectivo, totalCambio, transactionType);
            return totalEfectivo;
        } else { Error.setBackground(new Color(185, 0, 0)); return 0;}
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
            if(totalFactura == 0 && payType.equals("CN")){
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
            if(payType.equals("CN") && totalEfectivo == 0){
                Error.setText("El total debe de ser mayor que cero");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("El total de efectivo debe de ser un numero");
            return false;
        }
        if(totalEfectivo < totalFactura && payType.equals("CN")){
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
