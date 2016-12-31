package hacker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class StringsMakingAnagrams {
	
    public static int numberNeeded(String first, String second) {
        Map<String, Integer> m1 = getCharMap(first);  
        Map<String, Integer> m2 = getCharMap(second);
        Set<String> m1Keys = m1.keySet();
        Set<String> m2Keys = m2.keySet();
        
        Set<String> intersection = new HashSet<String>(m1Keys); 
        intersection.retainAll(m2Keys);
        
        Set<String> m1Only = new HashSet<String>(m1Keys);
        m1Only.removeAll(m2Keys);
        
        Set<String> m2Only = new HashSet<String>(m2Keys);
        m2Only.removeAll(m1Keys);
        
        int numNeeded = 0;
        Iterator<String> itInter = intersection.iterator();
        while(itInter.hasNext()) {
            String currKey = itInter.next();
            numNeeded = numNeeded + Math.abs(m1.get(currKey) - m2.get(currKey)); 
        }
        Iterator<String> itM1 = m1Only.iterator();
        while(itM1.hasNext()) {
            String currKey = itM1.next();
            numNeeded = numNeeded + m1.get(currKey); 
        }
        Iterator<String> itM2 = m2Only.iterator();
        while(itM2.hasNext()) {
            String currKey = itM2.next();
            numNeeded = numNeeded + m2.get(currKey); 
        }
        return numNeeded;         
    }
    
    public static Map<String, Integer> getCharMap(String s) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        char[] chars = s.toCharArray();
        for(int i = 0; i < chars.length; i++) {
            String currKey = new Character(chars[i]).toString();
            if(map.containsKey(currKey)) {
                int count = map.get(currKey);
                count++;
                map.put(currKey, count);
            } else {
                map.put(currKey, 1);
            }
        }
        return map;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String a = in.next();
        String b = in.next();
        in.close();
        System.out.println(numberNeeded(a, b));
    }
}

