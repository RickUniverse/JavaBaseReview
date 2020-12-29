package org.review.strings;

import javafx.scene.chart.Chart;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author lijichen
 * @date 2020/7/29 - 15:01
 */
public class StringAlgorithm {

    public static void main(String[] args) {
        //指定位置字符翻转
        /*System.out.println(reverse("1234567890", 2, 8));
        System.out.println(reverse2("1234567890", 2, 8));
        System.out.println(reverse3("1234567890", 2, 8));*/
        System.out.println("123456789".substring(1,4));
        System.out.println(getCount("asdfsdfaasdfsas","as"));
        String maxSameString = getMaxSameString("qwertygsdfghello001sdfgse","wxhello001ahcs");
        //System.out.println(maxSameString);
        System.out.println(Arrays.toString(
                getMaxSameString1("qwertygsdfghello001sdfgello002se", "wxhello001ahcello002s")
        ));
    }
    public static String reverse(String str,int startIndex,int endIndex){//algorithm：1
        if(str!=null && str.length() != 0){
            char[] chars = str.toCharArray();
            for(int x = startIndex,y = endIndex;x < y;x++,y--){
                char temp = chars[x];
                chars[x] = chars[y];
                chars[y] = temp;
            }
            return new String(chars);
        }
        return null;
    }
    public static String reverse2(String str,int startIndex,int endIndex) {//algorithm：2
        if(str != null){
            String reverseStr = str.substring(0,startIndex);
            for(int i = endIndex;i >= startIndex; i--){
                reverseStr += str.charAt(i);
            }
            return reverseStr += str.substring(endIndex + 1);
        }
        return null;
    }
    public static String reverse3(String str,int startIndex,int endIndex) {//algorithm：3
        if(str != null){
            StringBuilder builder = new StringBuilder(str.substring(0,startIndex));
            for(int i = endIndex;i >= startIndex; i--){
                builder.append(str.charAt(i));
            }
            return builder.append(str.substring(endIndex + 1)).toString();
        }
        return null;
    }
    //获取substr在mainstr中出现的次数
    public static int getCount(String mainStr,String subStr){
        int mainLength = mainStr.length();
        int subLength = subStr.length();
        int count = 0;
        int index = 0;
        if(mainLength >= subLength){
            //方式1
            /*while (((index = mainStr.indexOf(subStr)) != -1)){
                count++;
                mainStr = mainStr.substring(index + subLength);
            }*/
            //方式2
            while ((index = mainStr.indexOf(subStr,index)) != -1){
                count++;
                index += subLength;
            }

            return count;
        }
        return 0;
    }
    //获取两个字符串中最大相同字符串
    public static String getMaxSameString(String str1, String str2){
        if(str1 != null && str2 != null){
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            String minStr = (str1.length() < str2.length()) ? str1 : str2;

            int length = minStr.length();

            for (int i = 0; i < length; i++) {
                for (int x = 0,y = length - i;y <= length;){
                    String subStr = minStr.substring(x,y);
                    if(maxStr.contains(subStr)){
                        return subStr;
                    }
                    x++;y++;
                }
            }
        }
        return null;
    }
    public static String[] getMaxSameString1(String str1, String str2){
        if(str1 != null && str2 != null){
            StringBuilder sBuffer = new StringBuilder();
            String maxStr = (str1.length() >= str2.length()) ? str1 : str2;
            String minStr = (str1.length() < str2.length()) ? str1 : str2;

            int length = minStr.length();
            for (int i = 0; i < length; i++) {
                for (int x = 0,y = length - i;y <= length;){
                    String subStr = minStr.substring(x,y);
                    if(maxStr.contains(subStr)){
                        sBuffer.append(subStr + ",");
                    }
                    x++;y++;
                }
                if(sBuffer.length() != 0){
                    break;
                }
            }
            System.out.println(sBuffer.toString());
            System.out.println(sBuffer.toString().replaceAll(",$", ""));
            String[] split = sBuffer.toString().replaceAll(",$", "").split("\\,");
            System.out.println(Arrays.toString(split));
            return split;
        }
        return null;
    }
}
