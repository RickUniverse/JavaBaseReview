package org.arithmetic.sort;

import java.util.Arrays;

/**
 * 快速排序
 * @author lijichen
 * @date 2020/8/28 - 19:04
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr = {-9, 78, 0, 23, -567, 70};
//        quickSort(arr,0,arr.length - 1);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        quickSort(arr,0,arr.length - 1);
        System.out.println(System.currentTimeMillis() - l);
//        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr, int left, int right) {
        int l = left;//左下标
        int r = right;//右下标
        //中轴值pivot
        int pivot = arr[(left + right) / 2];
        int temp = 0;//临时变量作为交换时使用
        //while，比pivot小的值放在左边
        //比pivot大的值放在右边
        while (l < r) {
            //在pivot的左边一直找，找到大于等于pivot值才推出
            while (arr[l] < pivot) {
                l += 1;//从左往右找，++
            }
            //在pivot的右边一直找，找到小于等于pivot值才推出
            while (arr[r] > pivot) {
                r -= 1;//从右往左找，--
            }
            //如果l >= r 说明pivot 的左右两边的值，已经按照
            // 左边全部都是小于等于pivot
            // 右边全部都是大于pivot的值
            if (l >= r) {
                break;//
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            //如果交换完成之后，发现arr[l] == pivot值 相等 r-- ,前移
            if (arr[l] == pivot) {
                r -= 1;
            }
            //如果交换完后，发现arr[r] == pivot值 相等 l++ ,后移
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        // 如果l == r，必须l++，r--，否则会出现栈溢出
        if (l == r) {
            l += 1;
            r -= 1;
        }
        //向左递归
        if (left < r) {
            quickSort(arr,left,r);
        }
        //向右递归
        if (right > l) {
            quickSort(arr,l,right);
        }
    }
}
