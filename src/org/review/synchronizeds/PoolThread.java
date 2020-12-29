package org.review.synchronizeds;

import java.util.concurrent.*;

/**
 * @author lijichen
 * @date 2020/7/28 - 11:42
 */
public class PoolThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 != 0){
                System.out.println(Thread.currentThread().getName() + ":::" + i);
            }
        }
        return "True";
    }
}
class PoolThread2 implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i <= 100; i++) {
            if(i % 2 == 0){
                System.out.println(Thread.currentThread().getName() + ":::" + i);
            }
        }
    }
}
class PoolTest{
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(15);
//        System.out.println(service);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
//        service1.setCorePoolSize();
//        service1.setKeepAliveTime();
//        service1.setMaximumPoolSize(2);

        //执行实现runnable的
        service.execute(new PoolThread2());
        service.execute(new PoolThread2());

        //执行实现callrunable的
        /*PoolThread pt = new PoolThread();
        Future<String> future = new FutureTask<String>(pt);
        service.submit(pt);*/
        service.shutdown();
        /*try {
            String s = future.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/


    }
}