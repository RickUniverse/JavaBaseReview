package org.arithmetic.sort;

import java.util.Arrays;

/**
 * 希尔排序
 * @author lijichen
 * @date 2020/8/27 - 18:54
 */
public class ShellSort {
    public static void main(String[] args) {
        //数组
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort2(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        shellSort2(arr);//移位法
        System.out.println(System.currentTimeMillis() - l);
    }

    //希尔排序：交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        /*//临时变量
        int temp = 0;
        //第一轮
        for (int i = 5; i < arr.length; i++) {//比较五组
            for (int j = i - 5; j >= 0; j -= 5) {//当i=5，第一次循环开始循环，第一个位置arr[0]跟，arr[5],进行比较
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];//当i=5，第一次循环开始循环，将大于的数放在temp
                    arr[j] = arr[j + 5];//当i=5，第一次循环开始循环，将小于的数放在arr[j]
                    arr[j + 5] = temp;//当i=5，第一次循环开始循环，将大于的数放在arr[j + 5]
                }
            }
        }
        System.out.println(Arrays.toString(arr));*/

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {//比较五组
                for (int j = i - gap; j >= 0; j -= gap) {//当i=5，第一次循环开始循环，第一个位置arr[0]跟，arr[5],进行比较
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];//当i=5，第一次循环开始循环，将大于的数放在temp
                        arr[j] = arr[j + gap];//当i=5，第一次循环开始循环，将小于的数放在arr[j]
                        arr[j + gap] = temp;//当i=5，第一次循环开始循环，将大于的数放在arr[j + 5]
                    }
                }
            }
//            System.out.println("第"+(++count)+"轮，走了"+gap+"步");
//            System.out.println(Arrays.toString(arr));
        }
    }

    //希尔排序：移位法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];//第一次循环，arr[5]
                if (arr[j] < arr[j - gap]) {//第一次循环，从arr[5],往前找，所以是-gap
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环的时候，就找到位置了
                    arr[j] = temp;
                }
            }
        }
//        System.out.println(Arrays.toString(arr));
    }
}
