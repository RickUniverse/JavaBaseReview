package org.review.connections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/1 - 15:53
 */
public class ArrayTest {
    public static void main(String[] args) {
        List coll4 = new ArrayList();
        coll4.add(123);//顺序相同
        coll4.add(456);//顺序相同
        coll4.add(true);
        coll4.add(new String("aaa"));
        coll4.add(new Parens("lisi",12));
        coll4.add(123);
        System.out.println("********************");
        System.out.println(coll4.indexOf(456));
        System.out.println("********************");
        coll4.addAll(1,Arrays.asList("asdf","fffff"));
        System.out.println(coll4);
        System.out.println("********************");
        System.out.println(coll4.get(4));
        System.out.println("********************");
        System.out.println(coll4.lastIndexOf(123));
        System.out.println("********************");
        coll4.removeAll(Arrays.asList("asdf","fffff"));
        System.out.println(coll4);
        System.out.println("********************");
        System.out.println(coll4.set(0, 333333333));
        System.out.println("********************");
        System.out.println(coll4.subList(1, 4));
        coll4.remove(new Integer(456));
    }
}
