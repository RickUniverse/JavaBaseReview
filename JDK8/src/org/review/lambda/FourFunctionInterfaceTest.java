package org.review.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author lijichen
 * @date 2020/8/7 - 19:00
 */
public class FourFunctionInterfaceTest {
    public static void main(String[] args) {
        consumerMethor(123, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println(aDouble);
            }
        });
        System.out.println("***************************");
        consumerMethor(444,money -> System.out.println(money));
        System.out.println("***************************");
        //判断型接口
        List<String> list = Arrays.asList("asdf", "asdf", "sdf", "ggg", "sdsf");
        List<String> g = predicateMethor(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("g");
            }
        });
        System.out.println(g);
        List<String> a = predicateMethor(list, s -> s.contains("a"));//调用s.contains("a")
        System.out.println(a);
    }
    //消费型接口
    static void consumerMethor(double money, Consumer<Double> consumer){
        consumer.accept(money);
    }
    //断定型接口
    static List<String> predicateMethor(List<String> aLi, Predicate<String> predicatee){
        List<String> list = new ArrayList();
        if (aLi != null){
            for (String s : aLi) {
                if (predicatee.test(s)){//方法的调用，重写方法之后，s这个变量就进入重写后的方法，调用s.contains("a")
                    list.add(s);
                }
            }
        }
        return list;
    }
}
