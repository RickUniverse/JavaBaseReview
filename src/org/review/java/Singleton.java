package org.review.java;

/**
 * @author lijichen
 * @date 2020/7/26 - 12:51
 */
public class Singleton {
    public static void main(String[] args) {
        //饿汉式
        Blank blank = Blank.getBlank();
        Blank blank2 = Blank.getBlank();
        System.out.println(blank == blank2);
        //懒汉式
        Order order = Order.getOrder();
        Order order2 = Order.getOrder();
        System.out.println(order == order2);

        System.out.println(Runtime.getRuntime());
    }
}
class Blank{//饿汉式
    private Blank(){}

    private static Blank blank = new Blank();

    public static Blank getBlank(){
        return blank;
    }
}
class Order{//懒汉式
    private Order(){}

    private static Order order = null;

    public synchronized static Order getOrder(){//懒汉式＋线程安全
        if (order == null){
            order = new Order();
        }
        return order;
    }
}