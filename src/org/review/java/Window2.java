package org.review.java;

/**
 * @author lijichen
 * @date 2020/7/25 - 10:50
 */
class Window2 extends Thread{
    private static int ticket = 100;

    private String str;
    public Window2(String str){
        this.str = str;
    }

    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            //synchronized (Window.class){
            //synchronized (obj) {//this就是唯一的window对象 {//synchronized (obj) {
                if(!show()){
                    break;
                }
            //}
        }
    }
    private static synchronized boolean show(){//锁是this,使用继承的方式，不能用同步代码块
        if (ticket > 0) {
            System.out.println(Thread.currentThread().getName() + "获得了第:" + ticket + "张票！");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ticket--;
            return true;
        }
        return false;
    }
}

class Test2{
    public static void main(String[] args) {
        String str = "";
        Window2 w1 = new Window2(str);
        Window2 w2 = new Window2(str);
        Window2 w3 = new Window2(str);


        /*Thread t1 = new Thread(w1, "线程1");
        Thread t2 = new Thread(w1, "线程2");
        Thread t3 = new Thread(w1, "线程3");*/

        w1.start();
        w2.start();
        w3.start();
    }
}
