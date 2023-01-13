
import java.text.DecimalFormat;


public class TestNumberFormat {
    public static void main(String[] args){
        DecimalFormat format = new DecimalFormat("#,###.00");
        String valor = "145890";
        System.err.println("Test: "+format.format(105.3756f));
        String codigo = "123456";
        System.out.println("Test space: "+codigo.replace("", " "));
    }
}
