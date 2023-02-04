package ViewsControllers.Dialogs;

import Controllers.AbonoJpaController;
import Controllers.CompraJpaController;
import Controllers.VentaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Abono;
import Models.Compra;
import Models.Compradetalle;
import Models.Venta;
import Models.Ventadetalle;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AddAbonoDialogViewController {
    
    private JLabel FacturaID;
    private JLabel DeudaTotal;
    private JLabel TotalAbonado;
    private JLabel DeudaPendiente;
    private JTable Abonos;
    private JTextField TotalAbonar;
    private JTextField TotalRestante;
    private JLabel Error;
    
    private boolean isAbonoVenta = true;
    
    public AddAbonoDialogViewController(JLabel FacturaID, JLabel DeudaTotal, JLabel TotalAbonado, JLabel DeudaPendiente, JTable Abonos, JTextField TotalAbonar, JTextField TotalRestante, JLabel Error) {
        this.FacturaID = FacturaID;
        this.DeudaTotal = DeudaTotal;
        this.TotalAbonado = TotalAbonado;
        this.DeudaPendiente = DeudaPendiente;
        this.Abonos = Abonos;
        this.TotalAbonar = TotalAbonar;
        this.TotalRestante = TotalRestante;
        this.Error = Error;
    }
    
    public void setVenta(int VentaID){
        FacturaID.setText(String.valueOf(VentaID));
        Venta venta = new VentaJpaController(Conection.CreateEntityManager()).findVenta(VentaID);
        
        List<Ventadetalle> detallesventa = venta.getVentadetalleList();
        float total = 0;
        for(Ventadetalle ventadetalle : detallesventa){
            total+= ventadetalle.getCantidad() * ventadetalle.getPrecio() - ventadetalle.getDescuento();
        }
        
        DeudaTotal.setText(getNumberFormat(total));
        
        loadAbonos(venta);
    }
    
    public void setCompra(int CompraID){
        isAbonoVenta = false;
        FacturaID.setText(String.valueOf(CompraID));
        Compra compra = new CompraJpaController(Conection.CreateEntityManager()).findCompra(CompraID);
        
        List<Compradetalle> detallescompra = compra.getCompradetalleList();
        float total = 0;
        for(Compradetalle compradetalle : detallescompra){
            total+= compradetalle.getCantidad() * compradetalle.getPrecio() - compradetalle.getDescuento();
        }
        
        DeudaTotal.setText(getNumberFormat(total));
        
        loadAbonos(compra);
    }
    
    private void loadAbonos(Object Transaccion){
        DefaultTableModel model = new DefaultTableModel();
        String[] columns = {"No. Abono", "Fecha", "Hora", "Total"};
        model.setColumnIdentifiers(columns);
        
        List<Abono> abonos = new ArrayList<>();
        if(isAbonoVenta){
            abonos = Conection.CreateEntityManager().createEntityManager()
                    .createNamedQuery("Abono.findByVentaID")
                    .setParameter("ventaID", (Venta) Transaccion)
                    .getResultList();
        }else{
            abonos = Conection.CreateEntityManager().createEntityManager()
                  .createNamedQuery("Abono.findByCompraID")
                  .setParameter("compraID", (Compra) Transaccion)
                  .getResultList();  
        }
        
        if(!abonos.isEmpty()){
            abonos.forEach(abono -> {
                Object[] row = {
                    abono.getAbonoID(),
                    new SimpleDateFormat("dd/MM/yyyy").format(abono.getFecha()),
                    new SimpleDateFormat("HH:mm").format(abono.getHora()),
                    getNumberFormat(abono.getTotal())
                };

                model.addRow(row);
            });
        }
        Abonos.setModel(model);
        updateTotal();
    }
    
    private void updateTotal(){
        float totalAbonado = 0;
        float pendientePago = 0;
        if(Abonos.getRowCount() > 0){
            for(int i = 0; i < Abonos.getRowCount(); i++){
                totalAbonado+=Float.parseFloat(Abonos.getValueAt(i, 3).toString().replace(",", ""));
            }
            
            TotalAbonado.setText(getNumberFormat(totalAbonado));
            pendientePago = Float.parseFloat(DeudaTotal.getText().replace(",", "")) - totalAbonado;
            TotalRestante.setText(String.valueOf(pendientePago));
            DeudaPendiente.setText(String.valueOf(pendientePago));
        }else{
            TotalRestante.setText(DeudaTotal.getText());
            DeudaPendiente.setText(DeudaTotal.getText());
        }
    }
    
    public boolean insertAbono(){
        if(validate()){
            AbonoJpaController controller = new AbonoJpaController(Conection.CreateEntityManager());
            Abono abono = createAbonoObject();
            controller.create(abono);

            updateFactura(abono);
            return true;
        }else{
            Error.setBackground(new Color(185, 0, 0));
        }
        return false;
    }
    
    private void updateFactura(Abono abono){
        float totalDeuda = Float.parseFloat(DeudaTotal.getText().replace(",", ""));
        float totalAbonado = Float.parseFloat(TotalAbonado.getText().replace(",", ""));
        System.err.println("Total abono: "+abono.getTotal());
        if((totalAbonado + abono.getTotal()) == totalDeuda){
            if(isAbonoVenta){
                try {
                    VentaJpaController controller = new VentaJpaController(Conection.CreateEntityManager());
                    Venta venta = controller.findVenta(abono.getVentaID().getVentaID());
                    venta.setEstado("P");
                    controller.edit(venta);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    System.err.println("Error: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ha ocurrido un error al actualizar estado de factura", Dialogs.ERROR_ICON);
                }
            }else{
                try {
                    CompraJpaController controller = new CompraJpaController(Conection.CreateEntityManager());
                    Compra compra = controller.findCompra(abono.getCompraID().getCompraID());
                    compra.setEstado("P");
                    controller.edit(compra);
                } catch (IllegalOrphanException | NonexistentEntityException ex) {
                    System.err.println("Error: "+ex.getMessage());
                    Dialogs.ShowMessageDialog("Ha ocurrido un error al actualizar estado de factura", Dialogs.ERROR_ICON);
                }
            }
        }
    }
    
    private Abono createAbonoObject(){
        
        Abono abono = new Abono();
        
        Venta venta = new VentaJpaController(Conection.CreateEntityManager()).findVenta(Integer.valueOf(FacturaID.getText()));
        Compra compra = new CompraJpaController(Conection.CreateEntityManager()).findCompra(Integer.valueOf(FacturaID.getText()));
        
        abono.setFecha(Utilities.getDate());
        abono.setHora(Utilities.getTime());
        abono.setUsuarioID(Utilities.getUsuarioActual());
        abono.setTipo(isAbonoVenta ? "V" : "C");
        abono.setVentaID(isAbonoVenta ? venta : null);
        abono.setCompraID(!isAbonoVenta ? compra : null);
        abono.setClienteID(isAbonoVenta ? venta.getClienteID() : null);
        abono.setProveedorID(!isAbonoVenta ? compra.getProveedorID() : null);
        abono.setTotal(Float.parseFloat(TotalAbonar.getText().replace(",", "")));
        return abono;
    }
    
    private boolean validate(){
        float total = 0; 
        if(TotalAbonar.getText().isEmpty()){
            Error.setText("El total a abonar es olbigatorio");
            return false;
        }
        try{
            total = Float.parseFloat(TotalAbonar.getText());
        }catch(NumberFormatException ex){
            Error.setText("El total a abonar debe de ser un numero");
            return false;
        }
        if(total <= 0){
            Error.setText("El total debe ser mayor a cero");
            return false;
        }
        if(total > Float.parseFloat(TotalRestante.getText().replace(",", ""))){
            Error.setText("El total no puede ser mayor al total pendiente");
            return false;
        }
        return true;
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
