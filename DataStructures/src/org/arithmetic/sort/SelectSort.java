package org.arithmetic.sort;

import java.util.Arrays;

/**
 * 选择排序,时间复杂度：O(n^2)
 * @author lijichen
 * @date 2020/8/27 - 16:46
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1};
//        selectSort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        selectSort(arr);
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            //最小值下标
            int minIndex = i;
            //最小值
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {//如果默认的最小值，比arr[i]大
                    minIndex = j;//则重新初始化最小值的下标和值
                    min = arr[j];
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];//将找到的最小值的位置赋值为arr[i]
                arr[i] = min;//将最小值位置的值修改为当前位置最小值
            }
//            System.out.println("第"+(i + 1)+""+"趟");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
