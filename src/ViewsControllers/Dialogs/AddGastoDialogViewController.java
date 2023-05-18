package ViewsControllers.Dialogs;

import Controllers.GastoJpaController;
import Models.Gasto;
import Resource.Conection;
import Resource.LocalDataController;
import Resource.Utilities;
import Views.Dialogs.AddGastoDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddGastoDialogViewController {
    
    private GastoJpaController controller;
    private AddGastoDialog Instance;
    
    private JTextArea Descripcion;
    private JTextField Total;
    private JLabel Error;
    private JLabel Cargando;

    private int GastoID = 0;
    
    public AddGastoDialogViewController(AddGastoDialog Instance, JTextArea Descripcion, JTextField Total, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Descripcion = Descripcion;
        this.Total = Total;
        this.Error = Error;
        this.Cargando = Cargando;
        
        controller = new GastoJpaController(Conection.createEntityManagerFactory());
    }
    
    private void setLoad(boolean state){
        Icon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    public void save(){
        //En caso de ser cero inserta el gasto como un nuevo gasto
        if (GastoID == 0) {
            insertExpense();
        } else {
            //Caso contrario edita el gasto que se ha deseado
            editExpense();
        }
    }
    
    //Task
    private void insertExpense(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                //Agregando registro de gasto en la base de datos
                Gasto gasto = createObjectExpense();
                controller.create(gasto);
                
                //Agregando gasto a registro de arqueo
                LocalDataController ldc = new LocalDataController();
                ldc.insertArqueoDetalle(
                        gasto.getGastoID(), 
                        -Float.parseFloat(Total.getText().replace(",", "")), 
                        -Float.parseFloat(Total.getText().replace(",", "")), 
                        0, 
                        "G"
                );
                
                setLoad(false);
                Instance.setVisible(false);
                Dialogs.ShowMessageDialog("¡El gasto ha sido agregado exitosamente!", Dialogs.ERROR_ICON);
            };
            new Thread(run).start();            
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    public void setEditingExpense(int GastoID){
        this.GastoID = GastoID;
        Gasto gasto = controller.findGasto(GastoID);
        if(gasto != null){
            Descripcion.setText(gasto.getDescripcion());
            Total.setText(getNumberFormat(-gasto.getTotal()));
        }
    }
    
    //Task
    public void editExpense(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                try {
                    //Editando gasto en base de datos
                    Gasto gasto = createObjectExpense();
                    controller.edit(gasto);
                    
                    //Editando gasto a registro de arqueo
                    LocalDataController ldc = new LocalDataController();
                    ldc.editArqueoDetalle(
                            gasto.getGastoID(), 
                            -Float.parseFloat(Total.getText().replace(",", "")), 
                            -Float.parseFloat(Total.getText().replace(",", "")), 
                            0, 
                            "G"
                    );
                    
                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("¡El gasto ha sido modificado exitosamente!", Dialogs.ERROR_ICON);
                } catch (Exception ex) {
                    setLoad(false);
                    System.err.println("Error al editar gasto: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Error al intentar editar el gasto", Dialogs.ERROR_ICON);
                }
                setLoad(false);
            };
            new Thread(run).start();
        }
    }
    
    //Inside task
    private Gasto createObjectExpense(){
        Gasto gasto = new Gasto();
        if(GastoID > 0){
            gasto = controller.findGasto(GastoID);
        }
        
        gasto.setGastoID(GastoID > 0 ? GastoID : null);
        gasto.setFecha(GastoID > 0 ? gasto.getFecha() : Utilities.getDate());
        gasto.setHora(GastoID > 0  ? gasto.getHora() : Utilities.getTime());
        gasto.setUsuarioID(GastoID > 0 ? gasto.getUsuarioID() : Utilities.getUsuarioActual());
        gasto.setDescripcion(Descripcion.getText());
        gasto.setTotal(-Float.parseFloat(Total.getText().replace(",", "")));
        
        return gasto;
    }
    
    private boolean validate(){
        //Validacion de campo de descripcion
        if(Descripcion.getText().isEmpty()){
            Error.setText("La descripción del gasto es obligatoria");
            return false;
        }
        if(Total.getText().isEmpty()){
            Error.setText("El total del gasto es obligatorio");
            return false;
        }

        //Validacion de campo del total de gasto
        try {
            float total = Float.parseFloat(Total.getText());
            if(total <= 0){
                Error.setText("El total del gasto debe ser mayor 0");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("El total del gasto debe ser un número");
            return false;
        }
        return true;
    }
    
    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
