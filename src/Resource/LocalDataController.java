package Resource;

import Controllers.NotificacionJpaController;
import Models.Notificacion;
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
    
    public boolean checkCambios(){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT COUNT(*) FROM Notificacion");
            rs.next();
            int TotalNotificacionesLocal  = rs.getInt(1);
            
            List<Notificacion> notificaciones = new NotificacionJpaController(Conection.CreateEntityManager())
                    .findNotificacionEntities();
            int TotalNotificacionesBaseDatos = notificaciones.size();
            
            if(TotalNotificacionesLocal < TotalNotificacionesBaseDatos){
                return true;
            }
            else if(TotalNotificacionesBaseDatos < TotalNotificacionesLocal){
                updateNotificaciones();
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocalDataController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void updateNotificaciones(){
        deleteAllNotificaciones();
        
        List<Object[]> notificaciones = Conection.CreateEntityManager()
                .createEntityManager()
                .createNativeQuery("SELECT * FROM Notificacion")
                .getResultList();
        
        notificaciones.forEach(notificacion -> {
            try {
                PreparedStatement ps = localConection.getconec().prepareStatement("INSERT INTO Notificacion VALUES(?)");
                ps.setInt(1, (int) notificacion[0]);
                ps.execute();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(LocalDataController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
    
    private void deleteAllNotificaciones(){
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("DELETE FROM Notificacion");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.err.println("Error: No se pudieron eliminar las notificaciones. Message: "+ex.getMessage());
        }
    }
}
