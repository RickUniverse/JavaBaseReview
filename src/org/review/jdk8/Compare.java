package org.review.jdk8;

public interface Compare {
    abstract void method1();
    final int MAX = 10;
    static void method2(){
        System.out.println("compare:static");
    }
    default void method3(){
        System.out.println("compare:default");
    }

}
interface Compare2 {
    default void method3(){
        System.out.println("compare:default");
    }

}
class SuperClass{
    public void method3(){
        System.out.println("superclass:public");
    }
}
class Test{
    public static void main(String[] args) {
        //调用接口中的静态方法
        Compare.method2();
        //实现类中方法调用接口中的默认方法
        new SubClass().method1();
        //调用实现类中的方法3
        new SubClass().method3();
    }
}
class SubClass extends SuperClass implements Compare,Compare2{

    @Override
    public void method1() {
        Compare.super.method3();
    }

    /*public void method3(){
        System.out.println("subclass:public:method3");
    }*/
}