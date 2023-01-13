package Reports;

import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

public class Reports {
    public void GenerateTickeVenta(int VentaID)
    {
        try {
            File archivo = new File("./reports/FacturaVenta.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("./reports/FacturaVenta.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("VentaID", VentaID);
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
    
    public void GenerateTicketCotizacion(int CotizacionID)
    {
        try {
            File archivo = new File("./reports/Cotizacion.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("./reports/Cotizacion.jrxml");
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
            File archivo = new File("./reports/FacturaCompra.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("./reports/FacturaCompra.jrxml");
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
    
    public void GenerateInventarioReport(String Usuario)
    {
        try {
            File archivo = new File("./reports/Inventario.jrxml");
            if(archivo.exists())
            {
                JasperReport jr = JasperCompileManager.compileReport("./reports/Inventario.jrxml");
                Map<String, Object> map = new HashMap<>();
                map.put("Usuario", Usuario);
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
}
