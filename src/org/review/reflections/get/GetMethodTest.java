package org.review.reflections.get;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author lijichen
 * @date 2020/8/6 - 18:45
 */
public class GetMethodTest {
    public static void main(String[] args) {
        Class<Persion> clazz = Persion.class;

        //获取当前运行时类和父类的public修饰的方法
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        System.out.println("****************************");
        //获取当前运行时类的所有方法
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods) {

            //注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a : annotations) {
                System.out.println(a);
            }
            //访问修饰符
            System.out.print(Modifier.toString(m.getModifiers()) + "\t");
            //返回值类型
            System.out.print(m.getReturnType().getName() + "\t");
            //方法名
            System.out.print(m.getName() +"(");

            //获取参数
            Class[] par = m.getParameterTypes();
            if (par.length > 0){
                for (int i = 0; i < par.length; i++) {
                    if (i == par.length-1){
                        System.out.print(par[i].getName() + "\targs" + (i+1) + ",");
                        break;
                    }
                    System.out.print(par[i].getName() + "\targs" + (i+1) + ",");
                }
            }
            System.out.print(")");
            //声明的异常
            Class[] ex = m.getExceptionTypes();
            if (ex.length > 0){
                System.out.println("throws");
                for (int i = 0; i < ex.length; i++) {
                    if (i == ex.length-1){
                        System.out.print(ex[i].getName());
                        break;
                    }
                    System.out.print(ex[i].getName() + ",");
                }
            }

            System.out.println("{}");



        }
    }
}
