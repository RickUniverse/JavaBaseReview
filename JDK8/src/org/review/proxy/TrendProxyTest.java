package org.review.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lijichen
 * @date 2020/8/7 - 16:41
 */
interface HuMan{
    String Fly();
    void eat(String str);
}
//被代理类
class SuperMan implements HuMan{

    @Override
    public String Fly() {
        return "i can fly";
    }

    @Override
    public void eat(String str) {
        System.out.println(str);
    }
}
//获取代理类的类
class ProxyFactory{
    public static Object getProxyInstance(Object obj) {//传入被代理类
        MyInvocationHandler ih = new MyInvocationHandler();
        ih.bind(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), ih);
    }
}
class MyInvocationHandler implements InvocationHandler {
    private Object obj;//声明被代理对象

    public void bind(Object obj) {//使用方法绑定被代理类
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        //创建通用类
        HuManUtil huManUtil = new HuManUtil();
        huManUtil.method1();//通用代码1

        Object invoke = method.invoke(this.obj, args);//当前中类的被代理类,

        huManUtil.method2();//通用代码2

        return invoke;//方法返回值
    }
}
class HuManUtil{
    public void method1(){
        System.out.println("***************通用代码1****************");
    }
    public void method2(){
        System.out.println("***************通用代码2****************");
    }
}
public class TrendProxyTest {
    public static void main(String[] args) {
        //创建被代理类
        SuperMan sm = new SuperMan();
        //动态获取代理类
        HuMan proxyInstance = (HuMan) ProxyFactory.getProxyInstance(sm);
        //调用方法
        proxyInstance.eat("asdf");
        String fly = proxyInstance.Fly();
        System.out.println(fly);
        //动态获取代理类ClothFactory
        ClothFactory proxyInstance1 = (ClothFactory) ProxyFactory.getProxyInstance(new NikeClothFactory());
        proxyInstance1.productCloth();//调用方法
    }
}
