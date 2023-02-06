package controlinventario;

//import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import Views.Login;
import main.Theme;

public class ControlInventario {

    public static void main(String[] args) {
        Theme.setup();
        Dialogs.ShowConectionDialog();
        new Login().setVisible(true);
    }
}
