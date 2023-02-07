package ViewsControllers.Dialogs;

import Controllers.InventarioJpaController;
import Controllers.InventariodetalleaccionesJpaController;
import Models.Inventario;
import Models.Inventariodetalleacciones;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.AddInventarioDetalle;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class AddInventarioDetalleViewController {

    private AddInventarioDetalle Instance;
    private JTextArea Descripcion;
    private JTextField Cantidad;
    private JTextField ExistenciaPrevia;
    private JTextField ExistenciaResultante;
    private JLabel Error;
    private JLabel Cargando;

    private int InventarioID;
    private int ProductoID;

    public AddInventarioDetalleViewController(AddInventarioDetalle Instance, JTextArea Descripcion, JTextField Cantidad, JTextField ExistenciaPrevia, JTextField ExistenciaResultante, JLabel Error, JLabel Cargando) {
        this.Instance = Instance;
        this.Descripcion = Descripcion;
        this.Cantidad = Cantidad;
        this.ExistenciaPrevia = ExistenciaPrevia;
        this.ExistenciaResultante = ExistenciaResultante;
        this.Error = Error;
        this.Cargando = Cargando;
        //Inicializando carga de datos
        Init();
    }

    public void setLoad(boolean status) {
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(status ? icon : null);
    }

    private void Init() {
        setLoad(true);
        Runnable run = () -> {

            //Cargando existencia actual del producto
            loadAmount();

            setLoad(false);
        };
        new Thread(run).start();
    }

    public void updateData(int InventarioID) {
        this.InventarioID = InventarioID;
    }

    public void insertInventoryDetail() {

        if (validateDescription()) {
            if (validateAmount()) {
                if(validateExistence()){
                    if(validateCalculations()){
                        
                        setLoad(true);
                        setEditableFields(false);
                        Runnable run = () -> {

                            InventariodetalleaccionesJpaController controller = new InventariodetalleaccionesJpaController(Conection.createEntityManagerFactory());
                            Inventariodetalleacciones inventarioDetalle = createObjectInventoryDetail();
                            controller.create(inventarioDetalle);

                            setLoad(false);

                            Instance.setVisible(false);
                            Dialogs.ShowMessageDialog("¡La modificación del inventario ha sido exitosa!", Dialogs.COMPLETE_ICON);

                        };
                        new Thread(run).start();
                        
                    }else{ Error.setBackground(new Color(185, 0, 0)); }
                } else { Error.setBackground(new Color(185, 0, 0)); }
            } else { Error.setBackground(new Color(185, 0, 0)); }
        } else { Error.setBackground(new Color(185, 0, 0)); }
        
    }

    public Inventariodetalleacciones createObjectInventoryDetail() {
        Inventariodetalleacciones inventarioDetalle = new Inventariodetalleacciones();

        inventarioDetalle.setProductoID(new Producto(ProductoID));
        inventarioDetalle.setUsuarioID(Utilities.getUsuarioActual());
        inventarioDetalle.setFecha(Utilities.getDate());
        inventarioDetalle.setHora(Utilities.getTime());
        inventarioDetalle.setDescripcion(Descripcion.getText());
        inventarioDetalle.setAccion("M");
        inventarioDetalle.setExistenciaPrevia(Float.parseFloat(ExistenciaPrevia.getText()));
        inventarioDetalle.setCantidadModificada(Float.parseFloat(Cantidad.getText()));

        return inventarioDetalle;
    }

    private void loadAmount() {
        Object cantidad = Conection.createEntityManagerFactory().createEntityManager()
                .createNativeQuery("SELECT Cantidad FROM inventario WHERE InventarioID = " + InventarioID)
                .getSingleResult();

        Inventario inventario = new InventarioJpaController(Conection.createEntityManagerFactory()).findInventario(InventarioID);
        if(inventario != null){
            ProductoID = inventario.getProductoID().getProductoID();
            ExistenciaPrevia.setText(getNumberFormat(Float.parseFloat(cantidad.toString())));
            ExistenciaResultante.setText(getNumberFormat(Float.parseFloat(cantidad.toString())));
            setEditableFields(true);
        } else { Error.setText("Error al cargar el total en existencia"); Error.setBackground(new Color(185, 0, 0)); }
    }

    public void updateAmount() {
        if (validateExistence()) {
            float cantidadPrevia = Float.parseFloat(ExistenciaPrevia.getText().replace(",", ""));
            float cantidadResultante = Float.parseFloat(ExistenciaResultante.getText().replace(",", ""));
            
            Cantidad.setForeground(Color.black);
            Cantidad.setText(getNumberFormat(cantidadResultante - cantidadPrevia));
            Error.setBackground(Color.white);
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }

    public void updateResultExistence() {
        if (validateAmount()) {
            float cantidadPrevia = Float.parseFloat(ExistenciaPrevia.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            
            if(cantidadPrevia + cantidad >= 0){
                ExistenciaResultante.setForeground(Color.black);
                ExistenciaResultante.setText(getNumberFormat(cantidadPrevia + cantidad));
                Error.setBackground(Color.white);
            }else{ Error.setBackground(new Color(185, 0, 0)); Error.setText("La existencia resultante debe ser mayor a 0");}
        } else { Error.setBackground(new Color(185, 0, 0)); }
    }

    private boolean validateDescription() {
        if (Descripcion.getText().isEmpty()) {
            Error.setText("La justificación de la modificación es obligatoria");
            return false;
        }
        if (Descripcion.getText().length() > 65535) {
            Error.setText("La descripción de la modificación es demasiado larga");
            return false;
        }
        return true;
    }

    private boolean validateAmount(){
        if (Cantidad.getText().isEmpty() || Cantidad.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("La cantidad de modificación es obligatoria");
            return false;
        }
        try {
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            if(cantidad == 0){
                Error.setText("La cantidad es 0, no se estan realizando cambios");
                return false;
            }
        } catch (NumberFormatException e) {
            Error.setText("La cantidad de modificación debe ser un número");
            return false;
        }
        return true;
    }    
    
    private boolean validateExistence() {
        float existenciaResultante;
        if (ExistenciaResultante.getText().isEmpty() || ExistenciaResultante.getForeground().equals(new Color(180, 180, 180))) {
            Error.setText("La existencia resultante de modificación es obligatoria");
            return false;
        }
        try {
            existenciaResultante = Float.parseFloat(ExistenciaResultante.getText().replace(",", ""));
        } catch (NumberFormatException e) {
            Error.setText("La existencia resultante debe ser un número");
            return false;
        }
        if (existenciaResultante < 0) {
            Error.setText("La existencia resultante debe ser mayor o igual a 0");
            return false;
        }
        return true;
    }
    
    private boolean validateCalculations(){
        float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
        float existencia = Float.parseFloat(ExistenciaPrevia.getText().replace(",", ""));
        float existenciaReal = Float.parseFloat(ExistenciaResultante.getText().replace(",", ""));
        
        if(cantidad + existencia != existenciaReal){
            Error.setText("Error al calcula existencia real");
            return false;
        }
        return true;
    }

    private void setEditableFields(boolean state) {
        Cantidad.setEditable(state);
        ExistenciaResultante.setEditable(state);
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
