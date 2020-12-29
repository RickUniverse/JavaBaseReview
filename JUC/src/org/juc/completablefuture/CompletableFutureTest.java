package org.juc.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 异步回调
 * @author lijichen
 * @date 2020/10/1 - 17:44
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws Exception {
        //无返回值
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "：：没有返回值！");
        });
        System.out.println(voidCompletableFuture.get());

        //有返回值
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "：：有返回值！");
//            int temp = 10 / 0;
            return 1024;
        });

        Integer integer = integerCompletableFuture.whenComplete((r, e) -> {
            System.out.println("结果：" + r);
            System.out.println("错误：" + e);
        }).exceptionally(f -> {
            System.out.println("exception:" + f.getMessage());
            return 44;
        }).get();

        System.out.println(integer);
    }
}
