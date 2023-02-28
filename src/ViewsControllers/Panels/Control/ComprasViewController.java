package ViewsControllers.Panels.Control;

import Controllers.CompraJpaController;
import Controllers.CompradetalleJpaController;
import Controllers.ProductoJpaController;
import Controllers.exceptions.NonexistentEntityException;
import Models.Compra;
import Models.Compradetalle;
import Models.Producto;
import Models.Proveedor;
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
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ComprasViewController {
    
    private CompraJpaController controller;
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; } 
    };
    
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
    
    private JTextField Barra;
    
    private JTable Compras;
    private JTextField Subtotal;
    private JTextField Descuento;
    private JTextField Importe;
    private JTextField ISV;
    private JTextField Total;
    private JLabel Cargando;

    public ComprasViewController(JComboBox Proveedores, JTextField Factura, JComboBox DiaCompra, JComboBox MesCompra, JComboBox AnioCompra, JComboBox DiaVencimiento, JComboBox MesVencimiento, JComboBox AnioVencimiento, JRadioButton Pagado, JRadioButton Pendiente, JTextField Barra, JTable Compras, JTextField Subtotal, JTextField Descuento, JTextField Importe, JTextField ISV, JTextField Total, JLabel Cargando) {
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
        this.Barra = Barra;
        this.Compras = Compras;
        this.Subtotal = Subtotal;
        this.Descuento = Descuento;
        this.Importe = Importe;
        this.ISV = ISV;
        this.Total = Total;
        this.Cargando = Cargando;
     
        controller = new CompraJpaController(Conection.createEntityManagerFactory());
        
        //Cargando años en combobox de fechas
        Utilities.CargarAnios(AnioCompra, 0);
        Utilities.CargarAnios(AnioVencimiento, 0);
            
        //Cargando fecha actual
        loadCurrentDate();
        
        //Cargando modelo de tabla en tabla de compra
        setModelTableBuys();
        
        //Inicializando carga de datos
        Init();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }    
    
    private void Init(){
        setLoad(true);
        Runnable run = ()->{
            //Cargando combobox de proveedores    
            loadSuppliers();
            
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    public void updateData(){
        Init();
    }
    
    public void loadProduct(Object[] values){
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
    
    public void editItemValue(){
        int fila = Compras.getSelectedRow();
        if(fila >= 0){
            Object[] values = {
                Compras.getValueAt(fila, 0).toString(),
                Compras.getValueAt(fila, 1).toString(),
                Compras.getValueAt(fila, 2).toString(),
                Compras.getValueAt(fila, 3).toString(),
                Compras.getValueAt(fila, 4).toString(),
                Compras.getValueAt(fila, 5).toString(),
            };
            
            Object[] newValues = Dialogs.ShowEditCompraDialog(values);
            
            if(newValues != null && newValues[0] != null){
                Compras.setValueAt(getNumberFormat(Float.parseFloat(newValues[0].toString())), fila, 3);
                Compras.setValueAt(getNumberFormat(Float.parseFloat(newValues[1].toString())), fila, 4);
                Compras.setValueAt(getNumberFormat(Float.parseFloat(newValues[2].toString())), fila, 5);
                Compras.setValueAt(getNumberFormat(Float.parseFloat(newValues[3].toString())), fila, 6);
                updateTotal();
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione un producto de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void loadbyBarCode() {
        setLoad(true);
        Runnable run = ()->{
            ProductoJpaController controllerProducto = new ProductoJpaController(Conection.createEntityManagerFactory());
            List<Producto> productos = controllerProducto.findProductoEntities();
            if(!productos.isEmpty()){
                for(Producto producto  : productos) {
                    if(producto.getBarra() != null){
                        if (producto.getBarra().equals(Barra.getText())) {
                            Object[] row = {
                                producto.getProductoID(),
                                producto.getDescripcion(),
                                producto.getUnidad(),
                                getNumberFormat(1f),
                                getNumberFormat(producto.getPrecioCompra()),
                                getNumberFormat(0f),
                                getNumberFormat(producto.getPrecioCompra())
                            };
                            loadProduct(row);
                            break;
                        }
                    }
                }
            }
            setLoad(false);
            Barra.setText("");
        };
        new Thread(run).start();
    }
    
    private void loadSuppliers(){
        Proveedores.removeAllItems();
        Proveedores.addItem(new Proveedor(0, "-- Seleccione proveedor --", "", "", "", 0));
        List<Proveedor> proveedores = Conection.createEntityManager().createNamedQuery("Proveedor.findAll").getResultList();
        proveedores.forEach(Proveedores::addItem);
    }
    
    private void loadCurrentDate(){
        DiaCompra.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE) - 1);
        MesCompra.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
        DiaVencimiento.setSelectedIndex(Calendar.getInstance().get(Calendar.DATE) - 1);
        MesVencimiento.setSelectedIndex(Calendar.getInstance().get(Calendar.MONTH));
    }
    
    public void deleteAllBuys(){
        if(model.getRowCount() > 0){
            if(Dialogs.ShowOKCancelDialog("¿Desea eliminar todas las compras de la factura?", Dialogs.WARNING_ICON)){
                model.setRowCount(0);
                updateTotal();
            }
        }
    }
    
    private void setModelTableBuys(){
        String[] columns = {"Codigo", "Producto", "Unidades", "Cantidad", "Precio", "Descuento", "Subtotal"};
        model.setColumnIdentifiers(columns);
        
        Compras.setModel(model);
        Compras.getColumn("Codigo").setPreferredWidth(60);
        Compras.getColumn("Producto").setPreferredWidth(720);
        Compras.getColumn("Unidades").setPreferredWidth(70);
        Compras.getColumn("Cantidad").setPreferredWidth(70);
        Compras.getColumn("Precio").setPreferredWidth(120);
        Compras.getColumn("Descuento").setPreferredWidth(120);
        Compras.getColumn("Subtotal").setPreferredWidth(120);
    }
    
    public void deleteBuy(){
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
    
    public void insertBuy(){
        if(validate()){
            setLoad(true);
            Runnable run = () ->{
                //Insertando compra
                Compra compra = createObjectBuy();
                int CompraID = controller.create(compra);
                
                //Insertando detalles de la compra
                List<Compradetalle> compras = createListBuyDetails(CompraID);
                CompradetalleJpaController compradetalleJpaController = new CompradetalleJpaController(Conection.createEntityManagerFactory());
                compras.forEach(compradetalleJpaController::create);
                
                //Actulizando precios de producto a nuevos precios
                updateProductPrice(compras);
                
                setLoad(false);
                Dialogs.ShowMessageDialog("La compra ha sido agregada exitosamente", Dialogs.COMPLETE_ICON);
                
                //Enviando a imprimir factura de compra propia
                if(Dialogs.ShowOKCancelDialog("¿Desea imprimir una factura de compra propia?", Dialogs.WARNING_ICON)){
                    setLoad(true);
                    Runnable runnable = () -> {
                        Reports reports = new Reports();
                        reports.GenerateTicketCompra(CompraID);
                        setLoad(false);
                    };
                    new Thread(runnable).start();
                }
                clear();
            };
            new Thread(run).start();
        }
    }
    
    private Compra createObjectBuy(){
        Compra compra = new Compra(); 
        
        compra.setNoFactura(Factura.getText());
        compra.setUsuarioID(Utilities.getUsuarioActual());
        compra.setProveedorID((Proveedor) Proveedores.getSelectedItem());
        compra.setEstado(!Pagado.isSelected() ? "N": "P");
        compra.setFecha(Utilities.getDate());
        compra.setHora(Utilities.getTime());
        compra.setFechaCompra(getRealBuyDate());
        compra.setFechaVencimiento(getDueBuyDate());
        
        return compra;
    }
    
    private void updateProductPrice(List<Compradetalle> compras){
        if(!compras.isEmpty()){
            compras.forEach(compra ->{
                ProductoJpaController controllerProduct = new ProductoJpaController(Conection.createEntityManagerFactory());
                Producto producto = controllerProduct.findProducto(compra.getProductoID().getProductoID());
                producto.setPrecioCompra(compra.getPrecio());

                try {
                    controllerProduct.edit(producto);
                } catch (NonexistentEntityException ex) {
                    System.err.println("Error: "+ex.getMessage());
                } catch (Exception ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            });
        }
    }
    
    private List<Compradetalle> createListBuyDetails(int CompraID){
        
        ArrayList<Compradetalle> list = new ArrayList<>();
        int index = 0;
        while(index < Compras.getRowCount()){
            Compradetalle compradetalle = new Compradetalle();
            
            compradetalle.setCompraID(new Compra(CompraID));
            compradetalle.setProductoID(new Producto(Integer.valueOf(Compras.getValueAt(index, 0).toString())));
            compradetalle.setCantidad(Float.parseFloat(Compras.getValueAt(index, 3).toString().replace(",", "")));
            compradetalle.setPrecio(Float.parseFloat(Compras.getValueAt(index, 4).toString().replace(",", "")));
            compradetalle.setDescuento(Float.parseFloat(Compras.getValueAt(index, 5).toString().replace(",", "")));
            compradetalle.setIsv(0.15f);
            
            list.add(compradetalle);
            
            index++;
        }
      
        return list;
    }
    
    private Date getRealBuyDate(){
        int diaCompra = DiaCompra.getSelectedIndex() + 1;
        int mesCompra = MesCompra.getSelectedIndex();
        int anioCompra = Integer.parseInt(AnioCompra.getSelectedItem().toString()) - 1900;
        
        return new Date(anioCompra, mesCompra, diaCompra);
    }
    
    private Date getDueBuyDate(){
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
        if(Proveedores.getSelectedIndex() == 0){
            Dialogs.ShowMessageDialog("Para agregar la compra debe seleccionar un proveedor", Dialogs.ERROR_ICON);
            return false;
        }
        if(Proveedores.getSelectedIndex() !=  0){
            if(!Pendiente.isSelected() && !Pagado.isSelected()){
                Dialogs.ShowMessageDialog("Para agregar la compra debe seleccionar la forma de pago de la factura", Dialogs.ERROR_ICON);
                return false;
            }
        }
        if(Factura.getForeground().equals(new Color(180, 180, 180)) || Factura.getText().isEmpty()){
            Dialogs.ShowMessageDialog("Para agregar la compra debe agregar el numero de factura", Dialogs.ERROR_ICON);
            return false;
        }
        if(getRealBuyDate().after(Utilities.getDate())){
            Dialogs.ShowMessageDialog("La fecha de compra real no puede ser mayor a la fecha actual", Dialogs.ERROR_ICON);
            return false;
        }
        if(getDueBuyDate().before(getRealBuyDate())){
            Dialogs.ShowMessageDialog("La fecha de vencimiento no puede ser menor a la fecha de compra real", Dialogs.ERROR_ICON);
            return false;
        }
        if(Pendiente.isSelected()){
            if(getDueBuyDate().compareTo(getRealBuyDate()) == 0){
                Dialogs.ShowMessageDialog("En factura pendiente de pago debe agregar fecha de vencimiento", Dialogs.ERROR_ICON);
                return false;
            }
        }
        return true;
    }
    
    private void clear(){
        Proveedores.setSelectedIndex(0);
        Factura.setText("12345...");
        Factura.setForeground(new Color(180, 180, 180));
        
        Subtotal.setText("0.00");
        Descuento.setText("0.00");
        Importe.setText("0.00");
        ISV.setText("0.00");
        Total.setText("0.00");
        
        loadCurrentDate();
        model.setRowCount(0);
    }
}