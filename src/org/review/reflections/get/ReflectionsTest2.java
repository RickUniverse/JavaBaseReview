package org.review.reflections.get;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lijichen
 * @date 2020/8/6 - 20:13
 */
public class ReflectionsTest2 {
    public static void main(String[] args) throws Exception {
        Class<Persion> clazz = Persion.class;
        Persion p = clazz.newInstance();
        Field name = clazz.getDeclaredField("name");//可以获取私有属性
        name.setAccessible(true);//可以修改私有属性
        name.set(p,"1爱傻傻的噶三个");//修改
        System.out.println(name.get(p));//获取属性

        System.out.println("*********方法操作*************");
        //方法操作
        Method m = clazz.getDeclaredMethod("showNation",String.class,int.class);
        m.setAccessible(true);
        System.out.println(m.invoke(p,"cnnnnn",123));

        //静态方法
        Method m3 = clazz.getDeclaredMethod("method3");
        m3.setAccessible(true);
        System.out.println(m3.invoke(Persion.class));

        System.out.println("*********有参构造器操作*************");

        Constructor<Persion> constructor = clazz.getDeclaredConstructor(String.class);
        constructor.setAccessible(true);
        Persion wfaw = constructor.newInstance("wfaw");
        System.out.println(wfaw.toString());
    }
}
