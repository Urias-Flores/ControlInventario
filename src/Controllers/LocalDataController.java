package Controllers;

import Models.Notificacion;
import Resource.Conection;
import Resource.LocalConection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.persistence.Query;

public class LocalDataController {
    
    LocalConection localConection = new LocalConection();
    
    public String getValue(String Data){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT Value FROM data WHERE Data = '"+Data+"'");
            if(rs.next()){
                return rs.getString(1);
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
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
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    private void cargarNotificaciones(){
        List<Notificacion> notificaciones = new NotificacionJpaController(Conection.CreateEntityManager()).findNotificacionEntities();
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("INSERT INTO Notificacion values(?)");
            for(Notificacion notificacion : notificaciones){
                ps.setInt(1, notificacion.getNotificacionID());
                ps.execute();
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    private void eliminarNotificaciones(){
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("DELETE FROM Notificacion");
            ps.execute();
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
    }
    
    public void updateNotificaciones(){
        eliminarNotificaciones();
        cargarNotificaciones();
    }
    
    public boolean checkCambios() {
        boolean hayNotificaiones = false;
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT COUNT(*) FROM Notificacion");
            rs.next();
            int notificacionesLocal = rs.getInt(1);
            
            Query query = Conection.CreateEntityManager().createEntityManager().createNativeQuery("SELECT COUNT(*) FROM Notificacion");
            int notificacionesBD = Integer.parseInt(query.getSingleResult().toString());
            
            if(notificacionesLocal < notificacionesBD){
                System.out.println("Test: Notitificaciones en info. local -> "+notificacionesLocal+" Notificaciones en bd -> "+notificacionesBD);
                hayNotificaiones = true;
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
        }
        return hayNotificaiones;
    }
}
