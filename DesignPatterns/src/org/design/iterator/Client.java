package org.design.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/17 - 14:31
 */
public class Client {
    public static void main(String[] args) {
        //开始迭代
        //创建两个学院
        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();
        //添加到集合中
        List<College> coll = new ArrayList<>();
        coll.add(computerCollege);
        coll.add(infoCollege);
        //创建输出类
        OutputImpl output = new OutputImpl(coll);
        //第一种迭代
        output.printCollegeAll();
        System.out.println("_______________________________________");
        //第二种迭代
        output.printAll();
    }
}
//系类
class Department {
    private String name;
    private String desc;

    public Department(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
//计算机学院迭代器
class ComputerCollegeIterator implements Iterator {
    //计算机学院使用数组存储
    Department[] departments;
    //当前对象个数
    int numOfDepartment = 0;

    //构造器，初始化值
    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    //判断是否有值
    @Override
    public boolean hasNext() {
        if (numOfDepartment >= departments.length || departments[numOfDepartment] == null){
            return false;
        }
        return true;
    }

    //获取元素
    @Override
    public Object next() {
        Department department = departments[numOfDepartment];
        numOfDepartment += 1;
        return department;
    }

    @Override
    public void remove() {
        departments[numOfDepartment] = null;
    }
}
//信息学院迭代器
class InfoCollegeIterator implements Iterator {
    //使用数组存储
    List<Department> departments;
    //记录下标
    int index = -1;

    //初始化值
    public InfoCollegeIterator(List<Department> departments) {
        this.departments = departments;
    }

    //获取元素
    @Override
    public boolean hasNext() {
        if (index >= departments.size() -1) {
            return false;
        } else {
            index += 1;
            return true;
        }
    }

    @Override
    public Object next() {
        return departments.get(index);
    }

    @Override
    public void remove() {
        departments.remove(index);
    }
}
//学院接口，用来管理department
interface College {
    String getName();
    void addDepartment(Department department);//添加
    Iterator createIterator();//返回一个迭代器对象就是继承了iterator的两个类
}
//电脑学院类
class ComputerCollege implements College {
    //创建一个电脑学院的数组
    Department[] departments;
    //记录个数
    int count = 0;

    //初始化值
    public ComputerCollege() {
        departments = new Department[5];
        addDepartment(new Department("电脑学院","电脑学院"));
        addDepartment(new Department("电脑学院2","电脑学院2"));
        addDepartment(new Department("电脑学院3","电脑学院3"));
    }

    @Override
    public String getName() {
        return "电脑学院";
    }

    @Override
    public void addDepartment(Department department) {
        departments[count++] = department;
    }

    @Override
    public Iterator createIterator() {
        return new ComputerCollegeIterator(departments);
    }
}
//信息学院类
class InfoCollege implements College {
    //信息学院使用list集合
    List<Department> departmentList;
    //初始化值


    public InfoCollege() {
        departmentList = new ArrayList<>();
        addDepartment(new Department("信息学院","信息学院"));
        addDepartment(new Department("信息学院2","信息学院2"));
        addDepartment(new Department("信息学院3","信息学院3"));
    }

    @Override
    public String getName() {
        return "信息学院";
    }

    @Override
    public void addDepartment(Department department) {
        departmentList.add(department);
    }

    @Override
    public Iterator createIterator() {
        return new InfoCollegeIterator(departmentList);
    }
}
//输出类
class OutputImpl {
    //创建学院集合，里面可以有计算机学院和信息学院
    List<College> collegeList;

    public OutputImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }
    //输出某个学院系的方法1:
    public void printCollegeOneDepartment(College college) {
        Iterator iterator = college.createIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    //输出所有学院+系1:
    public void printCollegeAll() {
        for (College college : collegeList) {
            printCollegeOneDepartment(college);
        }
    }
    //输出某个学院系的方法2:
    public void printAll(){

        //迭代出所有的学院
        Iterator<College> iterator = collegeList.iterator();
        while ((iterator.hasNext())) {
            College next = iterator.next();
            System.out.println("______________"+next.getName()+"________________");
            printIterator(next.createIterator());
        }
    }
    //输出所有学院+系2:
    public void printIterator(Iterator iterator){
        while ((iterator.hasNext())) {
            Department next = (Department) iterator.next();
            System.out.println(next);
        }
    }
}