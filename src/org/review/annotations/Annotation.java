package org.review.annotations;

import java.lang.annotation.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import static java.lang.System.*;//只导入静态成员

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @author lijichen
 * @date 2020/7/31 - 16:07
 */
public class Annotation {
    public static void main(String[] args) {

        @SuppressWarnings({"unused","..."})
        int a = 2;

        //inherited
        Son name = new Son("name");
        Class<Son> sonClass = Son.class;
        for (java.lang.annotation.Annotation annotation : sonClass.getAnnotations()) {
            System.out.println(annotation);
        };
        gc();//静态导入结果
    }
}
@Inherited//加上之后，使用该注解的父类的所有子类都会自动加上该注解
@Repeatable(MyAnnotations.class)//可重复注解
@Retention(RetentionPolicy.RUNTIME)//指定注解的声明周期，RUNTIME是运行时会加载到内存中
@Target({TYPE, FIELD, METHOD, PARAMETER, LOCAL_VARIABLE, TYPE_PARAMETER, TYPE_USE})//可以修饰哪些结构比如：类，方法，构造器，属性等
@interface MyAnnotation{//创建注解
    String value() default "hello";
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)//指定注解的声明周期，RUNTIME是运行时会加载到内存中
@Target({TYPE, FIELD, METHOD, PARAMETER, LOCAL_VARIABLE})//可以修饰哪些结构比如：类，方法，构造器，属性等
@interface MyAnnotations{//创建注解
    MyAnnotation[] value();
}
//@MyAnnotations({@MyAnnotation(value = "hi"),@MyAnnotation(value = "hi")})//jdk8之前的重复注解
@MyAnnotation(value = "hi")//使用注解
@MyAnnotation(value = "hi")//使用注解
class Hello{
    @MyAnnotation
    private String name;

    //@MyAnnotation
    public Hello(String name) {
        this.name = name;
    }
    @MyAnnotation
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Son extends Hello{
    public Son(String name) {
        super(name);
    }
}
class Grean<@MyAnnotation T>{
    public static void main(String[] args) throws @MyAnnotation RuntimeException{
        List<@MyAnnotation String> str = new ArrayList<>();
        int i = (@MyAnnotation int) 10L;
    }
}