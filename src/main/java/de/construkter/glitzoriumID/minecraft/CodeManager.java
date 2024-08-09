package de.construkter.glitzoriumID.minecraft;

import java.util.HashMap;
import java.util.Map;

public class CodeManager {
    private static Map<Integer, Integer> idToNumber = new HashMap<>();
    private static int id = 0;
    public static int[] gen(){
        int[] code = new int[2];
        code[0] = (int)(Math.random()*10000);
        code[1] = id++;
        idToNumber.put(code[1], code[0]);
        return code;
    }

    public static int get(int id){
        return idToNumber.get(id);
    }
}
