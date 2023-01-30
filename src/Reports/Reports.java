package Reports;

import Controllers.LocalDataController;
import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Reports {
    public void GenerateTickeVenta(int VentaID)
    {
        try {
            File archivo = new File("reports/FacturaVenta.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("reports/FacturaVenta.jrxml");
                Map<String, Object> map = new HashMap<>();
                LocalDataController ldc = new LocalDataController();
                
                map.put("VentaID", VentaID);
                map.put("NombreTienda", ldc.getValue("Company"));
                map.put("RNT", ldc.getValue("RTN"));
                map.put("NumeroTelefono", ldc.getValue("NumberPhone"));
                map.put("Direccion", ldc.getValue("Address"));
                
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                JasperExportManager.exportReportToPdfFile(print, "temp/tempTikect.pdf");
                
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File("temp/tempTikect.pdf"));
            }else
            {
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        } catch (IOException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Error al buscar archivo de impresion", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTicketCotizacion(int CotizacionID)
    {
        try {
            File archivo = new File("reports/Cotizacion.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("reports/Cotizacion.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("CotizacionID", CotizacionID);
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                JasperPrintManager.printReport(print, true);
            }else
            {
                //Mensaje a mostrar en caso de no encontrar archio modelo
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
        }
    }
    
    public void GenerateTickeCompra(int CompraID)
    {
        try {
            File archivo = new File("reports/FacturaCompra.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("reports/FacturaCompra.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("CompraID", CompraID);
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                JasperPrintManager.printReport(print, true);
            }else
            {
                //Mensaje a mostrar en caso de no encontrar archio modelo
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            
        }
    }
    
    public void GenerateInventarioReport(String Usuario) throws PrinterException
    {
        try {
            File archivo = new File("reports/Inventario.jasper");
            if(archivo.exists())
            {
                JasperReport jr = (JasperReport) JRLoader.loadObject(archivo);
                Map<String, Object> map = new HashMap<>();
                map.put("Usuario", Usuario);
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                JasperExportManager.exportReportToPdfFile(print, "temp/tempReport.pdf");
                
                Desktop desktop = Desktop.getDesktop();
                desktop.open(new File("temp/tempReport.pdf"));
            }
            else{
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("TestPrint: "+ex.getMessage());
        } catch (IOException ex) {
            System.err.println("TestIO: "+ex.getMessage());
        }
    }
    
    public String getLetterPrint(){
        return "Microsoft Print to PDF".toUpperCase();
    }
}
