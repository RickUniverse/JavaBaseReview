package org.review.generic;

import java.util.Arrays;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/2 - 19:03
 */
class GenericClassTest{
    public static void main(String[] args) {
        GenericClass generic = new GenericClass();
        generic.setGenericT(new Object());
        GenericClass<String> generic2 = new GenericClass();
        generic2.setGenericT("aaa");
//        Order<int> order = new Order();
        Order2<String> order2 = new Order2<String>();
        order2.setGenericT(new String());
        //泛型方法
        List list = generic.getList(new Integer[]{1, 2, 3, 4, 5});
        System.out.println(list);
        //可以直接调用泛型静态方法
        List<Son> list1 = AAA.getList(new Son[]{new Son(), new Son(), new Son(), new Son()});
        System.out.println(list1);
        //泛型方法
        //new AAA().getList();
    }
}
class AAA {
    public static  <T>List<T> getList(T[] arr){
        return Arrays.asList(arr);
    }
}
class BBB extends AAA {
    public static  <T>List<T> getList(T[] arr){
        return Arrays.asList(arr);
    }
}
class Son<E2, E3, R1> extends Order<String, E2, E3>{//保留两个E2,E3，自己增加一个R1
    public static <T>List<T> getList(T[] arr){
        return Arrays.asList(arr);
    }
}
class Order<E1, E2, E3> extends GenericClass<Integer> {//自己增加三个E1,E2,E3

}
class Order2<T> extends GenericClass<T>{

}
public class GenericClass<T> {
    private int age;
    private String name;
    T genericT;

    //泛型方法,可以是静态的
    public static <T>List<T> getList(T[] arr){
        return Arrays.asList(arr);
    }

    public GenericClass() {
        //给泛型创建数组
        T[] arr = (T[]) new Object[age];
    }

    public GenericClass(int age,String name,T genericT){
        this.age = age;
        this.name = name;
        this.genericT = genericT;
    }

    public T getGenericT(){
        return this.genericT;
    }
    public void setGenericT(T genericT){
        this.genericT = genericT;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "GenericClass{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", genericT=" + genericT +
                '}';
    }
}
