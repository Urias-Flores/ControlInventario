package ViewsControllers.Dialogs;

import Controllers.EmpleadoJpaController;
import Controllers.UsuarioJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Empleado;
import Models.Usuario;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddUsuarioDialog;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddUsuarioDialogViewController {
    private UsuarioJpaController controller;
    
    private AddUsuarioDialog Instance;
    private JTextField Nombre;
    private JComboBox Cargo;
    private JTextField Empleado;
    private JLabel Error;
    private JLabel Cargando;
    
    private Usuario currentUsuario = new Usuario();

    public AddUsuarioDialogViewController(AddUsuarioDialog Instance, JTextField Nombre, JComboBox Cargo,JTextField Empleado ,JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Nombre = Nombre;
        this.Cargo = Cargo;
        this.Empleado = Empleado;
        this.Error = Error;
        this.Cargando = Cargando;
        
        controller = new UsuarioJpaController(Conection.createEntityManagerFactory());
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    //Task
    public void Insert(){
        if(validate()){
            setLoad(true);
            Runnable run = ()->{
                List<Usuario> usuarios = controller.findUsuarioEntities();
                if(!existUser(usuarios)){
                    Usuario usuario = CreateObjectUsuario();
                    controller.create(usuario);

                    setLoad(false);
                    Instance.setVisible(false);
                    Dialogs.ShowMessageDialog("El usuario ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                } else { 
                    setLoad(false);
                    Error.setText("Ya existe un usuario con el nombre ingresado");
                    Error.setBackground(new Color(185, 0, 0));
                }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    public void Edit(){
        if(validate()){
            setLoad(true);
            Runnable run = () ->{
                List<Usuario> usuarios = controller.findUsuarioEntities();
                if(!existUser(usuarios)){
                    try {
                        Usuario usuario = CreateObjectUsuario();
                        controller.edit(usuario);

                        setLoad(false);
                        Instance.setVisible(false);
                        Dialogs.ShowMessageDialog("El usuario ha sido agregado exitosamente", Dialogs.COMPLETE_ICON);
                    } catch (NonexistentEntityException ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Los datos de este usuario se encuentran enlzados a otros. No se puedo editar", Dialogs.ERROR_ICON);
                    } catch (Exception ex) {
                        setLoad(false);
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error inesperado", Dialogs.ERROR_ICON);
                    }
                } else { 
                    setLoad(false);
                    Error.setText("Ya existe un usuario con el nombre ingresado");
                    Error.setBackground(new Color(185, 0, 0));
                }
                setLoad(false);
            };
            new Thread(run).start();
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }
    
    //Task
    public void setEditing(int UsuarioID){
        setLoad(true);
        Runnable run = () -> {
            currentUsuario = controller.findUsuario(UsuarioID);
            
            Nombre.setText(currentUsuario.getNombre());
            Nombre.setName(String.valueOf(UsuarioID));
            Nombre.setForeground(Color.BLACK);
            Empleado.setText(currentUsuario.getEmpleadoID().getNombre()+" "+currentUsuario.getEmpleadoID().getApellido());
            Empleado.setName(String.valueOf(currentUsuario.getEmpleadoID().getEmpleadoID()));
            Empleado.setForeground(Color.BLACK);
            Cargo.setSelectedIndex(currentUsuario.getCargo().equals("A") ? 1 : 2);
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Inside Taks
    public Usuario CreateObjectUsuario(){
        
        Usuario usuario = new Usuario();
        Empleado empleado = new EmpleadoJpaController(Conection.createEntityManagerFactory())
                .findEmpleado(Integer.valueOf(Empleado.getName()));
        
        //Verificar si se esta editando
        if(Nombre.getName() != null){
            usuario = currentUsuario;
            usuario.setUsuarioID(Integer.valueOf(Nombre.getName()));
        }
        
        //En caso de que no se este editando
        if(Nombre.getName() == null){
            usuario.setContrasena(null);
            usuario.setToken(null);
            usuario.setEstado(0);
        }
        
        usuario.setEmpleadoID(empleado);
        usuario.setNombre(Nombre.getText());
        usuario.setCargo(Cargo.getSelectedIndex() == 1 ? "A" : "D");
        
        return usuario;
    }
    
    
    public boolean validate(){
        if(Nombre.getText().isEmpty() || Nombre.getForeground().equals(new Color(180, 180, 180))){
            Error.setText("El nombre de usuario es obligatorio");
            return false;
        }
        if(Nombre.getText().length() > 80){
            Error.setText("El nombre de usuario debe contener menos de 80 caracteres");
            return false;
        }
        
        if(Cargo.getSelectedIndex() == 0){
            Error.setText("Debe seleccionar el cargo del usuario");
            return false;
        }
        
        if(Empleado.getText().isEmpty() || Empleado.getForeground().equals(new Color(180, 180, 180)) || Empleado.getName() == null){
            Error.setText("La seleccion de un empleado es obligatoria");
            return false;
        }
        return true;
    }
    
    //Inside Task
    private boolean existUser(List<Usuario> usuarios){
        if(!usuarios.isEmpty()){
            for(Usuario usuario : usuarios){
                if(Nombre.getText().equals(usuario.getNombre())){
                    if(Nombre.getName() != null){
                        int UsuarioID = Integer.parseInt(Nombre.getName());
                        if(UsuarioID != usuario.getUsuarioID()){
                            return true;
                        }
                    } else { return true; }
                }
            }    
        }
        return false;
    }
}
