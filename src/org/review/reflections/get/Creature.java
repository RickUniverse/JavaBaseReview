package org.review.reflections.get;

import java.io.Serializable;

/**
 * @author lijichen
 * @date 2020/8/6 - 18:03
 */
public class Creature<T> implements Serializable {
    private String nameFather;
    public int ageFather;

    public Creature() {}

    private Creature(String nameFather) {
        this.nameFather = nameFather;
    }

    public void show1(){
        System.out.println("动物1");
    }
    private String show2(){
        System.out.println("动物2");
        return "动物2sssss";
    }
}
