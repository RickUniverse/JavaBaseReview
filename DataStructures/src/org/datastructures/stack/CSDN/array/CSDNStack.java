package org.datastructures.stack.CSDN.array;

/**
 * @author lijichen
 * @date 2020/8/24 - 13:46
 */
public class CSDNStack {
    public static void main(String[] args) {
        //创建数组栈
        ArrayStack3 arrayStack3 = new ArrayStack3(5);
        //入栈
        arrayStack3.push(new Tree(1));
        //输出栈
        arrayStack3.showStack();
        //出栈
        arrayStack3.pop();
        arrayStack3.showStack();
    }
}

class ArrayStack3 {
    //最大栈空间
    public int maxSize;
    //栈顶
    public int top = -1;
    //栈数组
    public Tree[] stack;

    //构造器
    public ArrayStack3(int maxSize) {
        this.maxSize = maxSize;
        //初始化栈数组
        stack = new Tree[this.maxSize];
    }
    //判断栈是否满
    public boolean isFall() {
        return top == this.maxSize - 1;
    }
    //判断栈是否空
    public boolean isEmpty() {
        return top == -1;
    }
    //入栈
    public void push(Tree value) {
        if (isFall()) {
            System.out.println("栈满！");
            return;
        }
        //先自身加 1
        stack[++top] = value;
    }
    //出栈
    public Tree pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        Tree value = stack[top];//接收到值之后
        top--;//top--
        return value;
    }
    //循环遍历栈
    public void showStack() {
        if (isEmpty()) {
            System.out.println("栈为空！");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.println("stack["+i+"]"+ stack[i]);
        }
    }
}
//创建树类
class Tree {
    private int no;

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