
package Resource;

import java.util.HashMap;
import java.util.Map;

public class Code {
    
    
    public String codeString(String valor)
    {
        
        Map<String, String> dic = new HashMap<>();
        //minusculas
        dic.put("a", "45tI");
        dic.put("b", "v6yg");
        dic.put("c", "7rtr");
        dic.put("d", "95dI");
        dic.put("e", "h5t1");
        dic.put("f", "4t6I");
        dic.put("g", "1jxI");
        dic.put("h", "45wI");
        dic.put("i", "h5tk");
        dic.put("j", "6ztb");
        dic.put("k", "s5vI");
        dic.put("l", "t5t6");
        dic.put("m", "10gh");
        dic.put("n", "yf6t");
        dic.put("ñ", "gt45");
        dic.put("o", "jh7d");
        dic.put("p", "re23");
        dic.put("q", "d4t8");
        dic.put("r", "zs90");
        dic.put("s", "d5j7");
        dic.put("t", "m0d2");
        dic.put("u", "m03s");
        dic.put("v", "v67w");
        dic.put("w", "yh34");
        dic.put("x", "asd2");
        dic.put("y", "ind2");
        dic.put("z", "fj5x");
        //mayusculas
        dic.put("A", "jd91");
        dic.put("B", "8h3b");
        dic.put("C", "jn2s");
        dic.put("D", "plm2");
        dic.put("E", "n8wg");
        dic.put("F", "lba3");
        dic.put("G", "vcb2");
        dic.put("H", "dtñ1");
        dic.put("I", "ax58");
        dic.put("J", "v23f");
        dic.put("K", "vag3");
        dic.put("L", "kchd");
        dic.put("M", "che8");
        dic.put("N", "ks9e");
        dic.put("Ñ", "x61n");
        dic.put("O", "plm3");
        dic.put("P", "gc8w");
        dic.put("Q", "mb93");
        dic.put("R", "anc8");
        dic.put("S", "10mr");
        dic.put("T", "s9cm");
        dic.put("U", "19xj");
        dic.put("V", "x8je");
        dic.put("W", "c01f");
        dic.put("X", "n83c");
        dic.put("Y", "c62g");
        dic.put("Z", "78mf");
        //Numeros
        dic.put("1", "la94");
        dic.put("2", "31fg");
        dic.put("3", "90ch");
        dic.put("4", "72jc");
        dic.put("5", "dh81");
        dic.put("6", "h7mm");
        dic.put("7", "hc9e");
        dic.put("8", "9kwm");
        dic.put("9", "8chs");
        dic.put("0", "hj32");
        //Simbolos
        dic.put("*", "j32l");
        dic.put("#", "32hg");
        dic.put("(", "dj89");
        dic.put(")", "hae2");
        dic.put("{", "gjk2");
        dic.put("}", "kl1n");
        dic.put(".", "90j2");
        dic.put(",", "la1x");
        dic.put(";", "9xdm");
        dic.put(":", "klop");
        dic.put("\"", "9082");
        dic.put("'", "jj33");
        dic.put("<", "h24m");
        dic.put(">", "xj3w");
        dic.put("?", "23z2");
        dic.put("!", "xx7l");
        dic.put("¿", "mlx1");
        dic.put("¡", "jkex");
        dic.put("=", "lpd6");
        dic.put("+", "kop4");
        dic.put("-", "ev52");
        dic.put("0", "zxm7");
        dic.put("&", "jlh2");
        dic.put("^", "ll5w");
        dic.put("%", "sjp1");
        dic.put("$", "hf7w");
        dic.put("@", "tj3r");
        dic.put("~", "wir0");
        dic.put("`", "klpx");
        dic.put("[", "op20");
        dic.put("]", "hb4d");
        dic.put("\\", "mkle");
        dic.put("/", "mcx5");
        dic.put("_", "mvx6");
        dic.put("|", "mskl");
        dic.put("°", "op09");
        dic.put("´", "cf41");
        
        String[] valor1 = valor.split("", valor.length());
        valor = "";
        for(int i = 0; i < valor1.length; i++)
        {
            valor1[i] = dic.get(valor1[i]);
            valor = valor + valor1[i];
        }
        return valor;
    }
    
