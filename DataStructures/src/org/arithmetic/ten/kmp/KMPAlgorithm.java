package org.arithmetic.ten.kmp;

import java.util.Arrays;

/**
 * kmp算法
 * @author lijichen
 * @date 2020/9/6 - 18:10
 */
public class KMPAlgorithm {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1,str2,next));

    }

    /**
     * kmp搜索算法
     * @param str1 源字符串
     * @param str2 子串
     * @param next 子串对应的部分匹配表
     * @return -1 表示没有匹配到
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        //遍历
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //需要处理
            //kmp核心算法点
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }

            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }

            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    //获取到一个字符串（子串）的部分匹配值表
    public static int[] kmpNext(String dest) {
        //创建一个next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;//如果字符串是长度为1部分匹配值就是0
        for (int i = 1, j = 0; i < dest.length(); i++) {
            //kmp算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1];
            }

            //当dest.charAt(i) == dest.charAt(j)
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
