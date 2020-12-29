package org.review.connections;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author lijichen
 * @date 2020/7/31 - 19:01
 */
public class ArrayListTest {
    public static void main(String[] args) {
        Collection coll = new ArrayList();
        coll.add(123);
        coll.add(456);
        coll.add(true);
        coll.add(new String("aaa"));
        coll.add(new Parens("lisi",12));
        coll.add(123);
        coll.add(123);
        Collection coll2 = new ArrayList();
        coll2.add("sdf");
        coll.addAll(coll2);
        coll2.add(new String("aaa"));
        System.out.println("1********************************");
        System.out.println(coll.contains(new Parens("lisi", 12)));
        System.out.println(coll.contains(new String("aaa")));//判断是否存在
        System.out.println(coll.containsAll(coll2));//判断coll2中的所有元素是否都在coll中
        System.out.println(coll);
        System.out.println("2********************************");
        //删除,差集
        //coll.removeAll(coll2);
        System.out.println(coll);
        System.out.println("3********************************");
        Collection coll3 = new ArrayList();
        coll3.add(123);//顺序相同
        coll3.add(456);//顺序相同
        coll3.add(true);
        coll3.add(new String("aaa"));
        coll3.add(new Parens("lisi",12));
        coll3.add(123);
        //交集
        //coll.retainAll(coll2);
        System.out.println(coll);
        System.out.println("4********************************");
        Collection coll4 = new ArrayList();
        coll4.add(123);//顺序相同
        coll4.add(456);//顺序相同
        coll4.add(true);
        coll4.add(new String("aaa"));
        coll4.add(new Parens("lisi",12));
        coll4.add(123);
        System.out.println(coll3.equals(coll4));//判断集合中数据是否相同和数据下标顺序
        System.out.println("5********************************");
        //返回哈希数值
        System.out.println(coll4.hashCode());
        //集合转换为数组
        Object[] to = coll4.toArray();
        for (Object o : to) {
            System.out.println(o);
        }
        //数组转换为集合
        List<String> strings = Arrays.asList(new String[]{"a", "b"});
        System.out.println(strings);
        //注意事项
        List<int[]> ints = Arrays.asList(new int[]{1, 2, 3, 4});//会视为别一个元素
        System.out.println(ints.size());
        //integer
        List<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3});//这样可以添加为多个
        System.out.println(integers);
    }
}
class Parens{
    private String name;
    private int age;

    public Parens(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        System.out.println(this);
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parens parens = (Parens) o;
        return age == parens.age &&
                Objects.equals(name, parens.name);
    }

    /*@Override
    public int hashCode() {
        return Objects.hash(name, age);
    }*/

    @Override
    public String toString() {
        return "Parens{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}