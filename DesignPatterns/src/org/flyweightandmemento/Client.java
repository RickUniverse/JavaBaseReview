package org.flyweightandmemento;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 原型模式加备忘录模式实现游戏角色状态备份
 * @author lijichen
 * @date 2020/8/19 - 16:59
 */
public class Client {
    public static void main(String[] args) {
        //创建游戏角色
        GameRole gameRole = new GameRole("SuperMan", 1000, new Weapon("Red underpants"));
        System.out.println(gameRole);//输出当前角色状态
        //创建Caretaker
        Caretaker caretaker = new Caretaker();
        //保存角色的当前状态到Caretaker的集合中
        caretaker.add(gameRole.saveRoleState());//角色的方法会直接返回一个克隆后的实例
        //修改角色的声明值和武器
        gameRole.setLife(500);
        gameRole.getWeapon().setName("Not have Weapon");
        //修改后的
        System.out.println(gameRole);
        //恢复到之前的状态
        gameRole.recoverGameRole(caretaker.getGameRole(gameRole));//只需要把角色传入就可以
        System.out.println(gameRole);//之后在输出
    }
}
//武器类
class Weapon implements Serializable {
    //声明标识
    static final long serialVersionUID = 4332L;

    private String name;
    //构造器
    public Weapon(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weapon{" +
                "武器名称='" + name + '\'' +
                '}';
    }
}
//游戏角色类
class GameRole implements Serializable {
    static final long serialVersionUID = 43323422L;

    //游戏角色名称
    private String name;
    //角色生命值
    private int life;
    //武器对象
    private Weapon weapon;

    public GameRole(String name, int life, Weapon weapon) {
        this.name = name;
        this.life = life;
        this.weapon = weapon;
    }

    //恢复到某个状态
    public void recoverGameRole(GameRole gameRole){
        this.life = gameRole.getLife();
        this.weapon = gameRole.getWeapon();
    }

    //保存当前角色状态，并返回一个克隆角色
    public GameRole saveRoleState() {
        return cloneGameRole();
    }
    /*
    * 原型模式
    * 这个方法就是算是原型模式
    * 我是使用的序列化和反序列化的方式
    * 也可以使用继承Cloneable接口来实现
    * 由于使用的是序列化，直接就是深拷贝，相比较继承Cloneable接口来说比较方便
    * */
    private GameRole cloneGameRole() {
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        GameRole gameRole = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            bais = new ByteArrayInputStream(baos.toByteArray());
            ois = new ObjectInputStream(bais);
            gameRole = (GameRole) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bais != null){
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return gameRole;//返回一个克隆后的对象
    }

    public String getName() {
        return name;
    }

    public int getLife() {
        return life;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public String toString() {
        return "GameRole{" +
                "角色名字='" + name + '\'' +
                ", 声明值=" + life +
                ", 武器=" + weapon +
                '}';
    }
}
//创建看管角色对象的类
class Caretaker {
    //维护一个保存游戏角色当前状态的集合
    Map<String, GameRole> memento;

    public Caretaker() {
        memento = new HashMap<>();//创建时初始化集合
    }

    //添加一个角色(状态)
    public void add(GameRole gameRole){
        memento.put(gameRole.getName(),gameRole);
    }
    //获取摸个角色最近保存的状态,参数直接传入角色就可以
    public GameRole getGameRole(GameRole gameRole) {
        if (memento.containsKey(gameRole.getName())) {
            return memento.get(gameRole.getName());//如果存在就获取
        } else {
            throw new RuntimeException("没有该角色的状态！");//不然抛异常
        }
    }
}