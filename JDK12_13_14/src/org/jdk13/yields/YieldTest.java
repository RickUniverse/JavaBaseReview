package org.jdk13.yields;

import java.util.Scanner;

/**
 * @author lijichen
 * @date 2020/8/9 - 20:31
 */
public class YieldTest {
    public static void main(String[] args) {
        String s = switch (new Scanner(System.in).next()){
            case "q" : yield "q";
            case "w","e","r": {
                System.out.println("wwwwww");
                yield "wer";
            }
            case "s","f":yield "sf";
            default: throw new IllegalStateException("Unexpected value: " + new Scanner(System.in).next());
        };
        System.out.println(s);
    }
}
