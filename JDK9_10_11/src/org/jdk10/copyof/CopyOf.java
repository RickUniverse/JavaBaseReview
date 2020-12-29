package org.jdk10.copyof;

import org.review.jdk9.Persion;

import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/9 - 17:29
 */
public class CopyOf {
    public static void main(String[] args) {
        List<String> arr = List.of("asdf", "asdf", "e");//只读
        List<String> strings = List.copyOf(arr);//只读
        System.out.println(arr == strings);//true
        new Persion(1);
    }
}
