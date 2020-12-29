package org.design.factory.simplefactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 简单工厂模式
 * @author lijichen
 * @date 2020/8/12 - 18:19
 */
//披萨类
public abstract class AbstactPizza {
    protected String name;

    public void setName(String name) {
        this.name = name;
    }

    public AbstactPizza(String name) {
        this.name = name;
    }

    public abstract void perpare();

    public void bake() {
        System.out.println(name+"烘焙中。。。。");
    }

    public void cut() {
        System.out.println(name+"切块中。。。。");
    }

    public void box() {
        System.out.println(name+"装盒中。。。");
    }
}
//奶酪披萨类
class CheesePizza extends AbstactPizza {
    public CheesePizza(String name) {
        super(name);
    }

    @Override
    public void perpare() {
        System.out.println("给cheesepizza奶酪准备材料");
    }
}
//希腊披萨类
class GreekPizza extends AbstactPizza {
    public GreekPizza(String name) {
        super(name);
    }

    @Override
    public void perpare() {
        System.out.println("给greekpizza希腊准备材料");
    }
}
//工厂类，简单工厂模式
class SimpleFactory {

    //简单工厂模式、、静态工厂模式
    public static AbstactPizza getPizza(String pizza_Type) {

        if (pizza_Type.equals("cheese")) {
            return new CheesePizza("cheese");
        } else if (pizza_Type.equals("greek")) {
            return new CheesePizza("greek");
        }
        return null;
    }
}
class OrderPizza {
    //使用聚合
    SimpleFactory simpleFactory;
    //尽量不生命局部变量
    AbstactPizza pizza;

    //构造方法，只要创建购买披萨的类就询问购买
    public OrderPizza(SimpleFactory simpleFactory) {
        try {
            do {
                //实例化工厂
                this.pizza = simpleFactory.getPizza(printPizzaType());//调用方法输入信息
                if (this.pizza != null){
                    this.pizza.perpare();
                    this.pizza.bake();
                    this.pizza.cut();
                    this.pizza.box();
                }
            }while (this.pizza != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输入比萨的名字
    public String printPizzaType() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza type:");
        String s = br.readLine();
        //br.close();
        return s;
    }
}
class OrderPizza2 {

    //尽量不生命局部变量
    AbstactPizza pizza;

    //构造方法，只要创建购买披萨的类就询问购买
    public OrderPizza2() {
        try {
            do {
                //实例化工厂，，静态方法直接调用
                this.pizza = SimpleFactory.getPizza(printPizzaType());//调用方法输入信息
                if (this.pizza != null){
                    this.pizza.perpare();
                    this.pizza.bake();
                    this.pizza.cut();
                    this.pizza.box();
                }
            }while (this.pizza != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输入比萨的名字
    public String printPizzaType() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("input pizza type:");
        String s = br.readLine();
        //br.close();
        return s;
    }
}
class PizzaStore{
    public static void main(String[] args) {
        //创建购买者
        new OrderPizza(new SimpleFactory());
        new OrderPizza2();
    }
}