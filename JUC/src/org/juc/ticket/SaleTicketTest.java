package org.juc.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 买票复习
 * @author lijichen
 * @date 2020/9/24 - 15:59
 */
public class SaleTicketTest {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        //lamdbaExpress表达式
        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 0; i < 50; i++) ticket.saleTicket();},"C").start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.saleTicket();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 50; i++) {
                    ticket.saleTicket();
                }
            }
        },"C").start();*/


    }

}


//资源类
class Ticket{
    private int ticket = 30;

    private final Lock lock = new ReentrantLock();

    public void saleTicket() {

        //每个线程到这里都需要获取锁
        lock.lock();
        try {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName()+"买到了第"+ ticket-- +"张票，还剩：" + ticket + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //到这里后，释放锁
            lock.unlock();
        }
    }
}



