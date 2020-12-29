package org.design.factory.abdfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lijichen
 * @date 2020/8/13 - 14:53
 */
public class PizzaStore {
    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
        new OrderPizza(new LDFactory());
    }
}
abstract class Pizza {
    String name;
    public abstract void prepare();

    public Pizza(String name) {
        this.name = name;
    }

    public void bake(){
        System.out.println(name +"正在准备");
    }

    public void cut(){
        System.out.println(name +"正在切");
    }

    public void box(){
        System.out.println(name +"正在装箱");
    }
}
class BJCheesePizza extends Pizza {
    public BJCheesePizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "北京奶酪披萨");
    }
}
class BJGreekPizza extends Pizza {
    public BJGreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "北京希腊披萨");
    }
}
class LDCheesePizza extends Pizza {
    public LDCheesePizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "伦敦奶酪披萨");
    }
}
class LDGreekPizza extends Pizza {
    public LDGreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "伦敦希腊披萨");
    }
}
interface AbsFactory {
    //抽象创建pizza
    Pizza createFactory(String type_Pizza);
}
class BJFactory implements AbsFactory {

    @Override
    public Pizza createFactory(String type_Pizza) {
        if (type_Pizza.equals("cheese")) {
            return new BJCheesePizza("北京奶酪cheese：");
        } else if (type_Pizza.equals("greek")) {
            return new BJGreekPizza("北京希腊greek：");
        }
        return null;
    }
}
class LDFactory implements AbsFactory {

    @Override
    public Pizza createFactory(String type_Pizza) {
        if (type_Pizza.equals("cheese")) {
            return new LDCheesePizza("LD奶酪cheese：");
        } else if (type_Pizza.equals("greek")) {
            return new LDGreekPizza("LD希腊greek：");
        }
        return null;
    }
}
class OrderPizza {
    AbsFactory factory;
    Pizza pizza;

    public OrderPizza(AbsFactory factory) {
        setFactory(factory);
    }

    private void setFactory(AbsFactory factory){
        this.factory = factory;
        do {
            this.pizza = factory.createFactory(getPizza_Type());
            if (this.pizza != null){
                this.pizza.prepare();
                this.pizza.bake();
                this.pizza.cut();
                this.pizza.box();
            }
        } while (this.pizza != null);
    }

    public String getPizza_Type(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入比萨名称！");
        try {
            String s = bufferedReader.readLine();
            return s;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("错误！");
    }
}