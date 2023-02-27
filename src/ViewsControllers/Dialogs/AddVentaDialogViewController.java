package ViewsControllers.Dialogs;

import Models.Marca;
import Models.Producto;
import Resource.Conection;
import Resource.Utilities;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AddVentaDialogViewController {

    private JTextField Buscar;
    private JComboBox Marcas;
    private JTable Productos;
    private JTextField Existencia;
    private JTextField DescuentoPorcentaje;
    private JTextField DescuentoLempiras;
    private JTextField Precio;
    private JTextField Cantidad;
    private JTextField Subtotal;
    private JLabel Error;
    private JLabel Cargando;

    private DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    
    private KeyListener filterProductsOnKey = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {}
        @Override
        public void keyReleased(KeyEvent e) { filter(); }
    };
    
    private ActionListener filterProductsOnAction = (ActionEvent e) -> { filter(); };

    public AddVentaDialogViewController(JTextField Buscar, JComboBox Marcas, JLabel Cargando, JTable Productos, JTextField Existencia, JTextField DescuentoPorcentaje, JTextField DescuentoLempiras, JTextField Precio, JTextField Cantidad, JTextField Subtotal, JLabel Error) {
        this.Buscar = Buscar;
        this.Marcas = Marcas;
        this.Productos = Productos;
        this.Existencia = Existencia;
        this.DescuentoPorcentaje = DescuentoPorcentaje;
        this.DescuentoLempiras = DescuentoLempiras;
        this.Precio = Precio;
        this.Cantidad = Cantidad;
        this.Subtotal = Subtotal;
        this.Error = Error;
        this.Cargando = Cargando;

        //Inhabilitando campos de edicion de venta
        setEditableFields(false);

        //Cargando modelo de tabla en tabla de productos
        setModelTableProducts();
        
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
            //Cargando marcas en combobox
            loadBrands();
            
            //Cargando datos de productos a la lista
            loadProducts();

            setLoad(false);
        };
        new Thread(run).start();
    }

    public void updateData() {
        Init();
    }

    private void setModelTableProducts() {
        String[] columns = {"Codigo", "Descripcion", "Marca", "Unidad", "Precio"};
        model.setColumnIdentifiers(columns);

        Productos.setModel(model);
        Productos.getColumn("Codigo").setPreferredWidth(50);
        Productos.getColumn("Descripcion").setPreferredWidth(320);
        Productos.getColumn("Marca").setPreferredWidth(100);
        Productos.getColumn("Unidad").setPreferredWidth(80);
        Productos.getColumn("Precio").setPreferredWidth(90);
    }

    private void loadProducts() {
        model.setRowCount(0);
        List<Producto> productos = Conection.createEntityManager().createNamedQuery("Producto.findAll").getResultList();
        productos.forEach(producto -> {
            //Demasiado lento
            //if(productExistence(producto.getProductoID()) > 0){
                Object[] row = {
                    producto.getProductoID(),
                    producto.getDescripcion(),
                    producto.getMarcaID().getNombre(),
                    producto.getUnidad(),
                    getNumberFormat(producto.getPrecioVenta())
                };
                model.addRow(row);
            //}
        });
        
        Buscar.addKeyListener(filterProductsOnKey);
        Marcas.addActionListener(filterProductsOnAction);
    }

    private void loadBrands() {
        Marcas.removeAllItems();
        Marcas.addItem("-- Todas la marcas --");
        List<Marca> marcas = Conection.createEntityManagerFactory().createEntityManager()
            .createNamedQuery("Marca.findAll")
            .getResultList();
        marcas.forEach(Marcas::addItem);
    }

    public void filter(){
        TableRowSorter rowSorter = new TableRowSorter(Productos.getModel());
        List<RowFilter<TableModel, String>> filters = new LinkedList<>();
        if(!Buscar.getForeground().equals(new Color(180, 180, 180))){
            String[] words = Buscar.getText().split(" ");
            for(String word : words) {
                filters.add(RowFilter.regexFilter(Buscar.getForeground().equals(new Color(180, 180, 180)) ? "" : "(?i)" + word, 0, 1));
            }
        }
        filters.add(RowFilter.regexFilter(Marcas.getSelectedIndex() <= 0 ? "" : Marcas.getSelectedItem().toString(), 2));
        rowSorter.setRowFilter(RowFilter.andFilter(filters));
        Productos.setRowSorter(rowSorter);
        clear();
        setEditableFields(false);
    }

    //Task
    public void loadProduct() {
        int fila = Productos.getSelectedRow();
        if (fila >= 0) {
            setLoad(true);
            Runnable run = () -> {
                float existenciaProducto = productExistence(Integer.parseInt(Productos.getValueAt(fila, 0).toString()));

                if (existenciaProducto <= 0) {
                    Error.setBackground(new Color(185, 0, 0));
                    Error.setText("No hay existencia del producto seleccionado");
                    setEditableFields(false);
                } else {
                    Error.setBackground(Color.white);
                    setEditableFields(true);
                }

                DescuentoLempiras.setText(getNumberFormat(0f));
                DescuentoPorcentaje.setText(getNumberFormat(0f));
                Existencia.setText(getNumberFormat(existenciaProducto));
                Precio.setText(getNumberFormat(Float.parseFloat(Productos.getValueAt(fila, 4).toString().replace(",", ""))));
                Cantidad.setText(getNumberFormat(1f));
                float Total
                        = Float.parseFloat(Productos.getValueAt(fila, 4).toString().replace(",", ""))
                        * Float.parseFloat(Cantidad.getText().replace(",", ""));
                Subtotal.setText(getNumberFormat(Total));

                setLoad(false);
            };
            new Thread(run).start();
        }
    }
    
    //Use in task
    private float productExistence(int ProductoID){
        Query query = Conection.createEntityManagerFactory().createEntityManager()
                        .createNativeQuery("SELECT cantidad FROM inventario WHERE ProductoID = " + ProductoID);
        List values = query.getResultList();
        float existenciaProducto =  0;
        if(!values.isEmpty()){
            existenciaProducto = Float.parseFloat(values.get(0).toString());
        }
        return existenciaProducto;
    }

    public void updateSubtotal() {
        if (validate()) {
            Error.setBackground(Color.white);
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", "")) * cantidad;
            float subtotal = (precio * cantidad) - descuento;
            Subtotal.setText(getNumberFormat(subtotal));
        } else {
            Error.setBackground(new Color(185, 0, 0));
        }
    }

    public void updatePorcentLempiras() {
        if (validate()) {
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float descuento = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
            float DescuentoEnLempiras = (precio * cantidad) * (descuento * 0.01f);
            DescuentoLempiras.setText(getNumberFormat(DescuentoEnLempiras));
        } else {
            Error.setBackground(new Color(185, 0, 0));
        }
    }

    public void updateLempirasPorcent() {
        if (validate()) {
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
            float DescuentoEnPorcentaje = (descuento) / ((precio * cantidad) / 100);
            DescuentoPorcentaje.setText(getNumberFormat(DescuentoEnPorcentaje));
        } else {
            Error.setBackground(new Color(185, 0, 0));
        }
    }

    public Object[] getValues() {

        if (validate()) {
            float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
            float precio = Float.parseFloat(Precio.getText().replace(",", ""));
            float descuento = Float.parseFloat(DescuentoLempiras.getText().replace(",", "")) * cantidad;
            float subtotal = (cantidad * precio) - descuento;

            Object[] values = {
                Productos.getValueAt(Productos.getSelectedRow(), 0),
                Productos.getValueAt(Productos.getSelectedRow(), 1),
                Productos.getValueAt(Productos.getSelectedRow(), 3),
                getNumberFormat(cantidad),
                getNumberFormat(precio),
                getNumberFormat(descuento),
                getNumberFormat(subtotal)
            };
            return values;
        } else {
            Error.setBackground(new Color(185, 0, 0));
        }
        return null;
    }

    private boolean validate() {
        int fila = Productos.getSelectedRow();
        if (fila >= 0) {
            float descuentoLempiras;
            float precio;
            //validando descuento en porcentaje
            try {
                float descuentoPorcentaje = Float.parseFloat(DescuentoPorcentaje.getText().replace(",", ""));
                if (descuentoPorcentaje < 0 || descuentoPorcentaje > 100) {
                    Error.setText("El descuento en porcentaje debe de ser mayor a cero y menor a 100");
                    return false;
                }
            } catch (NumberFormatException ex) {
                Error.setText("El descuento en porcentaje debe de ser un numero");
                return false;
            }

            //validando descuento en lempiras
            try {
                descuentoLempiras = Float.parseFloat(DescuentoLempiras.getText().replace(",", ""));
                if (descuentoLempiras < 0) {
                    Error.setText("El descuento debe de ser mayor a cero");
                    return false;
                }
            } catch (NumberFormatException ex) {
                Error.setText("El descuento en lempiras debe de ser un numero");
                return false;
            }

            //validando precio
            try {
                precio = Float.parseFloat(Precio.getText().replace(",", ""));
                if (precio <= 0) {
                    Error.setText("El precio debe de ser mayor a cero");
                    return false;
                }
            } catch (NumberFormatException ex) {
                Error.setText("El precio debe de ser un numero");
                return false;
            }

            //validando que el descuento no supere el precio
            if (descuentoLempiras > precio) {
                Error.setText("El descuento no puede ser mayor al precio del producto");
                return false;
            }

            //validando la cantidad
            try {
                float cantidad = Float.parseFloat(Cantidad.getText().replace(",", ""));
                if (cantidad <= 0) {
                    Error.setText("La Cantidad debe de ser mayor a cero");
                    return false;
                }
                float existencia = Float.parseFloat(Existencia.getText().replace(",", " "));
                if (cantidad > existencia) {
                    Error.setText("La cantidad debe de ser menor a la existencia actual");
                    return false;
                }
            } catch (NumberFormatException ex) {
                Error.setText("La cantidad del producto debe de ser un numero");
                return false;
            }

        } else {
            Error.setText("Seleccione un producto de la lista");
            return false;
        }
        return true;
    }

    private void clear() {
        Existencia.setText("0.00");
        DescuentoPorcentaje.setText("0.00");
        DescuentoLempiras.setText("0.00");
        Precio.setText("0.00");
        Cantidad.setText("0.00");
        Subtotal.setText("0.00");
    }

    private void setEditableFields(boolean status) {
        DescuentoPorcentaje.setEditable(status);
        DescuentoLempiras.setEditable(status);
        Cantidad.setEditable(status);
    }

    private String getNumberFormat(float Value) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
