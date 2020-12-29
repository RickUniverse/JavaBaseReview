package org.review.reflections.get;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性
 *
 * @author lijichen
 * @date 2020/8/6 - 18:32
 */

public class GetFieldTest {
    public static void main(String[] args) {
        Class<Persion> clazz = Persion.class;

        //获取当前运行时类和父类的所有public修饰的属性
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        System.out.println("********************************");
        //获取当前运行时类的所有属性
        Field[] dFields = clazz.getDeclaredFields();
        for (Field dField : dFields) {
            //权限修饰符
            int modifiers = dField.getModifiers();
            System.out.print(Modifier.toString(modifiers)+"\t");
            //数据类型
            Class<?> type = dField.getType();
            System.out.print(type.getName()+"\t");
            //变量名
            System.out.println(dField.getName());

        }
    }
}
