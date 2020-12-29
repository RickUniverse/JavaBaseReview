package org.arithmetic.ten.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * 贪心算法
 * @author lijichen
 * @date 2020/9/6 - 19:49
 */
public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();
        //将各个电台放入到broadcasts
        HashSet<String> hashSetK1 = new HashSet<>();
        hashSetK1.add("北京");
        hashSetK1.add("上海");
        hashSetK1.add("天津");
        HashSet<String> hashSetK2 = new HashSet<>();
        hashSetK2.add("广州");
        hashSetK2.add("北京");
        hashSetK2.add("深圳");
        HashSet<String> hashSetK3 = new HashSet<>();
        hashSetK3.add("成都");
        hashSetK3.add("上海");
        hashSetK3.add("杭州");
        HashSet<String> hashSetK4 = new HashSet<>();
        hashSetK4.add("上海");
        hashSetK4.add("天津");
        HashSet<String> hashSetK5 = new HashSet<>();
        hashSetK5.add("杭州");
        hashSetK5.add("大连");

        //加入到broadcasts map
        broadcasts.put("K1",hashSetK1);
        broadcasts.put("K2",hashSetK2);
        broadcasts.put("K3",hashSetK3);
        broadcasts.put("K4",hashSetK4);
        broadcasts.put("K5",hashSetK5);

        //建立集合存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        for (Map.Entry<String,HashSet<String>> hashSet : broadcasts.entrySet()) {
            HashSet<String> setValue = hashSet.getValue();
            for (String city : setValue) {
                allAreas.add(city);
            }
        }
//        System.out.println(allAreas);//

        //创建arraylist集合，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey ,保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台key
        //如果maxKey 不为空，则会加入到selects集合中
        String maxKey = null;
        while (allAreas.size() > 0) {//如果allAreas不为0则说明还没有覆盖完成所有地区
            //每次循环都需要清空
            maxKey = null;

            //遍历broadcasts，取出对应的key
            for (String key : broadcasts.keySet()) {
                //临时的集合也需要清空
                tempSet.clear();

                //当前这个key能够覆盖的地区
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);//添加到临时集合中
                //求出tempSet 和 arrAreas 集合的交集，交集会赋给tempSet
                tempSet.retainAll(allAreas);//tempSet只有跟allAreas的交集
                //如果当前这个集合包含的未覆盖地区的数量，比maxKey指向的集合地区多
                //就需要重置maxKey
                //贪心算法特点：(maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()) )
                if (tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size()) ) {
                    //重置maxKey
                    maxKey = key;
                }
            }
            //maxKey不为null就应该加入selects集合中
            if (maxKey != null) {
                selects.add(maxKey);
                //这时就需要将allArea中已经覆盖的城市去掉，才能进行下次循环
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("得到的结果为：" + selects);
    }
}
