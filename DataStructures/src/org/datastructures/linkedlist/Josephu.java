package org.datastructures.linkedlist;

/**
 * 约瑟夫问题，环形链表
 * @author lijichen
 * @date 2020/8/22 - 18:51
 */
public class Josephu {
    public static void main(String[] args) {
        //创建环形链表实例
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //添加小孩
        circleSingleLinkedList.addBoy(5);
        //遍历小孩
        circleSingleLinkedList.showCircle();
        System.out.println("*********出圈************");
        //约瑟夫问题，出圈
        circleSingleLinkedList.countBoy(1,2,5);
    }
}
//环形链表管理类
class CircleSingleLinkedList {
    //先维护一个不能动的first
    public Boy first = null;
    //添加小孩数据
    public void addBoy(int nums) {
        if (nums <= 0) {
            System.out.println(nums+"不正确！");
            return;
        }
        //维护一个指针
        Boy curBoy = null;
        //循环创建小孩环形链表
        for (int i = 1; i <= nums; i++) {
            Boy boy = new Boy(i);
            //如果是创建的第一个小孩
            if (i == 1) {
                first = boy;//第一个小孩
                first.setNext(boy);//修改first为第一个小孩，形成环形，自己的next指向自己
                curBoy = first;//将指针初始化为第一个小孩
            } else {
                //之后的小孩
                curBoy.setNext(boy);//第二次循环时，第一个小孩的下一个改为boy2
                boy.setNext(first);//第二次循环时，将第二个小孩的next指向first形成环形
                curBoy = boy;//第二次循环时，将指针修改为第二个小孩，往后循环
            }
        }
    }

    //出圈，约瑟夫问题
    /*
    * startNo:开始位置
    * countNum:报数次数
    * nums:几个小孩
    * */
    public void countBoy(int startNo, int countNum, int nums) {
        //对传入的数值进行校验
        if (first == null || startNo < 1 || startNo > nums) {
            System.out.println("输入的数据有误！");
            return;
        }
        //创建辅助指针，帮助小孩出圈
        Boy helper = first;
        //辅助指针应该指向环形链表的最后一个节点
        while (true) {
            if (helper.getNext() == first) {//说明找到了最后的节点
                break;
            }
            //没有找到就指针后移
            helper = helper.getNext();
            //循环完毕后，helper始终在出圈的前一个，即helper.getnext指向的就是first，直到只剩一个小孩
        }
        //小孩报数前，先让first和helper移动，startNo - 1次，即从哪里开始数数
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //当小孩报数时，让first和helper指针同事后移 countNum - 1次，因为自己也要报数，所以要减一
        //循环报数，直到圈中只有一个节点
        while (true) {
            if (helper == first) {
                break;//已经到最后
            }
            //开始后移countNum - 1
            //无论如何，helper都要是出圈小孩的上一个，它指向的是出圈的first，除非只剩一个小孩
            //所以first进行报数的时候，helper也要进行报数，然后进行出圈操作，即修改指针指向
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();//helper是出圈小孩的上一个，它指向的是出圈的first
            }
            //后移之后
            System.out.printf("出圈的小孩是%d\n", first.getNo());
            //修改链的指向
            first = first.getNext();
            helper.setNext(first);
        }
        //此时队列出圈只剩一个人
        System.out.printf("最后的小孩是%d\n", helper.getNo());
    }

    //遍历环形链表
    public void showCircle() {
        if (first == null) {
            System.out.println("环形链表为空！");
            return;
        }
        //辅助指针
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号是：%d\n",curBoy.getNo());
            //如果相等说明找到了最后
            if (curBoy.getNext() == first) {
                break;
            }
            //如果没有找到了最后
            //指针后移、
            curBoy = curBoy.getNext();
        }
    }
}
//小孩类
class Boy {
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    public int getNo() {
        return no;
    }

    public Boy getNext() {
        return next;
    }
}
