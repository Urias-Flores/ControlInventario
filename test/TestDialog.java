import Views.Dialogs.Dialogs;
import com.myapp.themes.ControlInventarioTheme;

public class TestDialog {
    
    public static void main(String[] args){
        ControlInventarioTheme.setup();
        System.out.println(Dialogs.ShowEnterPasswordDialog("", "", "", Dialogs.WARNING_ICON));
        System.exit(0);
    }
}
