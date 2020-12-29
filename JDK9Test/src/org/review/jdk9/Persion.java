package org.review.jdk9;

/**
 * @author lijichen
 * @date 2020/8/8 - 20:10
 */
public class Persion {
    private int age;

    @Override
    public String toString() {
        return "Persion{" +
                "age=" + age +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Persion(int age) {
        this.age = age;
    }
}
