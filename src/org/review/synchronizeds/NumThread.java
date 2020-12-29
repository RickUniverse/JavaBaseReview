package org.review.synchronizeds;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lijichen
 * @date 2020/7/27 - 21:38
 */
public class NumThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int num = 0;
        for (int i = 1; i <= 100; i++) {
            if(i % 2 == 0){
                num += i;
                System.out.println(i);
            }
        }
        return num;
    }
}
class ThreadNewTest{
    public static void main(String[] args) {
        NumThread nunT = new NumThread();
        FutureTask<Integer> fTask = new FutureTask<>(nunT);
        new Thread(fTask).start();

        try {
            Integer integer = fTask.get();
            System.out.println(integer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}