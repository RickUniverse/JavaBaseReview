package org.datastructures.linkedlist;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author lijichen
 * @date 2020/8/21 - 19:17
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode node1 = new HeroNode(1, "牛");
        HeroNode node2 = new HeroNode(2, "牛2");
        HeroNode node3 = new HeroNode(3, "牛3");
        HeroNode node4 = new HeroNode(4, "牛4");
        //创建单向链表实例
        SingleLinkedList linkedList = new SingleLinkedList();
        //添加
//        linkedList.add(node1);
//        linkedList.add(node2);
//        linkedList.add(node3);
        //顺序添加
        linkedList.addByOrder(node2);
        linkedList.addByOrder(node1);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node3);
        linkedList.addByOrder(node4);
        //遍历
        linkedList.list();
        System.out.println("*******************");
        linkedList.update(new HeroNode(1,"dssssssssssss"));
        //修改后
        linkedList.list();
        System.out.println("*********************");
        //删除
        linkedList.remove(2);
        //删除后
        linkedList.list();
        //获取链表个数
        System.out.println(getLenght(linkedList.getHead()));
        //获取倒数第几个元素
        System.out.println(getLastIndexHeroNode(linkedList.getHead(),1));
        //翻转
        System.out.println("------翻转--------");
        reverse(linkedList.getHead());
        linkedList.list();
        //翻转输出
        System.out.println("********print********");
        reversePrint(linkedList.getHead());
        System.out.println("*******************");
        SingleLinkedList linkedList2 = new SingleLinkedList();
        linkedList2.addByOrder(new HeroNode(4,"qq"));
        linkedList2.addByOrder(new HeroNode(5,"王五"));
        linkedList2.addByOrder(new HeroNode(6,"嗯嗯"));
        //linkedList2.addByOrder(node4);//不能添加中间有引用的，node4是最后一个所以可以添加，不然node3会形成环状
        linkedList2.list();
        System.out.println("************链接**************");
        linkedList.mergeHeroNode(linkedList2);
        linkedList.list();
    }

//    public static void mergeHeroHode(HeroNode heroNode1,HeroNode heroNode2) {
//
//    }

    //逆序输出
    public static void reversePrint(HeroNode heroNode) {
        if (heroNode.next == null) {
            return;//链表为空
        }
        //辅助
        HeroNode temp = heroNode.next;
        //声明一个栈
        Stack<HeroNode> stack = new Stack<>();
        //循环入栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;//后移
        }
        //循环出栈
        while (stack.size() > 0) {
            HeroNode pop = stack.pop();
            System.out.println(pop);
        }
    }

    //单链表翻转
    public static void reverse(HeroNode heroNode) {
        //如果没有节点或只有一个节点则直接返回
        if (heroNode.next == null || heroNode.next.next == null) {
            return;
        }
        //辅助节点
        HeroNode cur = heroNode.next;
        HeroNode next = null;//保存下一个节点
        HeroNode reverseHead = new HeroNode(0,"");//临时头节点
        //循环
        while (cur != null) {//判断是否到最后一个节点
            next = cur.next;//1，将temp。next节点保存起来
            cur.next = reverseHead.next;//2，第一次遍历时，reverseHead.next为空，下一次遍历便不是空
            reverseHead.next = cur;//3,将每一次的cur添加到头
            cur = next;//4,因为第二步导致原来的cur。next为空，所以next在这就派上了用处
        }
        heroNode.next = reverseHead.next;//将引用修改
    }

    //获取链表中元素的数量
    public static int getLenght(HeroNode heroNode) {
        if (heroNode.next == null) {
            return 0;//为空
        }
        int length = 0;//记录个数
        HeroNode temp = heroNode;//临时指针
        while (temp.next != null) {
            length++;
            temp = temp.next;//迭代
        }
        return length;
    }
    //获取链表中倒数第几个元素
    public static HeroNode getLastIndexHeroNode(HeroNode heroNode,int last) {
        if (heroNode.next == null) {
            return null;//没有元素
        }
        int lenght = getLenght(heroNode);//获取总共的个数
        if (last <= 0 || last > lenght) {//倒数不能小于或等于0，如果倒数的元素，大于总长度则不可能找到
            return null;
        }
        //维护指针
        HeroNode temp = heroNode.next;
        for (int i = 0; i < lenght - last; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
//创建SinglelinkedList管理英雄节点
class SingleLinkedList {
    //先初始化一个头节点
    private HeroNode head = new HeroNode(0,"");

    //get
    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void add(HeroNode heroNode) {
        //因为头节点不可以动，所以创建一个临时节点
        HeroNode temp = head;
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
    }
    //根据编号添加
    public void addByOrder(HeroNode heroNode) {
        //头节点不可动，声明一个指针节点
        HeroNode temp = head;
        boolean flag = false;//是否找到了节点
        //循环遍历查找
        while (true) {
            //如果temp的下一个节点为null说明没有数据,直接返回
            if (temp.next == null) {
                break;
            }
            //如果temp的next节点的no大于要添加的值说明要添加到temp.next和temp节点之间
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no ) {//如果相等就将flag设置为false
                flag = true;
                break;
            }
            //如果都不匹配，则指针后移
            temp = temp.next;
        }
        //循环结束后，确定要添加的位置
        if (flag) {
            System.out.println("不能添加");
        } else {
            heroNode.next = temp.next;//先将需要添加的节点的next指向temp.next
            temp.next = heroNode;//然后将temp.next的指针指向herNode
        }
    }

    //合并链表
    public void mergeHeroNode(SingleLinkedList list) {
        if (list.getHead().next == null) {
            return;//为空不能合并
        }
        //不能弄
        HeroNode heroNode = list.getHead().next;
        //System.out.println(list.getHead().next.next.next.next.next);
        //System.out.println(list.getHead().next.next.next.next);
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
//        System.out.println("temp.next:" + temp.next);
//        System.out.println("heroNode:" + heroNode.next.next.next);
        //System.out.println("head:" + head);
//        System.out.println(temp);
        temp.next = heroNode;
//        System.out.println("head:" + head.next.next.next.next.next.next);


//        HeroNode listHero = list.getHead().next;
//        HeroNode temp = head.next;
//        HeroNode tempNext = null;
//        HeroNode listHeroNext = null;
//        HeroNode mergeHead = new HeroNode(0,"");
//        while (temp != null) {
//            tempNext = temp.next;
//            mergeHead.next = temp;
//        }

    }

    //根据编号修改节点
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            return;//表示链表为空
        }
        //维护一个辅助变量
        HeroNode temp = head.next;
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
        //维护一个指针
        HeroNode temp = head;
        boolean flag = false;//是否找到
        //循环遍历
        while (true) {
            if (temp.next == null) {
                break;//找到了最后
            }
            if (temp.next.no == no) {
                flag = true;//找到了
                break;
            }
            //指针后移
            temp = temp.next;
        }
        //循环结束后
        if (flag) {
            temp.next = temp.next.next;
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
        HeroNode temp = head.next;
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
class HeroNode {
    public int no;
    public String name;
    public HeroNode next;//指向下一个

    public HeroNode(int no, String name) {
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
