package org.review.java;

/**
 * @author lijichen
 * @date 2020/7/25 - 10:50
 */
class Window implements Runnable{
    private int ticket = 30;

    private String str;
    public Window(String str){
        this.str = str;
    }

    Object obj = new Object();
    @Override
    public void run() {
        while (true){
            //synchronized (Window.class){
            synchronized (this) {//this就是唯一的window对象 {//synchronized (obj) {
                if (ticket > 0) {
                    System.out.println(Thread.currentThread().getName() + "获得了第:" + ticket + "张票！");
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                } else {
                    break;
                }
            }
        }
    }
}
class Test{
    public static void main(String[] args) {
        String str = "";
        Window w1 = new Window(str);

        Thread t1 = new Thread(w1, "线程1");
        Thread t2 = new Thread(w1, "线程2");
        Thread t3 = new Thread(w1, "线程3");

        t1.start();
        t2.start();
        t3.start();
    }
}
