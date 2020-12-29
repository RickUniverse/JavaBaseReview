package org.jdk9.lists;

import org.review.jdk9.Persion;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijichen
 * @date 2020/8/9 - 16:04
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> integers = List.of(1, 2, 3, 4, 5);//创建只读集合
        System.out.println(integers);
//        integers.add(333);
        Map<String, Integer> sdf = Map.of("sdf", 12, "asdf", 234);//创建只读集合
        //Set<Integer> integers1 = Set.of(4, 5, 6, 2, 23, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4);
        //System.out.println(integers1);
    }
}
