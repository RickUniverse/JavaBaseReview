package org.design.principle.segregation;

//接口隔离原则
public interface interface1 {
    void method1();
}

interface interface2 {
    void method2();

    void method3();

}

interface interface3 {
    void method4();

    void method5();
}

class B { //B类通过interface1，interface2依赖于 A类，但是只会用到1,2,3方法
    public void depend1(interface1 inte) {
        inte.method1();
    }

    public void depend2(interface2 inte) {
        inte.method2();
    }

    public void depend3(interface2 inte) {
        inte.method3();
    }
}

class A implements interface1, interface2 {

    @Override
    public void method1() {
        System.out.println("类A");
    }

    @Override
    public void method2() {
        System.out.println("类A");

    }

    @Override
    public void method3() {
        System.out.println("类A");
    }
}

class C implements interface1, interface3 {

    @Override
    public void method1() {
        System.out.println("类C");
    }

    @Override
    public void method4() {
        System.out.println("类C");
    }

    @Override
    public void method5() {
        System.out.println("类C");
    }
}

class D {//D通过接口interface1，interface3 依赖C类，但是只会用1,4，5方法

    public void depend1(interface1 inte) {
        inte.method1();
    }

    public void depend2(interface3 inte) {
        inte.method4();
    }

    public void depend3(interface3 inte) {
        inte.method5();
    }
}

class Test {
    public static void main(String[] args) {
        B b = new B();
        b.depend1(new A());//B通过接口去依赖A类
        b.depend2(new A());
        b.depend3(new A());
        D d = new D();
        d.depend1(new C());
        d.depend2(new C());
        d.depend3(new C());
    }
}