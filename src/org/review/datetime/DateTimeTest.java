package org.review.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijichen
 * @date 2020/7/30 - 17:00
 */
public class DateTimeTest {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();

        System.out.println(date);
        //格式化：日期 ---》 字符串
        String strF = sdf.format(date);
        System.out.println(strF);

        //解析：字符串 ---》 日期
        Date parse = sdf.parse(strF);//可能会抛异常

        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//开发常用格式

        //格式化
        String format = sdf2.format(date);
        System.out.println(format);
        //解析
        Date parse1 = sdf2.parse(format);
        System.out.println(parse1);

        //转换为sqldate
        String str = "2020-07-30 05:06:57";
        java.sql.Date date1 = new java.sql.Date(parse1.getTime());
        System.out.println(date1);
    }
}
