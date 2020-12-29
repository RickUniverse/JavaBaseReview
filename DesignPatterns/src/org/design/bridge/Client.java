package org.design.bridge;

/**
 * 桥接模式
 * @author lijichen
 * @date 2020/8/14 - 16:25
 */
public class Client {
    public static void main(String[] args) {
        //创建手机种类
        PhoneBridge phoneBridge = new FoldedPhone(new XiaoMi());
        phoneBridge.call();
        phoneBridge.close();
        phoneBridge.open();
    }
}
//手机品牌接口
interface Brand{
    void open();
    void close();
    void call();
}
//具体手机实现类:小米
class XiaoMi implements Brand {

    @Override
    public void open() {
        System.out.println("小米手机打开了");
    }

    @Override
    public void close() {
        System.out.println("小米手机关闭了");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话了");
    }
}
//具体手机实现类:vivo
class Vivo implements Brand {

    @Override
    public void open() {
        System.out.println("Vivo手机打开了");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关闭了");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话了");
    }
}
/*
* 桥
* 充当桥的角色，将品牌聚合到桥中
* */
abstract class PhoneBridge{
    private Brand brand;

    public PhoneBridge(Brand brand) {
        this.brand = brand;
    }
    //充当桥的角色，调用传入参数的具体实例的方法
    protected void open() {
        this.brand.open();
    }
    protected void close() {
        this.brand.open();
    }
    protected void call() {
        this.brand.open();
    }
}
//手机种类：折叠手机
class FoldedPhone extends PhoneBridge {

    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("折叠手机");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("折叠手机");
    }
}