package org.juc.communiation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程通信
 * @author lijichen
 * @date 2020/9/24 - 17:44
 */
public class CommuniationTest {
    public static void main(String[] args) {
        Communiation communiation = new Communiation();

        //lock,不需要处理异常
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                communiation.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                communiation.decrement();
            }
        },"C").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                communiation.increment();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                communiation.decrement();
            }
        },"D").start();

        //开启两个线程
        /*new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //先生产
                    communiation.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //后消费
                    communiation.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        //开启两个线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //先生产
                    communiation.increment();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    //后消费
                    communiation.decrement();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();*/
    }
}



//资源类
class Communiation {
    //初始化值为0
    private int number = 0;

    //使用lock
    private Lock lock = new ReentrantLock();
    //condition
    private Condition condition = lock.newCondition();

    public void increment() {
        //获取锁
        lock.lock();
        try {
            //循环判断条件
            while (number != 0) {
                //线程等待
                condition.await();
            }
            //生产
            number++;
            System.out.println(Thread.currentThread().getName()+":"+number);
            //唤醒其他线程
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    //消费
    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+":"+number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }



    //生产者
    /*public synchronized void increment() throws InterruptedException {

        //如果已经是1，就等待,让1变为0之后在加加
        *//*
        * 为了避免虚假唤醒
        * 多个线程必须使用while判断
        * while是为了让已将进入wait的线程再次进行判断是否！=0
        * 只有number==0 之后，得到执行允许的线程才可以++
        * *//*
        while (number != 0) {
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":"+number);
        //唤醒所有线程
        this.notifyAll();
    }
    //消费者
    public synchronized void decrement() throws InterruptedException {

        //如果是0，就进行等待，生产之后在消费
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":"+number);
        //唤醒所有线程
        this.notifyAll();
    }*/
}
