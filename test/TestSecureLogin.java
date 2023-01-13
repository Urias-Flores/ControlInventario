
import Resource.Security;

public class TestSecureLogin {
    
    public static void main(String[] args){
        String hash = Security.generateStrongPasswordHash("alone2020");
        int length = hash.length();
        boolean result = Security.validatePassword("alone2020", hash);
        
        System.out.println("Test: "+result+" Longitud de hash: "+length+" Hash: "+hash);
    }
    
}
