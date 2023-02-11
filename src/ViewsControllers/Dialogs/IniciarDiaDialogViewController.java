package ViewsControllers.Dialogs;

import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import Views.Dialogs.IniciarDiaDialog;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class IniciarDiaDialogViewController {
    
    private IniciarDiaDialog Instance;
    private JTextField SaldoInicial;
    private JLabel Error;

    public IniciarDiaDialogViewController(IniciarDiaDialog Instance, JTextField SaldoInicial, JLabel Error) {
        this.Instance = Instance;
        this.SaldoInicial = SaldoInicial;
        this.Error = Error;
    }
    
    public void saveArqueo(){
        LocalDataController ldc = new LocalDataController();
        if(validate()){
            ldc.initDay(Utilities.getUsuarioActual().getUsuarioID(), Float.valueOf(SaldoInicial.getText().replace(",", "")));
            
            Instance.setVisible(false);
            Dialogs.ShowMessageDialog("El dia ha sido iniciado con un saldo de "+SaldoInicial.getText(), Dialogs.COMPLETE_ICON);
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    private boolean validate(){
        if(SaldoInicial.getText().isEmpty()){
            Error.setText("El saldo inicial es obligatorio");
            return false;
        }
        try {
            float saldoInicial = Float.parseFloat(SaldoInicial.getText().replace(",", ""));
            
            if(saldoInicial < 0){
                Error.setText("El saldo inicial debe ser mayor o igual a cero");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("El saldo inicial debe ser un numero");
            return false;
        }
        return true;
    }
}
