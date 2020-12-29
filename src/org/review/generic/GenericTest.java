package org.review.generic;

import java.util.*;

/**
 * @author lijichen
 * @date 2020/8/2 - 18:23
 */
public class GenericTest {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(12,"wer");
        map.put(234,"4sd");
        map.put(55,"sdf");
        map.put(23,"ag");

        Set<Map.Entry<Integer,String>> set =  map.entrySet();
        Iterator<Map.Entry<Integer,String>> iterable =  set.iterator();
        while (iterable.hasNext()) {
            Map.Entry<Integer,String> entry =  iterable.next();
            System.out.println(entry.getKey()+"____"+entry.getValue());
        }
        /////////////////////
        AbstractList<String> alist = null;
        List<String> list = null;
        ArrayList arlist = null;

        list = arlist;//允许
        list = alist;//允许
        //
        ArrayList<String> arlist2 = null;
        ArrayList<Object> arlist3 = null;
        //arlist2 = arlist3;//编译不通过
        //arlist3 = arlist2;//编译不通过
        //通配符
        Map<String,?> stu = new HashMap<>();
        //stu.put("qq",);
        //stu.put("S",);
        ArrayList<? extends Object> arlist4 = null;
        //arlist4.add();
        System.out.println("*****************");
        prints(map);
        System.out.println("*****************");
//        ArrayList<?> arr1 = new ArrayList<String>();//不能添加数据只能添加null类型的
//        arr1.add(null);
        ArrayList<String> arr1 = new ArrayList<String>();
        arr1.add("aa");
        List arr2 = null;
        arr2 = arr1;//这时arr2的类型变为了object类型
        Object o = arr2.get(0);
        System.out.println("*****************");
        Map<String,Integer> maps = new HashMap<>();
        maps.put("a",123);
        maps.put("s",666);
        maps.put("g",444);
        show(maps);
        System.out.println("*****************");

        List<Animal> array1 = new ArrayList<>();
        List<Cat> array2 = new ArrayList<>();
        List<Object> array3 = new ArrayList<>();
        List<? extends Animal> list1 = null;
        List<? super Animal> list2 = null;

        //list1.add(new Animal());//使用通配符？之后不能直接添加类型
        list1.add(null);//只能添加Null

        list1 = array1;
        list1 = array2;
        //list1 = array3;//编译不成功，说明<? extends Animal>泛型不能引用Animal继承链之上的类型比如：Object

        list2 = array1;
        //list2 = array2;//编译不成功，说明<? super Animal>泛型不能引用Animal继承链之下的类型比如：继承了Animal的Cat类
        list2 = array3;



    }

    public static void prints(Map<Integer,?> map){//通配符？不确定的类型
        System.out.println(map.getClass());
        Set<? extends Map.Entry<Integer, ?>> entries = map.entrySet();
        System.out.println(entries.getClass());
        Iterator<? extends Map.Entry<Integer, ?>> iterator = entries.iterator();
        System.out.println(iterator.getClass());
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
    public static void show(Map<String,Integer> map){
        System.out.println(map.getClass());
        Set<Map.Entry<String, Integer>> entries = map.entrySet();

    }
}
class Animal{}//动物类
class Cat extends Animal{}//继承了Animal的猫类
class Test{
    public static void main(String[] args) {
        List<Animal> array1 = new ArrayList<>();
        List<Cat> array2 = new ArrayList<>();
        List<Object> array3 = new ArrayList<>();
        List<? extends Animal> list1 = null;
        List<? super Animal> list2 = null;

        //list1.add(new Animal());//使用通配符？之后不能直接添加类型
        list1.add(null);//只能添加Null

        list1 = array1;
        list1 = array2;
        //list1 = array3;//编译不成功，说明<? extends Animal>泛型不能引用Animal继承链之上的类型比如：Object

        list2 = array1;
        //list2 = array2;//编译不成功，说明<? super Animal>泛型不能引用Animal继承链之下的类型比如：继承了Animal的Cat类
        list2 = array3;

        //创建一个map集合
        Map<Integer,String> maps = new HashMap<>();
        maps.put(1,"AAA");
        maps.put(2,"BBB");
        maps.put(3,"CCC");
        show(maps);//调用show方法，泛型参数则是声明<Integer,String>的集合
    }
    public static void show(Map<Integer,?> map){//通配符"？"不确定的类型
        /*
         * 生成变量
         * 因为有一个类型是不确定的所以显示的是最大的范围即：Map.Entry<Integer, ?>;<? extends Map.Entry<Integer, ?>>
         * 其他泛型集合都一样适用于此方法的通配符使用方式
         * */
        Set<? extends Map.Entry<Integer, ?>> entries = map.entrySet();
        //获取迭代器对象
        Iterator<? extends Map.Entry<Integer, ?>> iterator = entries.iterator();
        //使用while循环，迭代器遍历Map键值对集合
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }
}