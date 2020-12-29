package org.arithmetic.ten.kmp;

/**
 * 字符串暴力匹配算法
 * @author lijichen
 * @date 2020/9/6 - 17:03
 */
public class ViolenceMath {
    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        System.out.println(violenceMath(str1,str2));
    }

    public static int violenceMath(String str1, String str2) {
        //转换为char数组
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        //数组长度
        int s1Len = str1.length();
        int s2Len = str2.length();

        int i = 0;//指向s1
        int j = 0;//指向s2
        while (i < s1Len && j < s2Len) {
            //匹配到一个字符
            if (s1[i] == s2[j]) {
                i++;//匹配下一个字符
                j++;
            } else {//没有匹配成功
                i = i - (j - 1);//如果匹配过程中出现失败，就将i回归找到了一个匹配的值之前的+1 下标，所以 (j - 1)
                j = 0;//从新开始匹配
            }
        }

        //循环结束后
        if (j == s2Len) {//匹配成功
            return i - j;//找到最开始匹配到的下标
        } else {
            return -1;//没有找到
        }
    }
}
