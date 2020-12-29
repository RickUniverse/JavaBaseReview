package org.design.principle.liskov;

/**
 * @author lijichen
 * @date 2020/8/11 - 14:42
 */
//里氏替换原则
public class LiskovTest {
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.fun1(10, 5));
        B b = new B();
        System.out.println(b.fun2(10,5));
        System.out.println(b.fun(10, 5));
        System.out.println(b.fun1(10, 5));
    }
}
class Base{
    public int fun(int a,int b){
        return a * b;//a * b
    }
}
class A extends Base{
    public int fun1(int a,int b){
        return a - b;// a - b
    }

}
class B extends Base{
    //使用组合实现里氏替换原则,组合会实例化
    private A aF = new A();

    public int fun1(int a,int b){
        return a + b;//返回a + b
    }

    public int fun2(int a,int b){
        return aF.fun1(a,b);//返回a - b
    }
    public int fun3(int a,int b){
        return new A().fun1(a,b);//返回a - b
    }

}

