package org.design.strategy;

/**
 * 策略模式
 * @author lijichen
 * @date 2020/8/18 - 19:39
 */
public class Client {
    public static void main(String[] args) {
        //创建野鸭子
        WildDuch wildDuch = new WildDuch("野鸭子");
        wildDuch.fly();
        //创建玩具鸭子
        ToyDuck toyDuck = new ToyDuck("玩具鸭");
        toyDuck.fly();
    }
}
//鸭子飞翔的行为接口
interface FlyBehavior {
    void fly(String name);
}
//鸭子叫的接口
interface QuackBehavior {
    void quack(String name);
}
//具体的飞翔类
class GoodFlyBehavior implements FlyBehavior {

    @Override
    public void fly(String name) {
        System.out.println(name+"：这个鸭子非得很好");
    }
}
//具体的飞翔类
class BadFlyBehavior implements FlyBehavior {

    @Override
    public void fly(String name) {
        System.out.println(name+"：这个鸭子飞的糟糕");
    }
}
//具体的飞翔类
class NoFlyBehavior implements FlyBehavior {

    @Override
    public void fly(String name) {
        System.out.println(name+"：这个鸭子不会飞");
    }
}
//抽象的鸭子类
abstract class Duck {
    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public Duck() {
    }

    //聚合一个飞行行为
    FlyBehavior flyBehavior;
    //也可以聚合 叫 行为
    QuackBehavior quackBehavior;

    public void fly(){
        if (flyBehavior != null) {
            flyBehavior.fly(this.name);
        }
    }

    //动态修改当前鸭子的飞行能力
    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }
}
//具体的鸭子类,野鸭
class WildDuch extends Duck {
    public WildDuch(String name) {
        super(name);
        super.flyBehavior = new GoodFlyBehavior();
    }

    public WildDuch() {
        super.flyBehavior = new GoodFlyBehavior();//不管有沒有实例化名字都创建
    }
}
//具体的鸭子类,玩具鸭
class ToyDuck extends Duck {
    public ToyDuck(String name) {
        super(name);
        super.flyBehavior = new NoFlyBehavior();
    }

    public ToyDuck() {
        super.flyBehavior = new NoFlyBehavior();
    }
}