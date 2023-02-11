
import Resource.Code;
import Resource.Utilities;


public class TestCifrado {
    
    
    public static void main(String[] args){
        Code code = new Code();
        
//        String result = code.codeString("|°!#$%&/()=?¡*[Ñ_:;.-{ñ´+");
//        System.out.println("Test cifrado: "+result);
//        String result2 = code.decodeString(result);
//        System.out.println("Test decifrado: "+result2);
//        System.out.println("Test: "+UIManager.getSystemLookAndFeelClassName());
//        System.out.println("Test: "+UIManager.getInstalledLookAndFeels()[0].getClassName());
//        System.out.println("Test: "+UIManager.getInstalledLookAndFeels()[1].getClassName());
//        System.out.println("Test: "+UIManager.getInstalledLookAndFeels()[2].getClassName());
//        System.out.println("Test: "+UIManager.getInstalledLookAndFeels()[3].getClassName());
//        System.out.println("Test: "+UIManager.getInstalledLookAndFeels()[4].getClassName());
        System.out.println(Utilities.getDate().getDate());
        System.out.println(Utilities.getDate().getMonth() + 1);
        System.out.println(1900 + Utilities.getDate().getYear());
        
        System.out.println("\n"+Utilities.getTime().getHours());
        System.out.println(Utilities.getTime().getMinutes());
        System.out.println(1900 + Utilities.getTime().getSeconds());
    }
}
