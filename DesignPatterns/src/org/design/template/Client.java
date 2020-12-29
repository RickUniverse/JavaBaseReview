package org.design.template;

/**
 * 模板方法模式
 * @author lijichen
 * @date 2020/8/16 - 15:59
 */
public class Client {
    public static void main(String[] args) {
        SoyaMilk redBeanMilk = new RedBeanMilk();
        redBeanMilk.make();
        System.out.println("**************************");
        //纯豆浆
        SoyaMilk pureSoyaMilk = new PureSoyaMilk();
        pureSoyaMilk.make();
    }
}
//抽象模板方法类
abstract class SoyaMilk {

    //模板方法
    void make(){
        select();
        if (customerWantCondiments()){
            addCondiments();
        }
        soak();
        beat();
    }

    void select(){
        System.out.println("选择好的");
    }
    abstract void addCondiments();
    void soak(){
        System.out.println("xunpao");
    }
    void beat(){
        System.out.println("beat打碎");
    }
    //钩子方法
    boolean customerWantCondiments(){
        return true;
    }
}
//具体类
//红豆类
class RedBeanMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        System.out.println("加入红豆");
    }
}
//存豆浆
class PureSoyaMilk extends SoyaMilk {

    @Override
    void addCondiments() {
        //空实现
    }

    @Override
    boolean customerWantCondiments() {
        return false;
    }
}