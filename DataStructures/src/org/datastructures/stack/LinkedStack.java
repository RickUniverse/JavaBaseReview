package org.datastructures.stack;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author lijichen
 * @date 2020/8/23 - 15:10
 */
class LinkedStackDemo{
    public static void main(String[] args) {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.pop();
//        linkedStack.push(new HeroNode(1,"q"));
//        linkedStack.push(new HeroNode(2,"q2"));
//        linkedStack.push(new HeroNode(3,"q3"));
//        linkedStack.push(new HeroNode(4,"q4"));
//        linkedStack.push(new HeroNode(5,"q5"));
        linkedStack.showLinkedStack();
        System.out.println("**********出栈后************");
        System.out.println(linkedStack.pop());
        System.out.println(linkedStack.pop());
        linkedStack.showLinkedStack();
        System.out.println("**************第二种方式******************");
        linkedStack.push2(new HeroNode(1,"q"));
        linkedStack.push2(new HeroNode(2,"q2"));
        linkedStack.push2(new HeroNode(3,"q3"));
        linkedStack.push2(new HeroNode(4,"q4"));
        linkedStack.push2(new HeroNode(5,"q5"));
        linkedStack.showLinkedStack2();
        linkedStack.pop2();
        System.out.println("**********出栈后************");
        linkedStack.showLinkedStack2();

    }
}
public class LinkedStack {
    //维护一个栈
    public HeroNode head = new HeroNode(0,"");

    //入栈二
    public void push2(HeroNode heroNode) {
        //维护下一个节点
        HeroNode next = head.next;//头节点的下一个节点，如果是第一次便是空，第二次就不是空了
        //给头节。next点赋值
        head.next = heroNode;
        //然后把之间的链接起来
        heroNode.next = next;

    }
    //出栈二
    public HeroNode pop2() {
        if (head.next == null) {
            throw new RuntimeException("栈为空！");
        }
        //维护一个节点
        HeroNode cur = head.next;
        head.next = head.next.next;//自我删除
        return cur;
    }
    //循环显示二
    public void showLinkedStack2() {
        if (head.next == null) {
            System.out.println("空！");
            return;
        }
        //维护指针
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
    //入栈
    public void push(HeroNode heroNode) {
        //维护一个辅助指针
        HeroNode temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;//入栈
    }
    //出栈
    public HeroNode pop() {
        if (head.next == null) {
            System.out.println("栈为空！");
            return null;
        }
        //维护一个辅助指针
        HeroNode temp = head;
        while (temp.next.next != null) {//如果temp.next.next不等于空,就接着找下一个
            temp = temp.next;//指针后移
        }
        HeroNode temp1 = temp.next;
        temp.next = null;
        return temp1;
    }
    //显示栈
    public void showLinkedStack() {
        if (head.next == null) {
            System.out.println("空！");
            return;
        }
        //维护指针
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
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
