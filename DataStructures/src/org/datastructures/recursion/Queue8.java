package org.datastructures.recursion;

/**八皇后问题
 * @author lijichen
 * @date 2020/8/26 - 18:17
 */
public class Queue8 {
    //有多少皇后
    int max = 8;
    //定义数组保存皇后存放的位置
    int[] array = new int[max];

    static int resultCount = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("摆放方式结果：" + resultCount);
        System.out.println("判断次数：" + judgeCount);
    }

    //编写一个方法，放置第n个皇后
    private void check(int n) {
        if (n == max) {//n等于8，其实八个皇后已然放好
            print();
            return;
        }
        //依次放入皇后，判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n ，放到该行的第 1 列
            array[n] = i;
            //判断当放置第n个皇后到i时，是否冲突
            if (judge(n)) {// 不冲突
                //接着开始放n + 1个皇后，即开始递归
                check(n + 1);
            }
            //如果冲突，就继续执行array[n] = i; 即将第n个皇后，放置在本行的后移的位置
        }
    }

    //查看当我们摆放第n个皇后，就去检测该皇后是否跟前面已经摆放的皇后冲突
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //array[i] == array[n]:判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i])：判断第n个皇后是否和第i皇后是否在同一斜线
            if (array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    //将皇后拜访的位置输出
    private void print() {
        resultCount++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println();
    }
}
