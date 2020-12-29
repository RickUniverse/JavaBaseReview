package org.jdk11;

/**
 * @author lijichen
 * @date 2020/8/9 - 17:50
 */
public class StringTest {
    public static void main(String[] args) {
        var str = "    \t\n  s df \t\n    ";
        System.out.println(str.isBlank());//是否是空格
        System.out.println(str.strip());//去除首位空白
        System.out.println(str.stripTrailing());//去除尾部空白
        System.out.println(str.stripLeading());//去除首部部空白
        System.out.println("str".repeat(3));//复制三次字符串
        System.out.println("\n\n\n".lines().count());//行数

    }
}
