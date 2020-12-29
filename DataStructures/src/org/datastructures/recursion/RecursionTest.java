package org.datastructures.recursion;

/**
 * 递归调用
 * @author lijichen
 * @date 2020/8/24 - 19:00
 */
public class RecursionTest {
    public static void main(String[] args) {
        //test(4);
        System.out.println(cheng(4));
    }
    //输出问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }//else {
        System.out.println(n);
        //}
    }//1 * 2 * 3 * 4
    //阶乘问题
    public static int cheng(int n) {
        if (n == 1) {
            return 1;
        }
        int s = cheng(n - 1) * n;
        return s;
    }
}
