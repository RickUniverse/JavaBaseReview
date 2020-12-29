package org.design.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 中介者模式
 * @author lijichen
 * @date 2020/8/17 - 18:34
 */
public class Client {
    public static void main(String[] args) {
        //创建中介者
        Mediator concreteMediator = new ConcreteMediator();
        //创建同事
        Alarm alarm = new Alarm("alarm",concreteMediator);
        CoffeeMachine coffeeMachine = new CoffeeMachine("coffeemachine",concreteMediator);
        TV tv = new TV("tv", concreteMediator);
        Curtain curtain = new Curtain("Curtain",concreteMediator);
        alarm.sendAlarm(0);//闹钟发送请求，咖啡机跟电视打开le
        coffeeMachine.finishCoffee();//咖啡煮好了，发送请求打开窗帘
        alarm.sendAlarm(1);//发送请求，关闭电视
    }
}
//创建同事接口
abstract class Colleague {
    protected String name;//同事名称
    private Mediator mediator;//中介者

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }

    abstract void sendMessage(int stateChange);//发送消息,传入状态值
    //获取中介
    public Mediator getMediator() {
        return this.mediator;
    }
}
//具体的同事类：alarm
class Alarm extends Colleague {

    public Alarm(String name, Mediator mediator) {
        super(name, mediator);
        mediator.Register(name,this);
    }

    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    @Override
    public void sendMessage(int stateChange) {
        super.getMediator().getMessage(stateChange,this.name);
    }
}
//具体的同事类：coffeemachine
class CoffeeMachine extends Colleague {

    public CoffeeMachine(String name, Mediator mediator) {
        super(name, mediator);
        mediator.Register(name,this);
    }

    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    public void startCoffee(){
        System.out.println("咖啡机打开了！");
    }

    public void finishCoffee() {
        System.out.println("咖啡煮好了！");
        sendMessage(0);//发送打开窗帘
    }

//    public void upCurtains() {
//        System.out.println("打开窗帘！");
//    }

    @Override
    public void sendMessage(int stateChange) {
        super.getMediator().getMessage(stateChange,this.name);
    }
}
//具体的同事类：TV
class TV extends Colleague {

    public TV(String name, Mediator mediator) {
        super(name, mediator);
        mediator.Register(name,this);
    }

    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    public void startTv(){
        System.out.println("电视大开了");
    }
    public void stopTv(){
        System.out.println("电视关闭了");
    }

    @Override
    public void sendMessage(int stateChange) {
        super.getMediator().getMessage(stateChange,this.name);
    }
}
//具体的同事类：Curtain
class Curtain extends Colleague {

    public Curtain(String name, Mediator mediator) {
        super(name, mediator);
        mediator.Register(name,this);
    }

    public void sendAlarm(int stateChange){
        sendMessage(stateChange);
    }

    public void upCurtains(){
        System.out.println("窗帘大开了");
    }

    @Override
    public void sendMessage(int stateChange) {
        super.getMediator().getMessage(stateChange,this.name);
    }
}
//创建中介抽象类
abstract class Mediator {
    //将同事添加到中介中
    public abstract void Register(String colleagueName,Colleague colleague);
    //得到同事发送的消息
    public abstract void getMessage(int stateChange, String colleagueName);
    //给同事发送消息
    public abstract void sendMessage();
}
//具体的中介类
class ConcreteMediator extends Mediator {
    //同事的map集合
    Map<String,Colleague> colleagueMap;
    Map<String,String> interMap;

    public ConcreteMediator() {
        colleagueMap = new HashMap<>();
        interMap = new HashMap<>();
    }

    @Override
    public void Register(String colleagueName, Colleague colleague) {
        colleagueMap.put(colleagueName,colleague);//将同事添加到map中
        if (colleague instanceof Alarm) {
            interMap.put("Alarm",colleagueName);
        } else if (colleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine",colleagueName);
        } else if (colleague instanceof TV) {
            interMap.put("TV",colleagueName);
        } else if (colleague instanceof Curtain){
            interMap.put("Curtain",colleagueName);
        }
    }

    @Override
    public void getMessage(int stateChange, String colleagueName) {
        if (colleagueMap.get(colleagueName) instanceof Alarm) {
            if (stateChange == 0) {
                ((CoffeeMachine) (colleagueMap.get(interMap.get("CoffeeMachine")))).startCoffee();
                ((TV)(colleagueMap.get(interMap.get("TV")))).startTv();
            }else if (stateChange == 1) {
                ((TV)(colleagueMap.get(interMap.get("TV")))).stopTv();
            }
        } else if (colleagueMap.get(colleagueName) instanceof CoffeeMachine) {
            if (stateChange == 0) {
                ((Curtain)(colleagueMap.get(interMap.get("Curtain")))).upCurtains();//打开窗帘
            }
        }
    }

    @Override
    public void sendMessage() {

    }
}