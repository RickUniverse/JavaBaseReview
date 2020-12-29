package org.arithmetic.search;

/** 线性查找
 * @author lijichen
 * @date 2020/8/30 - 14:19
 */
public class SeqSearch {
    public static void main(String[] args) {
        int arr[] = {1,2,3,4,5,6,6,234,2};
        System.out.println(seqSearch(arr, 4));
    }

    //线性查找
    public static int seqSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
