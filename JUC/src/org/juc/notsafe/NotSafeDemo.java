package org.juc.notsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * list不安全
 * @author lijichen
 * @date 2020/9/25 - 16:27
 */
public class NotSafeDemo {
    public static void main(String[] args) {

//         Map map = new HashMap();
         Map map = new ConcurrentHashMap();//线程安全
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            }).start();
        }
//        set();
//        arrayList();
    }

    private static void set() {
        Set set = new CopyOnWriteArraySet();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            }).start();
        }
    }

    private static void arrayList() {
        List<String> listA = new ArrayList<String>();
        //线程安全之Vector
        List<String> list = new Vector();
        //将线程不安全的转换为线程安全的
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        //线程安全之，CopyOnWriteArrayList
        /*
        *  public boolean add(E e) {
            final ReentrantLock lock = this.lock;
            lock.lock();
            try {
                Object[] elements = getArray();
                int len = elements.length;
                Object[] newElements = Arrays.copyOf(elements, len + 1);
                newElements[len] = e;
                setArray(newElements);
                return true;
            } finally {
                lock.unlock();
            }
        }
        * */
        List<String> list3 = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list3.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list3);
            }).start();
        }
    }
}
