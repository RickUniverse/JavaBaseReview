package org.jdk9.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author lijichen
 * @date 2020/8/9 - 16:42
 */
public class StreamTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(2, 4, 5, 7, 8, 9, 4, 4, 5, 7, 8);
        //integers.stream().dropWhile( i -> i == 4).forEach(System.out::println);

        //integers.stream().takeWhile(i -> i == 4).forEach(System.out::println);

        Stream<Object> objectStream = Stream.ofNullable(null);
        System.out.println(objectStream.count());//0

        Stream.iterate(0,i -> i+1).limit(10).forEach(System.out::println);

        Stream.iterate(0,i -> i <= 10 ,i -> i+2).forEach(System.out::println);

        //Optional
        System.out.println("*************************************************");
        Optional<List<Integer>> optional = Optional.ofNullable(integers);
        Stream<List<Integer>> stream = optional.stream();
        Stream<Integer> integerStream = stream.flatMap(x -> x.stream());
        integerStream.forEach(System.out::println);
    }
}
