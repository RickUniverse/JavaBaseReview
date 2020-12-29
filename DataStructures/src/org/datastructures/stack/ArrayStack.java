package org.datastructures.stack;

/**
 * @author lijichen
 * @date 2020/8/23 - 14:52
 */
class Client {
    public static void main(String[] args) {

    }
}
class ArrayStack2 {
    //最大栈空间
    public int maxSize;
    //栈顶
    public int top;
    //栈数组
    public int[] stack;

    //构造器
    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        //初始化栈数组
        stack = new int[this.maxSize];
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
    public void push(int value) {
        if (isFall()) {
            System.out.println("栈满！");
            return;
        }
        stack[++top] = value;
    }
    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈为空！");
        }
        int value = stack[top];
        top--;
        return value;
    }
    //循环遍历栈
    public void showStack() {
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}
