package org.review.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author lijichen
 * @date 2020/8/7 - 19:48
 */
public class QuoteTest {
    public static void main(String[] args) {
        Consumer<String> con = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        //con.accept("sss");
        System.out.println("***********第一种引用情况************");
        PrintStream ps = System.out;
        Consumer<String> con2 =  ps :: println;
        con2.accept("fff");
        Persion per = new Persion("qqq");
        /*
        * /函数接口的方法的参数类型和返回值类型必须与getName的类型一致
        * 引用了per.getName方法体
        * 必须是一定确定的
        * */
        Supplier<String> sup = per :: getName;
        System.out.println(sup.get());
        System.out.println("***********第二种引用情况************");
        Comparator<String> com = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        Comparator<Integer> com2 = Integer::compare;
        int compare = com2.compare(3, 2);
        System.out.println(compare);
        System.out.println("2".compareTo("4"));
        System.out.println("***********第三种引用情况************");
        /*
        * Comparator中的int compare(T t1,T t2)
        * String 中的int t1.compareTo(t2)
        * 解释：Comparator中的方法的参数列表中T t1作为String类中的方法t1.compareTo(t2)的调用者
        * */
        Comparator<String> com4 = (o1,o2) -> o1.compareTo(o2);
        Comparator<String> com3 = String::compareTo;
        int compare3 = com3.compare("a2", "d4");//
        //比较两个字符串
        BiPredicate<String,String> bi = (s1,s2) -> s1.equals(s2);
        System.out.println(bi.test("qwe", "qwe"));
        BiPredicate<String,String> bi2 = String :: equals;
        System.out.println(bi2.test("qwe", "qwe"));
        //直接返回
        Persion p2 = new Persion("eeee");
        /*
        1，
        泛型<Persion,String>
        第一个泛型Persion为apply的参数
        第二个泛型String为返回值
         */
        Function<Persion,String> fu = new Function<Persion, String>() {
            @Override
            public String apply(Persion persion) {
                return persion.getName();
            }
        };
        System.out.println(fu.apply(p2));
        Function<Persion,String> fun = e -> e.getName();//
        System.out.println(fun.apply(p2));
        //
        /*
        *2，
        * 重写后的方法体是返回Persion的getName(),得到一个返回值为String类型的值
        * */
        Function<Persion,String> fun2 = Persion :: getName;//只是定义规则
        System.out.println(fun2.apply(p2));
    }
}
class Persion{
    private int age;

    public Persion(int age, String name) {
        this.age = age;
        this.name = name;
    }

    private String name;

    public Persion() {
    }

    public Persion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                '}';
    }
}