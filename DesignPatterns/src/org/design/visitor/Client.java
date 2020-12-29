package org.design.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/16 - 19:40
 */
public class Client {
    public static void main(String[] args) {
        //结果类中添加
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());
        //显示结果
        objectStructure.display(new Success());
    }
}
abstract class Action {
    //男评价
    abstract void getManResult(Man man);
    //女评价
    abstract void getWomanResult(Woman woman);
}
//结果具体类
class Success extends Action {

    @Override
    void getManResult(Man man) {
        System.out.println("男人给的评价成功");
        System.out.println(man);
    }

    @Override
    void getWomanResult(Woman woman) {
        System.out.println("女人给的评价成功");
        System.out.println(woman);
    }
}
//结果具体类
class Fail extends Action {

    @Override
    void getManResult(Man man) {
        System.out.println("男人给的评价不成功");
        System.out.println(man);
    }

    @Override
    void getWomanResult(Woman woman) {
        System.out.println("女人给的评价不成功");
        System.out.println(woman);
    }
}
//人类
abstract class Person {
    abstract void accept(Action action);
}
//男类
//这里的accept方法用到了：” 双生派 “
class Man extends Person {

    @Override
    void accept(Action action) {
        action.getManResult(this);
    }
}
//女人类
class Woman extends Person {

    @Override
    void accept(Action action) {
        action.getWomanResult(this);
    }
}
//结果类
class ObjectStructure {
    //维护一个集合
    List<Person> personList = new LinkedList<>();

    //增加到list
    public void attach(Person person) {
        personList.add(person);
    }
    //分离
    public void detach(Person person) {
        personList.remove(person);
    }
    //显示测评情况
    public void display(Action action){
        for (Person person : personList) {
            person.accept(action);
        }
    }
}