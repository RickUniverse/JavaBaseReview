package org.review.connections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author lijichen
 * @date 2020/8/1 - 13:36
 */
public class InteratorTest {
    public static void main(String[] args) {
        Collection coll4 = new ArrayList();
        coll4.add(123);//顺序相同
        coll4.add(456);//顺序相同
        coll4.add(true);
        coll4.add(new String("aaa"));
        coll4.add(new Parens("lisi",12));
        coll4.add(123);
        //推荐写法
        Iterator iterator = coll4.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        //删除,
        Iterator iterator1 = coll4.iterator();//重新获取
        while (iterator1.hasNext()){
            Object next = iterator1.next();
            if("aaa".equals(next)){
                iterator1.remove();
            }
        }
        Iterator iterator2 = coll4.iterator();
        while (iterator2.hasNext()){
            System.out.println(iterator2.next());
        }
    }
}
