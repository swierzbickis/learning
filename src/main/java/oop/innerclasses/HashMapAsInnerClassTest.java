package oop.innerclasses;

import java.util.HashMap;
import java.util.Map;

public class HashMapAsInnerClassTest {

    public static void testHashMap() {
        Map<String, Integer> dayInMonths = new HashMap<>();
        dayInMonths.put("styczen", 31);
        dayInMonths.put("luty", 28);
        dayInMonths.put("marzec", 31);

        for (Map.Entry<String, Integer> entry : dayInMonths.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: " + entry.getValue());
        }

        System.out.println("Key Set : ");
        for (String key : dayInMonths.keySet()) {
            System.out.println(key);
            //System.out.println("Key: " + key + " Value: " + dayInMonths.get(key)); //getting key and value from key set
        }

        System.out.println("Value Set : ");
        for (Integer value : dayInMonths.values()) {
            System.out.println("Value: " + value);
        }

    }

}
