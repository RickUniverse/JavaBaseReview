package org.design.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * @author lijichen
 * @date 2020/8/15 - 20:17
 */
public class Clinet {
    public static void main(String[] args) {
        //使用动态代理
        //使用代理工厂获取动态的代理
        /*
        * 传入被代理类
        * 返回类型必须为被代理类实现的接口
        * 需要强转
        * */
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(new Teacher()).getProxyInstance();
        //Teacher proxyInstance2 = (Teacher) new ProxyFactory(new Teacher()).getProxyInstance();
        proxyInstance.teach();//调用方法
        System.out.println(proxyInstance.getClass().getName());
        //
        CharSequence proxyInstance1 = (CharSequence) new ProxyFactory(new String("1")).getProxyInstance();
        System.out.println(proxyInstance1.hashCode());
    }
}
//接口被代理类需要实现
interface ITeacherDao {
    void teach();
}
//具体老师
class Teacher implements ITeacherDao {

    @Override
    public void teach() {
        System.out.println("老师在教课");
    }
}
//代理工厂
//获取动态代理
//不需要要继承别代理类继承的接口
class ProxyFactory{
    //维护被代理对象
    private Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    //获取代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),//获取被代理对象的类加载器
                target.getClass().getInterfaces(),//获取被代理对象实现的所有接口
                new InvocationHandler() {//事件处理器
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("jdk动态代理。。。");
                        /*
                        * 参数1：被代理对象
                        * 参数2：方法参数列表
                        * */
                        Object invoke = method.invoke(target,args);//调用方法，接收参数
                        System.out.println("提交");
                        return invoke;//返回方法的返回值
                    }
                });
    }
}