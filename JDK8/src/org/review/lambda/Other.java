package org.review.lambda;

import sun.security.util.Length;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lijichen
 * @date 2020/8/7 - 21:23
 */
public class Other {
    public static void main(String[] args) {
        /*
        * 构造器引用
        *
        * */
        Supplier<Persion> su = new Supplier<Persion>() {
            @Override
            public Persion get() {
                return new Persion();
            }
        };
        Supplier<Persion> su2 = () -> new Persion();//lambda
        Persion persion = su2.get();
        System.out.println(persion);
        Supplier<Persion> su3 = Persion::new;//引用
        System.out.println(su3.get());
        //
        Function<String,Persion> fu = new Function<String, Persion>() {
            @Override
            public Persion apply(String s) {
                return new Persion(s);
            }
        };
        Function<String,Persion> fu2 = name -> new Persion(name);//lambda
        Persion persion1 = fu2.apply("22");
        System.out.println(persion1);
        Function<String,Persion> fu3 = Persion::new;//引用
        System.out.println(fu3.apply("fff"));
        //
        BiFunction<Integer,String,Persion> bif = new BiFunction<Integer, String, Persion>() {
            @Override
            public Persion apply(Integer integer, String s) {
                return new Persion(integer,s);
            }
        };
        System.out.println(bif.apply(123, "fffff"));
        BiFunction<Integer,String,Persion> bif2 = (age,name) -> new Persion(age,name);//lambda
        BiFunction<Integer,String,Persion> bif3 = Persion::new;//引用
        Persion fgar = bif3.apply(555, "fgar");
        System.out.println(fgar);
        /*
        * 数组引用
        * */
        Function<Integer,String[]> func = new Function<Integer, String[]>() {
            @Override
            public String[] apply(Integer integer) {
                return new String[integer];
            }
        };
        String[] apply = func.apply(33);
        System.out.println(apply);
        Function<Integer,String[]> func2 = length -> new String[length];//lambda
        System.out.println(Arrays.toString(func2.apply(3)));
        Function<Integer,String[]> func3 = String[] :: new;//引用
        System.out.println(Arrays.toString(func3.apply(33)));
    }
}
