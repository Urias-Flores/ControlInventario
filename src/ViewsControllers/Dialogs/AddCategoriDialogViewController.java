package ViewsControllers.Dialogs;

import Controllers.CategoriaJpaController;
import Models.Categoria;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddCategoriaDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCategoriDialogViewController {
    private AddCategoriaDialog Instance;
    private JTextField Nombre;
    private JLabel Error;
    private JLabel Cargando;

    public AddCategoriDialogViewController(AddCategoriaDialog Instance, JTextField Nombre, JLabel Error, JLabel Cargando) {
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
                CategoriaJpaController controller = new CategoriaJpaController(Conection.createEntityManagerFactory());
                List<Categoria> categorias = controller.findCategoriaEntities();
                
                if(!validateExist(categorias)){
                    Categoria categoria = new Categoria();
                    categoria.setNombre(Nombre.getText());
                    controller.create(categoria);
                    
                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("La categoria ha sido agregada exitosamente", Dialogs.COMPLETE_ICON);
                } else {
                    setLoad(false);
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("El nombre de la categoria ingresada ya existe");
                }
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty()){
            Error.setText("El nombre de la categoria es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 60){
            Error.setText("La categoria solo puede contener 60 caracteres");
            return false;
        }
        return true;
    }
    
    private boolean validateExist(List<Categoria> categorias){
        if(!categorias.isEmpty()){
            for(Categoria categoria : categorias){
                if(categoria.getNombre().equalsIgnoreCase(Nombre.getText())){
                    return true;
                }
            }   
        }
        return false;
    }
}
