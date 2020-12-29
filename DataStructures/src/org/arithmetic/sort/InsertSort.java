package org.arithmetic.sort;

import java.util.Arrays;

/**
 * @author lijichen
 * @date 2020/8/27 - 17:47
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 4, 5};
//        insertSort(arr);
        int[] arr = new int[80000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l = System.currentTimeMillis();
        System.out.println(l);
        insertSort(arr);
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void insertSort(int[] arr) {
        /*//[101, 34, 119, 1]
        //保存有序值表，的后一个值
        int insertVal = arr[1];//因为数组是从0开始
        //保存有序值表的前一个下标，用作比较
        int insertIndex = 1 - 1;//即arr[1]，中的1 - 1
        //循环,
        //insertIndex <= 0：保证不越界，即insertIndex--后小于0的时候，不会进行下列操作
        //insertVal < arr[insertIndex]：将insertVal和arr[insertIndex]的值进行对比，
        //  如果insertVal的值小于arr[insertIndex],需要进行替换
        while (insertIndex <= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = insertVal;//先将101，的后一个即34，改为101：[101, 101, 119, 1]
            insertIndex--;//之后对有序值表中的数据一一比对，知道insertIndex=-1，即找到了最后一个需要比对的值
        }
        //循环完成之后需要将arr[1],插入到应该在的位置
        arr[insertIndex + 1] = insertVal;//因为如果满足循环条件会进行insertIndex--操作，所以需要+1*/

        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];//因为数组是从0开始
            insertIndex = i - 1;//即arr[1]，中的1 - 1,即101值对应的下标
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//先将101，的后一个即34，改为101：[101, 101, 119, 1]
                insertIndex--;//之后对有序值表中的数据一一比对，知道insertIndex=-1，即找到了最后一个需要比对的值
            }
            arr[insertIndex + 1] = insertVal;//因为如果满足循环条件会进行insertIndex--操作，所以需要+1
            //输出
//            System.out.println("第"+i+""+"趟");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
