package Resource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateDataController {
    
    private UpdateConection conec = new UpdateConection();
    
    public ArrayList<String[]> getValues() {
        ArrayList<String[]> updates = new ArrayList<>();
        try {
            ResultSet rs = conec.getStatement().executeQuery("SELECT * FROM updates");
            
            while(rs.next()){
                String[] row = {
                    rs.getString("Type"),
                    rs.getString("Name"),
                    rs.getString("Path")
                };
                updates.add(row);
            }
        } catch (SQLException ex) {
            System.err.println("Error: "+ex.getMessage());
            return null;
        }
        return updates;
    }
}
