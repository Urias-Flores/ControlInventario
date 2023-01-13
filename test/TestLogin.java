
import Resource.Utilities;

public class TestLogin {
    public static void main(String[] args){
        int result = Utilities.IniciarSesion("Admi", "2020");
        System.out.println("Test = " + result);
    }
}
