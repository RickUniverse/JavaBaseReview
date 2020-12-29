package org.datastructures.tree;

import java.util.Arrays;

/**
 * @author lijichen
 * @date 2020/9/1 - 19:00
 */
public class HeapSort {
    public static void main(String[] args) {
//        int[] arr = {4,6,8,5,9};
//        heapSort(arr);
//        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        
        /*
        * 从左向右，从下往上进行adjustHeap
        * 说明：arr.length / 2 - 1  ： 指的是有多少叶子节点
        * */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr,i,arr.length);
        }

        /*
        * 1,将堆顶的元素沉到最后，即数组尾
        * 2，从新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个数组有序
        * 说明：arr.length - 1 ：因为最后一个一定是最小的值，所以只需要循环 arr.length - 1次
        *       是从顶部开始的
        * */

        int temp = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            //先保存栈顶的最大的值，即数组中下标为0，即arr[0]
            temp = arr[0];
            //交换
            arr[0] = arr[i];//将数组arr[i],位置的赋给栈顶，之后进行调整
            arr[i] = temp;//将最大的置到最后，即是arr[i]，的最后
            adjustHeap(arr,0, i);//将栈顶的元素进行调整，最大的放到栈顶
        }
    }

    /**
     * 将一个数组调整成为一个大顶堆
     * 功能：完成将以i节点对应的非叶子节点的树调整为大顶堆
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中索引
     * @param length 表示对多少个元素进行调整，length是在逐渐的减少
     */
    public static void adjustHeap(int arr[],int i, int length) {
        //保存i对应的非叶子节点
        int temp = arr[i];
        /*
        * 说明：（2 * i + 1）：是为了找到该非叶子节点的左子节点
        *       (j = j * 2 + 1) : 当前j节点的左子节点,的下标位置
        * */
        for (int j = i * 2 + 1; j < length; j = j * 2 + 1) {
            //说明：j+1是当前i所在，非叶子结点的右子节点
            if (j+1 < length && arr[j] < arr[j + 1]) {
                j++;//将指针指向右子节点
            }
            //如果上诉if判断成立则：判断右子节点是否比i这个非叶子节点大
            if (arr[j] > temp) {
                //将当前非叶子节点的值修改为右子节点
                arr[i] = arr[j];
                //将j指向右子节点的下标
                i = j;
            } else {
                break;//如果不大于就结束循环
            }
        }
        //循环结束后，需要将右叶子节点修改为temp
        arr[i] = temp;
    }
}
