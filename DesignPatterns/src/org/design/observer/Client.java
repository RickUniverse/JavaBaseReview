package org.design.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * @author lijichen
 * @date 2020/8/17 - 16:47
 */
public class Client {
    public static void main(String[] args) {
        //创建气象站
        WeatherData weatherData = new WeatherData();
        weatherData.registerObserver(new BaiDu("百度"));//添加观察者
        weatherData.registerObserver(new BaiDu("百度2"));
        //气温修改
        weatherData.setData(123,235,566);
        //删除观察者
        //weatherData.remove();
        //气温改变，通知观察者
        weatherData.dataChange();
    }
}
//创建观察者接口
interface Observer {
    /*
    * 观察者更新数据
    * 将数据更新为最新数据
    * */
    void update(String name, float temperature, float pressure, float humidity);
    /*
    * 显示最新数据
    * */
    void display();
    /*
    * 获取观察者名字
    * */
    String getName();
}
//观察者抽象类
abstract class AbsObserver implements Observer {
    protected String name;
    protected float temperature;//温度
    protected float pressure;//气压
    protected float humidity;//湿度

    public AbsObserver(String name) {
        this.name = name;
    }

    //重写获取名字的方法
    @Override
    public String getName() {
        return this.name;
    }

    //显示气温
    @Override
    public void display() {
        System.out.println("___________"+name+"____________");
        System.out.println("最新温度："+temperature);
        System.out.println("最新气压："+pressure);
        System.out.println("最新湿度："+humidity);
    }
}
class BaiDu extends AbsObserver {

    public BaiDu(String name) {
        super(name);
    }

    @Override
    public void update(String name,float temperature, float pressure, float humidity) {
        super.name = name;
        super.temperature = temperature;
        super.pressure = pressure;
        super.humidity = humidity;
        super.display();
    }
}
//气象局接口
interface Subject {
    void registerObserver(Observer observer);//注册
    void remove(Observer observer);//删除
    void notifyObserver();//通知
}
//具体气象类
class WeatherData implements Subject {
    //维护一个集合
    List<Observer> observerList = new ArrayList<>();
    //气温
    private float temperature;//温度
    private float pressure;//气压
    private float humidity;//湿度

    public void setData(float temperature, float pressure, float humidity){
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    public void dataChange(){
        notifyObserver();
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observerList) {
            observer.update(observer.getName(),this.temperature,this.pressure,this.humidity);
        }
    }
}