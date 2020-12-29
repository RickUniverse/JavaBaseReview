package org.review.generic;

import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/3 - 19:04
 */
public class DAOS<T> {
    private Map<String,T> map = new HashMap<>();

    public List<T> getList(){
        List<T> list = new ArrayList<>();
        Collection<T> values = map.values();
        for (T value : values) {
            list.add(value);
        }
        return list;
    }
    public T remove(String id){
        if (map.size() > 0){
            return map.remove(id);
        }
        throw new RuntimeException("没有数据！");
    }
}
