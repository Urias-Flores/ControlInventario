package ViewsControllers.Dialogs;

import Controllers.AbonoJpaController;
import Controllers.ClienteJpaController;
import Controllers.CompraJpaController;
import Controllers.ProveedorJpaController;
import Controllers.SolicitudJpaController;
import Controllers.VentaJpaController;
import Controllers.exceptions.IllegalOrphanException;
import Controllers.exceptions.NonexistentEntityException;
import Models.Abono;
import Models.Cliente;
import Models.Compra;
import Models.Proveedor;
import Models.Solicitud;
import Models.Venta;
import Reports.Reports;
import Resource.Conection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.text.DecimalFormat;
import java.util.List;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CuentasPendientesDialogViewController {
    private JLabel Nombre;
    private JTable Cuentas;
    private JLabel Total;
    private JLabel Cargando;
    
    private int Cliente = 0;
    private int Proveedor = 0;
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column){ return false; }
    };

    public void setCliente(int Cliente) {
        this.Cliente = Cliente;
        loadClient();
    }

    public void setProveedor(int Proveedor) {
        this.Proveedor = Proveedor;
        loadSupplier();
    }
    
    public CuentasPendientesDialogViewController(JLabel Nombre, JTable Cuentas, JLabel Total, JLabel Cargando) {
        this.Nombre = Nombre;
        this.Cuentas = Cuentas;
        this.Total = Total;
        this.Cargando = Cargando;
        
        //Cargando modelo de tabla
        setModelTable();
    }
    
    private void setLoad(boolean state){
        ImageIcon icon = new ImageIcon(getClass().getResource(Utilities.getLoadingImage()));
        Cargando.setIcon(state ? icon : null);
    }
    
    private void setModelTable(){
        String[] columns = {"No.", "Tipo","Fecha", "Hora", "Total", "Abonado"};
        model.setColumnIdentifiers(columns);
        
        Cuentas.setModel(model);  
        Cuentas.getColumn("No.").setPreferredWidth(55);
        Cuentas.getColumn("Tipo").setPreferredWidth(55);
        Cuentas.getColumn("Fecha").setPreferredWidth(80);
        Cuentas.getColumn("Hora").setPreferredWidth(80);
        Cuentas.getColumn("Total").setPreferredWidth(110);
        Cuentas.getColumn("Abonado").setPreferredWidth(110);
    }
    
    //Task
    private void loadClient(){
        setLoad(true);
        model.setRowCount(0);
        
        Runnable run = () -> {
            Cliente cliente = new ClienteJpaController(Conection.createEntityManagerFactory()).findCliente(Cliente);
            if(cliente != null){
                Nombre.setText(cliente.getNombre());
                StoredProcedureQuery sp = Conection.createEntityManagerFactory().createEntityManager()
                        .createStoredProcedureQuery("ProcedureFacturaCliente")
                        .registerStoredProcedureParameter("Cliente", Integer.class, ParameterMode.IN);
                sp.setParameter("Cliente", Cliente);

                List<Object[]> facturas = sp.getResultList();
                facturas.forEach(factura -> {
                    if(factura[4] != null){
                        factura[4] = getNumberFormat(Float.parseFloat(factura[4].toString()));
                        if(factura[1].toString().equals("Venta")){
                            factura[5] = getNumberFormat(getTotalBillCredits(new Venta(Integer.valueOf(factura[0].toString()))));
                        } else {
                            factura[5] = getNumberFormat(getTotalRequestCredits(new Solicitud(Integer.valueOf(factura[0].toString()))));
                        }
                    }
                    model.addRow(factura);
                });
                updateTotal();
            }
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //Task
    private void loadSupplier(){
        setLoad(true);
        model.setRowCount(0);
        Runnable run = () -> {
            Proveedor proveedor = new ProveedorJpaController(Conection.createEntityManagerFactory()).findProveedor(Proveedor);
            if(proveedor != null){
                Nombre.setText(proveedor.getNombre());
                StoredProcedureQuery sp = Conection.createEntityManagerFactory().createEntityManager()
                        .createStoredProcedureQuery("ProcedureFacturaProveedor")
                        .registerStoredProcedureParameter("Proveedor", Integer.class, ParameterMode.IN);
                sp.setParameter("Proveedor", Proveedor);

                List<Object[]> facturas = sp.getResultList();
                facturas.forEach(factura -> {
                    if(factura[4] != null){
                        factura[4] = getNumberFormat(Float.parseFloat(factura[4].toString()));
                        factura[5] = getNumberFormat(getTotalBuyCredits(new Compra(Integer.valueOf(factura[0].toString()))));
                    }
                    model.addRow(factura);
                });
                updateTotal();
            }
            setLoad(false);
        };
        new Thread(run).start();
    }
    
    //In Task
    private float getTotalBillCredits(Venta VentaID){
        List<Abono> abonos = Conection.createEntityManagerFactory().createEntityManager()
                .createNamedQuery("Abono.findByVentaID")
                .setParameter("ventaID", VentaID)
                .getResultList();
        float TotalAbonado = 0;
        if(!abonos.isEmpty()){
            for(Abono abono : abonos){
                TotalAbonado+=abono.getTotal();
            }
        }
        return TotalAbonado;
    }
    
    private float getTotalRequestCredits(Solicitud SolicitudID){
        List<Abono> abonos = Conection.createEntityManagerFactory().createEntityManager()
                .createNamedQuery("Abono.findBySolicitudID")
                .setParameter("solicitudID", SolicitudID)
                .getResultList();
        float TotalAbonado = 0;
        if(!abonos.isEmpty()){
            for(Abono abono : abonos){
                TotalAbonado+=abono.getTotal();
            }
        }
        return TotalAbonado;
    }
    
    //In Task
    private float getTotalBuyCredits(Compra CompraID){
        List<Abono> abonos = Conection.createEntityManagerFactory().createEntityManager()
                .createNamedQuery("Abono.findByCompraID")
                .setParameter("compraID", CompraID)
                .getResultList();
        
        float TotalAbonado = 0;
        if(!abonos.isEmpty()){
            for(Abono abono : abonos){
                TotalAbonado+=abono.getTotal();
            }
        }
        
        return TotalAbonado;
    }
    
    public void loadCredits(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            if(Cliente != 0){ 
                if(Cuentas.getValueAt(fila, 1).toString().equals("Venta")){
                    Dialogs.ShowAbonosVentaClienteDialog(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
                } else if(Cuentas.getValueAt(fila, 1).toString().equals("Solicitud")){
                    Dialogs.ShowAbonosSolicitudClienteDialog(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
                }
                loadClient(); 
            }else if(Proveedor != 0){ 
                Dialogs.ShowAbonosProveedorDialog(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
                loadSupplier(); 
            }
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void loadBillDetails(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowDetalleFactura(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    public void loadBuyDetails(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            Dialogs.ShowDetalleCompra(Integer.parseInt(Cuentas.getValueAt(fila, 0).toString()));
        }else{
            Dialogs.ShowMessageDialog("Seleccione una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void payBill(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            float totalAporPagar = 
                    Float.parseFloat(Cuentas.getValueAt(fila, 4).toString().replace(",", "")) 
                  - Float.parseFloat(Cuentas.getValueAt(fila, 5).toString().replace(",", ""));
            
            if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada una factura se realizara un abono", 
                    "a la factura con un valor de: "+getNumberFormat(totalAporPagar)+" Lps. por medidas de seguridad", 
                    "Por favor ingrese su contraseña de usuario para pagar.", Dialogs.WARNING_ICON)){
                
                setLoad(true);
                Runnable run = ()->{
                    try {
                        int TransaccionID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());                        
                        //Creando controllador de abono
                        AbonoJpaController controllerAbono = new AbonoJpaController(Conection.createEntityManagerFactory());
                        //Obteniendo tipo de dato que se va ingresar
                        final String transaccionType = Cuentas.getValueAt(fila, 1).toString();
                        Abono abono;
                        
                        switch (transaccionType) {
                            case "Venta":
                                
                                //Creando objeto de venta
                                VentaJpaController controllerVenta = new VentaJpaController(Conection.createEntityManagerFactory());
                                Venta venta = controllerVenta.findVenta(TransaccionID);
                                
                                venta.setEstado("P");
                                abono = createObjectAbono(venta, null, null, fila);
                                
                                //Creando un abono conrespecto al pago realizado
                                int AbonoID = controllerAbono.create(abono);
                                        
                                abono.setAbonoID(AbonoID);
                                System.err.println("Venta en abono: "+abono.getVentaID());
                                
                                //Enviando a editar la venta para marcar como pagada
                                controllerVenta.edit(venta);
                                
                                break;
                            case "Solicitud":
                                
                                //Creando obejto de solicitud
                                SolicitudJpaController controllerSolicitud = new SolicitudJpaController(Conection.createEntityManagerFactory());
                                Solicitud solicitud = controllerSolicitud.findSolicitud(TransaccionID);
                                
                                solicitud.setEstado("P");
                                abono = createObjectAbono(null, null, solicitud, fila);
                                
                                //Creando un abono conrespecto al pago realizado
                                abono.setAbonoID(controllerAbono.create(abono));
                                
                                //Enviando a editar la solicitud para marca como pagada
                                controllerSolicitud.edit(solicitud);
                                
                                break;
                            default:
                                throw new AssertionError();
                        }
                        
                        setLoad(false);
                        loadClient();
                        Dialogs.ShowMessageDialog("La factura ha sido marcada como pagada exitosamente", Dialogs.COMPLETE_ICON);
                        
                        //Definiendo se enviara a imprimir el recibo de pago
                        if(abono != null){
                            if(Dialogs.ShowOKCancelDialog("¿Deseea enviar a imprimir el rebido de abono a cuenta?", Dialogs.WARNING_ICON)){
                                setLoad(true);
                                Runnable runnable = () -> {
                                    Reports reports = new Reports();
                                    int type = transaccionType.equals("Venta") ? 1 : 2;
                                    reports.GenerateTicketAbono(abono.getAbonoID(), type);
                                    setLoad(false);
                                };
                                new Thread(runnable).start();
                            }
                        }
                    } catch (IllegalOrphanException | NonexistentEntityException | NumberFormatException ex) {
                        System.err.println("Error al enviar a pagar factura: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar", Dialogs.ERROR_ICON);
                    }
                };
                new Thread(run).start();
            }
        } else {
            Dialogs.ShowMessageDialog("Seleccion una factura de la lista", Dialogs.ERROR_ICON);
        }
    }
    
    //Task
    public void payBuy(){
        int fila = Cuentas.getSelectedRow();
        if(fila >= 0){
            if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada una compra", 
                    "con un valor de: "+Cuentas.getValueAt(fila, 3).toString()+" Lps. por medidas de seguridad", 
                    "Por favor ingrese su contraseña de usuario para pagar.", Dialogs.WARNING_ICON)){
                setLoad(true);
                Runnable run = () ->{
                    try {
                        int CompraID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                        //Creando objeto de compra
                        CompraJpaController controllerCompra = new CompraJpaController(Conection.createEntityManagerFactory());
                        Compra compra = controllerCompra.findCompra(CompraID);
                        compra.setEstado("P");
                        
                        //Creando objeto de abono
                        AbonoJpaController controllerAbono = new AbonoJpaController(Conection.createEntityManagerFactory());
                        Abono abono = createObjectAbono(null, compra, null, fila);
                        
                        //Enviando a marcas la compra como pagada
                        controllerCompra.edit(compra);
                        //Enviando a agregas abono por total de factura
                        controllerAbono.create(abono);
                        
                        setLoad(false);
                        loadSupplier();
                        Dialogs.ShowMessageDialog("La compra ha sido marcada como pagada exitosamente", Dialogs.COMPLETE_ICON);
                        
                        //Enviando a imprimir recibo de abono propio
                        if(Dialogs.ShowOKCancelDialog("¿Deseea enviar a imprimir el recibo de abono a cuenta pendiente?", fila)){
                            setLoad(true);
                            Runnable runnable = () -> {
                                //Codigo para enviar a imprimir un ticket de abono a cuenta
                                setLoad(false);
                            };
                            new Thread(runnable).start();
                        }
                       
                    } catch (NonexistentEntityException | IllegalOrphanException ex) {
                        System.err.println("Error: "+ex.getMessage());
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar", Dialogs.ERROR_ICON);
                    }
                };
                new Thread(run).start();
            }
        }
    }
    
    public Abono createObjectAbono(Venta venta, Compra compra, Solicitud solicitud, int fila){
        Abono abono = new Abono();
        
        abono.setFecha(Utilities.getDate());
        abono.setHora(Utilities.getTime());
        abono.setUsuarioID(Utilities.getUsuarioActual());
        abono.setTipo(Cliente != 0 && solicitud == null ? "V" : Cliente != 0 && solicitud != null ? "S" : "C");
        abono.setVentaID(venta);
        abono.setSolicitudID(solicitud);
        abono.setCompraID(compra);
        
        float totalFactura = Float.parseFloat(Cuentas.getValueAt(fila, 4).toString().replace(",", ""));
        float totalAbonado = Float.parseFloat(Cuentas.getValueAt(fila, 5).toString().replace(",", ""));
        float totalAporPagar = totalFactura - totalAbonado;
        
        abono.setTotal(totalAporPagar);

        return abono;
    }
    
    public void payBills(){
        if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada todas las facturas", 
                    "pendientes de pago con un valor total de: "+Total.getText()+" Lps. ", 
                    "por seguridad por favor ingrese su contraseña para pagar.", Dialogs.WARNING_ICON)){
            
            setLoad(true);
            Runnable run = () -> {
                int fila = 0;
                boolean state = true;
                while (fila < Cuentas.getRowCount()){
                    int VentaID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                    //Creando objeto de venta
                    VentaJpaController controllerVenta = new VentaJpaController(Conection.createEntityManagerFactory());
                    Venta venta = controllerVenta.findVenta(VentaID);
                    venta.setEstado("P");
                    
                    try {
                        //Creando objeto de abono a factura
                        AbonoJpaController controllerAbono = new AbonoJpaController(Conection.createEntityManagerFactory());
                        Abono abono = createObjectAbono(venta, null, null, fila);
                        
                        //Enviando a marcar venta como pagada
                        controllerVenta.edit(venta);
                        //Creando abono a cuenta con valor de la factura
                        controllerAbono.create(abono);
                    } catch (NonexistentEntityException | IllegalOrphanException ex) {
                        System.err.println("Test: "+ex.getMessage());
                        state = false;
                        Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo la factura "+venta.getVentaID(), Dialogs.ERROR_ICON);
                    }
                    fila++;
                }
                
                if(state){
                    loadClient();
                    Dialogs.ShowMessageDialog("Todas las facturas han sido marcadas como pagadas exitosamente", Dialogs.COMPLETE_ICON);
                }
            };
            new Thread(run).start();
        }
    }
    
    public void payBuys(){
        if(Dialogs.ShowEnterPasswordDialog("Esta a punto de marcar como pagada todas las compras", 
                    "pendientes de pago con un valor total de: "+Total.getText()+" Lps. ", 
                    "por seguridad por favor ingrese su contraseña para pagar.", Dialogs.WARNING_ICON)){
            boolean state = true;
            
            int fila = 0;
            
            while (fila < Cuentas.getRowCount()){
                int CompraID = Integer.parseInt(Cuentas.getValueAt(fila, 0).toString());
                CompraJpaController controllerCompra = new CompraJpaController(Conection.createEntityManagerFactory());
                Compra compra = controllerCompra.findCompra(CompraID);
                compra.setEstado("P");
                
                try {
                    controllerCompra.edit(compra);
                } catch (NonexistentEntityException | IllegalOrphanException ex) {
                    System.err.println("Test: "+ex.getMessage());
                    state = false;
                    Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error, no se pudo pagar la compra "+compra.getCompraID(), Dialogs.ERROR_ICON);
                }
                fila++;
            }
            
            if(state){
                loadSupplier();
                Dialogs.ShowMessageDialog("Las facturas han sido marcadas como pagadas exitosamente", Dialogs.COMPLETE_ICON);
            }
        }
    }
    
    public void updateTotal(){
        float total = 0;
        float totalAbonado = 0;
        for(int i = 0; i < Cuentas.getModel().getRowCount(); i++){
            if(Cuentas.getValueAt(i, 3) != null && Cuentas.getValueAt(i, 4) != null){
                total += Float.parseFloat(Cuentas.getValueAt(i, 4).toString().replace(",", ""));
                totalAbonado += Float.parseFloat(Cuentas.getValueAt(i, 5).toString().replace(",", ""));
            }
        }
        
        Total.setText(getNumberFormat(total - totalAbonado));
    }
    
    private String getNumberFormat(float Value){
        DecimalFormat format = new DecimalFormat("#,##0.00");
        return format.format(Value);
    }
}
