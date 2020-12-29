package org.juc.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * @author lijichen
 * @date 2020/9/26 - 17:57
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        //五个线程写操作
        for (int i = 1; i <= 5; i++) {
            final int tempFinal = i;
            new Thread(() -> {
                try {
                    myCache.put(tempFinal+"",tempFinal+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        //五个线程读操作
        for (int i = 1; i <= 5; i++) {
            final int tempFinal = i;
            new Thread(() -> {
                try {
                    myCache.get(tempFinal+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}


//写一个缓存
class MyCache {
    private volatile Map<String,Object> map = new HashMap<String, Object>();

    //读写锁
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写操作
    public void put(String key, Object value) throws InterruptedException {

        //写操作使用写锁
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入数据");
            //线程休眠三秒
            TimeUnit.MICROSECONDS.sleep(300);
            //写入数据
            map.put(key,value);

            System.out.println(Thread.currentThread().getName()+"写入数据完成");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            readWriteLock.writeLock().unlock();
        }

    }

    //读操作
    public void get(String key) throws InterruptedException {

        //读操作使用读锁
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取数据");
            //线程休眠三秒
            TimeUnit.MICROSECONDS.sleep(300);
            //读取数据
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"开始读取数据,读取到了："+o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
