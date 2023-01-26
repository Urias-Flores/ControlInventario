package Reports;

import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import java.awt.PrintJob;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSizeName;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;

public class Reports {
    public void GenerateTickeVenta(int VentaID)
    {
        try {
            File archivo = new File("reports/FacturaVenta.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("reports/FacturaVenta.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("VentaID", VentaID);
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                JasperPrintManager.printReport(print, true);
            }else
            {
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            System.err.println("Error: "+ex.getMessage());
            Dialogs.ShowMessageDialog("Ups... Ha ocurrido un error al enviar a imprimir", Dialogs.ERROR_ICON);
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
            File archivo = new File("reports/Inventario.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("reports/Inventario.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("Usuario", Usuario);
                JasperPrint print = JasperFillManager.fillReport(jr, map, new NoJpaConection().getconec());
                
                PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
                PrintService printServiceSelected = null;
                
                for(PrintService printService : printServices){
                   if(printService.getName().toUpperCase().contains(getLetterPrint())){
                       printServiceSelected = printService;
                   }
                }
                
                if(printServiceSelected != null){
                    PrinterJob job = PrinterJob.getPrinterJob();
                    job.setPrintService(printServiceSelected);
                    PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                    MediaSizeName mediaSizeName = MediaSize.findMedia(4,4,MediaPrintableArea.INCH);
                    printRequestAttributeSet.add(mediaSizeName);
                    printRequestAttributeSet.add(new Copies(1));

                    JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                    exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                    /* We set the selected service and pass it as a parameter */
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE,
                        printServiceSelected);
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET,
                        printServiceSelected.getAttributes());
                    exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET,
                        printRequestAttributeSet);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG,
                        Boolean.FALSE);
                    exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG,
                        Boolean.FALSE);
                    exporter.exportReport();
                }else{
                    Dialogs.ShowMessageDialog("La impresora seleccionada no fue encontrada", Dialogs.ERROR_ICON);
                }
            }else
            {
                //Mensaje a mostrar en caso de no encontrar archio modelo
                Dialogs.ShowMessageDialog("El archivo de modelo de factura no fue encontrado", Dialogs.ERROR_ICON);
            }
        } catch (JRException ex) {
            
        }
    }
    
    public String getLetterPrint(){
        return "Microsoft Print to PDF".toUpperCase();
    }
}
