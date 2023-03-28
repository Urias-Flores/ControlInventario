package controlinventario;

import Resource.NoJpaConection;
import Views.Dialogs.Dialogs;
import Views.Login;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.Theme;

public class ControlInventario {

    public static void main(String[] args) {
        Runnable mainThread = () -> {
            Theme.setup();
            Dialogs.ShowConectionDialog();
            new Login().setVisible(true);
        };
        new Thread(mainThread).start();
        
        Runnable verifyConectionThread = () -> {
            while(true){
                try {
                    ResultSet rs = new NoJpaConection().getStatement().executeQuery("SELECT 1");
                    rs.next();
                    if(rs.getInt(1) != 1){
                        Dialogs.ShowMessageDialog("Se ha perdido la conexion con el servidor", Dialogs.ERROR_ICON);
                        System.exit(0);
                    }
                } catch (SQLException ex) {
                    Dialogs.ShowMessageDialog("Se ha perdido la conexion con el servidor", Dialogs.ERROR_ICON);
                    System.exit(0);
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    System.err.println("Error: "+ex.getMessage());
                }
            }
        };
        new Thread(verifyConectionThread).start();
    }
}
