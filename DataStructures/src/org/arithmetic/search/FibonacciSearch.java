package org.arithmetic.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 * @author lijichen
 * @date 2020/8/30 - 16:47
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public static void main(String[] args) {
        int arr[] = {1,8,10,89,1000,1234};
        fibSearch(arr, 89);
        System.out.println();
    }

    //得到一个斐波那契数列
    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    //斐波那契
    public static int fibSearch(int[] a,int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;//表示斐波那契分割数值的下标
        int mid = 0;//存放mid值
        int f[] = fib();//获取到斐波那契数列
        //获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }
        //因为f[k]的值可能大于a的长度，因此我们需要用arrays类，构建一个新数组，并指向a
        //不足的部分用0填充
        int[] temp = Arrays.copyOf(a,f[k]);
        //实际上需求使用数组最后的数填充temp
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = a[high];
        }

        //使用while来循环处理，找到我们的数key
        while (low <= high) {//只要条件满足，就可以找
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {//我们应该继续向数组的前面查找（左边）
                high = mid - 1;
                /*
                * 为什么是k--
                * 说明
                * 1，全部的元素 = 前面的元素 +　后面的元素
                * 2，f[k] = f[k-1] + f[k-2]
                * 3，因为 前面有f[k-1]个元素，所以可以继续拆分 f[k-1] = f[k-2] + f[k-3]
                *,4，即在f[k-1] 的前面继续查找k--
                *,5，即下次循环 mid = f[k-1-1]-1
                * */
                k--;
            }else if (key > temp[mid]) {//我们应该继续向数组的后面查找（右边）
                low = mid + 1;
                /*
                * 为什么是k -= 2
                * 说明
                * 1，全部的元素 = 前面的元素 +　后面的元素
                * 2，f[k] = f[k-1] + f[k-2]
                * 3，因为我们后面有f[k-2] 所以可以继续拆分 f[k-1] = f[k-3] + f[k-4]
                *,4，即在f[k-2] 的前面继续查找k -= 2
                *,5，即下次循环 mid = f[k-1-2]-1
                * */
                k -= 2;
            } else {//找到
                //需要确定返回的是那个下标
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}
