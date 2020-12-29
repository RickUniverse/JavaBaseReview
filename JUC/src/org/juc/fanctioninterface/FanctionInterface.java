package org.juc.fanctioninterface;

/**
 * 函数式接口
 * @author lijichen
 * @date 2020/9/24 - 17:11
 */
public class FanctionInterface {
    public static void main(String[] args) {

        //lamdba表达式
        Foo foo = ((num1, num2) -> {
           return num1 + num2;
        });

        System.out.println(foo.add(1, 3));
        System.out.println(foo.sub(1,3));
        System.out.println(Foo.div(1,3));
        System.out.println(Foo.mv(1,1));
    }
}



//函数式接口
@FunctionalInterface
interface Foo{
    int add(int num1, int num2);

    //默认方法
    default int sub(int num1, int num2) {
        return num1 - num2;
    }

    //静态方法
    static int div(int num1, int num2) {
        return num1 / num2;
    }

    static int mv(int num1, int num2) {
        return num1 * num2;
    }
}

