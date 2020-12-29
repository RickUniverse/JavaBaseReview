package org.review.proxy;

/**
 * @author lijichen
 * @date 2020/8/7 - 16:03
 */
interface ClothFactory{
    void productCloth();
}
//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory clothFactory;

    public ProxyClothFactory(ClothFactory clothFactory) {
        this.clothFactory = clothFactory;
    }

    @Override
    public void productCloth() {
        System.out.println("代理类");
        clothFactory.productCloth();
        System.out.println("代理完成！");
    }
}
//被代理类
class NikeClothFactory implements ClothFactory{

    @Override
    public void productCloth() {
        System.out.println("nike工厂!");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //nike实例
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        //代理类代理nike
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nikeClothFactory);
        //代理调用nike方法
        proxyClothFactory.productCloth();
    }
}
