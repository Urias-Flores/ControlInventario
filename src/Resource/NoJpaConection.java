package Resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoJpaConection {
    private static Connection conec;
    private static Statement stm;
    private String ip, port, user, password, driver, database;
    
    public Statement getStatement()
    {
        try{

            stm = conec.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

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
    
    public NoJpaConection()
    {
        if(conec == null)
        {
            ip = "127.0.0.1";
            port = "3306";
            user = "root";
            password = "alone2020";
            driver = "com.mysql.cj.jdbc.Driver";
            database = "inventario";
            try{
            Class.forName(driver);
            String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
            conec = DriverManager.getConnection(url, user , password);
            System.out.println("La conexion noJpa ha sido abierta");
            }catch(ClassNotFoundException | SQLException ex){
                System.out.print(ex.getMessage());
            }
        }
    }
    
    public Connection tryConexion(String ip, String port, String user, String password, String driver, String database)
    {
        Connection c = null;
        try{
            Class.forName(driver);
            String url = "jdbc:mysql://"+ip+":"+port+"/"+database;
            c = DriverManager.getConnection(url, user , password);
        }catch(ClassNotFoundException | SQLException ex){
            System.out.print(ex.getMessage());
        }
        return c;
    }
    
    public void closeConec()
    {
        try {
            conec.close();
            conec = null;
            System.out.println("La conexion NoJpa ha sido cerrada");
        } catch (SQLException ex) {
            System.out.print(ex.getMessage());
        }
    }
}
