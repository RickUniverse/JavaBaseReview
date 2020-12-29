package org.review.lambda;

import java.util.Comparator;

/**
 * @author lijichen
 * @date 2020/8/7 - 17:48
 */
public class LambdaTest {
    public static void main(String[] args) {
        //lambda
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("sss");
            }
        };
        runnable.run();
        System.out.println("*********************");
        Runnable runnable2 = () -> System.out.println("sss");
        System.out.println("*********************");
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        System.out.println(com1.compare(123, 44));
        System.out.println("*********************");
        //lambda表达式写法
        Comparator<Integer> com2 = (o1,o2) -> Integer.compare(o1,o2);
        System.out.println(com2.compare(123, 44));
        System.out.println("*********************");
        //方法引用
        Comparator<Integer> com3 = Integer :: compareTo;
        System.out.println(com3.compare(12,55));
    }
}
