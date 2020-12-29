package org.datastructures.queue;

import java.util.Scanner;

/**
 * 环形数组
 * @author lijichen
 * @date 2020/8/21 - 17:19
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
//        System.out.println(5 % 3);
//        System.out.println(5 % 2);
        CircleQueue arrayQueue = new CircleQueue(4);//这里的有效空间是3
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
class CircleQueue {
    private int maxSize;//最大长度
    private int front;//队列头
    private int reat;//队列尾
    private int[] arr;//队列数组

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    //判断队列是否满
    public boolean isFull() {
        return (reat + 1) % maxSize == front;
    }

    //判断是否为空
    public boolean isEmpty() {
        return reat == front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        if (isFull()) {
            //throw new RuntimeException("数据已满！");
            System.out.println("数据已经满了！");
            return;
        }
        //直接将数据加入
        arr[reat] = n;
        //将reat后移，这里必须考虑取模
        reat = (reat + 1) % maxSize;
    }
    //获得队列的数据，出列
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1，先把front对应的值保存到一个临时变量
        //2，将front后移，考虑取模
        //3，将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }
    //显示队列中的所有数据
    public void show() {
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        //思考：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n" , i % maxSize , arr[i % maxSize]);
        }
    }
    //求出当前队列中的有效个数
    public int size(){
        return (reat + maxSize -front) % maxSize;
    }
    //显示队列的头数据,注意不是取数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("数据为空！");
        }
        return arr[front];
    }
}