package org.review.synchronizeds;

/**
 * @author lijichen
 * @date 2020/7/27 - 15:09
 */
public class A {
    public synchronized void foo(B b){
        System.out.println("当前线程：" + Thread.currentThread().getName() + "进入了classA，foo");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程：" + Thread.currentThread().getName() + "企图调用classB，last方法");
        b.last();
    }
    public synchronized void last(){
        System.out.println("进入了classA，last方法");
    }
}
class B {
    public synchronized void foo(A a){
        System.out.println("当前线程：" + Thread.currentThread().getName() + "进入了classB，foo");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程：" + Thread.currentThread().getName() + "企图调用classA，last方法");
        a.last();
    }
    public synchronized void last(){
        System.out.println("进入了classB，last方法");
    }
}
class DeadLock implements Runnable{
    A a = new A();
    B b = new B();


    public void init(){
        Thread.currentThread().setName("主线程");
        a.foo(b);
        System.out.println("进入了主线程之后");
    }
    @Override
    public void run() {
        Thread.currentThread().setName("副线程");
        b.foo(a);
        System.out.println("进入了副线程之后");
    }
    public static void main(String[] args) {
        DeadLock deablock = new DeadLock();
        new Thread(deablock).start();
        deablock.init();
    }
}