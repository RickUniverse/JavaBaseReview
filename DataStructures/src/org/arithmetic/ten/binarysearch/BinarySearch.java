package org.arithmetic.ten.binarysearch;

/**
 * 非递归二分查找算法
 * @author lijichen
 * @date 2020/9/5 - 17:31
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int index = binarySearch(arr,67);
        System.out.println(index);
    }

    //
    public static int binarySearch(int[] arr, int target) {
        int left = 0;//左边开始查找的下标
        int right = arr.length - 1;//右边开始查找的下标
        while (left <= right) {//说明继续查找
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;//需要向左边查找
            } else {
                //因为需要向右边查找所以是将left = mid + 1，所以需要从mid+1开始查找，因为mid已经判断过了所以需要加一
                left = mid + 1;//需要向右边查找
            }
        }
        return -1;
    }
}
