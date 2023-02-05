package Reports;

import Resource.LocalDataController;
import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import java.awt.Desktop;
import java.awt.print.PrinterException;
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
    public void GenerateTickeVenta(int VentaID)
    {
        try {
            File archivo = new File("reports/FacturaVenta.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                
                Map<String, Object> parameters = getCompanyParameters();
                parameters.put("VentaID", VentaID);
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
    
    public void GenerateTickeCompra(int CompraID)
    {
        try {
            File archivo = new File("reports/FacturaCompra.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> map = getCompanyParameters();
                map.put("CompraID", CompraID);
                
                sendPrintTicket(jr, map);
            }else
            {
                Dialogs.ShowMessageDialog("El archivo base de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
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
    
    public void GenerateInventarioReport(String Usuario) throws PrinterException
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
        parameters.put("CAI", ldc.getValue("CAI"));
        
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
