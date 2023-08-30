package LAS_searcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LT {
    
    public char[] llegirLiniaC() {
        String res = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
            res = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toCharArray();
    }

    public Integer llegirSencer() {
        Integer res = 0;
        try {
            char[] pal = llegirLiniaC();
            res = Integer.valueOf(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public Double llegirReal() {
        Double res;
        try {
            char[] pal = llegirLiniaC();
            res = Double.valueOf(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public String llegirLiniaS() {
        String res;
            char[] pal = llegirLiniaC();
            res = new String(pal);
        return res;
    }

    public Character llegirCaracter() {
        Character res;
            char[] pal = llegirLiniaC();
            String s = new String(pal);
            if (s.length()==0) {
                res = null;
            } else {
                res = s.charAt(0);
            }
        return res;
    }
}

