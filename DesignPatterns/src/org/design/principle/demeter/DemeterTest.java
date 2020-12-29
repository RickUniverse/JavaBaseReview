package org.design.principle.demeter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijichen
 * @date 2020/8/11 - 16:37
 */
//符合迪米特法则：
//满足最小知道原则之后的代码
public class DemeterTest {
    public static void main(String[] args) {
        SchoolManager s = new SchoolManager();
        s.show(new CollegeManager());
    }
}
//总部人
class Employee {
    private int id;

    public Employee(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                '}';
    }
}
//学院人
class CollegeEmployee {
    private int id;

    public CollegeEmployee(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CollegeEmployee{" +
                "id=" + id +
                '}';
    }
}
//学院工具类
class CollegeManager {
    //获取学院人，
    public List<CollegeEmployee> getCollegeEmployee(){
        List<CollegeEmployee> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new CollegeEmployee(i));
        }
        return list;
    }
    //展示学院人，展示的代码要写在学院工具类中，保持最小知道原则
    public void showCollegeEmployee(){
        System.out.println("学院人（****************）");
        for (CollegeEmployee collegeEmployee : getCollegeEmployee()) {
            System.out.println(collegeEmployee);
        }
    }
}
//总部工具类
class SchoolManager{
    //获取总部人，
    public List<Employee> getEmployee(){
        List<Employee> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Employee(i));
        }
        return list;
    }
    //展示总部人
    public void showCollegeEmployee(){
        System.out.println("总部人（****************）");
        for (Employee employee : getEmployee()) {
            System.out.println(employee);
        }
    }
    //输出总部人和摸个学院人
    public void show(CollegeManager col){
        //学院人
        col.showCollegeEmployee();
        //总部人
        showCollegeEmployee();
    }
}