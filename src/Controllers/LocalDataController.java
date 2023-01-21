package Controllers;

import Models.Notificacion;
import Resource.Conection;
import Resource.LocalConection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalDataController {
    
    LocalConection localConection = new LocalConection();
    
    public String getValue(String Data){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT Value FROM data WHERE Data = '"+Data+"'");
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }
    
    public void UpdateData(String Data, String Value){
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("UPDATE data SET Value = ? WHERE Data = ?");
            ps.setString(1, Value);
            ps.setString(2, Data);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(LocalDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
