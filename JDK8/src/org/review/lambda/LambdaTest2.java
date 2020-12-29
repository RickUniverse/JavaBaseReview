package org.review.lambda;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author lijichen
 * @date 2020/8/7 - 18:10
 */
public class LambdaTest2 {
    public static void main(String[] args) {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con.accept("Sdfasd");
        System.out.println("*************");
        Consumer<String> con1 = (String str) -> {
            System.out.println(str);
        };
        con1.accept("asdfasdf");
        System.out.println("*************");
        Consumer<String> con2 = (str) -> {
            System.out.println(str);
        };
        con1.accept("asdfasdf");
        //
        Comparator<String> com = (o1,o2) -> {
            return o1.compareTo(o2);
        };
        Comparator<String> com2 = (o1,o2) -> o1.compareTo(o2);
        //
        Consumer<String> con3 = str -> System.out.println(str);
    }
}
