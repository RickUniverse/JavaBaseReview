package org.jdk11;

import java.util.Optional;
import java.util.function.Consumer;

/**
 * @author lijichen
 * @date 2020/8/9 - 18:10
 */
public class OptionalTest {
    public static void main(String[] args) {
        Optional<Object> op = Optional.empty();
        System.out.println(op.isEmpty());
        //System.out.println(op.orElseThrow());

        op = Optional.of("s");
        Optional<String> op2 = Optional.of("sdf");
        Optional<Object> or = op.or(() -> op2);//如果op为空。则返回op2，不为空返回op
        //Optional<Object> or2 = op.or(Optional::new);//如果op为空。则返回op2，不为空返回op
        System.out.println(or);
        //System.out.println(or2);

        //
        Consumer c = (@Deprecated var t) -> System.out.println(t.toString());
        c.accept("sdfasdf");
    }
}
