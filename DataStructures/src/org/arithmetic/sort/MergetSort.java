package org.arithmetic.sort;

import java.util.Arrays;

/** 归并排序算法
 * @author lijichen
 * @date 2020/8/29 - 17:21
 */
public class MergetSort {
    public static void main(String[] args) {
//        int[] arr = {8,4,5,7,1,3,6,2};
//        int[] temp = new int[arr.length];
//        mergetSort(arr,0,arr.length - 1, temp);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[80000];
        int[] temp = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        mergetSort(arr,0,arr.length - 1, temp);
        System.out.println(System.currentTimeMillis() - l);
    }

    //分+合
    public static void mergetSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergetSort(arr,left,mid,temp);
            //向右递归进行分解，mid需要加1
            mergetSort(arr,mid + 1,right,temp);
            //合并
            merget(arr,left,mid,right,temp);
        }
    }

    //合并的方法
    /**
     *
     * @param arr 排序的原始数据
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 临时数组，做中转的数组
     */
    public static void merget(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1;//初始化j，右边有序序列的初始索引
        int t = 0;// 指向temp的当前索引

        /*
        * 1
        * 先把左右两边的（有序）的数据按照规则填充到temp数组
        * 直到左右两边的有序序列，有一边的处理完毕为止
        * */
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边的有序序列的当前元素
            //即将左边的当前元素，填充到temp数组
            //然后t++，i++
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {//否则就将右边的元素填充到temp
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        /*
        * 2
        * 把有剩余数据的一边的数据依次全部填充到temp
        * */
        //如果左边有剩余元素
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        //如果右边有剩余元素
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }
        /*
        * 3
        * 将temp数组的元素拷贝到arr
        * */
        //注意并不是每次都拷贝所有
        t = 0;//临时数组指针指向第一个数
        int tempLeft = left;//第一次是从0开始，所以是left：0 ， right：2
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
