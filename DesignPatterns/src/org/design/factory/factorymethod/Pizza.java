package org.design.factory.factorymethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 方法工厂模式
 * @author lijichen
 * @date 2020/8/12 - 19:44
 */
public abstract class Pizza {
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
class BJCheesePizza extends Pizza{
    public BJCheesePizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "北京奶酪披萨");
    }
}
class BJGreekPizza extends Pizza{
    public BJGreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "北京希腊披萨");
    }
}
class LDCheesePizza extends Pizza{
    public LDCheesePizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "伦敦奶酪披萨");
    }
}
class LDGreekPizza extends Pizza{
    public LDGreekPizza(String name) {
        super(name);
    }

    @Override
    public void prepare() {
        System.out.println(super.name + "伦敦希腊披萨");
    }
}
abstract class OrderPizza{

    //String pizza_Type;
    Pizza pizza;

    public OrderPizza() {
        do {
            this.pizza = createPizza(getPizza_Type());
            if (pizza != null){
                this.pizza.prepare();
                this.pizza.bake();
                this.pizza.cut();
                this.pizza.box();
            }
        } while (pizza != null);
    }

    public abstract Pizza createPizza(String type_Pizza);

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
class BJOrderPizza extends OrderPizza {

    @Override
    public Pizza createPizza(String type_Pizza) {
        if (type_Pizza.equals("greek")) {
            return new BJGreekPizza(type_Pizza);
        } else if (type_Pizza.equals("cheese")){
            return new BJCheesePizza("cheese");
        }
        return null;
    }
}
class LDOrderPizza extends OrderPizza {

    @Override
    public Pizza createPizza(String type_Pizza) {
        if (type_Pizza.equals("greek")) {
            return new LDGreekPizza(type_Pizza);
        } else if (type_Pizza.equals("cheese")){
            return new LDCheesePizza("cheese");
        }
        return null;
    }
}
class PizzaStore {
    public static void main(String[] args) {
        new BJOrderPizza();
        //new LDOrderPizza();
    }
}