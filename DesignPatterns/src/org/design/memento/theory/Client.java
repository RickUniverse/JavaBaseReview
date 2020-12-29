package org.design.memento.theory;

import java.util.HashMap;
import java.util.Map;

/**
 * 备忘录模式
 * @author lijichen
 * @date 2020/8/17 - 20:00
 */
public class Client {
    public static void main(String[] args) {
        //创建角色，发起者
        Originator originator = new Originator();
        //创建备忘录仓库
        Caretaker caretaker = new Caretaker();

        originator.setState("状态1");//设置状态
        caretaker.add(1,originator.saveMemento());//保存状态
        originator.setState("状态2");//设置状态
        caretaker.add(2,originator.saveMemento());//保存状态
        originator.setState("状态3");//设置状态
        caretaker.add(3,originator.saveMemento());//保存状态
        System.out.println(originator);//恢复前的状态
        originator.getStateFromMemento(caretaker.getMemento(1));//恢复到某个状态
        System.out.println(originator);//恢复后的状态

    }
}
//角色，发起者
class Originator {
    private String state;//状态

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    //保存返回一个备忘录对象
    public Memento saveMemento(){
        return new Memento(this.state);
    }
    //恢复到某个状态
    public void getStateFromMemento(Memento memento){
        this.state = memento.getState();
    }

    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                '}';
    }
}
//状态备忘录
class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
//备忘录仓库
class Caretaker {
    //一个集合用来保存备忘录
    Map<Integer,Memento> mementoMap = new HashMap<>();

    //添加方法
    public void add(int len, Memento memento){
        mementoMap.put(Integer.valueOf(len),memento);
    }
    //get获取到备忘录
    public Memento getMemento(int len) {
        return mementoMap.get(Integer.valueOf(len));
    }
}