package org.arithmetic.search;

/**
 * 插值查找
 * @author lijichen
 * @date 2020/8/30 - 16:00
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.println(insertValueSearch(arr, 0, arr.length - 1, 55));
    }

    //插值查找
    public static int insertValueSearch(int[] arr, int left, int right, int findValue) {
        //如果left大于right，返回-1
        //findValue > arr[arr.length - 1] || findValue < arr[0])  ： 优化且防止mid数据越界
        if (left > right || findValue > arr[arr.length - 1] || findValue < arr[0]) {
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);//算法
        int midValue = arr[mid];
        if (findValue > midValue) {//向右查找
            return insertValueSearch(arr,mid + 1, right, findValue);
        } else if (findValue < midValue) {//想左查找
            return insertValueSearch(arr,left,mid - 1,findValue);
        } else {
            return mid;
        }
    }
}
