package org.datastructures.hashtab;

import java.util.Scanner;

/**
 * 哈希表/散列表
 * @author lijichen
 * @date 2020/8/30 - 18:09
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建hashtab
        HashTab hashTab = new HashTab(7);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加");
            System.out.println("list:遍历");
            System.out.println("find:查找");
            System.out.println("del:删除");
            System.out.println("exit:退出");
            System.out.println("请输入操作：");
            switch (scanner.next()) {
                case "add":
                    System.out.println("请输入id：");
                    int id = scanner.nextInt();
                    System.out.println("请输入name：");
                    String name = scanner.next();
                    hashTab.add(new Emp(id,name));
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("输入编号：");
                    Emp empById = hashTab.findEmpById(scanner.nextInt());
                    if (empById != null) {
                        System.out.println(empById.name);
                    }else {
                        System.out.println("未找到！");
                    }
                    break;
                case "del":
                    System.out.println("输入编号：");
                    id = scanner.nextInt();
                    System.out.println(hashTab.delete(id));
                    break;
                case "exit":
                    scanner.close();
                    return;
                default:
                    System.out.println("输入错误！");
                    break;
            }
        }
    }
}

//创建hashtab管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;//链表数组的长度

    public HashTab(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        //需要依次初始化链表数组
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //数据都是通过hashtab添加的
    public void add(Emp emp) {
        empLinkedListArray[hasFun(emp.id)].add(emp);//添加到链表
    }
    //遍历链表
    public void list() {
        for (int i = 0; i < empLinkedListArray.length; i++) {
            empLinkedListArray[i].list(i);
        }
    }
    //获取添加到那个链表，
    //取模
    public int hasFun(int id) {
        return id % size;
    }

    //找到emp
    public Emp findEmpById(int id) {
        return empLinkedListArray[hasFun(id)].findEmpById(id);
    }

    //删除emp
    public boolean delete(int id){
        return empLinkedListArray[hasFun(id)].delete(id);
    }
}

//员工类
class Emp {
    public int id;
    public String name;
    public Emp next;//链表；指向下一个，默认为空
    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
//创建链表
class EmpLinkedList {
    //头节点，是有效的
    Emp head;
    //添加的方法
    public void add(Emp emp) {
        if (head == null) {//直接赋值给头节点
            head = emp;
            return;
        }
        //维护一个辅助指针
        Emp temp = head;
        while (temp.next != null) {
            temp = temp.next;//指针后移
        }
        //直到找到为空的位置
        temp.next = emp;//赋值
    }
    //显示所有列表的方法
    public void list(int no) {
        if (head == null) {//表为空
            System.out.println("链表："+(no+1)+"空");
            return;
        }
        //维护指针
        Emp temp = head;
        while (temp != null) {
            System.out.printf("=》那个链表:"+(no+1)+",id = %d,name = %s\t\t",temp.id,temp.name+"");
            temp = temp.next;//指针后移
        }
        System.out.println();
    }
    //查找emp
    public Emp findEmpById(int id) {
        if (head == null) {
            return null;
        }
        //辅助指针
        Emp temp = head;
        while (true) {
            if (temp.id == id) {//如果相等当前的temp就是要找的emp
                break;
            }
            if (temp.next == null) {//如果下一个为空，退出
                temp = null;//置空temp
                break;
            }
            temp = temp.next;//指针后移
        }
        return temp;//返回
    }
    //删除emp
    public boolean delete(int id) {
        if (head == null) {
            return false;
        }
        //如果头节点就是需要找的emp
        if (head.id == id) {
            head = head.next;//自我删除
            return true;
        }
        Emp temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next.id == id) {//如果找到了
                flag = true;
                break;
            }
            if (temp.next == null) {//找到头了
                //没找到
                flag = false;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;//删除找到的节点t
            return true;
        } else {
            return false;
        }
    }
}