package org.review.connections;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/1 - 16:30
 */
class User implements Comparable<User>{
    private String name;
    private int age;
    private Date date;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(name, user.name) &&
                Objects.equals(date, user.date);
    }

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

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.age,o.age);
    }

//    @Override
//    public int compareTo(Object o) {
//        if (o instanceof User){
//            User user = (User)o;
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
public class SetTest {
    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(123);
        set.add(123);
        set.add(321);
        set.add("Sdfasd");
        set.add(new User("a",1));
        set.add(new User("a",1));
        set.add(false);

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //LinkedList

    }
}
