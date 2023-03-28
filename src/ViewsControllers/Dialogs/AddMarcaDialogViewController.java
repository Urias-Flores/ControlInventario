package ViewsControllers.Dialogs;

import Controllers.MarcaJpaController;
import Models.Marca;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddMarcaDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddMarcaDialogViewController {
    
    private AddMarcaDialog Instance;
    private JTextField Nombre;
    private JLabel Error;
    private JLabel Cargando;

    public AddMarcaDialogViewController(AddMarcaDialog Instance, JTextField Nombre, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.Error = Error;
        this.Cargando = Cargando;
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void Insert(){
        if(validate()){
            setLoad(true);
            Runnable run = () -> {
                MarcaJpaController controller = new MarcaJpaController(Conection.createEntityManagerFactory());
                List<Marca> marcas = controller.findMarcaEntities();
                if(!validateMarcaExist(marcas)){
                    Marca marca = new Marca();
                    marca.setNombre(Nombre.getText());
                    controller.create(marca);
                    
                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("La marca ha sido agregada exitosamente", Dialogs.COMPLETE_ICON);
                }else{
                    setLoad(false);
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El nombre de la marca ingresada ya existe");
                }
                setLoad(false);
            };
            new Thread(run).start();
            
        }else{ Error.setBackground(new Color(185, 0, 0)); }
    }
    
    private boolean validate(){
        if(Nombre.getText().isEmpty()){
            Error.setText("El nombre de la marca es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 60){
            Error.setText("El nombre únicamente admite 60 caracteres");
            return false;
        }
        return true;
    }
    
    public boolean validateMarcaExist(List<Marca> marcas){
        if(!marcas.isEmpty()){
            for(Marca marca : marcas){
                if(marca.getNombre().equalsIgnoreCase(Nombre.getText())){
                    return true;
                }
            }
        }
        return false;
    }
}
