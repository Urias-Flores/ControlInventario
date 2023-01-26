
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class TestPrint {
    
    public static void main(String[] args){
         
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);
        List<String> printers = new ArrayList<>();
        for(PrintService printService : printServices){
           printers.add(printService.getName());
        }
        
        printers.forEach(printer -> {
             System.out.println("Impresora: "+printer);
        });
        
    }
}
