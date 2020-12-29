package org.review.streamapi;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author lijichen
 * @date 2020/8/8 - 14:36
 */
public class StreamAPITest {
    public static void main(String[] args) {
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("ge"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("zed"));
        arr.add(new Persion("we"));
        //1,通过集合获取Stream
        Stream<Persion> stream = arr.stream();
        //2,通过数组获取Stream
        IntStream stream1 = Arrays.stream(new int[4]);
        Persion[] persions = {new Persion("33"),new Persion("555")};
        Stream<Persion> stream2 = Arrays.stream(persions);
        //3,Stream的of
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 45, 65, 5);
        //4，创建无限流
        Stream.iterate(0, new UnaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return integer += 2;
            }
        }).limit(10).forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        //引用方式
        Stream.iterate(0,i -> i + 2).limit(10).forEach(System.out::println);
        //
        Stream.generate(new Supplier<Object>() {
            @Override
            public Object get() {
                return Math.random();
            }
        }).limit(10).forEach(System.out::println);
        //rambda
        Stream.generate(() -> new Persion("W").getName()).limit(10).forEach(System.out::println);
        //引用
        Persion persion = new Persion("ddddd");
        Stream.generate(persion::getName).limit(10).forEach(System.out::println);
        System.out.println("*******************************");

    }
}
class Test2{
    public static void main(String[] args) {
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("ge"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("zed"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        //筛选
        Stream<Persion> stream = arr.stream();
        stream.filter(p -> p.getName().equals("we")).forEach(System.out::println);

        System.out.println();
        //截断流
        arr.stream().limit(2).forEach(System.out::println);
        System.out.println();
        //跳过元素
        arr.stream().skip(3).forEach(System.out::println);
        System.out.println();
        //筛选,需要重写hashCode和equals方法
        arr.stream().distinct().forEach(System.out::println);
        System.out.println(arr);
        System.out.println(arr);
    }
}
class Test3{
    public static void main(String[] args) {
        //映射
        List<String> stringList = Arrays.asList("aa", "bb", "cc", "dd");
        stringList.stream().map(str -> str.toUpperCase()).forEach(System.out::println);
        System.out.println();
        //
        Stream<Stream<Character>> streamStream = stringList.stream().map(Test3::forStringToStream);//.forEach(System.out::println);
        //streamStream.for
        streamStream.forEach(s ->{//这个foreach是外部的遍历
            s.forEach(System.out::print);//这个foreach是Stream内部的遍历
            System.out.println();//每次遍历完内部的一个Stream就换行
        });
        System.out.println();
        //
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("gdde"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("zed"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        //2,
        arr.stream().map(Persion::getName).filter(str -> str.length() > 3).forEach(System.out::println);
        System.out.println();
        //3,自动将其中的流转换
        stringList.stream().flatMap(Test3::forStringToStream).forEach(System.out::println);
    }
    static Stream<Character> forStringToStream(String str){
        ArrayList<Character> arr = new ArrayList<>();
        for (char c : str.toCharArray()) {
            arr.add(c);
        }
        return arr.stream();
    }
}
/*
排序
* */
class Test5{
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(-3, 43, 2, 24, 55, 0);
        integers.stream().sorted().forEach(System.out::println);
        System.out.println();
        //
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("gdde"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("azed"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        //重写
        arr.stream().sorted(new Comparator<Persion>() {
            @Override
            public int compare(Persion o1, Persion o2) {
                return o1.getName().compareTo(o2.getName());
            }
        }).forEach(System.out::println);
        System.out.println();
        //倒叙排序:lambda
        arr.stream().sorted((o1,o2) -> -o1.getName().compareTo(o2.getName())).forEach(System.out::println);
        System.out.println("**********************************");
        //
        arr.stream().sorted(Test5::getComparator).forEach(System.out::println);
        //arr.stream().sorted(Persion::compare).forEach(System.out::println);必须是静态的，不需要实现接口
    }
    static int getComparator(Persion o1,Persion o2){
        return o1.getName().compareTo(o2.getName());
    }
}
class Test6{
    //匹配与查找
    public static void main(String[] args) {
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("gdde"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("azeds"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        //字符串长度是否都大于等于2
        boolean b = arr.stream().allMatch(p -> p.getName().length() >= 2);
        System.out.println(b);
        //是否有字符串大于等于5的
        boolean b1 = arr.stream().anyMatch(p -> p.getName().length() >= 5);
        System.out.println(b1);
        //检查没有匹配到的元素,有为false，没有为true
        boolean a = arr.stream().noneMatch(p -> p.getName().startsWith("a"));
        System.out.println(a);
        //返回第一个元素
        Optional<Persion> first = arr.stream().findFirst();
        System.out.println(first);
        //返回任意一个
        Optional<Persion> any = arr.parallelStream().findAny();
        System.out.println(any);
        //返回流中的字符串大于2总个数
        long count = arr.stream().map(p -> p.getName().length() > 2).count();
        System.out.println(count);
        //返回流中最大的
        Optional<String> max = arr.stream().map(Persion::getName).max(String::compareTo);
        System.out.println(max);
        //返回流中最小的
        Optional<String> min = arr.stream().map(Persion::getName).min(String::compareTo);
        System.out.println(min);
        //按照名字进行内迭代
        arr.stream().map(Persion::getName).forEach(System.out::println);
    }
}
class Test7{
    //归约
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer reduce = integers.stream().reduce(0, new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println(reduce);
        System.out.println(integers.stream().reduce(0, Integer::sum));
        System.out.println("****************************");
        //
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("gdde"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("azeds"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));

        //返回所有对象name的长度length的累加数
        Optional<Integer> reduce1 = arr.stream().map(Persion::getName).map(String::length).reduce(new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) {
                return integer + integer2;
            }
        });
        System.out.println(reduce1);
        System.out.println(arr.stream().map(Persion::getName).map(String::length).reduce(Integer::sum));
        //返回所有名字拼接后的字符串
        Optional<String> reduce2 = arr.stream().map(Persion::getName).reduce((i1, i2) -> i1 + i2);
        System.out.println(reduce2);
    }
}
class Test8{
    //收集
    public static void main(String[] args) {
        List<Persion> arr = new ArrayList<>();
        arr.add(new Persion("wer"));
        arr.add(new Persion("fs"));
        arr.add(new Persion("ef"));
        arr.add(new Persion("zsxd"));
        arr.add(new Persion("gdde"));
        arr.add(new Persion("hr"));
        arr.add(new Persion("azeds"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));
        arr.add(new Persion("we"));

        /*
        * 收集name长度length大于2 的
        *返回为list，set，connlectors
        * */
        List<Persion> collect = arr.stream().filter(p -> p.getName().length() > 2).collect(Collectors.toList());
        System.out.println(collect);
        Set<Persion> collect1 = arr.stream().filter(p -> p.getName().length() > 2).collect(Collectors.toSet());
        System.out.println(collect1);
        /*arr.stream().filter(p -> p.getName().length() > 2).collect(Collectors.toCollection(new Supplier<Collection<? super Persion>>() {
            @Override
            public Collection<? super Persion> get() {
                return new List<Persion>();
            }
        }));*/
        //ArrayList
        ArrayList<Persion> collect2 = arr.stream().filter(p -> p.getName().length() > 2).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect2);
        //LinkedList
        LinkedList<Persion> collect3 = arr.stream().filter(p -> p.getName().length() > 2).collect(Collectors.toCollection(LinkedList::new));
        System.out.println(collect3);

        Collection<Persion> co = new ArrayList<>();
        co.add(new Persion("as"));
    }
}
class Persion implements Comparator<Persion>{
    private String name;

    public Persion() {
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Persion(String name) {
        this.name = name;
    }

    @Override
    public int compare(Persion o1, Persion o2) {
        return o1.getName().compareTo(o2.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persion persion = (Persion) o;
        return Objects.equals(name, persion.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}