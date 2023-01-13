import Views.Dialogs.Dialogs;
import com.myapp.themes.ControlInventarioTheme;
import java.util.Arrays;

public class TestDialog {
    
    public static void main(String[] args){
        ControlInventarioTheme.setup();
        System.out.println(Arrays.toString(Dialogs.ShowSelectMarcaDialog()));
        System.exit(0);
    }
}
