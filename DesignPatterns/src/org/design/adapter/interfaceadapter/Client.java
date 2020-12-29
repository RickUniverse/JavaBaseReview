package org.design.adapter.interfaceadapter;

/**
 * 接口适配器模式
 *
 * @author lijichen
 * @date 2020/8/13 - 20:54
 */
public class Client {
    public static void main(String[] args) {
        //可以使用匿名对象实现单个接口
        AbaAdapter a = new AbaAdapter() {
            @Override
            public void m1() {
                System.out.println("实现了m1");
            }

            //抽象类没有显式重写则，实例化必须重写
            @Override
            public void m4() {
                System.out.println("被迫重写！~");
            }
        };
        a.m1();
        a.m2();
        a.m3();
        a.m4();
    }
}
interface IAdapter {
    void m1();
    void m2();
    void m3();
    void m4();
}
abstract class AbaAdapter implements IAdapter {
    @Override
    public void m1() {

    }

    @Override
    public void m2() {

    }

    @Override
    public void m3() {

    }


}
