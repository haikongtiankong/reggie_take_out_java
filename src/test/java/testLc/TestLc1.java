package testLc;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TestLc1 {
    @Test
    void test01() {
        Queue<Integer> queueX = new ArrayDeque<>();
        queueX.clear();
        HashMap<Integer,Integer> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put(1,1);
        objectObjectHashMap.put(2,3);
        Collection<Integer> values = objectObjectHashMap.values();
        for (Integer v: values) {
            System.out.println(v);
        }
        Hashtable<String, Set<String>> hashtable = new Hashtable<>();
        hashtable.put("Bob", new HashSet<>());
        hashtable.put("Icy", new HashSet<>());
        hashtable.get("Bob").add("Bo");
        for (String s: hashtable.keySet()) {
            System.out.println(s);
        }

        System.out.println(hashtable.get("Bob").contains("Bo"));



    }


}
