package org.juc.threadpool;

import java.util.concurrent.*;

/**
 * 线程池
 * @author lijichen
 * @date 2020/9/27 - 15:18
 */
public class ExecutorServiceTest {
    public static void main(String[] args) {

        //maximumPoolSize:最大线程数
        final int maximumPoolSize = Runtime.getRuntime().availableProcessors() + 1;

        /*
        * 使用ThreadPoolExecutor创建线程池
        * 对大线程数最好是电脑的逻辑处理器的数量 + 1
        * */

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,//初始线程数
                maximumPoolSize,//最大线程数
                2L,//减持空闲时间
                TimeUnit.SECONDS, //减持的时间单位
                new LinkedBlockingQueue<Runnable>(3),//阻塞队列,最好写上队列的长度
                Executors.defaultThreadFactory(),//默认线程模板
//                new ThreadPoolExecutor.AbortPolicy()//直接抛异常
//                new ThreadPoolExecutor.CallerRunsPolicy()//调用者策略
//                new ThreadPoolExecutor.DiscardOldestPolicy()//抛弃队列中等待最久的元素
                new ThreadPoolExecutor.DiscardPolicy()//直接忽略，最好的策略
        );//拒绝策略

        try {
            for (int i = 1; i <= 15; i++) {
                final int tempFinal = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" 受理了："+tempFinal);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * 关闭线程池
             * */
            threadPool.shutdown();
        }



//        initPool();
    }

    private static void initPool() {
        //一个线程池固定的线程数量
        //一池五线程
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5);
        /*
         * 一个线程池一个线程
         * */
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor();
        /*
         * 可扩展线程池
         * */
        ExecutorService threadPool3 = Executors.newCachedThreadPool();
        try {
            for (int i = 1; i <= 10; i++) {
                final int tempFinal = i;

                TimeUnit.SECONDS.sleep(1);

                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+" 受理了："+tempFinal);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*
             * 关闭线程池
             * */
            threadPool3.shutdown();
        }
    }
}
