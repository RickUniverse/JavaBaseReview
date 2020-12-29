package org.juc.fourfunction;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lijichen
 * @date 2020/9/27 - 18:17
 */
public class FunctionTest {
    public static void main(String[] args) {
        Persion persion = new Persion(1,"asdf",123.33);
        //消费型接口
        //匿名实现
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("asdfwer");
        //lamdba表达式
        Consumer<String> function = str -> {
            System.out.println(str);
        };
        function.accept("dfasdf");
        //方法引用
        Consumer<String> quote = persion::consumer;
        quote.accept("asdf");

         //供给型接口
        //lamdba表达式
        Supplier<Persion> supplier = () -> {return persion;};
        //方法引用
        Supplier<Persion> quoteSupplier = persion::supplier;
        System.out.println(quoteSupplier.get());

        //函数型接口
        //lamdba表达式
        Function<String,Integer> function1 = (str) -> {return str.length();};
        //方法引用
        Function<String,Integer> quoteFunction = persion::function;
        quoteFunction.apply(persion.getName());

        //判定/boolean型接口
        //lamdba表达式
        Predicate<Persion> predicate = (persion1) -> {return persion1!=null;};
        predicate.test(persion);
        //方法引用
        Predicate<Persion> quotePredicate = persion::predicate;
        System.out.println(quotePredicate.test(persion));

        //方法引用
//        List<String> list = Arrays.asList("a");
//        list.forEach(System.out::println);
    }
}


class Persion {
    private int id;
    private String name;
    private Double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Persion() {
    }

    public Persion(int id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void consumer(String str) {
        System.out.println(str);
    }

    public Persion supplier() {
        return this;
    }

    public Integer function(String str) {
        return str.length();
    }

    public boolean predicate(Persion persion) {
        return persion!=null;
    }
}
