package org.review.synchronizeds;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lijichen
 * @date 2020/7/27 - 18:55
 */
public class Account {
    private double balance;

    private ReentrantLock lock = new ReentrantLock(true);

    public Account(double balance) {
        this.balance = balance;
    }

    public void deposit(double money) {

        if (money > 0) {
            try {
                lock.lock();
                // synchronized (Account.class) {
                balance += money;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "存了：money：" + money + "," + "余额为：" + balance);
                // }
            }finally {
                lock.unlock();
            }
        }
    }
}

class Custumer extends Thread {
    private Account acc;
    private double money;
    public Custumer(Account acc) {
        this.acc = acc;
    }

    public Custumer(Account acc, double money) {
        this.acc = acc;
        this.money = money;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            acc.deposit(money);
        }
    }
}

class AccountTest {
    public static void main(String[] args) {
        Account acc = new Account(0);
        Custumer cus1 = new Custumer(acc, 111);
        Custumer cus2 = new Custumer(acc, 222);
        Custumer cus3 = new Custumer(acc, 333);
        cus1.setName("张三1");
        cus2.setName("张三2");
        cus3.setName("张三3");
        cus1.start();
        cus2.start();
        cus3.start();
    }
}