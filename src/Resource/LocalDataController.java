package Resource;

import Controllers.NotificacionJpaController;
import Models.Notificacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.err.println("Error al actualizar data: "+ex.getMessage());
        }
    }
    
    public boolean checkCambios(){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT COUNT(*) FROM Notificacion");
            rs.next();
            int TotalNotificacionesLocal  = rs.getInt(1);
            
            List<Notificacion> notificaciones = new NotificacionJpaController(Conection.createEntityManagerFactory())
                    .findNotificacionEntities();
            int TotalNotificacionesBaseDatos = notificaciones.size();
            
            if(TotalNotificacionesLocal < TotalNotificacionesBaseDatos){
                return true;
            }
            else if(TotalNotificacionesBaseDatos < TotalNotificacionesLocal){
                updateNotificaciones();
            }
        } catch (SQLException ex) {
            System.err.println("Error al chequear cambios: "+ex.getMessage());
        }
        return false;
    }
    
    public void updateNotificaciones(){
        deleteAllNotificaciones();
        
        List<Object[]> notificaciones = Conection.createEntityManagerFactory()
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
                System.err.println("Error al actualizar notificaciones: "+ex.getMessage());
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
    
    public boolean validateInitDay(){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT * FROM Arqueo WHERE SaldoFinal = 0");
            if(rs.next()){
                System.err.println(rs.getString("SaldoFinal"));
                float saldoFinal  = Float.parseFloat(rs.getString("SaldoFinal"));
                if(saldoFinal == 0){
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
        return false;
    }
    
    public float getInitSaldo(){
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT * FROM Arqueo WHERE SaldoFinal = 0");
            if(rs.next()){
                return Float.parseFloat(rs.getString("SaldoInicial"));
            }
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
        return 0f;
    }
    
    public void initDay(int UsuarioID, Float SaldoInicial){
        try {
            PreparedStatement ps = localConection.getconec()
                    .prepareStatement("INSERT INTO Arqueo (UsuarioID, Fecha, Hora, SaldoInicial, SaldoFinal) VALUES (?, ?, ?, ?, 0)");
            ps.setInt(1, UsuarioID);
            
            String dia = String.valueOf(Utilities.getDate().getDate());
            String mes = String.valueOf(Utilities.getDate().getMonth() + 1);
            String anio = String.valueOf(Utilities.getDate().getYear() + 1900);
            
            String hora = String.valueOf(Utilities.getTime().getHours());
            String minuto = String.valueOf(Utilities.getTime().getMinutes());
            String segundos = String.valueOf(Utilities.getTime().getSeconds());
            
            ps.setString(2, anio+"-"+mes+"-"+dia);
            ps.setString(3, hora+":"+minuto+":"+segundos);
            ps.setFloat(4, SaldoInicial);
            
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
    }
    
    public void insertArqueoDetalle(int FacturaID, float TotalFactura, float TotalEfectivo, float TotalCambio, String Tipo){
        try {
            PreparedStatement ps = localConection.getconec()
                    .prepareStatement("INSERT INTO ArqueoDetalle VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, FacturaID);
            ps.setFloat(2, TotalFactura);
            ps.setFloat(3, TotalEfectivo);
            ps.setFloat(4, TotalCambio);
            ps.setString(5, Tipo);
            ps.execute();
            ps.close();
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
    }
    
    public ArrayList<Object[]> getArqueos(){
        ArrayList<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = localConection.getStatement().executeQuery("SELECT * FROM ArqueoDetalle");
            while(rs.next()){
                Object[] row = {
                    rs.getInt("FacturaID"),
                    rs.getFloat("Total"),
                    rs.getFloat("Efectivo"),
                    rs.getFloat("Cambio")
                };
                
                list.add(row);
            }
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
        return list;
    }
    
    public void closeDay(float SaldoFinal){
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("UPDATE Arqueo SET SaldoFinal = ? WHERE SaldoFinal = 0;");
            ps.setFloat(1, SaldoFinal);
            
            ps.executeUpdate();
            ps.close();
            
            clearArqueoDetalle();
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
    }
    
    public void clearArqueoDetalle(){
        try {
            PreparedStatement ps = localConection.getconec().prepareStatement("DELETE FROM ArqueoDetalle");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            System.err.print("Error code: "+ex.getErrorCode()+" Error: "+ex.getMessage());
        }
    }
}
