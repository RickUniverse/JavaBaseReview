package org.design.flyweight;

import java.security.Key;
import java.util.HashMap;
import java.util.Map;

/**
 * 享元模式
 * @author lijichen
 * @date 2020/8/15 - 18:20
 */
public class Client {
    public static void main(String[] args) {
        //创建池工厂类
        WebSiteFactory factory = new WebSiteFactory();
        //获取网站对象
        WebSite concreteWebSite = factory.getConcreteWebSite("新闻！");
        WebSite concreteWebSite2 = factory.getConcreteWebSite("超级频道");
        concreteWebSite.use(new User("a"));
        concreteWebSite.use(new User("b"));
        concreteWebSite2.use(new User("c"));
        concreteWebSite2.use(new User("c"));
        concreteWebSite2.use(new User("谷歌"));
        //获取网站总数量
        System.out.println(factory.getWebSiteCount());
    }
}
//抽象类，制定方法
abstract class WebSite {
    public abstract void use(User user);
}
//具体网站
class ConcreteWebSite extends WebSite {

    private String type = "";//内部状态

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    //使用网站
    //user是外部状态
    @Override
    public void use(User user) {
        System.out.println("网站的类型是:" + this.type + "正在使用。。。。使用者是：" + user.getName());
    }
}
//池工厂
class WebSiteFactory {
    //池，用来减少网站对象的生成
    Map<String,ConcreteWebSite> factory = new HashMap<>();

    //按照网站类型获取网站
    public WebSite getConcreteWebSite(String type){
        if (!factory.containsKey(type)) {
            factory.put(type,new ConcreteWebSite(type));
        }
        return (WebSite)factory.get(type);
    }

    //获取网站的总数
    public int getWebSiteCount(){
        return factory.size();
    }
}
//用户类,外部状态
class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}