package org.datastructures.queue;

import java.util.Scanner;

/**
 * @author lijichen
 * @date 2020/8/21 - 15:26
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建队列实例
        ArrayQueue arrayQueue = new ArrayQueue(3);
        Scanner scanner = new Scanner(System.in);
        boolean isFlag = true;
        while (isFlag) {
            System.out.println("a[addQueue] : ");
            System.out.println("g[getQueue] : ");
            System.out.println("s[showQueue] : ");
            System.out.println("h[headQueue] : ");
            System.out.println("e[exit] : ");
            switch (scanner.next()) {
                case "a" :
                    System.out.println("请输入需要添加的数字：");
                    int c = scanner.nextInt();
                    arrayQueue.addQueue(c);
                    break;
                case "g" :

                    int queue = 0;
                    try {
                        queue = arrayQueue.getQueue();
                        System.out.printf("取出来了：%d\n",queue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "s" :
                    try {
                        arrayQueue.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "h" :
                    int headQueue = 0;
                    try {
                        headQueue = arrayQueue.headQueue();
                        System.out.printf("显示头数据：%d\n",headQueue);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "e" :
                    isFlag = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出！");
    }
}
//队列，先入先出队列
class ArrayQueue {
    private int maxSize;//最大长度
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//队列数组

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;//最大数组长度
        arr = new int[maxSize];
        front = -1;//指向队列头部
        rear = -1;//指向队列尾部
    }

    //判断队列是否满了
    public boolean isFull() {
        return rear == maxSize - 1;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }
    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            //throw new RuntimeException("数据已满！");
            System.out.println("数据已经满了！");
            return;
        }
        rear++;//rear后移
        arr[rear] = n;
    }
    //取数据
    public int getQueue(){
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        front++;//front后移，front自身加1
        return arr[front];
    }
    //显示队列所有数据
    public void show(){
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n" , i , arr[i]);
        }
    }
    //显示队列的头数据,注意不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        //System.out.println(arr[-3]+"--");//下標越界
        return arr[front + 1];//front + 1 ：返回一个值,不会自身加一
    }
}