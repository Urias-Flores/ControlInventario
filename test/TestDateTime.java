
import java.util.Calendar;

public class TestDateTime {
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        System.err.println("Test: "+calendar.get(Calendar.YEAR));
        System.err.println("Test: "+calendar.get(Calendar.MONTH));
        System.err.println("Test: "+calendar.get(Calendar.DATE));
    }
}
