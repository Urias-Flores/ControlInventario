package Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocalConection {
    private static Connection conec;
    private static Statement stm;
    
    public Statement getStatement()
    {
        try{
            stm = conec.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException ex)
                {
                   System.out.print(ex.getMessage());
                }
        return stm;
    }
    
    public Connection getconec()
    {
        return conec;
    }
    
    public LocalConection()
    {
        if(conec == null)
        {
            try{
            String url = "jdbc:sqlite:config.db";
            conec = DriverManager.getConnection(url);
            }catch(SQLException ex){
                System.out.print(ex.getMessage());
            }
        }
    }
    
    public void closeConection(){
        try {
            conec.close();
            conec = null;
        } catch (SQLException ex) {
            Logger.getLogger(LocalConection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
