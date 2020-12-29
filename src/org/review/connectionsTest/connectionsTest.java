package org.review.connectionsTest;

import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/2 - 16:22
 */
public class connectionsTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("w");
        list.add("wg");
        list.add("sd");
        list.add("e");
        list.add("z");
        list.add("a");
        //将一个集合中的数据copy到另一个集合中
        List best = Arrays.asList(new Object[list.size()]);
        //System.out.println(Arrays.asList(new Object[list.size()]));
        System.out.println(best);
        Collections.copy(best,list);
        System.out.println(best);
        System.out.println("************");
        List list1 = Collections.synchronizedList(list);
        System.out.println(list1);
        System.out.println("************");
        Collections.shuffle(list);
        Collections.sort(list, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof String && o2 instanceof String){
                    String s1 = (String) o1;
                    String s2 = (String) o2;
                    return -s1.compareTo(s2);
                }
                throw new RuntimeException("数据类型错误！");
            }
        });
        System.out.println(list);
        System.out.println("************");

    }
}
