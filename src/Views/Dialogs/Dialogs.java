package Views.Dialogs;

public class Dialogs {
    
    public static int DELETE_ICON = -2;
    public static int WARNING_ICON = -1;
    public static int ERROR_ICON = 0;
    public static int COMPLETE_ICON = 1;
    
    public static void ShowConectionDialog(){
        LoadDialogWithDownloadOption ld = new LoadDialogWithDownloadOption(null, true);
        ld.setVisible(true);
        ld.dispose();
    }
    
    public static void ShowMessageDialog(String texto, int Icono){
        GeneralDialog gd = new GeneralDialog(null, true);
        gd.setText(texto);
        gd.setIcon(Icono);
        gd.setVisible(true);
        gd.dispose();
    }
    
    public static boolean ShowOKCancelDialog(String texto, int Icono){
        OkCancelDialog ocd = new OkCancelDialog(null, true);
        ocd.setText(texto);
        ocd.setIcon(Icono);
        ocd.setVisible(true);
        boolean result = ocd.getValue();
        ocd.dispose();
        return result;
    }
    
    public static boolean ShowEnterPasswordDialog(String text, String text1, String text2, int Icon){
        EnterPasswordDialogs epd = new EnterPasswordDialogs(null, true);
        epd.setText(text);
        epd.setText1(text1);
        epd.setText2(text2);
        epd.setIcon(Icon);
        epd.setVisible(true);
        boolean value = epd.getValue();
        epd.dispose();
        return value;
    }
    
    public static void ShowAddProductoDialog(){
        AddProductoDialog pd = new AddProductoDialog(null, true);
        pd.setVisible(true);
        pd.dispose();
    }
    
    public static void ShowModifyProductDialog(int ProductoID){
        AddProductoDialog pd = new AddProductoDialog(null, true);
        pd.EditigingMode(ProductoID);
        pd.setVisible(true);
        pd.dispose();
    }
    
    public static void ShowInfoProducto(int ProductoID){
        InfoProductoDialog ipd = new InfoProductoDialog(null, true);
        ipd.loadProduct(ProductoID);
        ipd.setVisible(true);
        ipd.dispose();
    }
    
    public static Object[] ShowAddCompraDialog(){
        AddCompraDialog cd = new AddCompraDialog(null, true);
        cd.setVisible(true);
        Object[] values = cd.getValues();
        cd.dispose();
        return values;
    }
    
    public static Object[] ShowEditCompraDialog(Object[] values){
        EditVentaDialog evd = new EditVentaDialog(null, true);
        evd.setValues(values);
        evd.setCompraState();
        evd.setVisible(true);
        values = evd.getValues();
        evd.dispose();
        return values;
    }
    
    public static Object[] ShowAddVentaDialog(){
        AddVentaDialog vd = new AddVentaDialog(null, true);
        vd.setVisible(true);
        Object[] values = vd.getValues();
        vd.dispose();
        return values;
    }
    
    public static Object[] ShowEditVentaDialog(Object[] values){
        EditVentaDialog evd = new EditVentaDialog(null, true);
        evd.setValues(values);
        evd.setVisible(true);
        values = evd.getValues();
        evd.dispose();
        return values;
    }
    
    public static void ShowAddProveedorDialog(){
        AddProveedorDialog pd = new AddProveedorDialog(null, true);
        pd.setVisible(true);
        pd.dispose();
    }
    
    public static void ShowModifyProveedorDialog(int ProveedorID){
        AddProveedorDialog pd = new AddProveedorDialog(null, true);
        pd.EditingMode(ProveedorID);
        pd.setVisible(true);
        pd.dispose();
    }
    
    public static void ShowAddClienteDialog(){
        AddClienteDialog cd = new AddClienteDialog(null, true);
        cd.setVisible(true);
        cd.dispose();
    }
    
    public static void ShowModifyClienteDialog(int ClienteID){
        AddClienteDialog cd = new AddClienteDialog(null, true);
        cd.EditingMode(ClienteID);
        cd.setVisible(true);
        cd.dispose();
    }
    
    public static void ShowAddUsuarioDialog(){
        AddUsuarioDialog ud = new AddUsuarioDialog(null, true);
        ud.setVisible(true);
        ud.dispose();
    }
    
    public static void ShowModifyUsuarioDialog(int UsuarioID){
        AddUsuarioDialog ud = new AddUsuarioDialog(null, true);
        ud.EditingMode(UsuarioID);
        ud.setVisible(true);
        ud.dispose();
    }
    
    public static void ShowAddEmpleadoDialog(){
        AddEmpleadoDialog ed = new AddEmpleadoDialog(null, true);
        ed.setVisible(true);
        ed.dispose();
    }
    
    public static void ShowModifyEmpleadoDialog(int EmpleadoID){
        AddEmpleadoDialog ed = new AddEmpleadoDialog(null, true);
        ed.EditingMode(EmpleadoID);
        ed.setVisible(true);
        ed.dispose();
    }
    
    public static Object[] ShowEmpleadosDialog(){
        EmpleadosDialog ed = new EmpleadosDialog(null, true);
        ed.setVisible(true);
        Object[] values = ed.getValue();
        ed.dispose();
        return values;
    }
    
