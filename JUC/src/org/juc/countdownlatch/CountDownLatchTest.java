package org.juc.countdownlatch;

import java.util.concurrent.*;

/**
 *  强大的并发辅助工具类
 * @author lijichen
 * @date 2020/9/26 - 16:57
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        //抢车位
        Semaphore semaphore = new Semaphore(3);//三个车位

        for (int i = 1; i <= 7; i++) {
            new Thread(() -> {
                try {
                    //枪位置，同时车位会-1
                    semaphore.acquire();

                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    //停三秒钟
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //释放车位，同时车位会+1
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }

//        addCyclicBareier();
        //        subCountDown();
    }

    private static void addCyclicBareier() {
        //addCyclicBareier,做加法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,() -> {
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7; i++) {
            final int tempFinal = i;
            new Thread(() -> {
                System.out.println("收集到了" + tempFinal);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

    private static void subCountDown() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"走了");
                //做减法，每执行完一个线程-1
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //当计数器也就是6个线程都走完之后，await阻塞的线程就会唤醒
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"最后走");
    }
}
