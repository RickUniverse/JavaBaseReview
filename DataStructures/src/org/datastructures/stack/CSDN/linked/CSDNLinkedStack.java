package org.datastructures.stack.CSDN.linked;

/**
 * @author lijichen
 * @date 2020/8/24 - 14:55
 */
public class CSDNLinkedStack {
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
        //保存head的下一个节点
        Tree next = head.next;//头节点的下一个节点，如果是第一次便是空，第二次就不是空了
        //给头节。next点赋值
        head.next = tree;
        //然后把之间的链接起来
        tree.next = next;
    }
    //出栈
    public Tree pop() {
        if (head.next == null) {
            throw new RuntimeException("栈为空！");
        }
        //保存一个节点
        Tree cur = head.next;
        /*
        * 将头节点的下一个指针指向头节点的下一个的下一个
        * 这样就完成了最简单的删除操作
        * */
        head.next = head.next.next;//自我删除
        return cur;
    }
    //循环显示
    public void showLinkedStack() {
        if (head.next == null) {
            System.out.println("栈空！");
            return;
        }
        //维护指针
        Tree temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;//指针后移
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