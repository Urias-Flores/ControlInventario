package Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalConection {
    private static Connection conec;
    private static Statement stm;
    
    public Statement getStatement()
    {
        try{
            stm = conec.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        }catch(SQLException ex)
                {
                   System.err.print(ex.getMessage());
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
            System.out.println("Test local information: Archivo de configuracion encontrado.");
            }catch(SQLException ex){
                System.err.print(ex.getMessage());
            }
        }
    }
    
    public void closeConection(){
        try {
            conec.close();
            conec = null;
            System.out.println("Local information: Archivo de configuracion local cerrado.");
        } catch (SQLException ex) {
            System.err.print(ex.getMessage());
        }
    }
}
