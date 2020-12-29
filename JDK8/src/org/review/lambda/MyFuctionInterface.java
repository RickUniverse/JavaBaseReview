package org.review.lambda;

/*
    自定义函数式接口
 */
@FunctionalInterface
public interface MyFuctionInterface {
    void method1();
}
class FunctionTest{
    public static void main(String[] args) {
        MyFuctionInterface mi = () -> System.out.println("sssss");
        mi.method1();
    }
}