package org.datastructures.linkedlist;

/**
 * 双向链表
 * @author lijichen
 * @date 2020/8/22 - 17:20
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode2 node1 = new HeroNode2(1, "牛");
        HeroNode2 node2 = new HeroNode2(2, "牛2");
        HeroNode2 node3 = new HeroNode2(3, "牛3");
        HeroNode2 node4 = new HeroNode2(4, "牛4");
        //创建单向链表实例
        DoubleLinkedList linkedList = new DoubleLinkedList();
        //添加
        linkedList.add(node1);
        linkedList.add(node2);
        linkedList.add(node3);
        linkedList.add(node4);
        System.out.println("*******是否添加成功*******");
        linkedList.list();
        System.out.println("*******是否修改成功*******");
        linkedList.update(new HeroNode2(1,"DDDDDDDDDD"));
        linkedList.list();
        System.out.println("*******是否删除成功*******");
        linkedList.remove(2);
        linkedList.list();
    }
}
//双向链表
class DoubleLinkedList {
    //先初始化一个头节点
    private HeroNode2 head = new HeroNode2(0,"");

    //get
    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到双向链表
    public void add(HeroNode2 heroNode) {
        //因为头几点不可以动，所以创建一个临时节点
        HeroNode2 temp = head;
        //循环找到最后一个null节点，进行添加
        while (true) {
            //如果为空退出循环
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后，则将指针往下移
            temp = temp.next;
        }
        //当退出循环之后，指针就已经指向了最后
        temp.next = heroNode;//并将下一个指向添加来的数据
        heroNode.pre = temp;//将新添加的节点的上一个节点指向temp
    }

    //根据编号修改节点
    public void update(HeroNode2 newHeroNode) {
        if (head.next == null) {
            return;//表示链表为空
        }
        //维护一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到了
        while (true) {
            if (temp == null) {
                break;//没有节点
            }
            if (temp.no == newHeroNode.no) {
                flag = true;//找到了
                break;
            }
            //如果节点不为空,且没有找到
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
        } else {
            System.out.println("修改失败");
        }
    }

    //删除节点
    public void remove(int no) {
        if (head.next == null) {
            return;//没有节点
        }
        //维护一个指针
        HeroNode2 temp = head.next;
        boolean flag = false;//是否找到
        //循环遍历
        while (true) {
            if (temp == null) {
                break;//找到了最后
            }
            if (temp.no == no) {
                flag = true;//找到了
                break;
            }
            //指针后移
            temp = temp.next;
        }
        //循环结束后
        if (flag) {
            temp.pre.next = temp.next;//当前找到的元素的上一个元素的next为temp。next
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.println("没有找到！");
        }
    }

    //循环遍历节点
    public void list() {
        //先判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        //创建临时节点
        HeroNode2 temp = head.next;
        while (true) {
            //判断是否指向了最后
            if (temp == null) {
                break;
            }
            //否则输出该节点
            System.out.println(temp);
            //将指针后移
            temp = temp.next;
        }
    }
}

//创建英雄节点类
class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 next;//指向下一个
    public HeroNode2 pre;//指向上一个

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
