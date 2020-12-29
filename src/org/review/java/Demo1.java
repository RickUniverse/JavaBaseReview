package org.review.java;

/**
 * @author lijichen
 * @date 2020/7/25 - 9:55
 */
public class Demo1 {
    public static void main(String[] args) {
        //创建匿名线程
        new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++){
                    if(i % 2 == 0){
                        System.out.println("偶数:" + i);
                    }else{
                        System.out.println("奇数:" + i);
                    }

                }
            }
        }.start();
    }
}
