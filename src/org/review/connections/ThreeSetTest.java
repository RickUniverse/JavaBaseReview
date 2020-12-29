package org.review.connections;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * @author lijichen
 * @date 2020/8/1 - 17:41
 */
public class ThreeSetTest {
    public static void main(String[] args) {
        TreeSet<User> set = new TreeSet<>(new Comparator<User>() {
            @Override
            public int compare(User u1, User u2) {
                //按照年龄排列
                //return Integer.compare(u1.getAge(),u2.getAge());
                //按照日期从小到大排列
                return u1.getDate().compareTo(u2.getDate());
            }

            //没有泛型
            /*@Override
            public int compare(Object o1, Object o2) {
                if(o1 instanceof User && o2 instanceof User){
                    User u1 = (User)o1;
                    User u2 = (User)o2;
                    //按照年龄排列
                    //return Integer.compare(u1.getAge(),u2.getAge());
                    //按照日期从小到大排列
                    return u1.getDate().compareTo(u2.getDate());
                }else{
                    throw new RuntimeException("数据错误！");
                }
            }*/
        });
        /*set.add(123);
        set.add(213);
        set.add(5123);
        set.add(3);
        set.add(3);*/
        set.add(new User("as",133,new Date(2020,1,33)));
        set.add(new User("asdf",133,new Date(345,1,2)));
        set.add(new User("as",133,new Date(2020,1,33)));
        set.add(new User("fd",6,new Date(64,1,4)));
        set.add(new User("aes",133,new Date(2020,1,33)));
        set.add(new User("as",5,new Date(2020,1,2)));
        set.add(new User("er",3,new Date(345,1,7)));
        set.add(new User("as",133,new Date(2020,1,33)));
        set.add(new User("sdf",53,new Date(243,1,6)));
        set.add(new User("as",133,new Date(2020,1,4)));
        set.add(new User("sdf",33,new Date(543,1,33)));
        set.add(new User("as",133,new Date(345,1,2)));
        set.add(new User("wer",34,new Date(2020,1,3)));
        set.add(new User("cxv",43,new Date(345,1,33)));
        set.add(new User("as",23,new Date(2020,1,5)));
        set.add(new User("wer",133,new Date(2020,1,33)));

        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

    }
}