    public static void ShowInfoEmpleadoDialog(int EmpleadoID){
        InfoEmpleadoDialog ied = new InfoEmpleadoDialog(null, true);
        ied.loadEmployee(EmpleadoID);
        ied.setVisible(true);
        ied.dispose();
    }
    
    public static void ShowInfoUsuarioDialog(int EmpleadoID){
        InfoUsuarioDialog ied = new InfoUsuarioDialog(null, true);
        ied.loadUser(EmpleadoID);
        ied.setVisible(true);
        ied.dispose();
    }
    
    public static void ShowMarcasDialog(){
        MarcasDialog md = new MarcasDialog(null, true);
        md.setVisible(true);
        md.dispose();
    }
    
    public static Object[] ShowSelectMarcaDialog(){
        MarcasDialog md = new MarcasDialog(null, true);
        md.SelectionMode();
        md.setVisible(true);
        Object[] value = md.getValue();
        md.dispose();
        return value;
    }
    
    public static void ShowCategoriasDialog(){
        CategoriasDialog md = new CategoriasDialog(null, true);
        md.setVisible(true);
        md.dispose();
    }
    
    public static Object[] ShowSelectCategoriaDialog(){
        CategoriasDialog cd = new CategoriasDialog(null, true);
        cd.SelectionMode();
        cd.setVisible(true);
        Object[] value = cd.getValue();
        cd.dispose();
        return value;
    }
    
    public static void ShowAddMarcaDialog(){
        AddMarcaDialog md = new AddMarcaDialog(null, true);
        md.setVisible(true);
        md.dispose();
    }
    
    public static void ShowAddCategoriaDialog(){
        AddCategoriaDialog md = new AddCategoriaDialog(null, true);
        md.setVisible(true);
        md.dispose();
    }
    
    public static void ShowAddInventarioDetalle(int InventarioID){
        AddInventarioDetalle aid = new AddInventarioDetalle(null, true);
        aid.setInventarioID(InventarioID);
        aid.setInventario();
        aid.setVisible(true);
        aid.dispose();
    }
    
    public static void ShowAddInventarioDetalleForDelete(int InventarioID){
        AddInventarioDetalleEliminacion aide = new AddInventarioDetalleEliminacion(null, true);
        aide.setInventarioID(InventarioID);
        aide.setVisible(true);
        aide.dispose();
    }
    
    public static void ShowInventarioAcciones(){
        AccionesInventarioDialog aid = new AccionesInventarioDialog(null, true);
        aid.setVisible(true);
        aid.dispose();
    }
    
    public static void ShowInfoInventarioAccion(int AccionID){
        InfoInventarioAccion i = new InfoInventarioAccion(null, true);
        i.loadAction(AccionID);
        i.setVisible(true);
        i.dispose();
    }
    
    public static void ShowCuentasClienteDialog(int ClienteID){
        CuentasPendientesDialog cpd = new CuentasPendientesDialog(null, true);
        cpd.cargar(ClienteID, true);
        cpd.setVisible(true);
        cpd.dispose();
    }
    
    public static void ShowAbonosClientesDialog(int VentaID){
        AddAbonoDialog ad = new AddAbonoDialog(null, true);    
        ad.setFactura(VentaID, "V");
        ad.setVisible(true);
        ad.dispose();
    }
    
    public static void ShowCuentasProveedorDialog(int ProveedorID){
        CuentasPendientesDialog cpd = new CuentasPendientesDialog(null, true);
        cpd.setCompraState();
        cpd.cargar(ProveedorID, false);
        cpd.setVisible(true);
        cpd.dispose();
    }
    
    public static void ShowAbonosProveedorDialog(int CompraID){
        AddAbonoDialog ad = new AddAbonoDialog(null, true);    
        ad.setFactura(CompraID, "C");
        ad.setVisible(true);
        ad.dispose();
    }
    
    public static void ShowDetalleFactura(int VentaID){
        DetalleFacturaDialog dfd = new DetalleFacturaDialog(null, true);
        dfd.load(VentaID, true);
        dfd.setVisible(true);
        dfd.dispose();
    }
    
    public static void ShowDetalleCompra(int CompraID){
        DetalleFacturaDialog dfd = new DetalleFacturaDialog(null, true);
        dfd.load(CompraID, false);
        dfd.setVisible(true);
        dfd.dispose();
    }
    
    public static void ShowNotificacionesDialog(){
        NotificacionesDialog nd = new NotificacionesDialog(null, true);
        nd.setVisible(true);
        nd.dispose();
    }
    
    public static float ShowArqueoDialog(int FacturaID, boolean isCredit, float Total){
        AddArqueoDetalleDialog aad = new AddArqueoDetalleDialog(null, true);
        aad.setBill(FacturaID, isCredit, Total);
        aad.setVisible(true);
        float efectivo = aad.getValue();
        aad.dispose();
        return efectivo;
    }
    
    public static void ShowInitDayDialog(){
        IniciarDiaDialog idd = new IniciarDiaDialog(null, true);
        idd.setVisible(true);
        idd.dispose();
    }
    
    public static void ShowCloseDayDialog(){
        AddCierreDialog acd = new AddCierreDialog(null, true);
        acd.setVisible(true);
        acd.dispose();
    }
}
