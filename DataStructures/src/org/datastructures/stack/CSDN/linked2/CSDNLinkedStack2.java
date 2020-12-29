package org.datastructures.stack.CSDN.linked2;

/**
 * @author lijichen
 * @date 2020/8/24 - 16:05
 */
public class CSDNLinkedStack2 {
    public static void main(String[] args) {
        //创建链表栈
        LinkedStack linkedStack = new LinkedStack();
        //入栈
        linkedStack.push(new Tree(1));
        linkedStack.push(new Tree(2));
        linkedStack.push(new Tree(3));
        //显示栈中数据
        linkedStack.showLinkedStack();
        //出栈
        System.out.println("*******出栈*******");
        System.out.println(linkedStack.pop());
        System.out.println("*******出栈后*******");
        linkedStack.showLinkedStack();
    }
}

class LinkedStack {
    //维护一个头节点
    public Tree head = new Tree(0);

    //入栈
    public void push(Tree tree) {
        //维护一个辅助指针
        Tree temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = tree;//入栈
    }
    //出栈
    public Tree pop() {
        if (head.next == null) {
            System.out.println("栈为空！");
            return null;
        }
        //维护一个辅助指针
        Tree temp = head;
        //temp.next.next找到当前节点的后一个节点的后一个节点为空的
        while (temp.next.next != null) {//如果temp.next.next不等于空,就接着找下一个
            temp = temp.next;//指针后移
        }
        //将需要pop的变量保存起来
        Tree temp1 = temp.next;
        //置空
        temp.next = null;
        return temp1;//返回
    }
    //显示栈
    public void showLinkedStack() {
        if (head.next == null) {
            System.out.println("空！");
            return;
        }
        //维护指针
        Tree temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }
}
//创建树类
class Tree {
    public int no;
    public Tree next;//保存指向的下一个Tree

    //构造器
    public Tree(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "no=" + no +
                '}';
    }
}