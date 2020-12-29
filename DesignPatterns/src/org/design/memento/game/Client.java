package org.design.memento.game;

import java.util.List;
import java.util.Map;

/**
 * 游戏备忘录
 * @author lijichen
 * @date 2020/8/18 - 14:46
 */
public class Client {
    public static void main(String[] args) {
        //创建角色
        GameRole gameRole = new GameRole();
        gameRole.setDef(100);
        gameRole.setVit(100);
        gameRole.display();
        //创建管理备忘录类
        Caretaker caretaker = new Caretaker();
        caretaker.setMemento(gameRole.createMemento());//保存当前状态

        //修改角色属性
        gameRole.setDef(1);
        gameRole.setVit(1);
        gameRole.display();

        //恢复后
        gameRole.recoverGameRoleFromMemento(caretaker.getMemento());
        gameRole.display();
    }
}
//备忘录对象
class Memento {
    private int vit;//攻击力
    private int def;//防御力

    public Memento(int vit, int def) {
        this.vit = vit;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
//管理备忘录类
class Caretaker {
    //维护一个备忘录
    public Memento memento;
    //维护一个角色多次状态集合
    //public List<Memento> mementoList;
    //维护多个角色的map集合
    //public Map<String,List<Memento>> stringListMap;

    //保存
    public void setMemento(Memento memento) {
        this.memento = memento;
    }
    //获取
    public Memento getMemento(){
        return this.memento;
    }
}
//游戏角色
//gameRole
class GameRole {
    private int vit;//攻击力
    private int def;//防御力

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
    //保存当前状态
    public Memento createMemento(){
        return new Memento(this.vit,this.def);
    }
    //恢復状态
    public void recoverGameRoleFromMemento(Memento memento){
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    public void display(){
        System.out.println("当前攻击力："+this.vit+"当前防御力："+this.def);
    }
}