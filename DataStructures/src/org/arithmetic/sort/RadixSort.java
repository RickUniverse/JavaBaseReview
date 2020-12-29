package org.arithmetic.sort;

import java.util.Arrays;

/**基数排序:不能排序负数
 * @author lijichen
 * @date 2020/8/29 - 19:08
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr = {53,3,542,748,14,214,-1345,44};//不能排负数
//        radixSort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        radixSort(arr);
        System.out.println(System.currentTimeMillis() - l);
    }

    //基数排序
    public static void radixSort(int[] arr) {
        //找到数组中的最大数
        int max = arr[0];//假设arr[0],为最大数
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //最大数的位数
        int maxIndex = (max + "").length();

        /*
        * 1，二维数组包含十个一维数组
        * 2，为了防止在放入数的时候，数据溢出，则每个一位数组（桶）大小为arr.length
        * 3，典型的空间换时间算法
        * */
        int[][] bucket = new int[10][arr.length];
        /*
        * 1，为了记录每个桶中放入了多少个数据，定义一个一位数据存放各个桶中存放的数据的个数
        * */
        int[] bucketElementCounts = new int[10];

        //因为遍历的次数是按照位数来进行遍历的
        for (int i = 0, n = 1; i < maxIndex; i++, n *= 10) {//每次遍历乘10
            //针对每个元素对应的位数进行排序处理
            for (int j = 0; j < arr.length; j++) {
                //得到各个元素对应的位数的值
                int digitOfElement = arr[j] / n % 10;//得到各个位数对应的值
                //放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                //桶内对应的个数++
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序，（一维数组的下标，依次取出数据，放入原来数组
            int index = 0;//记录放入桶中的下标
            for (int j = 0; j < bucketElementCounts.length; j++) {//遍历每一个桶
                //如果桶里有数组，我没才将数据放入原数组
                if (bucketElementCounts[j] != 0) {
                    //循环遍历桶
                    for (int k = 0; k < bucketElementCounts[j]; k++) {
                        //index需要++
                        arr[index++] = bucket[j][k];//第j个桶中的k个值（一位数组中k下标的值）
                    }
                    //将该桶记录的个数清零，便于下一次的使用
                    bucketElementCounts[j] = 0;
                }
            }
            //每轮打印一次桶排序后的数组
//            System.out.println(Arrays.toString(arr));
        }
    }
}
