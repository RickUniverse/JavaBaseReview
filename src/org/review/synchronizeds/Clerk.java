package org.review.synchronizeds;

/**
 * @author lijichen
 * @date 2020/7/27 - 20:52
 */
public class Clerk {
    private int productCount = 0;

    public synchronized void productProduct() {
        if(productCount < 20){
            productCount++;
            System.out.println(Thread.currentThread().getName() + "生产了第" + productCount + "个产品");
            notifyAll();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void customerProduct() {
        if(productCount > 0){
            System.out.println(Thread.currentThread().getName() + "消费了第" + productCount + "个产品");
            productCount--;
            notify();
        }else{
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
class Producer extends Thread{
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("生产者开始生产产品");
        while (true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.productProduct();
        }

    }
}
class Customer extends Thread{
    private Clerk clerk;

    public Customer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        System.out.println("消费者开始消费产品");

        while (true){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.customerProduct();
        }
    }
}
class ProductTest{
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer pr = new Producer(clerk);
        Customer cus = new Customer(clerk);
        Customer cus2 = new Customer(clerk);
        pr.setName("生产者");
        cus.setName("消费者1");
        cus2.setName("消费者2");
        pr.start();
        cus.start();
        cus2.start();
    }
}