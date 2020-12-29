package org.review.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author lijichen
 * @date 2020/8/6 - 15:14
 */
public class ReflectionTest {
    public static void main(String[] args) throws Exception {
//        Persion p = new Persion("1adasd",123);
//        p.show();
        ///反射
        Class clazz = Persion.class;
        Constructor cons = clazz.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("Tom",123);
        Persion p = (Persion)obj;
        System.out.println(p.toString());
        //通过反射调用属性
        Field age = clazz.getDeclaredField("age");
        age.set(p,355);
        System.out.println(p);
        //调用方法
        Method show = clazz.getDeclaredMethod("show");
        show.invoke(p);

        //调用私有的结构
        Constructor cons2 = clazz.getDeclaredConstructor(String.class);
        cons2.setAccessible(true);
        Persion wait = (Persion) cons2.newInstance("wait");
        System.out.println(wait.toString());
        //私有属性
        Field field2 = clazz.getDeclaredField("name");
        field2.setAccessible(true);
        field2.set(wait,"汗");
        //私有方法
        Method getNative = clazz.getDeclaredMethod("getNative",String.class);
        getNative.setAccessible(true);
        String  chao = (String) getNative.invoke(wait, "超级王八");
        System.out.println(chao);
    }
}
class Test2{
    public static void main(String[] args) throws ClassNotFoundException {
        //获取Class的几种方式
        //1
        Class<Persion> persion = Persion.class;
        System.out.println(persion);
        //2
        Persion persion1 = new Persion();
        Class<? extends Persion> persion2 = persion1.getClass();
        System.out.println(persion2);
        //3
        Class<?> aClass = Class.forName("org.review.reflections.Persion");
        System.out.println(aClass);

        System.out.println(persion == persion2);
        System.out.println(persion == aClass);
        //4
        ClassLoader classLoader = Test2.class.getClassLoader();
        Class<?> aClass1 = classLoader.loadClass("org.review.reflections.Persion");
        System.out.println(aClass1);
        System.out.println(persion == aClass1);

        Class<Void> voidClass = void.class;//

    }
}
class Persion{
    private String name;
    public int age;

    private Persion(int age) {
        this.age = age;
    }

    private Persion(String name) {
        this.name = name;
    }

    public Persion(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Persion() {
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    public void show(){
        System.out.println("public method");
    }
    private String getNative(String natives){
        System.out.println("private method return:"+natives);
        return natives;
    }
}