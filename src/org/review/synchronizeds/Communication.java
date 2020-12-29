package org.review.synchronizeds;

/**
 * @author lijichen
 * @date 2020/7/27 - 19:57
 */
public class Communication implements Runnable {
    private int num = 1;
    private Object obj = new Object();
    @Override
    public void run() {
        while (true){
            synchronized (obj){
                obj.notifyAll();
                if (num <= 100){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "输出了：" + num);
                    num ++;

                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    break;
                }
            }
        }
    }
}
class CommunicationTest{
    public static void main(String[] args) {
        Communication con = new Communication();
        Thread t1 = new Thread(con, "线程1");
        Thread t2 = new Thread(con, "线程2");
        Thread t3 = new Thread(con, "线程3");
        t1.start();
        t2.start();
        t3.start();
    }
}
