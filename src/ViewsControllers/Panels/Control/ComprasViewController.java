package ViewsControllers.Panels.Control;

import Controllers.CompraJpaController;
import Controllers.CompradetalleJpaController;
import Controllers.ProveedorJpaController;
import Models.Compra;
import Models.Compradetalle;
import Models.Producto;
import Models.Proveedor;
import Models.Usuario;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.util.List;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ComprasViewController {
    
    private CompraJpaController controller;
    private DefaultTableModel model = new DefaultTableModel();
    
    private JComboBox<Proveedor> Proveedores;
    private JTextField Factura;
    
    private JComboBox DiaCompra;
    private JComboBox MesCompra;
    private JComboBox AnioCompra;
    
    private JComboBox DiaVencimiento;
    private JComboBox MesVencimiento;
    private JComboBox AnioVencimiento;
    
    private JRadioButton Pagado;
    private JRadioButton Pendiente;
    
    private JTable Compras;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;

    public ComprasViewController(JComboBox Proveedores, JTextField Factura, JComboBox DiaCompra, JComboBox MesCompra, JComboBox AnioCompra, JComboBox DiaVencimiento, JComboBox MesVencimiento, JComboBox AnioVencimiento, JRadioButton Pagado, JRadioButton Pendiente, JTable Compras, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total) {
        this.Proveedores = Proveedores;
        this.Factura = Factura;
        this.DiaCompra = DiaCompra;
        this.MesCompra = MesCompra;
        this.AnioCompra = AnioCompra;
        this.DiaVencimiento = DiaVencimiento;
        this.MesVencimiento = MesVencimiento;
        this.AnioVencimiento = AnioVencimiento;
        this.Pagado = Pagado;
        this.Pendiente = Pendiente;
        this.Compras = Compras;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;
        
        CargarAnios(AnioCompra);
        CargarAnios(AnioVencimiento);
        cargarFechasActuales();
        controller = new CompraJpaController(Conection.CreateEntityManager());
    }
    
    public void cargarProducto(Object[] values){
        boolean NoexistRow = true;
        for(int i = 0; i < Compras.getRowCount(); i++){
            if(Compras.getValueAt(i, 0).toString().equals(values[0].toString())){
                float cantidadNew = Float.parseFloat(Compras.getValueAt(i, 3).toString().replace(",", "")) + Float.parseFloat(values[3].toString().replace(",", ""));
                float descuentoNew = Float.parseFloat(Compras.getValueAt(i, 5).toString().replace(",", "")) + Float.parseFloat(values[5].toString().replace(",", ""));
                float SubtotalNew = Float.parseFloat(Compras.getValueAt(i, 6).toString().replace(",", "")) + Float.parseFloat(values[6].toString().replace(",", ""));
                Compras.setValueAt(getNumberFormat(cantidadNew), i, 3);
                Compras.setValueAt(getNumberFormat(descuentoNew), i, 5);
                Compras.setValueAt(getNumberFormat(SubtotalNew), i, 6);
                
                NoexistRow = false;
            }
        }
        if(NoexistRow){ model.addRow(values); }
        updateTotal();
    }
    
    public void AgregarProveedor(){
        Dialogs.ShowAddProveedorDialog();
        Proveedores.removeAllItems();
        Proveedores.addItem(new Proveedor(0, "-- Seleccione proveedor --", "", "", "", 0));
        CargarProveedores();
    }
    
    public void CargarProveedores(){
        List<Proveedor> proveedores = new ProveedorJpaController(Conection.CreateEntityManager()).findProveedorEntities();
        proveedores.forEach(Proveedores::addItem);
    }
    
    public final void CargarAnios(JComboBox combo){
        int Year = Calendar.getInstance().get(Calendar.YEAR);
        
        for(int i = Year; i >= 1900; i--){
            combo.addItem(i);
        }
    }
    
    private void cargarFechasActuales(){
        DiaCompra.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE) - 1);
        MesCompra.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        DiaVencimiento.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE) - 1);
        MesVencimiento.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
    }
    
    public void InitTable(){
        String[] columns = {"Codigo", "Producto", "Unidades", "Cantidad", "Precio", "Descuento", "Subtotal"};
        model.setColumnIdentifiers(columns);
        
        Compras.setModel(model);
        Compras.getColumn("Codigo").setPreferredWidth(40);
        Compras.getColumn("Producto").setPreferredWidth(750);
        Compras.getColumn("Unidades").setPreferredWidth(70);
        Compras.getColumn("Cantidad").setPreferredWidth(70);
        Compras.getColumn("Precio").setPreferredWidth(120);
        Compras.getColumn("Descuento").setPreferredWidth(120);
        Compras.getColumn("Subtotal").setPreferredWidth(120);
    }
    
    public void DeleteCompra(){
        int fila = Compras.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowOKCancelDialog("¿Desea eliminar la compra seleccionada?", Dialogs.WARNING_ICON)){
                model.removeRow(fila);
                updateTotal();
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccion una compra de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void updateTotal(){
        int numberRows = Compras.getRowCount();
        if(numberRows > 0){
            float subtotal = 0;
            float descuento = 0;
            
            for(int i = 0; i < numberRows; i++){
                float cantidad = Float.parseFloat(Compras.getValueAt(i, 3).toString().replace(",", ""));
                float precio = Float.parseFloat(Compras.getValueAt(i, 4).toString().replace(",", ""));
                float descuentoUnitario = Float.parseFloat(Compras.getValueAt(i, 5).toString().replace(",", ""));
                
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
    
    public boolean InsertCompra(){
        if(validate()){
            Compra compra = CreateObjectCompra();
            int CompraID = controller.create(compra);
            List<Compradetalle> compras = createListCompraDetalle(CompraID);
            CompradetalleJpaController compradetalleJpaController = new CompradetalleJpaController(Conection.CreateEntityManager());
            compras.forEach(compradetalleJpaController::create);
            
            Runnable run = ()->{
                Reports reports = new Reports();
                reports.GenerateTickeCompra(CompraID);
            };
            Thread thread = new Thread(run);
            thread.start();
            
            Clear();
            return true;
        }
        return false;
    }
    
    private Compra CreateObjectCompra(){
        Compra compra = new Compra(); 
        
        compra.setNoFactura(Factura.getText());
        compra.setUsuarioID(new Usuario(Integer.valueOf(Utilities.getUsuarioActual()[0].toString())));
        
        if(Proveedores.getSelectedIndex() == 0){
            compra.setProveedorID(null);
            compra.setEstado("P");
        }else{
            compra.setProveedorID((Proveedor) Proveedores.getSelectedItem());
            compra.setEstado(Pagado.isSelected() ? "P": "N");
        }
        compra.setFecha(Utilities.getDate());
        compra.setHora(Utilities.getTime());
        compra.setFechaCompra(getFechaCompraReal());
        compra.setFechaVencimiento(getFechaVencimiento());
        
        return compra;
    }
    
    private List<Compradetalle> createListCompraDetalle(int CompraID){
        
        ArrayList<Compradetalle> list = new ArrayList<>();
        for(int i = 0; i < Compras.getRowCount(); i++){
            Compradetalle compradetalle = new Compradetalle();
            
            compradetalle.setCompraID(new Compra(CompraID));
            compradetalle.setProductoID(new Producto(Integer.valueOf(Compras.getValueAt(i, 0).toString())));
            compradetalle.setCantidad(Float.parseFloat(Compras.getValueAt(i, 3).toString().replace(",", "")));
            compradetalle.setPrecio(Float.parseFloat(Compras.getValueAt(i, 4).toString().replace(",", "")));
            compradetalle.setDescuento(Float.parseFloat(Compras.getValueAt(i, 5).toString().replace(",", "")));
            compradetalle.setIsv(0.15f);
            
            list.add(compradetalle);
        }
      
        return list;
    }
    
    private Date getFechaCompraReal(){
        int diaCompra = DiaCompra.getSelectedIndex() + 1;
        int mesCompra = MesCompra.getSelectedIndex();
        int anioCompra = Integer.parseInt(AnioCompra.getSelectedItem().toString()) - 1900;
        
        return new Date(anioCompra, mesCompra, diaCompra);
    }
    
    private Date getFechaVencimiento(){
        int diaCompra = DiaVencimiento.getSelectedIndex() + 1;
        int mesCompra = MesVencimiento.getSelectedIndex();
        int anioCompra = Integer.parseInt(AnioVencimiento.getSelectedItem().toString()) - 1900;
        
        return new Date(anioCompra, mesCompra, diaCompra);
    }
    
    private boolean validate(){
        if(Compras.getRowCount() < 0){
            Dialogs.ShowMessageDialog("Para agregar la compra debe agregar al menos una producto", Dialogs.ERROR_ICON);
            return false;
        }
        if(Proveedores.getSelectedIndex() != 0){
            if(!Pendiente.isSelected() && !Pagado.isSelected()){
                Dialogs.ShowMessageDialog("Para agregar la compra debe definir el estado de la factura", Dialogs.ERROR_ICON);
                return false;
            }
        }
        if(Factura.getForeground().equals(new Color(180, 180, 180)) || Factura.getText().isEmpty()){
            Dialogs.ShowMessageDialog("Para agregar la compra debe agregar el numero de factura", Dialogs.ERROR_ICON);
            return false;
        }
        if(getFechaCompraReal().after(Utilities.getDate())){
            Dialogs.ShowMessageDialog("La fecha de compra real no puede ser mayor a la fecha actual", Dialogs.ERROR_ICON);
            return false;
        }
        if(getFechaVencimiento().before(getFechaCompraReal())){
            Dialogs.ShowMessageDialog("La fecha de vencimiento no puede ser menor a la fecha de compra real", Dialogs.ERROR_ICON);
            return false;
        }
        if(Pendiente.isSelected()){
            if(getFechaVencimiento().compareTo(getFechaCompraReal()) == 0){
                Dialogs.ShowMessageDialog("En factura pendiente de pago debe agregar fecha de vencimiento", Dialogs.ERROR_ICON);
                return false;
            }
        }
        return true;
    }
    
    private void Clear(){
        Proveedores.setSelectedIndex(0);
        Factura.setText("12345...");
        Factura.setForeground(new Color(180, 180, 180));
        
        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");
        
        cargarFechasActuales();
        model = new DefaultTableModel();
        InitTable();
    }
}
