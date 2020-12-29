package org.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并框架
 * @author lijichen
 * @date 2020/10/1 - 16:45
 */
public class ForkJoinTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //分支对象
        MyTask myTask = new MyTask(0,100);
        //分支池
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);

        //输出结果
        System.out.println(forkJoinTask.get());
        //关闭池
        threadPool.shutdown();
    }
}



//资源类
class MyTask extends RecursiveTask<Integer> {

    //调整值
    private static final Integer ADDJUST_VALUE = 10;
    //begin开始值
    private int begin;
    //结束
    private int end;
    //结果
    private int result;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end - begin) <= ADDJUST_VALUE) {

            for (int i = begin; i <= end; i++) {
                //累加
                result = result + i;
            }
        } else {
            //计算中间值
            int middle = (end + begin) / 2;
            //分支对象
            MyTask m1 = new MyTask(begin,middle);
            MyTask m2 = new MyTask(middle + 1,end);
            m1.fork();
            m2.fork();
            //获取计算结果
            result = m1.join() + m2.join();
        }
        return result;
    }
}