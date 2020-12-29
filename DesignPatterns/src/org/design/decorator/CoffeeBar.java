package org.design.decorator;

/**
 * 装饰者模式
 * @author lijichen
 * @date 2020/8/14 - 18:04
 */
public class CoffeeBar {
    public static void main(String[] args) {
        //点一份咖啡
        Drink lb = new LongBlack();
        System.out.println(lb.getDes()+"价格：" + lb.getPrice());
        lb = new Chocolate(lb);
        lb = new Chocolate(lb);
        lb = new Soy(lb);
        lb = new Soy(lb);
        lb.cost();
        lb.getDes();//只知道用到了递归，以后理解--------
        System.out.println("总价：" + lb.cost());
        System.out.println("描述：" + lb.getDes());
        //System.out.println(lb.getDes()+"价格：" + lb.getPrice()+ "价格：" + lb.cost());
    }
}
//抽象类:和
abstract class Drink{
    private String des;
    private float price = 0.0f;

    public void setDes(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    //计算价格
    public abstract float cost();

}
//缓冲层：coffee
class Coffee extends Drink {

    @Override
    public float cost() {
        return super.getPrice();
    }
}
//具体的咖啡
//浓缩咖啡：espresso
class Espresso extends Coffee {
    public Espresso() {
        super.setDes(" 浓缩咖啡 ");
        super.setPrice(10.0f);
    }
}
//具体的咖啡
//longblack咖啡：longblack
class LongBlack extends Coffee {
    public LongBlack() {
        super.setDes(" LongBlack ");
        super.setPrice(4.0f);
    }
}
//包装类
class Decorator extends Drink {
    private Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    //输出价格
    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }
    //描述信息
    @Override
    public String getDes() {
        //获取描述信息和价格
        return super.getDes() + ": " + super.getPrice() + " && " + drink.getDes() + ": " + drink.getPrice();
    }
}
//配料类
//具体的decorator
//饺克力：chocolate
class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        super.setDes(" 巧克力 ");
        super.setPrice(2.0f);
    }

}
//配料类
//具体的decorator
//豆浆：soy
class Soy extends Decorator {

    public Soy(Drink drink) {
        super(drink);
        super.setDes(" 豆浆 ");
        super.setPrice(11.0f);
    }

}