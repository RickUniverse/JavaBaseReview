package org.review.maps;

import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/2 - 15:27
 */
public class TreeMapTest {
    public static void main(String[] args) {
        //按照键排序
        Map map = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o1 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
//                    return Integer.compare(u1.getAge(),u2.getAge());//按照年龄排序
                    return u1.getDate().compareTo(u2.getDate());//按照时间排序
                }
                throw new RuntimeException("数据类型错误！");
            }
        });
        map.put(new User("gwe",123,new Date(2020,2,23)),123);
        map.put(new User("gwe",324,new Date(-333,2,23)),"f");
        map.put(new User("gwe",55,new Date(234,2,23)),"s");
        map.put(new User("gwe",3,new Date(5020,2,23)),123);
        System.out.println(map);
    }
}
class User{
    private String name;
    private int age;
    private Date date;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        org.review.connections.User user = (org.review.connections.User) o;
//        return age == user.age &&
//                Objects.equals(name, user.name) &&
//                Objects.equals(date, user.date);
//    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, date);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", date=" + date.getTime() +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User(String name, int age, Date date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof org.review.connections.User){
//            org.review.connections.User user = (org.review.connections.User)o;
//            int compare = this.name.compareTo(user.name);
//            if (compare !=0 ){
//                return compare;
//            }else{
//                return Integer.compare(this.age,user.age);
//            }
//        }else{
//            throw new RuntimeException("异常");
//        }
//    }
}