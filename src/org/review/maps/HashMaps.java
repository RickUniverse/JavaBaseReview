package org.review.maps;

import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/2 - 14:42
 */
public class HashMaps {
    public static void main(String[] args) {
        Map map = new HashMap<>();
        map.put("a",123);
        map.put("4","f");
        map.put(11,"s");
        map.put(true,123);
        System.out.println("************");
        Map map2 = new HashMap<>();
        map2.put("a",123);
        map2.put("4","f");
        map2.putAll(map2);
        System.out.println(map);
        System.out.println("************");
        System.out.println(map.remove(11));
        System.out.println("************");
        //System.out.println(map.clear());
        System.out.println(map.size());
        System.out.println("************");
        System.out.println(map.get("4"));
        System.out.println(map.isEmpty());
        System.out.println(map.containsKey("4"));
        System.out.println("*****遍历******");
        System.out.println(map);
        Set set = map.keySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object key = iterator.next();
            Object value = map.get(key);

            System.out.println(key + "---" + value);
        }
        System.out.println("*****遍历******");
        Collection set1 = map.entrySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator1.next();
            System.out.println(entry.getKey()+"-------"+entry.getValue());
        }
    }
}
