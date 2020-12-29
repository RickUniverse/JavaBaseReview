package org.review.reflections.get;

import java.lang.reflect.Constructor;

/**
 * @author lijichen
 * @date 2020/8/6 - 19:30
 */
public class GetOther {
    public static void main(String[] args) {
        Class<Persion> clazz = Persion.class;
        Constructor[] c = clazz.getConstructors();
        for (Constructor constructor : c) {
            System.out.println(constructor);
        }
        Constructor[] d = clazz.getDeclaredConstructors();
        for (Constructor constructor : d) {
            System.out.println(constructor);
        }
    }
}
