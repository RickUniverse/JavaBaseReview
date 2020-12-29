package org.arithmetic.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 冒泡排序,时间复杂度：O(n^2)
 * @author lijichen
 * @date 2020/8/27 - 15:38
 */
public class BubbleSort {
    public static void main(String[] args) {
        //数组
//        int[] arr = {3, 9, -1, 10, 20};
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        bubbleSort(arr);
        System.out.println(System.currentTimeMillis() - l);
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        //声明临时变量
        int temp = 0;
        //排序变量
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {//控制趟数
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;//这一趟有进行排序
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag) {
                //没有进行排序
                break;
            } else {
                flag = false;//复原
            }
//            System.out.println("第"+(i + 1)+""+"趟");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
