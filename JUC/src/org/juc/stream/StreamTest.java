package org.juc.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijichen
 * @date 2020/10/1 - 15:43
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 13);
        User u2 = new User(2, "b", 4);
        User u3 = new User(3, "c", 14);
        User u4 = new User(4, "d", 15);
        User u5 = new User(5, "e", 6);
        User u6 = new User(6, "f", 17);
        User u7 = new User(7, "g", 18);

        List<User> userList = Arrays.asList(u1, u2, u3, u4, u5, u6, u7);
        //使用流
        List<String> collect = userList.stream()//获取流
                .filter(u -> u.getId() > 2)//过滤
                .map(u -> u.getName().toUpperCase())//映射
                .sorted((o1, o2) -> o2.compareTo(o2))//排序
                .limit(5).collect(Collectors.toList());//转换为list

        collect.forEach(System.out::println);
    }
}
