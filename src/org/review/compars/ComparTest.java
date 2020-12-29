package org.review.compars;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author lijichen
 * @date 2020/7/30 - 19:26
 */
public class ComparTest {
    public static void main(String[] args) {

        Goods[] arr = new Goods[10];
        arr[0] = new Goods("gsdf",40);
        arr[1] = new Goods("hsdf",620);
        arr[2] = new Goods("asdf",466);
        arr[3] = new Goods("xsdf",1);
        arr[4] = new Goods("gsdf",40);
        arr[5] = new Goods("asdf",40);
        arr[6] = new Goods("bsdf",40);
        arr[7] = new Goods("msdf",40);
        arr[8] = new Goods("zsdf",40);
        arr[9] = new Goods("asdf",40);
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        ////////
        show();
    }
    public static void show(){
        Goods[] arr = new Goods[10];
        arr[0] = new Goods("gsdf",40);
        arr[1] = new Goods("hsdf",620);
        arr[2] = new Goods("asdf",466);
        arr[3] = new Goods("xsdf",1);
        arr[4] = new Goods("gsdf",40);
        arr[5] = new Goods("asdf",40);
        arr[6] = new Goods("bsdf",40);
        arr[7] = new Goods("msdf",40);
        arr[8] = new Goods("zsdf",40);
        arr[9] = new Goods("asdf",40);
        Arrays.sort(arr, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if(o1.getName().equals(o2.getName())){
                    return -Double.compare(o1.getPrice(),o2.getPrice());//从高到低
                }else{
                    return o1.getName().compareTo(o2.getName());
                }
                //return 0;
                //throw new RuntimeException("错误！");
            }
        });
        System.out.println(Arrays.toString(arr));
    }
}
class Goods implements Comparable{
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        System.out.println("***********");
        if(o instanceof Goods){
            Goods goods = (Goods)o;
            //方式一
            if (this.price > goods.price){
                return 1;
            }else if (this.price < goods.price){
                return -1;
            }else{
                return -this.name.compareTo(goods.name);//z --> a
                //return this.name.compareTo(goods.name);//a --> z
            }
            //方式二
            //return Double.compare(this.price,goods.price);
        }
        //return 0;
        throw new RuntimeException("参数类型错误！");
    }
}