package Reports;

import Controllers.ConfiguracionJpaController;
import Resource.Conection;
import Resource.LocalConection;
import Resource.LocalDataController;
import Resource.NoJpaConection;
import Resource.Utilities;
import Views.Dialogs.Dialogs;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Reports {
    public void GenerateTickeVenta(int VentaID, float Efectivo)
    {
        try {
            File archivo = new File("reports/FacturaVenta.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("VentaID", VentaID);
                parameters.put("Efectivo", Efectivo);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else{
                Dialogs.ShowMessageDialog("El archivo base para creacion de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketSolicitud(int SolicitudID, float Efectivo)
    {
        try {
            File archivo = new File("reports/FacturaSolicitud.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("SolicitudID", SolicitudID);
                parameters.put("Efectivo", Efectivo);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else{
                Dialogs.ShowMessageDialog("El archivo base para creacion de solicitud no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketAbono(int AbonoID, int type)
    {
        try {
            Map<Integer, String> fileName = new HashMap<>();
            fileName.put(1, "reports/Abono.jasper");
            fileName.put(1, "reports/AbonoSolicitud.jasper");
            fileName.put(1, "reports/AbonoCompra.jasper");
            
            File archivo = new File(fileName.get(type));
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("AbonoID", AbonoID);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else{
                Dialogs.ShowMessageDialog("El archivo base para creacion de solicitud no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketCotizacion(int CotizacionID)
    {
        try {
            File archivo = new File("reports/Cotizacion.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("CotizacionID", CotizacionID);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else{
                Dialogs.ShowMessageDialog("El archivo base para creacion de cotizacion no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketCompra(int CompraID)
    {
        try {
            File archivo = new File("reports/FacturaCompra.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("CompraID", CompraID);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else
            {
                Dialogs.ShowMessageDialog("El archivo base de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketArqueo(int ArqueoID)
    {
        try {
            File archivo = new File("reports/ArqueoGeneral.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("ArqueoID", ArqueoID);
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                sendPrintTicket(jr, parameters);
            }else
            {
                Dialogs.ShowMessageDialog("El archivo base de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketCloseDay(float SaldoInicial){
        try {
            File archivo = new File("reports/Arqueo.jasper");
            
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("SaldoInicial", SaldoInicial);
                parameters.put("Usuario", Utilities.getUsuarioActual().getNombre());
                parameters.put(JRParameter.IS_IGNORE_PAGINATION, true);
                
                JasperPrint print = JasperFillManager.fillReport(jr, parameters, new LocalConection().getconec());
                JasperExportManager.exportReportToPdfFile(print, "temp/tempTikect.pdf");

                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File("temp/tempTikect.pdf"));
            }else
            {
                Dialogs.ShowMessageDialog("El archivo base de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        } catch (IOException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Error al cargar archivo base de cierre", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateVentasReport(String Usuario, Date FechaInicio, Date FechaFinal){
        try {
            File archivo = new File("reports/ReporteVentas.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("Usuario", Usuario);
                parameters.put("FechaInicio", FechaInicio);
                parameters.put("FechaFinal", FechaFinal);
                
                sendPrintReport(jr, parameters);
            }
            else{
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateInventarioReport(String Usuario)
    {
        try {
            File archivo = new File("reports/Inventario.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> map = getCompanyParameters();
                map.put("Usuario", Usuario);
                
                sendPrintReport(jr, map);
            }
            else{
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    private Map<String, Object> getCompanyParameters(){
        Map<String, Object> parameters = new HashMap<>();
        
        LocalDataController ldc = new LocalDataController();
        parameters.put("NombreTienda", ldc.getValue("Company"));
        parameters.put("RTN", ldc.getValue("RTN"));
        parameters.put("NumeroTelefono", ldc.getValue("NumberPhone"));
        parameters.put("Direccion", ldc.getValue("Address"));
        
        ConfiguracionJpaController controllerConfiguracion = new ConfiguracionJpaController(Conection.createEntityManagerFactory());
        
        parameters.put("Desde", controllerConfiguracion.findConfiguracion(2).getDato());
        parameters.put("Hasta", controllerConfiguracion.findConfiguracion(3).getDato());
        parameters.put("FechaLimite", controllerConfiguracion.findConfiguracion(4).getDato());
        parameters.put("CAI", controllerConfiguracion.findConfiguracion(5).getDato());
        parameters.put("Correo", controllerConfiguracion.findConfiguracion(6).getDato());
        
        return parameters;
    }
    
    private void sendPrintTicket(JasperReport jr, Map<String, Object> parameters){
        try {
            JasperPrint print = JasperFillManager.fillReport(jr, parameters, new NoJpaConection().getconec());
            JasperExportManager.exportReportToPdfFile(print, "temp/tempTikect.pdf");
            
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("temp/tempTikect.pdf"));
        } catch (JRException | IOException ex) {
            System.err.println("Error"+ex.getMessage());
            Dialogs.ShowMessageDialog("Error al intentar abrir archivo de impresion", Dialogs.ERROR_ICON);
        }
    }
    
    private void sendPrintReport(JasperReport jr, Map<String, Object> parameters){
        try {
            JasperPrint print = JasperFillManager.fillReport(jr, parameters, new NoJpaConection().getconec());
            JasperExportManager.exportReportToPdfFile(print, "temp/tempReport.pdf");
            
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("temp/tempReport.pdf"));
        } catch (JRException | IOException ex) {
            System.err.println("Error"+ex.getMessage());
            Dialogs.ShowMessageDialog("Error al intentar abrir archivo de impresion", Dialogs.ERROR_ICON);
        }
    }
    
    private String getLetterPrint(){
        return "Microsoft Print to PDF".toUpperCase();
    }
}
