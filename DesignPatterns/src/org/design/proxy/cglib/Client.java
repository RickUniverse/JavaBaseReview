package org.design.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author lijichen
 * @date 2020/8/16 - 15:08
 */
public class Client {
    public static void main(String[] args) {
        //获取代理对象，被代理类不需要实现接口
        Teacher teacher = (Teacher) new ProxyFactory(new Teacher()).getProxyInstace();
        String ok = teacher.teach("ok");
        System.out.println(ok);
        //不能搞
        //String str = (String) new ProxyFactory(new String("1")).getProxyInstace();
        //System.out.println(str.length());
    }
}
//具体老师
class Teacher {

    public String teach(String str) {
        System.out.println("老师在教课");
        return str;
    }
}
//代理工厂
//代理工厂需要实现：MethodInterceptor
class ProxyFactory implements MethodInterceptor {

    //被代理对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //获取代理对象
    public Object getProxyInstace(){
        //创建工具类
        Enhancer enhancer = new Enhancer();
        //设置父类
        enhancer.setSuperclass(target.getClass());
        //设置回调函数
        enhancer.setCallback(this);
        //创建子类对象即代理对象,并返回
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib代理开始");
        Object invoke = method.invoke(target, objects);
        System.out.println("cglib代理提交");
        return invoke;
    }
}