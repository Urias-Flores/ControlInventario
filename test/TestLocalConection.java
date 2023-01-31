
import Resource.LocalDataController;
import Resource.LocalConection;

public class TestLocalConection {
    public static void main(String[] args){
        LocalConection conec = new LocalConection();
        
        System.out.println("Test conec: "+conec.toString());
        
        LocalDataController ldc = new LocalDataController();
//        if(ldc.UpdateData("User", "root") == -1){
//            ldc.CreateData("User", "root");
//        }
        
        System.err.println("Test get: "+ldc.getValue("RemenberState"));
    }
}
