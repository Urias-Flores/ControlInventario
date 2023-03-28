import Views.Dialogs.Dialogs;
import com.myapp.themes.ControlInventarioTheme;

public class TestDialog {
    
    public static void main(String[] args){
        ControlInventarioTheme.setup();
        Dialogs.ShowErrorDialog("", 0);
        //System.out.println();
        System.exit(0);
    }
}
