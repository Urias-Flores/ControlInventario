public class TestRadom {
    
    public static void main(String[] args){
        for(int i = 0; i < 100000; i++){
            double randomValue = 100000 + Math.random() * 999999;
            System.err.println("Test: "+String.valueOf(randomValue).substring(0, 6));
        }
    }
}
