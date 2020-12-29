package org.review.generic;

/**
 * @author lijichen
 * @date 2020/8/2 - 20:08
 */
public class JDBC {
    public static void main(String[] args) {
        Student stu = new Student(123,"wer");
        Teacher tea = new Teacher(1234,"sdfa");

        System.out.println(DAO.getName(stu));//工具类
        System.out.println(DAO.getName(tea));//工具类

        //使用泛型方法
        System.out.println(DAO.getValue(new Integer(1)));
        //继承DAO
        Dog dog = new Dog();
        //dog.getDog();
        new DAO<Dog>().getDog(dog);
    }
}
class DAO<T>{
    T Persion;
    public static String getName(Persion obj){
        Persion per = (Persion) obj;//转换为人类型获取共有的属性,会丢失信息
        return per.getName();
    }

    public String getDog(T arr){
        return null;
    }

    static <E> E getValue(E obj){
        return obj;
    }
}
class Dog extends DAO<Dog> {

}
class Persion{
    private int age;
    private String name;

    public Persion(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
class Student extends Persion{

    public Student(int age, String name) {
        super(age, name);
    }
}
class Teacher extends Persion{
    public Teacher(int age, String name) {
        super(age, name);
    }
}
