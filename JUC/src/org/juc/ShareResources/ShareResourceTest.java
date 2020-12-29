package org.juc.ShareResources;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印问题：线程A打印5次 --> B打印10次 --> C打印15次
 * 打印十轮
 * @author lijichen
 * @date 2020/9/24 - 19:42
 */
public class ShareResourceTest {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        //A线程操作.print5();
        new Thread(() -> {
            //执行10轮
            for (int i = 0; i < 10; i++) {
                shareResource.print5();
            }
        },"A").start();
        //B线程操作.print10();
        new Thread(() -> {
            //执行10轮
            for (int i = 0; i < 10; i++) {
                shareResource.print10();
            }
        },"B").start();
        //C线程操作.print15();
        new Thread(() -> {
            //执行10轮
            for (int i = 0; i < 10; i++) {
                shareResource.print15();
            }
        },"C").start();
    }
}



//资源类
class ShareResource {

    /*
    * 默认线程A开始打印
    * １：表示线程Ａ
    * ２：表示线程Ｂ
    * ３：表示线程Ｃ
    * */
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    //打印五次的A的方法
    public void print5() {
        lock.lock();
        try {
            //判断
            //重点！！！！！！！！！！
            //多个线程用while
            //因为是A线程调用次方法，所以，只能是A打印了五次
            //number != 1 : 这里就相当于关联起来了A，这时就只有A线程可以打印，A线程调用的就是次方法
            while (number != 1) {//因为只有等于1的时候 才可以执行后面的打印五次，所以，这个number的值就隐秘的关联起来了condition1
                condition1.await();//这个满足条件后，就是相当于只要是当前方法，也就是线程A执行的方法，等待
            }
            //干活
            //等于1，之后需要改为2，让C打印
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }
            //修改为2，让C开始打印10次
            number = 2;
            //唤醒C线程
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //B
    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    //C
    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+(i+1));
            }
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}