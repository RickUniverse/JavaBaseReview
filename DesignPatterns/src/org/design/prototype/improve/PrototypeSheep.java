package org.design.prototype.improve;

/**
 * @author lijichen
 * @date 2020/8/13 - 16:08
 */
public class PrototypeSheep implements Cloneable {
    private int age;
    private String name;

    @Override
    public String toString() {
        return "PrototypeSheep{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public PrototypeSheep(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public PrototypeSheep() {
    }

    @Override
    protected Object clone()  {
        PrototypeSheep prototypeSheep = null;
        try {
            prototypeSheep = (PrototypeSheep)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return prototypeSheep;
    }
}
