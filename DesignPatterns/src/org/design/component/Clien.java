package org.design.component;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合模式
 * @author lijichen
 * @date 2020/8/14 - 20:34
 */
public class Clien {
    public static void main(String[] args) {
        //先创建大学
        University university = new University("可以", "好学大学");

        //创建学院
        College college = new College("asdf", "wwwww");
        College college1 = new College("asdf", "额鹅鹅鹅");
        //往学员中添加系别
        college.add(new Department("系别1","信息系"));
        college.add(new Department("系别2","信息系2"));
        college.add(new Department("系别3","信息系3"));
        college1.add(new Department("系别4","信息系4"));
        college1.add(new Department("系别5","信息系5"));
        college1.add(new Department("系别6","信息系6"));
        //将学院添加到大学中
        university.add(college);
        university.add(college1);

        //输出大学管理的学院
        university.print();
    }
}
//抽象类
//定义了属性和方法
abstract class OrganizationComponent {
    private String desc;
    private String name;

    public OrganizationComponent(String desc, String name) {
        this.desc = desc;
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    protected void add(OrganizationComponent organizationComponent){}
    protected void remove(OrganizationComponent organizationComponent){}
    protected abstract void print();
}
//大学
//管理 college
class University extends OrganizationComponent {

    //创建集合，管理学院：college
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public University(String desc, String name) {
        super(desc, name);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponentList.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        super.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("_____________________"+super.getName()+"_______________________");
        for (OrganizationComponent organizationComponent : organizationComponentList) {
            //System.out.println(organizationComponent.getName()+"描述："+organizationComponent.getDesc());
            organizationComponent.print();
        }
    }
}
//学院
//管理 Department
class College extends OrganizationComponent {

    //创建集合，管理学院：college
    List<OrganizationComponent> organizationComponentList = new ArrayList<>();

    public College(String desc, String name) {
        super(desc, name);
    }

    @Override
    protected void add(OrganizationComponent organizationComponent) {
        organizationComponentList.add(organizationComponent);
    }

    @Override
    protected void remove(OrganizationComponent organizationComponent) {
        super.remove(organizationComponent);
    }

    @Override
    protected void print() {
        System.out.println("_____________________"+super.getName()+"_______________________");
        for (OrganizationComponent organizationComponent : organizationComponentList) {
            //System.out.println(organizationComponent.getName()+"描述："+organizationComponent.getDesc());
            organizationComponent.print();
        }
    }
}
//叶子节点
//没有添加和删除方法，集合也没有
class Department extends OrganizationComponent {

    public Department(String desc, String name) {
        super(desc, name);
    }

    @Override
    protected void print() {
        System.out.println(super.getName()+"描述："+super.getDesc());
    }
}