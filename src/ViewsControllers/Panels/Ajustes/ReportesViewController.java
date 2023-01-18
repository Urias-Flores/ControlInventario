package ViewsControllers.Panels.Ajustes;

import Views.Dialogs.Dialogs;
import java.awt.Dialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

public class ReportesViewController {

    public String FACTURA_VENTA = "./reports/FacturaVenta.jrxml";
    public String FACTURA_COMPRA = "./reports/FacturaCompra.jrxml";
    public String FACTURA_COTIZACION = "./reports/Cotizacion.jrxml";
    public String REPORTE_INVENTARIO = "./reports/Inventario.jrxml";

    public boolean changeReportFile(String reportPath) {
        JFileChooser jfc = new JFileChooser(".jrxml");

        int result = jfc.showDialog(null, "Guardar");

        if (result == JFileChooser.APPROVE_OPTION) {
            FileOutputStream outputStream = null;
            try {
                String path = jfc.getSelectedFile().getAbsolutePath();
                File file = new File(path);

                outputStream = new FileOutputStream(reportPath);
                byte[] b = new byte[(int) file.length()];
                outputStream.write(b);
                System.out.println("Archivo escrito");
                return true;
            } catch (FileNotFoundException ex) {

                System.err.println("Error: " + ex.getMessage());
                Dialogs.ShowMessageDialog("El archivo seleccionado no fue encontrado", Dialogs.ERROR_ICON);

            } catch (IOException ex) {

                System.err.println("Error: " + ex.getMessage());
                Dialogs.ShowMessageDialog("Hubo un error al almacenar archivo", Dialogs.ERROR_ICON);

            } finally {
                try {
                    outputStream.close();
                } catch (IOException ex) {
                    Logger.getLogger(ReportesViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }

}
