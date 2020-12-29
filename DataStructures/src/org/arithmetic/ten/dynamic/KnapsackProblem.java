package org.arithmetic.ten.dynamic;

/**
 * 动态规划：背包问题
 * @author lijichen
 * @date 2020/9/6 - 16:27
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        //物品的重量
        int[] w = {1,4,3};
        //物品的价值，这里的val[i] 就是老师前面讲的v[i]
        int[] val = {1500,3000,2000};
        //背包的容量
        int m = 4;
        //物品的个数
        int n = val.length;

        //创建二维数组
        //v[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;//第一列为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//第一行为零
        }

        //动态规划处理
        for (int i = 1; i < v.length; i++) {//不处理第一行，因为第一行是0
            for (int j = 1; j < v[0].length; j++) {//不处理第一列
                //公式
                if (w[i - 1] > j) {//因为我们程序i是从1开始的，因此原来公式中的w[i] > j 需要修改为：w[i - 1] > j
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] < val[i - 1] + v[i - 1][j - w[i - 1]]) {
                        v[i][j] = val[i - 1] + v[i - 1][j - w[i - 1]];
                        //将当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        v[i][j] = v[i - 1][j];
                    }
                }
            }
        }

        //输出v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }


        //输出最佳的放入
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while (i > 0 && j > 0) {//从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i - 1];
            }
            i--;
        }
    }
}