    public String decodeString(String valor)
    {
        Map<String, String> dic = new HashMap<>();
        //minusculas
        dic.put("45tI","a");
        dic.put("v6yg","b");
        dic.put("7rtr","c");
        dic.put("95dI","d");
        dic.put("h5t1","e");
        dic.put("4t6I","f");
        dic.put("1jxI","g");
        dic.put("45wI","h");
        dic.put("h5tk","i");
        dic.put("6ztb","j");
        dic.put("s5vI","k");
        dic.put("t5t6","l");
        dic.put("10gh","m");
        dic.put("yf6t","n");
        dic.put("gt45","ñ");
        dic.put("jh7d","o");
        dic.put("re23","p");
        dic.put("d4t8","q");
        dic.put("zs90","r");
        dic.put("d5j7","s");
        dic.put("m0d2","t");
        dic.put("m03s","u");
        dic.put("v67w","v");
        dic.put("yh34","w");
        dic.put("asd2","x");
        dic.put("ind2","y");
        dic.put("fj5x","z");
        //mayusculas
        dic.put("jd91","A");
        dic.put("8h3b","B");
        dic.put("jn2s","C");
        dic.put("plm2","D");
        dic.put("n8wg","E");
        dic.put("lba3","F");
        dic.put("vcb2","G");
        dic.put("dtñ1","H");
        dic.put("ax58","I");
        dic.put("v23f","J");
        dic.put("vag3","K");
        dic.put("kchd","L");
        dic.put("che8","M");
        dic.put("ks9e","N");
        dic.put("x61n","Ñ");
        dic.put("plm3","O");
        dic.put("gc8w","P");
        dic.put("mb93","Q");
        dic.put("anc8","R");
        dic.put("10mr","S");
        dic.put("s9cm","T");
        dic.put("19xj","U");
        dic.put("x8je","V");
        dic.put("c01f","W");
        dic.put("n83c","X");
        dic.put("c62g","Y");
        dic.put("78mf","Z");
        //Numeros
        dic.put("la94", "1");
        dic.put("31fg", "2");
        dic.put("90ch", "3");
        dic.put("72jc", "4");
        dic.put("dh81", "5");
        dic.put("h7mm", "6");
        dic.put("hc9e", "7");
        dic.put("9kwm", "8");
        dic.put("8chs", "9");
        dic.put("hj32", "0");
        String valor1 = valor;
        //Simbolos
        dic.put("j32l", "*");
        dic.put("32hg", "#");
        dic.put("dj89", "(");
        dic.put("hae2", ")");
        dic.put("gjk2", "{");
        dic.put("kl1n", "}");
        dic.put("90j2", ".");
        dic.put("la1x", ",");
        dic.put("9xdm", ";");
        dic.put("klop", ":");
        dic.put("9082", "\"");
        dic.put("jj33", "'");
        dic.put("h24m", "<");
        dic.put("xj3w", ">");
        dic.put("23z2", "?");
        dic.put("xx7l", "!");
        dic.put("mlx1", "¿");
        dic.put("jkex", "¡");
        dic.put("lpd6", "=");
        dic.put("kop4", "+");
        dic.put("ev52", "-");
        dic.put("zxm7", "0");
        dic.put("jlh2", "&");
        dic.put("ll5w", "^");
        dic.put("sjp1", "%");
        dic.put("hf7w", "$");
        dic.put("tj3r", "@");
        dic.put("wir0", "~");
        dic.put("klpx", "`");
        dic.put("op20", "[");
        dic.put("hb4d", "]");
        dic.put("mkle", "\\");
        dic.put("mcx5", "/");
        dic.put("mvx6", "_");
        dic.put("mskl", "|");
        dic.put("op09", "°");
        dic.put("cf41", "´");
        
        valor = "";
        for(int i = 0; i <valor1.length(); i = i + 4)
        {
            valor = valor + dic.get(valor1.substring(i, i + 4));
        }
        return valor;
    }
}
