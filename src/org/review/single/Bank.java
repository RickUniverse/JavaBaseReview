package org.review.single;

/**
 * @author lijichen
 * @date 2020/7/27 - 14:35
 */
public class Bank {
    private Bank(){}

    private static Bank instance = null;

    public static Bank getInstance(){
        if (instance == null){
            synchronized (Bank.class){
                if (instance == null){
                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}
