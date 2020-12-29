package org.jdk10.vars;

import java.util.Arrays;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/9 - 17:22
 */
public class VarTest {
    public static void main(String[] args) {
        List<? extends Number> numbers = Arrays.asList(1, 2, 4, 5, 6, 7.22);

        var arr = Arrays.asList(1, 2, 4, 5, 6, 7.22);

        for (var number : arr) {
            System.out.println(number);
        }
        var str = "sdf";
        var i = 3;
    }
}
