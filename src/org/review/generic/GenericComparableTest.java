package org.review.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author lijichen
 * @date 2020/8/3 - 17:52
 */
public class GenericComparableTest {
    public static void main(String[] args) {
        //创建泛型为<? extends Comparable>的集合
        ArrayList<Students> array = new ArrayList<>();
        show(array);
    }

    //创建一个参数为List<? extends Comparable> array的show方法
    static void show(List<? extends Comparable> array){}
}

class Students implements Comparable<Students>{//继承比较器接口
    private String name;
    private int age;

    //重写equals比较方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return age == students.age &&
                Objects.equals(name, students.name);
    }
    //重写获得哈希值的方法
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    //重写toString方法
    @Override
    public String toString() {
        return "Students{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    //实现Comparable后，实现的方法
    @Override
    public int compareTo(Students o) {
        return this.name.compareTo(o.name);//降序a-z排序
    }
}