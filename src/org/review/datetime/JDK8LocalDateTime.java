package org.review.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

/**
 * @author lijichen
 * @date 2020/7/30 - 18:32
 */
public class JDK8LocalDateTime {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();

        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 10, 3, 44);
        LocalDateTime localDateTime2 = localDateTime.withHour(3);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);
        //瞬时类
        Instant now = Instant.now();
        //格式化或解析日期时间
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //格式化
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
        //解析
        TemporalAccessor parse = dateTimeFormatter.parse(format);
        System.out.println(parse);

    }
}
