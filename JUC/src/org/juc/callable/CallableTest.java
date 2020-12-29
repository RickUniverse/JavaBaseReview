package org.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author lijichen
 * @date 2020/9/26 - 16:06
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //FutureTask,实现了runnable接口
        FutureTask futureTask = new FutureTask(new MyThread());
        //所以
        new Thread(futureTask,"A").start();
        //获取返回值,必须在线程执行完成之后获取
        System.out.println(futureTask.get());
    }
}




//实现callable接口的类
class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
//        Thread.sleep(40000);
        System.out.println("in call method");
        return "hello world!";
    }
}