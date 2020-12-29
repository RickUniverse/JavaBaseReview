package org.review.strings;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;

import static javafx.scene.input.KeyCode.L;

/**
 * @author lijichen
 * @date 2020/7/28 - 21:00
 */
public class CharAndString {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String str = "Hello world!";
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            System.out.println(aChar);
        }
        String s = new String(chars);
        System.out.println(s);
        int i = 123123;
        String s1 = String.valueOf(i);
        System.out.println(s1);

        System.out.println("*************");

        str = "abc123中国";
        byte[] bytes = str.getBytes();
        System.out.println(bytes);
        System.out.println(Arrays.toString(bytes));

        byte[] gbks = str.getBytes("gbk");

        System.out.println(Arrays.toString(gbks));

        String s2 = new String(gbks);
        System.out.println(s2);
        String s3 = new String(gbks,"gbk");
        System.out.println(s3);
        System.out.println(System.currentTimeMillis());

        System.out.println("*************");

        StringBuffer sb = new StringBuffer(100);//给一个初始容量值
        long l = System.currentTimeMillis();
        Date date = new Date(l);
        java.sql.Date date1 = new java.sql.Date(l);

        //data yu sql date
        Date date2 = new Date();
        java.sql.Date date3 = new java.sql.Date(date2.getTime());
        System.out.println(date3.getTime());
    }
}
