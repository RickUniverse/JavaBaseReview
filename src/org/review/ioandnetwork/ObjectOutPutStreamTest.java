package org.review.ioandnetwork;

import java.io.*;

/**
 * @author lijichen
 * @date 2020/8/5 - 14:50
 */
public class ObjectOutPutStreamTest {
    public static void main(String[] args) {
        ObjectOutputStreamsTest();
        ObjectInputStreamTest();
    }
    /*
    * 序列化
    * */
    static void ObjectOutputStreamsTest(){
        ObjectOutputStream oops = null;
        try {
            oops = new ObjectOutputStream(new FileOutputStream("object.dat"));
            oops.writeObject(new String("是打发斯蒂芬"));
            oops.flush();
            oops.writeObject(new Persion(123,"asdfasdf",new Account(123)));
            oops.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (oops != null) {
                try {
                    oops.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /*
    * f反序列化
    * */
    static void ObjectInputStreamTest(){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("object.dat"));

            Object o = ois.readObject();
            String str = (String)o;
            Persion p = (Persion) ois.readObject();
            System.out.println(str);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class Persion implements Serializable{

    public static final long serialVersionUID = 423422L;

    private static int age;//该属性不可序列化
    private transient String str;//该属性不可序列化
    private Account acc;

    @Override
    public String toString() {
        return "Persion{" +
                "age=" + age +
                ", str='" + str + '\'' +
                ", acc=" + acc +
                '}';
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public Persion(int age, String str, Account acc) {
        this.age = age;
        this.str = str;
        this.acc = acc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public Persion(int age, String str) {
        this.age = age;
        this.str = str;
    }

    public Persion() {
    }
}
class Account implements Serializable{
    @Override
    public String toString() {
        return "Account{" +
                "money=" + money +
                '}';
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Account(double money) {
        this.money = money;
    }

    private double money;
}