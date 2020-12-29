package org.arithmetic.search;

import java.util.ArrayList;
import java.util.List;

/** 二分查找
 * @author lijichen
 * @date 2020/8/30 - 14:41
 */
public class BinarySearch {
    public static void main(String[] args) {
        int arr[] = {1,1,3,4,5,6,7,7,7,8,9,23};
//        int i = binarySearch(arr, 0, arr.length - 1, 7);
//        System.out.println(i);
        System.out.println(binarySearch2(arr, 0, arr.length - 1, 7));
    }

    //二分查找：递归
    public static int binarySearch(int[] arr,int left, int right, int findValue) {
        //如果left大于了right，说明递归到了最后，也没有找到
        if (left > right) {
            return -1;
        }
        //中间值
        int mid = (left + right) / 2;
        if (findValue > arr[mid]) {
            return binarySearch(arr,mid + 1, right,findValue);//向右递归
        } else if (findValue < arr[mid]) {
            return binarySearch(arr,left,mid - 1, findValue);//向左递归
        } else {
            return mid;//相等直接返回
        }
    }

    //查找多个相同值
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int findValue) {
        //如果left大于了right，说明递归到了最后，也没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        //中间值
        int mid = (left + right) / 2;
        if (findValue > arr[mid]) {
            return binarySearch2(arr,mid + 1, right,findValue);//向右递归
        } else if (findValue < arr[mid]) {
            return binarySearch2(arr,left,mid - 1, findValue);//向左递归
        } else {
            //创建集合
            List<Integer> resIndexList = new ArrayList<>();
            //向左查找,先左后右
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findValue) {//找到最后或者找的值不等于findValue
                    break;
                }
                //否则找到就加入到集合
                resIndexList.add(temp);
                temp --;//指针后移
            }
            //把一开始找到的值：mid加入到集合
            resIndexList.add(mid);
            //向右查找
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findValue) {
                    break;
                }
                //加入集合
                resIndexList.add(temp);
                temp++;
            }

            return resIndexList;//返回集合
        }
    }
}
