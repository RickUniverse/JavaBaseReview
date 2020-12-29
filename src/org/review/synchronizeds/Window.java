package org.review.synchronizeds;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijichen
 * @date 2020/7/27 - 18:26
 */
public class Window implements Runnable{
    private int ticket = 50;
    private ReentrantLock lock = new ReentrantLock(true);//true表示先进先出

    @Override
    public void run() {
        while (true){
            try {
                lock.lock();//需要使用try--finally，否则会一直运行
                if (ticket > 0){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程：" + Thread.currentThread().getName() + "抢到了第：" + ticket + "张票");
                    ticket--;
                }else{
                    break;
                }
            }finally {
                lock.unlock();
            }
        }
    }
}

class Test{
    public static void main(String[] args) {
        Window w = new Window();

        Thread t1 = new Thread(w, "线程1");
        Thread t2 = new Thread(w, "线程2");
        Thread t3 = new Thread(w, "线程3");

        t1.start();
        t2.start();
        t3.start();

    }
}