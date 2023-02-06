package ViewsControllers.Dialogs;

import Controllers.CategoriaJpaController;
import Models.Categoria;
import Resource.Conection;
import java.awt.Color;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCategoriDialogViewController {
    CategoriaJpaController controller;
    JTextField Nombre;
    JLabel Error;

    public AddCategoriDialogViewController(JTextField Nombre, JLabel Error) {
        this.Nombre = Nombre;
        this.Error = Error;
        controller = new CategoriaJpaController(Conection.createEntityManagerFactory());
    }
    
    public boolean Insert(){
        if(validate()){
            Categoria categoria = new Categoria();
            categoria.setNombre(Nombre.getText());
            controller.create(categoria);
            return true;
        }
        return false;
    }
    
    public boolean validate(){
        if(Nombre.getText().isEmpty()){
            Error.setBackground(new Color(185, 0 , 0));
            Error.setText("El nombre de la categoria es obligatorio");
            return false;
        }
        if(validateExist(Nombre.getText())){
            Error.setBackground(new Color(185, 0 , 0));
            Error.setText("El nombre de la categoria ingresada ya existe");
            return false;
        }
        return true;
    }
    
    public boolean validateExist(String marcaIngresar){
        List<Categoria> categorias = controller.findCategoriaEntities();
        for(int i = 0; i < categorias.size(); i++){
            if(categorias.get(i).getNombre().equalsIgnoreCase(marcaIngresar)){
                return true;
            }
        }
        return false;
    }
}
