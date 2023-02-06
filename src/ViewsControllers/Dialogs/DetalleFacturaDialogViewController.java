package ViewsControllers.Dialogs;

import Controllers.CompraJpaController;
import Controllers.VentaJpaController;
import Models.Compra;
import Models.Venta;
import Resource.Conection;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class DetalleFacturaDialogViewController {
    private JLabel NoFactura;
    private JLabel Entidad;
    private JLabel Usuario;
    private JLabel Hora;
    private JLabel Fecha;
    private JTable Detalles;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;
    
    private DefaultTableModel model = new DefaultTableModel();

    public DetalleFacturaDialogViewController(JLabel NoFactura, JLabel Entidad, JLabel Usuario, JLabel Hora, JLabel Fecha, JTable Detalles, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total) {
        this.NoFactura = NoFactura;
        this.Entidad = Entidad;
        this.Usuario = Usuario;
        this.Hora = Hora;
        this.Fecha = Fecha;
        this.Detalles = Detalles;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;
    }
    
    public void InitTable(){
        
        String[] columns = {"Codigo", "Producto", "Unidades", "Cantidad", "Precio", "Descuento", "Subtotal"};
        model.setColumnIdentifiers(columns);
        
        Detalles.setModel(model);
        Detalles.getColumn("Codigo").setPreferredWidth(80);
        Detalles.getColumn("Producto").setPreferredWidth(500);
        Detalles.getColumn("Unidades").setPreferredWidth(70);
        Detalles.getColumn("Cantidad").setPreferredWidth(70);
        Detalles.getColumn("Precio").setPreferredWidth(90);
        Detalles.getColumn("Descuento").setPreferredWidth(90);
        Detalles.getColumn("Subtotal").setPreferredWidth(90);
    }
    
    public void cargarFactura(int VentaID){
        Venta venta = new VentaJpaController(Conection.createEntityManagerFactory()).findVenta(VentaID);
        
        if(venta != null){
            NoFactura.setText(String.valueOf(venta.getVentaID()));
            Entidad.setText(venta.getClienteID().getNombre());
            Usuario.setText(venta.getUsuarioID().getNombre());
            Hora.setText(new SimpleDateFormat("HH:mm").format(venta.getHora()));
            Fecha.setText(new SimpleDateFormat("dd/MM/yyy").format(venta.getFecha()));
        }
        
        venta.getVentadetalleList().forEach(detalle -> {
            Object[] row = {
                detalle.getProductoID().getProductoID(),
                detalle.getProductoID().getDescripcion(),
                detalle.getProductoID().getUnidad(),
                getNumberFormat(detalle.getCantidad()),
                getNumberFormat(detalle.getPrecio()),
                getNumberFormat(detalle.getDescuento()),
                getNumberFormat((detalle.getCantidad() * detalle.getPrecio()) - detalle.getDescuento())
            };
            
            model.addRow(row);
        });
        
        updateTotal();
    }
    
    public void cargarCompra(int CompraID){
        Compra compra = new CompraJpaController(Conection.createEntityManagerFactory()).findCompra(CompraID);
        
        if(compra != null){
            NoFactura.setText(String.valueOf(compra.getCompraID()));
            Entidad.setText(compra.getProveedorID().getNombre());
            Usuario.setText(compra.getUsuarioID().getNombre());
            Hora.setText(new SimpleDateFormat("HH:mm").format(compra.getHora()));
            Fecha.setText(new SimpleDateFormat("dd/MM/yyy").format(compra.getFecha()));
        }
        
        compra.getCompradetalleList().forEach(detalle -> {
            Object[] row = {
                detalle.getProductoID().getProductoID(),
                detalle.getProductoID().getDescripcion(),
                detalle.getProductoID().getUnidad(),
                getNumberFormat(detalle.getCantidad()),
                getNumberFormat(detalle.getPrecio()),
                getNumberFormat(detalle.getDescuento()),
                getNumberFormat((detalle.getCantidad() * detalle.getPrecio()) - detalle.getDescuento())
            };
            
            model.addRow(row);
        });
        
        updateTotal();
    }
    
    public void updateTotal(){
        int numberRows = Detalles.getRowCount();
        if(numberRows > 0){
            float subtotal = 0;
            float descuento = 0;
            
            for(int i = 0; i < numberRows; i++){
                float cantidad = Float.parseFloat(Detalles.getValueAt(i, 3).toString().replace(",", ""));
                float precio = Float.parseFloat(Detalles.getValueAt(i, 4).toString().replace(",", ""));
                float descuentoUnitario = Float.parseFloat(Detalles.getValueAt(i, 5).toString().replace(",", ""));
                
                subtotal += cantidad * precio;
                descuento += descuentoUnitario;
            }
            float importe = (subtotal - descuento)/1.15f;
            float isv = importe*0.15f;
            float total = importe+isv;
            
            Subtotal.setText(getNumberFormat(subtotal));
            Descuento.setText(getNumberFormat(descuento));
            Importe.setText(getNumberFormat(importe));
            ISV.setText(getNumberFormat(isv));
            Total.setText(getNumberFormat(total));
        }
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
