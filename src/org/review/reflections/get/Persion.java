package org.review.reflections.get;

/**
 * @author lijichen
 * @date 2020/8/6 - 18:05
 */
@MyAnnotation(value = "class")
public class Persion extends Creature<String> implements Comparable<Persion>,MyInterface {

    @MyAnnotation(value = "parm")
    private String name;
    int age;
    public int id;

    @MyAnnotation
    public Persion() {}

    @MyAnnotation(value = "method")
    private String showNation(String nation,int age) throws ClassCastException,ArithmeticException{
        System.out.println(nation+"*********");
        return nation;
    }
    private static void method3(){
        System.out.println("wooooooooooo!!!!!");
    }
    public Persion(String name, int age, int id) {
        this.name = name;
        this.age = age;
        this.id = id;
    }

    Persion(int age, int id) {
        this.age = age;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Persion{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", id=" + id +
                '}';
    }

    private Persion(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Persion o) {
        return 0;
    }

    @Override
    public void info() {
        System.out.println("重写了myinterface接口中的方法！");
    }
}
